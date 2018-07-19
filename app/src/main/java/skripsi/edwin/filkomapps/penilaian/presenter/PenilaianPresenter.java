package skripsi.edwin.filkomapps.penilaian.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.edwin.filkomapps.api.EndpointAPI;
import skripsi.edwin.filkomapps.api.ServiceGenerator;
import skripsi.edwin.filkomapps.helper.PenilaianHelper;
import skripsi.edwin.filkomapps.helper.UserHelper;
import skripsi.edwin.filkomapps.model.DataKomponen;
import skripsi.edwin.filkomapps.model.GetKomponenResponse;
import skripsi.edwin.filkomapps.model.GetSaveResponse;
import skripsi.edwin.filkomapps.model.orm.Nilai;
import skripsi.edwin.filkomapps.model.orm.User;
import skripsi.edwin.filkomapps.preference.PrefUtil;

public class PenilaianPresenter implements PenilaianContract.Presenter {
    private PenilaianContract.View view;
    private Context context;
    private User user;

    public PenilaianPresenter(PenilaianContract.View view, Context context) {
        this.view = view;
        this.context = context;
        this.user = UserHelper.getUser(context);
    }

    @Override
    public void checkAkses() {
        view.showProgress();
        HashMap<String, String> param = new HashMap<>();
        param.put("id_ujian", PrefUtil.getIdUjianTemp(context));
        param.put("id_dosen", user.getId());
        ServiceGenerator.createService(EndpointAPI.class, user.getApiToken()).checkAkses(param)
                .enqueue(new Callback<GetKomponenResponse>() {
                    @Override
                    public void onResponse(Call<GetKomponenResponse> call, Response<GetKomponenResponse> response) {
                        if (response.code() == 200) {
                            if (response.body().getSuccess().equalsIgnoreCase("true")) {
                                saveKomponen(response.body().getDataKomponen());
                                view.dismissProgress();
                            } else {
                                view.dismissProgress();
                                view.showMessageError(response.body().getMessage());
                            }

                        } else {

                            view.showMessageError(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<GetKomponenResponse> call, Throwable t) {
                        view.dismissProgress();
                        view.showMessageError(t.getMessage());

                    }
                });

    }


    private void saveKomponen(List<DataKomponen> dataKomponen) {
        List<DataKomponen> custom = new ArrayList<>();
        for (int i = 0; i < dataKomponen.size(); i++) {
            if (PrefUtil.getIsPenguji(context).equalsIgnoreCase("1")) {
                if (!dataKomponen.get(i).getId().equalsIgnoreCase("1")) {
                    custom.add(dataKomponen.get(i));
                }
            } else {
                if (PrefUtil.getAccessPenilaian(context, PrefUtil.getIdUjianTemp(context))) {
                    if (!dataKomponen.get(i).getId().equalsIgnoreCase("6")) {
                        custom.add(dataKomponen.get(i));
                    }
                } else {
                    if (dataKomponen.get(i).getId().equalsIgnoreCase("1") ||
                            dataKomponen.get(i).getId().equalsIgnoreCase("2")) {
                        custom.add(dataKomponen.get(i));
                    }
                }
            }

        }
        view.setListKomponen(custom);
        PenilaianHelper.insertKomponen(context, dataKomponen);
    }

    @Override
    public void validationIsComplete(List<DataKomponen> dataKomponenList) {

        for (int i = 0; i < dataKomponenList.size(); i++) {
            List<Nilai> nilai = PenilaianHelper.checkPenilaianSaya(context, user.getId(),
                    dataKomponenList.get(i).getId());

            if (i == dataKomponenList.size() - 1) {
                if (nilai.size() == 0) {
                    view.validationError();
                } else {
                    view.validationSuccess();
                    break;
                }
            } else {
                if (nilai.size() == 0) {
                    view.validationError();
                    break;
                }

            }
        }
    }

    @Override
    public void submitNilai(String isKetua) {
        view.showProgress();
        HashMap<String, Object> param = new HashMap<>();
        param.put("id_skripsi", PrefUtil.getIdSkripsiTemp(context));
        param.put("id_ujian", PrefUtil.getIdUjianTemp(context));
        List<Nilai> nilaiUjian = PenilaianHelper.getPenilaian(context, user.getId());
        param.put("nilai_ujian", nilaiUjian);
        ServiceGenerator.createService(EndpointAPI.class, user.getApiToken()).saveNilai(param).enqueue(new Callback<GetSaveResponse>() {
            @Override
            public void onResponse(Call<GetSaveResponse> call, Response<GetSaveResponse> response) {
                view.dismissProgress();
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        if (response.body().getSuccess()) {
                            if (PrefUtil.getIsPenguji(context).equalsIgnoreCase("1")) {
                                PrefUtil.setIsSubmitNilai(context, PrefUtil.getIdSkripsiTemp(context), true);
                                PenilaianHelper.insertStatusKelulusan(context, response.body().getData());
                            } else {
                                if (PrefUtil.getAccessPenilaian(context, PrefUtil.getIdUjianTemp(context))) {
                                    PrefUtil.setIsSubmitNilai(context, PrefUtil.getIdSkripsiTemp(context), true);
                                }

                            }
                            view.submitSuccess();
                        }


                    }
                }
            }

            @Override
            public void onFailure(Call<GetSaveResponse> call, Throwable t) {
                view.dismissProgress();
                view.showSubmitError(t.getMessage());
            }
        });
    }


    @Override
    public void checkIsKetua() {

        if (PrefUtil.getIsKetua(context).equalsIgnoreCase("0")) {
            if (PrefUtil.getIsPenguji(context).equalsIgnoreCase("1")) {
                PrefUtil.setIsSubmitNilai(context, PrefUtil.getIdUjianTemp(context), true);
                view.goToSummary();

            } else {

                if (PrefUtil.getAccessPenilaian(context, PrefUtil.getIdUjianTemp(context))) {
                    PrefUtil.setIsSubmitNilai(context, PrefUtil.getIdUjianTemp(context), true);
                    view.goToSummary();
                } else {

                }
            }

        } else {
            PrefUtil.setIsSubmitNilai(context, PrefUtil.getIdUjianTemp(context), true);
            view.goToMonitor();
        }
    }

    @Override
    public void isShowMenuTimer() {
        if (PrefUtil.getIsKetua(context).equalsIgnoreCase("1")) {
            view.showMenuTimer();
        }
    }

    @Override
    public boolean checkIsDone(String komponen) {
        List<Nilai> nilai = PenilaianHelper.checkPenilaianSaya(context, user.getId(), komponen);
        if (nilai.size() > 0) {
            return true;
        } else {
            return false;

        }
    }
}
