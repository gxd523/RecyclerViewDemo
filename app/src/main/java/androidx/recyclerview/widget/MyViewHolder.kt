package androidx.recyclerview.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.recyclerview.R
import kotlinx.android.synthetic.main.layout_adapter.view.*

class MyViewHolder(parent: ViewGroup, onItemClick: ((View, Int) -> Unit)?) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_adapter, parent, false)) {
    fun onBindViewHolder(data: String) {
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
        MyAdapter.viewHolderHashCodeList.add(hashCode())
        onItemClick?.also {
            itemView.setOnClickListener { v -> it.invoke(v, layoutPosition) }
        }
        itemView.setBackgroundColor(-0x1)
    }

    override fun toString(): String {
        return "${MyAdapter.viewHolderHashCodeList.indexOf(hashCode()) + 1}"
    }
}