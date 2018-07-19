package skripsi.edwin.filkomapps.beritaAcaraResechedule.presenter;

public class BeritaAcaraContract {
    public interface View{
        void showProgress();
        void dismisProgress();
        void submitFail(String message);
        void submitSuccess();
        void validationFieldError();
        void validationFieldSuccess();

    }
    public  interface Presenter{
        void validationField(String alasan);
        void submitBeritaAcara(String alasan,String catatan);
    }
}
