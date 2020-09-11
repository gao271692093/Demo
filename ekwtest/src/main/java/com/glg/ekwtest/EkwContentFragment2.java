package com.glg.ekwtest;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_CANCELED;

public class EkwContentFragment2 extends Fragment {

    //private ImageView imageView;
    private File tempFile = null;
    private RelativeLayout relativeLayout;
    private Bitmap facePhoto;

    /*头像名称*/
    private static final String IMAGE_FILE_NAME = "faceImage.jpg";

    /* 请求码*/
    private static final int IMAGE_REQUEST_CODE = 0x10;
    private static final int CAMERA_REQUEST_CODE = 0x11;
    private static final int RESULT_REQUEST_CODE = 0x12;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ekw_content_fragment2, container, false);

        relativeLayout = view.findViewById(R.id.imageView);
        //mFacePhoto = getActivity().findViewById(R.id.facePhoto);
        if(this.facePhoto != null) {
            FacePhoto facePhoto = new FacePhoto(getActivity());
            facePhoto.setBitmap(this.facePhoto);
            relativeLayout.removeAllViews();
            relativeLayout.addView(facePhoto);
        }

        //relativeLayout.addView(new FacePhoto(getActivity()));
        //imageView = view.findViewById(R.id.image_true);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String items[] = {"从相册获取", "拍照"};
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(items[which].equals("拍照")) {
                            Intent intentFromCapture = new Intent(
                                    MediaStore.ACTION_IMAGE_CAPTURE);
                            // 判断存储卡是否可以用，可用进行存储
                            if (Tools.hasSdcard()) {

                                intentFromCapture.putExtra(
                                        MediaStore.EXTRA_OUTPUT,
                                        Uri.fromFile(new File(Environment
                                                .getExternalStorageDirectory(),
                                                IMAGE_FILE_NAME)));
                            }

                            if(getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                getActivity().requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
                            }

                            startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
//                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                            //判断内存卡是否可用，可用的话就进行存储
//                            //putExtra：取值，Uri.fromFile：传一个拍照所得到的文件，fileImg.jpg：文件名
//                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"fileImg.jpg")));
//                            startActivityForResult(intent,0x10); // 101: 相机的返回码参数（随便一个值就行，只要不冲突就好）
//                            //startActivity(new Intent(getActivity(), TakePhotoActivity.class));
                        }
                        if(items[which].equals("从相册获取")) {
                            Intent intentFromGallery = new Intent();
                            if (Build.VERSION.SDK_INT < 19) {
                                intentFromGallery = new Intent(
                                        Intent.ACTION_GET_CONTENT);
                                intentFromGallery.setType("image/*");
                                intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
                            } else {

                                intentFromGallery = new Intent(
                                        Intent.ACTION_PICK,
                                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                            }
                            startActivityForResult(intentFromGallery,
                                    IMAGE_REQUEST_CODE);
                            //startActivityForResult(new Intent(Intent.ACTION_PICK).setType("image/*"), 0x11);
                        }
                    }
                });
                builder.create().show();
            }
        });

        RelativeLayout relativeLayout1 = view.findViewById(R.id.relativeLayout1);
        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setIcon(R.drawable.tel);
                builder.setTitle("手机");
                builder.setMessage("您要更换手机号码吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                        final EditText editText = new EditText(getActivity());
                        editText.setHint("请输入新的手机号码：");
                        builder1.setTitle("更换手机");
                        builder1.setView(editText);
                        builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                TextView textView = getActivity().findViewById(R.id.textView_tel1);
                                textView.setText(editText.getText());
                                Toast.makeText(getActivity(), "更换手机成功！", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder1.show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
        RelativeLayout relativeLayout2 = view.findViewById(R.id.relativeLayout2);
        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        RelativeLayout relativeLayout3 = view.findViewById(R.id.relativeLayout3);
        relativeLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(getActivity());
                editText.setHint("请输入：");
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setIcon(R.drawable.advice);
                builder.setTitle("意见反馈");
                builder.setView(editText);
                builder.setCancelable(false);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "您的反馈意见已提交，我们会努力做的更好！", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
        RelativeLayout relativeLayout4 = view.findViewById(R.id.relativeLayout4);
        relativeLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("联系客服");
                builder.setMessage("需要帮您联系客服吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:400-013-0081"));
//                        startActivity(intent);
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:400-013-0081"));
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case IMAGE_REQUEST_CODE:
                    startPhotoZoom(data.getData());
                    break;
                case CAMERA_REQUEST_CODE:
                    if (Tools.hasSdcard()) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if(getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                getActivity().requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                            }
                            if(getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                getActivity().requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                            }
                        }
                        File tempFile = new File(
                                Environment.getExternalStorageDirectory() + "/"
                                        + IMAGE_FILE_NAME);
                        startPhotoZoom(Uri.fromFile(tempFile));
                    } else {
                        Toast.makeText(getActivity(), "未找到存储卡，无法存储照片！",
                                Toast.LENGTH_LONG).show();
                    }

                    break;
                case RESULT_REQUEST_CODE:
                    if (data != null) {
                        getImageToView(data);
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode != getActivity().RESULT_CANCELED) {
//            switch (requestCode){
//                case 0x11:   //相册返回的数据（相册的返回码）
//                    Uri uri01 = data.getData();
//                    try {
//                        Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri01));
//                        imageView.setImageBitmap(bitmap);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    break;
//                case 0x10:  //相机返回的数据（相机的返回码）
//                    try {
//                        tempFile = new File(Environment.getExternalStorageDirectory(),"fileImg.jpg");  //相机取图片数据文件
//                        Uri uri02 = Uri.fromFile(tempFile);   //图片文件
//                        Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri02));
//                        imageView.setImageBitmap(bitmap);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
    }

    public void startPhotoZoom(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        //获取屏幕信息
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int screenWidth = displayMetrics.widthPixels;
//        int screenHeight = displayMetrics.heightPixels;

        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 140);
        intent.putExtra("outputY", 140);
        intent.putExtra("return-data", true);

        //解决图片无法加载的问题
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }

    private void getImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
//            Drawable drawable = new BitmapDrawable(photo);
//            imageView.setImageDrawable(drawable);
            FacePhoto facePhoto = new FacePhoto(getActivity());
            facePhoto.setBitmap(photo);
            this.facePhoto = photo;
            relativeLayout.removeAllViews();
            //relativeLayout.setBackgroundColor(0x00000000);
            relativeLayout.addView(facePhoto);
        }
    }
}
