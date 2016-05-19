package com.example.ulouigene.florida_crud;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
/**
 * Created by ulouigene on 5/11/2016.
 */
public class CustomAdapter extends ArrayAdapter<PowerBall> {

    public CustomAdapter(Context context, int resource, List<PowerBall> powerball) {
        super(context, resource, powerball);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.view_powerball_entry, parent, false);
        }

        PowerBall powerball = getItem(position);

        if (powerball != null) {
            TextView tvStudentId = (TextView) v.findViewById(R.id.student_Id);
            TextView tvStudentName = (TextView) v.findViewById(R.id.student_name);
            tvStudentId.setText(Long.toString(powerball.ID));
            tvStudentName.setText(powerball.First);
        }

        return v;
    }

}
