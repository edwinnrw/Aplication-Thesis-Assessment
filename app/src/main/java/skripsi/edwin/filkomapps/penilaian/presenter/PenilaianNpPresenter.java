package skripsi.edwin.filkomapps.penilaian.presenter;

import android.content.Context;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import skripsi.edwin.filkomapps.helper.PenilaianHelper;
import skripsi.edwin.filkomapps.helper.UserHelper;
import skripsi.edwin.filkomapps.model.DataKomponen;
import skripsi.edwin.filkomapps.model.orm.Nilai;
import skripsi.edwin.filkomapps.model.orm.User;

public class PenilaianNpPresenter implements PenilaianNpContract.Presenter {
    private Context context;
    private User user;
    private PenilaianNpContract.View view;

    public PenilaianNpPresenter(Context context, PenilaianNpContract.View view) {
        this.context = context;
        this.view = view;
        this.user = UserHelper.getUser(context);

    }

    @Override
    public void buildIndikatorPenilaian(DataKomponen indikators) {
        for (int i = 0; i < indikators.getIndikators().size(); i++) {
            view.setIndikator(indikators.getIndikators().get(i).getId(), indikators.getIndikators().get(i).getIndikator(), indikators.getIndikators().get(i).getKeterangan());
        }


    }

    @Override
    public void validationIsComplete(DataKomponen dataKomponen, HashMap<String, EditText> editTexts) {
        int error = 0;
        for (String key : editTexts.keySet()) {
            if (editTexts.get(key)
                    .getText().toString().equalsIgnoreCase("") || editTexts.get(key)
                    .getText().toString().equalsIgnoreCase(null)) {
                view.validationError(editTexts.get(key));
                error += 1;

            }
        }
        if (error == 0) {
            view.validationComplete();
        }

    }

    @Override
    public void saveNilai(DataKomponen dataKomponen, HashMap<String, EditText> editTexts) {
        for (int i = 0; i < dataKomponen.getIndikators().size(); i++) {
            Nilai nilaiRincian = new Nilai();
            nilaiRincian.setIndikatorId(dataKomponen.getIndikators().get(i).getId());
            nilaiRincian.setIdDosen(user.getId());
            nilaiRincian.setNilai(Integer.parseInt(editTexts.get(dataKomponen.getIndikators().get(i).getIndikator()).getText().toString()));
            PenilaianHelper.insertNilai(context,nilaiRincian);

        }

    }

    @Override
    public String getNilaiTemp(String indikatorId) {
        Nilai nilai = PenilaianHelper.getIndikatorNilai(context,indikatorId,user.getId());
        if (nilai != null) {
            return String.valueOf(nilai.getNilai());

        } else {
            return "";
        }

    }


}
