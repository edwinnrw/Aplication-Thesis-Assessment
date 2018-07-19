package skripsi.edwin.filkomapps.summaryPenilaian.view.adapter;

import android.content.Context;
import android.content.Intent;
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
import skripsi.edwin.filkomapps.model.orm.Indikator;
import skripsi.edwin.filkomapps.model.orm.NilaiSaya;
import skripsi.edwin.filkomapps.summaryPenilaian.view.EditNilaiPublikasiDialog;
import skripsi.edwin.filkomapps.summaryPenilaian.view.EditNilaiUjianActivity;


/**
 * Created by EDN on 1/15/2018.
 */

public class NilaiAdapter extends RecyclerView.Adapter<NilaiAdapter.ViewHolder> {
    private List<NilaiSaya> mDataSet;
    Context context;


    public NilaiAdapter(Context context, List<NilaiSaya> nilai) {
        this.context = context;
        mDataSet = nilai;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v;

        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_summary_nilai, viewGroup, false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        String[]abjad={"A","B","C","D","E","F"};
        final ViewHolder holder = (ViewHolder) viewHolder;
        final NilaiSaya iteminfo = (NilaiSaya) mDataSet.get(position);
        holder.txtIndikator.setText(iteminfo.indikator);
        holder.txtNilai.setText(iteminfo.nilai);
        holder.txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iteminfo.idKomponen.equalsIgnoreCase("6")){
                    EditNilaiPublikasiDialog editNilaiPublikasiDialog=new EditNilaiPublikasiDialog(context,iteminfo);
                    editNilaiPublikasiDialog.show();
                    editNilaiPublikasiDialog.setDialogResult(new EditNilaiPublikasiDialog.onDialogResult() {
                        @Override
                        public void finish(String nilai) {
                            holder.txtNilai.setText(nilai);
                        }
                    });
                }else{
                    Intent intent=new Intent(context, EditNilaiUjianActivity.class);
                    intent.putExtra("posisiIndikator",position);
                    intent.putExtra("idKomponen",iteminfo.idKomponen);
                    intent.putExtra("idIndikator",iteminfo.idIndikator);

                    intent.putExtra("namaIndikator",iteminfo.indikator);
                    intent.putExtra("nilai",holder.txtNilai.getText());
                    context.startActivity(intent);
                }

            }
        });

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
        @BindView(R.id.txt_edit)
        TextView txtEdit;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
