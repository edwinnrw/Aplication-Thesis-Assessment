package skripsi.edwin.filkomapps.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.List;

import skripsi.edwin.filkomapps.R;
import skripsi.edwin.filkomapps.model.orm.StatusKelulusan;


/**
 * Created by EDN on 12/22/2017.
 */

public class CustomArrayAdapater extends BaseAdapter implements SpinnerAdapter {

    private final Context activity;
    String []list;
    public CustomArrayAdapater(Context context, String[] list) {
        activity = context;
        this.list=list;
    }



    public int getCount()
    {
        return list.length;
    }

    public Object getItem(int i)
    {
        return list[i];
    }

    public long getItemId(int i)
    {
        return (long)i;
    }



    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v= inflater.inflate(R.layout.spinner_item_alasan, null);
        TextView textView = (TextView) v.findViewById(R.id.txt_spinner);
        textView.setText(list[position]);
        textView.setPadding(20,15,10,15);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setTextColor(activity.getResources().getColor(R.color.colorBlackFilkom));
        return  v;
    }

    public View getView(int i, View view, ViewGroup viewgroup) {
        TextView txt = new TextView(activity);
        txt.setGravity(Gravity.LEFT);
        txt.setPadding(0, 8, 0, 8);
        txt.setTextColor(activity.getResources().getColor(R.color.colorBlackFilkom));
        txt.setTextSize(16);
        txt.setText(list[i]);
        return  txt;
    }

}

