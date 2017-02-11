package assignment.android.acadgild.nav;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import assignment.android.acadgild.R;
/**
 * Created by DivyaVipin on 2/4/2017.
 */

public class NavigationAdapter  extends ArrayAdapter<String> {

    String[] names={};

    int[] image={};
    Context c;
    LayoutInflater inflator;

    public NavigationAdapter(Context context,String[] names,int[] image) {
        super(context,R.layout.listrow_navigation,names);
        this.names=names;

        this.c=context;
        this.image=image;
    }
    public class ViewHolder
    {
        TextView contactName;


        ImageView contactImage;


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            inflator=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflator.inflate(R.layout.listrow_navigation,null);

        }
        final ViewHolder holder=new ViewHolder();
        holder.contactName=(TextView)convertView.findViewById(R.id.txtViewOption);

        holder.contactImage=(ImageView)convertView.findViewById(R.id.imageViewOption);
        holder.contactImage.setImageResource(image[position]);
        holder.contactName.setText(names[position]);

        return convertView;
    }
}
