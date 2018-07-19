package skripsi.edwin.filkomapps.login.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.edwin.filkomapps.api.EndpointAPI;
import skripsi.edwin.filkomapps.api.ServiceGenerator;
import skripsi.edwin.filkomapps.dbPersistance.AppDatabase;
import skripsi.edwin.filkomapps.helper.UserHelper;
import skripsi.edwin.filkomapps.model.GetLoginResponse;
import skripsi.edwin.filkomapps.preference.PrefUtil;

public class LoginPresenter implements LoginContract.Presenter {
    Context context;
    LoginContract.View view;

    public LoginPresenter(Context context, LoginContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void doLogin(HashMap<String, String> param) {
        view.showProgressBar();
        ServiceGenerator.createService(EndpointAPI.class).login(param).enqueue(new Callback<GetLoginResponse>() {
            @Override
            public void onResponse(Call<GetLoginResponse> call, final Response<GetLoginResponse> response) {
                view.dismissProgressBar();
                if (response.code() == 200) {
                    PrefUtil.setIsLogin(context, true);
                    UserHelper.insertUser(context, response.body().getData());
                    view.loginSuccess(response.body().getData());

                } else {
                    view.loginFailed("Akun Tidak Ditemukan");

                }


            }

            @Override
            public void onFailure(Call<GetLoginResponse> call, Throwable t) {
                view.dismissProgressBar();
                view.loginFailed("Error: " + t.getMessage());

            }
        });
    }

    @Override
    public void validation(EditText editText1, EditText editText2) {
        List<EditText> editTextsError = new ArrayList<>();
        if (editText1.getText().toString().length() == 0 || editText2.getText().toString().length() == 0) {
            if (editText1.getText().toString().length() == 0) {
                editTextsError.add(editText1);
                view.validationFail(editTextsError);
            }
            if (editText2.getText().toString().length() == 0) {
                editTextsError.add(editText2);
                view.validationFail(editTextsError);
            }

        } else {

            view.validationSuccess();
        }
    }

}
