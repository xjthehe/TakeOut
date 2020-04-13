package ndk.pax.com.paxtakeout.model.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import ndk.pax.com.paxtakeout.model.bean.RecepitAddressBean
import ndk.pax.com.paxtakeout.model.bean.User
import java.sql.SQLException

/**
 * User：Rowen
 * Description:
 * 时间: 2020/3/31:16:35
 *
 */

class TakeOutOpenHelper(val context: Context) :OrmLiteSqliteOpenHelper(context,"takeout_ktlin.db",null,2){
    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
       //创建User表
        TableUtils.createTable(connectionSource,User::class.java)

        TableUtils.createTable(connectionSource,RecepitAddressBean::class.java)
    }

    override fun onUpgrade(database: SQLiteDatabase?, connectionSource: ConnectionSource?, oldVersion: Int, newVersion: Int) {
        try {
            TableUtils.createTable(connectionSource,RecepitAddressBean::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


}