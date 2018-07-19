package skripsi.edwin.filkomapps.beritaAcaraResechedule.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;
import skripsi.edwin.filkomapps.MainActivity;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.beritaAcaraResechedule.presenter.BeritaAcaraContract;
import skripsi.edwin.filkomapps.beritaAcaraResechedule.presenter.BeritaAcaraPresenter;
import skripsi.edwin.filkomapps.util.CustomArrayAdapater;
import skripsi.edwin.filkomapps.util.CustomArrayAdapaterAlasan;

public class BeritaAcaraReschedule extends AppCompatActivity implements BeritaAcaraContract.View {
    @BindView(R.id.spiner_alasan)
    Spinner spinner;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.txt_mahasiswa)
    TextView txtMahasiswa;
    @BindView(R.id.txt_nim)
    TextView txtNim;
    @BindView(R.id.txt_catatan_khusus)
    TextView txtCatatan;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    BeritaAcaraPresenter beritaAcaraPresenter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita_acara_reschedule);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Berita Acara");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        CustomArrayAdapaterAlasan customSpinnerAdapter=new
                CustomArrayAdapaterAlasan(this,getResources().getStringArray(R.array.alasan));
        spinner.setAdapter(customSpinnerAdapter);
        txtMahasiswa.setText(getIntent().getStringExtra("nama_mahasiswa"));
        txtNim.setText(getIntent().getStringExtra("nim"));
        beritaAcaraPresenter=new BeritaAcaraPresenter(this,this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
    }
    @OnClick(R.id.btn_submit)
    public void onClickSubmit(){
        beritaAcaraPresenter.validationField(spinner.getSelectedItem().toString());

    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void dismisProgress() {
        progressDialog.dismiss();

    }

    @Override
    public void submitFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void submitSuccess() {
        final MaterialDialog materialDialog=new MaterialDialog(this);
        materialDialog.setMessage("Berita Acara Berhasil Disimpan");
        materialDialog.setPositiveButton("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDialog.dismiss();
                Intent intent=new Intent(BeritaAcaraReschedule.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });
        materialDialog.show();
    }

    @Override
    public void validationFieldError() {
        Toast.makeText(this, "Anda Belum Memilih Alasan", Toast.LENGTH_LONG).show();
    }

    @Override
    public void validationFieldSuccess() {
        beritaAcaraPresenter.submitBeritaAcara(spinner.getSelectedItem().toString(),txtCatatan.getText().toString());

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
