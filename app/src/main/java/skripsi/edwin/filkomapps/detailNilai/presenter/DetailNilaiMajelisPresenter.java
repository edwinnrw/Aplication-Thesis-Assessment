package skripsi.edwin.filkomapps.detailNilai.presenter;

import android.content.Context;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import skripsi.edwin.filkomapps.helper.PenilaianHelper;
import skripsi.edwin.filkomapps.model.orm.DetailNilai;
import skripsi.edwin.filkomapps.model.orm.Majelis;
import skripsi.edwin.filkomapps.model.orm.NilaiSaya;

public class DetailNilaiMajelisPresenter implements DetailNilaiMajelisContract.Presenter {
    Context context;
    DetailNilaiMajelisContract.View view;
    DecimalFormat decimalFormat;

    public DetailNilaiMajelisPresenter(Context context, DetailNilaiMajelisContract.View view) {
        this.context = context;
        this.view = view;
        decimalFormat = new DecimalFormat("#0.0");

    }

    @Override
    public void getNilai(int pos) {
        String posisi[] = {"pembimbing1", "pembimbing2", "penguji1", "penguji2"};
        List<DetailNilai> nilaiSaya = PenilaianHelper.getSummaryNilaiMajelis(context, posisi[pos]);
        if (nilaiSaya.size() > 0) {
            List<HashMap<String, Object>> list = new ArrayList<>();
            String tempKomponen = "";
            //melakukan pengelompokkan nilai;
            int i = 0;
            for (int j = 0; j < nilaiSaya.size(); j++) {
                if (!nilaiSaya.get(j).komponen.equalsIgnoreCase(tempKomponen)) {
                    tempKomponen = nilaiSaya.get(j).komponen;
                    HashMap<String, Object> nilai = new HashMap<>();
                    nilai.put("namaKomponen", nilaiSaya.get(j).komponen);
                    List<DetailNilai> nilaiKomponen = new ArrayList<>();
                    while (i < nilaiSaya.size()) {
                        if (nilaiSaya.get(i).komponen.equalsIgnoreCase(tempKomponen)) {
                            nilaiKomponen.add(nilaiSaya.get(i));
                        } else {
                            j = i;
                            break;
                        }
                        i++;
                    }
                    nilai.put("nilai", nilaiKomponen);
                    list.add(nilai);
                }
            }
            view.setNilai(list, nilaiSaya.get(0).namaDosen, posisi[pos]);
        }

    }

}
