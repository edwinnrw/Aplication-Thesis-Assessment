package skripsi.edwin.filkomapps.detailNilai.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.model.orm.DetailNilai;


/**
 * Created by EDN on 1/15/2018.
 */

public class NilaiMajelisAdapter extends RecyclerView.Adapter<NilaiMajelisAdapter.ViewHolder> {
    private List<DetailNilai> mDataSet;
    Context context;


    public NilaiMajelisAdapter(Context context, List<DetailNilai> nilai) {
        this.context = context;
        mDataSet = nilai;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v;

        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_detail_nilai, viewGroup, false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(NilaiMajelisAdapter.ViewHolder viewHolder, final int position) {
        String[]abjad={"A","B","C","D","E","F","G"};
        final NilaiMajelisAdapter.ViewHolder holder = (ViewHolder) viewHolder;
        final DetailNilai iteminfo = (DetailNilai) mDataSet.get(position);
        holder.txtIndikator.setText(abjad[position]+"."+iteminfo.indikator);
        holder.txtNilai.setText(iteminfo.nilai);

    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_indikator)
        TextView txtIndikator;
        @BindView(R.id.txt_nilai)
        TextView txtNilai;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
