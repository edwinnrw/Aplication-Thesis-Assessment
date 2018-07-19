package skripsi.edwin.filkomapps.penilaian.presenter;

import java.util.List;

import skripsi.edwin.filkomapps.model.DataKomponen;

public class PenilaianContract
{
    public interface View{
        void showMessageError(String message);
        void showProgress();
        void dismissProgress();
        void setListKomponen(List<DataKomponen>dataKomponens);
        void showSubmitError(String message);
        void submitSuccess();
        void validationError();
        void validationSuccess();
        void goToMonitor();
        void goToSummary();
        void showMenuTimer();
    }
    public  interface Presenter{
        void checkAkses();
        void validationIsComplete(List<DataKomponen> dataKomponenList);
        void submitNilai(String isKetua);
        void checkIsKetua();
        void isShowMenuTimer();
        boolean checkIsDone(String komponen);
    }
}
