package skripsi.edwin.filkomapps.verifikasihKehadiran.presenter;

import android.content.Context;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.api.EndpointAPI;
import skripsi.edwin.filkomapps.api.ServiceGenerator;
import skripsi.edwin.filkomapps.helper.MajelisHelper;
import skripsi.edwin.filkomapps.helper.UserHelper;
import skripsi.edwin.filkomapps.model.GetVerifikasiResponse;
import skripsi.edwin.filkomapps.model.orm.Majelis;
import skripsi.edwin.filkomapps.model.orm.User;
import skripsi.edwin.filkomapps.preference.PrefUtil;

public class VerifikasiPresenter implements VerifikasiContract.Presenter {
    private Context context;
    private VerifikasiContract.View view;
    private User user;
    private List<HashMap<String, String>> kehadiran;
    private List<HashMap<String, String>> dataDosen;
    private HashMap<String, String> kehadiranPembimbing1;
    private HashMap<String, String> kehadiranPembimbing2;
    private HashMap<String, String> kehadiranPenguji1;
    private HashMap<String, String> kehadiranPenguji2;
    private HashMap<String, String> dosenPengganti;

    public VerifikasiPresenter(Context context, VerifikasiContract.View view, List<HashMap<String, String>> dataDosen) {
        this.context = context;
        this.view = view;
        this.dataDosen = dataDosen;

    }

    @Override
    public void validateIsChecked(int mahasiswaIsChecked, int pembimbing1IsChecked,
                                  int pembimbing2IsChecked, int penguji1IsChecked, int penguji2IsChecked) {
        kehadiran = new ArrayList<>();
        if (mahasiswaIsChecked == -1 || pembimbing1IsChecked == -1 || pembimbing2IsChecked == -1
                || penguji1IsChecked == -1 || penguji2IsChecked == -1) {

            if (mahasiswaIsChecked == R.id.radio_tdkhadir_mahasiswa) {
                view.validateSuccess(kehadiran);
            } else {

                view.validateFailed("Anda Tidak Dapat Melakukan Verifikasi, Terdapat Kehadiran Yang Belum Anda Checklist");

            }

        } else {
            if (penguji1IsChecked == R.id.radio_tdkhadir_penguji1) {
                view.validateFailed("Anda Tidak Dapat Melakukan Verifikasi, Ketika Ketua Majelis Tidak Hadir");

            } else {
                setDataKehadiran();
                view.validateSuccess(kehadiran);

            }
        }

    }

    private void setDataKehadiran() {
        if (kehadiranPembimbing1 != null) {
            kehadiran.add(kehadiranPembimbing1);
        }
        if (kehadiranPembimbing2 != null) {
            kehadiran.add(kehadiranPembimbing2);
        }
        if (kehadiranPenguji1 != null) {
            kehadiran.add(kehadiranPenguji1);
        }
        if (kehadiranPenguji2 != null) {
            kehadiran.add(kehadiranPenguji2);
        }

    }

    @Override
    public void verifikasiProcess() {
        user = UserHelper.getUser(context);
        final HashMap<String, Object> param = new HashMap<>();
        param.put("id_ujian", PrefUtil.getIdUjianTemp(context));
        if (dosenPengganti != null) {
            param.put("update_majelis", "1");
            param.put("data_pengganti", dosenPengganti);

        } else {
            param.put("update_majelis", "0");

        }
        param.put("dosen", kehadiran);
        view.showProgress();
        ServiceGenerator.createService(EndpointAPI.class, user.getApiToken()).verifikasi(param)
                .enqueue(new Callback<GetVerifikasiResponse>() {
                    @Override
                    public void onResponse(Call<GetVerifikasiResponse> call, Response<GetVerifikasiResponse> response) {
                        view.dismissProgress();
                        if (response.code() == 200) {
                            PrefUtil.setIsDoneVerifikasi(context, true);
                            PrefUtil.setAccessPenilaian(context, PrefUtil.getIdUjianTemp(context), true);
                            if (String.valueOf(param.get("update_majelis")).equalsIgnoreCase("0")) {
                                view.showVerifikasiSuccess();

                            } else {
                                view.showWaitingConfirm();
                            }
                        } else {
                            view.showVerifikasiFailed(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<GetVerifikasiResponse> call, Throwable t) {
                        view.dismissProgress();
                        view.showVerifikasiFailed(t.getMessage());
                    }
                });

    }

    @Override
    public void saveDosen() {
        for (int i = 0; i < dataDosen.size(); i++) {
            for (int j = 0; j < kehadiran.size(); j++) {
                if (dataDosen.get(i).get("id_dosen").equalsIgnoreCase(kehadiran.get(j).get("id_dosen"))) {
                    Majelis majelis = new Majelis();
                    majelis.setId(dataDosen.get(i).get("id_dosen"));
                    majelis.setNama(dataDosen.get(i).get("nama"));
                    majelis.setPeran(dataDosen.get(i).get("peran"));
                    majelis.setIsHadir(kehadiran.get(i).get("is_hadir"));
                    MajelisHelper.insertMajelis(context,majelis);
                }
            }

        }

    }

    @Override
    public void setKehadiranDosenPembimbing1(String isHadir) {
        kehadiranPembimbing1 = new HashMap<>();
        kehadiranPembimbing1.put("is_hadir", isHadir);
        kehadiranPembimbing1.put("id_dosen", dataDosen.get(0).get("id_dosen"));

    }

    @Override
    public void setKehadiranDosenPembimbing2(String isHadir) {
        kehadiranPembimbing2 = new HashMap<>();
        kehadiranPembimbing2.put("is_hadir", isHadir);
        kehadiranPembimbing2.put("id_dosen", dataDosen.get(1).get("id_dosen"));

    }

    @Override
    public void setKehadiranDosenPenguji1(String isHadir) {
        kehadiranPenguji1 = new HashMap<>();
        kehadiranPenguji1.put("is_hadir", isHadir);
        kehadiranPenguji1.put("id_dosen", dataDosen.get(2).get("id_dosen"));

    }

    @Override
    public void setKehadiranDosenPenguji2(String isHadir, boolean isUpdate) {
        kehadiranPenguji2 = new HashMap<>();
        if (!isUpdate) {
            kehadiranPenguji2.put("is_hadir", isHadir);
            kehadiranPenguji2.put("id_dosen", dataDosen.get(3).get("id_dosen"));
        }

    }

    @Override
    public void setNamaDosenPengujiPengganti(String nama, String id) {
        dosenPengganti = new HashMap<>();
        dosenPengganti.put("id_dosen_saat_ini", dataDosen.get(3).get("id_dosen"));
        dosenPengganti.put("id_dosen_pengganti", id);
        kehadiranPenguji2.put("is_hadir", "1");
        kehadiranPenguji2.put("id_dosen", id);
        dataDosen.get(3).put("id_dosen", dosenPengganti.get("id_dosen_pengganti"));
    }
}
