package ndk.pax.com.paxtakeout.model.net

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/24:15:57
 *
 */

interface TakeOutService {
//        @GET("")
//        fun listRepos(@Path("user") user: String): Call<List<Repo>>

    @GET("home")
    fun  getHomeInfo(): Call<ResponseInfo>

    @GET("login")
    fun  getLoginInfo(@Query("phone") phone:String): Call<ResponseInfo>


    @GET("order")
    fun  getOrderInfo(@Query("id") userId:String): Call<ResponseInfo>



}