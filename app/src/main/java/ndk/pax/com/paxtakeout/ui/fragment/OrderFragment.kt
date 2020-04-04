package fragment

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_order.*
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.adapter.OrderListAdapter
import ndk.pax.com.paxtakeout.contract.OrderFragmentContract
import ndk.pax.com.paxtakeout.model.bean.Order
import ndk.pax.com.paxtakeout.presenter.OrderFragmentPresenter
import ndk.pax.com.paxtakeout.utils.TakeoutApp

/**
 * User：Rowen
 * Description:首页fragment
 * 时间: 2020/3/21:15:13
 *
 */

class OrderFragment:BaseFragment(),OrderFragmentContract.View{
    override fun onInfoSuccess(){
        //刷新适配器
        rv_order_list.adapter?.notifyDataSetChanged()
        srl_order.isRefreshing=false
    }

    override fun onInfoFail(){
        Toast.makeText(activity,"订单更新失败",Toast.LENGTH_SHORT).show()
        srl_order.isRefreshing=false
    }

    val orderFragmentPresenter by lazy {
        OrderFragmentPresenter(this)
    }

    override fun getLayoutId(): View? {
        val view= View.inflate(activity,R.layout.fragment_order,null)
        return  view
    }

    override fun init(){
        initRecyleView()
        //p层逻辑
        val userId=TakeoutApp.user.id
        if(-1 == userId){
            Toast.makeText(activity,"请先登录，再查询订单",Toast.LENGTH_SHORT).show()
        }else{
            orderFragmentPresenter.getOrderInfo(userId.toString())
        }
    }

    private fun initRecyleView(){
        rv_order_list.apply {
            layoutManager = LinearLayoutManager(context)//默认从上到下
            adapter=OrderListAdapter(context,orderFragmentPresenter.allOrderList)
        }
        //swiplayout
        srl_order.setOnRefreshListener(object :SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {
                //p层逻辑
                val userId=TakeoutApp.user.id
                if(-1 == userId){
                    Toast.makeText(activity,"请先登录，再查询订单",Toast.LENGTH_SHORT).show()
                }else{
                    orderFragmentPresenter.getOrderInfo(userId.toString())
                }
            }
        })

    }
}