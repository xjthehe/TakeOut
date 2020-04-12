package ndk.pax.com.paxtakeout.utils

import cn.jpush.android.api.JPushInterface
import com.mob.MobApplication
import ndk.pax.com.paxtakeout.model.bean.CacheSelectedInfo
import ndk.pax.com.paxtakeout.model.bean.User
import java.util.concurrent.CopyOnWriteArrayList



/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/25:14:06
 *
 */

class TakeoutApp:MobApplication(){
    //点餐缓存
    val infos:CopyOnWriteArrayList<CacheSelectedInfo> = CopyOnWriteArrayList()

    companion object {
        var user:User= User()
        lateinit var inStance: TakeoutApp
    }


    override fun onCreate() {
        super.onCreate()
        user.id=-1//未登录用户
        inStance=this
        //极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    //根据馒头id,查找馒头个数
    fun queryCacheSelectedInfoByGoodsId(goodsId: Int): Int {
        var count = 0
        for (i in 0 until infos.size) {
            val (_, _, goodsId1, count1) = infos[i]
            if (goodsId1 == goodsId) {
                count = count1
                break
            }
        }
        return count
    }

    fun queryCacheSelectedInfoByTypeId(typeId: Int): Int {
        var count = 0
        for (i in 0 until infos.size) {
            val (_, typeId1, _, count1) = infos[i]
            if (typeId1 == typeId) {
                count = count + count1
            }
        }
        return count
    }

    fun queryCacheSelectedInfoBySellerId(sellerId: Int): Int {
        var count = 0
        for (i in 0 until infos.size) {
            val (sellerId1, _, _, count1) = infos[i]
            if (sellerId1 == sellerId) {
                count = count + count1
            }
        }
        return count
    }

    fun addCacheSelectedInfo(info: CacheSelectedInfo) {
        infos.add(info)
    }

    fun clearCacheSelectedInfo(sellerId: Int) {
        for (i in 0 until infos.size) {
            val info = infos[i]
            if (info.sellerId == sellerId) {
                infos.remove(info)
            }
        }
    }

    fun deleteCacheSelectedInfo(goodsId: Int) {
        for (i in 0 until infos.size) {
            val info = infos[i]
            if (info.goodsId == goodsId) {
                infos.remove(info)
                break
            }
        }
    }

    fun updateCacheSelectedInfo(goodsId: Int, operation: Int) {
        for (i in 0 until infos.size) {
            val info = infos[i]
            if (info.goodsId == goodsId) {
                when (operation) {
                    Constants.ADD -> info.count = info.count + 1
                    Constants.MINUS -> info.count = info.count - 1
                }

            }
        }
    }



}