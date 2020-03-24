package ndk.pax.com.paxtakeout.presenter

import ndk.pax.com.paxtakeout.contract.HomeFragmentContract

/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/24:14:59
 */

class HomeFragmentPresenter(val view:HomeFragmentContract.View):HomeFragmentContract.Presenter{
    override fun getHomeInfo() {
        //异步访问
    }

}