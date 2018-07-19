package skripsi.edwin.filkomapps.jadwalUjian.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.supercharge.shimmerlayout.ShimmerLayout;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.jadwalUjian.presenter.presenterRiwayat.ResultSearchRiwayatContract;
import skripsi.edwin.filkomapps.jadwalUjian.presenter.presenterRiwayat.ResultSearchRiwayatPresenter;
import skripsi.edwin.filkomapps.jadwalUjian.view.adapter.RiwayatAdapter;
import skripsi.edwin.filkomapps.model.JadwalUjian;

public class ResultSearchRiwayatActivity extends AppCompatActivity implements ResultSearchRiwayatContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.ln_empty)
    LinearLayout ln_empty;
    @BindView(R.id.txt_empty)
    TextView txt_empty;
    @BindView(R.id.shimmer_view_container)
    ShimmerLayout shimmerFrameLayout;
    ResultSearchRiwayatPresenter resultSearchPresenter;
    RiwayatAdapter adapter;
    private List<JadwalUjian> dataJadwalUjianList;
    List<String>peran;
    String isComplete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search_riwayat);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        resultSearchPresenter=new ResultSearchRiwayatPresenter(this,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        peran=(List<String>)getIntent().getSerializableExtra("peran");
        isComplete=getIntent().getStringExtra("isComplete");
        resultSearchPresenter.getJadwal(peran,getIntent().getStringExtra("search"),isComplete);
    }


    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate( R.menu.menu_search, menu);

        MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setQuery(getIntent().getStringExtra("search"),true);
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                resultSearchPresenter.getJadwal(peran, query, isComplete);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });

        return true;
    }



    @Override
    public void showProgress() {
        shimmerFrameLayout.startShimmerAnimation();
    }

    @Override
    public void dismisProgress() {
        shimmerFrameLayout.stopShimmerAnimation();
        shimmerFrameLayout.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setJadwal(List<JadwalUjian> jadwalUjian) {
        dataJadwalUjianList=jadwalUjian;
        adapter=new RiwayatAdapter(this,jadwalUjian);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setVisibility(View.VISIBLE);
        ln_empty.setVisibility(View.GONE);
    }



    @Override
    public void setEmptyView() {
        ln_empty.setVisibility(View.VISIBLE);

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
