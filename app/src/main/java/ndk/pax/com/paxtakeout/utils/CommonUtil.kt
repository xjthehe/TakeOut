package ndk.pax.com.paxtakeout.utils

import android.content.Context

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/12:20:16
 *
 */

class CommonUtil{

    companion object {
        //是否有虚拟按键
        fun checkDeviceHasNavigationBar(context: Context): Boolean {
            var hasNavigationBar: Boolean = false;
            val resources=context.resources
            val id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
            if (id > 0) {
                hasNavigationBar = resources.getBoolean(id);
            }
            try {
                val systemPropertiesClass = Class.forName("android.os.SystemProperties");
                val m = systemPropertiesClass.getMethod("get", String::class.java);
                val navBarOverride = m.invoke(systemPropertiesClass, "qemu.hw.mainkeys") as String;
                if ("1".equals(navBarOverride)) {
                    //不存在虚拟按键
                    hasNavigationBar = false
                } else if ("0".equals(navBarOverride)) {
                    //存在虚拟按键
                    hasNavigationBar = true
                }
            } catch (e: Exception) {
            }
            return hasNavigationBar;
        }


    }



}
