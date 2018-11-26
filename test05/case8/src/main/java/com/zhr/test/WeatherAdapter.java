package com.zhr.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhr.test.bean.ResultBean;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherHolder>{

    private Context mContext;
    private List<ResultBean> mResult;

    WeatherAdapter(Context context, List<ResultBean> result) {
        this.mContext = context;
        this.mResult = result;
    }

    @NonNull
    @Override
    public WeatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WeatherHolder(LayoutInflater.from(mContext).inflate(R.layout.weather_item , parent , false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull WeatherHolder holder, int position) {
        if(mResult != null){
            ResultBean resultBean = mResult.get(position);
            if(resultBean != null){
                if(!TextUtils.isEmpty(resultBean.getWeatherIcon()) && !AppConstance.NULL_STR.equals(resultBean.getWeatherIcon())){
                    Glide.with(mContext).load(resultBean.getWeatherIcon()).into(holder.mIv);
                }

                holder.mTv.setText("日期：" + resultBean.getDays() + "\n"
                        + "星期：" + resultBean.getWeek() + "\n"
                        + "城市拼音：" + resultBean.getCityno()+ "\n"
                        + "城市名称：" + resultBean.getCitynm()+ "\n"
                        + "天气城市代码：" + resultBean.getCityid() + "\n"
                        + "最高最低温度：" + resultBean.getTemperature() + "\n"
                        + "最高最低湿度：" + resultBean.getHumidity() + "\n"
                        + "天气：" + resultBean.getWeather() + "\n"
                        + "风向：" + resultBean.getWind() + "\n"
                        + "风级：" + resultBean.getWinp()
                );
            }
        }
    }

    @Override
    public int getItemCount() {
        return mResult == null ? 0 : mResult.size();
    }

    class WeatherHolder extends RecyclerView.ViewHolder {

        private TextView mTv;
        private ImageView mIv;

        WeatherHolder(View itemView) {
            super(itemView);

            mTv = itemView.findViewById(R.id.tv_item);
            mIv = itemView.findViewById(R.id.iv_item);
        }
    }
}
