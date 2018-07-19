package skripsi.edwin.filkomapps.penilaian.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.stepstone.stepper.StepperLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.model.DataKomponen;
import skripsi.edwin.filkomapps.penilaian.view.adapter.StepperAdapter;

public class PenilaianUjianActivity extends AppCompatActivity{
    @BindView(R.id.stepperLayout)
    StepperLayout stepperLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    List<HashMap<String,String>> nilai;

    DataKomponen dataKomponen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indikator_penilaian);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        dataKomponen=(DataKomponen)getIntent().getSerializableExtra("komponen");
        getSupportActionBar().setTitle(dataKomponen.getKomponen());
       stepperLayout.setAdapter(new StepperAdapter(getSupportFragmentManager(),this,
                dataKomponen.getIndikators().size()));
        nilai=new ArrayList<>();

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
