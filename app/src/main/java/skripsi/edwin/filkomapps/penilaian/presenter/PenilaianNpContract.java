package skripsi.edwin.filkomapps.penilaian.presenter;

import android.widget.EditText;

import java.util.HashMap;

import skripsi.edwin.filkomapps.model.DataKomponen;

public class PenilaianNpContract {
    public interface View{
        void setIndikator(String id, String s, String indikator);
        void validationError(EditText editText);
        void validationComplete();
    }
    public interface Presenter{
        void buildIndikatorPenilaian(DataKomponen indikators);
        void validationIsComplete(DataKomponen dataKomponen,HashMap<String,EditText>editTexts);
        void saveNilai(DataKomponen dataKomponen, HashMap<String, EditText> editTexts);
        String getNilaiTemp(String indikator);
    }
}
