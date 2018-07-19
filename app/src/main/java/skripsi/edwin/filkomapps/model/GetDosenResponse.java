package skripsi.edwin.filkomapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDosenResponse {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Dosen>list_dosen;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Dosen> getList_dosen() {
        return list_dosen;
    }

    public void setList_dosen(List<Dosen> list_dosen) {
        this.list_dosen = list_dosen;
    }
}
