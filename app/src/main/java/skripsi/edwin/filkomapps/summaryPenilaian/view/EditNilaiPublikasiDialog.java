package skripsi.edwin.filkomapps.summaryPenilaian.view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.model.orm.Indikator;
import skripsi.edwin.filkomapps.model.orm.NilaiSaya;
import skripsi.edwin.filkomapps.summaryPenilaian.presenter.EditPublikasiContract;
import skripsi.edwin.filkomapps.summaryPenilaian.presenter.EditPublikasiPresenter;
import skripsi.edwin.filkomapps.util.InputFilterMinMax;

public class EditNilaiPublikasiDialog extends Dialog implements EditPublikasiContract.View {

    @BindView(R.id.txt_indikator)
    TextView txtIndikator;
    @BindView(R.id.txt_keterangan)
    TextView txtKeterangan;
    @BindView(R.id.edit_nilai)
    EditText editTextNilai;
    EditPublikasiPresenter editPresenter;
    Context context;
    NilaiSaya indikator;
    ProgressDialog progressDialog;
    onDialogResult onDialogResult;
    public EditNilaiPublikasiDialog(Context context, NilaiSaya indikator) {
        super(context);
        this.context=context;
        this.indikator=indikator;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_publikasi_dialog);
        this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ButterKnife.bind(this);
        editPresenter=new EditPublikasiPresenter(this,context);
        editPresenter.getNilaiIndikator(indikator);
        editTextNilai.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "100")});
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Loading...");

    }
    @OnClick(R.id.txtCancel)
    public  void  onClickCancel(){
        dismiss();
    }
    @OnClick(R.id.txtUpdate)
    public  void  onClickUpdate(){
        editPresenter.validationIsComplete(editTextNilai.getText().toString());
    }

    @Override
    public void setIndikator(String id, String indikator, String ket, String nilai) {
        txtIndikator.setText(indikator);
        txtKeterangan.setText(ket);
        editTextNilai.setText(nilai);
    }

    @Override
    public void validationComplete() {
        editPresenter.saveNilai(indikator,editTextNilai.getText().toString());

    }

    @Override
    public void validationError() {
        editTextNilai.setError("Wajib Diisi");

    }

    @Override
    public void showProgress() {
        progressDialog.show();

    }

    @Override
    public void dismissProgress() {
        progressDialog.dismiss();

    }

    @Override
    public void showUpdateFail(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showUpdateSuccess(String nilai) {
        dismiss();
        onDialogResult.finish(nilai);

        Toast.makeText(context,"Update Nilai Berhasil", Toast.LENGTH_SHORT).show();

    }
    public void setDialogResult(onDialogResult dialogResult){
        this.onDialogResult = dialogResult;
    }
    public interface onDialogResult{
        void finish(String nilai);
    }
}
