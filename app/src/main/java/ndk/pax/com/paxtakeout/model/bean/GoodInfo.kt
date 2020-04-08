package ndk.pax.com.paxtakeout.model.bean

import com.google.gson.annotations.SerializedName

/**
 * User：Rowen
 * Description:商品信息结构，左右列表
 * 时间: 2020/4/7:10:30
 *
 */
class GoodInfo{
    private var list: List<ListBeanX>? = null
    fun getList(): List<ListBeanX>? {
        return list
    }

    fun setList(list: List<ListBeanX>) {
        this.list = list
    }

    class ListBeanX {
        /**
         * id : 101
         * info : (不与其它活动同享)13.9元特价套餐!!|13.9特价套餐!!(每单仅限2份)
         * list : [{"bargainPrice":true,"form":"肉末烧汁茄子+千叶豆腐+小食+时蔬+含粗粮米饭)","icon":"http://203.195.245.169:8080/TakeOutService/imgs/goods/caiping_taocan.webp","id":1001,"monthSaleNum":53,"name":"肉末烧汁茄子+千叶豆腐套餐(含粗粮米饭)","new":false,"newPrice":"13.9","oldPrice":30,"sellerId":1},{"bargainPrice":true,"form":"肉末烧汁茄子+榄菜肉末四季豆+小食+时蔬+含粗粮米饭)","icon":"http://203.195.245.169:8080/TakeOutService/imgs/goods/caiping_taocan.webp","id":1002,"monthSaleNum":37,"name":"肉末烧汁茄子+四季豆套餐(含粗粮米饭)","new":false,"newPrice":"13.9","oldPrice":30,"sellerId":1},{"bargainPrice":true,"form":"手撕杏鲍菇+千叶豆腐+小食+时蔬+含粗粮米饭)","icon":"http://203.195.245.169:8080/TakeOutService/imgs/goods/caiping_taocan.webp","id":1003,"monthSaleNum":27,"name":"手撕杏鲍菇+千叶豆腐套餐(含粗粮米饭)","new":false,"newPrice":"13.9","oldPrice":30,"sellerId":1},{"bargainPrice":true,"form":"肉末烧汁茄子+杏鲍菇+小食+时蔬+含粗粮米饭)","icon":"http://203.195.245.169:8080/TakeOutService/imgs/goods/caiping_taocan.webp","id":1004,"monthSaleNum":24,"name":"肉末烧汁茄子+杏鲍菇套餐(含粗粮米饭)","new":false,"newPrice":"13.9","oldPrice":30,"sellerId":1},{"bargainPrice":true,"form":"榄菜肉末四季豆+千叶豆腐+小食+时蔬+含粗粮米饭)","icon":"http://203.195.245.169:8080/TakeOutService/imgs/goods/caiping_taocan.webp","id":1005,"monthSaleNum":53,"name":"榄菜肉末四季豆+千叶豆腐套餐(含粗粮米饭)","new":false,"newPrice":"13.9","oldPrice":30,"sellerId":1},{"bargainPrice":true,"form":"榄菜肉末四季豆+手撕杏鲍菇+小食+时蔬+含粗粮米饭)","icon":"http://203.195.245.169:8080/TakeOutService/imgs/goods/caiping_taocan.webp","id":1006,"monthSaleNum":53,"name":"榄菜肉末四季豆+手撕杏鲍菇套餐(含粗粮米饭)","new":false,"newPrice":"13.9","oldPrice":30,"sellerId":1}]
         * name : 13.9特价套餐
         */

        var id: Int = 0
        var info: String? = null
        var name: String? = null
        var list: List<ListBean>? = null

        class ListBean {
            /**
             * bargainPrice : true
             * form : 肉末烧汁茄子+千叶豆腐+小食+时蔬+含粗粮米饭)
             * icon : http://203.195.245.169:8080/TakeOutService/imgs/goods/caiping_taocan.webp
             * id : 1001
             * monthSaleNum : 53
             * name : 肉末烧汁茄子+千叶豆腐套餐(含粗粮米饭)
             * new : false
             * newPrice : 13.9
             * oldPrice : 30
             * sellerId : 1
             */

            var isBargainPrice: Boolean = false
            var form: String? = null
            var icon: String? = null
            var id: Int = 0
            var monthSaleNum: Int = 0
            var name: String? = null
            @SerializedName("new")
            var isNewX: Boolean = false
            var newPrice: String? = null
            var oldPrice: Int = 0
            var sellerId: Int = 0
        }
    }
}