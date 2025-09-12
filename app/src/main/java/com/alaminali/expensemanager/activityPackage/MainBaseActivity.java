package com.alaminali.expensemanager.activityPackage;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.alaminali.expensemanager.R;
import com.alaminali.expensemanager.databinding.ActivityMainBaseBinding;
import com.alaminali.expensemanager.fragmentPackage.accountFragment;
import com.alaminali.expensemanager.fragmentPackage.statsFragment;
import com.alaminali.expensemanager.fragmentPackage.transctionFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainBaseActivity extends AppCompatActivity
{
    ActivityMainBaseBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.materialToolbar);

        /* TOOLBAR  TITLE NAME IS SETTING BELOW */
        getSupportActionBar().setTitle("Transction");


        /* TO OPEN DEFAULT FRAGMENT ON THE MAIN ACTIVITY */
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_fragment_replace_id,new transctionFragment()).commit();


        /* PLACED EVERY FRAGMENT ON THE MAIN ACTIVITY BELOW */
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int idx=item.getItemId();

                if (idx==R.id.transction_id)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_fragment_replace_id,new transctionFragment()).commit();
                }
                else if (idx==R.id.account_id)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_fragment_replace_id,new accountFragment()).commit();
                }
                else if (idx==R.id.stats_id)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_fragment_replace_id,new statsFragment()).commit();
                }


                return true;
            }
        });



    }
    /* SETTING SEARCH BAR AND FAVOURITE ON MATERIAL TOOLBAR BELOW */

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.title_bar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }





}