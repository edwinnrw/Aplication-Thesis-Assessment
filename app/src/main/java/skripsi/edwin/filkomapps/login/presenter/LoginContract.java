package skripsi.edwin.filkomapps.login.presenter;

import android.widget.EditText;

import java.util.HashMap;
import java.util.List;

import skripsi.edwin.filkomapps.model.orm.User;

public class LoginContract {
    public interface View{
        void loginSuccess(User data);
        void loginFailed(String message);
        void dismissProgressBar();
        void showProgressBar();
        void validationFail(List<EditText> editTexts);
        void validationSuccess();
    }
    public interface  Presenter{
        void doLogin(HashMap<String,String> param);
        void validation(EditText editText1, EditText editText2);
    }
}
