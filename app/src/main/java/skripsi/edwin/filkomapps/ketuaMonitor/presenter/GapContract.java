package skripsi.edwin.filkomapps.ketuaMonitor.presenter;

import java.util.List;

import skripsi.edwin.filkomapps.model.DetailGAP;
import skripsi.edwin.filkomapps.model.GAP;

public class GapContract {
    public interface View{
        void setGap(List<DetailGAP>detailGAPS);
    }
    public interface Presenter{
        void getDetailGap(List<GAP>gapList);

    }
}
