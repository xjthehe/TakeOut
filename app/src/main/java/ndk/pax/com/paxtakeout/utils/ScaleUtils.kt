package ndk.pax.com.paxtakeout.utils

import android.content.Context
import android.util.TypedValue

/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/23:9:56
 *
 */

object ScaleUtils{

    //dp转px
    fun  dip2px( context:Context, dpValue:Float):Int {
        val scale = context.getResources().getDisplayMetrics().density;
        return(dpValue * scale + 0.5f)as Int;
    }

//px转dp
    fun px2dip(context:Context, pxValue:Float):Int {
        return (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pxValue, context.getResources().getDisplayMetrics()))as Int;
    }

}