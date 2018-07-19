package skripsi.edwin.filkomapps.summaryPenilaian.presenter;

import java.util.HashMap;
import java.util.List;

import skripsi.edwin.filkomapps.model.DataKomponen;

public class SummaryContract {
    public interface View{

        void setNilai(List<HashMap<String,Object>>list);
    }
    public  interface Presenter{
        void getNilai();
        void removeDatTemp();
    }
}
