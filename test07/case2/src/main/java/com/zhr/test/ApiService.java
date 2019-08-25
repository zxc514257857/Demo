package com.zhr.test;

import com.zhr.test.bean.CommercailInfoBean;
import com.zhr.test.bean.CommercialVolumeBean;
import com.zhr.test.bean.FanYiBean;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService {

    /**
     * Query 传递参数值  主要用于GET请求
     * 前加后不加 后加前要少  主要说加/ 的问题
     * @param str
     * @return
     */

    @GET("ajax.php?a=fy&f=auto&t=auto")
    Call<FanYiBean> getCall(@Query("w") String str);

    /**
     * QuaryMap 相当于要传递多个参数  主要用于GET请求
     * @param map
     * @return
     */
    @GET("ajax.php")
    Call<FanYiBean> getCall(@QueryMap Map<String , String> map);

    /**
     * Field 传递参数值  主要用于POST请求  POST和FormUrlEncoded一起使用
     * @param deviceId
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=Nm&m=MerchantScreenAppScaleNineteenMarch&a=index")
    Call<CommercailInfoBean> getCommercialInfo(@Field("device_id") String deviceId);

    /**
     * FieldMap 相当于要传递多个参数  主要用于GET请求  POST和FormUrlEncoded一起使用
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?g=Nm&m=MerchantScreenAppScaleNineteenMarch&a=get_volume")
    Call<CommercialVolumeBean> getCommercialInfo(@FieldMap Map<String , String> map);

    /**
     * Path  相当于路径中没有键只有值  替换{}中的值  常用于GET请求
     * @GET("users/{user}/question")
     * Call<List<Repo>> getData(@Path("user") String user);}
     */


    /**
     * Url  可以传递后半个url，也可以替换整个url  常用于GET请求
     * @param url
     * @return
     */
    @GET
    Call<FanYiBean> getCommercialInfo1(@Url String url);

}
