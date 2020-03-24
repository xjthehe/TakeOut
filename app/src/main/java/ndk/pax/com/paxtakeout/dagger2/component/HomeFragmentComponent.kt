package ndk.pax.com.paxtakeout.dagger2.component

import dagger.Component
import fragment.HomeFragment
import ndk.pax.com.paxtakeout.dagger2.module.HomeFragmentModule

/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/24:22:59
 *
 */
@Component(modules = arrayOf(HomeFragmentModule::class))
interface HomeFragmentComponent {
    fun inject(view:HomeFragment)
}