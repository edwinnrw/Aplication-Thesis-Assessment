package skripsi.edwin.filkomapps.summaryPenilaian.presenter;

import android.content.Context;
import android.text.TextUtils;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.edwin.filkomapps.api.EndpointAPI;
import skripsi.edwin.filkomapps.api.ServiceGenerator;
import skripsi.edwin.filkomapps.helper.PenilaianHelper;
import skripsi.edwin.filkomapps.helper.UserHelper;
import skripsi.edwin.filkomapps.model.GetSaveResponse;
import skripsi.edwin.filkomapps.model.orm.Nilai;
import skripsi.edwin.filkomapps.model.orm.NilaiSaya;
import skripsi.edwin.filkomapps.model.orm.User;
import skripsi.edwin.filkomapps.preference.PrefUtil;

public class EditPublikasiPresenter implements EditPublikasiContract.Presenter {
    EditPublikasiContract.View view;
    Context context;
    User user;

    public EditPublikasiPresenter(EditPublikasiContract.View view, final Context context) {
        this.view = view;
        this.context = context;
        this.user= UserHelper.getUser(context);
    }

    @Override
    public void getNilaiIndikator(NilaiSaya nilaiSaya) {
        view.setIndikator(nilaiSaya.idIndikator,nilaiSaya.indikator,nilaiSaya.keterangan,
                String.valueOf(nilaiSaya.nilai));
    }

    @Override
    public void validationIsComplete(String input) {
        if (TextUtils.isEmpty(input)){
            view.validationError();
        }else {
            view.validationComplete();
        }
    }



    @Override
    public void saveNilai(final NilaiSaya indikator, final String editTexts) {
        view.showProgress();
        HashMap<String,String>param=new HashMap<>();
        param.put("id_ujian", PrefUtil.getIdUjianTemp(context));
        param.put("id_skripsi", PrefUtil.getIdSkripsiTemp(context));
        param.put("id_penilai",user.getId());
        param.put("is_ketua",PrefUtil.getIsKetua(context));
        param.put("id_indikator",indikator.idIndikator);
        param.put("id_komponen",indikator.idKomponen);
        param.put("nilai",editTexts);
        ServiceGenerator.createService(EndpointAPI.class,user.getApiToken()).updateNilai(param).enqueue(new Callback<GetSaveResponse>() {
            @Override
            public void onResponse(Call<GetSaveResponse> call, Response<GetSaveResponse> response) {
                view.dismissProgress();
                if (response.code()==200){
                    Nilai nilaiSave=new Nilai();
                    nilaiSave.setIndikatorId(indikator.idIndikator);
                    nilaiSave.setIdDosen(user.getId());
                    nilaiSave.setNilai(Integer.parseInt(editTexts));
                    PenilaianHelper.updateNilai(context,nilaiSave);
                    view.showUpdateSuccess(editTexts);
                }else {
                    view.showUpdateFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<GetSaveResponse> call, Throwable t) {
                view.dismissProgress();
                view.showUpdateFail(t.getMessage());
            }
        });

    }


}
