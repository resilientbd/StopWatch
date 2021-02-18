package com.faisal.stopwatch;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.faisal.basemodule.StopWatch;

public class MainActivityViewModel extends AndroidViewModel implements StopWatch.OnUpdateTimer {
    private StopWatch stopWatch;
    public MutableLiveData<String> timeText = new MutableLiveData<>();
    public MutableLiveData<String> buttonText = new MutableLiveData<>();
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
       if(stopWatch==null)
       {
           stopWatch = new StopWatch(this);
       }
    }
    void start()
    {
        try{
            stopWatch.startTimer();
        }catch (Exception e)
        {
            stopWatch = new StopWatch(this::onUpdate);
            stopWatch.startTimer();
        }

    }
    void stop()
    {
        try {
            stopWatch.stopTimer();
        }catch (Exception e)
        {
            stopWatch = new StopWatch(this::onUpdate);
            stopWatch.stopTimer();
        }
    }

    @Override
    public void onUpdate(String time) {
        timeText.postValue(time);
    }
}
