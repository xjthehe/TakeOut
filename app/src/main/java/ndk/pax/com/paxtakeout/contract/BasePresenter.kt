package ndk.pax.com.paxtakeout.contract;

import android.os.Handler
import android.os.Looper
import ndk.pax.com.paxtakeout.model.net.TakeOutService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/13:16:50
 */

interface BasePresenter {
    companion object {
        val handler by lazy {
            Handler(Looper.getMainLooper())
        }


    }
    fun uiThread(f:()->Unit){
        handler.post(object :Runnable{
            override fun run() {
                f();
            }
        })
    }
//    fun initRetrofit():TakeOutService{
//        val service: TakeOutService
//                = retrofit.create(TakeOutService::class.java)
//        return service
//    }


}
