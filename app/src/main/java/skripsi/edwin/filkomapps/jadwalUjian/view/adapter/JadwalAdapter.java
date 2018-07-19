package skripsi.edwin.filkomapps.jadwalUjian.view.adapter;

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
import skripsi.edwin.filkomapps.util.DateUtils;


/**
 * Created by EDN on 1/15/2018.
 */

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder> {
    private List<JadwalUjian> mDataSet;
    Context context;



    public JadwalAdapter(Context context, List<JadwalUjian> dataSet) {
        this.context = context;
        mDataSet = dataSet;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v;

        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_jadwal_ujian, viewGroup, false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        JadwalUjian iteminfo = (JadwalUjian) mDataSet.get(position);
        holder.nama.setText(iteminfo.getNamaMahasiswa());
        holder.title.setText(iteminfo.getJudulSkripsi());
        holder.date.setText(DateUtils.convertUnixTimeStamp("dd MMM yyyy",iteminfo.getTanggal()));
        String jam[]=iteminfo.getJam().split(":");
        holder.time.setText(jam[0]+"."+jam[1]+" WIB");
        if (iteminfo.getIs_penguji()==1){
            if (iteminfo.getIs_ketua()==1){
                holder.peran.setBackgroundColor(context.getResources().getColor(R.color.colorKetuaMajelis));
                holder.peran.setText("Ketua Majelis");
            }else{
                holder.peran.setBackgroundColor(context.getResources().getColor(R.color.colorPenguji));
                holder.peran.setText("Penguji");
            }
        }else{
            holder.peran.setBackgroundColor(context.getResources().getColor(R.color.colorPembimbing));
            holder.peran.setText("Pembimbing");
        }
        holder.context=context;
        holder.jadwalUjian=iteminfo;
    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
    public void setFilter(List<JadwalUjian> models) {
        mDataSet= new ArrayList<>();
        mDataSet.addAll(models);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_title)
        TextView title;
        @BindView(R.id.txt_mahasiswa)
        TextView nama;
        @BindView(R.id.txt_date)
        TextView date;
        @BindView(R.id.txt_time)
        TextView time;
        @BindView(R.id.txt_peran)
        TextView peran;
        Context context;

        JadwalUjian jadwalUjian;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(context, DetailUjianActivity.class);

                    in.putExtra("info_ujian",(Serializable)jadwalUjian);
                    context.startActivity(in);
                }
            });

        }
    }
}
