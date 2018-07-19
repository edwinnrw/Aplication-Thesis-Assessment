package skripsi.edwin.filkomapps.notifikasi.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.detailUjian.view.DetailUjianActivity;
import skripsi.edwin.filkomapps.model.JadwalUjian;
import skripsi.edwin.filkomapps.model.orm.Notifikasi;
import skripsi.edwin.filkomapps.util.DateUtils;
import skripsi.edwin.filkomapps.util.ExpandableTextView;


/**
 * Created by EDN on 1/15/2018.
 */

public class NotifikasiAdapter extends RecyclerView.Adapter<NotifikasiAdapter.ViewHolder> {
    private List<Notifikasi> mDataSet;
    Context context;



    public NotifikasiAdapter(Context context, List<Notifikasi> dataSet) {
        this.context = context;
        mDataSet = dataSet;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v;

        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_notif, viewGroup, false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        Notifikasi iteminfo = (Notifikasi) mDataSet.get(position);
        holder.detail.setText(iteminfo.getBody());
        holder.time.setText(iteminfo.getTime());
        holder.date.setText(iteminfo.getDate());

    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.expandable_detail)
        ExpandableTextView detail;
        @BindView(R.id.txt_date)
        TextView date;
        @BindView(R.id.txt_time)
        TextView time;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);


        }
    }
}
