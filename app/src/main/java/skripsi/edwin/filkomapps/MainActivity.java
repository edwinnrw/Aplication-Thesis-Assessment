package skripsi.edwin.filkomapps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;

import butterknife.BindView;
import butterknife.ButterKnife;
import skripsi.edwin.filkomapps.home.view.HomeFragment;
import skripsi.edwin.filkomapps.jadwalUjian.view.JadwalUjianMainFragment;
import skripsi.edwin.filkomapps.login.view.LoginActivity;
import skripsi.edwin.filkomapps.util.GuideUtil;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainActivityContract.View {
    @BindView(R.id.toolbar)
    public  Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    public NavigationView navigationView;
    LinearLayout nav_header;
    TextView txt_nama;
    TextView txt_nik;
    MainActivityPresenter mainActivityPresenter;
    ProgressDialog progressDialog;
    boolean twice = false;

    public Fragment currentFragment(){
        return getSupportFragmentManager().findFragmentByTag("Fragment");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        changeFragment(new HomeFragment());
        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.setSelected(false);

        View nav = navigationView.getHeaderView(0);
        nav_header=nav.findViewById(R.id.nav_header);
        txt_nama=nav_header.findViewById(R.id.txt_nama);
        txt_nik=nav_header.findViewById(R.id.txt_nik);
        getSupportActionBar().setTitle("Beranda");
        mainActivityPresenter=new MainActivityPresenter(this,this);
        mainActivityPresenter.getDataProfile();
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
//        showSideNavigationPrompt();
     }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (!GuideUtil.isRunningGuide){
                if (currentFragment() instanceof HomeFragment){
                    if (twice){
                        finish();

                    }
                    this.twice = true;
                    Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            twice=false;

                        }
                    }, 2000);
                }else{
                    getSupportActionBar().setTitle("Beranda");
                    changeFragment(new HomeFragment());
                    navigationView.setCheckedItem(R.id.nav_home);
                }
            }

        }
    }


    public void changeFragment(Fragment frag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.content_body, frag,"Fragment");
        transaction.commit();
        fm.executePendingTransactions();

    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportActionBar().setTitle("Beranda");
                changeFragment(new HomeFragment());
                navigationView.setCheckedItem(item.getItemId());
                break;
            case R.id.nav_jadwal:
                getSupportActionBar().setTitle("Jadwal Ujian");
                changeFragment(new JadwalUjianMainFragment());
                navigationView.setCheckedItem(item.getItemId());
                break;
            case R.id.nav_logout:
                if (currentFragment() instanceof HomeFragment){
                    navigationView.setCheckedItem(R.id.nav_home);
                }else{

                    navigationView.setCheckedItem(R.id.nav_jadwal);
                }
                mainActivityPresenter.logout();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void setNavProfile(String name, String nik) {
        txt_nama.setText(name);
        txt_nik.setText(nik);
    }

    @Override
    public void logoutSuccsess() {
        Intent intent=new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void logoutFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showProgress() {
        progressDialog.show();

    }

    @Override
    public void dismisProgress() {
        progressDialog.dismiss();

    }


}
