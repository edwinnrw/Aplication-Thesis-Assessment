package skripsi.edwin.filkomapps.ketuaMonitor.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.edwin.filkomapps.api.EndpointAPI;
import skripsi.edwin.filkomapps.api.ServiceGenerator;
import skripsi.edwin.filkomapps.helper.MajelisHelper;
import skripsi.edwin.filkomapps.helper.PenilaianHelper;
import skripsi.edwin.filkomapps.helper.UserHelper;
import skripsi.edwin.filkomapps.model.GAP;
import skripsi.edwin.filkomapps.model.GetBeritaAcaraResponse;
import skripsi.edwin.filkomapps.model.orm.CompareNilai;
import skripsi.edwin.filkomapps.model.orm.Majelis;
import skripsi.edwin.filkomapps.model.orm.Nilai;
import skripsi.edwin.filkomapps.model.orm.RataRataKomponen;
import skripsi.edwin.filkomapps.model.orm.StatusKelulusan;
import skripsi.edwin.filkomapps.model.orm.User;
import skripsi.edwin.filkomapps.preference.PrefUtil;
import skripsi.edwin.filkomapps.util.TimeUtil;


/**
 * Created by EDN on 3/9/2018.
 */

public class RekapPresenter implements RekapContract.Presenter {
    private Context context;
    private RekapContract.View view;
    private HashMap<String, String> gap;
    private User user;
    private boolean isGap = false;

    public RekapPresenter(Context context, RekapContract.View view) {
        this.context = context;
        this.view = view;
        this.user = UserHelper.getUser(context);
    }

    @Override
    public void checkGap() {
        List<GAP> gapList = new ArrayList<>();
        this.gap = new HashMap<>();
        compare();
        if (gap.size() > 0) {
            for (String key : gap.keySet()) {
                GAP gap = new GAP();
                gap.setIdKomponen(gap.getIdKomponen());
                gap.setIdIndikator(key);
                gapList.add(gap);
            }
            view.setGap(gapList);
            isGap = true;
        } else {
            view.setGap(gapList);
            isGap = false;
        }
    }

    public void compare() {
        String peran[][] = {{"pembimbing1", "pembimbing2"}, {"penguji1", "penguji2"},
                {"pembimbing1", "penguji1"}, {"pembimbing1", "penguji2"}, {"pembimbing2", "penguji1"},
                {"pembimbing2", "penguji2"}};
        for (int i = 0; i < peran.length; i++) {
            List<Majelis> penguji = MajelisHelper.getMajelisByPeran(context, peran[i][0], peran[i][1]);
            List<CompareNilai> nilai = PenilaianHelper.getPenilaian(context, penguji.get(0).getId(),
                    penguji.get(1).getId());
            for (int j = 0; j < nilai.size(); j++) {
                int gap = nilai.get(j).penilai1 - nilai.get(j).penilai2;
                if (Math.abs(gap) >= 2) {
                    this.gap.put(nilai.get(j).indikatorId, nilai.get(j).idKomponen);
                }
            }
        }
    }

    @Override
    public void getRekap() {
        getRataRataPembimbing();
        getRataRataPenguji();
        getNilaiSemhas();
        getNilaiPublikasi();
        getKualitasPenulisanPembimbing();
        getKualitasPenulisanPenguji();
        getIsNilaiMajelis();
    }


    public void getRataRataPembimbing() {
        List<RataRataKomponen> rataRataKomponens = PenilaianHelper.getRataRataPembimbing(context);
        double ratarataKeseluruhan = 0;
        try {
            if (rataRataKomponens.size()>0){
                String peranTemp = rataRataKomponens.get(0).peran;
                double rataRataPembimbing = 0;
                int jumlahPembimbing = 1;
                for (int i = 0; i < rataRataKomponens.size(); i++) {
                    if (rataRataKomponens.get(i).peran.equalsIgnoreCase(peranTemp)) {
                        switch (rataRataKomponens.get(i).id) {
                            case "1":
                                rataRataPembimbing += rataRataKomponens.get(i).avg;
                                break;
                            case "2":
                                rataRataPembimbing += rataRataKomponens.get(i).avg;
                                break;
                            case "3":
                                rataRataPembimbing = rataRataPembimbing + (0.5 * rataRataKomponens.get(i).avg);
                                break;
                            case "4":
                                rataRataPembimbing += rataRataKomponens.get(i).avg;
                                break;
                            case "5":
                                rataRataPembimbing += (1.5 * rataRataKomponens.get(i).avg);
                                break;
                        }
                        if (i == rataRataKomponens.size() - 1) {
                            if (rataRataKomponens.get(i).isHadir.equalsIgnoreCase("1")) {
                                rataRataPembimbing = ((rataRataPembimbing / 5) * 100) / 7;
                            } else {
                                rataRataPembimbing = ((rataRataPembimbing / 2) * 100) / 7;
                            }
                            ratarataKeseluruhan += rataRataPembimbing;
                            rataRataPembimbing = 0;
                            ratarataKeseluruhan = ratarataKeseluruhan / jumlahPembimbing;
                            double subtotal = (ratarataKeseluruhan * 55) / 100;
                            DecimalFormat decimalFormat = new DecimalFormat("#0.0");
                            view.setRataRataPembimbing(decimalFormat.format(ratarataKeseluruhan),
                                    decimalFormat.format(subtotal));
                        }
                    } else {
                        peranTemp = rataRataKomponens.get(i).peran;
                        rataRataPembimbing = ((rataRataPembimbing / 5) * 100) / 7;
                        ratarataKeseluruhan += rataRataPembimbing;
                        rataRataPembimbing = 0;
                        jumlahPembimbing += 1;
                        i -= 1;
                    }
                }
            }
        }catch (Exception e){

        }


    }

    public void getRataRataPenguji() {
        List<RataRataKomponen> rataRataKomponens = PenilaianHelper.getRataRataPenguji(context);
        double ratarataKeseluruhan = 0;
        try {
            if (rataRataKomponens.size()>0){
                String peranTemp = rataRataKomponens.get(0).peran;
                double rataRataPenguji = 0;
                int jumlahPenguji = 1;
                for (int i = 0; i < rataRataKomponens.size(); i++) {
                    if (rataRataKomponens.get(i).peran.equalsIgnoreCase(peranTemp)) {
                        switch (rataRataKomponens.get(i).id) {
                            case "2":
                                rataRataPenguji += rataRataKomponens.get(i).avg;
                                break;
                            case "3":
                                rataRataPenguji = rataRataPenguji + (0.5 * rataRataKomponens.get(i).avg);
                                break;
                            case "4":
                                rataRataPenguji += rataRataKomponens.get(i).avg;
                                break;
                            case "5":
                                rataRataPenguji += (1.5 * rataRataKomponens.get(i).avg);
                                break;
                        }
                        if (i == rataRataKomponens.size() - 1) {
                            rataRataPenguji = ((rataRataPenguji / 4) * 100) / 7;
                            ratarataKeseluruhan += rataRataPenguji;
                            rataRataPenguji = 0;
                            ratarataKeseluruhan = ratarataKeseluruhan / jumlahPenguji;
                            double subtotal = (ratarataKeseluruhan * 30) / 100;
                            DecimalFormat decimalFormat = new DecimalFormat("#0.0");
                            view.setRataRataPenguji(decimalFormat.format(ratarataKeseluruhan),
                                    decimalFormat.format(subtotal));
                        }
                    } else {
                        peranTemp = rataRataKomponens.get(i).peran;
                        rataRataPenguji = ((rataRataPenguji / 4) * 100) / 7;
                        ratarataKeseluruhan += rataRataPenguji;
                        rataRataPenguji = 0;
                        jumlahPenguji += 1;
                        i -= 1;
                    }
                }
            }
        }catch (Exception e){

        }


    }

    public void getKualitasPenulisanPembimbing() {
        double ratarataKeseluruhan = 0;
        List<RataRataKomponen> rataRataKomponens = PenilaianHelper.getRataRataPenulisan(context, "pembimbing1", "pembimbing2");
        for (int i = 0; i < rataRataKomponens.size(); i++) {
            double rataRataPembimbing = 0;
            rataRataPembimbing += (rataRataKomponens.get(i).avg * 100) / 7;
            ratarataKeseluruhan += rataRataPembimbing;
        }
        ratarataKeseluruhan = ratarataKeseluruhan / rataRataKomponens.size();
        double subtotal = (ratarataKeseluruhan * 60) / 100;
        DecimalFormat decimalFormat = new DecimalFormat("#0.0");
        view.setNilaiPenulisanPembimbing(decimalFormat.format(ratarataKeseluruhan),
                decimalFormat.format(subtotal));

    }

    public void getKualitasPenulisanPenguji() {
        double ratarataKeseluruhan = 0;
        List<RataRataKomponen> rataRataKomponens = PenilaianHelper.getRataRataPenulisan(context, "penguji1", "penguji2");
        for (int i = 0; i < rataRataKomponens.size(); i++) {
            double rataRataPenguji = 0;
            rataRataPenguji += (rataRataKomponens.get(i).avg * 100) / 7;
            ratarataKeseluruhan += rataRataPenguji;
        }
        ratarataKeseluruhan = ratarataKeseluruhan / rataRataKomponens.size();
        double subtotal = (ratarataKeseluruhan * 40) / 100;
        DecimalFormat decimalFormat = new DecimalFormat("#0.0");
        view.setNilaiPenulisanPenguji(decimalFormat.format(ratarataKeseluruhan),
                decimalFormat.format(subtotal));
    }

    public void getNilaiPublikasi() {
        double jumlahRatarata = 0;
        List<Double> rataRataPublikasi = PenilaianHelper.getNilaiPublikasi(context);
        for (int i = 0; i < rataRataPublikasi.size(); i++) {
            jumlahRatarata += rataRataPublikasi.get(i);
        }
        double subtotal = ((jumlahRatarata / 2) * 10) / 100;
        DecimalFormat decimalFormat = new DecimalFormat("#0.0");
        view.setNilaiPublikasi(decimalFormat.format(jumlahRatarata / 2),
                decimalFormat.format(subtotal));
    }

    public void getNilaiSemhas() {
        double subtotal = (Double.parseDouble(PrefUtil.getSemhasTemp(context)) * 5) / 100;
        DecimalFormat decimalFormat = new DecimalFormat("#0.0");

        view.setSemhas(PrefUtil.getSemhasTemp(context), decimalFormat.format(subtotal));
    }

    public String getHurufNilai(String nilai) {
        double nilaiConvert = Double.parseDouble(nilai);
        if (nilaiConvert <= 100 && nilaiConvert >= 80) {
            return "A";
        }
        if (nilaiConvert < 80 && nilaiConvert >= 75) {
            return "B+";
        }
        if (nilaiConvert < 75 && nilaiConvert >= 70) {
            return "B";
        }
        if (nilaiConvert < 70 && nilaiConvert >= 60) {
            return "C+";
        }
        if (nilaiConvert < 60 && nilaiConvert >= 56) {
            return "C";
        }
        if (nilaiConvert < 56) {
            return "D";
        }
        return "";
    }

    @Override
    public void validation(int s) {
        List<Majelis> majelis = MajelisHelper.getMajelisIsHadir(context);
        boolean isComplete = true;
        for (int i = 0; i < majelis.size(); i++) {
            List<Nilai> nilaiList = PenilaianHelper.getPenilaian(context, majelis.get(i).getId());
            if (nilaiList.size() > 0) {
                isComplete = false;
                break;
            }
        }
        if (!isComplete || !isGap || s == 0) {
            if (isComplete) {
                view.validationFalse("Penilaian Belum Lengkap");
            } else {
                if (this.isGap) {
                    view.validationFalse("Masih Terdapat GAP Nilai");
                } else {
                    if (s == 0) {
                        view.validationFalse("Anda Belum Menentukan Status Kelulusan Mahasiswa");
                    } else {
                        view.validationSuccess();
                    }
                }
            }
        }

    }

    @Override
    public void submitFinal(String catatan, String predikat, String na) {
        view.showProgress();
        StatusKelulusan statusKelulusan = PenilaianHelper.getStatusKelulusan(context, predikat);
        HashMap<String, String> param = new HashMap<>();
        param.put("id_ujian", PrefUtil.getIdUjianTemp(context));
        param.put("id_status_kelulusan", statusKelulusan.getId());
        param.put("catatan", catatan);
        param.put("nilai_akhir", na);
        ServiceGenerator.createService(EndpointAPI.class, user.getApiToken()).submitBeritaAcara(param)
                .enqueue(new Callback<GetBeritaAcaraResponse>() {
                    @Override
                    public void onResponse(Call<GetBeritaAcaraResponse> call, Response<GetBeritaAcaraResponse> response) {
                        view.dismisProgress();
                        if (response.code() == 200) {
                            view.submitSuccess();
                            deleteDataTemp();
                        } else {
                            view.showFailMessage(response.message());
                        }

                    }

                    @Override
                    public void onFailure(Call<GetBeritaAcaraResponse> call, Throwable t) {
                        view.showFailMessage(t.getMessage());

                    }
                });
    }

    public void deleteDataTemp() {
        PenilaianHelper.deletePenilaian(context);
        MajelisHelper.deleteMajelis(context);
        PrefUtil.destroyPrefUjian(context);
    }

    @Override
    public void isNotif() {
        if (PrefUtil.haveNewNotif(context)){
            view.showBadge();
        }else {
            view.hideBadge();
        }
    }

    @Override
    public void saveUpdate() {
        PrefUtil.setTimeUpdate(context, TimeUtil.getCurrentTime());
        view.setTimeUpdate(PrefUtil.getTimeUpdate(context));

    }

    @Override
    public void getTimeUpdate() {
        if (PrefUtil.getTimeUpdate(context).equalsIgnoreCase("")) {
            view.setTimeUpdate(TimeUtil.getCurrentTime());
        } else {
            view.setTimeUpdate(PrefUtil.getTimeUpdate(context));
        }
    }

    @Override
    public void getNilaiAkhirSkripsi(double semhas, double np, double nuPembimbing, double v) {
        double nilaiAkhir = semhas + np + nuPembimbing + v;
        DecimalFormat decimalFormat = new DecimalFormat("#0.0");
        view.setNilaiAkhirUjian(decimalFormat.format(nilaiAkhir));

    }

    @Override
    public void getIsNilaiMajelis() {
        List<Majelis> majelis = MajelisHelper.getMajelis(context);
        for (int i = 0; i < majelis.size(); i++) {
            List<Nilai> nilaiList = PenilaianHelper.getPenilaian(context, majelis.get(i).getId());
            switch (majelis.get(i).getPeran()) {
                case "pembimbing1":
                    if (nilaiList.size() > 0) {
                        view.setCheckPembimbing1(true);

                    } else {
                        view.setCheckPembimbing1(false);

                    }
                    break;
                case "pembimbing2":
                    if (nilaiList.size() > 0) {
                        view.setCheckPembimbing2(true);

                    } else {
                        view.setCheckPembimbing2(false);

                    }
                    break;
                case "penguji1":
                    if (nilaiList.size() > 0) {
                        view.setCheckPenguji1(true);

                    } else {
                        view.setCheckPenguji1(false);

                    }
                    break;
                case "penguji2":
                    if (nilaiList.size() > 0) {
                        view.setCheckPenguji2(true);

                    } else {
                        view.setCheckPenguji2(false);

                    }
                    break;
            }
        }

    }

    @Override
    public void buildDropdown() {
        List<StatusKelulusan> statusKelulusans = PenilaianHelper.getStatusKelulusan(context);
        String list[] = new String[statusKelulusans.size() + 1];
        list[0] = "Pilih Status";
        for (int i = 0; i < statusKelulusans.size(); i++) {
            list[i + 1] = statusKelulusans.get(i).getStatus();
        }
        view.setDropdown(list);
    }

}
