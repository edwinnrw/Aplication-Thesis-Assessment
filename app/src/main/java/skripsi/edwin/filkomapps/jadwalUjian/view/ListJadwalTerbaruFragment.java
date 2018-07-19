package skripsi.edwin.filkomapps.jadwalUjian.view;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.supercharge.shimmerlayout.ShimmerLayout;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.jadwalUjian.presenter.presenterJadwalTerbaru.ListJadwalContract;
import skripsi.edwin.filkomapps.jadwalUjian.presenter.presenterJadwalTerbaru.ListJadwalPresenter;
import skripsi.edwin.filkomapps.jadwalUjian.view.adapter.JadwalAdapter;
import skripsi.edwin.filkomapps.model.JadwalUjian;
import skripsi.edwin.filkomapps.util.GuideUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListJadwalTerbaruFragment extends Fragment implements ListJadwalContract.View, SearchView.OnQueryTextListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.ln_empty)
    LinearLayout ln_empty;
    @BindView(R.id.txt_empty)
    TextView txt_empty;
    @BindView(R.id.shimmer_view_container)
    ShimmerLayout shimmerFrameLayout;
    @BindView(R.id.search)
    SearchView searchView;
    @BindView(R.id.btn_peran)
    LinearLayout btn_peran;
    ListJadwalPresenter jadwalPresenter;
    JadwalAdapter adapter;
    private List<JadwalUjian> dataJadwalUjianList;
    List<String> seletedItems;
    public ListJadwalTerbaruFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_jadwal_terbaru, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        jadwalPresenter=new ListJadwalPresenter(getActivity(),this);
        jadwalPresenter.getJadwal();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        dataJadwalUjianList=new ArrayList<>();
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Nama Mahasiswa");
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        seletedItems=new ArrayList();
        GuideUtil.isShowGuideJadwal(getActivity(),searchView,btn_peran);
    }
    @OnClick(R.id.btn_peran)
    public void onClickPeran(){
        seletedItems.clear();
        final String[]peran=getActivity().getResources().getStringArray(R.array.peran);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Peran")
                .setMultiChoiceItems(getActivity().getResources().getStringArray(R.array.peran),
                        null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    seletedItems.add(peran[which]);
                                } else if (seletedItems.contains(peran[which])) {
                                    // Else, if the item is already in the array, remove it
                                    seletedItems.remove(peran[which]);
                                }
                            }
                        })
                .setPositiveButton("Terapkan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        jadwalPresenter.filterJadwal(seletedItems,searchView.getQuery().toString());
                    }
                }).show();
    }
    @Override
    public void showProgress() {
        recyclerView.setVisibility(View.GONE);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmerAnimation();
    }

    @Override
    public void dismisProgress() {
        recyclerView.setVisibility(View.VISIBLE);
        shimmerFrameLayout.stopShimmerAnimation();
        shimmerFrameLayout.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setFilterJadwal(List<JadwalUjian> jadwalUjian) {
        dataJadwalUjianList.clear();
        dataJadwalUjianList=jadwalUjian;
        adapter=new JadwalAdapter(getActivity(),jadwalUjian);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setVisibility(View.VISIBLE);
        ln_empty.setVisibility(View.GONE);
    }

    @Override
    public void setJadwal(List<JadwalUjian> jadwalUjian) {
        dataJadwalUjianList=jadwalUjian;
        adapter=new JadwalAdapter(getActivity(),jadwalUjian);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setVisibility(View.VISIBLE);
        ln_empty.setVisibility(View.GONE);
    }

    @Override
    public void setEmptyView() {
        ln_empty.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Intent intent=new Intent(getActivity(),ResultSearchActivity.class);
        intent.putExtra("search",query);
        intent.putExtra("isComplete","0");
        intent.putExtra("peran", (Serializable) seletedItems);
        startActivity(intent);
//        jadwalPresenter.filterJadwal(seletedItems, query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


}
