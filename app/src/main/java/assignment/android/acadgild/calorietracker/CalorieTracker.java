package assignment.android.acadgild.calorietracker;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.DatePickerDialog;

import java.text.DateFormat;
import java.util.ArrayList;import android.app.DatePickerDialog;

import android.app.TimePickerDialog;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.DatePicker;

import android.widget.TextView;

import android.widget.TimePicker;



import java.text.DateFormat;

import java.util.Calendar;
import java.util.Calendar;

import assignment.android.acadgild.R;
import assignment.android.acadgild.calorietracker.database.*;
import assignment.android.acadgild.calorietracker.database.Constants;
import assignment.android.acadgild.calorietracker.listView.CalorieAdapter;
import assignment.android.acadgild.calorietracker.object.CalorieObject;
import assignment.android.acadgild.nav.Calender;

/**
 * Created by DivyaVipin on 1/19/2017.
 */

public class CalorieTracker extends AppCompatActivity{

    FloatingActionButton btnAdd;
    Dialog d;
    EditText calValueDialog;
    Spinner spactivity;
    String c_Value=null;
    int cValue=0;
    String activityname="";
    Button btnSave;
    Button btnDate;
    DBAdapter db;
    Constants c;
    String dmy;
    EditText dateCalorie;
    String cal_val_dialog;
    DatePicker datePicker;
    CalorieAdapter caladapter;
    String new_Calorie;
    int new_Calorie_value;
    Calendar dateTime=Calendar.getInstance();
    ArrayList<CalorieObject> caloriedetails=new ArrayList<>();
    ListView lv;
int day,month,year;
DateFormat formatDate=DateFormat.getDateTimeInstance();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calorie_tracker);
       // final TextView maxCalorieBurned = (TextView) findViewById(R.id.txtViewMCalValue);
        btnAdd=(FloatingActionButton)findViewById(R.id.addDetails);
        lv=(ListView)findViewById(R.id.listViewCalorie) ;

        db=new DBAdapter(CalorieTracker.this);
        db.openDB();

        caladapter=new CalorieAdapter(this,caloriedetails);
        lv.setAdapter(caladapter);
        registerForContextMenu(lv);

        getCalorieDetails();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d=new Dialog(CalorieTracker.this);
                d.setTitle("Add Calorie Details");
                d.setContentView(R.layout.cal_track_dialog);
                dateCalorie=(EditText)d.findViewById(R.id.editTextDate) ;
                calValueDialog = (EditText) d.findViewById(R.id.editTextCalorie);
                btnDate=(Button)d.findViewById(R.id.btnCalDate);
                final  DatePickerDialog.OnDateSetListener dialog = new DatePickerDialog.OnDateSetListener() {

                    @Override

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        dateTime.set(Calendar.YEAR, year);

                        dateTime.set(Calendar.MONTH, monthOfYear);

                        dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dateCalorie.setText(formatDate.format(dateTime.getTime()));
                        // updateTextLabel();

                    }

                };
                btnDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new DatePickerDialog(CalorieTracker.this,dialog, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });



                spactivity=(Spinner)d.findViewById(R.id.spnActivity);

                String[] activities = new String[] { "Swimming","Walking","Dancing","Hockey" };
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(CalorieTracker.this,android.R.layout.simple_spinner_item,activities);
                spactivity.setAdapter(adapter);
             btnSave=(Button)d.findViewById(R.id.btnCSave);
                d.show();

                btnSave.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        try {
                            new_Calorie=calValueDialog.getText().toString();
                            new_Calorie_value = Integer.parseInt(new_Calorie);
                        }
                        catch(NumberFormatException e)
                        {
                            new_Calorie_value=0;
                        }




                       // db.add(spactivity.getSelectedItem().toString(),Integer.parseInt(calValueDialog.getText().toString()),dmy);
                        db.add(spactivity.getSelectedItem().toString(),new_Calorie_value,dateCalorie.getText().toString());
                        d.dismiss();
                        getCalorieDetails();

                    }
                });




            }
        });


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.listViewCalorie) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle("Select the Action");

            menu.add(0, v.getId(), 0, "Delete");
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {


        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int listPosition=info.position;
        int id=caladapter.getCalDetails().get(listPosition).getId();
        if(item.getTitle()=="Delete")
        {
            deleteDetails(id);

        }



        return super.onContextItemSelected(item);

    }

    private void getCalorieDetails()
    {
        caloriedetails.clear();
        CalorieObject calorieObject=null;
        Cursor c=db.retrieve();
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String activity_name=c.getString(1);
            int cal_value=c.getInt(2);
            String date=c.getString(3);
            calorieObject=new CalorieObject();
            calorieObject.setId(id);
           calorieObject.setActivity(activity_name);
calorieObject.setCalValue(cal_value);
            calorieObject.setDate(" "+date);
            caloriedetails.add(calorieObject);
        }
        lv.setAdapter(caladapter);

    }
    private void deleteDetails(int id)
    {
        Toast.makeText(getApplicationContext(), "This is deleted.", Toast.LENGTH_SHORT).show();

        db.deleteRecords(Constants.TB_NAME, Constants.ROW_ID+ " = '" +id+
                "' ", null);
        finish();
        getCalorieDetails();
    }
}
