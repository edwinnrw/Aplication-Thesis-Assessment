package skripsi.edwin.filkomapps.notifikasi.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.model.orm.Notifikasi;
import skripsi.edwin.filkomapps.notifikasi.presenter.NotifikasiContract;
import skripsi.edwin.filkomapps.notifikasi.presenter.NotifikasiPresenter;
import skripsi.edwin.filkomapps.notifikasi.view.adapter.NotifikasiAdapter;

public class NotificationActivity extends AppCompatActivity implements NotifikasiContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    NotifikasiPresenter notifikasiPresenter;
    NotifikasiAdapter notifikasiAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notifikasi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        notifikasiPresenter=new NotifikasiPresenter(this,this);
        notifikasiPresenter.getNotif();
    }

    @Override
    public void setNotifikasi(List<Notifikasi> notifikasi) {
        notifikasiAdapter=new NotifikasiAdapter(this,notifikasi);
        recyclerView.setAdapter(notifikasiAdapter);
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
