package fragment

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.fragment_home.*
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.adapter.HomeListAdapter
import ndk.pax.com.paxtakeout.contract.HomeFragmentContract
import ndk.pax.com.paxtakeout.dagger2.component.DaggerHomeFragmentComponent
import ndk.pax.com.paxtakeout.dagger2.module.HomeFragmentModule
import ndk.pax.com.paxtakeout.extentions.dip2px
import ndk.pax.com.paxtakeout.model.SellerListItem
import ndk.pax.com.paxtakeout.presenter.HomeFragmentPresenter
//import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * User：Rowen
 * Description:首页fragment
 * 时间: 2020/3/21:15:13
 *
 */

class HomeFragment : BaseFragment(), HomeFragmentContract.View {
    var sum: Int = 0
    var distance: Int = 0
    var alph = 55

    @Inject
    lateinit var homeFragmentPresenter: HomeFragmentPresenter
    //解耦P层和View层 通过dragger2(基于注解的依赖注入，生成HomeFragmentPresenter)
//    val homeFragmentPresenter by lazy {
//        HomeFragmentPresenter(this)
//    }

    override fun onHomeSuccess(nearSellerList: List<SellerListItem>, otherSellerList: List<SellerListItem>) {
        //数据接收成功回调
//        context?.toast("接收数据成功")
        Log.e("succes", "接收数据成功")
        rv_home.adapter?.notifyDataSetChanged()
    }

    override fun onHomeFail() {
        Log.e("fail", "接收数据失败")
    }


    override fun getLayoutId(): View? {
        val view = View.inflate(activity, R.layout.fragment_home, null)
        return view
    }

    override fun init() {
        //初始化P层
        initDarrager()
        //初始化recycleview
        initRecyleView()
        //初始化头部最大距离(通过透明度来控制）
        distance = context?.let { 120.dip2px(it) }!!//120dp-----转换为像素
        //执行home首页p层逻辑请求
        homeFragmentPresenter.getHomeInfo()
    }

    private fun initDarrager() {
        DaggerHomeFragmentComponent.builder()
                .homeFragmentModule(HomeFragmentModule(this))
                .build()
                .inject(this)
    }

    private fun initRecyleView() {
//        val mdats = arrayListOf<String>("xx", "xxx", "qqqqq", "xx", "xxx", "qqqqq", "xx", "xxx", "qqqqq")
//        for (i in 0 until 100) {
//            mdats.add("商家" + i)
//        }
        rv_home.apply {
            layoutManager = LinearLayoutManager(context)//默认从上到下
            adapter = HomeListAdapter(context, homeFragmentPresenter.allList)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    sum = sum?.plus(dy)
                    Log.e("home", "sum:$sum")//px值
                    if (sum > distance) {
                        alph = 255
                    } else {
                        alph += 55 + (sum * 200 / distance).toInt()
                    }
                    ll_title_container.setBackgroundColor(Color.argb(alph, 0x31, 0x90, 0xe0))
                }
            })
        }
    }




}