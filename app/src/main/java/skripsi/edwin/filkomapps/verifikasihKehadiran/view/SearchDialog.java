package skripsi.edwin.filkomapps.verifikasihKehadiran.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.model.Dosen;
import skripsi.edwin.filkomapps.util.onClickListDialog;
import skripsi.edwin.filkomapps.verifikasihKehadiran.presenter.DialogContract;
import skripsi.edwin.filkomapps.verifikasihKehadiran.presenter.DialogPresenter;
import skripsi.edwin.filkomapps.verifikasihKehadiran.view.adapter.SearchAdapter;

public class SearchDialog extends Dialog implements SearchView.OnQueryTextListener, DialogContract.View, onClickListDialog {
    @BindView(R.id.btn_close)
    Button btn_close;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.ln_progress)
    RelativeLayout progress;
    DialogPresenter dialogPresenter;
    Context context;
    SearchAdapter searchAdapter;
    List<Dosen> listDosen;
    onDialogResult onDialogResult;
    List<HashMap<String, String>> dataDosen;
    public SearchDialog(@NonNull Context context, int themeResId, List<HashMap<String, String>> dataDosen) {
        super(context,themeResId);
        this.context=context;
        this.dataDosen=dataDosen;
    }

    public SearchDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context=context;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_search_dosen);
        ButterKnife.bind(this);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search");
        listDosen=new ArrayList<>();
        dialogPresenter=new DialogPresenter(context,this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        dialogPresenter.getListDosen(dataDosen);
    }
    @OnClick(R.id.btn_close)
    public void onClickClose(){
        cancel();
    }

    @Override
    public void cancel() {
        super.cancel();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<Dosen> filteredModelList = filter(listDosen, newText);

        searchAdapter.setFilter(filteredModelList);
        return true;    }

    @Override
    public void showListDosen(List<Dosen> listDosen) {
        this.listDosen=listDosen;
        searchAdapter=new SearchAdapter(context,listDosen);
        searchAdapter.setOnClickDialog(this);
        recyclerView.setAdapter(searchAdapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void showMessageError(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);

    }

    @Override
    public void dismissProgress() {
        progress.setVisibility(View.GONE);
    }
    private List<Dosen> filter(List<Dosen> models, String query) {
        query = query.toLowerCase();
        final List<Dosen> filteredModelList = new ArrayList<>();
        for (Dosen model : models) {
            final String text = model.getNama().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    @Override
    public void onClick(String name, String id) {
        onDialogResult.finish(name,id);
        dismiss();
    }
    public void setDialogResult(onDialogResult dialogResult){
        this.onDialogResult = dialogResult;
    }
    public interface onDialogResult{
        void finish(String name,String id);
    }
}
