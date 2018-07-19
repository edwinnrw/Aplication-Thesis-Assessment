package skripsi.edwin.filkomapps.penilaian.view;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.penilaian.presenter.TimerContract;
import skripsi.edwin.filkomapps.penilaian.presenter.TimerPresenter;
import skripsi.edwin.filkomapps.service.TimerReceiver;
import skripsi.edwin.filkomapps.util.NotifUtil;
import skripsi.edwin.filkomapps.util.TimeUtil;

public class TimerFragment extends BottomSheetDialogFragment implements TimerContract.View {


    @BindView(R.id.play_presentasi)
    ImageView play_timer_presentas;
    @BindView(R.id.stop_presentasi)
    ImageView stop_presentasi;
    @BindView(R.id.pause_presentasi)
    ImageView pause_presentasi;
    @BindView(R.id.play_tanya_jawab)
    ImageView play_tanya_jawab;
    @BindView(R.id.stop_tanya_jawab)
    ImageView stop_tanya_jawab;
    @BindView(R.id.pause_tanya_jawab)
    ImageView pause_tanya_jawab;

    @BindView(R.id.countDownPresentasi)
    CountdownView countdownView;
    @BindView(R.id.countDownTanyaJawab)
    CountdownView countdownTanyaJawab;
    TimerPresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_timer, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter=new TimerPresenter(this,getActivity());
        presenter.isStartTime();

    }

    @OnClick(R.id.play_presentasi)
    public void onClickPResentasi(){
        presenter.onStartTimer("presentasi");
    }
    @OnClick(R.id.play_tanya_jawab)
    public void onClickTanyaJawab(){
        presenter.onStartTimer("tanya jawab");
    }
    @OnClick(R.id.pause_presentasi)
    public void  onClickPausePresentasi(){
        play_timer_presentas.setVisibility(View.VISIBLE);
        pause_presentasi.setVisibility(View.GONE);
        countdownView.pause();
        presenter.setTimeMilisLeft(countdownView.getRemainTime());
        presenter.onPauseTimer();

    }
    @OnClick(R.id.stop_presentasi)
    public void  onClickStopPresentasi(){
        stop_presentasi.setVisibility(View.GONE);
        pause_presentasi.setVisibility(View.GONE);
        countdownView.stop();
        presenter.onStopTimer("presentasi");

    }
    @OnClick(R.id.pause_tanya_jawab)
    public void onClickPauseTanyaJawab(){
        play_tanya_jawab.setVisibility(View.VISIBLE);
        pause_tanya_jawab.setVisibility(View.GONE);
        countdownTanyaJawab.pause();
        presenter.setTimeMilisLeft(countdownTanyaJawab.getRemainTime());
        presenter.onPauseTimer();
    }

    @OnClick(R.id.stop_tanya_jawab)
    public void  onClickStopTanyaJawab(){
        stop_tanya_jawab.setVisibility(View.GONE);
        pause_tanya_jawab.setVisibility(View.GONE);
        countdownTanyaJawab.stop();
        presenter.onStopTimer("tanya_jawab");

    }

    @OnClick(R.id.lnTutup)
    public void onClickTutup(){
        dismiss();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStartBackground();
        if (countdownView != null) {
            countdownView.pause();
        }
        if (countdownTanyaJawab !=null){
            countdownTanyaJawab.pause();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.isStartTime();

    }

    @Override
    public void showTimePresentasi() {
        play_tanya_jawab.setVisibility(View.GONE);
        play_timer_presentas.setVisibility(View.GONE);
        stop_tanya_jawab.setVisibility(View.GONE);

        pause_presentasi.setVisibility(View.VISIBLE);
        stop_presentasi.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideTimePresentasi() {
        play_timer_presentas.setVisibility(View.VISIBLE);
        play_tanya_jawab.setVisibility(View.VISIBLE);
        pause_presentasi.setVisibility(View.GONE);
        stop_presentasi.setVisibility(View.GONE);

    }

    @Override
    public void showTextTimeTanyaJawab() {
        play_timer_presentas.setVisibility(View.GONE);
        play_tanya_jawab.setVisibility(View.GONE);
        stop_presentasi.setVisibility(View.GONE);

        pause_tanya_jawab.setVisibility(View.VISIBLE);
        stop_tanya_jawab.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideTexTimeTanyaJawab() {
        play_timer_presentas.setVisibility(View.VISIBLE);
        play_tanya_jawab.setVisibility(View.VISIBLE);
        pause_tanya_jawab.setVisibility(View.GONE);
        stop_tanya_jawab.setVisibility(View.GONE);
    }

    @Override
    public void startTimePresentasi(final long mTimeMilis) {
        countdownView.start(mTimeMilis);
        showTimePresentasi();
        countdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                hideTimePresentasi();
                presenter.onFinishTimer("presentasi");
            }
        });
    }


    @Override
    public void startTimeTanyaJawab(final long mTimeMilis) {
        countdownTanyaJawab.start(mTimeMilis);
        showTextTimeTanyaJawab();
        countdownTanyaJawab.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                hideTexTimeTanyaJawab();
                presenter.onFinishTimer("tanya_jawab");
            }
        });
    }

    @Override
    public void showLastTimerPresentasi(final long mTimeMilis) {
        play_timer_presentas.setVisibility(View.VISIBLE);
        play_tanya_jawab.setVisibility(View.GONE);
        stop_presentasi.setVisibility(View.VISIBLE);
        pause_presentasi.setVisibility(View.GONE);
        pause_tanya_jawab.setVisibility(View.GONE);
        stop_tanya_jawab.setVisibility(View.GONE);
        final Handler h = new Handler(Looper.getMainLooper());
        countdownView.start(mTimeMilis);
        h.postDelayed((new Runnable() {
            @Override
            public void run () {
                countdownView.pause();
            }}), 1000);

    }

    @Override
    public void showLastTimerTanyaJawab(long mTimeMilis) {
        play_tanya_jawab.setVisibility(View.VISIBLE);
        play_timer_presentas.setVisibility(View.GONE);
        stop_tanya_jawab.setVisibility(View.VISIBLE);
        pause_tanya_jawab.setVisibility(View.GONE);
        pause_presentasi.setVisibility(View.GONE);
        stop_presentasi.setVisibility(View.GONE);
        final Handler h = new Handler(Looper.getMainLooper());
        countdownTanyaJawab.start(mTimeMilis);
        h.postDelayed((new Runnable() {
            @Override
            public void run () {
                countdownTanyaJawab.pause();
            }}),1000);
    }
}
