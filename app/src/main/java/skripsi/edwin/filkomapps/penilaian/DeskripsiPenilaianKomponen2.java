package skripsi.edwin.filkomapps.penilaian;

import android.content.Context;

import skripsi.edwin.filkomapps.R;

public class DeskripsiPenilaianKomponen2 {
    Context context;

    public DeskripsiPenilaianKomponen2(Context context) {
        this.context = context;
    }

    public String getDeskripsiKeterangan1(int progress) {
        String desc1[] = context.getResources().getStringArray(R.array.desc2_1_1);
        return  desc1[progress-1];


    }

    public String getDeskripsiKeterangan2(int progress) {
        String desc2[]=context.getResources().getStringArray(R.array.desc2_2_2);
        return  desc2[progress-1];


    }


    public String getDeskripsiKeterangan3(int progress) {
        String desc3[]=context.getResources().getStringArray(R.array.desc2_3_3);
        return  desc3[progress-1];


    }

    public String getDeskripsiKeterangan4(int progress) {
        String desc4[]=context.getResources().getStringArray(R.array.desc2_4_4);
        return  desc4[progress-1];


    }
}

