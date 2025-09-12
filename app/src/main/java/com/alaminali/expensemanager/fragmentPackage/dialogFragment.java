package com.alaminali.expensemanager.fragmentPackage;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.alaminali.expensemanager.R;
import com.alaminali.expensemanager.databinding.FragmentDialogBinding;
import com.alaminali.expensemanager.modelPackage.Constants;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;

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

    public static int INCOME_BTN_TYPE=1;
    public static int EXPENSE_BTN_TYPE=2;
    Calendar calendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        dialogBinding=FragmentDialogBinding.inflate(getLayoutInflater());
        //-------------------CODING START FROM HERE-----------------
        /* CREATE A CALENDER ONJECT */
         calendar=Calendar.getInstance();

        /* WHEN CLICK INCOME BUTTON ITS BORDER AND TEXT WILL BE GREEN */

        dialogBinding.dialogIncomeBtnId.setOnClickListener(v -> {
            dialogBinding.dialogIncomeBtnId.setBackground(getActivity().getDrawable(R.drawable.selected_income_btn));
            dialogBinding.dialogIncomeBtnId.setTextColor(getContext().getColor(R.color.Darkgreen));


            /* WHEN CLICK INCOME BUTTON THEN EXPENSE BUTTON BORDER AND TEXT WILL BE GREY */
            dialogBinding.dialogExpenseBtnId.setBackground(getActivity().getDrawable(R.drawable.unselected_btn));
            dialogBinding.dialogExpenseBtnId.setTextColor(getContext().getColor(R.color.dark_blur));
            INCOME_BTN_TYPE=1;
            EXPENSE_BTN_TYPE=0;

        });



        /* WHEN CLICK EXPENSE BUTTON ITS BORDER AND TEXT WILL BE RED */

            dialogBinding.dialogExpenseBtnId.setOnClickListener(v -> {
            dialogBinding.dialogExpenseBtnId.setBackground(getActivity().getDrawable(R.drawable.selected_expense_btn));
            dialogBinding.dialogExpenseBtnId.setTextColor(getContext().getColor(R.color.red));

            /* WHEN CLICK EXPENSE BUTTON THEN INCOME BUTTON BORDER AND TEXT WILL BE GREY */
            dialogBinding.dialogIncomeBtnId.setBackground(getActivity().getDrawable(R.drawable.unselected_btn));
            dialogBinding.dialogIncomeBtnId.setTextColor(getContext().getColor(R.color.dark_blur));

                INCOME_BTN_TYPE=0;
                EXPENSE_BTN_TYPE=2;

        });


        /* WHEN CLICK SELECT INPUT_EDIT_TEXT ITS OPENED A DIALOG BOX WITH DATE PICKER */

        dialogBinding.selectDateId.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DatePickerDialog dateDialog=new DatePickerDialog(getContext());
                dateDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {

                        calendar.set(Calendar.DAY_OF_MONTH,view.getDayOfMonth());
                        calendar.set(Calendar.MONTH,view.getMonth());
                        calendar.set(Calendar.YEAR,view.getYear());

                        dialogBinding.selectDateId.setText(Constants.getDateFormat(calendar));


                    }
                });

                dateDialog.show();
            }
        });





     //---------------CODING END HERE-------------------------------------
        return dialogBinding.getRoot();
    }


}