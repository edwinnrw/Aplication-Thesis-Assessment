package skripsi.edwin.filkomapps;

import android.content.Context;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.edwin.filkomapps.api.EndpointAPI;
import skripsi.edwin.filkomapps.api.ServiceGenerator;
import skripsi.edwin.filkomapps.helper.UserHelper;
import skripsi.edwin.filkomapps.model.GetLogoutResponse;
import skripsi.edwin.filkomapps.model.orm.User;
import skripsi.edwin.filkomapps.preference.PrefUtil;


/**
 * Created by EDN on 3/6/2018.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {
    Context context;
    MainActivityContract.View view;
    User user;
    public MainActivityPresenter(Context context, MainActivityContract.View view) {
        this.context = context;
        this.view = view;

    }



    @Override
    public void getDataProfile() {
        this.user = UserHelper.getUser(context);
        view.setNavProfile(user.getNama(),user.getNikNip());
    }

    @Override
    public void logout() {
        view.showProgress();
        HashMap<String,String>param=new HashMap<>();
        param.put("email",user.getEmail());
        ServiceGenerator.createService(EndpointAPI.class,user.getApiToken()).logout(param).enqueue(new Callback<GetLogoutResponse>() {
            @Override
            public void onResponse(Call<GetLogoutResponse> call, Response<GetLogoutResponse> response) {
                view.dismisProgress();
                if (response.code()==200){
                    view.logoutSuccsess();
                    UserHelper.deleteUser(context);
                    PrefUtil.setIsLogin(context,false);
                }else {
                    view.logoutFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<GetLogoutResponse> call, Throwable t) {
                view.dismisProgress();
                view.logoutFail(t.getMessage());

            }
        });

    }
}
