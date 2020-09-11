package com.glg.viewmodelandlivedata;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/**
 * Description:
 *
 * @package: com.glg.viewmodelandlivedata
 * @className: AccountModel
 * @author: gao
 * @date: 2020/8/17 16:57
 */
public class AccountModel extends AndroidViewModel {

    private MutableLiveData<AccountBean> mAccount = new MutableLiveData<>();

    public AccountModel(@NonNull Application application) {
        super(application);
    }

    public void setAccount(String name, String phone, String blog){
        mAccount.setValue(new AccountBean(name, phone, blog));
    }

    public MutableLiveData<AccountBean> getAccount(){
        return mAccount;
    }

    // 当MyActivity被销毁时，Framework会调用ViewModel的onCleared()
    @Override
    protected void onCleared() {
        Log.e("AccountModel", "==========onCleared()==========");
        super.onCleared();
    }

    public static String getFormatContent(String name, String phone, String blog) {
        StringBuilder mBuilder = new StringBuilder();
        mBuilder.append("昵称:");
        mBuilder.append(name);
        mBuilder.append("\n手机:");
        mBuilder.append(phone);
        mBuilder.append("\n博客:");
        mBuilder.append(blog);
        return mBuilder.toString();
    }
}
