package com.example.udhay.attendence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    ArrayList<Integer> num = new ArrayList<Integer>();

    CustomAdapter adapter;

    ArrayList<Integer> arrayList;

    HashMap<Integer , Boolean> hashMap = new HashMap<>();

    static final int  totalStudents = 79;

    ListView list;


    {
        for (int i = 1; i <=totalStudents ; i++) {
            hashMap.put(i , false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

         list = (ListView)findViewById(R.id.list);

         arrayList = new ArrayList<Integer>();
         for(int i =1; i<=totalStudents ; i++) {
             if(i != totalStudents) {
                 arrayList.add(16103000 + i);
             }
             else{
                 arrayList.add(15103062);
             }
         }
        //ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext() , android.R.layout.simple_list_item_1 , arrayList);

       adapter = new CustomAdapter(this , arrayList , hashMap);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.v("message" , "on Item Click listener");

                CheckBox checkBox = view.findViewById(R.id.checkBox);

                checkBox.setChecked(!checkBox.isChecked());

                if(checkBox.isChecked()){
                    hashMap.put(position+1 , true);
                    Log.v("position" , Integer.toString(position+1)+hashMap.get(position+1));
                }
                else{
                    hashMap.put(position+1 , false);
                    Log.v("position" , Integer.toString(position+1)+ hashMap.get(position+1));
                }


            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       getMenuInflater().inflate(R.menu.menulist, menu);
       return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.send: {

                send();
                break;

            }

            case R.id.reset:{
                reset();
                break;
            }
        }
        return true;
    }

    private void send(){

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        String numbers =currentDateTimeString+"\n ";



        for(int i=1 ; i<=totalStudents ;i++){

            if(hashMap.get(i)){
                if(i != totalStudents){
                numbers += (i +" , ");}
                else if(i == totalStudents){

                    numbers += "15103062.";

                }
            }

        }

        Log.v("Number" , numbers);

        Toast.makeText(getApplicationContext() , numbers , Toast.LENGTH_LONG).show();

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, numbers.toString());
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);
    }

    private void reset() {

        Toast.makeText(getApplicationContext() , "This option is still in development" , Toast.LENGTH_LONG).show();
    }

}
