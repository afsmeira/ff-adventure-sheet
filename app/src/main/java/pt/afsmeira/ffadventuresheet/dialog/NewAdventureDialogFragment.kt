package pt.afsmeira.ffadventuresheet.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.model.Book

class NewAdventureDialogFragment(
    private val book: Book,
    private val newAdventureClickListener: NewAdventureClickListener
) : DialogFragment() {

    interface NewAdventureClickListener {
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