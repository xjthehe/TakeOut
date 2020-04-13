package ndk.pax.com.paxtakeout.ui.activity

import kotlinx.android.synthetic.main.activity_confirm_order.*
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.extentions.dip2px
import ndk.pax.com.paxtakeout.utils.CommonUtil

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/12:20:01
 *
 */

class ConfirmActivity:BaseActivity(){
    override fun getLayoutResId(): Int {
        return R.layout.activity_confirm_order
    }

    override fun init(){
        //dp转换为px
        if(CommonUtil.checkDeviceHasNavigationBar(this)){
            activity_confirm_order.setPadding(0, 0, 0, 48.dip2px(this))
        }





    }













}