package assignment.android.acadgild.nav;

import android.animation.Animator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import assignment.android.acadgild.R;
import assignment.android.acadgild.calorietracker.CalorieTracker;
import assignment.android.acadgild.weighttips.WeightLossTips;

import java.io.File;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    DrawerLayout drawer;
    String[] names={"Weight Loss Foods","BMI Calculator","GM Diet Plan",
            "Calorie Calculator","Weight Loss Tips","Calorie Tracker","Magic Weight Loss Drink","Calender"};

    int[] images={R.drawable.weight5,R.drawable.bmi,R.drawable.gm_weightloss,R.drawable.calculator,
            R.drawable.w_loss,R.drawable.w8,R.drawable.magicdrinkweightloss,R.drawable.calenderimage};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
listView=(ListView)findViewById(R.id.listViewNavigation);
        NavigationAdapter adapter=new NavigationAdapter(this,names,images);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new DrawerItemClickListener());


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

       NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView parent, View view, int position,long id) {

            // Highlight the selected item, update the title, and close the drawer
            // update selected item and title, then close the drawer
            listView.setItemChecked(position, true);
            selectItem(position);

        }
    }
    private void selectItem(int position) {

        // update the main content by replacing fragments
         if(position ==0)
        {
        Intent weight=new Intent(MainActivity.this,WeightLossFoods.class);
        startActivity(weight);
        }
        else if(position ==1)
        {
            Intent bmi=new Intent(MainActivity.this,BMICalculator.class);
            startActivity(bmi);
        }
        else if(position ==2)
        {
            Intent gm=new Intent(MainActivity.this,GMDiet.class);
            startActivity(gm);
        }
        else if(position ==3)
        {
            Intent cal=new Intent(MainActivity.this,CalorieCalculator.class);
            startActivity(cal);
        }
        else if(position == 4)
        {
            Intent weighttip=new Intent(MainActivity.this,WeightLossTips.class);
            startActivity(weighttip);
        }
        else if(position == 5)
        {
            Intent tracker=new Intent(MainActivity.this,CalorieTracker.class);
            startActivity(tracker);
        }
        else if(position ==6)
        {
            Intent drink=new Intent(MainActivity.this,MagicDrink.class);
            startActivity(drink);
        }
        else
        {
            Intent calender=new Intent(MainActivity.this,Calender.class);
            startActivity(calender);
        }


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

         if(id==R.id.action_shareus)
        {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Your body here";
            String shareSub = "Your subject here";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share using"));

            return true;
        }
else
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_weightlossfoods) {
            Intent weightloss_foods=new Intent(MainActivity.this,WeightLossFoods.class);
            startActivity(weightloss_foods);

        } else if (id == R.id.nav_bmicalculator) {
            Intent bmi_calculator=new Intent(MainActivity.this,BMICalculator.class);
            startActivity(bmi_calculator);

        } else if (id == R.id.nav_losskg) {
            Intent gm_diet=new Intent(MainActivity.this,GMDiet.class);
            startActivity(gm_diet);

        } else if (id == R.id.nav_calorie_calculator) {
            Intent cal_calculator=new Intent(MainActivity.this,CalorieCalculator.class);
            startActivity(cal_calculator);

        } else if (id == R.id.nav_weight_tips) {
            Intent weight_tips=new Intent(MainActivity.this, WeightLossTips.class);
            startActivity(weight_tips);
        }
        else if(id == R.id.nav_calorie_tracker) {
            Intent calorie_tracker=new Intent(MainActivity.this,CalorieTracker.class);
            startActivity(calorie_tracker);
        }
        else if(id == R.id.nav_magic_Drink) {
            Intent magic_drink=new Intent(MainActivity.this,MagicDrink.class);
            startActivity(magic_drink);
        }
        else if(id == R.id.nav_calendar) {
            Intent calendar=new Intent(MainActivity.this,Calender.class);
            startActivity(calendar);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
