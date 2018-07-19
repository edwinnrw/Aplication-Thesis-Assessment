package skripsi.edwin.filkomapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDataUjianResponse {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private DataUjian data = null;

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

    public DataUjian getData() {
        return data;
    }

    public void setData(DataUjian data) {
        this.data = data;
    }
}
