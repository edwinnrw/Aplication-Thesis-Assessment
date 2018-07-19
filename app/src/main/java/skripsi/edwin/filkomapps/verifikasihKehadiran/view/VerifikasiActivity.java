package skripsi.edwin.filkomapps.verifikasihKehadiran.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.beritaAcaraResechedule.view.BeritaAcaraReschedule;
import skripsi.edwin.filkomapps.penilaian.view.PenilaianActivity;
import skripsi.edwin.filkomapps.verifikasihKehadiran.presenter.VerifikasiContract;
import skripsi.edwin.filkomapps.verifikasihKehadiran.presenter.VerifikasiPresenter;

public class VerifikasiActivity extends AppCompatActivity implements VerifikasiContract.View, SearchDialog.onDialogResult {
    @BindView(R.id.txt_mahasiswa)
    TextView txtMahasiswa;
    @BindView(R.id.radio_g_mahasiswa)
    RadioGroup rgMahasiswa;
    @BindView(R.id.txt_pembimbing1)
    TextView txtPembimbing1;
    @BindView(R.id.radio_g_pembimbing1)
    RadioGroup rgPembimbing1;
    @BindView(R.id.txt_pembimbing2)
    TextView txtPembimbing2;
    @BindView(R.id.radio_g_pembimbing2)
    RadioGroup rgPembimbing2;
    @BindView(R.id.txt_penguji1)
    TextView txtPenguji1;
    @BindView(R.id.radio_g_penguji1)
    RadioGroup rgPenguji1;
    @BindView(R.id.txt_penguji2)
    TextView txtPenguji2;
    @BindView(R.id.radio_g_penguji2)
    RadioGroup rgPenguji2;
    @BindView(R.id.btn_verifikasi)
    Button btnVerifikasi;
    @BindView(R.id.layout_pembimbing2)
    LinearLayout layoutPembimbing2;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ln_dosen_pengganti)
    LinearLayout lnDosenPenguji;
    @BindView(R.id.radio_g_penguji3)
    RadioGroup rgPenguji3;
    @BindView(R.id.edt_search_dosen)
    EditText editTextSearch;

    private VerifikasiPresenter verifikasiPresenter;
    private  List<HashMap<String,String>>dataDosen;

    private SearchDialog searchDialog;
    private ProgressDialog progressDialog;
    boolean updatePengganti=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifikasi);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Verifikasi Kehadiran");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        dataDosen=(List<HashMap<String,String>>)getIntent().getSerializableExtra("dataDosen");
        searchDialog=new SearchDialog(this,R.style.AppTheme_NoActionBar,dataDosen);

        txtMahasiswa.setText(getIntent().getStringExtra("mahasiswa"));
        verifikasiPresenter=new VerifikasiPresenter(this,this,dataDosen);
        setDosen();
        searchDialog.setDialogResult(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

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
    public void setDosen(){
       for (int i=0; i<dataDosen.size(); i++){
           switch (dataDosen.get(i).get("peran")){
               case "pembimbing1":
                   txtPembimbing1.setText(dataDosen.get(i).get("nama"));
                   break;
               case "pembimbing2":
                   txtPembimbing2.setText(dataDosen.get(i).get("nama"));
                   break;
               case "penguji1":
                   txtPenguji1.setText(dataDosen.get(i).get("nama"));
                   break;
               case "penguji2":
                   txtPenguji2.setText(dataDosen.get(i).get("nama"));
                   break;
           }
       }
    }

    @OnClick(R.id.btn_verifikasi)
    public void onClickVerifikasi(){
        final MaterialDialog materialDialog=new MaterialDialog(this);
        materialDialog.setMessage("Apakah Anda Yakin?")
                .setPositiveButton("Ya", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDialog.dismiss();
                        verifikasiPresenter.validateIsChecked(rgMahasiswa.getCheckedRadioButtonId(),
                                rgPembimbing1.getCheckedRadioButtonId(),
                                rgPembimbing2.getCheckedRadioButtonId(),rgPenguji1.getCheckedRadioButtonId(),
                                rgPenguji2.getCheckedRadioButtonId());                    }
                })
                .setNegativeButton("Tidak", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDialog.dismiss();
                    }
                }).show();


    }
    @OnClick({R.id.radio_hadir_mahasiswa,R.id.radio_tdkhadir_mahasiswa})
    public void onRbClickMahasiswa(RadioButton radioButton){
        if (radioButton.getId()==R.id.radio_hadir_mahasiswa){
            rgPembimbing1.setVisibility(View.VISIBLE);
            rgPembimbing2.setVisibility(View.VISIBLE);
            rgPenguji1.setVisibility(View.VISIBLE);
            rgPenguji2.setVisibility(View.VISIBLE);
        }else{
            rgPembimbing1.setVisibility(View.GONE);
            rgPembimbing2.setVisibility(View.GONE);
            rgPenguji1.setVisibility(View.GONE);
            rgPenguji2.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.radio_hadir_pembimbing1,R.id.radio_tdkhadir_pembimbing1})
    public void onRbClickPembimbing1(RadioButton radioButton){

        if (radioButton.getId()==R.id.radio_hadir_pembimbing1){
            verifikasiPresenter.setKehadiranDosenPembimbing1("1");
        }else{
            verifikasiPresenter.setKehadiranDosenPembimbing1("0");
        }
    }
    @OnClick({R.id.radio_hadir_pembimbing2,R.id.radio_tdkhadir_pembimbing2})
    public void onRbClickPembimbing2(RadioButton radioButton){

        if (radioButton.getId()==R.id.radio_hadir_pembimbing2){
            verifikasiPresenter.setKehadiranDosenPembimbing2("1");
        }else{
            verifikasiPresenter.setKehadiranDosenPembimbing2("0");
        }
    }
    @OnClick({R.id.radio_hadir_penguji1,R.id.radio_tdkhadir_penguji1})
    public void onRbClickPenguji1(RadioButton radioButton){
        if (radioButton.getId()==R.id.radio_hadir_penguji1){
            verifikasiPresenter.setKehadiranDosenPenguji1("1");
        }else{
            verifikasiPresenter.setKehadiranDosenPenguji1("1");
        }
    }
    @OnClick({R.id.radio_hadir_penguji2,R.id.radio_tdkhadir_penguji2})
    public void onRbClickPenguji2(RadioButton radioButton){
        if (radioButton.getId()==R.id.radio_hadir_penguji2){
            verifikasiPresenter.setKehadiranDosenPenguji2("1",false);
            lnDosenPenguji.setVisibility(View.GONE);
            updatePengganti=false;

        }else{
            updatePengganti=true;
            lnDosenPenguji.setVisibility(View.VISIBLE);

        }
    }
    @OnClick({R.id.radio_hadir_penguji3,R.id.radio_tdkhadir_penguji3})
    public void onRBPengujiPengganti(RadioButton radioButton){
        if (radioButton.getId()==R.id.radio_hadir_penguji3){
            editTextSearch.setVisibility(View.VISIBLE);

        }else {
            editTextSearch.setVisibility(View.GONE);

        }
    }
    @OnClick(R.id.edt_search_dosen)
    public void  searchClick(){
        searchDialog.show();
    }

    @Override
    public void validateSuccess(List<HashMap<String, String>> kehadiran) {
        if (rgMahasiswa.getCheckedRadioButtonId()==R.id.radio_hadir_mahasiswa){
            verifikasiPresenter.verifikasiProcess();
        }else{
            Intent intent=new Intent(VerifikasiActivity.this, BeritaAcaraReschedule.class);
            intent.putExtra("nama_mahasiswa",txtMahasiswa.getText().toString());
            intent.putExtra("nim",getIntent().getStringExtra("nim"));
            startActivity(intent);
        }
    }

    @Override
    public void showWaitingConfirm() {
        verifikasiPresenter.saveDosen();
        final MaterialDialog materialDialog=new MaterialDialog(this);
        materialDialog.setMessage("Verifikasi Berhasil.Silahkan Tunggu Konfirmasi Sekjur Atas Pergantian Penguji");
        materialDialog.setPositiveButton("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDialog.dismiss();
                onBackPressed();

            }
        });
        materialDialog.show();
    }

    @Override
    public void validateFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showVerifikasiFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showVerifikasiSuccess() {

        verifikasiPresenter.saveDosen();
        final MaterialDialog materialDialog=new MaterialDialog(this);
        materialDialog.setMessage("Verifikasi Berhasil.Silahkan Lakukan Penilaian");
        materialDialog.setPositiveButton("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDialog.dismiss();
                Intent intent=new Intent(VerifikasiActivity.this, PenilaianActivity.class);
                intent.putExtra("status","1");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });
        materialDialog.setNegativeButton("Nanti", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDialog.dismiss();
                onBackPressed();
            }
        });
        materialDialog.show();
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
    public void finish(String name, String id) {
        verifikasiPresenter.setNamaDosenPengujiPengganti(name,id);
        editTextSearch.setText(name);
    }
}
