package pt.afsmeira.ffadventuresheet.ui.adapter

import androidx.recyclerview.widget.RecyclerView

/**
 * [RecyclerView.Adapter] for displaying an array of data of type `T`, on a [RecyclerView].
 *
 * The data is required to be displayed on a view class that extends [DataAdapter.View].
 *
 * Callers using the adapter will be notified of a click on a [DataAdapter.View] via the provided
 * `clickListener`, that implements [DataAdapter.ClickListener].
 */
abstract class DataAdapter<T>(
    private val data: Array<T>,
    private val clickListener: ClickListener<T>
) : RecyclerView.Adapter<DataAdapter.View<T>>() {

    /**
     * Listener to notify callers of this adapter that a [DataAdapter.View] was clicked.
     */
    interface ClickListener<T> {

        /**
         * Notify callers of which data item was bound to the clicked [DataAdapter.View].
         */
        fun onDataItemClicked(dataItem: T)
    }

    /**
     * The view holder for a data item of type `T`.
     *
     * Extending classes should not call any of the methods of this class.
     *
     * @param self The actual underlying Android view that supports this view holder.
     */
    abstract class View<T>(private val self: android.view.View) : RecyclerView.ViewHolder(self) {

        /**
         * Bind this view to `dataItem` and to `clickListener`.
         */
        fun bind(dataItem: T, clickListener: ClickListener<T>) {
            self.setOnClickListener {
                clickListener.onDataItemClicked(dataItem)
            }

            bind(dataItem)
        }

        /**
         * Bind this view to `dataItem`.
         */
        abstract fun bind(dataItem: T)
    }

    override fun onBindViewHolder(holder: View<T>, position: Int) =
        holder.bind(data[position], clickListener)

    override fun getItemCount(): Int = data.size
}
