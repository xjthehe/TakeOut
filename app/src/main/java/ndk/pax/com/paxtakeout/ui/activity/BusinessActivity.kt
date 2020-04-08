package ndk.pax.com.paxtakeout.ui.activity

import android.content.Context
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_business.*
import kotlinx.android.synthetic.main.activity_main.*
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.adapter.BusinessFragmentPagerAdapter
import ndk.pax.com.paxtakeout.extentions.dip2px
import ndk.pax.com.paxtakeout.ui.fragment.CommentsFragment
import ndk.pax.com.paxtakeout.ui.fragment.GoodsFragment
import ndk.pax.com.paxtakeout.ui.fragment.SellerFragment

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/6:19:26
 *
 */

class BusinessActivity:BaseActivity(){
    override fun getLayoutResId(): Int {
        return R.layout.activity_business
    }

    override fun init() {
        //获取是否存在NavigationBar
        if(checkDeviceHasNavigationBar(this)){
            //dp转换为px
            ll_main_activity.setPadding(0,0,0, 48.dip2px(this))
        }
        //viewpager
        vp.adapter= BusinessFragmentPagerAdapter(fragments,titles,supportFragmentManager)
        //tablayout+viewpager关联
        tabs.setupWithViewPager(vp)

    }

    val titles= listOf<String>("商品","商家","评论")
    val fragments= listOf<Fragment>(GoodsFragment(),SellerFragment(),CommentsFragment())


    //是否有虚拟按键
    fun checkDeviceHasNavigationBar(context: Context): Boolean {
        var hasNavigationBar: Boolean = false;
        val id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = resources.getBoolean(id);
        }
        try {
            val systemPropertiesClass = Class.forName("android.os.SystemProperties");
            val m = systemPropertiesClass.getMethod("get", String::class.java);
            val navBarOverride = m.invoke(systemPropertiesClass, "qemu.hw.mainkeys") as String;
            if ("1".equals(navBarOverride)) {
                //不存在虚拟按键
                hasNavigationBar = false
            } else if ("0".equals(navBarOverride)) {
                //存在虚拟按键
                hasNavigationBar = true
            }
        } catch (e: Exception) {
        }
        return hasNavigationBar;
    }

}