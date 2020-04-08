package ndk.pax.com.paxtakeout.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.model.bean.GoodInfo
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter

/**
 * User：Rowen
 * Description:商品右侧列表适配器
 * 时间: 2020/4/7:23:13
 *
 */

class GoodsAdapter(val context:Context?):BaseAdapter(), StickyListHeadersAdapter{
     var arrayTypeGoodLists:List<GoodInfo.ListBeanX.ListBean> = ArrayList()

    fun setData(arrayTypeGoodLists:ArrayList<GoodInfo.ListBeanX.ListBean>){
        this.arrayTypeGoodLists=arrayTypeGoodLists
        notifyDataSetChanged()
    }

    //内部类 holder
    inner class GoodsItemHolder(itemView:View) : View.OnClickListener {
        override fun onClick(v: View?) {

        }


        fun bindData(goodInfo: GoodInfo.ListBeanX.ListBean) {
            tvName.text=goodInfo.name

        }

        lateinit var ivIcon:ImageView
        lateinit var tvName:TextView
        lateinit var tvForm:TextView
        lateinit var tvMonthSale:TextView
        lateinit var tvNewPrice:TextView
        lateinit var tvOldPrice:TextView
        lateinit var tvCount:TextView
        lateinit var btnAdd: ImageButton
        lateinit var btnMinuss:ImageButton


        init {
            ivIcon= itemView.findViewById<ImageView>(R.id.iv_icon)
            tvName= itemView.findViewById<TextView>(R.id.tv_name)
            tvForm= itemView.findViewById<TextView>(R.id.tv_form)
            tvMonthSale= itemView.findViewById<TextView>(R.id.tv_month_sale)

            tvOldPrice= itemView.findViewById<TextView>(R.id.tv_oldprice)
            tvNewPrice= itemView.findViewById<TextView>(R.id.tv_newprice)

            tvCount= itemView.findViewById<TextView>(R.id.tv_count)

            btnAdd= itemView.findViewById<ImageButton>(R.id.ib_add)
            btnMinuss= itemView.findViewById<ImageButton>(R.id.ib_minus)
            btnAdd.setOnClickListener(this)
            btnMinuss.setOnClickListener(this)
        }

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var itemView:View
            val goodHolder:GoodsItemHolder
            if(convertView==null){
                itemView=LayoutInflater.from(context).inflate(R.layout.item_goods,parent,false)
                goodHolder=GoodsItemHolder(itemView)
                itemView.tag=goodHolder
            }else{
                itemView=convertView
                goodHolder=itemView.tag as GoodsItemHolder
            }
            //绑定数据
            goodHolder.bindData(arrayTypeGoodLists.get(position))
            return itemView
    }

    override fun getItem(position: Int): Any {
            return arrayTypeGoodLists.get(position)
    }

    override fun getItemId(position: Int): Long =position.toLong()

    override fun getCount(): Int =arrayTypeGoodLists.size

    override fun getHeaderId(position: Int): Long {
        val goodInfo = arrayTypeGoodLists.get(position)
        return goodInfo.typeId.toLong()
    }

    override fun getHeaderView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val goodInfo = arrayTypeGoodLists.get(position)
        val typeName = goodInfo.typeName


        val textView=TextView(context)
        textView.text=typeName
        textView.setTextColor(Color.RED)
        return textView
    }

}