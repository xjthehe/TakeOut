package ndk.pax.com.paxtakeout.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import cn.jpush.android.api.JPushInterface
import com.heima.takeout.utils.OrderObservable
import ndk.pax.com.paxtakeout.TestActivity
import org.json.JSONException
import org.json.JSONObject

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/2:13:49
 *
 */

class MyReceiver:BroadcastReceiver(){
    private val TAG = "JIGUANG-Example"

    override fun onReceive(context: Context, intent: Intent) {
        try {
            val bundle = intent.getExtras()
            Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle!!))

            if (JPushInterface.ACTION_REGISTRATION_ID == intent.getAction()) {
                val regId = bundle!!.getString(JPushInterface.EXTRA_REGISTRATION_ID)
                Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId!!)
                //send the Registration Id to your server...

            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED == intent.getAction()) {
                Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle!!.getString(JPushInterface.EXTRA_MESSAGE)!!)

                val bundle:Bundle=intent.extras
                val message:String? = bundle.getString(JPushInterface.EXTRA_MESSAGE); //外卖订单状态改变
//                Log.e("message",message)
                val extras:String? = bundle.getString(JPushInterface.EXTRA_EXTRA);
//                Log.e("extras",message)
                if(extras?.isNotEmpty()!!){
                    //方法1 mainactivity---orderfragment---orderadapter
                    //方法2 观察者模式（orderadapter是观察者，被观察者 Myreceive）
                    OrderObservable.instance.newMsgComing(extras)
                }
//                processCustomMessage(context, bundle)

            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED == intent.getAction()) {
                Log.d(TAG, "[MyReceiver] 接收到推送下来的通知")
                val notifactionId = bundle!!.getInt(JPushInterface.EXTRA_NOTIFICATION_ID)
                Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: $notifactionId")

            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED == intent.getAction()) {
                //				Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
                //
                //				//打开自定义的Activity
                val i = Intent(context, TestActivity::class.java)
                i.putExtras(bundle!!)
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                context.startActivity(i)

            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK == intent.getAction()) {
                Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle!!.getString(JPushInterface.EXTRA_EXTRA)!!)
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

            } else if (JPushInterface.ACTION_CONNECTION_CHANGE == intent.getAction()) {
                val connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false)
                Log.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected)
            } else {
                Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction()!!)
            }
        } catch (e: Exception) {

        }

    }

    // 打印所有的 intent extra 数据
    private fun printBundle(bundle: Bundle): String {
        val sb = StringBuilder()
        for (key in bundle.keySet()) {
            if (key == JPushInterface.EXTRA_NOTIFICATION_ID) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key))
            } else if (key == JPushInterface.EXTRA_CONNECTION_CHANGE) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key))
            } else if (key == JPushInterface.EXTRA_EXTRA) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    Log.i(TAG, "This message has no Extra data")
                    continue
                }

                try {
                    val json = JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA))
                    val it = json.keys()

                    while (it.hasNext()) {
                        val myKey = it.next()
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]")
                    }
                } catch (e: JSONException) {
                    Log.e(TAG, "Get message extra JSON error!")
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.get(key))
            }
        }
        return sb.toString()
    }

    //send msg to MainActivity
    private fun processCustomMessage(context: Context, bundle: Bundle) {
        //		if (MainActivity.isForeground) {
        //			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        //			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        //			Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
        //			msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
        //			if (!ExampleUtil.isEmpty(extras)) {
        //				try {
        //					JSONObject extraJson = new JSONObject(extras);
        //					if (extraJson.length() > 0) {
        //						msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
        //					}
        //				} catch (JSONException e) {
        //
        //				}
        //
        //			}
        //			LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
        //		}
    }
}