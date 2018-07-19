package skripsi.edwin.filkomapps.ketuaMonitor.presenter;

import java.util.List;

import skripsi.edwin.filkomapps.model.DetailGAP;
import skripsi.edwin.filkomapps.model.GAP;

public class KetuaMonitorContract {
    public interface View{
        void showProgress();
        void dismisProgress();
        void getNilaiFail(String message);
    }
    public interface Presenter{
       void checkGetNilai();
    }
}
