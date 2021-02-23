package androidx.recyclerview.widget

import android.app.Activity
import android.os.Bundle
import android.util.Log

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recyclerView = RecyclerView(this)
        setContentView(recyclerView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = MyAdapter(10).apply {
                onItemClick = { _, position ->
                    recyclerView.adapter?.notifyItemRemoved(position)
                    printCacheInfo(recyclerView)
                }
                preloadItemCount = 1
                onPreload = {
                    if (dataList.size < 20) {
                        dataList += listOf(*Array(5) { dataList.size + it })
                        Log.d("gxd", "dataList = ${dataList.size}")
                        post { notifyDataSetChanged() }
                    }
                }
            }
        }
    }

    private fun printCacheInfo(recyclerView: RecyclerView) {
        val mRecycler = recyclerView.mRecycler
        if (mRecycler.mAttachedScrap.size > 0) {
            Log.d("gxd", "mAttachedScrap = ${mRecycler.mAttachedScrap}")
        }
        if (mRecycler.mChangedScrap != null && mRecycler.mChangedScrap.size > 0) {
            Log.d("gxd", "mChangedScrap = ${mRecycler.mChangedScrap}")
        }

//        Log.d("gxd", "mCachedViews max size = ${mRecycler.mViewCacheMax}")
        if (mRecycler.mCachedViews.size > 0) {
            Log.d("gxd", "mCachedViews = ${mRecycler.mCachedViews}")
        }

//        val mScrap = mRecycler.recycledViewPool.mScrap[0]
//        Log.d("gxd", "recycledViewPool max size = ${mScrap.mMaxScrap}")
        val mScrap = mRecycler.recycledViewPool.mScrap[0]
        if (mScrap.mScrapHeap.size > 0) {
            Log.d("gxd", "recycledViewPool = ${mScrap.mScrapHeap}")
        }
    }
}