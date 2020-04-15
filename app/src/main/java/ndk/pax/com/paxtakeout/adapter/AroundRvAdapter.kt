package ndk.pax.com.paxtakeout.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.amap.api.services.core.PoiItem
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.ui.activity.MapLocationActivity

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/15:21:13
 *
 */


class AroundRvAdapter(val context: Context):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var poiItemList:ArrayList<PoiItem> = ArrayList()

    fun setData(poiItems:ArrayList<PoiItem>){
        this.poiItemList=poiItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_around_address, p0, false)
        return RoundViewHolder(itemView)
    }

    override fun getItemCount(): Int=poiItemList.size

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val roundViewHolder = p0 as RoundViewHolder
        roundViewHolder.bindData(poiItemList[p1])
    }

    inner class RoundViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle:TextView
        val tvAddress:TextView
        init {
            tvTitle=itemView.findViewById<TextView>(R.id.tv_title)
            tvAddress=itemView.findViewById<TextView>(R.id.tv_address)
            itemView.setOnClickListener{
                val intent= Intent()
                intent.putExtra("title",tvTitle.text)
                intent.putExtra("address",tvAddress.text)
                (context as MapLocationActivity).setResult(200,intent)
                context.finish()
            }
        }

        fun  bindData(poiItem: PoiItem) {
            tvTitle.text=poiItem.title
            tvAddress.text=poiItem.snippet
        }
    }

}