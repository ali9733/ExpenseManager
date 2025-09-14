package com.alaminali.expensemanager.fragmentPackage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alaminali.expensemanager.R;
import com.alaminali.expensemanager.databinding.FragmentAccountBinding;


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

                         String bankname=bankName.getText().toString();
                         String bankbranch=bankBranch.getText().toString();
                         String banknumber=bankNumber.getText().toString();
                         String bankifsc=bankIfsc.getText().toString();
                         if (bankname.length()!=0 && bankbranch.length()!=0&&bankifsc.length()!=0&&banknumber.length()!=0)
                         {
                             Log.d("Bank_Name", "Item: "+bankname);
                             Log.d("Bank_branch", "Item: "+bankbranch);
                             Log.d("Bank_Number", "Item: "+banknumber);
                             Log.d("Bank_Ifsc", "Item: "+bankifsc);

                             bankDialog.dismiss();
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
                      EditText card_number,card_cvv,card_expire;

                      Dialog debitDialog=new Dialog(getContext());
                      debitDialog.setContentView(R.layout.custom_debit_dialog);

                      card_expire=debitDialog.findViewById(R.id.et_debit_expire_id);
                      card_number=debitDialog.findViewById(R.id.et_debit_expire_id);
                      card_cvv=debitDialog.findViewById(R.id.et_debit_expire_id);
                      Button cardBtn=debitDialog.findViewById(R.id.save_debit_btn_id);

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
                         
                          String cardNumber,cardCvv,cardExpire;
                          cardNumber=card_number.getText().toString();
                          cardExpire=card_expire.getText().toString();
                          cardCvv=card_cvv.getText().toString();

                          if (cardNumber.length()!=0&&cardCvv.length()!=0&&cardExpire.length()!=0)
                          {
                              Log.d("Card_Number", "Item: "+cardNumber);
                              Log.d("Card_Cvv", "Item: "+cardCvv);
                              Log.d("Card_Expire", "Item: "+cardExpire);

                              debitDialog.dismiss();
                          }
                          else {
                              Toast.makeText(getContext(), "Field Can Not Be Empty!", Toast.LENGTH_SHORT).show();
                          }

                      });



                      debitDialog.show();

                  }
              });

             /* opened paytm dialog */

             /* opened phonepay dialog */


             /* opened paypal dialog */

             dialog.show();

         });



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