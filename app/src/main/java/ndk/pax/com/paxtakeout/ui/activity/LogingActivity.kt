package ndk.pax.com.paxtakeout.ui.activity

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.text.TextUtils
import android.util.Log
import android.widget.Switch
import android.widget.Toast
import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_user.view.*
import ndk.pax.com.paxtakeout.R
import cn.smssdk.SMSSDK.unregisterEventHandler
import ndk.pax.com.paxtakeout.contract.LogingActivityContract
import ndk.pax.com.paxtakeout.presenter.LogingActivityPresenter
import ndk.pax.com.paxtakeout.utils.SMSUtil


/**
 * User：Rowen
 * Description:登录界面
 * 时间: 2020/3/25:13:02
 *
 */

class LogingActivity:BaseActivity(),LogingActivityContract.View{

    lateinit var mEventHandler:EventHandler
    lateinit var phone:String

    override fun getLayoutResId(): Int= R.layout.activity_login

    val logingActivityPresenter:LogingActivityPresenter by lazy {
        LogingActivityPresenter(this,this)
    }

    override fun onLoginByPhoneSuccess() {
        Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onLoginByPhoneFail() {
        Toast.makeText(this,"登陆失败",Toast.LENGTH_SHORT).show()
    }


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
            phone=et_user_phone.text.toString().trim()
            //验证是否是手机号
            if(SMSUtil.judgePhoneNums(this,phone)){
                SMSSDK.getVerificationCode("86",phone)
                //倒计时
                //按钮禁用
                tv_user_code.isEnabled=false
                Thread(CuntDownTask()).start()
            }
        }

        //提交验证码
        login.setOnClickListener {
            phone=et_user_phone.text.toString().trim()
            val code=et_user_code.text.toString().trim()
            //测试时候关闭短信验证
//            if(SMSUtil.judgePhoneNums(this,phone)&&!TextUtils.isEmpty(code)){
//                SMSSDK.submitVerificationCode("86",phone,code)
//            }
            //模块测试时候打开
            logingActivityPresenter.loginByPhone(phone)
        }
    }

    var handler= @SuppressLint("HandlerLeak")
    object :Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
              when(msg?.what) {
                  TIME_MINUS->tv_user_code.text="剩余时间${time}"
                  TIME_END->{
                      tv_user_code.isEnabled=true
                      tv_user_code.text="点击重发"
                      time=60
                  }

              }

        }
    }
    companion object {
        val TIME_MINUS=-1
        val TIME_END=0

    }
    var time=60
    inner class CuntDownTask():Runnable {
        override fun run() {
            //
            while (time>0){
                handler.sendEmptyMessage(TIME_MINUS)
                SystemClock.sleep(999)
                time--
            }
            handler.sendEmptyMessage(TIME_END)

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
                        logingActivityPresenter.loginByPhone(phone)
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