package com.glg.hefengweathertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import interfaces.heweather.com.interfacesmodule.bean.Code;
import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;
import interfaces.heweather.com.interfacesmodule.bean.air.now.AirNow;
import interfaces.heweather.com.interfacesmodule.bean.grid.forecast.GridForecast;
import interfaces.heweather.com.interfacesmodule.bean.grid.forecast.GridForecastBase;
import interfaces.heweather.com.interfacesmodule.bean.weather.hourly.Hourly;
import interfaces.heweather.com.interfacesmodule.bean.weather.lifestyle.Lifestyle;
import interfaces.heweather.com.interfacesmodule.bean.weather.lifestyle.LifestyleBase;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.NowBase;
import interfaces.heweather.com.interfacesmodule.view.HeConfig;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;

public class MainActivity extends AppCompatActivity {

    private String TAG = "和风天气API测试===>>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HeConfig.init("KuWoKey", "f9e1ff1714174702a1f064c120de5769");
        HeConfig.switchToFreeServerNode();
        /**
         * 实况天气
         * 实况天气即为当前时间点的天气状况以及温湿风压等气象指数，具体包含的数据：体感温度、
         * 实测温度、天气状况、风力、风速、风向、相对湿度、大气压强、降水量、能见度等。
         *
         * @param context  上下文
         * @param location 地址详解
         * @param lang     多语言，默认为简体中文，海外城市默认为英文
         * @param unit     单位选择，公制（m）或英制（i），默认为公制单位
         * @param listener 网络访问回调接口
         */
        HeWeather.getWeatherNow(MainActivity.this, "CN101010100", Lang.CHINESE_SIMPLIFIED , Unit.METRIC , new HeWeather.OnResultWeatherNowBeanListener() {
            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "Weather Now onError: ", e);
            }

            @Override
            public void onSuccess(Now dataObject) {
                Log.i(TAG, " Weather Now onSuccess: " + new Gson().toJson(dataObject));
                //先判断返回的status是否正确，当status正确时获取数据，若status不正确，可查看status对应的Code值找到原因
                if ( Code.OK.getCode().equalsIgnoreCase(dataObject.getStatus()) ){
                    //此时返回数据
                    NowBase now = dataObject.getNow();
                } else {
                    //在此查看返回数据失败的原因
                    String status = dataObject.getStatus();
                    Code code = Code.toEnum(status);
                    Log.i(TAG, "failed code: " + code);
                }
            }
        });
        HeWeather.getWeatherLifeStyle(this, "CN101010100", new HeWeather.OnResultWeatherLifeStyleBeanListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, "获取LifeStyle失败");
            }

            @Override
            public void onSuccess(Lifestyle lifestyle) {
                List<LifestyleBase> list = lifestyle.getLifestyle();
                for(LifestyleBase lifestyleBase : list) {
                    Log.i(TAG, lifestyleBase.getBrf() + " " + lifestyleBase.getTxt() + " " + lifestyleBase.getDate() + " " + lifestyleBase.getType());
                }
            }
        });
        HeWeather.getWeatherNow(this, "CN101010100", new HeWeather.OnResultWeatherNowBeanListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, "获取WeatherNow失败");
            }

            @Override
            public void onSuccess(Now now) {
                Log.i(TAG,now.getBasic().toString() + " " + now.getStatus() + " " + now.getUpdate().toString() + " " + now.getNow().toString());
            }
        });
        HeWeather.getWeatherGridForecast(this, "CN101010100", new HeWeather.OnResultWeatherGirdForecastBeanListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, "获取格点七天数据失败");
            }

            @Override
            public void onSuccess(GridForecast gridForecast) {
                List<GridForecastBase> list = gridForecast.getGrid_forecast();
                for(GridForecastBase  gridForecastBase : list) {
                    Log.i(TAG, gridForecastBase.toString());
                }
            }
        });
        HeWeather.getAirNow(this, "CN101010100", new HeWeather.OnResultAirNowBeansListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, "获取空气质量实况失败");
            }

            @Override
            public void onSuccess(AirNow airNow) {
                Log.i(TAG, airNow.getAir_now_city().getAqi() + " " + airNow.getAir_now_city().getPm25());
            }
        });
    }
}
