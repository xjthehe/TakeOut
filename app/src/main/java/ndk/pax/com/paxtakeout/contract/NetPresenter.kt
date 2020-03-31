package ndk.pax.com.paxtakeout.contract

import android.util.Log
import ndk.pax.com.paxtakeout.model.net.ResponseInfo
import ndk.pax.com.paxtakeout.model.net.TakeOutService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/27:13:57
 */
open abstract class NetPresenter : BasePresenter {
    val service: TakeOutService
    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://203.195.245.169:8080/TakeOutService/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(TakeOutService::class.java)
    }

    val callBack=object : Callback<ResponseInfo> {
        override fun onFailure(call: Call<ResponseInfo>?, t: Throwable?) {
            Log.e("onFailure","没有连上服务器")
        }
        override fun onResponse(call: Call<ResponseInfo>?, response: Response<ResponseInfo>?) {
            if(response!=null){
                if(response.isSuccessful){
                    val responseInfo = response.body()
                    val code = responseInfo?.code?.toInt()
                    if(code==0){
                        Log.e("onResponse","连上服务器"+code)
                        val json = responseInfo?.data
                        parseJson(json)
                    }else{

                    }
                }else{
                    Log.e("onResponse","服务器返回错误")
                }
            }else{
                Log.e("onFailure","服务器没有成功返回")
            }
        }
    }
    abstract fun parseJson(json: String?)
}