package skripsi.edwin.filkomapps.beritaAcaraResechedule.presenter;

import android.content.Context;
import android.os.AsyncTask;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.edwin.filkomapps.api.EndpointAPI;
import skripsi.edwin.filkomapps.api.ServiceGenerator;
import skripsi.edwin.filkomapps.dbPersistance.AppDatabase;
import skripsi.edwin.filkomapps.model.GetBeritaAcaraResponse;
import skripsi.edwin.filkomapps.model.orm.User;
import skripsi.edwin.filkomapps.preference.PrefUtil;

public class BeritaAcaraPresenter implements BeritaAcaraContract.Presenter {
    BeritaAcaraContract.View view;
    Context context;
    User user;

    public BeritaAcaraPresenter(BeritaAcaraContract.View view, final Context context) {
        this.view = view;
        this.context = context;
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                user = AppDatabase.dbInstance(context).userDao().getUser();

            }
        });
    }

    @Override
    public void validationField(String alasan) {
        if (alasan.equalsIgnoreCase("Pilih Alasan")) {
            view.validationFieldError();
        } else {
            view.validationFieldSuccess();
        }

    }

    @Override
    public void submitBeritaAcara(String alasan, String catatan) {
        HashMap<String, String> param = new HashMap<>();
        param.put("id_ujian", PrefUtil.getIdUjianTemp(context));
        param.put("alasan", alasan);
        param.put("catatan", catatan);
        view.showProgress();
        ServiceGenerator.createService(EndpointAPI.class, user.getApiToken()).submitReschedule(param)
                .enqueue(new Callback<GetBeritaAcaraResponse>() {
                    @Override
                    public void onResponse(Call<GetBeritaAcaraResponse> call, Response<GetBeritaAcaraResponse> response) {
                        view.dismisProgress();
                        if (response.code() == 200) {
                            view.submitSuccess();
                        } else {
                            view.submitFail(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<GetBeritaAcaraResponse> call, Throwable t) {
                        view.dismisProgress();
                        view.submitFail(t.getMessage());
                    }
                });
    }
}
