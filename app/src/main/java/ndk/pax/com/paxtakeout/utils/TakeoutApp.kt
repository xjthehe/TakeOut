package ndk.pax.com.paxtakeout.utils

import cn.jpush.android.api.JPushInterface
import com.mob.MobApplication
import ndk.pax.com.paxtakeout.model.bean.User

/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/25:14:06
 *
 */

class TakeoutApp:MobApplication(){
    companion object {
        var user:User= User()
    }

    override fun onCreate() {
        super.onCreate()
        user.id=-1//未登录用户
        //极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}