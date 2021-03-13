package pt.afsmeira.ffadventuresheet.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.model.Adventure

class DeleteAdventureDialogFragment(
    private val adventure: Adventure,
    private val deleteAdventureClickListener: DeleteAdventureClickListener
) : DialogFragment() {

    interface DeleteAdventureClickListener {

        fun onDeleteAdventureClick(adventure: Adventure)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(activity)
            .setTitle(R.string.dialog_delete_adventure_title)
            .setMessage(R.string.dialog_delete_adventure_message)
            .setPositiveButton(android.R.string.yes) { _, _ ->
                deleteAdventureClickListener.onDeleteAdventureClick(adventure)
            }
            .setNegativeButton(android.R.string.cancel) { _, _ -> }
            .create()

    companion object {
        const val TAG = "DeleteAdventureDialog"
    }
}
