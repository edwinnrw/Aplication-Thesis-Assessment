package skripsi.edwin.filkomapps.penilaian.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.ketuaMonitor.view.KetuaMonitorActivity;
import skripsi.edwin.filkomapps.model.DataKomponen;
import skripsi.edwin.filkomapps.penilaian.presenter.PenilaianContract;
import skripsi.edwin.filkomapps.penilaian.presenter.PenilaianPresenter;
import skripsi.edwin.filkomapps.penilaian.view.adapter.PenilaianAdapter;
import skripsi.edwin.filkomapps.summaryPenilaian.view.SummaryPenilaianActivity;
import skripsi.edwin.filkomapps.util.GuideUtil;

public class PenilaianActivity extends AppCompatActivity implements PenilaianContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private PenilaianAdapter penilaianAdapter;
    private PenilaianPresenter penilaianPresenter;
    private ProgressDialog progressDialog;
    private List<DataKomponen>dataKomponenList;
    private MenuItem menu;
    private ImageView btnTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penilaian);
        ButterKnife.bind(this);
        toolbar.inflateMenu(R.menu.menu_penilaian);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Penilaian");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        penilaianPresenter=new PenilaianPresenter(this,this);
        progressDialog.setCancelable(false);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                onBackPressed();
            }
        });
        penilaianPresenter.checkAkses();
    }

    @Override
    public void showMessageError(String message) {
        final MaterialDialog materialDialog=new MaterialDialog(this);
        materialDialog.setMessage(message)
                .setPositiveButton("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDialog.dismiss();
                        onBackPressed();


                    }
                }).show();
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
    public void setListKomponen(List<DataKomponen> dataKomponens) {
        penilaianAdapter=new PenilaianAdapter(this,dataKomponens,penilaianPresenter);
        recyclerView.setAdapter(penilaianAdapter);
        dataKomponenList=dataKomponens;


    }
    @OnClick(R.id.btn_submit)
    public void onClickSubmit(){
        final MaterialDialog materialDialog=new MaterialDialog(this);
        materialDialog.setMessage("Apakah Anda Yakin?")
                .setPositiveButton("Ya", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDialog.dismiss();
                        penilaianPresenter.validationIsComplete(dataKomponenList);


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
    public void showSubmitError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void submitSuccess() {
        Toast.makeText(this, "Penilain Berhasi Disimpan", Toast.LENGTH_SHORT).show();
        penilaianPresenter.checkIsKetua();

    }

    @Override
    public void validationError() {
        Toast.makeText(this, "Anda Belum Melengkapi Penilaian", Toast.LENGTH_LONG).show();

    }

    @Override
    public void validationSuccess() {
        Log.d("yesssNilai","aaa");
        penilaianPresenter.submitNilai(getIntent().getStringExtra("isKetua"));
    }

    @Override
    public void goToMonitor() {
        Intent intent=new Intent(PenilaianActivity.this, KetuaMonitorActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToSummary() {
        Intent intent=new Intent(PenilaianActivity.this, SummaryPenilaianActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void showMenuTimer() {

        menu.setVisible(true);
        GuideUtil.isShowGuideTimer(this, btnTimer);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==1){
            ((TextView)recyclerView.findViewHolderForAdapterPosition(data.getIntExtra("posisiKomponen",0))
                    .itemView.findViewById(R.id.txt_komponen))
                    .setCompoundDrawablesWithIntrinsicBounds(
                            getResources().getDrawable(R.drawable.ic_check_24dp),
                            null,null,null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_penilaian, menu);
        this.menu = menu.findItem(R.id.menu_timer);
        MenuItemCompat.setActionView(this.menu, R.layout.timer_layout);
        RelativeLayout timerLayout = (RelativeLayout) MenuItemCompat.getActionView(this.menu);
        btnTimer =timerLayout.findViewById(R.id.timer_btn);
        timerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimerFragment bottomSheetFragment = new TimerFragment();
                bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
            }
        });
        penilaianPresenter.isShowMenuTimer();
        return super.onCreateOptionsMenu(menu);
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
