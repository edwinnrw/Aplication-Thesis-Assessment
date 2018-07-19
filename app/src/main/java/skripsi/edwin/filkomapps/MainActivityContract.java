package skripsi.edwin.filkomapps;

/**
 * Created by EDN on 3/6/2018.
 */

public class MainActivityContract {
    public interface View{

        void setNavProfile(String name, String nik);
        void logoutSuccsess();
        void logoutFail(String message);
        void showProgress();
        void dismisProgress();
    }
    public  interface Presenter{
        void getDataProfile();
        void logout();
    }
}
