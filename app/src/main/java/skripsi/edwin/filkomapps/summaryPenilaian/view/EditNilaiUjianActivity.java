package skripsi.edwin.filkomapps.summaryPenilaian.view;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.model.orm.Nilai;
import skripsi.edwin.filkomapps.summaryPenilaian.presenter.EditContract;
import skripsi.edwin.filkomapps.summaryPenilaian.presenter.EditPresenter;

public class EditNilaiUjianActivity extends AppCompatActivity implements EditContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txt_indikator)
    TextView txtIndikator;
    @BindView(R.id.txt_nilai)
    TextView txtNilai;
    @BindView(R.id.txt_deskripsi)
    TextView txtDeskripsi;
    EditPresenter editPresenter;
    String idKomponen,idIndikator,nilai;
    int pos;
    HashMap<String,String>nilaiUpdate;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_nilai);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        nilaiUpdate=new HashMap<>();
        editPresenter=new EditPresenter(this,this);
        idIndikator=getIntent().getStringExtra("idIndikator");
        idKomponen=getIntent().getStringExtra("idKomponen");
        nilai=getIntent().getStringExtra("nilai");
        nilaiUpdate.put("nilai",nilai);
        pos=getIntent().getIntExtra("posisiIndikator",0);
        txtIndikator.setText(getIntent().getStringExtra("namaIndikator"));
        editPresenter.getNilaiTemp(nilai);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
    }

    @Override
    public void setDeskripsi(String deskripsi) {
        txtDeskripsi.setText(deskripsi);
    }

    @Override
    public void setNilai(String nilai) {
        txtNilai.setText(nilai);
    }

    @Override
    public void setNilaiTemp(String nilaiTemp, int rbNilai1) {
        txtNilai.setText(nilaiTemp);

        RadioButton radioButton = findViewById(rbNilai1);
        radioButton.setChecked(true);
        editPresenter.getDeskripsi(idKomponen,Integer.parseInt(nilai),pos);
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
    public void updateFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateSuccess() {
        Toast.makeText(this, "Update Nilai Berhasil", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @OnClick({R.id.rbNilai1,R.id.rbNilai2,R.id.rbNilai3,R.id.rbNilai4,R.id.rbNilai5,
            R.id.rbNilai6,R.id.rbNilai7})
    public void onClickNilai(RadioButton radioButton){
        editPresenter.getDeskripsi(idKomponen,Integer.parseInt(radioButton.getText().toString()),pos);
        editPresenter.getNilai(radioButton.getText().toString());
        nilaiUpdate.put("nilai",radioButton.getText().toString());


    }
    @OnClick(R.id.btnUpdate)
    public void onClickUpdate(){
        final MaterialDialog materialDialog=new MaterialDialog(this);
        materialDialog.setMessage("Apakah Anda Yakin?")
                .setPositiveButton("Ya", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDialog.dismiss();
                        editPresenter.saveNilai(idIndikator,idKomponen,nilaiUpdate);


                    }
                })
                .setNegativeButton("Tidak", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDialog.dismiss();
                    }
                }).show();
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
