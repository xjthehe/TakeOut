package ndk.pax.com.paxtakeout.contract

import ndk.pax.com.paxtakeout.model.SellerListItem

/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/24:14:53
 *
 */

interface HomeFragmentContract{
    interface Presenter:BasePresenter{
        fun getHomeInfo()

    }

    interface View{
        fun onHomeSuccess(nearSellerList: List<SellerListItem>, otherSellerList: List<SellerListItem>)
        fun onHomeFail()
    }
}