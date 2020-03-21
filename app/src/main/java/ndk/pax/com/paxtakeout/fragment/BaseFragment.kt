package fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * User：Rowen
 * Description:BaseFragment积累
 * 时间: 2020/3/21:15:10
 *
 */

abstract class BaseFragment :Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getLayoutId()
    }

    abstract fun getLayoutId(): View?

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    open fun init() {
        //初始化公共资源
    }
}