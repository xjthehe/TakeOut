package ndk.pax.com.paxtakeout.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ndk.pax.com.paxtakeout.R
import ndk.pax.com.paxtakeout.model.bean.RecepitAddressBean

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

    class ReceiptViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvEdit:ImageView
        val tvName:TextView
        val tvSex:TextView
        val tv_phone:TextView
        val tv_label:TextView
        val tv_address:TextView

        init {
            tvEdit=itemView.findViewById(R.id.iv_edit)
            tvEdit.setOnClickListener{

            }
            tvName=itemView.findViewById(R.id.tv_name)
            tvSex=itemView.findViewById(R.id.tv_sex)
            tv_phone=itemView.findViewById(R.id.tv_phone)
            tv_label=itemView.findViewById(R.id.tv_label)
            tv_address=itemView.findViewById(R.id.tv_address)
        }

        fun bindData(address: RecepitAddressBean) {
            tvName.text=address.username
            tvSex.text=address.sex
            tv_phone.text=address.phone+","+address.phoneOther
            tv_address.text="${address.address},${address.detailaddress}"
            tv_label.text=address.label

        }


    }

}