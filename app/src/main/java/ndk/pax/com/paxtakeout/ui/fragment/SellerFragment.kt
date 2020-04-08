package ndk.pax.com.paxtakeout.ui.fragment

import android.support.v4.app.Fragment
import android.view.View
import fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_.*
import ndk.pax.com.paxtakeout.R

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/6:20:13
 *
 */

class SellerFragment:BaseFragment(){
    override fun getLayoutId(): View? {
        val view = View.inflate(activity, R.layout.fragment_, null)
        return view
    }

    override fun init() {
      //
        tv.text="商家"
    }


}