package skripsi.edwin.filkomapps.verifikasihKehadiran.presenter;

import android.content.Context;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.edwin.filkomapps.api.EndpointAPI;
import skripsi.edwin.filkomapps.api.ServiceGenerator;
import skripsi.edwin.filkomapps.model.GetDosenResponse;

public class DialogPresenter implements DialogContract.Presenter {
    Context context;
    DialogContract.View view;

    public DialogPresenter(Context context, DialogContract.View view) {
        this.context = context;
        this.view = view;
    }



    @Override
    public void getListDosen(List<HashMap<String, String>> dataDosen) {
        HashMap<String,String>param=new HashMap<>();

        for (int i=0; i<dataDosen.size(); i++){
            switch (dataDosen.get(i).get("peran")){
                case "pembimbing1":
                    param.put("id_pembimbing1",dataDosen.get(i).get("id_dosen"));
                    break;
                case "pembimbing2":
                    param.put("id_pembimbing2",dataDosen.get(i).get("id_dosen"));
                    break;
                case "penguji1":
                    param.put("id_penguji1",dataDosen.get(i).get("id_dosen"));
                    break;
                case "penguji2":
                    param.put("id_penguji2",dataDosen.get(i).get("id_dosen"));
                    break;
            }
        }
        view.showProgress();
        ServiceGenerator.createService(EndpointAPI.class).getDosen(param).enqueue(new Callback<GetDosenResponse>() {
            @Override
            public void onResponse(Call<GetDosenResponse> call, Response<GetDosenResponse> response) {
                view.dismissProgress();
                if (response.code()==200){
                    view.showListDosen(response.body().getList_dosen());
                }else{
                    view.showMessageError(response.message());
                }
            }

            @Override
            public void onFailure(Call<GetDosenResponse> call, Throwable t) {
                view.dismissProgress();
                view.showMessageError(t.getMessage());

            }
        });
    }
}
