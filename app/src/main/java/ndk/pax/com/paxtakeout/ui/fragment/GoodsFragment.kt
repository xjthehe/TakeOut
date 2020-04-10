package ndk.pax.com.paxtakeout.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.AbsListView
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
    lateinit var goodTypeAdapter:GoodTypeAdapter//左侧列表刷新
    lateinit var adapterGoods:GoodsAdapter//右侧列表刷新
    lateinit var arrayTypeGoodLists:ArrayList<GoodInfo.ListBeanX.ListBean>

    val goodsFragmentPresenter by lazy{
        GoodFragmentPresenter(this)
    }

    override fun onGoodInfoSuccess(allGoodInfoList: ArrayList<GoodInfo.ListBeanX>, arrayTypeGoodLists: ArrayList<GoodInfo.ListBeanX.ListBean>) {
        Log.e("onGoodInfoSuccess",allGoodInfoList.size.toString())
        goodTypeAdapter.setData(allGoodInfoList,arrayTypeGoodLists)
        adapterGoods.setData(arrayTypeGoodLists)//刷新右侧商品信息
        //数据成功才监听
        slhlv.setOnScrollListener(object :AbsListView.OnScrollListener{
            override fun onScroll(p0: AbsListView?, firstVisibleItem: Int, p2: Int, p3: Int) {
                //1.获取旧的position
                val oldPosition=goodTypeAdapter.selectPosition
                //2.获取新的position
                val typeId = adapterGoods.arrayTypeGoodLists.get(firstVisibleItem).typeId
                val newPosition = goodsFragmentPresenter.getTypePositionByTypeId(typeId)
                //当newtype和旧的不同时，证明
                if(oldPosition!=newPosition){
                    goodTypeAdapter.selectPosition=newPosition
                    goodTypeAdapter.notifyDataSetChanged()
                }

            }

            override fun onScrollStateChanged(p0: AbsListView?, p1: Int) {

            }


        })
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
        goodTypeAdapter=GoodTypeAdapter(context,this)
        rv_goods_type.adapter=goodTypeAdapter

//        slhlv 商品右侧粘性listview 带分类标题的
        adapterGoods=GoodsAdapter(context,this)
        slhlv.adapter=adapterGoods
        //


    }


}

