package ndk.pax.com.paxtakeout.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageButton
import android.widget.TextView
import com.heima.takeout.utils.PriceFormater
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.R.id.tv_name
import ndk.pax.com.paxtakeout.model.bean.GoodInfo
import ndk.pax.com.paxtakeout.model.bean.goodInfo
import ndk.pax.com.paxtakeout.ui.activity.BusinessActivity
import ndk.pax.com.paxtakeout.ui.fragment.GoodsFragment

/**
 * User：Rowen
 * Description:底部购物车弹出布局
 * 时间: 2020/4/11:15:53
 *
 */

class CardRvAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val cardInfoList: ArrayList<GoodInfo.ListBeanX.ListBean> = ArrayList()
    lateinit var goodFrament:GoodsFragment
    init{
        goodFrament=(context as BusinessActivity).fragments.get(0) as GoodsFragment
    }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val bottomSheetView = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false)
        return CardViewHolder(bottomSheetView)
    }

    override fun getItemCount(): Int = cardInfoList.size

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, position: Int) {
        val cardViewHolder = p0 as CardViewHolder
        cardViewHolder.bindData(cardInfoList[position])
        
    }

    fun setData(cartGoodList: List<GoodInfo.ListBeanX.ListBean>) {
        cardInfoList.clear()
        cardInfoList.addAll(cartGoodList)
        notifyDataSetChanged()
    }

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        lateinit var goodInfo: GoodInfo.ListBeanX.ListBean

        override fun onClick(v: View?) {
            var isAdd:Boolean=false
            when(v?.id){
                R.id.ib_add->{
                    isAdd=true
                    doAddOperation()
                }

                R.id.ib_minus->{
                    isAdd=false
                    doMinusOperation()
                }


            }
            //左侧红点
            processRedDotCount(isAdd)
            //刷新购物车ui
            updateCartUi(isAdd)
        }

        private fun updateCartUi(isAdd: Boolean) {
            (goodFrament.activity as BusinessActivity).updateCart()
        }

        private fun processRedDotCount(isAdd: Boolean) {
            //1.找到此商品属于哪个类别
            val typeId = goodInfo.typeId

            //2.此类别在左侧位置
            val typePosition =goodFrament.goodsFragmentPresenter.getTypePositionByTypeId(typeId)

            //3最后找出tvReddotCount
            val goodsTypeInfo = goodFrament.goodsFragmentPresenter.allGoodTypeList.get(typePosition)

            var redDotCount = goodsTypeInfo.redDotCount
            if(isAdd){
                redDotCount++
            }else{
                redDotCount--
            }
            goodsTypeInfo.redDotCount=redDotCount
            //刷新适配器
            goodFrament.goodTypeAdapter.notifyDataSetChanged()
        }

        private fun doMinusOperation() {
            var count=goodInfo.count
            if(count==1){
                cardInfoList.remove(goodInfo)
                if(cardInfoList.size==0){
                    (context as BusinessActivity).showOrHindCart()
                }
            }else{

            }
            count--
            goodInfo.count=count
            notifyDataSetChanged()
            //右侧列表
            goodFrament.goodInfoAdapter.notifyDataSetChanged()
        }

        private fun doAddOperation() {
            //数据层count
            var count=goodInfo.count
            count++
            goodInfo.count=count
            //购物车内部数量与价格刷新
            notifyDataSetChanged()

            //右侧列表
            goodFrament.goodInfoAdapter.notifyDataSetChanged()


        }

        val tv_name: TextView
        val tv_count: TextView
        val tv_type_all_price: TextView
        val ib_minus: ImageButton
        val ib_add: ImageButton


        init {
            //初始化
            tv_name = itemView.findViewById<TextView>(R.id.tv_name)
            tv_count = itemView.findViewById<TextView>(R.id.tv_count)
            tv_type_all_price = itemView.findViewById<TextView>(R.id.tv_type_all_price)
            ib_minus = itemView.findViewById<ImageButton>(R.id.ib_minus)
            ib_add= itemView.findViewById<ImageButton>(R.id.ib_add)

            ib_minus.setOnClickListener(this)
            ib_add.setOnClickListener(this)
        }


        fun bindData(listBean: GoodInfo.ListBeanX.ListBean) {
            goodInfo=listBean
            tv_name.text = listBean.name
            tv_count.text = listBean.count.toString()
            var allPrice=listBean.count*listBean.newPrice!!.toFloat()
            tv_type_all_price.text=PriceFormater.format(allPrice)
        }
    }


}