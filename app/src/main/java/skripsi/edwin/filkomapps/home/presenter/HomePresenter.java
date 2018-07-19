package skripsi.edwin.filkomapps.home.presenter;

import android.content.Context;
import android.os.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.edwin.filkomapps.api.EndpointAPI;
import skripsi.edwin.filkomapps.api.ServiceGenerator;
import skripsi.edwin.filkomapps.helper.UserHelper;
import skripsi.edwin.filkomapps.model.GetJadwalUjianResponse;
import skripsi.edwin.filkomapps.model.orm.User;
import skripsi.edwin.filkomapps.preference.PrefUtil;


/**
 * Created by EDN on 1/15/2018.
 */

public class HomePresenter implements HomeContract.Presenter {
    Context context;
    HomeContract.View view;
    User user;
    Handler handler;

    public HomePresenter(final Context context, HomeContract.View view) {
        this.context = context;
        this.view = view;
        this.handler=new Handler();
        this.user= UserHelper.getUser(context);

    }

    @Override
    public void getJadwal() {
        view.showProress();

        ServiceGenerator.createService(EndpointAPI.class,user.getApiToken()).getJadwalToday().enqueue(new Callback<GetJadwalUjianResponse>() {
            @Override
            public void onResponse(Call<GetJadwalUjianResponse> call, final Response<GetJadwalUjianResponse> response) {
                view.dismisProgrss();
                if (response.code()==200){
                    view.setJadwal(response.body().getData());
                }
                if (response.code()==404){
                    view.setEmptyJadwal();
                }
            }

            @Override
            public void onFailure(Call<GetJadwalUjianResponse> call, Throwable t) {
                view.dismisProgrss();
                view.showMessage(t.getMessage());
            }
        });

    }

    @Override
    public void isNotif() {
        if (PrefUtil.haveNewNotif(context)){
            view.showBadge();
        }else{
            view.hideBadge();

        }
    }
}
