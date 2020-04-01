package ndk.pax.com.paxtakeout.model.bean;

import java.util.List;

/**
 * User：Rowen
 * Description:java里面GsonFormate转换对象，然后拷贝到kotlin类中 测试类
 * 时间: 2020/4/1:10:39
 */

public class SSS {

    /**
     * detail : {"address":"外环西路999号","pay":"微信支付","phone":"18575627762","time":"2016-8-8 18:00","username":"用户250"}
     * distribution : {"des":"湘赣木桶饭，好吃到爆","type":"yes"}
     * goodsInfos : [{"bargainPrice":false,"id":0,"isNew":false,"monthSaleNum":0,"name":"土豆炒牛肉","newPrice":"10.00","oldPrice":0,"sellerId":0},{"bargainPrice":false,"id":0,"isNew":false,"monthSaleNum":0,"name":"湘味腊肉饭","newPrice":"10.00","oldPrice":0,"sellerId":0}]
     * id : 0001
     * rider : {"id":1,"name":"广州骑士","phone":"13888888888"}
     * seller : {"id":0,"name":"一品木桶饭"}
     * type : 20
     */
    private String id;
    private String type;
    private RiderBean rider;
    private SellerBean seller;
    private List<GoodsInfosBean> goodsInfos;
    private DetailBean detail;
    private DistributionBean distribution;

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public DistributionBean getDistribution() {
        return distribution;
    }

    public void setDistribution(DistributionBean distribution) {
        this.distribution = distribution;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RiderBean getRider() {
        return rider;
    }

    public void setRider(RiderBean rider) {
        this.rider = rider;
    }

    public SellerBean getSeller() {
        return seller;
    }

    public void setSeller(SellerBean seller) {
        this.seller = seller;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<GoodsInfosBean> getGoodsInfos() {
        return goodsInfos;
    }

    public void setGoodsInfos(List<GoodsInfosBean> goodsInfos) {
        this.goodsInfos = goodsInfos;
    }

    public static class DetailBean {
        /**
         * address : 外环西路999号
         * pay : 微信支付
         * phone : 18575627762
         * time : 2016-8-8 18:00
         * username : 用户250
         */

        private String address;
        private String pay;
        private String phone;
        private String time;
        private String username;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPay() {
            return pay;
        }

        public void setPay(String pay) {
            this.pay = pay;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public static class DistributionBean {
        /**
         * des : 湘赣木桶饭，好吃到爆
         * type : yes
         */

        private String des;
        private String type;

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class RiderBean {
        /**
         * id : 1
         * name : 广州骑士
         * phone : 13888888888
         */

        private int id;
        private String name;
        private String phone;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

    public static class SellerBean {
        /**
         * id : 0
         * name : 一品木桶饭
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class GoodsInfosBean {
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

        private boolean bargainPrice;
        private int id;
        private boolean isNew;
        private int monthSaleNum;
        private String name;
        private String newPrice;
        private int oldPrice;
        private int sellerId;

        public boolean isBargainPrice() {
            return bargainPrice;
        }

        public void setBargainPrice(boolean bargainPrice) {
            this.bargainPrice = bargainPrice;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIsNew() {
            return isNew;
        }

        public void setIsNew(boolean isNew) {
            this.isNew = isNew;
        }

        public int getMonthSaleNum() {
            return monthSaleNum;
        }

        public void setMonthSaleNum(int monthSaleNum) {
            this.monthSaleNum = monthSaleNum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNewPrice() {
            return newPrice;
        }

        public void setNewPrice(String newPrice) {
            this.newPrice = newPrice;
        }

        public int getOldPrice() {
            return oldPrice;
        }

        public void setOldPrice(int oldPrice) {
            this.oldPrice = oldPrice;
        }

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }
    }
}
