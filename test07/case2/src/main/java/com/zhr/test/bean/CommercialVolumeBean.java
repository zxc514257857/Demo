package com.zhr.test.bean;

import java.util.List;

public class CommercialVolumeBean {


    /**
     * num : 2873
     * data : 交易量：2873单
     * hotkey : [{"index":"1","plu1":"16807","plu2":""},{"index":"2","plu1":"16808","plu2":""},{"index":"3","plu1":"322","plu2":""},{"index":"4","plu1":"321","plu2":""},{"index":"5","plu1":"12023","plu2":""},{"index":"6","plu1":"1144","plu2":""},{"index":"7","plu1":"1815","plu2":""},{"index":"8","plu1":"6531","plu2":""},{"index":"9","plu1":"190","plu2":""},{"index":"10","plu1":"198","plu2":""},{"index":"11","plu1":"193","plu2":""}]
     * goods : [{"name":"猕猴桃","unit":"kg","price":"24.00","mem_price":"0.00","plu_type":"0","min_price":"0.00","discount":"1","change_price":"1","plu":"193","self_code":""},{"name":"草莓","unit":"kg","price":"76.00","mem_price":"0.00","plu_type":"0","min_price":"0.00","discount":"1","change_price":"1","plu":"322","self_code":""},{"name":"石榴","unit":"kg","price":"10.00","mem_price":"0.00","plu_type":"0","min_price":"0.00","discount":"1","change_price":"1","plu":"321","self_code":""},{"name":"红富士苹果","unit":"kg","price":"16.00","mem_price":"0.00","plu_type":"0","min_price":"0.00","discount":"1","change_price":"1","plu":"12023","self_code":""},{"name":"车厘子","unit":"kg","price":"176.00","mem_price":"0.00","plu_type":"0","min_price":"0.00","discount":"1","change_price":"1","plu":"1144","self_code":""},{"name":"皇冠梨","unit":"kg","price":"10.00","mem_price":"0.00","plu_type":"0","min_price":"0.00","discount":"1","change_price":"1","plu":"1815","self_code":""},{"name":"蛇果","unit":"kg","price":"24.00","mem_price":"0.00","plu_type":"0","min_price":"0.00","discount":"1","change_price":"1","plu":"6531","self_code":""},{"name":"香蕉","unit":"kg","price":"10.00","mem_price":"0.00","plu_type":"0","min_price":"0.00","discount":"1","change_price":"1","plu":"190","self_code":""},{"name":"香梨","unit":"kg","price":"20.00","mem_price":"0.00","plu_type":"0","min_price":"0.00","discount":"1","change_price":"1","plu":"198","self_code":""}]
     * ticket : [{"ticFlg":"0","aliagnFlg":"0","prtFlg":"0","prtData":"乌镇农贸市场"},{"ticFlg":"1","aliagnFlg":"0","prtFlg":"0","prtData":"谢谢惠顾"}]
     */

    private String num;
    private String data;
    private List<HotkeyBean> hotkey;
    private List<GoodsBean> goods;
    private List<TicketBean> ticket;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<HotkeyBean> getHotkey() {
        return hotkey;
    }

    public void setHotkey(List<HotkeyBean> hotkey) {
        this.hotkey = hotkey;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public List<TicketBean> getTicket() {
        return ticket;
    }

    public void setTicket(List<TicketBean> ticket) {
        this.ticket = ticket;
    }

    public static class HotkeyBean {
        /**
         * index : 1
         * plu1 : 16807
         * plu2 :
         */

        private String index;
        private String plu1;
        private String plu2;

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getPlu1() {
            return plu1;
        }

        public void setPlu1(String plu1) {
            this.plu1 = plu1;
        }

        public String getPlu2() {
            return plu2;
        }

        public void setPlu2(String plu2) {
            this.plu2 = plu2;
        }
    }

    public static class GoodsBean {
        /**
         * name : 猕猴桃
         * unit : kg
         * price : 24.00
         * mem_price : 0.00
         * plu_type : 0
         * min_price : 0.00
         * discount : 1
         * change_price : 1
         * plu : 193
         * self_code :
         */

        private String name;
        private String unit;
        private String price;
        private String mem_price;
        private String plu_type;
        private String min_price;
        private String discount;
        private String change_price;
        private String plu;
        private String self_code;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMem_price() {
            return mem_price;
        }

        public void setMem_price(String mem_price) {
            this.mem_price = mem_price;
        }

        public String getPlu_type() {
            return plu_type;
        }

        public void setPlu_type(String plu_type) {
            this.plu_type = plu_type;
        }

        public String getMin_price() {
            return min_price;
        }

        public void setMin_price(String min_price) {
            this.min_price = min_price;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getChange_price() {
            return change_price;
        }

        public void setChange_price(String change_price) {
            this.change_price = change_price;
        }

        public String getPlu() {
            return plu;
        }

        public void setPlu(String plu) {
            this.plu = plu;
        }

        public String getSelf_code() {
            return self_code;
        }

        public void setSelf_code(String self_code) {
            this.self_code = self_code;
        }
    }

    public static class TicketBean {
        /**
         * ticFlg : 0
         * aliagnFlg : 0
         * prtFlg : 0
         * prtData : 乌镇农贸市场
         */

        private String ticFlg;
        private String aliagnFlg;
        private String prtFlg;
        private String prtData;

        public String getTicFlg() {
            return ticFlg;
        }

        public void setTicFlg(String ticFlg) {
            this.ticFlg = ticFlg;
        }

        public String getAliagnFlg() {
            return aliagnFlg;
        }

        public void setAliagnFlg(String aliagnFlg) {
            this.aliagnFlg = aliagnFlg;
        }

        public String getPrtFlg() {
            return prtFlg;
        }

        public void setPrtFlg(String prtFlg) {
            this.prtFlg = prtFlg;
        }

        public String getPrtData() {
            return prtData;
        }

        public void setPrtData(String prtData) {
            this.prtData = prtData;
        }
    }
}
