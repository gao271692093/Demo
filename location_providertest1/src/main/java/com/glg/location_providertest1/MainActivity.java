package com.glg.location_providertest1;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        textView = findViewById(R.id.provider);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //获取所有的LocationProvider
//        List<String> providerNames = locationManager.getAllProviders();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (Iterator<String> iterator = providerNames.iterator(); iterator.hasNext();) {
//            stringBuilder.append(iterator.next() + "\n");
//        }
//        textView.setText(stringBuilder);
        //locationManager.getProvider(LocationManager.GPS_PROVIDER);//获取基于GPS的LocationProvider
        //获取最佳的LocationProvider
        Criteria criteria = new Criteria();//创建一个过滤条件对象
        criteria.setCostAllowed(false);//不收费的
        criteria.setAccuracy(Criteria.ACCURACY_FINE);//使用精度最准确的
        criteria.setPowerRequirement(Criteria.POWER_LOW);//使用耗电量最低的
        String provider = locationManager.getBestProvider(criteria, true);//获取最佳的LocationProvider名称
    }
}
