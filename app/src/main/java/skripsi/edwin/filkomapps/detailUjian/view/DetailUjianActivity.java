package skripsi.edwin.filkomapps.detailUjian.view;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.detailUjian.presenter.DetailJadwalUjianContract;
import skripsi.edwin.filkomapps.detailUjian.presenter.DetailJadwalUjianPresenter;
import skripsi.edwin.filkomapps.ketuaMonitor.view.KetuaMonitorActivity;
import skripsi.edwin.filkomapps.model.BeritaAcara;
import skripsi.edwin.filkomapps.model.DataUjian;
import skripsi.edwin.filkomapps.model.JadwalUjian;
import skripsi.edwin.filkomapps.model.Mahasiswa;
import skripsi.edwin.filkomapps.model.Matkul;
import skripsi.edwin.filkomapps.model.Pembimbing;
import skripsi.edwin.filkomapps.model.Penguji;
import skripsi.edwin.filkomapps.model.Skripsi;
import skripsi.edwin.filkomapps.penilaian.view.PenilaianActivity;
import skripsi.edwin.filkomapps.summaryPenilaian.view.SummaryPenilaianActivity;
import skripsi.edwin.filkomapps.util.DateUtils;
import skripsi.edwin.filkomapps.util.GuideUtil;
import skripsi.edwin.filkomapps.verifikasihKehadiran.view.VerifikasiActivity;

public class DetailUjianActivity extends AppCompatActivity implements DetailJadwalUjianContract.View {
    @BindView(R.id.btn_menilai)
    Button btnMenilai;
    @BindView(R.id.btn_verifikasi)
    Button btnVerifikasi;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_baca_dokumen)
    Button btnBaca;
    @BindView(R.id.txt_nama)
    TextView txtNama;
    @BindView(R.id.txt_nim)
    TextView txtNim;
    @BindView(R.id.txt_jurusan)
    TextView txtJurusan;
    @BindView(R.id.txt_phone)
    TextView txtTelp;
    @BindView(R.id.txt_email)
    TextView txtEmail;
    @BindView(R.id.txt_date)
    TextView txtTanggal;
    @BindView(R.id.txt_waktu)
    TextView txtWaktu;
    @BindView(R.id.txt_tempat)
    TextView txtTempat;
    @BindView(R.id.txt_judul_skripsi)
    TextView txtJudul;
    @BindView(R.id.txt_bidang_skripsi)
    TextView txtBidang;
    @BindView(R.id.txt_tipe_penelitian)
    TextView txtTipePenelitian;
    @BindView(R.id.txt_asal_judul)
    TextView txtAsalJudul;
    @BindView(R.id.txt_matkul_piliahb)
    TextView txtMkPilihan;
    @BindView(R.id.txt_pembimbing1)
    TextView txtPembimbing1;
    @BindView(R.id.txt_pembimbing2)
    TextView txtPembimbing2;
    @BindView(R.id.txt_penguji1)
    TextView txtPenguji1;
    @BindView(R.id.txt_penguji2)
    TextView txtPenguji2;
    @BindView(R.id.detail_layout)
    ScrollView detailLayout;
    @BindView(R.id.progress_layout)
    LinearLayout progressLayout;
    @BindView(R.id.layout_pembimbing)
    LinearLayout layoutPembimbing;

    @BindView(R.id.txt_nilai_akhir)
    TextView txtNilaiAkhir;
    @BindView(R.id.txt_status_lulus)
    TextView txtStatusLulus;
    @BindView(R.id.txt_catatan)
    TextView txtCatatan;
    @BindView(R.id.layout_berita_acara)
    CardView layoutBeritaAcara;
    @BindView(R.id.img_mahasiswa)
    ImageView imgMahasiswa;
    DetailJadwalUjianPresenter detailPresenter;
    JadwalUjian jadwalUjian;
    List<HashMap<String,String>>dosen;
    String idSkripsi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ujian);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Rincian Jadwal Ujian");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        dosen=new ArrayList<>();

        detailPresenter=new DetailJadwalUjianPresenter(this,this);
        jadwalUjian=(JadwalUjian) getIntent().getSerializableExtra("info_ujian");
        detailPresenter.getDetailJadwal(jadwalUjian.getInfoUjianId());

        String day= DateUtils.convertUnixTimeStamp("EEEE",jadwalUjian.getTanggal());
        txtTanggal.setText(DateUtils.getDay(day)+" ,"+DateUtils.convertUnixTimeStamp("dd MMMM yyyy",
                jadwalUjian.getTanggal()));
        txtWaktu.setText(jadwalUjian.getJam().split(":")[0]+":"+jadwalUjian.getJam().split(":")[1]+"WIB");
        detailPresenter.isShowBtnKehadiran(jadwalUjian.getIs_ketua());
        detailPresenter.savePeran(String.valueOf(jadwalUjian.getIs_ketua()),String.valueOf(jadwalUjian.getIs_penguji()));
        detailPresenter.isEnableBtnMenilai(jadwalUjian.getIs_penguji(),jadwalUjian.getStatus());

        detailLayout.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {

                Rect scrollBounds = new Rect();
                detailLayout.getHitRect(scrollBounds);
                if ((!btnVerifikasi.getLocalVisibleRect(scrollBounds)
                        || scrollBounds.height() < btnVerifikasi.getHeight()) || (!btnVerifikasi.getLocalVisibleRect(scrollBounds)
                        || scrollBounds.height() < btnVerifikasi.getHeight())) {
                } else {
                    GuideUtil.isShowGuideDetailUjian(DetailUjianActivity.this,btnVerifikasi,btnMenilai,jadwalUjian.getIs_ketua());
                }


            }
        });

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
    @Override
    public void showDataUjian(DataUjian dataUjian) {
        showDataMahasiswa(dataUjian.getMahasiswa());
        showDataPembimbing(dataUjian.getPembimbing());
        showDataPenguji(dataUjian.getPenguji());


    }
    public void showDataMahasiswa(Mahasiswa mahasiswa) {
        txtNama.setText(mahasiswa.getNama());
        txtNim.setText(mahasiswa.getNim());
        txtJurusan.setText(mahasiswa.getJenjang()+" "+mahasiswa.getJurusan());
        txtTelp.setText(mahasiswa.getNohp());
        txtEmail.setText(mahasiswa.getEmail());
        RequestOptions requestOptions=new RequestOptions()
                .error(R.drawable.image_place_holder)
                .placeholder(R.drawable.image_place_holder);
        Glide.with(this).load(mahasiswa.getFoto()).apply(requestOptions).into(imgMahasiswa);
        showDataSkripsi(mahasiswa.getSkripsi());

    }

    public void showDataSkripsi(final Skripsi skripsi) {
        idSkripsi=skripsi.getId();
        txtJudul.setText(skripsi.getJudulSkripsi());
        txtBidang.setText(skripsi.getBidang());
        txtTipePenelitian.setText(skripsi.getTipe_penelitian());
        txtAsalJudul.setText(skripsi.getAsalJudul());
        for (Matkul matkul : skripsi.getMatkulPendukung()){
            txtMkPilihan.setText("");
            txtMkPilihan.append("\u25CF "+matkul.getMatkul()+"\n");
        }
        btnBaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DetailUjianActivity.this, BacaDokumenActivity.class);
                intent.putExtra("url",skripsi.getDokumen());
                startActivity(intent);
            }
        });

    }

    public void showDataPembimbing(List<Pembimbing> pembimbingList) {
        if (pembimbingList.size()>1){
            HashMap<String,String>pembimbing1=new HashMap<>();
            pembimbing1.put("id_dosen",pembimbingList.get(0).getIdDosen());
            pembimbing1.put("nama",pembimbingList.get(0).getNama());
            pembimbing1.put("peran","pembimbing1");
            dosen.add(pembimbing1);
            HashMap<String,String>pembimbing2=new HashMap<>();
            pembimbing2.put("id_dosen",pembimbingList.get(1).getIdDosen());
            pembimbing2.put("nama",pembimbingList.get(1).getNama());
            pembimbing2.put("peran","pembimbing2");
            dosen.add(pembimbing2);

            txtPembimbing1.setText("1."+pembimbingList.get(0).getNama());
            txtPembimbing2.setText("2."+pembimbingList.get(1).getNama());
        }else {
            HashMap<String,String>pembimbing1=new HashMap<>();
            pembimbing1.put("id_dosen",pembimbingList.get(0).getIdDosen());
            pembimbing1.put("nama",pembimbingList.get(0).getNama());
            pembimbing1.put("peran","pembimbing1");
            dosen.add(pembimbing1);
            txtPembimbing1.setText("1."+pembimbingList.get(0).getNama());
            layoutPembimbing.setVisibility(View.GONE);
        }

    }

    public void showDataPenguji(List<Penguji> pengujiList) {
        for (int i=0; i<pengujiList.size(); i++){
            HashMap<String,String>penguji=new HashMap<>();
            penguji.put("id_dosen",pengujiList.get(i).getIdDosen());
            penguji.put("nama",pengujiList.get(i).getNama());
            penguji.put("peran","penguji"+(i+1));
            dosen.add(penguji);
        }

        txtPenguji1.setText("1."+pengujiList.get(0).getNama());
        txtPenguji2.setText("2."+pengujiList.get(1).getNama());
    }




    @Override
    public void showBtnKehadiran() {
        detailPresenter.isEnableBtnKehadiran(jadwalUjian.getTanggal(),jadwalUjian.getStatus());
        btnVerifikasi.setVisibility(View.VISIBLE);

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressLayout.setVisibility(View.VISIBLE);

    }

    @Override
    public void dismissProgress() {
        progressLayout.setVisibility(View.GONE);
    }

    @Override
    public void enableMenilai() {
        btnMenilai.setEnabled(true);
    }

    @Override
    public void enableBtnKehadiran(boolean isEnable) {

        btnVerifikasi.setEnabled(isEnable);
    }

    @Override
    public void goToPenilaian() {
        Intent intent=new Intent(this, PenilaianActivity.class);
        intent.putExtra("isPenguji",jadwalUjian.getIs_penguji());
        intent.putExtra("isKetua",jadwalUjian.getIs_ketua());
        intent.putExtra("status",jadwalUjian.getStatus());
        startActivity(intent);
    }

    @Override
    public void goToSummary() {
        Intent intent=new Intent(this, SummaryPenilaianActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToMonitor() {
        Intent intent=new Intent(this, KetuaMonitorActivity.class);
        startActivity(intent);
    }

    @Override
    public void showBeritaAcara(BeritaAcara beritaAcara) {
        btnMenilai.setVisibility(View.GONE);
        btnVerifikasi.setVisibility(View.GONE);
        layoutBeritaAcara.setVisibility(View.VISIBLE);
        txtNilaiAkhir.setText(beritaAcara.getNilaiAkhir());
        txtCatatan.setText(beritaAcara.getCatatan());
        txtStatusLulus.setText(beritaAcara.getStatusLulus());
    }

    @OnClick(R.id.btn_verifikasi)
    public void onClickVerifikasi(){
        Intent intent=new Intent(this,VerifikasiActivity.class);
        intent.putExtra("dataDosen", (Serializable) dosen);
        intent.putExtra("namaPembimbing1",txtPembimbing1.getText().toString());
        intent.putExtra("namaPembimbing2",txtPembimbing2.getText().toString());
        intent.putExtra("namaPenguji1",txtPenguji1.getText().toString());
        intent.putExtra("namaPenguji2",txtPenguji2.getText().toString());
        intent.putExtra("mahasiswa",txtNama.getText().toString());
        intent.putExtra("nim",txtNim.getText().toString());
        startActivity(intent);
    }
    @OnClick(R.id.btn_menilai)
    public void onClickMenilai(){
        detailPresenter.checkIsNilai();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        detailPresenter.getDetailJadwal(jadwalUjian.getInfoUjianId());
        detailPresenter.isEnableBtnKehadiran(jadwalUjian.getTanggal(),jadwalUjian.getStatus());
        detailPresenter.isEnableBtnMenilai(jadwalUjian.getIs_penguji(),jadwalUjian.getStatus());
    }
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void notifHandler(String update){
        detailPresenter.isEnableBtnMenilai(jadwalUjian.getIs_penguji(),jadwalUjian.getStatus());

    }
}
