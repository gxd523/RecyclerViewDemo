package androidx.recyclerview.widget

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.demo.recyclerview.R
import kotlinx.android.synthetic.main.layout_adapter.view.*
import java.util.*

class MyAdapter(private val onItemClick: (View, Int) -> Unit) : RecyclerView.Adapter<ViewHolder>() {
    private val dataList by lazy { listOf(*Array(20) { it }) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = RecyclerCacheViewHolder(parent, onItemClick)
        Log.e("gxd", "ViewHolder = ${viewHolderHashCodeList.size}")
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.e("gxd", "onBindViewHolder...${holder}")
        if (holder is RecyclerCacheViewHolder) {
            holder.bindData((dataList[position]).toString())
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class RecyclerCacheViewHolder(parent: ViewGroup, onItemClick: (View, Int) -> Unit) :
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_adapter, parent, false)) {
        fun bindData(data: String) {
            itemView.titleTextView.text = "index = ${toString()}, pos = $data"
            itemView.setBackgroundColor(if (adapterPosition % 2 == 0) 0x33666666 else -0x1)
            itemView.postDelayed({
                //                    itemView.setDrawingCacheEnabled(true);
//                    Bitmap bitmap = Bitmap.createBitmap(itemView.getDrawingCache());
//                    itemView.setDrawingCacheEnabled(false);
//                    myView.setBackground(new BitmapDrawable(myView.getResources(), bitmap));
            }, 200)
        }

        init {
            viewHolderHashCodeList.add(hashCode())
            itemView.setOnClickListener { v -> onItemClick.invoke(v, layoutPosition) }
            itemView.setBackgroundColor(-0x1)
        }

        override fun toString(): String {
            return "${viewHolderHashCodeList.indexOf(hashCode())}"
        }
    }

    companion object {
        var viewHolderHashCodeList: MutableList<Int> = ArrayList()
    }
}