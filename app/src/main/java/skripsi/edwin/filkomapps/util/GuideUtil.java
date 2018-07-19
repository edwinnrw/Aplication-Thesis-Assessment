package skripsi.edwin.filkomapps.util;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;

import skripsi.edwin.filkomapps.preference.PrefUtilTutorial;


public class GuideUtil {
    public static boolean isRunningGuide=false;

    public static void isShowGuideHome(final Context context,Toolbar toolbar, ImageView btn_notif){
        final TapTargetSequence sequence = new TapTargetSequence((Activity) context);
        sequence.targets(TapTarget.forView(btn_notif,"Menu Notifikasi","Tekan menu ini untuk melihat notifikasi yang telah masuk pada aplikasi").id(2));
        sequence.targets(TapTarget.forToolbarNavigationIcon(toolbar, "Navigation Drawer", "Tekan navigation drawer, untuk melihat menu utama yang dimiliki oleh aplikasi ini ").id(1));
        if (PrefUtilTutorial.getShowCaseNotif(context)){

            sequence.start();
            sequence.listener(new TapTargetSequence.Listener() {
                @Override
                public void onSequenceFinish() {
                    PrefUtilTutorial.setShowCaseNotifikasi(context,false);
                    PrefUtilTutorial.setShowCaseNavDrawer(context,false);
                    isRunningGuide=false;
                }

                @Override
                public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {
                    PrefUtilTutorial.setShowCaseNotifikasi(context,false);
                    PrefUtilTutorial.setShowCaseNavDrawer(context,false);

                }

                @Override
                public void onSequenceCanceled(TapTarget lastTarget) {
                    PrefUtilTutorial.setShowCaseNotifikasi(context,false);
                    isRunningGuide=false;

                }
            });
        }else{
            if (PrefUtilTutorial.getShowCaseNavDrawer(context)){ ;
                sequence.startWith(2);
                sequence.listener(new TapTargetSequence.Listener() {
                    @Override
                    public void onSequenceFinish() {
                        PrefUtilTutorial.setShowCaseNavDrawer(context,false);
                        isRunningGuide=false;
                    }

                    @Override
                    public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {

                    }

                    @Override
                    public void onSequenceCanceled(TapTarget lastTarget) {
                        PrefUtilTutorial.setShowCaseNavDrawer(context,false);
                        isRunningGuide=false;

                    }
                });
            }
        }

    }
    public static void isShowGuideJadwal(final Context context, SearchView searchView, final LinearLayout btn_peran){

        if (PrefUtilTutorial.getShowCaseJadwalUjian(context)){
            PrefUtilTutorial.setShowCaseJadwalUjian(context,false);
            new GuideView.Builder(context)
                    .setContentText("Tekan bagian ini untuk memfilter jadwal ujian berdasarkan peran")
                    .setGravity(GuideView.Gravity.center)
                    .setDismissType(GuideView.DismissType.okClick) //optional - default GuideView.DismissType.targetView
                    .setTargetView(btn_peran)
                    .setContentTextSize(16)
                    .build()
                    .show();
        }
    }
    public static void  isShowGuideDetailUjian(final Context context,Button btnVerifikasi, final Button btnMenilai, int is_ketua){

        if (is_ketua==1){
            if (PrefUtilTutorial.getShowCaseVerifikasi(context) && PrefUtilTutorial.getShowCaseMenilai(context)){
                PrefUtilTutorial.setShowCaseVerifikasi(context,false);
                PrefUtilTutorial.setShowCaseMenilai(context,false);

                new GuideView.Builder(context)
                        .setContentText("Tekan tombol ini untuk memverifikasi kehadiran majelis, " +
                                "tombol ini akan aktif pada saat ujian berlangsung")
                        .setGravity(GuideView.Gravity.center)
                        .setDismissType(GuideView.DismissType.okClick) //optional - default GuideView.DismissType.targetView
                        .setTargetView(btnVerifikasi)
                        .setContentTextSize(16)
                        .setGuideListener(new GuideView.GuideListener() {
                            @Override
                            public void onDismiss(View view) {
                                new GuideView.Builder(context)
                                        .setContentText("Tekan tombol menilai untuk melakukan penilaian,khusus penguji tombol akan aktif ketika sudah mendapatkan akses penilaian")
                                        .setGravity(GuideView.Gravity.center)
                                        .setDismissType(GuideView.DismissType.okClick) //optional - default GuideView.DismissType.targetView
                                        .setTargetView(btnMenilai)
                                        .setContentTextSize(16)
                                        .build()
                                        .show();
                            }
                        })
                        .build()
                        .show();

            }else {
                if (PrefUtilTutorial.getShowCaseVerifikasi(context)){
                    PrefUtilTutorial.setShowCaseVerifikasi(context,false);
                    new GuideView.Builder(context)
                            .setContentText("Tekan tombol ini untuk memverifikasi kehadiran majelis, " +
                                    "tombol ini akan aktif pada saat ujian berlangsung")
                            .setGravity(GuideView.Gravity.center)
                            .setDismissType(GuideView.DismissType.okClick) //optional - default GuideView.DismissType.targetView
                            .setTargetView(btnVerifikasi)
                            .setContentTextSize(16)
                            .build()
                            .show();
                }
            }

        }else{

            if (PrefUtilTutorial.getShowCaseMenilai(context)){

                PrefUtilTutorial.setShowCaseMenilai(context,false);
                new GuideView.Builder(context)
                        .setContentText("Tekan tombol menilai untuk melakukan penilaian, khusus di penguji tombol ini " +
                                "akan aktif ketika kehadiran telah di verifikasi oleh ketua majelis")
                        .setGravity(GuideView.Gravity.center)
                        .setDismissType(GuideView.DismissType.okClick) //optional - default GuideView.DismissType.targetView
                        .setTargetView(btnMenilai)
                        .setContentTextSize(16)
                        .build()
                        .show();
            }
        }
    }

    public static void isShowGuideTimer(final Context context, ImageView btn){
        final TapTargetSequence sequence = new TapTargetSequence((Activity) context);
        if (PrefUtilTutorial.getShowCaseTimer(context)){
            PrefUtilTutorial.setShowCaseTimer(context,false);
            sequence.targets(TapTarget.forView(btn, "Menu Pengatur Waktu", "Tekan menu ini untuk menjalankan pengaturan waktu ujian")).start();

        }

    }
    public static void isShowMonitorGuide(final Context context, CardView cardView, final LinearLayout gap){
        if (PrefUtilTutorial.getTutorialRekapPenilain(context)){
            PrefUtilTutorial.setTutorialRekapPenilain(context,false);
            new GuideView.Builder(context)
                    .setContentText("Informasi status penilain penguji dan pembimbing,checklist menandakan penilaian sudah dilakukan")
                    .setDismissType(GuideView.DismissType.okClick)
                    .setContentTextSize(16)
                    .setGravity(GuideView.Gravity.center)
                    .setTargetView(cardView)

                    .setGuideListener(new GuideView.GuideListener() {
                        @Override
                        public void onDismiss(View view) {
                            new GuideView.Builder(context)
                                    .setContentText("Informasi GAP nilai penilaian majelis. tekan selengkapnya untuk melihat rincian GAP")
                                    .setGravity(GuideView.Gravity.center)
                                    .setContentTextSize(16)
                                    .setDismissType(GuideView.DismissType.okClick) //optional - default GuideView.DismissType.targetView
                                    .setTargetView(gap)
                                    .build()
                                    .show();
                        }
                    })
                    .build()
                    .show();
        }

    }
}
