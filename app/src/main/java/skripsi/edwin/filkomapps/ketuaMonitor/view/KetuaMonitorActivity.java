package skripsi.edwin.filkomapps.ketuaMonitor.view;

import android.app.ProgressDialog;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import butterknife.BindView;
import butterknife.ButterKnife;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.ketuaMonitor.presenter.KetuaMonitorContract;
import skripsi.edwin.filkomapps.ketuaMonitor.presenter.KetuaMonitorPresenter;

public class KetuaMonitorActivity extends AppCompatActivity implements KetuaMonitorContract.View {
    @BindView(R.id.viewpager)
    ViewPager historyViewPager;
    @BindView(R.id.tab)
    ViewGroup tab;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    public  View badge;
    KetuaMonitorPresenter ketuaMonitorPresenter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketua_monitor);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Monitor");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        ketuaMonitorPresenter=new KetuaMonitorPresenter(this,this);
        ketuaMonitorPresenter.checkGetNilai();
        setTabLayout();


    }
    private void setTabLayout() {
        tab.addView(LayoutInflater.from(this).inflate(R.layout.tab_text, tab, false));
        SmartTabLayout historyTabLayout=tab.findViewById(R.id.viewpagertab);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Penilaian Saya", SummaryPenilaianFragement.class)
                .add("Rekap Nilai & Berita Acara", RekapBeritaAcaraFragment.class)


                .create());
        historyViewPager.setAdapter(adapter);
        historyTabLayout.setViewPager(historyViewPager);
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
    public void showProgress() {
        progressDialog.show();

    }

    @Override
    public void dismisProgress() {
        progressDialog.dismiss();

    }

    @Override
    public void getNilaiFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }


}
