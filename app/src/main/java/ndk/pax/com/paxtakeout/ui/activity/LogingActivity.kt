package ndk.pax.com.paxtakeout.ui.activity

import android.os.Message
import android.util.Log
import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_user.view.*
import ndk.pax.com.paxtakeout.R
import cn.smssdk.SMSSDK.unregisterEventHandler



/**
 * User：Rowen
 * Description:登录界面
 * 时间: 2020/3/25:13:02
 *
 */

class LogingActivity:BaseActivity(){
    lateinit var mEventHandler:EventHandler
    override fun getLayoutResId(): Int= R.layout.activity_login


    override fun init() {
        super.init()
        initListener()
        //初始化hangdler
        initSmsHander()

    }

    private fun initListener() {
        iv_user_back.setOnClickListener {
            finish()
        }

        //通过手机号获取验证号
        tv_user_code.setOnClickListener {
            SMSSDK.getVerificationCode("86",et_user_phone.text.toString().trim())
        }

    }

    private fun initSmsHander() {
        mEventHandler= object : EventHandler() {
            override fun afterEvent(event: Int, result: Int, data: Any?) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //TODO:提交验证码成功
                        Log.e("sms", "提交验证码成功")
                        //TODO:短信验证已通过，登录外卖服务器
                        //mLoginPresenter.loginByPhone(mPhone, 2);
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //TODO:获取验证码成功
                        Log.e("sms", "获取验证码成功")
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    (data as Throwable).printStackTrace()
                }
            }
        }
        //注册一个事件回调监听，用于处理SMSSDK接口请求的结果
        SMSSDK.registerEventHandler(mEventHandler)
    }


    // 使用完EventHandler需注销，否则可能出现内存泄漏
    override fun onDestroy() {
        super.onDestroy()
        SMSSDK.unregisterEventHandler(mEventHandler)
    }
}