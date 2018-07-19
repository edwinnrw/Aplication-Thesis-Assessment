package skripsi.edwin.filkomapps.penilaian.presenter;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import skripsi.edwin.filkomapps.helper.PenilaianHelper;
import skripsi.edwin.filkomapps.helper.UserHelper;
import skripsi.edwin.filkomapps.model.DataKomponen;

import skripsi.edwin.filkomapps.model.orm.Nilai;
import skripsi.edwin.filkomapps.model.orm.User;
import skripsi.edwin.filkomapps.penilaian.DeskripsiPenilaianKomponen1;
import skripsi.edwin.filkomapps.penilaian.DeskripsiPenilaianKomponen2;
import skripsi.edwin.filkomapps.penilaian.DeskripsiPenilaianKomponen3;
import skripsi.edwin.filkomapps.penilaian.DeskripsiPenilaianKomponen4;
import skripsi.edwin.filkomapps.penilaian.DeskripsiPenilaianKomponen5;

public class IndikatorPresenter implements IndikatorContract.Presenter {
    private Context context;
    private IndikatorContract.View view;
    private User user;

    public IndikatorPresenter(Context context, IndikatorContract.View view) {
        this.context = context;
        this.view = view;
        this.user = UserHelper.getUser(context);

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
    public void getNilai(int nilai) {
        switch (nilai) {
            case 1:
                view.setKategoriNilai("Sangat Buruk");
                break;
            case 2:
                view.setKategoriNilai("Buruk");
                break;
            case 3:
                view.setKategoriNilai("Kurang");
                break;
            case 4:
                view.setKategoriNilai("Netral");
                break;
            case 5:
                view.setKategoriNilai("Cukup Baik");
                break;
            case 6:
                view.setKategoriNilai("Baik");
                break;
            case 7:
                view.setKategoriNilai("Sangat Baik");
                break;
        }
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

    @Override
    public void saveNilaiTemp(DataKomponen dataKomponen, List<HashMap<String, String>> nilai) {
        for (int i = 0; i < nilai.size(); i++) {
            Nilai nilaiRincian = new Nilai();
            nilaiRincian.setIndikatorId(nilai.get(i).get("id"));
            nilaiRincian.setIdDosen(user.getId());
            nilaiRincian.setNilai(Integer.parseInt(nilai.get(i).get("nilai")));
            PenilaianHelper.insertNilai(context,nilaiRincian);
        }


    }

    @Override
    public void getNilaiTemp(String indikator) {
        Nilai nilai = PenilaianHelper.getIndikatorNilai(context,indikator,user.getId());
        if (nilai != null) {
            view.setNilaiTempt(nilai.getNilai());
        }

    }

}
