package skripsi.edwin.filkomapps.ketuaMonitor.presenter;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import skripsi.edwin.filkomapps.helper.PenilaianHelper;
import skripsi.edwin.filkomapps.model.DetailGAP;
import skripsi.edwin.filkomapps.model.GAP;
import skripsi.edwin.filkomapps.model.orm.KomponenIndikatorName;
import skripsi.edwin.filkomapps.model.orm.KomponenWithIndikator;
import skripsi.edwin.filkomapps.model.orm.Majelis;
import skripsi.edwin.filkomapps.model.orm.Nilai;
import skripsi.edwin.filkomapps.model.orm.NilaiDosenName;

public class GapPresenter implements GapContract.Presenter {
    Context context;
    GapContract.View view;

    public GapPresenter(Context context, GapContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getDetailGap(List<GAP> gapList) {
        List<DetailGAP>detailGAPList=new ArrayList<>();
        if (gapList.size()>0){
            for (int i=0; i<gapList.size(); i++){
                DetailGAP detailGAP=new DetailGAP();
                KomponenIndikatorName komponen=PenilaianHelper.getKomponenIndikator
                        (context,gapList.get(i).getIdIndikator());
                detailGAP.setNamaKomponen(komponen.namaKomponen);
                detailGAP.setNamaIndikator(komponen.namaIndikator);
                List<NilaiDosenName>nilaiList=PenilaianHelper.getNilaiDosenName(context,gapList.get(i).getIdIndikator());
                List<HashMap<String,String>>nilaiDetail=new ArrayList<>();
                for (int j=0; j<nilaiList.size(); j++){
                    HashMap<String,String> nilai=new HashMap<>();
                    nilai.put("nilai",nilaiList.get(j).nilai);
                    nilai.put("nama",nilaiList.get(j).namaDosen);
                    nilaiDetail.add(nilai);
                    detailGAP.setNilai(nilaiDetail);
                }
                detailGAPList.add(detailGAP);
                view.setGap(detailGAPList);

            }
        }

    }
}
