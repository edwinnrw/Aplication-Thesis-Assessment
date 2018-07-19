package skripsi.edwin.filkomapps.summaryPenilaian.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.materialdialog.MaterialDialog;
import skripsi.edwin.filkomapps.MainActivity;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.model.DataKomponen;
import skripsi.edwin.filkomapps.summaryPenilaian.presenter.SummaryContract;
import skripsi.edwin.filkomapps.summaryPenilaian.presenter.SummaryPresenter;
import skripsi.edwin.filkomapps.summaryPenilaian.view.adapter.SummaryAdapter;

public class SummaryPenilaianActivity extends AppCompatActivity implements SummaryContract.View {
    SummaryPresenter presenter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    SummaryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_penilaian);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hasil Penilaian");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_close);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        presenter=new SummaryPresenter(this,this);
        presenter.getNilai();
    }

    @Override
    public void setNilai(List<HashMap<String,Object>>list) {
        adapter=new SummaryAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.getNilai();

    }
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void notifHandler(String update){
        final MaterialDialog materialDialog=new MaterialDialog(this);
        materialDialog.setMessage("Ujian Telah Selesai. Anda Sudah Tidak Bisa Melakukan Perubahan Nilai")
                .setPositiveButton("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.removeDatTemp();
                        Intent intent=new Intent(SummaryPenilaianActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    }
                }).setCanceledOnTouchOutside(false).show();

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
