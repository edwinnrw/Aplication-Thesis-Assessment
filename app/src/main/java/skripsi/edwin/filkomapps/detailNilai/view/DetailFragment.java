package skripsi.edwin.filkomapps.detailNilai.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.detailNilai.presenter.DetailNilaiMajelisContract;
import skripsi.edwin.filkomapps.detailNilai.presenter.DetailNilaiMajelisPresenter;
import skripsi.edwin.filkomapps.detailNilai.view.adapter.DetailAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements DetailNilaiMajelisContract.View {
    DetailNilaiMajelisPresenter presenter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    DetailAdapter adapter;
    @BindView(R.id.txt_empty)
    TextView txt_empty;
    @BindView(R.id.txt_dosen)
    TextView txtNamaDosen;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this,view);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        presenter=new DetailNilaiMajelisPresenter(getActivity(),this);
        presenter.getNilai(FragmentPagerItem.getPosition(getArguments()));
    }

    @Override
    public void setNilai(List<HashMap<String, Object>> list, String namaDosen, String peran) {
        adapter=new DetailAdapter(getActivity(),list,peran);
        recyclerView.setAdapter(adapter);
        txt_empty.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        txtNamaDosen.setText(namaDosen);

    }




}
