package pt.afsmeira.ffadventuresheet.model

import java.time.Instant

/**
 * Represents an entity that can be created.
 */
interface Creatable {
    val createdAt: Instant
}

/**
 * Represents an entity that can be created and updated.
 */
interface Updateable : Creatable {
    val updatedAt: Instant
}
