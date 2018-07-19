package skripsi.edwin.filkomapps.summaryPenilaian.presenter;

import skripsi.edwin.filkomapps.model.orm.NilaiSaya;

public class EditPublikasiContract {
    public interface View{
        void setIndikator(String id,String indikator,String ket,String nilai);
        void validationComplete();
        void validationError();
        void showProgress();
        void dismissProgress();
        void showUpdateFail(String message);
        void showUpdateSuccess(String nilai);
    }
    public interface Presenter{
        void getNilaiIndikator(NilaiSaya nilaiSaya);
        void validationIsComplete(String input);
        void saveNilai(NilaiSaya indikator, String editTexts);
    }
}
