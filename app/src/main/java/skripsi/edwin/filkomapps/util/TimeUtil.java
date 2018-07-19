package skripsi.edwin.filkomapps.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    public static String getCurrentTime(){
        DateFormat df = new SimpleDateFormat("HH:mm");
        String time = df.format(Calendar.getInstance().getTime());
        return time;
    }
    public static String geTimeAlarm(long timeAlarm){
        DateFormat df = new SimpleDateFormat("HH:mm");
        String time = df.format(timeAlarm);
        return time;
    }
    public static String getTimeFromDate(Date timeAlarm){
        DateFormat df = new SimpleDateFormat("HH:mm");
        String time = df.format(timeAlarm);
        return time;
    }
    public static long getLongTime(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            Date mDate = sdf.parse(time);
            long timeInMilliseconds = mDate.getTime();
            return timeInMilliseconds;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

    }
}
