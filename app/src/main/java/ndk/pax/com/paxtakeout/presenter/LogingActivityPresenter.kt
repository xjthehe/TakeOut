package ndk.pax.com.paxtakeout.presenter
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.j256.ormlite.android.AndroidDatabaseConnection
import com.j256.ormlite.dao.Dao
import ndk.pax.com.paxtakeout.contract.BasePresenter
import ndk.pax.com.paxtakeout.contract.LogingActivityContract
import ndk.pax.com.paxtakeout.contract.NetPresenter
import ndk.pax.com.paxtakeout.model.bean.User
import ndk.pax.com.paxtakeout.model.dao.TakeOutOpenHelper
import ndk.pax.com.paxtakeout.model.net.TakeOutService
import ndk.pax.com.paxtakeout.utils.TakeoutApp
import java.sql.Savepoint
import java.util.concurrent.ExecutionException
/**
 * User：Rowen
 * Description:登录
 * 时间: 2020/3/27:12:01
 */
class LogingActivityPresenter(val view:LogingActivityContract.View,val context:Context) :LogingActivityContract.Presenter,NetPresenter(){
    override fun parseJson(json: String?){
        var user= Gson().fromJson(json,User::class.java)
        if(user!=null){
            //暂时用临时变量缓存
            TakeoutApp.user=user
            var savePoint:Savepoint?=null
            var androidDatabaseConnection:AndroidDatabaseConnection?=null
              try {
                  //事务处理
                  //第三方ORM框架(ormlite greedao),直接操作javabean
                  val takeOutOpenHelper=TakeOutOpenHelper(context)
                  val userDao: Dao<User, Int> = takeOutOpenHelper.getDao(User::class.java)
                  //保存用户信息
            //            userDao.create(user)
            //            userDao.createOrUpdate(user)
                  //开启事务点  保证张三 -1000 李四+1000
                  androidDatabaseConnection= AndroidDatabaseConnection(takeOutOpenHelper.writableDatabase, true)
                  savePoint= androidDatabaseConnection.setSavePoint("start")
                  androidDatabaseConnection.isAutoCommit=false//取消自动提交
                  //区分是否老用户
                  //1.step1
                  val userList:List<User> = userDao.queryForAll()
                  var isOldUser:Boolean=false

                  for (i in 0 until  userList.size){
                      val u=userList.get(i)
                      if(u.name ==user.name){
                          isOldUser=true
                          Log.e("login","老用户更新信息")
                          break
                      }
                  }
                  //2.step2
                  if(isOldUser){
                      userDao.update(user)
                      Log.e("login","老用户更新信息")
                  }else{
                      userDao.create(user)
                      Log.e("login","新用户登录")
                  }
              }catch (e:Exception){
                  view.onLoginByPhoneFail()
                  Log.e("login",e.localizedMessage)
                  if(androidDatabaseConnection!=null){
                      androidDatabaseConnection.rollback(savePoint)
                  }
              }
            androidDatabaseConnection?.commit(savePoint)
            view.onLoginByPhoneSuccess()
        }else{
            view.onLoginByPhoneFail()
        }
    }

    override fun loginByPhone(phone: String) {
        val loginInfo = service.getLoginInfo(phone)
        loginInfo.enqueue(callBack)
    }
}