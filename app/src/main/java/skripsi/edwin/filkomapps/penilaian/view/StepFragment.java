package skripsi.edwin.filkomapps.penilaian.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.penilaian.presenter.IndikatorContract;
import skripsi.edwin.filkomapps.penilaian.presenter.IndikatorPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepFragment extends Fragment implements BlockingStep, IndikatorContract.View{
    @BindView(R.id.txt_deskripsi)
    TextView txtDeskripsi;

    @BindView(R.id.txt_nilai)
    TextView txtNilai;
    @BindView(R.id.txt_indikator)
    TextView txtIndikator;
    @BindView(R.id.rgPenilaian)
    RadioGroup radioGroup;
    @BindView(R.id.rbNilai1)
    RadioButton rbNilai1;
    @BindView(R.id.rbNilai2)
    RadioButton rbNilai2;
    @BindView(R.id.rbNilai3)
    RadioButton rbNilai3;
    @BindView(R.id.rbNilai4)
    RadioButton rbNilai4;
    @BindView(R.id.rbNilai5)
    RadioButton rbNilai5;
    @BindView(R.id.rbNilai6)
    RadioButton rbNilai6;
    @BindView(R.id.rbNilai7)
    RadioButton rbNilai7;
    private IndikatorPresenter indikatorPresenter;
    private PenilaianUjianActivity activity;
    private Bundle b;
    private View view;
    private boolean isNilai=false;
    private int posisi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_step, container, false);
        ButterKnife.bind(this,view);
        this.view=view;
        radioSelect(view,radioGroup.getCheckedRadioButtonId());
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        radioSelect(this.view,radioGroup.getCheckedRadioButtonId());

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity=(PenilaianUjianActivity)getActivity();
        b=getArguments();
        posisi=b.getInt("posisi");
        txtIndikator.setText(activity.dataKomponen.getIndikators().get(posisi).getIndikator());
        indikatorPresenter=new IndikatorPresenter(getActivity(),this);
        indikatorPresenter.getNilaiTemp(activity.dataKomponen.getIndikators().get(posisi).getId());

    }

    private void radioSelect(View view,int select) {
        if (select!=-1){
            isNilai=true;
            RadioButton radioButton = view.findViewById(select);
            indikatorPresenter.getDeskripsi(activity.dataKomponen.getId(),
                    Integer.parseInt(radioButton.getText().toString()),b.getInt("posisi"));
            indikatorPresenter.getNilai(Integer.parseInt(radioButton.getText().toString()));
        }
    }

    @Override
    public VerificationError verifyStep() {
        if (!isNilai){
            return new VerificationError("Nilai Belum Ditentukan");
        }else{
            return null;

        }
    }

    @Override
    public void onSelected() {

    }


    @Override
    public void onError(@NonNull VerificationError error) {
        Toast.makeText(getActivity(), error.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setDeskripsi(String des) {
        txtDeskripsi.setText(des);
    }

    @Override
    public void setKategoriNilai(String nilai) {
        txtNilai.setText(nilai);
    }

    @Override
    public void setNilaiTempt(int nilai) {
        indikatorPresenter.getNilai(nilai);
        switch (nilai) {
            case 1:
                rbNilai1.setChecked(true);
                break;
            case 2:
                rbNilai2.setChecked(true);
                break;
            case 3:
                rbNilai3.setChecked(true);
                break;
            case 4:
                rbNilai4.setChecked(true);
                break;
            case 5:
                rbNilai5.setChecked(true);
                break;
            case 6:
                rbNilai6.setChecked(true);
                break;
            case 7:
                rbNilai7.setChecked(true);
                break;
        }
    }

    @OnClick({R.id.rbNilai1,R.id.rbNilai2,R.id.rbNilai3,R.id.rbNilai4,R.id.rbNilai5,
            R.id.rbNilai6,R.id.rbNilai7})
    public void onClickNilai(RadioButton radioButton){
        isNilai=true;
        HashMap<String,String>nilaiPerIndikator=new HashMap<>();
        indikatorPresenter.getDeskripsi(activity.dataKomponen.getId(),
                        Integer.parseInt(radioButton.getText().toString()),b.getInt("posisi"));
        indikatorPresenter.getNilai(Integer.parseInt(radioButton.getText().toString()));
        nilaiPerIndikator.put("id",activity.dataKomponen.getIndikators().get(posisi).getId());
        nilaiPerIndikator.put("indikator",activity.dataKomponen.getIndikators().get(posisi).getIndikator());
        nilaiPerIndikator.put("nilai",radioButton.getText().toString());
        activity.nilai.add(nilaiPerIndikator);
    }


    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        callback.goToNextStep();
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        final MaterialDialog materialDialog=new MaterialDialog(getActivity());
        materialDialog.setMessage("Apakah Anda Yakin?")
                .setPositiveButton("Ya", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDialog.dismiss();
                        indikatorPresenter.saveNilaiTemp(activity.dataKomponen,activity.nilai);
                        Intent intentResult=new Intent(activity,PenilaianActivity.class);
                        intentResult.putExtra("posisiKomponen", activity.getIntent().getIntExtra("posisiKomponen",0));
                        intentResult.putExtra("komponen",activity.dataKomponen.getKomponen());
                        activity.setResult(1,intentResult);
                        activity.finish();//finishing activity
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
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }

}
