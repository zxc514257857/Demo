package com.zhr.test.bean;

import java.util.List;

public class CommercailInfoBean {


    /**
     * title : 夏玉华
     * photo : http://cdn.zhumei.net/data/upload/market_26/20190410/5cad90c51c0b7.jpg
     * star : 3
     * mobile_phone : 15527962751
     * commitment_date : 2019-04-01
     * number : 010
     * ercode_title : 点评/点赞/投诉
     * qrcodeurl : http://nm.zhumei.net/index.php?g=Nmadmin&m=merchantCode&a=codeImg&mid=26&sid=4500
     * ad_list : []
     * ad_right_list : ["http://n.zhumei.net/data/upload/default/20181129/5bff3cd259786.jpg","http://n.zhumei.net/data/upload/default/20181129/5bff3cd2a39ac.jpg","http://n.zhumei.net/data/upload/default/20181129/5bff3cd2ca03a.jpg","http://n.zhumei.net/data/upload/default/20181129/5bff3cd30639a.jpg","http://n.zhumei.net/data/upload/default/20181129/5bff3cd33495a.jpg","http://n.zhumei.net/data/upload/default/20181129/5bff3cd35a80a.jpg","http://n.zhumei.net/data/upload/default/20181129/5bff3cd3cf74b.jpg","http://n.zhumei.net/data/upload/default/20181129/5bff3cd40989a.jpg"]
     * ad_type : 2
     * right_title_name : 承诺书
     * right_title : 我，农贸市场经营户郑重承诺：
     * right_content : 本商位绝不出售假冒伪劣、过期变质的商品；食品安全可追溯，票据齐全、价格公道、计量准确；诚信经营，文明经商。
     * right_name : 承诺人：
     * right_time : 2019-04-01
     * stall_id : 4500
     * market_id : 26
     * merchant_id : 2001
     * market_code : 421202A001
     * party_number : false
     * businesslicense_imgs : {"id_number_img":"http://cdn.zhumei.net/data/upload/market_26/20190215/5c665070ac899.jpg","business_img":"http://cdn.zhumei.net/data/upload/default/20181114/5bec0da885186.png","health_img":"http://cdn.zhumei.net/data/upload/market_26/20190215/5c665067d879b.jpg","other_img":"http://cdn.zhumei.net/data/upload/default/20181102/5bdbb878ec4dc.jpg"}
     * is_show : 0
     * excellent_party : 1
     * right_type : 0
     * is_color_switcher : 0
     * background_color : #888888
     * technical_support : 技术支持：杭州筑美信息科技有限公司      服务电话：400-0086522
     */

    private String title;
    private String photo;
    private String star;
    private String mobile_phone;
    private String commitment_date;
    private String number;
    private String ercode_title;
    private String qrcodeurl;
    private String ad_type;
    private String right_title_name;
    private String right_title;
    private String right_content;
    private String right_name;
    private String right_time;
    private String stall_id;
    private String market_id;
    private String merchant_id;
    private String market_code;
    private String party_number;
    private BusinesslicenseImgsBean businesslicense_imgs;
    private String is_show;
    private String excellent_party;
    private String right_type;
    private String is_color_switcher;
    private String background_color;
    private String technical_support;
    private List<?> ad_list;
    private List<String> ad_right_list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getCommitment_date() {
        return commitment_date;
    }

    public void setCommitment_date(String commitment_date) {
        this.commitment_date = commitment_date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getErcode_title() {
        return ercode_title;
    }

    public void setErcode_title(String ercode_title) {
        this.ercode_title = ercode_title;
    }

    public String getQrcodeurl() {
        return qrcodeurl;
    }

    public void setQrcodeurl(String qrcodeurl) {
        this.qrcodeurl = qrcodeurl;
    }

    public String getAd_type() {
        return ad_type;
    }

    public void setAd_type(String ad_type) {
        this.ad_type = ad_type;
    }

    public String getRight_title_name() {
        return right_title_name;
    }

    public void setRight_title_name(String right_title_name) {
        this.right_title_name = right_title_name;
    }

    public String getRight_title() {
        return right_title;
    }

    public void setRight_title(String right_title) {
        this.right_title = right_title;
    }

    public String getRight_content() {
        return right_content;
    }

    public void setRight_content(String right_content) {
        this.right_content = right_content;
    }

    public String getRight_name() {
        return right_name;
    }

    public void setRight_name(String right_name) {
        this.right_name = right_name;
    }

    public String getRight_time() {
        return right_time;
    }

    public void setRight_time(String right_time) {
        this.right_time = right_time;
    }

    public String getStall_id() {
        return stall_id;
    }

    public void setStall_id(String stall_id) {
        this.stall_id = stall_id;
    }

    public String getMarket_id() {
        return market_id;
    }

    public void setMarket_id(String market_id) {
        this.market_id = market_id;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getMarket_code() {
        return market_code;
    }

    public void setMarket_code(String market_code) {
        this.market_code = market_code;
    }

    public String getParty_number() {
        return party_number;
    }

    public void setParty_number(String party_number) {
        this.party_number = party_number;
    }

    public BusinesslicenseImgsBean getBusinesslicense_imgs() {
        return businesslicense_imgs;
    }

    public void setBusinesslicense_imgs(BusinesslicenseImgsBean businesslicense_imgs) {
        this.businesslicense_imgs = businesslicense_imgs;
    }

    public String getIs_show() {
        return is_show;
    }

    public void setIs_show(String is_show) {
        this.is_show = is_show;
    }

    public String getExcellent_party() {
        return excellent_party;
    }

    public void setExcellent_party(String excellent_party) {
        this.excellent_party = excellent_party;
    }

    public String getRight_type() {
        return right_type;
    }

    public void setRight_type(String right_type) {
        this.right_type = right_type;
    }

    public String getIs_color_switcher() {
        return is_color_switcher;
    }

    public void setIs_color_switcher(String is_color_switcher) {
        this.is_color_switcher = is_color_switcher;
    }

    public String getBackground_color() {
        return background_color;
    }

    public void setBackground_color(String background_color) {
        this.background_color = background_color;
    }

    public String getTechnical_support() {
        return technical_support;
    }

    public void setTechnical_support(String technical_support) {
        this.technical_support = technical_support;
    }

    public List<?> getAd_list() {
        return ad_list;
    }

    public void setAd_list(List<?> ad_list) {
        this.ad_list = ad_list;
    }

    public List<String> getAd_right_list() {
        return ad_right_list;
    }

    public void setAd_right_list(List<String> ad_right_list) {
        this.ad_right_list = ad_right_list;
    }

    public static class BusinesslicenseImgsBean {
        /**
         * id_number_img : http://cdn.zhumei.net/data/upload/market_26/20190215/5c665070ac899.jpg
         * business_img : http://cdn.zhumei.net/data/upload/default/20181114/5bec0da885186.png
         * health_img : http://cdn.zhumei.net/data/upload/market_26/20190215/5c665067d879b.jpg
         * other_img : http://cdn.zhumei.net/data/upload/default/20181102/5bdbb878ec4dc.jpg
         */

        private String id_number_img;
        private String business_img;
        private String health_img;
        private String other_img;

        public String getId_number_img() {
            return id_number_img;
        }

        public void setId_number_img(String id_number_img) {
            this.id_number_img = id_number_img;
        }

        public String getBusiness_img() {
            return business_img;
        }

        public void setBusiness_img(String business_img) {
            this.business_img = business_img;
        }

        public String getHealth_img() {
            return health_img;
        }

        public void setHealth_img(String health_img) {
            this.health_img = health_img;
        }

        public String getOther_img() {
            return other_img;
        }

        public void setOther_img(String other_img) {
            this.other_img = other_img;
        }
    }
}
