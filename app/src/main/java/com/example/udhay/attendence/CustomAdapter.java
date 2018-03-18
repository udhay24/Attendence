package com.example.udhay.attendence;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by udhay on 01-02-2018.
 */

public class CustomAdapter extends ArrayAdapter<Integer> {

    HashMap<Integer , Boolean> studentMap;

    public CustomAdapter(Context context, ArrayList<Integer> list , HashMap<Integer , Boolean> hashMap){
        super(context , 0 , list);
        this.studentMap = hashMap;

    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentView;

        if(convertView == null){
           currentView = (LayoutInflater.from(getContext())).inflate(R.layout.item , parent , false);

        }else{
            currentView = convertView;
        }

        TextView number = (TextView)currentView.findViewById(R.id.Number);

      CheckBox checkBox = currentView.findViewById(R.id.checkBox);

      if(studentMap.get(position+1)) {
          checkBox.setChecked(true);
          Log.v("position" , Integer.toString(position+1)+"true");
      }
      else{
          checkBox.setChecked(false);
          Log.v("position" , Integer.toString(position+1)+"false");
      }


      if(position == MainActivity.totalStudents){

          number.setText(Integer.toString(15103062));

      }

        number.setText(getItem(position).toString());

return currentView;
    }
}
