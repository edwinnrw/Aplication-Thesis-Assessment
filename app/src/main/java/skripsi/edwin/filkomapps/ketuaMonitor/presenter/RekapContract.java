package skripsi.edwin.filkomapps.ketuaMonitor.presenter;

import java.util.List;

import skripsi.edwin.filkomapps.model.GAP;


/**
 * Created by EDN on 3/9/2018.
 */

public class RekapContract {
    public interface  View{
        void showFailMessage(String message);
        void submitSuccess();
        void showProgress();
        void dismisProgress();
        void setGap(List<GAP> totalGap);
        void validationFalse(String message);
        void validationSuccess();
        void showBadge();
        void hideBadge();
        void setRataRataPembimbing(String avgAll, String format);
        void setRataRataPenguji(String avgAll, String format);
        void setNilaiPenulisanPembimbing(String avgAll, String format);
        void setNilaiPenulisanPenguji(String avgAll, String format);
        void setNilaiPublikasi(String avgAll, String format);
        void setSemhas(String nilai,String subtotal);
        void setNilaiAkhirUjian(String nilai);
        void setCheckPembimbing1(boolean isCheck);
        void setCheckPembimbing2(boolean isCheck);
        void setCheckPenguji1(boolean isCheck);
        void setCheckPenguji2(boolean isCheck);
        void setDropdown(String[] list);
        void setTimeUpdate(String update);

    }
    public interface Presenter{
        void checkGap();
        void getRekap();
        void getIsNilaiMajelis();
        void buildDropdown();
        void validation(int s);
        void submitFinal(String catatan, String predikat,String na);
        void isNotif();
        void saveUpdate();
        void getTimeUpdate();
        void getNilaiAkhirSkripsi(double semhas, double np, double nuPembimbing, double v);
    }
}
