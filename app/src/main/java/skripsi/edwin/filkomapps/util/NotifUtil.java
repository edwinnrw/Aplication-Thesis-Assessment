package skripsi.edwin.filkomapps.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

import skripsi.edwin.filkomapps.MainActivity;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.penilaian.view.PenilaianActivity;
import skripsi.edwin.filkomapps.service.TimerReceiver;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class NotifUtil {
    public static  void showNotifikasiWaktu(Context context,String title){
        Intent yesReceive = new Intent(context, PenilaianActivity.class);
        yesReceive.putExtra("titleTime",title);
        yesReceive.putExtra("action","stop");
        PendingIntent pendingIntentYes = PendingIntent.getBroadcast(context, 5, yesReceive, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context,"2")
                .setSmallIcon(R.drawable.logo_app)
                .setColor(context.getResources().getColor(R.color.colorOrangeFilkomNewApp))
                .setContentTitle(title)
                .setAutoCancel(true)
                .setSound(null)
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setContentIntent(pendingIntentYes);
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("2","channelalrm",NotificationManager.IMPORTANCE_DEFAULT));
        }else{
            notificationBuilder.setPriority(Notification.PRIORITY_MAX);

        }
        notificationManager.notify(0, notificationBuilder.build());

    }
    public static void showNotifFirebase(Context context,RemoteMessage remoteMessage){
        Intent intent1 = new Intent(context,MainActivity.class);
        intent1.addFlags(FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pIntent1 = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent1, 0);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context,"1")
                .setSmallIcon(R.drawable.logo_app)
                .setColor(context.getResources().getColor(R.color.colorOrangeFilkomNewApp))
                .setContentTitle("Notifikasi Baru")
                .setContentText(remoteMessage.getData().get("detail_notif"))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getData().get("detail_notif")))
                .setAutoCancel(true)
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setContentIntent(pIntent1)
                .setSound(defaultSoundUri);
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("1","channelread",NotificationManager.IMPORTANCE_HIGH));
        }
        notificationManager.notify(0, notificationBuilder.build());

    }
}
