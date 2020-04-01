package ndk.pax.com.paxtakeout.model.bean

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/1:10:24
 *
 */

class Order{
    /**
     * detail : {"address":"外环西路999号","pay":"微信支付","phone":"18575627762","time":"2016-8-8 18:00","username":"用户250"}
     * distribution : {"des":"湘赣木桶饭，好吃到爆","type":"yes"}
     * goodsInfos : [{"bargainPrice":false,"id":0,"isNew":false,"monthSaleNum":0,"name":"土豆炒牛肉","newPrice":"10.00","oldPrice":0,"sellerId":0},{"bargainPrice":false,"id":0,"isNew":false,"monthSaleNum":0,"name":"湘味腊肉饭","newPrice":"10.00","oldPrice":0,"sellerId":0}]
     * id : 0001
     * rider : {"id":1,"name":"广州骑士","phone":"13888888888"}
     * seller : {"id":0,"name":"一品木桶饭"}
     * type : 20
     */
    private var id: String? = null
    private var type: String? = null
    private var rider: RiderBean? = null
    private var seller: SellerBean? = null
    private var goodsInfos: List<GoodsInfosBean>? = null
    private var detail: DetailBean? = null
    private var distribution: DistributionBean? = null

    fun getDetail(): DetailBean? {
        return detail
    }

    fun setDetail(detail: DetailBean) {
        this.detail = detail
    }

    fun getDistribution(): DistributionBean? {
        return distribution
    }

    fun setDistribution(distribution: DistributionBean) {
        this.distribution = distribution
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getRider(): RiderBean? {
        return rider
    }

    fun setRider(rider: RiderBean) {
        this.rider = rider
    }

    fun getSeller(): SellerBean? {
        return seller
    }

    fun setSeller(seller: SellerBean) {
        this.seller = seller
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String) {
        this.type = type
    }

    fun getGoodsInfos(): List<GoodsInfosBean>? {
        return goodsInfos
    }

    fun setGoodsInfos(goodsInfos: List<GoodsInfosBean>) {
        this.goodsInfos = goodsInfos
    }

    class DetailBean {
        /**
         * address : 外环西路999号
         * pay : 微信支付
         * phone : 18575627762
         * time : 2016-8-8 18:00
         * username : 用户250
         */

        var address: String? = null
        var pay: String? = null
        var phone: String? = null
        var time: String? = null
        var username: String? = null
    }

    class DistributionBean {
        /**
         * des : 湘赣木桶饭，好吃到爆
         * type : yes
         */

        var des: String? = null
        var type: String? = null
    }

    class RiderBean {
        /**
         * id : 1
         * name : 广州骑士
         * phone : 13888888888
         */

        var id: Int = 0
        var name: String? = null
        var phone: String? = null
    }

    class SellerBean {
        /**
         * id : 0
         * name : 一品木桶饭
         */

        var id: Int = 0
        var name: String? = null

    }

    class GoodsInfosBean {
        /**
         * bargainPrice : false
         * id : 0
         * isNew : false
         * monthSaleNum : 0
         * name : 土豆炒牛肉
         * newPrice : 10.00
         * oldPrice : 0
         * sellerId : 0
         */

        var isBargainPrice: Boolean = false
        var id: Int = 0
        var isIsNew: Boolean = false
        var monthSaleNum: Int = 0
        var name: String? = null
        var newPrice: String? = null
        var oldPrice: Int = 0
        var sellerId: Int = 0

    }
}