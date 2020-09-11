package com.glg.utiltest.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.glg.utiltest.R;
import com.glg.utiltest.util.FileUtil;

import java.io.File;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        findViewById(R.id.get_sd_path).setOnClickListener(this);
        findViewById(R.id.get_file_suffix).setOnClickListener(this);
        findViewById(R.id.get_file_mime).setOnClickListener(this);
        findViewById(R.id.create_file).setOnClickListener(this);
        findViewById(R.id.create_dir).setOnClickListener(this);
        findViewById(R.id.delete_specific_file).setOnClickListener(this);
        findViewById(R.id.delete_all_for_dir).setOnClickListener(this);
        findViewById(R.id.copy_file).setOnClickListener(this);
        findViewById(R.id.copy_dir).setOnClickListener(this);
        findViewById(R.id.rename_file).setOnClickListener(this);
        findViewById(R.id.format_file_size).setOnClickListener(this);
        findViewById(R.id.format_dir_size).setOnClickListener(this);
        findViewById(R.id.get_file_list).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_sd_path:
                //eg."getSdPath:/storage/emulated/0"
                //获取sd路径
                SecondActivityPermissionsDispatcher.getSdPathWithPermissionCheck(SecondActivity.this);
                break;
            case R.id.get_file_suffix:
                //读取文件后缀
                String fileName = "log.txt";
                String fileSuffix = FileUtil.getFileSuffix(fileName);
                Log.i(TAG, "onClick: fileSuffix::" + fileSuffix);
                break;
            case R.id.get_file_mime:
                //获取文件MIME类型
                String filePath1 = FileUtil.getSDPath() + "/00/" + "1.doc";
                String mimeType = FileUtil.getMIMEType(filePath1);
                Log.i(TAG, "onClick: mimeType::" + mimeType);
                break;
            case R.id.create_file:
                //创建文件
                String dirPath2 = FileUtil.getSDPath() + "/00";
                String fileName2 = "2.doc";
                boolean isCreated = FileUtil.createFile(dirPath2, fileName2);
                Log.i(TAG, "onClick: file isCreated::" + isCreated);
                break;
            case R.id.create_dir:
                //创建目录
                String dirPath3 = FileUtil.getSDPath()+"/00";
                //android10只能操作自己对应的数据文件夹
                //getExternalFilesDir("00");///storage/emulated/0/Android/data/com.glg.utiltest/files/00
                boolean isCreated3 = FileUtil.createFolder(dirPath3);
                Log.i(TAG, "onClick: dir isCreated::" + isCreated3);
                break;
            case R.id.delete_specific_file:
                //删除指定的某个文件
                String filePath4 = FileUtil.getSDPath() + "/00/4.doc";
                boolean isDeleted = FileUtil.deleteFile(filePath4);
                Log.i(TAG, "onClick: file isDeleted::" + isDeleted);
                break;
            case R.id.delete_all_for_dir:
                //删除某一目录下的所有文件
                String dirPath5 = FileUtil.getSDPath() + "/00";
                boolean isDeleted5 = FileUtil.deleteFolder(new File(dirPath5));
                Log.i(TAG, "onClick: all sub file isDeleted(include current dir)::" + isDeleted5);
                break;
            case R.id.copy_file:
                //复制文件
                String filePath6 = FileUtil.getSDPath() + "/00/6.wav";
                String dirPath6 = FileUtil.getSDPath() + "/11";
                boolean isCopyed = FileUtil.copyFile(filePath6, dirPath6);
                Log.i(TAG, "onClick: file isCopyed::" + isCopyed);
                break;
            case R.id.copy_dir://v
                //复制目录
                String srcDir7 = FileUtil.getSDPath() + "/00";
                String destDir7 = FileUtil.getSDPath() + "/11";
                boolean isCopyed7 = FileUtil.copyFolder(srcDir7, destDir7);
                Log.i(TAG, "onClick: file isCopyed::" + isCopyed7);
                break;
            case R.id.rename_file:
                //文件重命名
                String srcPath8 = FileUtil.getSDPath() + "/00/8.wav";
                String destPath8 = FileUtil.getSDPath() + "/00/8_1.wav";
                boolean b = FileUtil.renameTo(srcPath8, destPath8);

                //filename="7_1.wav";
                //boolean b = FileUtil.renameTo(new File(srcPath8), destPath8);
                Log.i(TAG, "onClick: file rename::" + b);
                break;
            case R.id.format_file_size://593.71kb---->579.79
                //格式化文件大小
                String filePath9 = FileUtil.getSDPath() + "/00/9.wav";
                long fileSize = FileUtil.getFileSize(filePath9);
                Log.i(TAG, "onClick: get fileSize::" + fileSize);
                if (fileSize != -1) {
                    String formatSize = FileUtil.formatSize(fileSize);
                    Log.i(TAG, "onClick:formatSize file::" + formatSize);
                }
                break;
            case R.id.format_dir_size://4.95--->4.72M
                //格式化目录大小
                String dir10 = FileUtil.getSDPath() + "/11";
                long folderSize = FileUtil.getFolderSize(new File(dir10));
                if (folderSize != -1) {
                    String formatSize10 = FileUtil.formatSize(folderSize);
                    Log.i(TAG, "onClick: formatSize dir::" + formatSize10);
                }
                break;
            case R.id.get_file_list:
                //获取当前目录的文件列表
                String dir11 = FileUtil.getSDPath() + "/11";
                File[] fileList = FileUtil.getFileList(dir11);
                Log.i(TAG, "onClick: get file list size::" + fileList.length + "  fileList::" + fileList);
                break;
        }
    }


    //*****************************封装的文件操作方法***********************************************
    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void getSdPath() {
        String sdPath = FileUtil.getSDPath();
        Log.d(TAG, "getSdPath:"+sdPath);
    }

    //****************************************************************************

    //首次权限弹出是系统权限。当点击拒绝之后才会走此
    // 向用户说明为什么需要这些权限（可选）
    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showRationaleForCamera(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("文件操作需要读写权限，请求授予")
                .setPositiveButton("下一步", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int button) {
                        request.proceed();//继续请求权限
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int button) {
                        request.cancel();
                    }
                })
                .show();
    }

    // 用户拒绝授权回调（可选）//当点击系统权限的拒绝时候，不会触发
    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showDeniedForCamera() {
        Toast.makeText(this, "读写权限被拒绝。", Toast.LENGTH_SHORT).show();
    }

    // 用户勾选了“不再提醒”时调用（可选）
    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showNeverAskForCamera() {
        openAppDetails();
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 代理权限处理到自动生成的方法
        //必须手动添加，不然下一步不走。。
        SecondActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    /**
     * 打开 APP 的详情设置
     */
    private void openAppDetails() {
        Toast.makeText(this, "勾选了不在拒绝。", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("操作文件读写权限，请到'设置'中授予权限．");
        builder.setPositiveButton("去手动授权", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

}