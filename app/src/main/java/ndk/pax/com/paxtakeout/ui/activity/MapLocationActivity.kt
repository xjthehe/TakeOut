package ndk.pax.com.paxtakeout.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import ndk.pax.com.paxtakeout.R
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.MapView
import com.amap.api.maps2d.model.LatLng
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.core.PoiItem
import com.amap.api.services.poisearch.PoiResult
import com.amap.api.services.poisearch.PoiSearch
import kotlinx.android.synthetic.main.activity_map_location.*
import ndk.pax.com.paxtakeout.adapter.AroundRvAdapter


/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/14:21:05
 *
 */

class MapLocationActivity:AppCompatActivity(), AMapLocationListener, PoiSearch.OnPoiSearchListener {
    //声明AMapLocationClient类对象
    lateinit var mLocationClient: AMapLocationClient
    //声明AMapLocationClient类对象
    lateinit var  aMap:AMap
    lateinit var roundAdapter:AroundRvAdapter

    override fun onPoiItemSearched(p0: PoiItem?, p1: Int) {

    }

    override fun onPoiSearched(poiResult: PoiResult?, rcode: Int) {
        if(rcode ==1000){
            if(poiResult!=null){
                val poiItem:ArrayList<PoiItem> =poiResult.pois
                roundAdapter.setData(poiItem)
                Toast.makeText(this,"一共有"+poiItem.size+"个兴趣点",Toast.LENGTH_SHORT).show()
            }
        }
    }

    //定位监听回调
    override fun onLocationChanged(amapLocation: AMapLocation?) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //解析定位结果
              Toast.makeText(this,amapLocation.address,Toast.LENGTH_SHORT).show()
//                Log.e("amapLocation",amapLocation.address)
                aMap.moveCamera(CameraUpdateFactory.changeLatLng(LatLng(amapLocation.latitude,amapLocation.longitude)))
                aMap.moveCamera(CameraUpdateFactory.zoomTo(16.0f))
                doSearchQuery(amapLocation)
                mLocationClient.stopLocation()
            }
        }
    }

    private fun  doSearchQuery(amapLocation: AMapLocation) {
        val query=PoiSearch.Query("","",amapLocation.city)
        query.pageSize=30
        query.pageNum=1
        val poiSearch= PoiSearch(this,query)
        //搜索范围
        poiSearch.bound=PoiSearch.SearchBound(LatLonPoint(amapLocation.latitude,amapLocation.longitude),500)
        poiSearch.setOnPoiSearchListener(this)
        poiSearch.searchPOIAsyn()
    }

//    override fun getLayoutResId(): Int {
//        return R.layout.activity_map_location
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_location)

        val mapView = findViewById(R.id.mMapView) as MapView
        rv_around.layoutManager= LinearLayoutManager(this)
        roundAdapter= AroundRvAdapter(this)
        rv_around.adapter=roundAdapter

        mapView.onCreate(savedInstanceState)//此方法必须重写
        aMap = mapView.map
        checkPermision()
    }

    val WRITE_COARSE_LOCATION_REQUEST_CODE=10
    private fun checkPermision() {
    //这里以ACCESS_COARSE_LOCATION为例
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    WRITE_COARSE_LOCATION_REQUEST_CODE);//自定义的code
        }else{
            initLocation()

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
            initLocation()
        }else{
            finish()
            Toast.makeText(this,"权限不足",Toast.LENGTH_SHORT).show()
        }
    }

    private fun initLocation() {
        //初始化定位
        mLocationClient = AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //启动定位
        val option=AMapLocationClientOption()
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //关闭缓存机制
        option.setLocationCacheEnable(false);

        mLocationClient.setLocationOption(option)
        mLocationClient.stopLocation();
        mLocationClient.startLocation();
    }
}