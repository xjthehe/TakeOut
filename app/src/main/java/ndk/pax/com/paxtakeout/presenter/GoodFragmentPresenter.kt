package ndk.pax.com.paxtakeout.presenter

import android.util.Log
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import ndk.pax.com.paxtakeout.contract.GoodFragmentContract
import ndk.pax.com.paxtakeout.contract.NetPresenter
import ndk.pax.com.paxtakeout.model.bean.GoodInfo
import org.json.JSONObject

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/7:10:24
 *
 */

class GoodFragmentPresenter(val view:GoodFragmentContract.View):GoodFragmentContract.Presenter,NetPresenter(){
    var allGoodInfoList:ArrayList<GoodInfo.ListBeanX> = ArrayList()
    lateinit var arrayTypeGoodLists:ArrayList<GoodInfo.ListBeanX.ListBean>


    override fun parseJson(json: String?){
        Log.e("GoodFragmentPresenter",json)
        //callback此处回调，开始解析
        val gson= Gson()
        val jsonObj= JSONObject(json)
        val allString=jsonObj.getString("list")
        //进行解析,以上必须注意下
        var goodInfoList:List<GoodInfo.ListBeanX> = gson.fromJson(allString,object: TypeToken<List<GoodInfo.ListBeanX>>(){}.type)

        if(goodInfoList.isNotEmpty()){
            allGoodInfoList.clear()
            allGoodInfoList.addAll(goodInfoList)
            Log.e("allGoodInfoList",allGoodInfoList.size.toString())

            for (i in 0 until allGoodInfoList.size){
                arrayTypeGoodLists.addAll(allGoodInfoList.get(i).list!!)
            }

            view.onGoodInfoSuccess(allGoodInfoList,arrayTypeGoodLists)
        }else{
            allGoodInfoList.clear()
            view.onGoodInfoError()
        }
    }

    //获取商品信息
    override fun getGoodInfo(sellerId: Int) {
        val businessInfo = service.getBusinessInfo(sellerId)
        businessInfo.enqueue(callBack)
    }

}