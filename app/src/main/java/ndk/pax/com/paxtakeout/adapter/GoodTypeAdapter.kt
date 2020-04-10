package ndk.pax.com.paxtakeout.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_goods.*
import kotlinx.android.synthetic.main.item_type.view.*
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.R.id.type
import ndk.pax.com.paxtakeout.model.bean.GoodInfo
import ndk.pax.com.paxtakeout.ui.fragment.GoodsFragment
import ndk.pax.com.paxtakeout.widget.GoodListItemView

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/7:20:18
 *
 */

class GoodTypeAdapter(val context:Context?,val goodFragment:GoodsFragment):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var goodsTypeList:List<GoodInfo.ListBeanX> = listOf()

    //数据载入,刷新适配器
    fun setData(goodsTypeList: List<GoodInfo.ListBeanX>, arrayTypeGoodLists: ArrayList<GoodInfo.ListBeanX.ListBean>){
        this.goodsTypeList=goodsTypeList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder{
        //解析布局
        val itemView=LayoutInflater.from(context).inflate(R.layout.item_type,parent,false)
        return GoodTypeViewHolder(itemView)
    }


    override fun getItemCount(): Int=goodsTypeList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val goodsTypeItemHolder=holder as GoodTypeViewHolder
        goodsTypeItemHolder.bindData(goodsTypeList.get(position),position)
    }

    var selectPosition:Int=0//选中的位置

    inner class GoodTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvType:TextView
        val tvRedDotCount:TextView//右上角红色数量

        var mPosition:Int=0
        lateinit var goodTypeInfo:GoodInfo.ListBeanX
        init {
            tvType= itemView.findViewById<TextView>(R.id.type)
            tvRedDotCount= itemView.findViewById<TextView>(R.id.tvRedDotCount)

            //左侧点击事件
            itemView.setOnClickListener {
                Log.e("setOnClickListener",mPosition.toString())
                selectPosition=mPosition
                notifyDataSetChanged()
                //右侧列表转到该类型的第一个商品
                val typeId=goodTypeInfo.id
                //遍历所有集合找到第一个id相同的
                val position=goodFragment.goodsFragmentPresenter.getGoodsPositionByTypeId(typeId)
                //右侧listview 选中position部分
                goodFragment.slhlv.setSelection(position)

            }
        }

        fun bindData(goodInfo: GoodInfo.ListBeanX, position: Int){
            mPosition=position
            this.goodTypeInfo=goodInfo

            if(position==selectPosition){
                itemView.setBackgroundColor(Color.WHITE)
                tvType.setTextColor(Color.BLACK)
                tvType.setTypeface(Typeface.DEFAULT_BOLD)
            }else{
                itemView.setBackgroundColor(Color.parseColor("#b9dedcdc"))
                tvType.setTextColor(Color.GRAY)
                tvType.setTypeface(Typeface.DEFAULT)
            }

            tvType.text=goodInfo.name
            tvRedDotCount.text=goodInfo.redDotCount.toString()

            if(goodTypeInfo.redDotCount>0){
                tvRedDotCount.visibility=View.VISIBLE
            }else{
                tvRedDotCount.visibility=View.GONE
            }

        }
    }

}