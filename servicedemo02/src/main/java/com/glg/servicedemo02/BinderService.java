package com.glg.servicedemo02;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BinderService extends Service {
    public BinderService() {
    }

    public class MyBinder extends Binder{
        public BinderService getService() {
            return BinderService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MyBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public List<Integer> getRandomRed() {
        List<Integer> resArr = new ArrayList<Integer>();
        HashSet<Integer> set = new HashSet<Integer>();
        String strNumber = "";
        int number = 0;
        while(set.size()<6) {
            number = new Random().nextInt(33) + 1;
            set.add(number);
        }
//        for(int i = 0; i < 6; i += 1) {
//            number = new Random().nextInt(33) + 1;
//            resArr.add(number);
//            if(number<10) {
//                strNumber= "0" + String.valueOf(number);
//            } else {
//                strNumber = String.valueOf(number);
//            }
            //resArr.add(strNumber);
//        }
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            resArr.add(iterator.next());
            iterator.remove();
        }
        number = new Random().nextInt(16) + 1;
        resArr.add(number);
        return resArr;
    }
    /*
    public String getRandomBlue() {
        int number = new Random().nextInt(16) + 1;
        String strNumber = "";
        if(number<10) {
            strNumber = "0" + String.valueOf(number);
        } else {
            strNumber = String.valueOf(number);
        }
        return strNumber;
    }*/
}
