package com.glg.viewmodelandlivedata;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Description:
 *
 * @package: com.glg.viewmodelandlivedata
 * @className: StudyLifecycle
 * @author: gao
 * @date: 2020/8/31 14:13
 */
public class StudyLifecycle implements LifecycleObserver {

    private String TAG;

    public StudyLifecycle(String TAG) {
        this.TAG = TAG;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(){
        Log.i(TAG,"onCreate");
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(){
        Log.i(TAG,"onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(){
        Log.i(TAG,"onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(){
        Log.i(TAG,"onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(){
        Log.i(TAG,"onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(){
        Log.i(TAG,"onDestroy");
    }
}
