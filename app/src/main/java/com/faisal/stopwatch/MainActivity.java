package com.faisal.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.faisal.basemodule.base.BaseActivity;
import com.faisal.stopwatch.databinding.ActivityMainBinding;


public class MainActivity extends BaseActivity {
    ActivityMainBinding mBinding;
    MainActivityViewModel viewModel;

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void startUI() {

        mBinding = (ActivityMainBinding) getViewDataBinding();
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mBinding.setLifecycleOwner(this);
        mBinding.setViewmodel(viewModel);
        if(viewModel.timeText.getValue()==null)
        {
            viewModel.timeText.postValue("00:00:00");
        }
        if(viewModel.buttonText.getValue()==null)
        {
            viewModel.buttonText.postValue("Start");
        }
//        viewModel.timeText.observeForever(s -> {
//            Log.d("chk","value:"+s);
//           // mBinding.textDisplay.setText(s);
//        });
        mBinding.btnControll.setOnClickListener(v -> {
            if (viewModel.buttonText.getValue().toLowerCase().equals("start")) {
                viewModel.start();
                viewModel.buttonText.postValue("stop");
            } else {
                viewModel.stop();
                viewModel.buttonText.postValue("start");
            }
        });
    }
}