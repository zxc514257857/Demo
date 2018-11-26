package com.zhr.test.bean;

import java.util.List;

public class IdcardBean {

    /**
     * success : 1
     * result : [{"weaid":"1","days":"2018-11-04","week":"星期日","cityno":"beijing","citynm":"北京","cityid":"101010100","temperature":"11℃/2℃","humidity":"0%/0%","weather":"小雨转多云","weather_icon":"http://api.k780.com/upload/weather/d/7.gif","weather_icon1":"http://api.k780.com/upload/weather/n/1.gif","wind":"北风转东北风","winp":"3-4级转<3级","temp_high":"11","temp_low":"2","humi_high":"0","humi_low":"0","weatid":"8","weatid1":"2","windid":"39","winpid":"402","weather_iconid":"7","weather_iconid1":"1"},{"weaid":"1","days":"2018-11-05","week":"星期一","cityno":"beijing","citynm":"北京","cityid":"101010100","temperature":"10℃/3℃","humidity":"0%/0%","weather":"晴转多云","weather_icon":"http://api.k780.com/upload/weather/d/0.gif","weather_icon1":"http://api.k780.com/upload/weather/n/1.gif","wind":"东北风转北风","winp":"<3级","temp_high":"10","temp_low":"3","humi_high":"0","humi_low":"0","weatid":"1","weatid1":"2","windid":"25","winpid":"395","weather_iconid":"0","weather_iconid1":"1"},{"weaid":"1","days":"2018-11-06","week":"星期二","cityno":"beijing","citynm":"北京","cityid":"101010100","temperature":"11℃/1℃","humidity":"0%/0%","weather":"多云","weather_icon":"http://api.k780.com/upload/weather/d/1.gif","weather_icon1":"http://api.k780.com/upload/weather/n/1.gif","wind":"北风","winp":"<3级","temp_high":"11","temp_low":"1","humi_high":"0","humi_low":"0","weatid":"2","weatid1":"2","windid":"20","winpid":"395","weather_iconid":"1","weather_iconid1":"1"},{"weaid":"1","days":"2018-11-07","week":"星期三","cityno":"beijing","citynm":"北京","cityid":"101010100","temperature":"13℃/3℃","humidity":"0%/0%","weather":"多云","weather_icon":"http://api.k780.com/upload/weather/d/1.gif","weather_icon1":"http://api.k780.com/upload/weather/n/1.gif","wind":"南风","winp":"<3级","temp_high":"13","temp_low":"3","humi_high":"0","humi_low":"0","weatid":"2","weatid1":"2","windid":"5","winpid":"395","weather_iconid":"1","weather_iconid1":"1"},{"weaid":"1","days":"2018-11-08","week":"星期四","cityno":"beijing","citynm":"北京","cityid":"101010100","temperature":"14℃/2℃","humidity":"0%/0%","weather":"多云转晴","weather_icon":"http://api.k780.com/upload/weather/d/1.gif","weather_icon1":"http://api.k780.com/upload/weather/n/0.gif","wind":"西南风转西风","winp":"<3级","temp_high":"14","temp_low":"2","humi_high":"0","humi_low":"0","weatid":"2","weatid1":"1","windid":"17","winpid":"395","weather_iconid":"1","weather_iconid1":"0"},{"weaid":"1","days":"2018-11-09","week":"星期五","cityno":"beijing","citynm":"北京","cityid":"101010100","temperature":"15℃/2℃","humidity":"0%/0%","weather":"多云","weather_icon":"http://api.k780.com/upload/weather/d/1.gif","weather_icon1":"http://api.k780.com/upload/weather/n/1.gif","wind":"西南风转北风","winp":"<3级","temp_high":"15","temp_low":"2","humi_high":"0","humi_low":"0","weatid":"2","weatid1":"2","windid":"92","winpid":"395","weather_iconid":"1","weather_iconid1":"1"},{"weaid":"1","days":"2018-11-10","week":"星期六","cityno":"beijing","citynm":"北京","cityid":"101010100","temperature":"16℃/1℃","humidity":"0%/0%","weather":"多云转晴","weather_icon":"http://api.k780.com/upload/weather/d/1.gif","weather_icon1":"http://api.k780.com/upload/weather/n/0.gif","wind":"北风","winp":"<3级","temp_high":"16","temp_low":"1","humi_high":"0","humi_low":"0","weatid":"2","weatid1":"1","windid":"20","winpid":"395","weather_iconid":"1","weather_iconid1":"0"}]
     */
    private String success;
    private List<ResultBean> result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "IdcardBean{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}
