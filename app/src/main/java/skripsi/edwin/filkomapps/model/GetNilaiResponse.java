package skripsi.edwin.filkomapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import skripsi.edwin.filkomapps.model.orm.Nilai;
import skripsi.edwin.filkomapps.model.orm.User;

public class GetNilaiResponse
{
    @SerializedName("success")
    @Expose
    private  Boolean success;

    @SerializedName("message")
    @Expose
    private   String message;

    @SerializedName("data")
    @Expose
    private List<Nilai> data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Nilai> getData() {
        return data;
    }

    public void setData(List<Nilai> data) {
        this.data = data;
    }
}
