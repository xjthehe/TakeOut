package ndk.pax.com.paxtakeout.widget

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import com.heima.takeout.utils.OrderObservable
import kotlinx.android.synthetic.main.item_order_item.view.*
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.model.bean.Order
import ndk.pax.com.paxtakeout.ui.activity.OrderDetailActivity

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/1:19:22
 *
 */

class OrderListItemView(context: Context?, attrs: AttributeSet?=null) : RelativeLayout(context, attrs) {
    lateinit var itemView:View
    init {
        itemView= View.inflate(context, R.layout.item_order_item,this)
    }

    fun bindView(order: Order) {
        //商家名
        tv_order_item_seller_name.text=(order.getSeller()as Order.SellerBean).name
        //订单状态
        tv_order_item_type.text=getOrderTypeInfo(order.getType())

        itemView.setOnClickListener {
            val intent:Intent=Intent(context, OrderDetailActivity::class.java)
            intent.putExtra("orderId", order.getId())
            intent.putExtra("type", order.getType())
            context.startActivity(intent)
//          Toast.makeText(context,"item点击",Toast.LENGTH_SHORT).show()
        }
    }

    private fun getOrderTypeInfo(type: String?): String {
        /**
         * 订单状态
         * 1 未支付 2 已提交订单 3 商家接单  4 配送中,等待送达 5已送达 6 取消的订单
         */
        //            public static final String ORDERTYPE_UNPAYMENT = "10";
        //            public static final String ORDERTYPE_SUBMIT = "20";
        //            public static final String ORDERTYPE_RECEIVEORDER = "30";
        //            public static final String ORDERTYPE_DISTRIBUTION = "40";
        //            public static final String ORDERTYPE_SERVED = "50";
        //            public static final String ORDERTYPE_CANCELLEDORDER = "60";

        var typeInfo = ""
        when (type) {
            OrderObservable.ORDERTYPE_UNPAYMENT -> typeInfo = "未支付"
            OrderObservable.ORDERTYPE_SUBMIT -> typeInfo = "已提交订单"
            OrderObservable.ORDERTYPE_RECEIVEORDER -> typeInfo = "商家接单"
            OrderObservable.ORDERTYPE_DISTRIBUTION -> typeInfo = "配送中"
            OrderObservable.ORDERTYPE_SERVED -> typeInfo = "已送达"
            OrderObservable.ORDERTYPE_CANCELLEDORDER -> typeInfo = "取消的订单"
        }
        return typeInfo
    }

}