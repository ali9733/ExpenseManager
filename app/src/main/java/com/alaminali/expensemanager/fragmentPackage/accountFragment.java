package com.alaminali.expensemanager.fragmentPackage;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alaminali.expensemanager.R;
import com.alaminali.expensemanager.adapterPackage.bankingPagerAdapter;
import com.alaminali.expensemanager.databinding.FragmentAccountBinding;
import com.alaminali.expensemanager.dbUtils.bankModel;
import com.alaminali.expensemanager.dbUtils.bankViewModel;
import com.alaminali.expensemanager.modelPackage.Constants;

import java.util.Calendar;


public class accountFragment extends Fragment {



    public accountFragment()
    {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    FragmentAccountBinding accountBinding;

   bankViewModel bankviewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        accountBinding=FragmentAccountBinding.inflate(inflater,container, false);

        /* CODING START FROM HERE */


         accountBinding.addAccountId.setOnClickListener(v -> {

             LinearLayout bankBtn,debitBtn,phonepayBtn,paytmBtn,paypalBtn;

             Dialog dialog=new Dialog(getContext());
            // dialog.setCancelable(false);
             dialog.setContentView(R.layout.add_account_dialog);

            /* initialize all button */

             bankBtn=dialog.findViewById(R.id.add_bank_id);
             debitBtn=dialog.findViewById(R.id.add_debit_card_id);
             phonepayBtn=dialog.findViewById(R.id.add_phone_pay_id);
             paytmBtn=dialog.findViewById(R.id.add_paytm_id);
             paypalBtn=dialog.findViewById(R.id.add_paypal_id);

             /* opened bank dialog */

             bankBtn.setOnClickListener(v1 ->
             {
                 dialog.dismiss();
                  Button saveBtn;
                  EditText bankName,bankBranch,bankNumber,bankIfsc;
                  Dialog bankDialog = new Dialog(getContext());
                  bankDialog.setContentView(R.layout.custom_bank_dialog);


                 bankName=bankDialog.findViewById(R.id.et_bank_name_id);
                 bankBranch=bankDialog.findViewById(R.id.et_branch_name_id);
                 bankNumber=bankDialog.findViewById(R.id.et_bank_number_id);
                 bankIfsc=bankDialog.findViewById(R.id.et_ifsc_code_id);
                 saveBtn = bankDialog.findViewById(R.id.save_bank_btn_id);


                 Window window =bankDialog.getWindow();
                 if (window != null)
                 {
                     WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                     layoutParams.copyFrom(window.getAttributes());
                     layoutParams.width = (int) (getScreenWidth() * 1); // 90% of screen width
                     layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                     window.setAttributes(layoutParams);
                 }

                 saveBtn.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v)
                     {

                         String bankname=bankName.getText().toString().trim();
                         String bankbranch=bankBranch.getText().toString().trim();
                         String banknumber=bankNumber.getText().toString().trim();
                         String bankifsc=bankIfsc.getText().toString().trim();
                         if (bankname.length()!=0 && bankbranch.length()!=0&&bankifsc.length()!=0&&banknumber.length()!=0)
                         {

                             if (bankname.length()<3)
                             {
                                 Toast.makeText(getContext(), "Invalid Bank Name", Toast.LENGTH_SHORT).show();
                             }
                             else if (bankbranch.length()<3)
                             {
                                 Toast.makeText(getContext(), "Invalid Branch Name", Toast.LENGTH_SHORT).show();
                             }
                             else if (bankifsc.length()<11)
                             {
                                 Toast.makeText(getContext(), "Invalid IFSC Code", Toast.LENGTH_SHORT).show();
                             }
                             else if (banknumber.length()<16)
                             {
                                 Toast.makeText(getContext(), "Invalid Bank Number", Toast.LENGTH_SHORT).show();
                             }
                             else
                             {
                                 if (bankifsc.length()==11&&banknumber.length()==16)
                                 {
                                     int lettercount=0,numbercount=0;
                                     String firstChar=bankifsc.substring(0,4);
                                     String lastNumber=bankifsc.substring(4);

                                     for (char letter:firstChar.toCharArray())
                                     {
                                         if (Character.isLetter(letter))
                                         {
                                             lettercount++;
                                         }

                                     }


                                     for (char number:lastNumber.toCharArray())
                                     {
                                         if (Character.isDigit(number))
                                         {
                                             numbercount++;
                                         }

                                     }

                                     if (lettercount==4&&numbercount==7)
                                     {

                                         if (banknumber.charAt(0)!='0')
                                         {

                                                  bankviewModel=new ViewModelProvider(getActivity()).get(bankViewModel.class);
                                                  bankModel model=new bankModel();
                                                  model.setBankName(bankname);
                                                  model.setBranchName(bankbranch);
                                                  model.setBankNumber(banknumber);
                                                  model.setIfscCode(bankifsc);
                                                  bankviewModel.insertViewBankingRecords(model);
                                                  bankDialog.dismiss();


                                         }
                                         else
                                         {
                                             Toast.makeText(getContext(), "bank number can not start with 0", Toast.LENGTH_SHORT).show();
                                         }

                                     }
                                     else
                                     {
                                         Toast.makeText(getContext(), "Your Ifsc Number Format Is Not Correct", Toast.LENGTH_SHORT).show();
                                     }


                                 }
                                 else
                                 {
                                     Toast.makeText(getContext(), "bank number must be 16 digit and ifsc number must be 11 digit ", Toast.LENGTH_SHORT).show();
                                 }



                             }

                         }
                         else {
                             Toast.makeText(getContext(), "Any Field Can Not Be Empty !", Toast.LENGTH_SHORT).show();
                         }


                     }
                 });



                bankDialog.show();


             });

             /* opened debit card dialog */
              debitBtn.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v)
                  {   dialog.dismiss();
                      EditText card_number,card_cvv,card_expire,card_holder;

                      Dialog debitDialog=new Dialog(getContext());
                      debitDialog.setContentView(R.layout.custom_debit_dialog);

                      card_expire=debitDialog.findViewById(R.id.et_debit_expire_id);
                      card_number=debitDialog.findViewById(R.id.et_debit_card_number_id);
                      card_cvv=debitDialog.findViewById(R.id.et_debitcard_cvv_id);
                      card_holder=debitDialog.findViewById(R.id.et_debit_card_holder_name_id);
                      Button cardBtn=debitDialog.findViewById(R.id.save_debit_btn_id);

                      card_expire.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v)
                          {
                              Calendar calendar=Calendar.getInstance();

                              DatePickerDialog datePickerDialog=new DatePickerDialog(getContext());
                              datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                                  @Override
                                  public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                                  {
                                      calendar.set(Calendar.DAY_OF_MONTH,view.getDayOfMonth());
                                      calendar.set(Calendar.MONTH,view.getMonth());
                                      calendar.set(Calendar.YEAR,view.getYear());

                                      card_expire.setText(Constants.getShortDateFormat(calendar));
                                  }
                              });
                              datePickerDialog.show();

                          }
                      });

                      //---------------------------------------------------------------------------
                      Window window =debitDialog.getWindow();
                      if (window != null)
                      {
                          WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                          layoutParams.copyFrom(window.getAttributes());
                          layoutParams.width = (int) (getScreenWidth() * 1); // 90% of screen width
                          layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                          window.setAttributes(layoutParams);
                      }
                    //-----------------------------------------------------------------------------

                      cardBtn.setOnClickListener(v1 -> {
                         
                          String cardNumber,cardCvv,cardExpire,cardHolder;
                          cardNumber=card_number.getText().toString().trim();
                          cardExpire=card_expire.getText().toString().trim();
                          cardCvv=card_cvv.getText().toString().trim();
                          cardHolder=card_holder.getText().toString().trim();

                          if (cardNumber.length()!=0&&cardCvv.length()!=0&&cardExpire.length()!=0&&cardHolder.length()!=0)
                          {


                                      if (cardNumber.length()==16 && cardCvv.length()==3)
                                      {
                                          if (cardNumber.charAt(0)=='0')
                                          {

                                              Toast.makeText(getContext(), "Card Number  can not start with zero", Toast.LENGTH_SHORT).show();

                                          }
                                          else
                                          {

                                              if (cardCvv.charAt(0)!='0')
                                              {


                                              /* DATA WILL BE INSERT IN DATABASE FROM HERE */

                                                  Log.d("Card_Number", "Item: "+cardNumber);
                                                  Log.d("Card_Cvv", "Item: "+cardCvv);
                                                  Log.d("Card_Expire", "Item: "+cardExpire);

                                                  debitDialog.dismiss();
                                              }
                                              else {
                                                  Toast.makeText(getContext(), "Card Cvv can not start with 0", Toast.LENGTH_SHORT).show();
                                              }

                                          }

                                      }
                                      else
                                      {
                                          Toast.makeText(getContext(), "card number 16 and cvv is 3 digit number", Toast.LENGTH_SHORT).show();



                                      }



                          }
                          else
                          {
                              Toast.makeText(getContext(), "Field Can Not Be Empty!", Toast.LENGTH_SHORT).show();
                          }

                      });



                      debitDialog.show();

                  }
              });

             /* opened paytm dialog */
                paytmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                            dialog.dismiss();

                            Dialog paytmDialog=new Dialog(getContext());
                            paytmDialog.setContentView(R.layout.custom_paytm_dialog);


                        //---------------------------------------------------------------------------
                        Window window =paytmDialog.getWindow();
                        if (window != null)
                        {
                            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                            layoutParams.copyFrom(window.getAttributes());
                            layoutParams.width = (int) (getScreenWidth() * 1); // 90% of screen width
                            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                            window.setAttributes(layoutParams);
                        }
                        //-----------------------------------------------------------------------------


                            paytmDialog.show();

                    }
                });
             /* opened paypal dialog */

             paypalBtn.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v)
                 {
                     dialog.dismiss();

                     Dialog paypalDialog=new Dialog(getContext());
                     paypalDialog.setContentView(R.layout.custom_paypal_dialog);



                     //---------------------------------------------------------------------------
                     Window window =paypalDialog.getWindow();
                     if (window != null)
                     {
                         WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                         layoutParams.copyFrom(window.getAttributes());
                         layoutParams.width = (int) (getScreenWidth() * 1); // 90% of screen width
                         layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                         window.setAttributes(layoutParams);
                     }
                     //-----------------------------------------------------------------------------


                     paypalDialog.show();

                 }
             });

             /* opened phonepay dialog */

             phonepayBtn.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v)
                 {
                     dialog.dismiss();

                     Dialog phonepayDialog=new Dialog(getContext());
                     phonepayDialog.setContentView(R.layout.custom_phonepay_dialog);


                     //---------------------------------------------------------------------------
                     Window window =phonepayDialog.getWindow();
                     if (window != null)
                     {
                         WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                         layoutParams.copyFrom(window.getAttributes());
                         layoutParams.width = (int) (getScreenWidth() * 1); // 90% of screen width
                         layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                         window.setAttributes(layoutParams);
                     }
                     //-----------------------------------------------------------------------------


                     phonepayDialog.show();

                 }
             });

             /* dialog end here */

             dialog.show();

         });

         /* VIEW PAGER ADAPTER START FROM HERE */

        bankingPagerAdapter pagerAdapter=new bankingPagerAdapter(getActivity().getSupportFragmentManager(),getLifecycle());
        accountBinding.viewpager2Id.setAdapter(pagerAdapter);

        accountBinding.viewpager2Id.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position)
            {
                super.onPageSelected(position);
                accountBinding.tablayoutId.selectTab(accountBinding.tablayoutId.getTabAt(position));
            }
        });

        /* VIEW PAGER ADAPTER END HERE */

        /* CODING END HERE */
         return accountBinding.getRoot();
    }




    // Helper method to get screen width
        private int getScreenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }


}