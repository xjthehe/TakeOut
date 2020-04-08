package ndk.pax.com.paxtakeout.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/6:21:45
 *
 */

class BusinessFragmentPagerAdapter(val fragments: List<Fragment>, val titles: List<String>, supportFragmentManager: FragmentManager) : FragmentPagerAdapter(supportFragmentManager) {
    override fun getItem(position: Int):Fragment {
        return  fragments.get(position)
    }

    override fun getCount(): Int =titles.size

    override fun getPageTitle(position: Int): CharSequence? {
        return titles.get(position)
    }

}