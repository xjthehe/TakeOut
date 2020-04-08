package ndk.pax.com.paxtakeout.widget

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
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
    lateinit var view:View
    var selectPosition: Int=0
    fun bindView(goodInfo: GoodInfo.ListBeanX, position: Int,mPosition:Int) {
        selectPosition= mPosition
        if(position==selectPosition){
            view.setBackgroundColor(Color.WHITE)
            type.setTextColor(Color.BLACK)
            type.setTypeface(Typeface.DEFAULT_BOLD)
        }else{
            view.setBackgroundColor(Color.parseColor("#b9dedcdc"))
            type.setTextColor(Color.GRAY)
            type.setTypeface(Typeface.DEFAULT)
        }
        type.text=goodInfo.name
    }

    init {
        view= View.inflate(context, R.layout.item_type,this)
    }

}