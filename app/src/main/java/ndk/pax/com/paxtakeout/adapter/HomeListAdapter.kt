package ndk.pax.com.paxtakeout.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import ndk.pax.com.paxtakeout.model.SellerListItem
import ndk.pax.com.paxtakeout.ui.activity.BusinessActivity
import ndk.pax.com.paxtakeout.widget.HomeHeadView
import ndk.pax.com.paxtakeout.widget.HomeListItemView

/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/23:10:20
 *
 */

class HomeListAdapter(val context:Context,val mdatas:ArrayList<SellerListItem>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object {
        val ITEM_TYPE_HEAD_VIEW=0
        val ITEM_TYPE_ITEM_VIEW=1
    }

    override fun getItemViewType(position: Int): Int {
        if(position==0){
            return ITEM_TYPE_HEAD_VIEW
        }else{
            return ITEM_TYPE_ITEM_VIEW
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType==ITEM_TYPE_HEAD_VIEW){
            return HomeHeadViewHolder(HomeHeadView(context))
        }else {
            return HomeItemViewHolder(HomeListItemView(context))
        }
    }



   override fun getItemCount(): Int {
       if(mdatas.size>0){
          return mdatas.size+1
       }else{
           return 0
       }
   }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position)== ITEM_TYPE_HEAD_VIEW){
            val homeHeadView= holder.itemView as HomeHeadView
            homeHeadView.bindView("我是大哥。。。。。。。。。。。。。。")
        }else{
            val homeListItemView= holder.itemView as HomeListItemView
            homeListItemView.bindView(mdatas[position-1])
            homeListItemView.setOnClickListener{
                val intent:Intent=Intent(context, BusinessActivity::class.java)
                context.startActivity(intent)
            }
        }
    }

    class HomeItemViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){

    }


    class HomeHeadViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){

    }
}