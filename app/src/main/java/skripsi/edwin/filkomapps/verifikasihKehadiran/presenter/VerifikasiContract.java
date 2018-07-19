package skripsi.edwin.filkomapps.verifikasihKehadiran.presenter;

import java.util.HashMap;
import java.util.List;


/**
 * Created by EDN on 1/27/2018.
 */

public class VerifikasiContract {
    public  interface  View{
        void validateSuccess(List<HashMap<String, String>> kehadiran);
        void showWaitingConfirm();
        void validateFailed(String message);
        void showVerifikasiFailed(String message);
        void showVerifikasiSuccess();
        void showProgress();
        void dismissProgress();

    }
    public interface Presenter {
        void validateIsChecked(int mahasiswa,int pembimbing,int pembimbing2,
                               int penguji1,int penguji2);
        void verifikasiProcess();
        void saveDosen();
        void setKehadiranDosenPembimbing1(String isHadir);
        void setKehadiranDosenPembimbing2(String isHadir);
        void setKehadiranDosenPenguji1(String isHadir);
        void setKehadiranDosenPenguji2(String isHadir, boolean isUpdate);
        void setNamaDosenPengujiPengganti(String nama,String id);

    }
}
