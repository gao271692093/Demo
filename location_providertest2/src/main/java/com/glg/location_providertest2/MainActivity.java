package com.glg.location_providertest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        textView = findViewById(R.id.location);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, //指定GPS定位的提供者
                100, //时间间隔
                1,//位置间隔1米
                new LocationListener() {//监听GPS定位信息是否改变
                    @Override
                    public void onLocationChanged(Location location) {//GPS位置改变时调用

                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {//GPS状态改变时调用

                    }

                    @Override
                    public void onProviderEnabled(String provider) {//定位提供者启动的时候调用

                    }

                    @Override
                    public void onProviderDisabled(String provider) {//定位提供者关闭的时候调用

                    }
                }
        );
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);//获取最新的GPS定位信息
        locationUpdates(location);
    }

    private void locationUpdates(Location location) {
        if(location!=null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("您的位置是：\n");
            stringBuilder.append("经度：" + location.getLongitude() + "\n");
            stringBuilder.append("纬度：" + location.getLatitude());
            textView.setText(stringBuilder);
        } else {
            textView.setText("没有获取到GPS信息");
        }
    }
}
