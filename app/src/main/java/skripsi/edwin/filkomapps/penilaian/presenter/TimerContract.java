package skripsi.edwin.filkomapps.penilaian.presenter;

public class TimerContract {
    public interface View{
        void showTimePresentasi();
        void hideTimePresentasi();
        void showTextTimeTanyaJawab();
        void hideTexTimeTanyaJawab();
        void startTimePresentasi(long mTimeMilis);
        void startTimeTanyaJawab(long mTimeMilis);
        void showLastTimerPresentasi(long mTimeMilis);
        void showLastTimerTanyaJawab(long mTimeMilis);

    }
    public interface Presenter{
        void isStartTime();
        void setTimeMilisLeft(long time);
        void onStartTimer(String title);
        void onStartBackground();
        void onFinishTimer(String presentasi);
        void onPauseTimer();
        void onStopTimer(String title);

    }
}
