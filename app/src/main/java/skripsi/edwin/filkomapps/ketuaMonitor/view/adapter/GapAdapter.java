package skripsi.edwin.filkomapps.ketuaMonitor.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.model.DetailGAP;
import skripsi.edwin.filkomapps.model.GAP;
import skripsi.edwin.filkomapps.model.orm.Notifikasi;
import skripsi.edwin.filkomapps.util.ExpandableTextView;


/**
 * Created by EDN on 1/15/2018.
 */

public class GapAdapter extends RecyclerView.Adapter<GapAdapter.ViewHolder> {
    private List<DetailGAP> mDataSet;
    Context context;



    public GapAdapter(Context context, List<DetailGAP> dataSet) {
        this.context = context;
        mDataSet = dataSet;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v;

        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_gap, viewGroup, false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        DetailGAP iteminfo = (DetailGAP) mDataSet.get(position);
        holder.indikator.setText(iteminfo.getNamaIndikator());
        holder.komponen.setText(iteminfo.getNamaKomponen());
        for (int i=0; i<iteminfo.getNilai().size();i++){
            switch (i){
                case 0:
                    holder.nilai1.setText(iteminfo.getNilai().get(0).get("nilai"));
                    holder.nama1.setText(iteminfo.getNilai().get(0).get("nama"));
                    holder.ln_nilai1.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    holder.nilai2.setText(iteminfo.getNilai().get(1).get("nilai"));
                    holder.nama2.setText(iteminfo.getNilai().get(1).get("nama"));
                    holder.ln_nilai2.setVisibility(View.VISIBLE);

                    break;
                case 2:
                    holder.nilai3.setText(iteminfo.getNilai().get(2).get("nilai"));
                    holder.nama3.setText(iteminfo.getNilai().get(2).get("nama"));
                    holder.ln_nilai3.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    holder.nilai4.setText(iteminfo.getNilai().get(3).get("nilai"));
                    holder.nama4.setText(iteminfo.getNilai().get(3).get("nama"));
                    holder.ln_nilai4.setVisibility(View.VISIBLE);
                    break;

            }
        }





    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_komponen)
        TextView komponen;
        @BindView(R.id.txt_indikator)
        TextView indikator;
        @BindView(R.id.txt_nama1)
        ExpandableTextView nama1;
        @BindView(R.id.nilai1)
        TextView nilai1;
        @BindView(R.id.txt_nama2)
        ExpandableTextView nama2;
        @BindView(R.id.nilai2)
        TextView nilai2;
        @BindView(R.id.txt_nama3)
        ExpandableTextView nama3;
        @BindView(R.id.nilai3)
        TextView nilai3;
        @BindView(R.id.txt_nama4)
        ExpandableTextView nama4;
        @BindView(R.id.nilai4)
        TextView nilai4;
        @BindView(R.id.ln_nilai1)
        LinearLayout ln_nilai1;
        @BindView(R.id.ln_nilai2)
        LinearLayout ln_nilai2;
        @BindView(R.id.ln_nilai3)
        LinearLayout ln_nilai3;
        @BindView(R.id.ln_nilai4)
        LinearLayout ln_nilai4;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            nama1.setTrimLength(8);
            nama2.setTrimLength(8);
            nama3.setTrimLength(8);
            nama4.setTrimLength(8);


        }
    }
}
