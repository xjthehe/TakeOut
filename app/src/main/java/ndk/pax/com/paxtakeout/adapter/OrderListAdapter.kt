package ndk.pax.com.paxtakeout.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.heima.takeout.utils.OrderObservable
import ndk.pax.com.paxtakeout.model.bean.Order
import ndk.pax.com.paxtakeout.widget.OrderListItemView
import org.json.JSONObject
import java.util.*

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/1:19:17
 *
 */

class OrderListAdapter(val context :Context,val mDatas:List<Order>):RecyclerView.Adapter<RecyclerView.ViewHolder>(),Observer{
    private var orderList:MutableList<Order> = ArrayList<Order>()

    init {
        //让观察者与被观察者建立关系
        OrderObservable.instance.addObserver(this)
    }


    //观察者响应
    override fun update(observer: Observable?, arg: Any?){
        //receive notifyodserverble回调
        var json:JSONObject= JSONObject(arg as String)
        val pushOrderId=json.getString("orderId")
        val pushType=json.getString("type")

        for(i in 0 until mDatas.size){
            val order=mDatas.get(i)
            if(order.getId().equals(pushOrderId)){
                //对相应的id订单的type进行更换
                mDatas.get(i).setType(pushType)
            }
        }
        notifyDataSetChanged()
        Log.e("update","观察者收到了消息，消息内容："+arg)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return OrderViewHolder(OrderListItemView(context))
    }

    override fun getItemCount(): Int =mDatas.size

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, position: Int) {
        val itemView = p0.itemView as OrderListItemView
        itemView.bindView(mDatas.get(position))
    }

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

}