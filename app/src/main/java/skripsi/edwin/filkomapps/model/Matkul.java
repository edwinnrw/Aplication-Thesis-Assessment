package skripsi.edwin.filkomapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Matkul {
    @SerializedName("matkul")
    @Expose
    private String matkul;

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }
}
