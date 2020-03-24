package fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_.*
import kotlinx.android.synthetic.main.fragment_home.*
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.adapter.HomeListAdapter

/**
 * User：Rowen
 * Description:首页fragment
 * 时间: 2020/3/21:15:13
 *
 */

class HomeFragment:BaseFragment(){
    override fun getLayoutId(): View?{
          val view= View.inflate(activity,R.layout.fragment_home,null)
          return  view
    }

    override fun init() {
        //初始化recycleview
        initRecyleView()

    }

    private fun initRecyleView() {
        val mdats= arrayListOf<String>("xx","xxx","qqqqq","xx","xxx","qqqqq","xx","xxx","qqqqq")

        for (i in 0 until 100){
            mdats.add("商家"+i)
        }

        rv_home.apply {
            layoutManager=LinearLayoutManager(context)//默认从上到下
            adapter=HomeListAdapter(context, mdats as ArrayList<String>)
        }

    }

}