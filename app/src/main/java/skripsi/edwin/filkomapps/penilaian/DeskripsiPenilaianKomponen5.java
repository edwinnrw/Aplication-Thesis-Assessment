package skripsi.edwin.filkomapps.penilaian;

import android.content.Context;

import skripsi.edwin.filkomapps.R;

public class DeskripsiPenilaianKomponen5 {
    Context context;
    public DeskripsiPenilaianKomponen5(Context context) {
        this.context = context;
    }

    public String getDeskripsiKeterangan1(int progress) {
        String desc1[] = context.getResources().getStringArray(R.array.desc5_1_1);
        return  desc1[progress-1];

    }

    public String getDeskripsiKeterangan2(int progress) {
        String desc2[]=context.getResources().getStringArray(R.array.desc5_2_2);
        return  desc2[progress-1];


    }


    public String getDeskripsiKeterangan3(int progress) {
        String desc3[]=context.getResources().getStringArray(R.array.desc5_3_3);
        return  desc3[progress-1];


    }


}
