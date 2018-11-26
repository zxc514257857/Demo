package com.zhr.test.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class ResultBean {

    /**
     * weaid : 1
     * days : 2018-11-04
     * week : 星期日
     * cityno : beijing
     * citynm : 北京
     * cityid : 101010100
     * temperature : 11℃/2℃
     * humidity : 0%/0%
     * weather : 小雨转多云
     * weather_icon : http://api.k780.com/upload/weather/d/7.gif
     * weather_icon1 : http://api.k780.com/upload/weather/n/1.gif
     * wind : 北风转东北风
     * winp : 3-4级转<3级
     * temp_high : 11
     * temp_low : 2
     * humi_high : 0
     * humi_low : 0
     * weatid : 8
     * weatid1 : 2
     * windid : 39
     * winpid : 402
     * weather_iconid : 7
     * weather_iconid1 : 1
     *
     * 天气id 日期 星期 城市拼音全拼 城市中文名称 天气城市代码 最高温最低温温度 湿度 天气 风向 风级
     */
    private String weaid;
    private String days;
    private String week;
    private String cityno;
    private String citynm;
    private String cityid;
    private String temperature;
    private String humidity;
    private String weather;
    private String weatherIcon;
    private String weatherIcon1;
    private String wind;
    private String winp;
    private String tempHigh;
    private String tempLow;
    private String humiHigh;
    private String humiLow;
    private String weatid;
    private String weatid1;
    private String windid;
    private String winpid;
    @JSONField(name = "weather_iconid")
    private String weatherIconid;
    @JSONField(name = "weather_iconid1")
    private String weatherIconid1;

    public String getWeaid() {
        return weaid;
    }

    public void setWeaid(String weaid) {
        this.weaid = weaid;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getCityno() {
        return cityno;
    }

    public void setCityno(String cityno) {
        this.cityno = cityno;
    }

    public String getCitynm() {
        return citynm;
    }

    public void setCitynm(String citynm) {
        this.citynm = citynm;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public String getWeatherIcon1() {
        return weatherIcon1;
    }

    public void setWeatherIcon1(String weatherIcon1) {
        this.weatherIcon1 = weatherIcon1;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWinp() {
        return winp;
    }

    public void setWinp(String winp) {
        this.winp = winp;
    }

    public String getTempHigh() {
        return tempHigh;
    }

    public void setTempHigh(String tempHigh) {
        this.tempHigh = tempHigh;
    }

    public String getTempLow() {
        return tempLow;
    }

    public void setTempLow(String tempLow) {
        this.tempLow = tempLow;
    }

    public String getHumiHigh() {
        return humiHigh;
    }

    public void setHumi_high(String humiHigh) {
        this.humiHigh = humiHigh;
    }

    public String getHumiLow() {
        return humiLow;
    }

    public void setHumi_low(String humiLow) {
        this.humiLow = humiLow;
    }

    public String getWeatid() {
        return weatid;
    }

    public void setWeatid(String weatid) {
        this.weatid = weatid;
    }

    public String getWeatid1() {
        return weatid1;
    }

    public void setWeatid1(String weatid1) {
        this.weatid1 = weatid1;
    }

    public String getWindid() {
        return windid;
    }

    public void setWindid(String windid) {
        this.windid = windid;
    }

    public String getWinpid() {
        return winpid;
    }

    public void setWinpid(String winpid) {
        this.winpid = winpid;
    }

    public String getWeatherIconid() {
        return weatherIconid;
    }

    public void setWeatherIconid(String weatherIconid) {
        this.weatherIconid = weatherIconid;
    }

    public String getWeatherIconid1() {
        return weatherIconid1;
    }

    public void setWeatherIconid1(String weatherIconid1) {
        this.weatherIconid1 = weatherIconid1;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "weaid='" + weaid + '\'' +
                ", days='" + days + '\'' +
                ", week='" + week + '\'' +
                ", cityno='" + cityno + '\'' +
                ", citynm='" + citynm + '\'' +
                ", cityid='" + cityid + '\'' +
                ", temperature='" + temperature + '\'' +
                ", humidity='" + humidity + '\'' +
                ", weather='" + weather + '\'' +
                ", weatherIcon='" + weatherIcon + '\'' +
                ", weatherIcon1='" + weatherIcon1 + '\'' +
                ", wind='" + wind + '\'' +
                ", winp='" + winp + '\'' +
                ", tempHigh='" + tempHigh + '\'' +
                ", tempLow='" + tempLow + '\'' +
                ", humiHigh='" + humiHigh + '\'' +
                ", humiLow='" + humiLow + '\'' +
                ", weatid='" + weatid + '\'' +
                ", weatid1='" + weatid1 + '\'' +
                ", windid='" + windid + '\'' +
                ", winpid='" + winpid + '\'' +
                ", weatherIconid='" + weatherIconid + '\'' +
                ", weatherIconid1='" + weatherIconid1 + '\'' +
                '}';
    }
}
