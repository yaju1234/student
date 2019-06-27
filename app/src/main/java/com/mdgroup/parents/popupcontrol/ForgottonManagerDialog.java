package com.mdgroup.parents.popupcontrol;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mdgroup.parents.R;
import com.mdgroup.parents.api.urlsApimanage.ApiClient;
import com.mdgroup.parents.interfaces.custom.ApiIHandler;
import com.mdgroup.parents.schoolmodel.ForgotPasswordModal;
import com.mdgroup.parents.utilityschool.UtilityFunction;

import retrofit2.Call;
import retrofit2.Callback;


public class ForgottonManagerDialog extends DialogFragment {

    Button submitButton, btnskip, btn_otpsubmit, btnchgpassword;

    EditText mobilenumber, otp_et, et_password, et_passwordcnf;

    RelativeLayout rl_forgotpassword, rl_otp, forgot_passwordchage;

    String str_admission_no_receive = "";

    public ForgottonManagerDialog() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forget_dialog, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
    }

    private void init(View view) {


        et_password = (EditText) view.findViewById(R.id.et_password);
        et_passwordcnf = (EditText) view.findViewById(R.id.et_passwordcnf);
        btnchgpassword = (Button) view.findViewById(R.id.btnchgpassword);

        otp_et = (EditText) view.findViewById(R.id.otp_et);
        rl_forgotpassword = (RelativeLayout) view.findViewById(R.id.rl_forgotpassword);
        rl_otp = (RelativeLayout) view.findViewById(R.id.rl_otp);
        forgot_passwordchage = (RelativeLayout) view.findViewById(R.id.forgot_passwordchage);

        rl_forgotpassword.setVisibility(View.VISIBLE);
        rl_otp.setVisibility(View.GONE);
        forgot_passwordchage.setVisibility(View.GONE);

        submitButton = (Button) view.findViewById(R.id.btn_forgotpasswordsubmit);
        btn_otpsubmit = (Button) view.findViewById(R.id.btn_otpsubmit);
        btnskip = (Button) view.findViewById(R.id.btnskip);

        mobilenumber = (EditText) view.findViewById(R.id.et_mobilenumber);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mobilenumber_str = mobilenumber.getText().toString();

                if (mobilenumber_str.isEmpty()) {
                    UtilityFunction.showCenteredToast(getActivity(), getResources().getString(R.string.error_mn));
                } else {
                    getForgotPasswrd(mobilenumber_str);
                }

            }
        });


        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();

            }
        });


        btn_otpsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String otp_str = otp_et.getText().toString();

                if (otp_str.isEmpty()) {
                    UtilityFunction.showCenteredToast(getActivity(), getResources().getString(R.string.error_otp));
                } else {
                    getverfyOTP(otp_str);
                }
            }
        });


        btnchgpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String et_password_str = et_password.getText().toString();
                String et_passwordcnf_str = et_passwordcnf.getText().toString();

                if (et_password_str.isEmpty()) {
                    UtilityFunction.showCenteredToast(getActivity(), getResources().getString(R.string.error_psnew));
                } else if (et_passwordcnf_str.isEmpty()) {
                    UtilityFunction.showCenteredToast(getActivity(), getResources().getString(R.string.error_cnfpass));
                } else if (!et_passwordcnf_str.equals(et_password_str)) {
                    UtilityFunction.showCenteredToast(getActivity(), getResources().getString(R.string.error_correctpass));
                } else {
                     changeForgotPasswordReset(et_password_str,et_passwordcnf_str);
                }

            }
        });
    }

    public void getForgotPasswrd(String mobilenumber_str_receive) {
        final ApiIHandler apiService =
                ApiClient.getClient().create(ApiIHandler.class);

        final ProgressDialog pDialog = ProgressDialog.show(getActivity(), "", "Please wait ...", true);

        Call<ForgotPasswordModal> call = apiService.getFORGOTSTUDENT(mobilenumber_str_receive);
        call.enqueue(new Callback<ForgotPasswordModal>() {
            @Override
            public void onResponse(Call<ForgotPasswordModal> call, retrofit2.Response<ForgotPasswordModal> response) {
                pDialog.dismiss();
                if (response.code() == 200) {
                    ForgotPasswordModal data = response.body();
                    pDialog.hide();
                    if (data.getResult().equalsIgnoreCase("1")) {
                        pDialog.hide();
                        str_admission_no_receive = data.getSTUDENT_ADMISSION_NO();

                        Toast.makeText(getActivity(), data.getMessage(), Toast.LENGTH_SHORT).show();
                        rl_forgotpassword.setVisibility(View.GONE);
                        rl_otp.setVisibility(View.VISIBLE);
                        forgot_passwordchage.setVisibility(View.GONE);
                    }
                    else {
                        Toast.makeText(getActivity(), data.getMessage(), Toast.LENGTH_SHORT).show();
                        pDialog.hide();
                    }
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordModal> call, Throwable t) {
                // Log error here since request failed
                Log.e("", t.toString());
                if (pDialog != null && pDialog.isShowing())
                    pDialog.hide();
                Toast.makeText(getActivity(), getResources().getString(R.string.error_tryagain), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void changeForgotPasswordReset(String et_password_str,String et_passwordcnf_str) {
        final ApiIHandler apiService =
                ApiClient.getClient().create(ApiIHandler.class);

        final ProgressDialog pDialog = ProgressDialog.show(getActivity(), "", "Please wait ...", true);

        Call<ForgotPasswordModal> call = apiService.get_forgotpassword_reset(str_admission_no_receive,et_password_str,et_passwordcnf_str);
        call.enqueue(new Callback<ForgotPasswordModal>() {
            @Override
            public void onResponse(Call<ForgotPasswordModal> call, retrofit2.Response<ForgotPasswordModal> response) {
                pDialog.dismiss();
                if (response.code() == 200) {
                    ForgotPasswordModal data = response.body();
                    pDialog.hide();
                    if(data.getResult().equalsIgnoreCase("1"))
                    {
                        Toast.makeText(getActivity(), data.getMessage(), Toast.LENGTH_SHORT).show();

                        getDialog().dismiss();
                    }
                    else {
                        Toast.makeText(getActivity(), data.getMessage(), Toast.LENGTH_SHORT).show();
                        pDialog.hide();
                    }
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordModal> call, Throwable t) {
                // Log error here since request failed
                Log.e("", t.toString());
                if (pDialog != null && pDialog.isShowing())
                    pDialog.hide();
                Toast.makeText(getActivity(), getResources().getString(R.string.error_tryagain), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getverfyOTP(String otp_receive) {
        final ApiIHandler apiService =
                ApiClient.getClient().create(ApiIHandler.class);

        final ProgressDialog pDialog = ProgressDialog.show(getActivity(), "", "Please wait ...", true);

        Call<ForgotPasswordModal> call = apiService.getOTPMATCHAPI(str_admission_no_receive, otp_receive);
        call.enqueue(new Callback<ForgotPasswordModal>() {
            @Override
            public void onResponse(Call<ForgotPasswordModal> call, retrofit2.Response<ForgotPasswordModal> response) {
                pDialog.dismiss();
                if (response.code() == 200) {
                    ForgotPasswordModal data = response.body();
                    pDialog.hide();
                    if (data.getResult().equalsIgnoreCase("1")) {

                        Toast.makeText(getActivity(), data.getMessage(), Toast.LENGTH_SHORT).show();
                        rl_forgotpassword.setVisibility(View.GONE);
                        rl_otp.setVisibility(View.GONE);
                        forgot_passwordchage.setVisibility(View.VISIBLE);
                    }
                    else {
                        Toast.makeText(getActivity(), data.getMessage(), Toast.LENGTH_SHORT).show();
                        pDialog.hide();
                    }
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordModal> call, Throwable t) {

                Log.e("", t.toString());
                if (pDialog != null && pDialog.isShowing())
                    pDialog.hide();
                Toast.makeText(getActivity(), getResources().getString(R.string.error_tryagain), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
