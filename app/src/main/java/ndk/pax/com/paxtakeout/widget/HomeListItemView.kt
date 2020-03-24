package ndk.pax.com.paxtakeout.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
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

    fun bindView(s: SellerListItem){
//        tv.text=s
    }
}