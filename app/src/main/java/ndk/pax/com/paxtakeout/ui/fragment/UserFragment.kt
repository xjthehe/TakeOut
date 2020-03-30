package fragment

import android.content.Intent
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_.*
import kotlinx.android.synthetic.main.fragment_user.*
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.ui.activity.LogingActivity
import ndk.pax.com.paxtakeout.utils.TakeoutApp

/**
 * User：Rowen
 * Description:首页fragment
 * 时间: 2020/3/21:15:13
 *
 */

class UserFragment:BaseFragment(){
    override fun getLayoutId(): View?{
        val view= View.inflate(activity,R.layout.fragment_user,null)
        return  view
    }

    override fun init(){
        login.setOnClickListener {
            val intent=Intent(activity, LogingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart(){
        super.onStart()
        val user=TakeoutApp.user
        if(user.id==-1){
            //未登录
            ll_userinfo.visibility=View.GONE
            login.visibility=View.VISIBLE
        }else{
            ll_userinfo.visibility=View.VISIBLE
            login.visibility=View.GONE
            username.text="欢迎您,${user.name}"
            phone.text=user.phone
        }
    }
}