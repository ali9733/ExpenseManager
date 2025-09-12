package com.alaminali.expensemanager.fragmentPackage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alaminali.expensemanager.R;
import com.alaminali.expensemanager.databinding.FragmentDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class dialogFragment extends BottomSheetDialogFragment
{



    public dialogFragment()
    {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    FragmentDialogBinding dialogBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        dialogBinding=FragmentDialogBinding.inflate(getLayoutInflater());
        //-------------------CODING START FROM HERE-----------------

        dialogBinding.selectDateId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "SELECT date", Toast.LENGTH_SHORT).show();
                Log.d("MESSAGES: ", "onClick:select date ");
            }
        });





     //---------------CODING END HERE-------------------------------------
        return dialogBinding.getRoot();
    }


}