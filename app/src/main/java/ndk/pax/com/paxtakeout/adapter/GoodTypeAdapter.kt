package ndk.pax.com.paxtakeout.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import ndk.pax.com.paxtakeout.model.bean.GoodInfo
import ndk.pax.com.paxtakeout.widget.GoodListItemView

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/7:20:18
 *
 */

class GoodTypeAdapter(val context:Context?):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var goodsTypeList:List<GoodInfo.ListBeanX> = listOf()

    fun setData(goodsTypeList: List<GoodInfo.ListBeanX>, arrayTypeGoodLists: ArrayList<GoodInfo.ListBeanX.ListBean>){
        this.goodsTypeList=goodsTypeList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder{
        return GoodTypeViewHolder(GoodListItemView(context))
    }


    override fun getItemCount(): Int=goodsTypeList.size

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, position: Int) {
        val itemView = p0.itemView as GoodListItemView
        itemView.bindView(goodsTypeList.get(position))
    }


    class GoodTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}