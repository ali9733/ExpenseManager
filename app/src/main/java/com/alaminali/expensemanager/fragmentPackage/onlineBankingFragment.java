package com.alaminali.expensemanager.fragmentPackage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alaminali.expensemanager.R;


public class onlineBankingFragment extends Fragment
{

    

    public onlineBankingFragment()
    {
        // Required empty public constructor
    }

    

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        return inflater.inflate(R.layout.fragment_online_banking, container, false);
    }
}