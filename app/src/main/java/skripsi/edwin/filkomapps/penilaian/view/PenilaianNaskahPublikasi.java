package skripsi.edwin.filkomapps.penilaian.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.model.DataKomponen;
import skripsi.edwin.filkomapps.penilaian.presenter.PenilaianNpContract;
import skripsi.edwin.filkomapps.penilaian.presenter.PenilaianNpPresenter;
import skripsi.edwin.filkomapps.util.InputFilterMinMax;

public class PenilaianNaskahPublikasi extends AppCompatActivity implements PenilaianNpContract.View{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout_parent)
    LinearLayout lnParent;
    private PenilaianNpPresenter presenter;
    private HashMap<String,EditText>editTextList;
    private DataKomponen dataKomponen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penilaian_naskah_publikasi);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Penilaian Naskah Publikasi");
        presenter=new PenilaianNpPresenter(this,this);
        dataKomponen=(DataKomponen)getIntent().getSerializableExtra("komponen");
        editTextList=new HashMap<>();
        presenter.buildIndikatorPenilaian(dataKomponen);

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
    public void setIndikator(String id, String indikator, String keterangan) {
        LinearLayout subParent = new LinearLayout(this);
        LinearLayout.LayoutParams paramsLn=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsLn.setMargins(0,20,0,5);
        subParent.setLayoutParams(paramsLn);
        subParent.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout layoutIndikator= new LinearLayout(this);
        LinearLayout.LayoutParams paramLnIndikator=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,0.3f);
        layoutIndikator.setLayoutParams(paramLnIndikator);
        layoutIndikator.setOrientation(LinearLayout.VERTICAL);

        TextView tvIndikator = new TextView(this);
        tvIndikator.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tvIndikator.setText(indikator);
        tvIndikator.setTypeface(tvIndikator.getTypeface(), Typeface.BOLD);
        tvIndikator.setTextSize(15);
        tvIndikator.setTextColor(getResources().getColor(R.color.colorQuarts));

        TextView tvKeterangan = new TextView(this);
        tvKeterangan.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tvKeterangan.setText(keterangan);
        tvKeterangan.setTextSize(13);

        tvKeterangan.setTextColor(getResources().getColor(R.color.colorQuarts));
        layoutIndikator.addView(tvIndikator);

        layoutIndikator.addView(tvKeterangan);
        subParent.addView(layoutIndikator);
        setEditText(id,indikator,subParent);
    }

    public void setEditText(String id, String indikator, LinearLayout subParent) {
        LinearLayout layoutEdit= new LinearLayout(this);
        LinearLayout.LayoutParams paramLnEdit=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1f);
        layoutEdit.setLayoutParams(paramLnEdit);
        layoutEdit.setGravity(Gravity.CENTER);
        layoutEdit.setOrientation(LinearLayout.VERTICAL);

        EditText editText =new EditText(this);
        editText.setGravity(Gravity.CENTER);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "100")});
        editText.setText(presenter.getNilaiTemp(id));
        editText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layoutEdit.addView(editText);
        subParent.addView(layoutEdit);
        lnParent.addView(subParent);
        editTextList.put(indikator,editText);



    }

    @Override
    public void validationError(EditText editText) {
        editText.setError("Wajib Diisi");
    }

    @Override
    public void validationComplete() {
        presenter.saveNilai(dataKomponen,editTextList);
        Intent intentResult=new Intent(this,PenilaianActivity.class);
        intentResult.putExtra("posisiKomponen", getIntent().getIntExtra("posisiKomponen",0));
        intentResult.putExtra("komponen",dataKomponen.getKomponen());
        setResult(1,intentResult);
        finish();//finishing activity

    }
    @OnClick(R.id.btn_finish)
    public void  onClickFinish(){
        final MaterialDialog materialDialog=new MaterialDialog(this);
        materialDialog.setMessage("Apakah Anda Yakin?")
                .setPositiveButton("Ya", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDialog.dismiss();
                        presenter.validationIsComplete(dataKomponen,editTextList);
                    }
                })
                .setNegativeButton("Tidak", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDialog.dismiss();
                    }
                }).show();


    }


}
