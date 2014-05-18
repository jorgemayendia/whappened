package io.github.androidhawks.whappened;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WhappenedArrayAdapter extends ArrayAdapter<String> {
  private final Context context;
  private final ArrayList<String> values;

  public WhappenedArrayAdapter(Context context, ArrayList<String> DAO_list) {
    super(context, R.layout.listview_view_events, DAO_list);
    this.context = context;
    this.values = DAO_list;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.listview_view_events, parent, false);
    ImageView is_rec_view = (ImageView) rowView.findViewById(R.id.is_rec_view);
    TextView  loc_view = (TextView) rowView.findViewById(R.id.loc_view);
    //TextView  d_t_view = (TextView) rowView.findViewById(R.id.d_t_view);
    TextView  com_view = (TextView) rowView.findViewById(R.id.com_view);
    String four_values = values.get(position);
    String[] parsed_values = four_values.split("[*^*]+");
    String rec = "recommened";
    if(rec.contentEquals(parsed_values[0])){
    	is_rec_view.setImageResource(R.drawable.happy_face);
    }
    else{
    	is_rec_view.setImageResource(R.drawable.sad_face);
    }
    loc_view.setText(parsed_values[1]);
   // d_t_view.setText(parsed_values[2]);
    com_view.setText(parsed_values[3]);
    return rowView;
  }
}