package androidx.recyclerview.widget

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.util.*

class MyAdapter(private val onItemClick: (View, Int) -> Unit) : RecyclerView.Adapter<ViewHolder>() {
    private val dataList by lazy { listOf(*Array(20) { it }) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = MyViewHolder(parent, onItemClick)
        Log.e("gxd", "ViewHolder = ${viewHolderHashCodeList.size}")
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.e("gxd", "onBindViewHolder...${holder}")
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