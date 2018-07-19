package skripsi.edwin.filkomapps.jadwalUjian.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import butterknife.BindView;
import butterknife.ButterKnife;
import skripsi.edwin.filkomapps.R;


public class JadwalUjianMainFragment extends Fragment {
    @BindView(R.id.viewpager)
    ViewPager historyViewPager;
    @BindView(R.id.tab)
    ViewGroup tab;

    public JadwalUjianMainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jadwal_ujian, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        setTabLayout();
    }

    private void setTabLayout() {
        tab.addView(LayoutInflater.from(getActivity()).inflate(R.layout.tab_text, tab, false));
        SmartTabLayout historyTabLayout=tab.findViewById(R.id.viewpagertab);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getChildFragmentManager(), FragmentPagerItems.with(getActivity())
                .add("Terbaru", ListJadwalTerbaruFragment.class)
                .add("Selesai", ListRiwayatUjianFragment.class)


                .create());
        historyViewPager.setAdapter(adapter);
        historyTabLayout.setViewPager(historyViewPager);
    }


}
