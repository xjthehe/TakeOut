package ndk.pax.com.paxtakeout.presenter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ndk.pax.com.paxtakeout.contract.NetPresenter
import ndk.pax.com.paxtakeout.contract.OrderFragmentContract
import ndk.pax.com.paxtakeout.model.SellerListItem
import ndk.pax.com.paxtakeout.model.bean.Order
import org.json.JSONObject

/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/31:22:56
 *
 */

class OrderFragmentPresenter(val view:OrderFragmentContract.View):OrderFragmentContract.Presenter,NetPresenter(){
    var allOrderList:ArrayList<Order> = ArrayList()

    override fun getOrderInfo(userId: String) {
        val orderInfo = service.getOrderInfo(userId)
        orderInfo.enqueue(callBack)
    }

    override fun parseJson(json: String?) {
        //callback此处回调，开始解析
        val gson= Gson()
//        val jsonObject = JSONObject(json)
//        val stringData = jsonObject.getString("data")
//      var otherSellerList:List<SellerListItem> = gson.fromJson(other,object : TypeToken<List<SellerListItem>>(){}.type)
        var orderList:List<Order> = gson.fromJson(json,object:TypeToken<List<Order>>(){}.type)

        if(orderList.isNotEmpty()){
            allOrderList.clear()
            allOrderList.addAll(orderList)
            view.onInfoSuccess()
        }else{
            allOrderList.clear()
            view.onInfoFail()
        }
    }
}