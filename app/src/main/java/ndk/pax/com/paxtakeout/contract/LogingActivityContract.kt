package ndk.pax.com.paxtakeout.contract

import ndk.pax.com.paxtakeout.model.SellerListItem

/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/24:14:53
 *
 */

interface LogingActivityContract{
    interface Presenter:BasePresenter{
        fun loginByPhone(phone:String)
    }

    interface View{
        fun onLoginByPhoneSuccess()
        fun onLoginByPhoneFail()
    }
}