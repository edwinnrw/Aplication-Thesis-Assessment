package skripsi.edwin.filkomapps.detailUjian.presenter;

import java.util.List;

import skripsi.edwin.filkomapps.model.BeritaAcara;
import skripsi.edwin.filkomapps.model.DataUjian;
import skripsi.edwin.filkomapps.model.Mahasiswa;
import skripsi.edwin.filkomapps.model.Pembimbing;
import skripsi.edwin.filkomapps.model.Penguji;
import skripsi.edwin.filkomapps.model.Skripsi;


/**
 * Created by EDN on 3/6/2018.
 */

public class DetailJadwalUjianContract {
    public interface View{
        void showDataUjian(DataUjian dataUjian);
        void showBtnKehadiran();
        void showMessage(String message);
        void showProgress();
        void dismissProgress();
        void enableMenilai();
        void enableBtnKehadiran(boolean enable);
        void goToPenilaian();
        void goToSummary();
        void goToMonitor();
        void showBeritaAcara(BeritaAcara beritaAcara);

    }
    public  interface Presenter{
        void getDetailJadwal(String id);
        void isShowBtnKehadiran(int isKetua);
        void isEnableBtnMenilai(int isPenguji,int status);
        void isEnableBtnKehadiran(long tanggal, int status);
        void checkIsNilai();
        void savePeran(String isKetua,String isPenguji);
    }
}
