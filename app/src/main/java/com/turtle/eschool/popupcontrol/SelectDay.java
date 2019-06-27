package com.turtle.eschool.popupcontrol;



import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.turtle.eschool.R;
import com.turtle.eschool.api.urlsApimanage.ApiClient;
import com.turtle.eschool.interfaces.custom.ApiIHandler;
import com.turtle.eschool.prefControl.PrefManager;
import com.turtle.eschool.schoolmodel.ForgotPasswordModal;
import com.turtle.eschool.utilityschool.UtilityFunction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pratibha on 2/28/2017.
 */

public class SelectDay extends DialogFragment {
    Button chdpassword, btnskip;
    EditText et_passwordold, et_password, et_passwordcnf;
    String str_oldpassword = "", str_newpassword = "", str_cnfpassword = "";
    PrefManager prefManager;

    public SelectDay() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.passwordchg_dialog, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        prefManager = new PrefManager(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        init(view);
    }

    private void init(View view) {

        btnskip = (Button) view.findViewById(R.id.btnskip);
        chdpassword = (Button) view.findViewById(R.id.btnchgpassword);

        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        et_passwordold = (EditText) view.findViewById(R.id.et_passwordold);
        et_password = (EditText) view.findViewById(R.id.et_password);
        et_passwordcnf = (EditText) view.findViewById(R.id.et_passwordcnf);


        chdpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                str_oldpassword = et_passwordold.getText().toString().trim();
                str_newpassword = et_password.getText().toString().trim();
                str_cnfpassword = et_passwordcnf.getText().toString().trim();
                if (str_oldpassword.isEmpty()) {
                    UtilityFunction.showCenteredToast(getActivity(), getResources().getString(R.string.error_psold));
                } else if (str_newpassword.isEmpty()) {
                    UtilityFunction.showCenteredToast(getActivity(), getResources().getString(R.string.error_psnew));
                } else if (str_cnfpassword.isEmpty()) {
                    UtilityFunction.showCenteredToast(getActivity(), getResources().getString(R.string.error_cnfpass));
                } else if (!str_cnfpassword.equals(str_newpassword)) {
                    UtilityFunction.showCenteredToast(getActivity(), getResources().getString(R.string.error_correctpass));
                } else {
                    changeMyPasswordSchoolApp(str_oldpassword, str_newpassword, str_cnfpassword);
                }
            }
        });
    }

    private void changeMyPasswordSchoolApp(String _old_password, String _new_password, String str_cnfpassword) {
        final ApiIHandler apiService =
                ApiClient.getClient().create(ApiIHandler.class);

        final ProgressDialog pDialog = ProgressDialog.show(getActivity(), "", "Please wait ...", true);

        System.out.println("prefManager.getCheckUser_id():"+prefManager.getCheckUser_id());
        Call<ForgotPasswordModal> call = apiService.get_change_password(prefManager.getCheckUser_id(),
                _old_password, _new_password, str_cnfpassword);
        call.enqueue(new Callback<ForgotPasswordModal>() {
            @Override
            public void onResponse(Call<ForgotPasswordModal> call, Response<ForgotPasswordModal> response) {
                ForgotPasswordModal data = response.body();
                if (pDialog != null && pDialog.isShowing())
                    pDialog.hide();
                if(data.getResult() !=null)
                {
                    if (data.getResult().equals("1")) {
                        Toast.makeText(getActivity(), data.getMessage(), Toast.LENGTH_SHORT).show();
                        getDialog().dismiss();
                    } else {
                        Toast.makeText(getActivity(), data.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getActivity(), data.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ForgotPasswordModal> call, Throwable t) {
                // Log error here since request failed
                Log.e("", t.toString());
                if (pDialog != null && pDialog.isShowing())
                    pDialog.hide();

            }
        });
    }

}

