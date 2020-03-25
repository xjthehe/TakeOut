package fragment

import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_.*
import ndk.pax.com.paxtakeout.R

/**
 * User：Rowen
 * Description:首页fragment
 * 时间: 2020/3/21:15:13
 *
 */

class MoreFragment:BaseFragment(){
    override fun getLayoutId(): View? {
        val view= View.inflate(activity,R.layout.fragment_,null)
        return  view
    }

    override fun init() {
        tv.text="更多"
    }

}