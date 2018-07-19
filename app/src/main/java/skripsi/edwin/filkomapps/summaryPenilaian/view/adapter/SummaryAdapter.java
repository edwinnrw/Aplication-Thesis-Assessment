package skripsi.edwin.filkomapps.summaryPenilaian.view.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.model.DataKomponen;
import skripsi.edwin.filkomapps.model.orm.NilaiSaya;
import skripsi.edwin.filkomapps.summaryPenilaian.view.SummaryPenilaianActivity;


/**
 * Created by EDN on 1/15/2018.
 */

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.ViewHolder> {
    Context context;
    NilaiAdapter nilaiAdapter;
    List<HashMap<String,Object>>dataSet;

    public SummaryAdapter(Context context, List<HashMap<String, Object>> list) {
        this.context = context;
        this.dataSet = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v;

        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.section_list_nilai, viewGroup, false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        HashMap<String,Object> iteminfo = (HashMap<String,Object>) dataSet.get(position);
        holder.context=context;
        nilaiAdapter=new NilaiAdapter(context,(List<NilaiSaya>)iteminfo.get("nilai"));
        holder.komponen.setText((String)iteminfo.get("namaKomponen"));
        holder.recyclerView.setAdapter(nilaiAdapter);

    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtKomponen)
        TextView komponen;
        @BindView(R.id.recycler_view)
        RecyclerView recyclerView;
        Context context;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setAutoMeasureEnabled(true);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

        }
    }
}
