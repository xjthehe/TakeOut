package ndk.pax.com.paxtakeout.ui.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_address_list.*
import kotlinx.android.synthetic.main.activity_confirm_order.*
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.adapter.ReceiptRvAdapter
import ndk.pax.com.paxtakeout.extentions.dip2px
import ndk.pax.com.paxtakeout.model.bean.RecepitAddressBean
import ndk.pax.com.paxtakeout.model.dao.AddressDao
import ndk.pax.com.paxtakeout.utils.CommonUtil

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/12:20:32
 */
class RecepitAddressActivity:BaseActivity(){
    lateinit var addressDao: AddressDao
    lateinit var receipRvAdapter: ReceiptRvAdapter

    override fun getLayoutResId(): Int {
        return R.layout.activity_address_list
    }


    override fun init(){
        addressDao= AddressDao(this)
        //dp转换为px
        if(CommonUtil.checkDeviceHasNavigationBar(this)){
            activity_confirm_order.setPadding(0, 0, 0, 48.dip2px(this))
        }
        //地址适配器列表
        rv_receipt_address.layoutManager=LinearLayoutManager(this) as RecyclerView.LayoutManager
        receipRvAdapter= ReceiptRvAdapter(this)
        rv_receipt_address.adapter= receipRvAdapter

        //添加地址
        tv_add_address.setOnClickListener {
            val intent=Intent(this,AddRecepitAddressActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val addressList=addressDao.queryAllAddress()
        if(addressList.isNotEmpty()){
            Toast.makeText(this,"一共有"+addressList.size+"个地址",Toast.LENGTH_SHORT).show()
            receipRvAdapter.setData(addressList as ArrayList<RecepitAddressBean>)
        }
    }
}
