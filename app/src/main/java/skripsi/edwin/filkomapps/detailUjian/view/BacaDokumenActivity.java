package skripsi.edwin.filkomapps.detailUjian.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.penilaian.view.TimerFragment;

public class BacaDokumenActivity extends AppCompatActivity implements DownloadFile.Listener, DialogInterface.OnCancelListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.remote_pdf_root)
    LinearLayout root;
    @BindView(R.id.pdf_page_container)
    RemotePDFViewPager remotePDFViewPager;
    PDFPagerAdapter adapter;
    ProgressDialog mProgressDialog;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baca_dokumen);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Dokumen");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        remotePDFViewPager=new RemotePDFViewPager(this,getIntent().getStringExtra("url"),this);
        remotePDFViewPager.setId(R.id.pdf_page_container);
        remotePDFViewPager.setHorizontalScrollBarEnabled(true);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCancelable(true);
        mProgressDialog.setOnCancelListener(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setMax(100);
        mProgressDialog.show();
        Toast.makeText(this, "Harap Tunggu", Toast.LENGTH_SHORT).show();
    }
    public void updateLayout() {
        root.removeAllViewsInLayout();
        root.addView(toolbar,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        root.addView(remotePDFViewPager,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        remotePDFViewPager.setHorizontalScrollBarEnabled(true);
    }
    @Override
    public void onSuccess(String url, String destinationPath) {
        i=100;
        mProgressDialog.setProgress(i);
        adapter = new PDFPagerAdapter(this, "sample.pdf");
        remotePDFViewPager.setAdapter(adapter);
        mProgressDialog.dismiss();
        updateLayout();
        final Snackbar snackbar = Snackbar
                .make(root, "Geser untuk melihat halaman berikutnya", Snackbar.LENGTH_INDEFINITE)
                .setAction("OKE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

        snackbar.show();
    }


    @Override
    public void onFailure(Exception e) {
        mProgressDialog.dismiss();
        Toast.makeText(this, "Koneksi Anda Bermasalah", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProgressUpdate(int progress, int total) {
        i++;
        mProgressDialog.setProgress(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter!=null){
            adapter.close();

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        onBackPressed();
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
