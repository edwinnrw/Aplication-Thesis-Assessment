package skripsi.edwin.filkomapps.penilaian.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.model.DataKomponen;
import skripsi.edwin.filkomapps.penilaian.presenter.PenilaianPresenter;
import skripsi.edwin.filkomapps.penilaian.view.PenilaianNaskahPublikasi;
import skripsi.edwin.filkomapps.penilaian.view.PenilaianUjianActivity;

/**
 * Created by EDN on 11/15/2017.
 */

public class PenilaianAdapter extends RecyclerView.Adapter<PenilaianAdapter.ViewHolder> {
    private List<DataKomponen> mDataSet;
    Context context;
    PenilaianPresenter penilaianPresenter;
    public PenilaianAdapter(Context context, List<DataKomponen> dataSet, PenilaianPresenter penilaianPresenter) {
        this.context = context;
        mDataSet = dataSet;
        this.penilaianPresenter=penilaianPresenter;

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v;

        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_komponen, viewGroup, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        final DataKomponen iteminfo = (DataKomponen) mDataSet.get(position);
        holder.txtKomponen.setText(String.valueOf(position)+"."+iteminfo.getKomponen());
        if (penilaianPresenter.checkIsDone(iteminfo.getId())){
            holder.txtKomponen.setCompoundDrawablesWithIntrinsicBounds(
                    context.getResources().getDrawable(R.drawable.ic_check_24dp),null,null,null);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iteminfo.getId().equalsIgnoreCase("6")){
                    Intent intent=new Intent(context, PenilaianNaskahPublikasi.class);
                    intent.putExtra("komponen", (Serializable) iteminfo);
                    intent.putExtra("posisiKomponen",position);
                    ((Activity) context).startActivityForResult(intent, 200);
                }else{
                    Intent intent=new Intent(context, PenilaianUjianActivity.class);
                    intent.putExtra("komponen", (Serializable) iteminfo);
                    intent.putExtra("posisiKomponen",position);
                    ((Activity) context).startActivityForResult(intent, 200);
                }

            }
        });
        holder.context=context;
    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        @BindView(R.id.txt_komponen)
        TextView txtKomponen;
        Context context;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);

        }
    }
}
