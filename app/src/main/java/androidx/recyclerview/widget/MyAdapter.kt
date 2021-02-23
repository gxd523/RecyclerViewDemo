package androidx.recyclerview.widget

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.util.*

class MyAdapter(private val count: Int) : PreloadAdapter() {
    var onItemClick: ((View, Int) -> Unit)? = null
    val dataList by lazy { listOf(*Array(count) { it + 1 }).toMutableList() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = MyViewHolder(parent, onItemClick)
        Log.e("gxd", "ViewHolder = ${viewHolderHashCodeList.size}")
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        Log.e("gxd", "onBindViewHolder...$holder...${position + 1}")
        if (holder is MyViewHolder) {
            holder.onBindViewHolder((dataList[position]).toString())
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    companion object {
        var viewHolderHashCodeList: MutableList<Int> = ArrayList()
    }
}