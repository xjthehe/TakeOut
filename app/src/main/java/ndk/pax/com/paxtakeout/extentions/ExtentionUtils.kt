package ndk.pax.com.paxtakeout.extentions

import android.content.Context


/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/14:13:48
 *
 */

fun String.isVaildName():Boolean=this.matches(kotlin.text.Regex("^[a-zA-Z]\\w{2,19}$"));
fun String.isValidPassword(): Boolean = this.matches(Regex("^[0-9]{3,20}$"));

fun<K,V> MutableMap<K,V>.toVarargArray():Array<Pair<K,V>>{
    //将MutliMap转换成Pair类型的数组
    return map {
        Pair(it.key,it.value)
    }.toTypedArray()
}


fun Int.dip2px(context: Context):Int{
    val scale = context.getResources().getDisplayMetrics().density;
    return(toFloat() * scale + 0.5f).toInt();
}


