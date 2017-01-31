package kristytsoi.spendingapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kristy on 31/01/2017.
 */
public class MenuListAdapter extends ArrayAdapter<MenuListItemDetails>{

    public  MenuListAdapter(Context context, MenuListItemDetails[] values){
        super(context,R.layout.main_detail_row_layout,values);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from((getContext()));
        View view = inflater.inflate(R.layout.main_detail_row_layout, parent, false);

        MenuListItemDetails rowDetails = getItem(position);
        TextView description = (TextView) view.findViewById(R.id.detailDescription);
        description.setText(rowDetails.detail);

        TextView value = (TextView) view.findViewById((R.id.textView));
        value.setText(rowDetails.valueText);

        return view;
    }
}
