package com.alaminali.expensemanager.fragmentPackage;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.alaminali.expensemanager.R;
import com.alaminali.expensemanager.adapterPackage.accountAdapter;
import com.alaminali.expensemanager.adapterPackage.categoryAdapter;
import com.alaminali.expensemanager.databinding.AccountRecyclerLayoutBinding;
import com.alaminali.expensemanager.databinding.CategoryRecyclerLayoutBinding;
import com.alaminali.expensemanager.databinding.FragmentDialogBinding;
import com.alaminali.expensemanager.dbUtils.transctionModel;
import com.alaminali.expensemanager.dbUtils.transctionViewModel;
import com.alaminali.expensemanager.modelPackage.Constants;
import com.alaminali.expensemanager.modelPackage.accountModel;
import com.alaminali.expensemanager.modelPackage.categoryModel;
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

    public static int INCOME_BTN_TYPE=0;
    public static int EXPENSE_BTN_TYPE=0;
    Calendar calendar;
    int amount;
    String notes;
    String fullDate,shortDate;
    String category;
    String account,type=" ";
    public int categoryImage=0;
    transctionViewModel viewModel;

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


        /* WHEN CLICK SELECT DATE INPUT_EDIT_TEXT ITS OPENED A DIALOG BOX WITH DATE PICKER */

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

                        dialogBinding.selectDateId.setText(Constants.getFullDateFormat(calendar));


                    }
                });

                dateDialog.show();
            }
        });
        /* WHEN CLICK SELECT CATEGORY INPUT_EDIT_TEXT ITS OPENED A ALERT DIALOG  WITH RECYCLER VIEW ITEM */

        dialogBinding.dialogCategoryId.setOnClickListener(v -> {

            CategoryRecyclerLayoutBinding catBinding=CategoryRecyclerLayoutBinding.inflate(inflater);
            AlertDialog categoryDialog=new AlertDialog.Builder(getContext()).create();
            categoryDialog.setView(catBinding.getRoot());
            categoryDialog.show();

            categoryAdapter catAdapter=new categoryAdapter(Constants.getAllCategoryDatas(), getContext(), new categoryAdapter.setOnCategoryDataListener() {
                @Override
                public void onCategoryDataListener(categoryModel model, int position)
                {
                    dialogBinding.dialogCategoryId.setText(model.getCategoryName());
                    categoryImage=model.getCategoryImage();
                    categoryDialog.dismiss();
                }
            });
            catBinding.categoryRecyclerViewId.setLayoutManager(new GridLayoutManager(getContext(),3));
            catBinding.categoryRecyclerViewId.setAdapter(catAdapter);



        });

        /* WHEN CLICK SELECT ACCOUNT INPUT_EDIT_TEXT ITS OPENED A ALERT DIALOG  WITH RECYCLER VIEW ITEM */

        dialogBinding.dialogSelectAccountId.setOnClickListener(v -> {

            AccountRecyclerLayoutBinding accountBinding=AccountRecyclerLayoutBinding.inflate(inflater);

            AlertDialog accountDialog=new AlertDialog.Builder(getContext()).create();
            accountDialog.setView(accountBinding.getRoot());
            accountDialog.show();

            accountAdapter adapter=new accountAdapter(getContext(), Constants.getAllAccountDatas(), new accountAdapter.setOnAccountDataListener() {
                @Override
                public void onAccountDataListener(accountModel model, int position)
                {
                    dialogBinding.dialogSelectAccountId.setText(model.getAccountName());
                   accountDialog.dismiss();
                }
            });
            accountBinding.accountRecyclerViewId.setLayoutManager(new GridLayoutManager(getContext(),3));
            accountBinding.accountRecyclerViewId.setAdapter(adapter);


        });


      //-----------------SAVE TRANSCTION BUTTON CLICK TO SAVE DATA IN DATABASE------
       dialogBinding.saveBtnId.setOnClickListener(v -> {

           notes=dialogBinding.dialogNoteId.getText().toString();
           fullDate=dialogBinding.selectDateId.getText().toString();
           shortDate=Constants.getShortDateFormat(calendar);
           category=dialogBinding.dialogCategoryId.getText().toString();
           account=dialogBinding.dialogSelectAccountId.getText().toString();
           String amount1=dialogBinding.dialogAmountId.getText().toString();

           /* SET INCOME OR EXPENSE BUTTON VALUE TO INSERT DATA IN DATABASE */

           if (INCOME_BTN_TYPE==1)
           {
               type="income";
           }
           else if (EXPENSE_BTN_TYPE==2)
           {
               type="expense";
           }


           /* CHECKING THAT AMOUNT IS NOT EMPTY BECAUSE EMPTY DATA PARSING GIVE ERRORS */


           if (amount1.length()!=0)
           {
               amount=Integer.parseInt(amount1);
           }

           /* CHECKING ALL FIELDS THAT MUST SHOULD NOT BE  EMPTY */
           if (notes.length()!=0&&fullDate.length()!=0&&shortDate.length()!=0&&category.length()!=0&&account.length()!=0&&amount1.length()!=0&&type.trim().length()!=0)
           {
               Log.d("Image: ", "onCreateView: "+categoryImage);
               Log.d("Category: ", "onCreateView: "+category);
               Log.d("Account: ", "onCreateView: "+account);
               Log.d("Amount: ", "onCreateView: "+amount);
               Log.d("ShortDate: ", "onCreateView: "+shortDate);
               Log.d("FullDate: ", "onCreateView: "+fullDate);
               Log.d("Notes: ", "onCreateView: "+notes);
               Log.d("Type: ", "onCreateView: "+type);

               transctionModel model=new transctionModel();
               model.setType(type);
               model.setAmount(amount);
               model.setAccountName(account);
               model.setNotes(notes);
               model.setCategoryImage(categoryImage);
               model.setFull_date(fullDate);
               model.setShort_date(shortDate);
               model.setCategoryName(category);

               viewModel=new ViewModelProvider(getActivity()).get(transctionViewModel.class);
               viewModel.insertTransctionViewData(model);

             //---RESET INCOME AND EXPENSE VALUE--------
               INCOME_BTN_TYPE=0;
               EXPENSE_BTN_TYPE=0;
               dismiss();
           }
           else
           {
               Toast.makeText(getContext(), "Empty Field Is Detected!! ", Toast.LENGTH_SHORT).show();
           }


       });

     //---------------CODING END HERE-------------------------------------
        return dialogBinding.getRoot();
    }


}