package ndk.pax.com.paxtakeout.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import ndk.pax.com.paxtakeout.widget.HomeListItemView

/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/23:10:20
 *
 */

class HomeListAdapter(val context:Context,val mdatas:ArrayList<String>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return HomeItemViewHolder(HomeListItemView(context))
    }

    override fun getItemCount(): Int =mdatas.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       val homeListItemView= holder.itemView as HomeListItemView
        homeListItemView.bindView(mdatas[position])
    }

    class HomeItemViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){

    }
}