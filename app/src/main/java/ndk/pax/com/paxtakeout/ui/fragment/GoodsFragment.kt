package ndk.pax.com.paxtakeout.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_goods.*
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.adapter.GoodTypeAdapter
import ndk.pax.com.paxtakeout.adapter.GoodsAdapter
import ndk.pax.com.paxtakeout.contract.GoodFragmentContract
import ndk.pax.com.paxtakeout.model.bean.GoodInfo
import ndk.pax.com.paxtakeout.presenter.GoodFragmentPresenter

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/6:20:13
 */
class GoodsFragment:BaseFragment(),GoodFragmentContract.View{
    lateinit var adapter:GoodTypeAdapter//左侧列表刷新
    lateinit var arrayTypeGoodLists:ArrayList<GoodInfo.ListBeanX.ListBean>

    val goodsFragmentPresenter by lazy{
       GoodFragmentPresenter(this)
   }

    override fun onGoodInfoSuccess(allGoodInfoList: ArrayList<GoodInfo.ListBeanX>, arrayTypeGoodLists: ArrayList<GoodInfo.ListBeanX.ListBean>) {
        Log.e("onGoodInfoSuccess",allGoodInfoList.size.toString())
        adapter.setData(allGoodInfoList,arrayTypeGoodLists)
    }

    override fun onGoodInfoError(){
        Log.e("onGoodInfoError","商品列表获取失败")
    }

    override fun getLayoutId(): View? {
        val view = View.inflate(activity,R.layout.fragment_goods, null)
        return view
    }

    override fun init() {
        initRecycleview()
        goodsFragmentPresenter.getGoodInfo(1)
    }

    private fun initRecycleview() {
        rv_goods_type.layoutManager=LinearLayoutManager(context)
        adapter=GoodTypeAdapter(context)
        rv_goods_type.adapter=adapter
//        slhlv 商品右侧粘性listview 带分类标题的
        slhlv.adapter=GoodsAdapter()
    }


}

