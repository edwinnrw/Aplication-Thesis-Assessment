package skripsi.edwin.filkomapps.ketuaMonitor.presenter;

import android.content.Context;


import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.edwin.filkomapps.api.EndpointAPI;
import skripsi.edwin.filkomapps.api.ServiceGenerator;
import skripsi.edwin.filkomapps.helper.PenilaianHelper;
import skripsi.edwin.filkomapps.helper.UserHelper;
import skripsi.edwin.filkomapps.model.GetNilaiResponse;
import skripsi.edwin.filkomapps.model.orm.Nilai;
import skripsi.edwin.filkomapps.model.orm.User;
import skripsi.edwin.filkomapps.preference.PrefUtil;

public class KetuaMonitorPresenter implements  KetuaMonitorContract.Presenter {
    Context context;
    KetuaMonitorContract.View view;

    public KetuaMonitorPresenter(Context context, KetuaMonitorContract.View view) {
        this.context = context;
        this.view = view;

    }

    public void getNilai() {
        view.showProgress();
        User user= UserHelper.getUser(context);
        HashMap<String,String>param=new HashMap<>();
        param.put("id_ujian", PrefUtil.getIdUjianTemp(context));
        param.put("id_skripsi", PrefUtil.getIdSkripsiTemp(context));
        param.put("id_dosen",user.getId());
        ServiceGenerator.createService(EndpointAPI.class,user.getApiToken()).getNilai(param).enqueue(new Callback<GetNilaiResponse>() {
            @Override
            public void onResponse(Call<GetNilaiResponse> call, Response<GetNilaiResponse> response) {
                if (response.code()==200){
                    view.dismisProgress();
                    saveNilai(response.body().getData());
                }else {
                    view.getNilaiFail(response.message());
                }

            }

            @Override
            public void onFailure(Call<GetNilaiResponse> call, Throwable t) {
                view.dismisProgress();
                view.getNilaiFail(t.getMessage());

            }
        });

    }

    private void saveNilai(List<Nilai> data) {
        PrefUtil.setOnceGetNilai(context);
        for (int i=0; i<data.size(); i++){
            PenilaianHelper.insertNilai(context,data.get(i));
        }
    }

    ///mendapatkan nilai yang belum masuk,kerana adanya pecegahan nilai masuk sebelum ujian berlangsung
    //contoh: penilaian pembimbimbing ketika tidak hadir
    @Override
    public void checkGetNilai() {
        if (!PrefUtil.getOnceNilai(context)){
            getNilai();
        }
    }
}
