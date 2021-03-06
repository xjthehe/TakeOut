package ndk.pax.com.paxtakeout.ui.activity

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_edit_receipt_address.*
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.extentions.dip2px
import ndk.pax.com.paxtakeout.model.bean.RecepitAddressBean
import ndk.pax.com.paxtakeout.model.dao.AddressDao
import ndk.pax.com.paxtakeout.utils.CommonUtil

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/12:21:08
 */

class AddOrEditAddressActivity : BaseActivity(), View.OnClickListener {
    lateinit var addressDao: AddressDao
    lateinit var  addressBean :RecepitAddressBean

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ib_back -> finish()
            R.id.ib_add_phone_other -> rl_phone_other.visibility = View.VISIBLE
            R.id.ib_delete_phone_other -> et_phone_other.setText("")
            R.id.ib_delete_phone -> et_phone.setText("")
            R.id.ib_select_label -> selectLabel()
            R.id.btn_ok -> {
                val isOk = checkReceiptAddressInfo()
                if (isOk) {
                    if(intent.hasExtra("address")){
                        updateAddress()
                    }else{
                        insertAddress()
                    }
                    finish()
                }
            }

            R.id.btn_location_address->{
                val intent= Intent(this,MapLocationActivity::class.java)
                startActivityForResult(intent,1001)
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==200){
            if(data!=null){
                val title=data.getStringExtra("title")
                val address=data.getStringExtra("address")
                et_receipt_address.setText(title)
                et_detail_address.setText(address)
            }
        }
    }

    //更新地址
    private fun updateAddress() {
        val username = et_name.text.toString().trim()
        var sex = "女士"
        if (rb_man.isChecked()) {
            sex = "男士"
        } else if (rb_women.isChecked()) {
            sex = "女士"
        }
        var phone = et_phone.text.toString().trim()
        var phoneOther = et_phone_other.text.toString().trim()
        var address = et_receipt_address.text.toString().trim()
        var detailAddress = et_detail_address.text.toString().trim()
        var label = tv_label.text.toString().trim()
        //更新bean
        addressBean.username=username
        addressBean.sex=sex

        addressBean.phone=phone
        addressBean.phoneOther=phoneOther

        addressBean.address=address
        addressBean.detailaddress=detailAddress
        addressBean.label=label

        addressDao.updateRecepitAddressBean(addressBean)
        Toast.makeText(this, "更新地址成功", Toast.LENGTH_SHORT).show()
    }
    private fun insertAddress() {
        val username = et_name.text.toString().trim()
        var sex = "女士"
        if (rb_man.isChecked()) {
            sex = "男士"
        } else if (rb_women.isChecked()) {
            sex = "女士"
        }
        var phone = et_phone.text.toString().trim()
        var phoneOther = et_phone_other.text.toString().trim()
        var address = et_receipt_address.text.toString().trim()
        var detailAddress = et_detail_address.text.toString().trim()
        var label = tv_label.text.toString().trim()
        val bean = RecepitAddressBean(999, username, sex, phone, phoneOther, address, detailAddress, label, "38")
        addressDao.addRecepitAddressBean(bean)
        Toast.makeText(this, "可以新增地址", Toast.LENGTH_SHORT).show()
    }

    val titles = arrayOf("无", "家", "学校", "公司")
    val colors = arrayOf("#778899", "#ff3399", "#ff9933", "#33ff99")

    private fun selectLabel() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("请选择地址标签")
        builder.setItems(titles, object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                tv_label.text = titles[p1]
                tv_label.setBackgroundColor(Color.parseColor(colors[p1]))
                tv_label.setTextColor(Color.BLACK)
            }
        })
        builder.show()
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_add_edit_receipt_address
    }

    override fun init() {
        addressDao = AddressDao(this)
        //dp转换为px
        if (CommonUtil.checkDeviceHasNavigationBar(this)) {
            activity_add_edit_container.setPadding(0, 0, 0, 48.dip2px(this))
        }
        //地图一键定位
        btn_location_address.setOnClickListener(this)

        //f返回按键
        ib_back.setOnClickListener(this)
        ib_add_phone_other.setOnClickListener(this)
        ib_delete_phone.setOnClickListener(this)
        btn_ok.setOnClickListener(this)
        ib_select_label.setOnClickListener(this)

        et_phone.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


            }

            override fun afterTextChanged(s: Editable?) {
                if (!TextUtils.isEmpty(s)) {
                    ib_delete_phone.visibility = View.VISIBLE
                } else {
                    ib_delete_phone.visibility = View.INVISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
        })
        et_phone_other.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


            }

            override fun afterTextChanged(s: Editable?) {
                if (!TextUtils.isEmpty(s)) {
                    ib_delete_phone_other.visibility = View.VISIBLE
                } else {
                    ib_delete_phone_other.visibility = View.INVISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
        })


        //处理intent
        processIntent()
    }

    private fun processIntent() {
        if(intent.hasExtra("address")){
            addressBean= intent.getSerializableExtra("address") as RecepitAddressBean
            if(addressBean!=null){
                //delete按钮显示
                ib_delete.visibility=View.VISIBLE
                ib_delete.setOnClickListener{
                    addressDao.deleteRecepitAddressBean(addressBean)
                    Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show()
                    finish()
                }
                tv_title.text="修改地址"
                et_name.setText(addressBean.username)
                val sex=addressBean.sex
                if("男士".equals(sex)){
                    rb_man.isChecked=true
                }else{
                    rb_women.isChecked=true
                }
                et_phone.setText(addressBean.phone)
                et_phone_other.setText(addressBean.phoneOther)
                et_receipt_address.setText(addressBean.address)
                et_detail_address.setText(addressBean.detailaddress)
                tv_label.text=addressBean.label
            }
        }else{
            ib_delete.visibility=View.GONE
        }
    }


    fun checkReceiptAddressInfo(): Boolean {
        val name = et_name.getText().toString().trim()
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请填写联系人", Toast.LENGTH_SHORT).show()
            return false
        }
        val phone = et_phone.getText().toString().trim()
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请填写手机号码", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!isMobileNO(phone)) {
            Toast.makeText(this, "请填写合法的手机号", Toast.LENGTH_SHORT).show()
            return false
        }
        val receiptAddress = et_receipt_address.getText().toString().trim()
        if (TextUtils.isEmpty(receiptAddress)) {
            Toast.makeText(this, "请填写收获地址", Toast.LENGTH_SHORT).show()
            return false
        }
        val address = et_detail_address.getText().toString().trim()
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "请填写详细地址", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun isMobileNO(phone: String): Boolean {
        val telRegex = "[1][358]\\d{9}"//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        return phone.matches(telRegex.toRegex())
    }

}