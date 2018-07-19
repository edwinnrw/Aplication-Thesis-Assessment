package skripsi.edwin.filkomapps.ketuaMonitor.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.model.DataKomponen;
import skripsi.edwin.filkomapps.summaryPenilaian.presenter.SummaryContract;
import skripsi.edwin.filkomapps.summaryPenilaian.presenter.SummaryPresenter;
import skripsi.edwin.filkomapps.summaryPenilaian.view.adapter.SummaryAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryPenilaianFragement extends Fragment implements SummaryContract.View {

    SummaryPresenter presenter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    SummaryAdapter adapter;
    public SummaryPenilaianFragement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_summary_penilaian_fragement, container, false);
        ButterKnife.bind(this,view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        presenter=new SummaryPresenter(getActivity(),this);
        presenter.getNilai();
        return view;
    }



    @Override
    public void onStart() {
        super.onStart();
        presenter.getNilai();

    }

    @Override
    public void setNilai(List<HashMap<String, Object>> list) {
        adapter=new SummaryAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);

    }
}
