package skripsi.edwin.filkomapps.detailNilai.view;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import butterknife.BindView;
import butterknife.ButterKnife;
import skripsi.edwin.filkomapps.R;

public class DetailNilaiMajelisActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager historyViewPager;
    @BindView(R.id.tab)
    ViewGroup tab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_nilai);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Rincian Penilaian");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTabLayout();
    }
    private void setTabLayout() {
        tab.addView(LayoutInflater.from(this).inflate(R.layout.tab_text_scroll, tab, false));
        SmartTabLayout historyTabLayout=tab.findViewById(R.id.viewpagertab);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Pembimbing 1", DetailFragment.class)
                .add("Pembimbing 2",DetailFragment.class)
                .add("Penguji 1", DetailFragment.class)
                .add("Penguji 2", DetailFragment.class)

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

}
