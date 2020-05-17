package pt.afsmeira.ffadventuresheet.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import pt.afsmeira.ffadventuresheet.model.*
import java.time.Instant

/**
 * Data access object for [Adventure].
 */
@Dao
interface AdventureDao {

    /**
     * Create a new [Adventure] and return its id.
     *
     * This method should only be called from [create].
     */
    @Insert
    suspend fun create(adventure: Adventure): Long

    /**
     * Create a new [AdventureStat].
     *
     * This method should only be called from [create].
     */
    @Insert
    suspend fun create(adventureStat: List<AdventureStat>)

    /**
     * Create a new [Adventure] for the [Book] identified by [bookId], with [stats] as the initial
     * [AdventureStat]s.
     */
    @Transaction
    suspend fun create(bookId: Long, stats: List<Stat.Typed<*>>) {
        val adventure =
            Adventure(createdAt = Instant.now(), updatedAt = Instant.now(), bookId = bookId)
        val adventureId = create(adventure)

        val adventureStats = stats.map { typedStat ->
            val typedValueJson = typedStat.currentValue.toJson()
            AdventureStat(
                adventureId,
                typedStat.id,
                Instant.now(),
                Instant.now(),
                typedValueJson,
                typedValueJson
            )
        }
        create(adventureStats)
    }

    // By reusing existing classes (with @Embedded), Room does not prefix the column names with the
    // table name, and collisions can occur when doing `SELECT *` (such as two tables defining an
    // `id` column).
    // Alternatives to this are:
    // - using the `prefix` argument in @Embbeded and using the resulting aliases directly in the
    //   query, as done below. This is error prone, but no other entities need to be defined.
    // - creating a view in the DB, and a corresponding @DatabaseView class. This might still result
    //   in collisions when doing `SELECT *` and the query would have to name the columns as they
    //   are defined in the @DatabaseView class. Plus it would create a view for a basic `JOIN`.
    // - using Room's @Relation to define relationships between entities. However, this particular
    //   use-case does not seem to be the correct fit for this annotation.

    /**
     * List all existing adventures, in reverse chronological order of when it was last played.
     */
    @Query("SELECT" +
            " adventure.id AS a_id," +
            " adventure.created_at AS a_created_at," +
            " adventure.updated_at AS a_updated_at," +
            " adventure.book_id AS a_book_id," +
            " adventure.last_paragraph AS a_last_paragraph," +
            " book.id AS b_id," +
            " book.name AS b_name," +
            " book.cover_url AS b_cover_url" +
            " FROM adventure JOIN book ON (a_book_id = b_id)" +
            " ORDER BY a_updated_at DESC")
    fun listAll(): LiveData<List<AdventureBook>>
}
