package skripsi.edwin.filkomapps.summaryPenilaian.presenter;

import android.content.Context;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import skripsi.edwin.filkomapps.helper.MajelisHelper;
import skripsi.edwin.filkomapps.helper.PenilaianHelper;
import skripsi.edwin.filkomapps.helper.UserHelper;
import skripsi.edwin.filkomapps.model.orm.NilaiSaya;
import skripsi.edwin.filkomapps.model.orm.User;
import skripsi.edwin.filkomapps.preference.PrefUtil;

public class SummaryPresenter implements SummaryContract.Presenter {
    Context context;
    SummaryContract.View view;
    User user;

    public SummaryPresenter(Context context, SummaryContract.View view) {
        this.context = context;
        this.view = view;
        this.user= UserHelper.getUser(context);

    }

    @Override
    public void getNilai() {
        List<NilaiSaya> nilaiSaya=PenilaianHelper.getNilaiSaya(context,user.getId());
        List<HashMap<String,Object>>list=new ArrayList<>();
        String tempKomponen="";
        //melakukan pengelompokkan nilai;
        int i=0;
        for (int j=0; j<nilaiSaya.size(); j++){
            if (!nilaiSaya.get(j).komponen.equalsIgnoreCase(tempKomponen)){
                tempKomponen=nilaiSaya.get(j).komponen;
                HashMap<String,Object>nilai=new HashMap<>();
                nilai.put("namaKomponen",nilaiSaya.get(j).komponen);
                List<NilaiSaya>nilaiKomponen=new ArrayList<>();
                while (i<nilaiSaya.size()){
                    if (nilaiSaya.get(i).komponen.equalsIgnoreCase(tempKomponen)){
                        nilaiKomponen.add(nilaiSaya.get(i));
                    }else {
                        j=i;
                        break;
                    }
                    i++;
                }
                nilai.put("nilai",nilaiKomponen);
                list.add(nilai);
            }
        }
        ///
        view.setNilai(list);

    }

    @Override
    public void removeDatTemp() {
        PenilaianHelper.deletePenilaian(context);
        MajelisHelper.deleteMajelis(context);
        PrefUtil.destroyPrefUjian(context);
    }
}
