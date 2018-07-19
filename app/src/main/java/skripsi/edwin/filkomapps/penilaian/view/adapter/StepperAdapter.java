package skripsi.edwin.filkomapps.penilaian.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import skripsi.edwin.filkomapps.penilaian.view.StepFragment;

public class StepperAdapter extends AbstractFragmentStepAdapter {
    int stepsize;
    public StepperAdapter(@NonNull FragmentManager fm, @NonNull Context context,int stepsize) {
        super(fm, context);
        this.stepsize=stepsize;
    }

    @Override
    public Step createStep(int position) {
        final StepFragment step = new StepFragment();
        Bundle b = new Bundle();
        b.putInt("posisi", position);
        step.setArguments(b);
        return step;    }

    @Override
    public int getCount() {
        return stepsize;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(int position) {
        return new StepViewModel.Builder(context).create();
    }
}
