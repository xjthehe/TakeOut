package ndk.pax.com.paxtakeout.contract

import ndk.pax.com.paxtakeout.model.bean.Order

/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/24:14:53
 *
 */

interface OrderFragmentContract{
    interface Presenter:BasePresenter{
        fun getOrderInfo(userId:String)

    }

    interface View{
        fun onInfoSuccess()
        fun onInfoFail()
    }
}