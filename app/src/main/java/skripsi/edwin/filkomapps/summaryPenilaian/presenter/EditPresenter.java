package skripsi.edwin.filkomapps.summaryPenilaian.presenter;

import android.content.Context;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.api.EndpointAPI;
import skripsi.edwin.filkomapps.api.ServiceGenerator;
import skripsi.edwin.filkomapps.helper.PenilaianHelper;
import skripsi.edwin.filkomapps.helper.UserHelper;
import skripsi.edwin.filkomapps.model.GetSaveResponse;
import skripsi.edwin.filkomapps.model.orm.Nilai;
import skripsi.edwin.filkomapps.model.orm.User;
import skripsi.edwin.filkomapps.penilaian.DeskripsiPenilaianKomponen1;
import skripsi.edwin.filkomapps.penilaian.DeskripsiPenilaianKomponen2;
import skripsi.edwin.filkomapps.penilaian.DeskripsiPenilaianKomponen3;
import skripsi.edwin.filkomapps.penilaian.DeskripsiPenilaianKomponen4;
import skripsi.edwin.filkomapps.penilaian.DeskripsiPenilaianKomponen5;
import skripsi.edwin.filkomapps.preference.PrefUtil;

public class EditPresenter implements EditContract.Presenter {
    Context context;
    EditContract.View view;
    User user;

    public EditPresenter(final Context context, EditContract.View view) {
        this.context = context;
        this.view = view;
        this.user= UserHelper.getUser(context);
    }


    @Override
    public void getDeskripsi(String komponen, int nilai, int indikator) {
        switch (komponen) {
            case "1":
                getDeskripsiKomponen1(nilai, indikator);
                break;
            case "2":
                getDeskripsiKomponen2(nilai, indikator);
                break;
            case "3":
                getDeskripsiKomponen3(nilai, indikator);
                break;
            case "4":
                getDeskripsiKomponen4(nilai, indikator);
                break;
            case "5":
                getDeskripsiKomponen5(nilai, indikator);
                break;
        }
    }

    @Override
    public void getNilaiTemp(String nilai) {
        switch (nilai) {
            case "1":
                view.setNilaiTemp("Sangat Buruk", R.id.rbNilai1);
                break;
            case "2":
                view.setNilaiTemp("Buruk", R.id.rbNilai2);
                break;
            case "3":
                view.setNilaiTemp("Kurang", R.id.rbNilai3);
                break;
            case "4":
                view.setNilaiTemp("Netral", R.id.rbNilai4);
                break;
            case "5":
                view.setNilaiTemp("Cukup Baik", R.id.rbNilai5);
                break;
            case "6":
                view.setNilaiTemp("Baik", R.id.rbNilai6);
                break;
            case "7":
                view.setNilaiTemp("Sangat Baik", R.id.rbNilai7);
                break;

        }
    }

    @Override
    public void getNilai(String nilai) {
        switch (nilai) {
            case "0":
                view.setNilai("");
                break;
            case "1":
                view.setNilai("Sangat Buruk");
                break;
            case "2":
                view.setNilai("Buruk");
                break;
            case "3":
                view.setNilai("Kurang");
                break;
            case "4":
                view.setNilai("Netral");
                break;
            case "5":
                view.setNilai("Cukup Baik");
                break;
            case "6":
                view.setNilai("Baik");
                break;
            case "7":
                view.setNilai("Sangat Baik");
                break;
        }
    }

    @Override
    public void saveNilai(final String idIndikator, final String idKomponen, final HashMap<String, String> nilai) {
        nilai.put("id_ujian", PrefUtil.getIdUjianTemp(context));
        nilai.put("id_skripsi", PrefUtil.getIdSkripsiTemp(context));
        nilai.put("id_penilai", user.getId());
        nilai.put("id_indikator", idIndikator);
        nilai.put("id_komponen", idKomponen);
        view.showProgress();
        ServiceGenerator.createService(EndpointAPI.class, user.getApiToken()).updateNilai(nilai).enqueue(new Callback<GetSaveResponse>() {
            @Override
            public void onResponse(Call<GetSaveResponse> call, Response<GetSaveResponse> response) {
                view.dismissProgress();
                if (response.code() == 200) {
                    Nilai nilaiSave = new Nilai();
                    nilaiSave.setIndikatorId(idIndikator);
                    nilaiSave.setIdDosen(user.getId());
                    nilaiSave.setNilai(Integer.parseInt(nilai.get("nilai")));
                    PenilaianHelper.updateNilai(context,nilaiSave);
                    view.updateSuccess();
                } else {
                    view.updateFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<GetSaveResponse> call, Throwable t) {
                view.dismissProgress();
                view.updateFail(t.getMessage());

            }
        });
    }

    public void getDeskripsiKomponen1(int nilai, int indikator) {
        DeskripsiPenilaianKomponen1 deskripsi = new DeskripsiPenilaianKomponen1(context);
        switch (indikator + 1) {
            case 1:
                view.setDeskripsi(deskripsi.getDeskripsiKeterangan1(nilai));
                break;
            case 2:
                view.setDeskripsi(deskripsi.getDeskripsiKeterangan2(nilai));
                break;
            case 3:
                view.setDeskripsi(deskripsi.getDeskripsiKeterangan3(nilai));
                break;
            case 4:
                view.setDeskripsi(deskripsi.getDeskripsiKeterangan4(nilai));
                break;
        }

    }

    public void getDeskripsiKomponen2(int nilai, int indikator) {
        DeskripsiPenilaianKomponen2 deskripsi = new DeskripsiPenilaianKomponen2(context);
        switch (indikator + 1) {
            case 1:
                view.setDeskripsi(deskripsi.getDeskripsiKeterangan1(nilai));
                break;
            case 2:
                view.setDeskripsi(deskripsi.getDeskripsiKeterangan2(nilai));
                break;
            case 3:
                view.setDeskripsi(deskripsi.getDeskripsiKeterangan3(nilai));
                break;
            case 4:
                view.setDeskripsi(deskripsi.getDeskripsiKeterangan4(nilai));
                break;
        }
    }

    public void getDeskripsiKomponen3(int nilai, int indikator) {
        DeskripsiPenilaianKomponen3 deskripsi = new DeskripsiPenilaianKomponen3(context);
        switch (indikator + 1) {
            case 1:
                view.setDeskripsi(deskripsi.getDeskripsiKeterangan1(nilai));
                break;
            case 2:
                view.setDeskripsi(deskripsi.getDeskripsiKeterangan2(nilai));
                break;
            case 3:
                view.setDeskripsi(deskripsi.getDeskripsiKeterangan3(nilai));
                break;
            case 4:
                view.setDeskripsi(deskripsi.getDeskripsiKeterangan4(nilai));
                break;
            case 5:
                view.setDeskripsi(deskripsi.getDeskripsiKeterangan5(nilai));
                break;
            case 6:
                view.setDeskripsi(deskripsi.getDeskripsiKeterangan6(nilai));
                break;
        }
    }

    public void getDeskripsiKomponen4(int nilai, int indikator) {
        DeskripsiPenilaianKomponen4 deskripsi = new DeskripsiPenilaianKomponen4(context);
        switch (indikator + 1) {
            case 1:
                view.setDeskripsi(deskripsi.getDeskripsiKeterangan1(nilai));
                break;
            case 2:
                view.setDeskripsi(deskripsi.getDeskripsiKeterangan2(nilai));
                break;
            case 3:
                view.setDeskripsi(deskripsi.getDeskripsiKeterangan3(nilai));
                break;
            case 4:
                view.setDeskripsi(deskripsi.getDeskripsiKeterangan4(nilai));
                break;

        }
    }

    public void getDeskripsiKomponen5(int nilai, int indikator) {
        DeskripsiPenilaianKomponen5 deskripsi = new DeskripsiPenilaianKomponen5(context);
        switch (indikator + 1) {
            case 1:
                view.setDeskripsi(deskripsi.getDeskripsiKeterangan1(nilai));
                break;
            case 2:
                view.setDeskripsi(deskripsi.getDeskripsiKeterangan2(nilai));
                break;
            case 3:
                view.setDeskripsi(deskripsi.getDeskripsiKeterangan3(nilai));
                break;

        }
    }

}
