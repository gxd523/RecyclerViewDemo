package androidx.recyclerview.widget

import android.util.Log
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlin.math.max

abstract class PreloadAdapter : RecyclerView.Adapter<ViewHolder>() {
    /**
     * preload threshold. [onPreload] will be called if there is [preloadItemCount] items remain in the list
     */
    var preloadItemCount = 0

    /**
     * an lambda will be invoked in [onBindViewHolder] when preload threshold is satisfied
     * implement this lambda with actual data loading action
     */
    var onPreload: (() -> Unit)? = null

    /**
     * scroll state of [RecyclerView] which this adapter attached to
     */
    private var scrollState = SCROLL_STATE_IDLE

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        checkPreload(position)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        /**
         * keep scroll state in [scrollState] which is used in preloading
         */
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                scrollState = newState
            }
        })
    }

    /**
     * check if preload threshold is satisfied
     */
    private fun checkPreload(position: Int) {
        if (onPreload != null
            && position == max(itemCount - 1 - preloadItemCount, 0)// reach the preload threshold position
            && scrollState != SCROLL_STATE_IDLE // the list is scrolling
        ) {
            Log.d("gxd", "position = $position")
            onPreload?.invoke()
        }
    }

//    var onDetachedFromRecyclerView: ((recyclerView: RecyclerView) -> Unit)? = null
//    var onFailedToRecycleView: ((holder: ViewHolder) -> Boolean)? = null
//    var onViewAttachedToWindow: ((holder: ViewHolder) -> Unit)? = null
//    var onViewDetachedFromWindow: ((holder: ViewHolder) -> Unit)? = null
//    var onViewRecycled: ((holder: ViewHolder) -> Unit)? = null
//
//    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
//        onDetachedFromRecyclerView?.invoke(recyclerView)
//    }
//
//    override fun onFailedToRecycleView(holder: ViewHolder): Boolean {
//        return onFailedToRecycleView?.invoke(holder) ?: return super.onFailedToRecycleView(holder)
//    }
//
//    override fun onViewAttachedToWindow(holder: ViewHolder) {
//        onViewAttachedToWindow?.invoke(holder)
//    }
//
//    override fun onViewDetachedFromWindow(holder: ViewHolder) {
//        onViewDetachedFromWindow?.invoke(holder)
//    }
//
//    override fun onViewRecycled(holder: ViewHolder) {
//        onViewRecycled?.invoke(holder)
//    }
}