package com.example.simon.gratisgoder.HelpClass;


        import android.app.Activity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.example.simon.gratisgoder.DataFromDB.Articles;
        import com.example.simon.gratisgoder.DataFromDB.Oplevelser;
        import com.example.simon.gratisgoder.R;
        import com.squareup.picasso.Picasso;

        import java.util.List;

public class CustomListAdapter extends ArrayAdapter<Oplevelser> {

    private  Activity context;
    private  List<Oplevelser> itemname;


    public CustomListAdapter(Activity context, List<Oplevelser> itemname) {
        super(context, R.layout.mylist, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;


    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.titel);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.sted);


        txtTitle.setText(getItem(position).getTitel());
        Picasso.with(context).load(getItem(position).getImage()).into(imageView);
        extratxt.setText(getItem(position).getSted());
        return rowView;

    };
}