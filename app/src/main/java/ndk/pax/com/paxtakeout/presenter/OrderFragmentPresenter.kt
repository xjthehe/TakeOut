package ndk.pax.com.paxtakeout.presenter

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ndk.pax.com.paxtakeout.contract.NetPresenter
import ndk.pax.com.paxtakeout.contract.OrderFragmentContract
import ndk.pax.com.paxtakeout.model.bean.Order
import ndk.pax.com.paxtakeout.model.net.ResponseInfo


/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/31:22:56
 *
 */

class OrderFragmentPresenter(val view:OrderFragmentContract.View):OrderFragmentContract.Presenter,NetPresenter(){
    var allOrderList:ArrayList<Order> = ArrayList()

    override fun getOrderInfo(userId: String) {
//        val orderInfo = service.getOrderInfo(userId)
//        orderInfo.enqueue(callBack)


//        val observable: Observable<ResponseInfo> = service.getOrderInfoByRxjava(userId)
//        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object :Observer<ResponseInfo>{
//                    override fun onError(e: Throwable?) {
//                        if(e!=null)
//                        Log.e("rxjava",e.localizedMessage)
//                    }
//
//                    override fun onNext(responseInfo: ResponseInfo?) {
//                            if(responseInfo!=null){
//                                parseJson(responseInfo.data)
//                            }
//                    }
//
//                    override fun onCompleted() {
//
//                    }
//                })

        val observable: Observable<ResponseInfo> = service.getOrderInfoByRxjava(userId)
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    parseJson(it.data)
                },{
                    Log.e("rxjava", it.localizedMessage)
                },{
                     Log.e("rxjava","rxjava complete")
                })

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
            view.onInfoSuccess(allOrderList)
        }else{
            allOrderList.clear()
            view.onInfoFail()
        }
    }
}