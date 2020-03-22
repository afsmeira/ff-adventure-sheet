package pt.afsmeira.ffadventuresheet.ui.adapter

import androidx.recyclerview.widget.RecyclerView

/**
 * [RecyclerView.Adapter] for displaying an array of data of type `T`, on a [RecyclerView].
 *
 * The data is required to be displayed on a view class that extends [DataAdapter.View].
 */
abstract class DataAdapter<T>(
    protected val data: Array<T>
) : RecyclerView.Adapter<DataAdapter.View<T>>() {

    /**
     * The view holder for a data item of type `T`. This view holder can notify callers of click
     * events or of underlying data changes.
     *
     * Extending classes should not call any of the methods of this class.
     *
     * @param self The actual underlying Android view that supports this view holder.
     * @param clickListener The listener to be called when this view (as a whole) is clicked.
     *        [clickListener] is reassigned to the view every time the view is bound to a new data
     *        item. Default value is `null`.
     * @param dataItemChangedListener The listener to be called when the underlying data bound to
     *        this view is changed. Default value is `null`.
     */
    abstract class View<T>(
        private val self: android.view.View,
        private val clickListener: ClickListener<T>? = null,
        protected val dataItemChangedListener: DataItemChangedListener<T>? = null
    ) : RecyclerView.ViewHolder(self) {

        /**
         * Bind this view to [dataItem] and to [clickListener].
         */
        fun fullBind(dataItem: T) {
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

        /**
         * Listener for data changed events on a [DataAdapter.View].
         *
         * This event is triggered when the underlying data [T] bound to this view is changed
         * (possibly by the view itself).
         */
        interface DataItemChangedListener<T> {

            /**
             * Notify that the data underlying this [DataAdapter.View] has changed.
             *
             * @param dataItem The changed data item (already up-to-date).
             * @param position The adapter position where this item was.
             */
            fun onDataItemChanged(dataItem: T, position: Int)
        }
    }

    override fun onBindViewHolder(holder: View<T>, position: Int) = holder.fullBind(data[position])

    override fun getItemCount(): Int = data.size
}
