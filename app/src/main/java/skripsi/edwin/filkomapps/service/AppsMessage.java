package skripsi.edwin.filkomapps.service;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

import skripsi.edwin.filkomapps.helper.ReceiveHelper;
import skripsi.edwin.filkomapps.preference.PrefUtil;
import skripsi.edwin.filkomapps.util.NotifUtil;
import skripsi.edwin.filkomapps.util.TimeUtil;

public class AppsMessage extends FirebaseMessagingService {
    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        switch (remoteMessage.getData().get("jenis_notif")){
            case "1":
                PrefUtil.setHaveNewNotif(getApplicationContext(),true);
                ReceiveHelper.insertNotifikasi(remoteMessage,getApplicationContext());
                EventBus.getDefault().post("verifikasi");
                PrefUtil.setIsDoneVerifikasi(getApplicationContext(),true);
                PrefUtil.setAccessPenilaian(getApplicationContext(),remoteMessage.getData().get("id_ujian"),true);
                NotifUtil.showNotifFirebase(getApplicationContext(),remoteMessage);
                break;
            case "2":
                if (PrefUtil.getOnceNilai(getApplicationContext())){
                    PrefUtil.setHaveNewNotif(getApplicationContext(),true);
                    ReceiveHelper.insertNotifikasi(remoteMessage,getApplicationContext());
                    PrefUtil.setTimeUpdate(getApplicationContext(), TimeUtil.getCurrentTime());
                    ReceiveHelper.insertNilaiTemp(getApplicationContext(),remoteMessage.getData().get("nilai_ujian"));
                    if (PrefUtil.getIsSubmitNilai(getApplicationContext(),PrefUtil.getIdUjianTemp(getApplicationContext()))){
                        EventBus.getDefault().post("insert");
                        NotifUtil.showNotifFirebase(getApplicationContext(),remoteMessage);
                    }
                }
                break;
            case "3":
                ReceiveHelper.updateNilai(getApplicationContext(),remoteMessage.getData().get("nilai_update"));
                PrefUtil.setHaveNewNotif(getApplicationContext(),true);
                if (PrefUtil.getIsSubmitNilai(getApplicationContext(),PrefUtil.getIdUjianTemp(getApplicationContext()))){
                    EventBus.getDefault().post("update");
                    NotifUtil.showNotifFirebase(getApplicationContext(),remoteMessage);
                }
                break;
            case "4":
                PrefUtil.setHaveNewNotif(getApplicationContext(),true);
                ReceiveHelper.finishUjian(getApplicationContext());
                ReceiveHelper.insertNotifikasi(remoteMessage,getApplicationContext());
                EventBus.getDefault().post("Selesai");
                NotifUtil.showNotifFirebase(getApplicationContext(),remoteMessage);
                break;
        }

    }

}
