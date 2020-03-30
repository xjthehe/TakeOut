package ndk.pax.com.paxtakeout.presenter

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ndk.pax.com.paxtakeout.contract.HomeFragmentContract
import ndk.pax.com.paxtakeout.contract.NetPresenter
import ndk.pax.com.paxtakeout.model.SellerListItem
import ndk.pax.com.paxtakeout.model.net.ResponseInfo
import ndk.pax.com.paxtakeout.model.net.TakeOutService
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/24:14:59
 */

class HomeFragmentPresenter(val view:HomeFragmentContract.View):HomeFragmentContract.Presenter,NetPresenter(){

    override fun parseJson(json: String?) {
        //解析数据
        val gson=Gson()
        var jsonObject = JSONObject(json)
        var nearby = jsonObject.getString("nearbySellerList")
        var nearSellerList:List<SellerListItem> = gson.fromJson(nearby,object :TypeToken<List<SellerListItem>>(){}.type)

        val other = jsonObject.getString("otherSellerList")
        var otherSellerList:List<SellerListItem> = gson.fromJson(other,object :TypeToken<List<SellerListItem>>(){}.type)

        //刷新UI
        if(nearSellerList.isNotEmpty()||otherSellerList.isNotEmpty()){
            //成功
            allList.clear()
            allList.addAll(nearSellerList)
            allList.addAll(otherSellerList)
            view.onHomeSuccess(nearSellerList,otherSellerList)
        }else{
            view.onHomeFail()
        }
    }

    var allList:ArrayList<SellerListItem> = ArrayList()

//    val service:TakeOutService
//    init {
//        val retrofit = Retrofit.Builder()
//                .baseUrl("http://203.195.245.169:8080/TakeOutService/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//        service= retrofit.create(TakeOutService::class.java)
//
//    }
    override fun getHomeInfo() {
    val homeCall = service.getHomeInfo()
    //异步访问
    homeCall.enqueue(callBack)
}
//    private fun parseJson(json: String?) {
//        //解析数据
//        val gson=Gson()
//        var jsonObject = JSONObject(json)
//        var nearby = jsonObject.getString("nearbySellerList")
//        var nearSellerList:List<SellerListItem> = gson.fromJson(nearby,object :TypeToken<List<SellerListItem>>(){}.type)
//
//        val other = jsonObject.getString("otherSellerList")
//        var otherSellerList:List<SellerListItem> = gson.fromJson(other,object :TypeToken<List<SellerListItem>>(){}.type)
//
//        //刷新UI
//        if(nearSellerList.isNotEmpty()||otherSellerList.isNotEmpty()){
//            //成功
//            allList.clear()
//            allList.addAll(nearSellerList)
//            allList.addAll(otherSellerList)
//            view.onHomeSuccess(nearSellerList,otherSellerList)
//        }else{
//            view.onHomeFail()
//        }
//
//    }

}