package ndk.pax.com.paxtakeout.model.dao

import android.content.Context
import android.util.Log
import com.j256.ormlite.dao.Dao
import ndk.pax.com.paxtakeout.model.bean.RecepitAddressBean

/**
 * Created by Administrator on 2020/4/13.
 */
class AddressDao(val context:Context){
    lateinit var  addressDao: Dao<RecepitAddressBean,Int>
    init {
        val openHelper = TakeOutOpenHelper(context)
        addressDao= openHelper.getDao(RecepitAddressBean::class.java)
    }

    fun addRecepitAddressBean(bean:RecepitAddressBean){
      try {
          addressDao.create(bean)
      }catch (e:Exception){
          Log.e("address",e.localizedMessage)
      }
    }


    fun deleteRecepitAddressBean(bean:RecepitAddressBean){
        try {
            addressDao.delete(bean)
        }catch (e:Exception){
            Log.e("address",e.localizedMessage)
        }
    }

    fun updateRecepitAddressBean(bean:RecepitAddressBean){
        try {
            addressDao.update(bean)
        }catch (e:Exception){
            Log.e("address",e.localizedMessage)
        }
    }


    fun queryAllAddress():List<RecepitAddressBean>{
        try {
          return  addressDao.queryForAll()
        }catch (e:Exception){
            Log.e("address",e.localizedMessage)
            return ArrayList<RecepitAddressBean>()
        }
    }
}