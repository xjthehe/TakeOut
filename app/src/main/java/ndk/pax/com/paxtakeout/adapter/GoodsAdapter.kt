package ndk.pax.com.paxtakeout.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import ndk.pax.com.paxtakeout.model.bean.GoodInfo
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter

/**
 * User：Rowen
 * Description:商品右侧列表适配器
 * 时间: 2020/4/7:23:13
 *
 */

class GoodsAdapter:BaseAdapter(), StickyListHeadersAdapter{
    lateinit var arrayTypeGoodLists:ArrayList<GoodInfo.ListBeanX.ListBean>

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItem(position: Int): Any {
            return arrayTypeGoodLists.get(position)
    }

    override fun getItemId(position: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getHeaderId(position: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getHeaderView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}