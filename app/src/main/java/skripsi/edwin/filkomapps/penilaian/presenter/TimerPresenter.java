package skripsi.edwin.filkomapps.penilaian.presenter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import skripsi.edwin.filkomapps.preference.TimerPrefUtil;
import skripsi.edwin.filkomapps.service.TimerReceiver;
import skripsi.edwin.filkomapps.util.TimeUtil;

public class TimerPresenter implements TimerContract.Presenter {
    TimerContract.View view;
    Context context;
    public boolean mTimerRunning;
    private long mTimeLeftInMillis;
    private long mEndTime;
    public  long START_TIME_IN_MILLIS=600000; //10 menit
    public  long START_TIME_IN_MILLIS_TH=900000; //15 menit

    AlarmManager manager;
    PendingIntent pendingIntent;
    public boolean onPause=false;
    public TimerPresenter(TimerContract.View view, Context context) {
        this.view = view;
        this.context = context;
        manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);//get instance of alarm manager

    }

    @Override
    public void isStartTime() {
        ///untuk mengecek timer apakah sedang berjalan
        mTimerRunning = TimerPrefUtil.getTimerIsRunning(context);
        mTimeLeftInMillis=TimerPrefUtil.getMilisLeft(context,START_TIME_IN_MILLIS);
        onPause=TimerPrefUtil.getTimerIsPause(context);
        if (mTimerRunning) {
            if (onPause){
                if (TimerPrefUtil.getRunningTitle(context).equalsIgnoreCase("presentasi")){
                    view.showLastTimerPresentasi(mTimeLeftInMillis);

                }else{
                    view.showLastTimerTanyaJawab(mTimeLeftInMillis);
                }
            }else{
                mEndTime = TimerPrefUtil.getEndTime(context);
                mTimeLeftInMillis = mEndTime - System.currentTimeMillis();

                if (mTimeLeftInMillis < 0) {
                    mTimeLeftInMillis = 0;
                    mTimerRunning = false;

                } else {
                    if (TimerPrefUtil.getRunningTitle(context).equalsIgnoreCase("presentasi")){
                        view.startTimePresentasi(mTimeLeftInMillis);
                    }else{
                        view.startTimeTanyaJawab(mTimeLeftInMillis);
                    }
                }
            }


        }

    }
    @Override
    public void onStartTimer(String title) {
        //memulai timer
        int minutes=0;
        if (title.equalsIgnoreCase("presentasi")){
            if (TimerPrefUtil.getTimerIsPause(context)){
                minutes = (int) ((mTimeLeftInMillis / (1000*60)) % 60);

            }else{
                mTimeLeftInMillis=START_TIME_IN_MILLIS;
                minutes = (int) ((mTimeLeftInMillis / (1000*60)) % 60);

            }

        }else{
            if (TimerPrefUtil.getTimerIsPause(context)){
                minutes = (int) ((mTimeLeftInMillis / (1000*60)) % 60);
            }else{
                mTimeLeftInMillis=START_TIME_IN_MILLIS_TH;
                minutes = (int) ((mTimeLeftInMillis / (1000*60)) % 60);

            }

        }
        mTimerRunning=true;
        onPause=false;
        Calendar end = Calendar.getInstance();
        end.add(Calendar.MINUTE, minutes);
        end.add(Calendar.SECOND,0);
        Intent alarmIntent = new Intent(context, TimerReceiver.class);
        alarmIntent.putExtra("titleTime",title);
        alarmIntent.putExtra("action","finish");

        pendingIntent = PendingIntent.getBroadcast(context, 1, alarmIntent, 0);

        manager.set(AlarmManager.RTC_WAKEUP, end.getTimeInMillis(), pendingIntent);

        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        TimerPrefUtil.setTimerIsRuniing(context,true);
        TimerPrefUtil.setRunningTitle(context,title);
        if (title.equalsIgnoreCase("presentasi")){
            view.startTimePresentasi(mTimeLeftInMillis);
        }else {
            view.startTimeTanyaJawab(mTimeLeftInMillis);
        }
    }

    @Override
    public void onStartBackground() {
        //menyimpan timer jika aplikasi ditutup
        TimerPrefUtil.setMilisLeft(context,mTimeLeftInMillis);
        TimerPrefUtil.setEndTime(context,mEndTime);
        TimerPrefUtil.setTimerIsRuniing(context,mTimerRunning);
        TimerPrefUtil.setTimerIsPause(context,onPause);

    }

    @Override
    public void onFinishTimer(String title) {
        //dijalankan ketika timer telah selesai, dengan menghancurkan preference,yang digunakan untuk menyimpan data sementara
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        TimerPrefUtil.setTimerIsRuniing(context,false);
        TimerPrefUtil.destroyRunningTitle(context,title);
        TimerPrefUtil.destroyTimeLeftMilis(context);
        if (title.equalsIgnoreCase("presentasi")){
            view.hideTimePresentasi();

        }else{
            view.hideTexTimeTanyaJawab();
        }
    }

    @Override
    public void onPauseTimer() {
        //menyimpan kondisi jika timer

        onPause=true;
        TimerPrefUtil.setTimerIsPause(context,onPause);
    }

    @Override
    public void onStopTimer(String title) {
        //memberhentikan timer,dengan mengembalikan timelefinmilis dan timerruning ke kondis awal
        Intent alarmIntent = new Intent(context, TimerReceiver.class);
        alarmIntent.putExtra("action","stop");
        alarmIntent.putExtra("titleTime",TimerPrefUtil.getRunningTitle(context));
        pendingIntent = PendingIntent.getBroadcast(context, 1, alarmIntent, 0);
        manager.cancel(pendingIntent);
        mTimerRunning=false;
        TimerPrefUtil.setTimerIsPause(context,false);
        if (title.equalsIgnoreCase("presentasi")){
            mTimeLeftInMillis = START_TIME_IN_MILLIS;
            view.hideTimePresentasi();

        }else {
            mTimeLeftInMillis=START_TIME_IN_MILLIS_TH;
            view.hideTexTimeTanyaJawab();
        }
    }


    @Override
    public void setTimeMilisLeft(long time) {
        mTimeLeftInMillis=time;
    }

    public long getmTimeLeftInMillis() {
        return mTimeLeftInMillis;
    }


}
