package ndk.pax.com.paxtakeout.contract

import ndk.pax.com.paxtakeout.model.bean.GoodInfo

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/7:10:13
 *
 */

interface GoodFragmentContract{

    interface Presenter:BasePresenter{
        fun getGoodInfo(sellerId:Int)
    }

    interface View{
        fun onGoodInfoSuccess(allGoodInfoList: ArrayList<GoodInfo.ListBeanX>, arrayTypeGoodLists: ArrayList<GoodInfo.ListBeanX.ListBean>)
        fun onGoodInfoError()
    }
}