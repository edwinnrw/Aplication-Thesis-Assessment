package skripsi.edwin.filkomapps.login.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import skripsi.edwin.filkomapps.MainActivity;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.login.presenter.LoginContract;
import skripsi.edwin.filkomapps.login.presenter.LoginPresenter;
import skripsi.edwin.filkomapps.model.orm.User;
import skripsi.edwin.filkomapps.preference.PrefUtil;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    @BindView(R.id.btn_sign)
    public Button buttonSignin;
    @BindView(R.id.edt_username)
    public EditText editTextUsername;
    @BindView(R.id.edt_password)
    public EditText editTextPassword;
    LoginPresenter loginPresenter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter=new LoginPresenter(this,this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
    }

    @OnClick(R.id.btn_sign)
    public void loginClick(){
        loginPresenter.validation(editTextUsername,editTextPassword);
    }

    @Override
    public void loginSuccess(User data) {
        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissProgressBar() {
        progressDialog.dismiss();
    }

    @Override
    public void showProgressBar() {
        progressDialog.show();
    }

    @Override
    public void validationFail(List<EditText> editTexts) {
        for (int i=0; i<editTexts.size(); i++){
            editTexts.get(i).setError("Wajib Diisi");

        }
    }

    @Override
    public void validationSuccess() {
        HashMap<String,String> param=new HashMap<>();
        param.put("email",editTextUsername.getText().toString());
        param.put("password",editTextPassword.getText().toString());
        param.put("firebase_token",FirebaseInstanceId.getInstance().getToken());
        loginPresenter.doLogin(param);
    }
}
