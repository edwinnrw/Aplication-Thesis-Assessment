package skripsi.edwin.filkomapps.ketuaMonitor.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.ketuaMonitor.presenter.GapContract;
import skripsi.edwin.filkomapps.ketuaMonitor.presenter.GapPresenter;
import skripsi.edwin.filkomapps.ketuaMonitor.view.adapter.GapAdapter;
import skripsi.edwin.filkomapps.model.DetailGAP;
import skripsi.edwin.filkomapps.model.GAP;

public class ListGapActivity extends AppCompatActivity implements GapContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.txt_empty)
    TextView txtEmpty;
    List<GAP> gapList;
    GapPresenter gapPresenter;
    GapAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_gap);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("GAP Nilai");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        gapList=(List<GAP>)getIntent().getSerializableExtra("gaplist");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        gapPresenter=new GapPresenter(this,this);
        gapPresenter.getDetailGap(gapList);
    }

    @Override
    public void setGap(List<DetailGAP> detailGAPS) {
        adapter=new GapAdapter(this,detailGAPS);
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);
        txtEmpty.setVisibility(View.GONE);

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
