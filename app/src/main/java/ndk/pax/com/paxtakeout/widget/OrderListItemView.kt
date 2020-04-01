package ndk.pax.com.paxtakeout.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.item_order_item.view.*
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.model.bean.Order

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/1:19:22
 *
 */

class OrderListItemView(context: Context?, attrs: AttributeSet?=null) : RelativeLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.item_order_item,this)
    }

    fun bindView(order: Order) {
        //商家名
        tv_order_item_seller_name.text=(order.getSeller()as Order.SellerBean).name
        //订单状态
        tv_order_item_type.text=order.getType()

    }

}