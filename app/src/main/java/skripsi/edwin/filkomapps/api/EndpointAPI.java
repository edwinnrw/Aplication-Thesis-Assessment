package skripsi.edwin.filkomapps.api;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import skripsi.edwin.filkomapps.model.GetBeritaAcaraResponse;
import skripsi.edwin.filkomapps.model.GetDataUjianResponse;
import skripsi.edwin.filkomapps.model.GetDosenResponse;
import skripsi.edwin.filkomapps.model.GetJadwalUjianResponse;
import skripsi.edwin.filkomapps.model.GetKomponenResponse;
import skripsi.edwin.filkomapps.model.GetLoginResponse;
import skripsi.edwin.filkomapps.model.GetLogoutResponse;
import skripsi.edwin.filkomapps.model.GetNilaiResponse;
import skripsi.edwin.filkomapps.model.GetSaveResponse;
import skripsi.edwin.filkomapps.model.GetVerifikasiResponse;

public interface EndpointAPI {
    @POST("./user/login")
    Call<GetLoginResponse> login(@Body HashMap<String,String> param);
    @POST("./user/logout")
    Call<GetLogoutResponse>logout(@Body HashMap<String,String>param);
    @GET("./ujian/get_jadwal_ujian")
    Call<GetJadwalUjianResponse>getJadwal(@QueryMap Map<String, String> options);

    @GET("./ujian/get_jadwal_ujian_today")
    Call<GetJadwalUjianResponse>getJadwalToday();

    @GET("./ujian/detail_ujian")
    Call<GetDataUjianResponse>getDataUjian(@QueryMap Map<String, String> options);

    @GET("./ujian/get_dosen")
    Call<GetDosenResponse>getDosen(@QueryMap Map<String, String> options);

    @POST("./ujian/verifikasi")
    Call<GetVerifikasiResponse>verifikasi(@Body HashMap<String,Object> param);

    @POST("./ujian/save_reschedule")
    Call<GetBeritaAcaraResponse>submitReschedule(@Body HashMap<String,String> param);

    @POST("./ujian/check_akses")
    Call<GetKomponenResponse>checkAkses(@Body HashMap<String,String> param);

    @POST("./ujian/save_nilai")
    Call<GetSaveResponse>saveNilai(@Body HashMap<String,Object> param);

    @POST("./ujian/update_nilai")
    Call<GetSaveResponse>updateNilai(@Body HashMap<String,String> param);



    @POST("./ujian/save_berita_acara")
    Call<GetBeritaAcaraResponse>submitBeritaAcara(@Body HashMap<String,String> param);

    @POST("./ujian/get_nilai")
    Call<GetNilaiResponse>getNilai(@Body HashMap<String,String>param);
}
