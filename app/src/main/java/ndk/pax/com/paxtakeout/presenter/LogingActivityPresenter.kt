package ndk.pax.com.paxtakeout.presenter

import com.google.gson.Gson
import ndk.pax.com.paxtakeout.contract.BasePresenter
import ndk.pax.com.paxtakeout.contract.LogingActivityContract
import ndk.pax.com.paxtakeout.contract.NetPresenter
import ndk.pax.com.paxtakeout.model.bean.User
import ndk.pax.com.paxtakeout.model.net.TakeOutService
import ndk.pax.com.paxtakeout.utils.TakeoutApp

/**
 * User：Rowen
 * Description:登录
 * 时间: 2020/3/27:12:01
 *
 */

class LogingActivityPresenter(val view:LogingActivityContract.View) :LogingActivityContract.Presenter,NetPresenter(){
    override fun parseJson(json: String?){
        var user= Gson().fromJson(json,User::class.java)
        if(user!=null){
            //暂时用临时变量缓存
            TakeoutApp.user=user
            view.onLoginByPhoneSuccess()
        }else{
            view.onLoginByPhoneFail()
        }
    }

    override fun loginByPhone(phone: String) {
        val loginInfo = service.getLoginInfo(phone)
        loginInfo.enqueue(callBack)
    }
}