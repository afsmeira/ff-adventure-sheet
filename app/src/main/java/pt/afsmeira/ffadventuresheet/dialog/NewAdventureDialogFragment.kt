package pt.afsmeira.ffadventuresheet.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.model.Book

/**
 * Dialog for confirming the creation of a new adventure for `book`.
 *
 * The dialog has a title, a message, a positive and a negative button.
 *
 * Callers creating the dialog will be notified of an affirmative response via the
 * `newAdventureClickListener`.
 */
class NewAdventureDialogFragment(
    private val book: Book,
    private val newAdventureClickListener: NewAdventureClickListener
) : DialogFragment() {

    /**
     * Listener to notify callers of this dialog that the user confirmed the creation of a new
     * adventure.
     */
    interface NewAdventureClickListener {

        /**
         * Notify callers of the [Book] to be used to create the new adventure.
         */
        fun onNewAdventureClick(book: Book)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(activity)
            .setTitle(R.string.dialog_new_adventure_title)
            .setMessage(getString(R.string.dialog_new_adventure_body, book.name))
            .setPositiveButton(android.R.string.yes) { _, _ ->
                newAdventureClickListener.onNewAdventureClick(book)
            }
            .setNegativeButton(android.R.string.cancel) { _, _ -> }
            .create()

    companion object {
        const val TAG = "NewAdventureDialog"
    }
}
