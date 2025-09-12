package com.alaminali.expensemanager.fragmentPackage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alaminali.expensemanager.R;
import com.alaminali.expensemanager.databinding.FragmentTransctionBinding;


public class transctionFragment extends Fragment {



    public transctionFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    FragmentTransctionBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding=FragmentTransctionBinding.inflate(inflater,container,false);

        //------------------------------CODING START FROM HERE--------------------------------

        /* AFTER CLICKING FLOATING BUTTON DIALOG WILL BE OPENED */

        binding.floatingActionButton.setOnClickListener(v -> {

            dialogFragment fragmentDialog=new dialogFragment();
            fragmentDialog.show(getActivity().getSupportFragmentManager(), fragmentDialog.getTag());

        });





       //----------------------------CODING END HERE-------------------------------------------
        return binding.getRoot();
    }
}