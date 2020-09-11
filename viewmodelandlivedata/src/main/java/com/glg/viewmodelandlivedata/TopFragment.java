package com.glg.viewmodelandlivedata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

/**
 * Description:
 *
 * @package: com.glg.viewmodelandlivedata
 * @className: TopFragment
 * @author: gao
 * @date: 2020/8/17 17:05
 */
public class TopFragment extends Fragment {

    private AccountModel mModel;
    private TextView mText;
    private String TAG;

    public TopFragment(String TAG) {
        this.TAG = TAG;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getLifecycle().addObserver(new StudyLifecycle(TAG));
        return inflater.inflate(R.layout.fragment_top, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mText = view.findViewById(R.id.fragment_text_view);
        mModel = ViewModelProviders.of(getActivity()).get(AccountModel.class);
        view.findViewById(R.id.fragment_set_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mModel.getAccount().postValue(new AccountBean("Change_all", "183****3101", "这段数据是从Fragment中获取出来的"));
            }
        });
        mModel.getAccount().observe(this, new Observer<AccountBean>() {
            @Override
            public void onChanged(@Nullable AccountBean accountBean) {
                mText.setText(AccountModel.getFormatContent(accountBean.getName(), accountBean.getPhone(), accountBean.getBlog()));
            }
        });
    }
}
