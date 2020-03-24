package ndk.pax.com.paxtakeout.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.item_home_common.view.*
import ndk.pax.com.paxtakeout.R
import android.os.Bundle
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.TextSliderView
import android.R.attr.keySet
import kotlinx.android.synthetic.main.item_title.view.*


/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/23:10:22
 *
 */
class HomeHeadView(context: Context?, attrs: AttributeSet?=null) : RelativeLayout(context, attrs){
        init {
            View.inflate(context, R.layout.item_title,this)
        }

        fun bindView(s: String) {
            val url_maps = HashMap<String, String>()
            url_maps["Hannibal"] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1584986043736&di=95b5b668158ae85fa31274f6d9058d1e&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fe9c0871fe111ce790c22052ed395ac19a8a9200ba5afa-VLWA3b_fw658"
            url_maps["Big Bang Theory"] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1584986123123&di=12a057c5c23ca9ff9a3157be2bd4772a&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F430eb25ca84a728281c7885c028cfa33d88aaa0f2bd31-KtuQa4_fw658"
            url_maps["House of Cards"] = "http://cdn3.nflximg.net/images/3093/2043093.jpg"
            url_maps["Game of Thrones"] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1584986123123&di=12a057c5c23ca9ff9a3157be2bd4772a&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F430eb25ca84a728281c7885c028cfa33d88aaa0f2bd31-KtuQa4_fw658"
                for ((key,value) in url_maps) {
                    val textSliderView = TextSliderView(context)
                    // initialize a SliderLayout
                    textSliderView
                            .description(key)
                            .image(value)
                            .setScaleType(BaseSliderView.ScaleType.Fit)
                        // .setOnSliderClickListener(this)
                       //add your extra information
                      // textSliderView.bundle(Bundle())
                      // textSliderView.bundle
                     //.putString("extra", name)
                      slider.addSlider(textSliderView)
                }
    }
}