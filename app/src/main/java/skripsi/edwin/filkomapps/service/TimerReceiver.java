package skripsi.edwin.filkomapps.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.penilaian.view.PenilaianActivity;
import skripsi.edwin.filkomapps.util.NotifUtil;

public class TimerReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("timeraaa",intent.getStringExtra("action"));
        if (intent.getStringExtra("action").equalsIgnoreCase("finish")){
            NotifUtil.showNotifikasiWaktu(context,"Waktu "+intent.getStringExtra("titleTime")+" telah habis");

        }

    }


}
