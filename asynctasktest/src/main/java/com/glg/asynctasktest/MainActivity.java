package com.glg.asynctasktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private String TAG = "test";
    private MyAsyncTask task1 = null;
    private MyAsyncTask task2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar progressBar1 = findViewById(R.id.progress1);
        final ProgressBar progressBar2 = findViewById(R.id.progress2);
        final Button button1 = findViewById(R.id.btnStart1);
        final Button button2 = findViewById(R.id.btnStart2);
        final TextView textView1 = findViewById(R.id.tvTask1);
        final TextView textView2 = findViewById(R.id.tvTask2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button1.getText().equals("启动")) {
                    task1 = new MyAsyncTask(progressBar1, button1, textView1);
                    task1.execute();
                } else {
                    if(task1 != null) {
                        task1.cancel(true);
                    }
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button2.getText().equals("启动")) {
                    task2 = new MyAsyncTask(progressBar2, button2, textView2);
                    task2.execute();
                } else {
                    if(task2 != null) {
                        task2.cancel(true);
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(task1 != null && task1.isCancelled()) {
            task1.cancel(true);
        }
        if(task2 != null && task2.isCancelled()) {
            task2.cancel(true);
        }
    }
}

class MyAsyncTask extends AsyncTask {

    private ProgressBar progressBar;

    private Button button;

    private TextView textView;

    public MyAsyncTask(ProgressBar progressBar, Button button, TextView textView) {
        this.progressBar = progressBar;
        this.button = button;
        this.textView = textView;
    }

    @Override
    protected String doInBackground(Object[] objects) {
        Log.i(TAG, "doInBackground:" + Thread.currentThread().getName());
        try {
            for(int i = 0; i < 100; i += 1) {
                publishProgress(i + 1);
                Thread.sleep(10);
            }
            return "任务完成";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "任务出错";
        }
    }

    @Override
    protected void onPreExecute() {
        Log.i(TAG, "onPreExecute:" + Thread.currentThread().getName());
        super.onPreExecute();
        progressBar.setProgress(0);
        progressBar.setMax(100);
        button.setText("取消");
        textView.setText("0");
    }

    @Override
    protected void onPostExecute(Object o) {
        Log.i(TAG, "onPostExecute:" + Thread.currentThread().getName() + " - " + o.toString());
        super.onPostExecute(o);
        if(!isCancelled()) {
            try {
                textView.setText((CharSequence) get());
                button.setText("启动");
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        Log.i(TAG, "onProgressUpdate:" + Thread.currentThread().getName() + " - " + values[0].toString());
        super.onProgressUpdate(values);
        textView.setText(values[0].toString());
        progressBar.setProgress(Integer.parseInt(values[0].toString()));
    }

    @Override
    protected void onCancelled(Object o) {
        Log.i(TAG, "onCancelled: " + Thread.currentThread().getName() + " - " + o.toString());
        super.onCancelled(o);
        if(o != null) {
            textView.setText("任务取消");
        }
        button.setText("启动");
    }
}
