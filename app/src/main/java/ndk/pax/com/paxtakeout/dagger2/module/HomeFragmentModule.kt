package ndk.pax.com.paxtakeout.dagger2.module

import dagger.Module
import dagger.Provides
import ndk.pax.com.paxtakeout.contract.HomeFragmentContract
import ndk.pax.com.paxtakeout.presenter.HomeFragmentPresenter

/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/24:22:55
 *
 */
@Module class HomeFragmentModule(val view:HomeFragmentContract.View){
    @Provides fun provideHomeFragmentPresenter():HomeFragmentPresenter{
        return HomeFragmentPresenter(view)
    }
}
