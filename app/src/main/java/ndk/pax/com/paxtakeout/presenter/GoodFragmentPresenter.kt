package ndk.pax.com.paxtakeout.presenter

import android.util.Log
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import ndk.pax.com.paxtakeout.contract.GoodFragmentContract
import ndk.pax.com.paxtakeout.contract.NetPresenter
import ndk.pax.com.paxtakeout.model.bean.GoodInfo
import ndk.pax.com.paxtakeout.ui.activity.BusinessActivity
import ndk.pax.com.paxtakeout.ui.fragment.GoodsFragment
import ndk.pax.com.paxtakeout.utils.TakeoutApp
import org.json.JSONObject

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/7:10:24
 *
 */

class GoodFragmentPresenter(val view:GoodFragmentContract.View,val goodsFragment:GoodsFragment):GoodFragmentContract.Presenter,NetPresenter(){
    var allGoodTypeList:ArrayList<GoodInfo.ListBeanX> = ArrayList()
    var allGoodInfoList:ArrayList<GoodInfo.ListBeanX.ListBean> = ArrayList()
    var aTypeCount:Int=0

    override fun parseJson(json: String?){
        Log.e("GoodFragmentPresenter",json)
        //callback此处回调，开始解析
        val gson= Gson()
        val jsonObj= JSONObject(json)
        val allString=jsonObj.getString("list")

        //判断是否有缓存
        val hasSelectInfo=(goodsFragment.activity as BusinessActivity).hasSelectInfo



        //进行解析,以上必须注意下
        var goodInfoList:List<GoodInfo.ListBeanX> = gson.fromJson(allString,object: TypeToken<List<GoodInfo.ListBeanX>>(){}.type)

        if(goodInfoList.isNotEmpty()){
            allGoodTypeList.clear()
            allGoodTypeList.addAll(goodInfoList)
            Log.e("allGoodTypeList",allGoodTypeList.size.toString())

            for (i in 0 until allGoodTypeList.size){
                val goodTypeInfo=allGoodTypeList.get(i)
                //如果有缓存，添加红点个数【粗粮多少个】
                if(hasSelectInfo){
                    aTypeCount= TakeoutApp.inStance.queryCacheSelectedInfoByTypeId(goodTypeInfo.id)
                    goodTypeInfo.redDotCount=aTypeCount//一个类别的记录
                }
                val aTypeList=goodTypeInfo.list
                //双向绑定，list属于哪一个type
                for (j in 0 until aTypeList!!.size){
                    val goodsInfo=aTypeList.get(j)
                    //继续判断馒头在不在缓存,有缓存，提前把集合里面count属性赋值
                    if(aTypeCount>0){
                        val goodCount = TakeoutApp.inStance.queryCacheSelectedInfoByGoodsId(goodsInfo.id)
                        goodsInfo.count=goodCount//具体商品记录
                    }
                    goodsInfo.typeName= goodTypeInfo.name!!
                    goodsInfo.typeId= goodTypeInfo.id!!
                }
                allGoodInfoList.addAll(aTypeList)
            }
            //更新购物车UI
            (goodsFragment.activity as BusinessActivity).updateCart()

            view.onGoodInfoSuccess(allGoodTypeList,allGoodInfoList)
        }else{
            allGoodTypeList.clear()
            view.onGoodInfoError()
        }
    }

    //获取商品信息
    override fun getGoodInfo(sellerId: Int) {
        val businessInfo = service.getBusinessInfo(sellerId)
        businessInfo.enqueue(callBack)
    }

    //根据左侧id  查询右边第一次出现的id
    fun getGoodsPositionByTypeId(typeId: Int):Int {
        var position:Int=-1
        for (j in 0 until allGoodInfoList.size){
            val goodInfo = allGoodInfoList.get(j)
            if(goodInfo.typeId ==typeId){
                position=j
                break
            }
        }
        return position
    }

    //根据右侧id  查询左边第一次出现的id
    fun getTypePositionByTypeId(typeId: Int):Int {
        var position:Int=-1
        for (i in 0 until allGoodTypeList.size){
            val goodType = allGoodTypeList.get(i)
            if(goodType.id==typeId){
                position=i
                break
            }
        }
        return position
    }
    //获取右侧添加过到购物车的集合
    fun getCartGoodList():List<GoodInfo.ListBeanX.ListBean> {
        var goodInfoList:ArrayList<GoodInfo.ListBeanX.ListBean> = ArrayList()

        for (i in 0 until allGoodInfoList.size ){
            var goodInfo=allGoodInfoList[i]
            val count = goodInfo.count
             if(count>0){
                 goodInfoList.add(goodInfo)
             }
        }
        return goodInfoList;
    }


}