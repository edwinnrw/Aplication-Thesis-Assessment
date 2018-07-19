package skripsi.edwin.filkomapps.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by EDN on 3/6/2018.
 */

public class DateUtils {
    public  static String  convertUnixTimeStamp(String pattern,long timestamp){
        Date date = new Date(timestamp*1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat(pattern); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Indonesian")); // give a timezone reference for formating (see comment at the bottom
        String formattedDate = sdf.format(date);
        return  formattedDate;


    }
    public static String getDay(String day){
        String dayIndo="";
        switch (day){
            case "Sunday":
                dayIndo="Minggu";
                break;
            case "Monday":
                dayIndo="Senin";
                break;
            case "Tuesday":
                dayIndo="Selasa";
                break;
            case "Wednesday":
                dayIndo="Rabu";
                break;
            case "Thursday":
                dayIndo="Kamis";
                break;
            case "Friday":
                dayIndo="Jumat";
                break;
            case "Saturday":
                dayIndo="Sabtu";
                break;
            default:

        }
        return dayIndo;
    }

}
