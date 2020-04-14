package ndk.pax.com.paxtakeout.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.model.bean.RecepitAddressBean
import ndk.pax.com.paxtakeout.ui.activity.AddOrEditAddressActivity

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/13:20:07
 *
 */

 class ReceiptRvAdapter(val context:Context):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var addressList= arrayListOf<RecepitAddressBean>()

    fun setData(beanList:ArrayList<RecepitAddressBean>){
        this.addressList=beanList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_receipt_address, parent, false)
        return ReceiptViewHolder(itemView)
    }

    override fun getItemCount(): Int =addressList.size


    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val receiptViewHolder = p0 as ReceiptViewHolder
        receiptViewHolder.bindData(addressList[p1])
    }

    inner class ReceiptViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivEdit:ImageView
        val tvName:TextView
        val tvSex:TextView
        val tv_phone:TextView
        val tv_label:TextView
        val tv_address:TextView
        lateinit var address:RecepitAddressBean

        init {
            ivEdit=itemView.findViewById(R.id.iv_edit)
            ivEdit.setOnClickListener{
                val intent= Intent(context, AddOrEditAddressActivity::class.java)
                intent.putExtra("address",address)
                context.startActivity(intent)
            }
            tvName=itemView.findViewById(R.id.tv_name)
            tvSex=itemView.findViewById(R.id.tv_sex)
            tv_phone=itemView.findViewById(R.id.tv_phone)
            tv_label=itemView.findViewById(R.id.tv_label)
            tv_address=itemView.findViewById(R.id.tv_address)
        }

        fun bindData(address: RecepitAddressBean) {
            this.address=address
            tvName.text=address.username
            tvSex.text=address.sex
            tv_phone.text=address.phone+","+address.phoneOther
            tv_address.text="${address.address},${address.detailaddress}"
            tv_label.text=address.label
        }


    }

}