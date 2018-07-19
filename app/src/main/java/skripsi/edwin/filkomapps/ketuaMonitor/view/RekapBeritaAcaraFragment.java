package skripsi.edwin.filkomapps.ketuaMonitor.view;



import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;
import skripsi.edwin.filkomapps.MainActivity;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.detailNilai.view.DetailNilaiMajelisActivity;
import skripsi.edwin.filkomapps.ketuaMonitor.presenter.RekapContract;
import skripsi.edwin.filkomapps.ketuaMonitor.presenter.RekapPresenter;
import skripsi.edwin.filkomapps.model.GAP;
import skripsi.edwin.filkomapps.notifikasi.view.NotificationActivity;
import skripsi.edwin.filkomapps.util.CustomArrayAdapater;
import skripsi.edwin.filkomapps.util.GuideUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class RekapBeritaAcaraFragment extends Fragment implements  RekapContract.View {


    @BindView(R.id.txt_catatan)
    EditText txtCatatan;
    @BindView(R.id.more_nilai_skripsi)
    TextView txtMoreNilaiSkripsi;
    @BindView(R.id.more_nilai_kualitas)
    TextView txtMoreNilaiKualitas;
    @BindView(R.id.txt_gap)
    TextView txtGap;
    @BindView(R.id.txt_ujian_skripsi1)
    TextView nilaiUjianPembimbing;
    @BindView(R.id.txt_sub_total_nilai_ujian1)
    TextView subTotalPembimbing;
    @BindView(R.id.txt_ujian_skripsi2)
    TextView nilaiUjianPenguji;
    @BindView(R.id.txt_sub_total_nilai_ujian2)
    TextView subTotalPenguji;
    @BindView(R.id.txt_nilai_kualitas_pembimbing)
    TextView nilaiKualitasPembimbing;
    @BindView(R.id.txt_sub_total_nilai_kualitas)
    TextView subTotalNKPembimbing;
    @BindView(R.id.txt_nilai_kualitas_penguji1)
    TextView nilaiKualitasPenguji;
    @BindView(R.id.txt_sub_total_nilai_kualitas1)
    TextView subTotalNKPenguji;
    @BindView(R.id.txt_nilai_akhir_penulisan)
    TextView nilaiAkhirPenulisan;
    @BindView(R.id.txt_nilai_publikasi)
    TextView nilaiPublikasi;
    @BindView(R.id.txt_sub_total_publikasi)
    TextView subTotalPublikasi;
    @BindView(R.id.txt_semhas)
    TextView nilaiSemhas;
    @BindView(R.id.txt_sub_total_semhas)
    TextView subTotalSemhas;
    @BindView(R.id.txt_nilai_akhir_total)
    TextView nilaiAkhirTotal;
    @BindView(R.id.txt_nilai_akhir_skripsi_huruf)
    TextView nilaiAkhirSkripsiHuruf;
    @BindView(R.id.txt_nilai_akhir_penulisan_huruf)
    TextView nilaiAkhirPenHuruf;
    @BindView(R.id.txt_nilai_akhir)
    TextView nilaiAkhirBeritaAcara;
    @BindView(R.id.checkbox_pembimbing1)
    CheckBox checkBoxPembimbing1;
    @BindView(R.id.checkbox_pembimbing2)
    CheckBox checkBoxPembimbing2;
    @BindView(R.id.checkbox_penguji1)
    CheckBox checkBoxPenguji1;
    @BindView(R.id.checkbox_penguji2)
    CheckBox checkBoxPenguji2;
    @BindView(R.id.ln_checkbox_pembimbing2)
    LinearLayout lnCheckBoxPem2;
    @BindView(R.id.ln_checkbox_pembimbing1)
    LinearLayout lnCheckBoxPem1;
    @BindView(R.id.spiner_status)
    Spinner spinner;
    @BindView(R.id.txt_udpdated)
    TextView txtUpdate;
    @BindView(R.id.card_info_penilaian)
    CardView cardViewInfo;
    @BindView(R.id.ln_gap)
    LinearLayout lnGap;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    private RekapPresenter rekapPresenter;
    private View badge;
    private List<GAP>gapList;
    private DecimalFormat decimalFormat;
    private ProgressDialog progressDialog;
    private String naSkripsi;
    private boolean mIsHiding = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_rekap_berita_acara, container, false);
        ButterKnife.bind(this,view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        decimalFormat=new DecimalFormat("#0.0");
        rekapPresenter=new RekapPresenter(getActivity(),this);
        rekapPresenter.buildDropdown();
        gapList=new ArrayList<>();
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        rekapPresenter.getTimeUpdate();
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                View view = scrollView.getChildAt(scrollView.getChildCount()-1);
                int diff = (view.getBottom()-(scrollView.getHeight()+scrollView.getScrollY()));
                // if diff is zero, then the bottom has been reached
                if (diff < view.getHeight()/6 ) {
                   hideGap();
                }else {
                    showGap();
                }
            }
        });
    }
    public void hideGap(){
        if (mIsHiding || lnGap.getVisibility() != View.VISIBLE) {
            return;
        }

        mIsHiding = true;
        Animation anim = AnimationUtils.loadAnimation(
                lnGap.getContext(), R.anim.slide_down);
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(200);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mIsHiding = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIsHiding = false;
                lnGap.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        lnGap.setAnimation(anim);
    }
    public void showGap(){
        if (lnGap.getVisibility() != View.VISIBLE || mIsHiding) {
            lnGap.setVisibility(View.VISIBLE);
            TranslateAnimation animate = new TranslateAnimation(
                    0,                 // fromXDelta
                    0,                 // toXDelta
                    lnGap.getHeight(),  // fromYDelta
                    0);                // toYDelta
            animate.setDuration(500);
            animate.setFillAfter(true);
            lnGap.startAnimation(animate);
        }
    }
    private void setNilaiAkhirUjian() {
        double semhas= Double.parseDouble(subTotalSemhas.getText().toString());
        double np=Double.parseDouble(subTotalPublikasi.getText().toString());
        double nuPembimbing=Double.parseDouble(subTotalPembimbing.getText().toString());
        double nuPenguji=Double.parseDouble(subTotalPenguji.getText().toString());
        double na=semhas+np+nuPembimbing+nuPenguji;
        rekapPresenter.getNilaiAkhirSkripsi(semhas,np,nuPembimbing,nuPenguji);
        nilaiAkhirTotal.setText(decimalFormat.format(na));
    }

    @OnClick({R.id.more_nilai_kualitas,R.id.more_nilai_skripsi})
    public  void onClickMoreNilai(){
        Intent intent=new Intent(getActivity(), DetailNilaiMajelisActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.btn_submit)
    public void onClickSubmit(){
        final MaterialDialog materialDialog=new MaterialDialog(getActivity());
        materialDialog.setMessage("Apakah Anda Yakin?")
                .setPositiveButton("Ya", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDialog.dismiss();
                        rekapPresenter.validation(spinner.getSelectedItemPosition());


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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_monitor, menu);
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem item1 = menu.findItem(R.id.menu_messages);
        MenuItemCompat.setActionView(item1, R.layout.badge_layout);
        RelativeLayout notificationCount1 = (RelativeLayout) MenuItemCompat.getActionView(item1);
        badge=notificationCount1.findViewById(R.id.badge_notification_1);
        rekapPresenter.isNotif();
        ImageView btn_notif=notificationCount1.findViewById(R.id.notif_btn);
        btn_notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), NotificationActivity.class),1);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showFailMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void submitSuccess() {
        final MaterialDialog materialDialog=new MaterialDialog(getActivity());
        materialDialog.setMessage("Berita Acara Berhasil Disimpan.Ujian Telah Selesai.")
                .setPositiveButton("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDialog.dismiss();
                        Intent intent=new Intent(getActivity(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        getActivity().finish();


                    }
                }).show();
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
    public void setGap(List<GAP> dataGap) {
        gapList=dataGap;
        txtGap.setText("Total GAP ("+dataGap.size()+")");

    }

    @Override
    public void validationFalse(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void validationSuccess() {
        rekapPresenter.submitFinal(txtCatatan.getText().toString(),spinner.getSelectedItem().toString(),naSkripsi);

    }

    @Override
    public void showBadge() {
        if (badge!=null){
            badge.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideBadge() {
        if (badge!=null){
            badge.setVisibility(View.GONE);
        }
    }

    @Override
    public void setRataRataPembimbing(String avgAll, String subtotal) {
        nilaiUjianPembimbing.setText(avgAll);
        subTotalPembimbing.setText(subtotal);

    }

    @Override
    public void setRataRataPenguji(String avgAll, String subtotal) {
        nilaiUjianPenguji.setText(avgAll);
        subTotalPenguji.setText(subtotal);
    }

    @Override
    public void setNilaiPenulisanPembimbing(String avgAll, String subtotal) {
        nilaiKualitasPembimbing.setText(avgAll);
        subTotalNKPembimbing.setText(subtotal);
    }

    @Override
    public void setNilaiPenulisanPenguji(String avgAll, String subtotal) {
        nilaiKualitasPenguji.setText(avgAll);
        subTotalNKPenguji.setText(subtotal);

    }

    @Override
    public void setNilaiPublikasi(String avgAll, String subtotal) {
        nilaiPublikasi.setText(avgAll);
        subTotalPublikasi.setText(subtotal);
    }

    @Override
    public void setSemhas(String nilai, String subtotal) {
        nilaiSemhas.setText(nilai);
        subTotalSemhas.setText(subtotal);
    }

    @Override
    public void setNilaiAkhirUjian(String nilai) {
        String huruf=rekapPresenter.getHurufNilai(String.valueOf(nilai));
        this.naSkripsi=nilai;
        nilaiAkhirSkripsiHuruf.setText(huruf+"("+nilai+")");
        nilaiAkhirBeritaAcara.setText(huruf+"("+nilai+")");
    }

    @Override
    public void setCheckPembimbing1(boolean isCheck) {
        lnCheckBoxPem1.setVisibility(View.VISIBLE);
        checkBoxPembimbing1.setChecked(isCheck);
        if (isCheck){
            checkBoxPembimbing1.setText("Selesai");
        }
    }

    @Override
    public void setCheckPembimbing2(boolean isCheck) {
        lnCheckBoxPem2.setVisibility(View.VISIBLE);
        checkBoxPembimbing2.setChecked(isCheck);
        if (isCheck){
            checkBoxPembimbing2.setText("Selesai");
            }
    }

    @Override
    public void setCheckPenguji1(boolean isCheck) {
        checkBoxPenguji1.setChecked(isCheck);
        if (isCheck){
            checkBoxPenguji1.setText("Selesai");
        }
    }

    @Override
    public void setCheckPenguji2(boolean isCheck) {
        checkBoxPenguji2.setChecked(isCheck);
        if (isCheck){
            checkBoxPenguji2.setText("Selesai");
        }
    }

    @Override
    public void setDropdown(String[] list) {
        CustomArrayAdapater customSpinnerAdapter=new CustomArrayAdapater(getActivity(),list);
        spinner.setAdapter(customSpinnerAdapter);
    }

    @Override
    public void setTimeUpdate(String update) {
        txtUpdate.setText("Updated("+update+" WIB"+")");
    }


    public void setNilaiAkhirPenulisan(){
        double nilaiKPembibing=Double.parseDouble(subTotalNKPembimbing.getText().toString());
        double nilaiKPenguji=Double.parseDouble(subTotalNKPenguji.getText().toString());
        double na=nilaiKPembibing+nilaiKPenguji;
        String huruf=rekapPresenter.getHurufNilai(String.valueOf(na));
        nilaiAkhirPenulisan.setText(decimalFormat.format(na));
        nilaiAkhirPenHuruf.setText(huruf+"("+decimalFormat.format(na)+")");

   }

    @OnClick({R.id.ln_gap,R.id.txt_gap})
    public void onClickGap(){

        Intent intent=new Intent(getActivity(),ListGapActivity.class);
        intent.putExtra("gaplist", (Serializable) gapList);
        startActivity(intent);
    }
    public void onStart() {
        super.onStart();
        rekapPresenter.getRekap();
        setNilaiAkhirPenulisan();
        setNilaiAkhirUjian();
        rekapPresenter.checkGap();
        rekapPresenter.getTimeUpdate();
        EventBus.getDefault().register(this);

    }

    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void notifHandler(String update){
        rekapPresenter.getRekap();
        setNilaiAkhirPenulisan();
        setNilaiAkhirUjian();
        rekapPresenter.checkGap();
        rekapPresenter.isNotif();
        rekapPresenter.saveUpdate();
        rekapPresenter.getTimeUpdate();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        rekapPresenter.isNotif();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            rekapPresenter.getRekap();
            rekapPresenter.getIsNilaiMajelis();
            setNilaiAkhirPenulisan();
            rekapPresenter.checkGap();
            setNilaiAkhirUjian();
            rekapPresenter.getTimeUpdate();
            GuideUtil.isShowMonitorGuide(getActivity(),cardViewInfo, lnGap);
        }
    }
}
