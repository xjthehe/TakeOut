package ndk.pax.com.paxtakeout.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.item_home_common.view.*
import ndk.pax.com.paxtakeout.R

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

    fun bindView(s: String){
//        tv.text=s
    }
}