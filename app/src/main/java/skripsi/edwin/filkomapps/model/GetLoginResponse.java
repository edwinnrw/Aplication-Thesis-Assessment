package skripsi.edwin.filkomapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import skripsi.edwin.filkomapps.model.orm.User;

public class GetLoginResponse {
    @SerializedName("success")
    @Expose
    private  Boolean success;

    @SerializedName("message")
    @Expose
    private   String message;

    @SerializedName("data")
    @Expose
    private User data;

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

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
