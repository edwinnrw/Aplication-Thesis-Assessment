package skripsi.edwin.filkomapps.penilaian;

import android.content.Context;
import android.widget.Toast;

import skripsi.edwin.filkomapps.R;

public class DeskripsiPenilaianKomponen1 {
    Context context;

    public DeskripsiPenilaianKomponen1(Context context) {
        this.context = context;
    }

    public String getDeskripsiKeterangan1(int progress) {
        String desc1[] = context.getResources().getStringArray(R.array.desc1_1_1);
        return  desc1[progress-1];

    }

    public String getDeskripsiKeterangan2(int progress) {
        String desc2[]=context.getResources().getStringArray(R.array.desc1_2_2);
        return  desc2[progress-1];

    }


    public String getDeskripsiKeterangan3(int progress) {
        String desc3[]=context.getResources().getStringArray(R.array.desc1_3_3);
        return  desc3[progress-1];

    }

    public String getDeskripsiKeterangan4(int progress) {
        String desc4[]=context.getResources().getStringArray(R.array.desc1_4_4);
        return  desc4[progress-1];


    }
}
