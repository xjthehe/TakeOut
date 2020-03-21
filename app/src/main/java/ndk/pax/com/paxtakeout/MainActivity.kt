package ndk.pax.com.paxtakeout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import factory.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomBar()
        changeIndex(0)
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
