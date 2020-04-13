package ndk.pax.com.paxtakeout.ui.activity

import android.content.Intent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_address_list.*
import kotlinx.android.synthetic.main.activity_confirm_order.*
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.extentions.dip2px
import ndk.pax.com.paxtakeout.model.dao.AddressDao
import ndk.pax.com.paxtakeout.utils.CommonUtil

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/12:20:32
 */
class RecepitAddressActivity:BaseActivity(){
    lateinit var addressDao: AddressDao
    override fun getLayoutResId(): Int {
        return R.layout.activity_address_list
    }

    override fun init(){
        addressDao= AddressDao(this)
        //dp转换为px
        if(CommonUtil.checkDeviceHasNavigationBar(this)){
            activity_confirm_order.setPadding(0, 0, 0, 48.dip2px(this))
        }

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
        }
    }
}
