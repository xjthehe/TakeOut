package ndk.pax.com.paxtakeout.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.item_type.view.*
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.model.bean.GoodInfo

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/7:20:26
 *
 */

class GoodListItemView(context: Context?, attrs: AttributeSet?=null) : RelativeLayout(context, attrs) {

    fun bindView(goodInfo: GoodInfo.ListBeanX) {
        type.text=goodInfo.name
    }

    init {
        View.inflate(context, R.layout.item_type,this)
    }

}