package ndk.pax.com.paxtakeout.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * User：Rowen
 * Description:
 * 时间: 2020/4/7:10:28
 */

public class goodInfo {

    private List<ListBeanX> list;

    public List<ListBeanX> getList() {
        return list;
    }

    public void setList(List<ListBeanX> list) {
        this.list = list;
    }

    public static class ListBeanX {
        /**
         * id : 101
         * info : (不与其它活动同享)13.9元特价套餐!!|13.9特价套餐!!(每单仅限2份)
         * list : [{"bargainPrice":true,"form":"肉末烧汁茄子+千叶豆腐+小食+时蔬+含粗粮米饭)","icon":"http://203.195.245.169:8080/TakeOutService/imgs/goods/caiping_taocan.webp","id":1001,"monthSaleNum":53,"name":"肉末烧汁茄子+千叶豆腐套餐(含粗粮米饭)","new":false,"newPrice":"13.9","oldPrice":30,"sellerId":1},{"bargainPrice":true,"form":"肉末烧汁茄子+榄菜肉末四季豆+小食+时蔬+含粗粮米饭)","icon":"http://203.195.245.169:8080/TakeOutService/imgs/goods/caiping_taocan.webp","id":1002,"monthSaleNum":37,"name":"肉末烧汁茄子+四季豆套餐(含粗粮米饭)","new":false,"newPrice":"13.9","oldPrice":30,"sellerId":1},{"bargainPrice":true,"form":"手撕杏鲍菇+千叶豆腐+小食+时蔬+含粗粮米饭)","icon":"http://203.195.245.169:8080/TakeOutService/imgs/goods/caiping_taocan.webp","id":1003,"monthSaleNum":27,"name":"手撕杏鲍菇+千叶豆腐套餐(含粗粮米饭)","new":false,"newPrice":"13.9","oldPrice":30,"sellerId":1},{"bargainPrice":true,"form":"肉末烧汁茄子+杏鲍菇+小食+时蔬+含粗粮米饭)","icon":"http://203.195.245.169:8080/TakeOutService/imgs/goods/caiping_taocan.webp","id":1004,"monthSaleNum":24,"name":"肉末烧汁茄子+杏鲍菇套餐(含粗粮米饭)","new":false,"newPrice":"13.9","oldPrice":30,"sellerId":1},{"bargainPrice":true,"form":"榄菜肉末四季豆+千叶豆腐+小食+时蔬+含粗粮米饭)","icon":"http://203.195.245.169:8080/TakeOutService/imgs/goods/caiping_taocan.webp","id":1005,"monthSaleNum":53,"name":"榄菜肉末四季豆+千叶豆腐套餐(含粗粮米饭)","new":false,"newPrice":"13.9","oldPrice":30,"sellerId":1},{"bargainPrice":true,"form":"榄菜肉末四季豆+手撕杏鲍菇+小食+时蔬+含粗粮米饭)","icon":"http://203.195.245.169:8080/TakeOutService/imgs/goods/caiping_taocan.webp","id":1006,"monthSaleNum":53,"name":"榄菜肉末四季豆+手撕杏鲍菇套餐(含粗粮米饭)","new":false,"newPrice":"13.9","oldPrice":30,"sellerId":1}]
         * name : 13.9特价套餐
         */

        private int id;
        private String info;
        private String name;
        private List<ListBean> list;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
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

            private boolean bargainPrice;
            private String form;
            private String icon;
            private int id;
            private int monthSaleNum;
            private String name;
            @SerializedName("new")
            private boolean newX;
            private String newPrice;
            private int oldPrice;
            private int sellerId;

            public boolean isBargainPrice() {
                return bargainPrice;
            }

            public void setBargainPrice(boolean bargainPrice) {
                this.bargainPrice = bargainPrice;
            }

            public String getForm() {
                return form;
            }

            public void setForm(String form) {
                this.form = form;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public boolean isNewX() {
                return newX;
            }

            public void setNewX(boolean newX) {
                this.newX = newX;
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
}
