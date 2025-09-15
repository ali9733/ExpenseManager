package com.alaminali.expensemanager.fragmentPackage;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alaminali.expensemanager.R;
import com.alaminali.expensemanager.adapterPackage.bankingAdapter;
import com.alaminali.expensemanager.databinding.FragmentBankingBinding;
import com.alaminali.expensemanager.databinding.OperationItemBinding;
import com.alaminali.expensemanager.dbUtils.bankModel;
import com.alaminali.expensemanager.dbUtils.bankViewModel;
import com.alaminali.expensemanager.interfacePackage.setOperationOnLongClickListener;

import java.lang.reflect.Type;
import java.util.List;


public class bankingFragment extends Fragment implements setOperationOnLongClickListener {



    public bankingFragment()
    {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }
     FragmentBankingBinding bankingBinding;
    bankViewModel bankviewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        bankingBinding=FragmentBankingBinding.inflate(inflater,container,false);


        /* CODING START FROM HERE */

        bankviewModel=new ViewModelProvider(getActivity()).get(bankViewModel.class);

        bankviewModel.getAllViewBankingRecords().observe(getViewLifecycleOwner(),bankModels->{

            bankingAdapter adapter=new bankingAdapter(bankModels,getContext(),this);
            bankingBinding.recyclerViewId.setLayoutManager(new LinearLayoutManager(getContext()));
            bankingBinding.recyclerViewId.setAdapter(adapter);

        });


        /* CODING END FROM HERE */
        return bankingBinding.getRoot();
    }


    @Override
    public void onOperationOnLongClickListener(bankModel model, int position)
    {
        OperationItemBinding operationItemBinding=OperationItemBinding.inflate(getLayoutInflater());
        Dialog dialog=new Dialog(getContext());
        dialog.setContentView(operationItemBinding.getRoot());

//---------------------------------------------------------------
        Window window =dialog.getWindow();
        if (window != null)
        {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(window.getAttributes());
            layoutParams.width = (int) (getScreenWidth() * 1); // 90% of screen width
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(layoutParams);
        }
//---------------------------ADD MONEY DIALOG-------------------------------------------

       operationItemBinding.addBankMoneyId.setOnClickListener(v -> {

           dialog.dismiss();

           AlertDialog alertDialog=new AlertDialog.Builder(getContext()).create();

           LinearLayout layout=new LinearLayout(getContext());
           layout.setOrientation(LinearLayout.VERTICAL);
           layout.setPadding(30,10,10,30);


           /* TEXTVIEW SETTING HERE */
           TextView tv=new TextView(getContext());
           tv.setText("INSERT BANK MONEY");
           tv.setTextColor(getContext().getColor(R.color.purple_700));
           tv.setTextSize(17);
           tv.setGravity(Gravity.CENTER);
           layout.addView(tv);

           /* EDITTEXT SETTING HERE */
           EditText etVal=new EditText(getContext());
           etVal.setHint("Enter Amount");
           etVal.setInputType(InputType.TYPE_CLASS_NUMBER);
           etVal.setBackground(getActivity().getDrawable(R.drawable.unselected_btn));
           etVal.setPadding(30,20,30,20);
           layout.addView(etVal);

           /* BUTTON SETTING HERE */

           Button btn=new Button(getContext());
           btn.setBackground(getActivity().getDrawable(R.drawable.btn_deisgn));
           btn.setText("ADD MONEY");
           btn.setTextColor(getContext().getColor(R.color.white));

           // Create LayoutParams (because parent is LinearLayout here)
           LinearLayout.LayoutParams params =
                   new LinearLayout.LayoutParams(
                           LinearLayout.LayoutParams.MATCH_PARENT,
                           LinearLayout.LayoutParams.WRAP_CONTENT
                   );

        // Set margins (left, top, right, bottom) in pixels
           params.setMargins(0, 30, 0, 10);

        // Apply params to TextView
           btn.setLayoutParams(params);
           tv.setLayoutParams(params);

           layout.addView(btn);

           alertDialog.setView(layout);
           //--------------------------------

           btn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v)
               {
                  String amount=etVal.getText().toString();
                   bankviewModel=new ViewModelProvider(getActivity()).get(bankViewModel.class);
                   bankModel model1=new bankModel();
                   model1.setUid(model.getUid());
                   model1.setBankAmount(Double.parseDouble(amount));
                   model1.setBankNumber(model.getBankNumber());
                   model1.setBankName(model.getBankName());
                   model1.setIfscCode(model.getIfscCode());
                   model1.setBranchName(model.getBranchName());
                   bankviewModel.updateViewBankingRecords(model1);
                   alertDialog.dismiss();
               }
           });

         //---------------------------------------
           alertDialog.show();


       });
//---------------------------ADD SEE DETAILS DIALOG-------------------------------------------

        operationItemBinding.seeBankDetails.setOnClickListener(v ->
        {
                dialog.dismiss();
            AlertDialog.Builder seeDialog=new AlertDialog.Builder(getContext());

            LinearLayout linearLayout=new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            seeDialog.setView(linearLayout);

            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(20,10,20,10);

            /* SETTING TEXTVIEW FOR BANK NAME */
            TextView tvBankName=new TextView(getContext());
            tvBankName.setText("BANK NAME: "+model.getBankName().toUpperCase());
            tvBankName.setTextSize(20);
            tvBankName.setTextColor(getContext().getColor(R.color.purple_500));
            tvBankName.setPadding(10,10,10,10);
            tvBankName.setLayoutParams(layoutParams);
            linearLayout.addView(tvBankName);

            /* SETTING TEXTVIEW FOR BRANCH NAME */

            TextView tvBankBranch=new TextView(getContext());
            tvBankBranch.setText("BRANCH NAME: "+model.getBranchName().toUpperCase());
            tvBankBranch.setTextSize(20);
            tvBankBranch.setTextColor(getContext().getColor(R.color.purple_500));
            tvBankBranch.setPadding(10,10,10,10);
            tvBankBranch.setLayoutParams(layoutParams);
            linearLayout.addView(tvBankBranch);

            /* SETTING TEXTVIEW FOR BANK ACCOUNT NAME */

            TextView tvBankAccount=new TextView(getContext());
            tvBankAccount.setText("ACCOUNT: "+model.getBankNumber());
            tvBankAccount.setTextSize(20);
            tvBankAccount.setTextColor(getContext().getColor(R.color.purple_500));
            tvBankAccount.setPadding(10,10,10,10);
            tvBankAccount.setLayoutParams(layoutParams);
            linearLayout.addView(tvBankAccount);

            /* SETTING TEXTVIEW FOR BANK IFSC CODE */

            TextView tvBankIfsc=new TextView(getContext());
            tvBankIfsc.setText("IFSC NUMBER: "+model.getIfscCode().toUpperCase());
            tvBankIfsc.setTextSize(20);
            tvBankIfsc.setTextColor(getContext().getColor(R.color.purple_500));
            tvBankIfsc.setPadding(10,10,10,10);
            tvBankIfsc.setLayoutParams(layoutParams);
            linearLayout.addView(tvBankIfsc);



            seeDialog.show();


        });
//---------------------------ADD DELETE DIALOG-------------------------------------------

    operationItemBinding.deleteBankId.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            dialog.dismiss();

            AlertDialog.Builder deleteDialog=new AlertDialog.Builder(getContext());
            deleteDialog.setCancelable(false);
            LinearLayout deleteLayout=new LinearLayout(getContext());
            deleteLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(10,10,10,10);
            deleteDialog.setView(deleteLayout);

            /* DELETE TITLE */

            TextView tvTitle=new TextView(getContext());
            deleteLayout.addView(tvTitle);
            tvTitle.setTextColor(getContext().getColor(R.color.red));
            tvTitle.setText("DELETE "+model.getBankName().toUpperCase());
            tvTitle.setPadding(10,10,10,10);
            tvTitle.setTextSize(20);
            tvTitle.setLayoutParams(layoutParams);


            /* DELETE MESSAGES */

            TextView tvMessage=new TextView(getContext());
            deleteLayout.addView(tvMessage);
            tvMessage.setTextColor(getContext().getColor(R.color.Darkgreen));
            tvMessage.setText("Are You Sure To Delete ?");
            tvMessage.setPadding(10,10,10,10);
            tvMessage.setTextSize(16);
            tvMessage.setLayoutParams(layoutParams);

            /* DELETE OK BUTTON */

            deleteDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    bankviewModel=new ViewModelProvider(getActivity()).get(bankViewModel.class);
                    bankviewModel.deleteViewBankingRecords(model.getUid());
                }
            });

            deleteDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {

                }
            });

            deleteDialog.show();


        }
    });

        dialog.show();


     /* CODING END HERE */
    }





    // Helper method to get screen width
    private int getScreenWidth()
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }





}