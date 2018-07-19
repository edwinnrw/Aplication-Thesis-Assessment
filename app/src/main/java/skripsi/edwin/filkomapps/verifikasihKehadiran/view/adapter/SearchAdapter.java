package skripsi.edwin.filkomapps.verifikasihKehadiran.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.model.Dosen;

import skripsi.edwin.filkomapps.util.onClickListDialog;


/**
 * Created by EDN on 1/15/2018.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private List<Dosen> mDataSet;
    Context context;
    onClickListDialog onClickListDialog;

    public SearchAdapter(Context context, List<Dosen> dataSet) {
        this.context = context;
        mDataSet = dataSet;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v;

        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_dosen, viewGroup, false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        final Dosen iteminfo = (Dosen) mDataSet.get(position);
        holder.txtNama.setText(iteminfo.getNama());

        holder.txtMayor.setText("Mayor: ");
        holder.txtMinor.setText("Minor: ");
        for (int i=0; i<iteminfo.getMayor().size(); i++){
            if (i==iteminfo.getMayor().size()-1){
                holder.txtMayor.append(iteminfo.getMayor().get(i).getBidang());

            }else {
                if (i==iteminfo.getMayor().size()-2){
                    holder.txtMayor.append(iteminfo.getMayor().get(i).getBidang()+" dan ");

                }else{
                    holder.txtMayor.append(iteminfo.getMayor().get(i).getBidang()+",");

                }

            }
        }
        for (int i=0; i<iteminfo.getMinor().size(); i++){
            if (i==iteminfo.getMinor().size()-1){
                holder.txtMinor.append(iteminfo.getMinor().get(i).getBidang());
            }else {
                holder.txtMinor.append(iteminfo.getMinor().get(i).getBidang()+",");
            }
        }
        RequestOptions requestOptions=new RequestOptions()
                .error(R.drawable.ic_user)
                .placeholder(R.drawable.ic_user);
        Glide.with(context).load(iteminfo.getImg()).apply(requestOptions).into(holder.circleImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListDialog.onClick(iteminfo.getNama(),iteminfo.getId());
            }
        });
    }
    public void setOnClickDialog(onClickListDialog onClickDialog){
        this.onClickListDialog=onClickDialog;
    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
    public void setFilter(List<Dosen> models) {
        mDataSet= new ArrayList<>();
        mDataSet.addAll(models);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtNama)
        TextView txtNama;
        @BindView(R.id.txtMayor)
        TextView txtMayor;
        @BindView(R.id.txtMinor)
        TextView txtMinor;
        @BindView(R.id.imgDosen)
        CircleImageView circleImageView;
        @BindView(R.id.line)
        View line;
        Context context;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);

        }
    }
}
