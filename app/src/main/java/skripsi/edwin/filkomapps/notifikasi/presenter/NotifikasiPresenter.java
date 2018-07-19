package skripsi.edwin.filkomapps.notifikasi.presenter;

import android.content.Context;

import java.util.List;

import skripsi.edwin.filkomapps.helper.NotifHelper;
import skripsi.edwin.filkomapps.helper.ReceiveHelper;
import skripsi.edwin.filkomapps.model.orm.Notifikasi;
import skripsi.edwin.filkomapps.preference.PrefUtil;

public class NotifikasiPresenter implements NotifikasiContract.Presenter {
    Context context;
    NotifikasiContract.View view;

    public NotifikasiPresenter(Context context, NotifikasiContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getNotif() {
        if (PrefUtil.haveNewNotif(context)){
            PrefUtil.setHaveNewNotif(context,false);
        }
        List<Notifikasi> notifikasi= NotifHelper.getNotifikasi(context);
        view.setNotifikasi(notifikasi);

    }
}
