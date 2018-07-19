package skripsi.edwin.filkomapps.penilaian;

import android.content.Context;

import skripsi.edwin.filkomapps.R;

public class DeskripsiPenilaianKomponen3 {
    Context context;

    public DeskripsiPenilaianKomponen3(Context context) {
        this.context = context;
    }

    public String getDeskripsiKeterangan1(int progress) {
        String desc1[] = context.getResources().getStringArray(R.array.desc3_1_1);
        return  desc1[progress-1];


    }

    public String getDeskripsiKeterangan2(int progress) {
        String desc2[]=context.getResources().getStringArray(R.array.desc3_2_2);
        return  desc2[progress-1];


    }


    public String getDeskripsiKeterangan3(int progress) {
        String desc3[]=context.getResources().getStringArray(R.array.desc3_3_3);
        return  desc3[progress-1];


    }

    public String getDeskripsiKeterangan4(int progress) {
        String desc4[]=context.getResources().getStringArray(R.array.desc3_4_4);
        return  desc4[progress-1];


    }
    public String getDeskripsiKeterangan5(int progress) {
        String desc4[]=context.getResources().getStringArray(R.array.desc3_5_5);
        return  desc4[progress-1];


    }
    public String getDeskripsiKeterangan6(int progress) {
        String desc4[]=context.getResources().getStringArray(R.array.desc3_6_6);
        return  desc4[progress-1];


    }

}
