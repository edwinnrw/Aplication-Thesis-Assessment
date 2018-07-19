package skripsi.edwin.filkomapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import skripsi.edwin.filkomapps.model.orm.StatusKelulusan;

public class GetSaveResponse {
    @SerializedName("success")
    @Expose
    private  Boolean success;

    @SerializedName("message")
    @Expose
    private   String message;
    @SerializedName("data")
    @Expose
    private List<StatusKelulusan> data;

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

    public List<StatusKelulusan> getData() {
        return data;
    }

    public void setData(List<StatusKelulusan> data) {
        this.data = data;
    }
}
