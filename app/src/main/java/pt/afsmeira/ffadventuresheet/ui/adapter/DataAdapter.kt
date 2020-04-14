package pt.afsmeira.ffadventuresheet.ui.adapter

import androidx.recyclerview.widget.RecyclerView

/**
 * [RecyclerView.Adapter] for displaying an array of data of type `T`, on a [RecyclerView].
 *
 * The data is required to be displayed on a view class that extends [DataAdapter.View].
 */
abstract class DataAdapter<T>(val data: List<T>) :
    RecyclerView.Adapter<DataAdapter.View<T>>() {

    /**
     * The view holder for a data item of type `T`. This view holder can notify callers of click
     * events.
     *
     * Extending classes should not call any of the methods of this class.
     *
     * @param self The actual underlying Android view that supports this view holder.
     * @param clickListener The listener to be called when this view (as a whole) is clicked.
     *        [clickListener] is reassigned to the view every time the view is bound to a new data
     *        item. Default value is `null`.
     */
    abstract class View<T>(
        private val self: android.view.View,
        private val clickListener: ClickListener<T>? = null
    ) : RecyclerView.ViewHolder(self) {

        /**
         * Bind this view to [dataItem] and to [clickListener].
         */
        fun preBind(dataItem: T) {
            self.setOnClickListener {
                clickListener?.onDataItemClicked(dataItem)
            }
            bind(dataItem)
        }

        /**
         * Bind this view to [dataItem].
         */
        abstract fun bind(dataItem: T)

        /**
         * Listener for click events on a [DataAdapter.View].
         */
        interface ClickListener<T> {

            /**
             * Notify callers of which data item was bound to the clicked [DataAdapter.View].
             */
            fun onDataItemClicked(dataItem: T)
        }
    }

    override fun onBindViewHolder(holder: View<T>, position: Int) = holder.preBind(data[position])

    override fun getItemCount(): Int = data.size
}
