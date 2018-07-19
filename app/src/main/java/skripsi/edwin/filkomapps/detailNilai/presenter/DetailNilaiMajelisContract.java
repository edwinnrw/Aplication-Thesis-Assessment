package skripsi.edwin.filkomapps.detailNilai.presenter;

import java.util.HashMap;
import java.util.List;

public class DetailNilaiMajelisContract {
    public interface View{

        void setNilai(List<HashMap<String, Object>> list, String namaDosen, String peran);


    }
    public  interface Presenter{
        void getNilai(int pos);
    }
}
