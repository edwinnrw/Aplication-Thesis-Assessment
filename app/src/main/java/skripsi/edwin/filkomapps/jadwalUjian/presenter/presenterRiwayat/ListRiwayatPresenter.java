package skripsi.edwin.filkomapps.jadwalUjian.presenter.presenterRiwayat;

import android.content.Context;


import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.edwin.filkomapps.api.EndpointAPI;
import skripsi.edwin.filkomapps.api.ServiceGenerator;
import skripsi.edwin.filkomapps.helper.UserHelper;
import skripsi.edwin.filkomapps.model.GetJadwalUjianResponse;
import skripsi.edwin.filkomapps.model.orm.User;
import skripsi.edwin.filkomapps.preference.PrefUtil;

public class ListRiwayatPresenter implements ListRiwayatContract.Presenter {
    Context context;
    ListRiwayatContract.View view;
    User user;
    HashMap<String, String> param;

    public ListRiwayatPresenter(Context context, ListRiwayatContract.View view) {
        this.context = context;
        this.view = view;
        this.param = new HashMap<>();
        this.user = UserHelper.getUser(context);
    }

    @Override
    public void getJadwal() {
        param.put("is_complete", "1");
        param.put("is_penguji", "1");
        param.put("is_pembimbing", "1");
        param.put("is_ketua", "1");
        view.showProgress();
        ServiceGenerator.createService(EndpointAPI.class, user.getApiToken()).getJadwal(param)
                .enqueue(new Callback<GetJadwalUjianResponse>() {
                    @Override
                    public void onResponse(Call<GetJadwalUjianResponse> call, Response<GetJadwalUjianResponse> response) {
                        view.dismisProgress();
                        if (response.code() == 200) {
                            if (response.body().getData().size() > 0) {
                                view.setJadwal(response.body().getData());

                            } else {
                                view.setEmptyView();
                            }

                        } else {
                            view.setEmptyView();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetJadwalUjianResponse> call, Throwable t) {
                        view.dismisProgress();
                        view.showMessage(t.getMessage());
                    }
                });
    }

    @Override
    public void filterJadwal(List<String> peran, String s) {
        view.showProgress();
        param.put("nama", s);
        if (peran.size() > 0) {
            param.put("is_penguji", "0");
            param.put("is_pembimbing", "0");
            param.put("is_ketua", "0");
            for (int i = 0; i < peran.size(); i++) {

                switch (peran.get(i)) {
                    case "Pembimbing":
                        param.put("is_pembimbing", "1");
                        break;
                    case "Penguji":
                        param.put("is_penguji", "1");
                        break;
                    case "Ketua Majelis":
                        param.put("is_ketua", "1");
                        break;
                    default:
                        break;

                }
            }
        }
        ServiceGenerator.createService(EndpointAPI.class, user.getApiToken()).getJadwal(param)
                .enqueue(new Callback<GetJadwalUjianResponse>() {
                    @Override
                    public void onResponse(Call<GetJadwalUjianResponse> call, final Response<GetJadwalUjianResponse> response) {
                        view.dismisProgress();
                        if (response.code() == 200) {
                            if (response.body().getData().size() > 0) {
                                view.setFilterJadwal(response.body().getData());
                            } else {
                                view.setEmptyView();
                            }
                        } else {
                            view.setEmptyView();
                        }


                    }

                    @Override
                    public void onFailure(Call<GetJadwalUjianResponse> call, Throwable t) {
                        view.dismisProgress();
                        view.showMessage(t.getMessage());
                    }
                });
    }
}
