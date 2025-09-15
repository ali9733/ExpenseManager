package com.alaminali.expensemanager.adapterPackage;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.alaminali.expensemanager.fragmentPackage.bankingFragment;
import com.alaminali.expensemanager.fragmentPackage.onlineBankingFragment;

public class bankingPagerAdapter extends FragmentStateAdapter
{

    public bankingPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle)
    {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position)
    {
        switch (position)
        {
            case 0:
                return new bankingFragment();
            case 1:
                return new onlineBankingFragment();
            default:
                Log.d("STATUS: ", "createFragment: no item found");
                break;

        }
        return null;
    }

    @Override
    public int getItemCount()
    {
        return 2;
    }
}
