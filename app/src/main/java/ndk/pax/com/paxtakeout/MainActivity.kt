package ndk.pax.com.paxtakeout

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import factory.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*
import ndk.pax.com.paxtakeout.extentions.dip2px

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //判断是否有虚拟按键
        //获取是否存在NavigationBar
        if(checkDeviceHasNavigationBar(this)){
            //dp转换为px
          ll_main_activity.setPadding(0,0,0, 50.dip2px(this))
        }
        initBottomBar()
        changeIndex(0)
    }

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

    private fun initBottomBar() {
        for (i in 0 until main_bottome_switcher_container.childCount) {
            main_bottome_switcher_container.getChildAt(i).setOnClickListener { view ->
                changeIndex(i)
            }
        }
    }

    private fun changeIndex(index: Int) {
        for (i in 0 until main_bottome_switcher_container.childCount) {
            //4个framelayout
            val child = main_bottome_switcher_container.getChildAt(i)
            if (i == index) {
                //选中禁用
                setEnable(child, false)
            } else {
                setEnable(child, true)
            }
        }

        //切换fragment
        val beginTransaction = supportFragmentManager.beginTransaction()
        FragmentFactory.instance.getInstance(index)?.let { beginTransaction.replace(R.id.main_fragment_container, it) }
        beginTransaction.commit()
    }

    //默认enable选择true 所有点击时候，控制为false
    private fun setEnable(child: View, isEnabled: Boolean) {
        child.isEnabled = isEnabled
        var temp = child
        if (temp is ViewGroup) {
            //image textview
            for (i in 0 until temp.childCount) {
                temp.getChildAt(i).isEnabled = isEnabled
            }
        }
    }
}
