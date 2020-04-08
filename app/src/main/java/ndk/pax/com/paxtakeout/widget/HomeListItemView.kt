package ndk.pax.com.paxtakeout.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_seller.view.*
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.model.SellerListItem

/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/23:10:22
 *
 */

class HomeListItemView(context: Context?, attrs: AttributeSet?=null) : RelativeLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.item_seller,this)

    }

    fun bindView(sellerListItem: SellerListItem){

        //商家名字
        tv_title.text=sellerListItem.name
        //TODO赋值其他字段
        // 图片加载框架
        Picasso.with(context).load(sellerListItem.icon).into(seller_logo)
        ratingBar.rating=sellerListItem.score.toFloat()
        tv_home_sale.text="月售${sellerListItem.sale}单"
        tv_home_send_price.text="￥${sellerListItem.sendPrice}起送/配送费￥${sellerListItem.deliveryFee}"
        tv_home_distance.text="${sellerListItem.distance}"
    }
}