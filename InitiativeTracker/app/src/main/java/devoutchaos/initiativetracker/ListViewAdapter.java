package devoutchaos.initiativetracker;

import java.lang.Object;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by chaos_000 on 25/07/2016.
 */

public class ListViewAdapter extends BaseAdapter{

    Activity context;
    String name[];
    Integer pasPer[];
    Integer init[];

    public ListViewAdapter(Activity context, String[] name, Integer[] pasPer, Integer[] init)
    {
        super();
        this.context = context;
        this.name = name;
        this.pasPer = pasPer;
        this.init = init;
    }

    public int getCount()
    {
        return name.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder
    {
        TextView txtName;
        TextView txtPasPer;
        TextView txtInitiative;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            holder.txtPasPer = (TextView) convertView.findViewById(R.id.txtPasPer);
            holder.txtInitiative= (TextView) convertView.findViewById(R.id.txtInitiative);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtName.setText(name[position]);
        holder.txtPasPer.setText(pasPer[position].toString());
        holder.txtInitiative.setText(init[position].toString());

        return convertView;
    }
}
