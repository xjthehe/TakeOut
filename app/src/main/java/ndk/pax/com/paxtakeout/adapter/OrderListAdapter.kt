package ndk.pax.com.paxtakeout.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import ndk.pax.com.paxtakeout.model.bean.Order
import ndk.pax.com.paxtakeout.widget.OrderListItemView

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/1:19:17
 *
 */

class OrderListAdapter(val context :Context,val mDatas:List<Order>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return OrderViewHolder(OrderListItemView(context))
    }

    override fun getItemCount(): Int =mDatas.size

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, position: Int) {
        val itemView = p0.itemView as OrderListItemView
        itemView.bindView(mDatas.get(position))
    }

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

}