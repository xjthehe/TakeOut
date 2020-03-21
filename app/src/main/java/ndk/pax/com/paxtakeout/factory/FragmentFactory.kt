package factory

import android.support.v4.app.Fragment
import fragment.*

/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/21:15:21
 *
 */

class FragmentFactory private constructor() {
    val home by lazy {
        HomeFragment()
    }

    val order by lazy {
        OrderFragment()
    }

    val user by lazy {
        UserFragment()
    }

    val more by lazy {
        MoreFragment()
    }

    companion object {
        val instance = FragmentFactory()
    }


    fun getInstance(tabId: Int): Fragment? {
        when (tabId) {
            0 -> return home
            1 -> return order
            2 -> return user
            3 -> return more
        }
        return null
    }

}