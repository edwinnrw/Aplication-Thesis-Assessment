package skripsi.edwin.filkomapps.detailUjian.presenter;

import android.content.Context;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.edwin.filkomapps.api.EndpointAPI;
import skripsi.edwin.filkomapps.api.ServiceGenerator;
import skripsi.edwin.filkomapps.helper.UserHelper;
import skripsi.edwin.filkomapps.model.GetDataUjianResponse;
import skripsi.edwin.filkomapps.model.orm.User;
import skripsi.edwin.filkomapps.preference.PrefUtil;
import skripsi.edwin.filkomapps.util.DateUtils;


/**
 * Created by EDN on 3/6/2018.
 */

public class DetailJadwalUjianPresenter implements DetailJadwalUjianContract.Presenter {
    Context context;
    DetailJadwalUjianContract.View view;
    User user;

    public DetailJadwalUjianPresenter(final Context context, DetailJadwalUjianContract.View view) {
        this.context = context;
        this.view = view;
        this.user = UserHelper.getUser(context);

    }

    @Override
    public void getDetailJadwal(String id) {
        PrefUtil.setIdUjianTemp(context, id);
        view.showProgress();
        HashMap<String, String> param = new HashMap<>();
        param.put("id_info_ujian", id);
        ServiceGenerator.createService(EndpointAPI.class, user.getApiToken()).getDataUjian(param)
                .enqueue(new Callback<GetDataUjianResponse>() {
                    @Override
                    public void onResponse(Call<GetDataUjianResponse> call, Response<GetDataUjianResponse> response) {
                        if (response.code() == 200) {
                            view.dismissProgress();
                            PrefUtil.setNilaiSemhasTemp(context, response.body().getData().getMahasiswa().getSkripsi().getSemhas());
                            PrefUtil.setIdSkripsiTemp(context, response.body().getData().getMahasiswa().getSkripsi().getId());
                            view.showDataUjian(response.body().getData());

                            if (response.body().getData().getStatus().equalsIgnoreCase("3")) {
                                view.showBeritaAcara(response.body().getData().getBeritaAcara());
                            }
                        } else {
                            view.showMessage(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<GetDataUjianResponse> call, Throwable t) {
                        view.showMessage("Error: " + t.getMessage());

                    }
                });

    }

    @Override
    public void isShowBtnKehadiran(int isKetua) {
        if (isKetua == 1) {
            view.showBtnKehadiran();
        }
    }

    @Override
    public void isEnableBtnMenilai(int isPenguji, int status) {

        if (isPenguji == 1) {
            if (PrefUtil.getAccessPenilaian(context, PrefUtil.getIdUjianTemp(context)) || status == 1) {
                if (PrefUtil.getIsKetua(context).equalsIgnoreCase("1")) {
                    if (PrefUtil.getIsDoneVerifikasi(context)) {
                        view.enableMenilai();
                    }

                } else {
                    view.enableMenilai();
                }
            }
        } else {

            view.enableMenilai();
        }
    }

    @Override
    public void isEnableBtnKehadiran(long tanggal, int status) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date strDate = sdf.parse(DateUtils.convertUnixTimeStamp("dd-MM-yyyy", tanggal));
            if (System.currentTimeMillis() >= strDate.getTime()) {
                if (!PrefUtil.getIsDoneVerifikasi(context)) {
                    view.enableBtnKehadiran(true);

                } else {
                    view.enableBtnKehadiran(false);
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void checkIsNilai() {
        if (PrefUtil.getIsSubmitNilai(context, PrefUtil.getIdSkripsiTemp(context))) {
            if (PrefUtil.getIsKetua(context).equalsIgnoreCase("0")) {
                view.goToSummary();

            } else {
                view.goToMonitor();
            }
        } else {
            view.goToPenilaian();
        }
    }

    @Override
    public void savePeran(String isKetua, String isPenguji) {
        PrefUtil.setIsKetua(context, isKetua);
        PrefUtil.setIsPenguji(context, isPenguji);
    }

}
