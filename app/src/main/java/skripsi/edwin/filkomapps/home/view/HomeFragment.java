package skripsi.edwin.filkomapps.home.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.supercharge.shimmerlayout.ShimmerLayout;
import skripsi.edwin.filkomapps.MainActivity;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.home.presenter.HomeContract;
import skripsi.edwin.filkomapps.home.presenter.HomePresenter;
import skripsi.edwin.filkomapps.jadwalUjian.view.adapter.JadwalAdapter;
import skripsi.edwin.filkomapps.model.JadwalUjian;
import skripsi.edwin.filkomapps.notifikasi.view.NotificationActivity;
import skripsi.edwin.filkomapps.preference.PrefUtil;
import skripsi.edwin.filkomapps.util.GuideUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.txt_empty)
    LinearLayout txt_empty;

    HomePresenter homePresenter;
    JadwalAdapter adapter;
    @BindView(R.id.shimmer_view_container)
    ShimmerLayout shimmerFrameLayout;
    public  View badge;
    MainActivity activity;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);
        return view;    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        homePresenter=new HomePresenter(getActivity(),this);
        homePresenter.getJadwal();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem item1 = menu.findItem(R.id.menu_messages);

        MenuItemCompat.setActionView(item1, R.layout.badge_layout);
        RelativeLayout notificationCount1 = (RelativeLayout) MenuItemCompat.getActionView(item1);
        badge=notificationCount1.findViewById(R.id.badge_notification_1);
        homePresenter.isNotif();
        ImageView btn_notif=notificationCount1.findViewById(R.id.notif_btn);
        btn_notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), NotificationActivity.class),1);
            }
        });
        activity=(MainActivity)getActivity();
        GuideUtil.isShowGuideHome(activity,activity.toolbar,btn_notif);

    }

    @Override
    public void setJadwal(List<JadwalUjian> jadwal) {
        txt_empty.setVisibility(View.GONE);
        adapter=new JadwalAdapter(getActivity(),jadwal);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
        recyclerView.setVisibility(View.VISIBLE);

    }

    @Override
    public void showProress() {
        shimmerFrameLayout.startShimmerAnimation();

    }

    @Override
    public void dismisProgrss() {
        shimmerFrameLayout.stopShimmerAnimation();
        shimmerFrameLayout.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showBadge() {
        badge.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBadge() {
        badge.setVisibility(View.GONE);
    }

    @Override
    public void setEmptyJadwal() {
        recyclerView.setVisibility(View.GONE);
        txt_empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        homePresenter.isNotif();
    }



}
