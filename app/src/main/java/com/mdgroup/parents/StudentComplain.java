package com.mdgroup.parents;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mdgroup.parents.R;
import com.mdgroup.parents.api.urlsApimanage.ApiClient;
import com.mdgroup.parents.interfaces.custom.ApiIHandler;
import com.mdgroup.parents.prefControl.PrefManager;
import com.mdgroup.parents.schoolmodel.ResSingle;
import com.mdgroup.parents.utilityschool.UtilityFunction;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * @Gaurav Mangal
 */
public class StudentComplain extends Fragment {

    EditText et_sentto, etsubject, etbody;
    Button from_date, todate, todate_multiple, senddatabutton, attchment_btn;
    private RadioGroup radiogroupleave;
    private RadioButton radio_oneday, radio_multipleday;
    static final int DATE_PICKER_ID = 1111;
    String selected_todate = "", selected_fromdate = "0000-00-00", selected_todatemultiple = "0000-00-00";

    String radio_button_check = "";

    PrefManager prefManager;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    public StudentComplain() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Complain");

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.complain__formdata, container, false);
        // find the radiobutton by returned id

        etsubject = (EditText) view.findViewById(R.id.etsubject);
        etbody = (EditText) view.findViewById(R.id.etbody);
        senddatabutton = (Button) view.findViewById(R.id.senddatabutton);
        prefManager = new PrefManager(getActivity());




        senddatabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vButton) {
                //UtilityFunction.showMessage_S(getActivity(), "Your have applied leave successfully.your teacher will approve it urgently.");



                String sendsubject = etsubject.getText().toString().trim();
                if (sendsubject != null) sendsubject = sendsubject;
                else
                    sendsubject = "";
                String etbody_str = etbody.getText().toString().trim();
                if (etbody_str != null) etbody_str = etbody_str;
                else
                    etbody_str = "";
                if (etbody_str != null) etbody_str = etbody_str;
                else
                    etbody_str = "";
                if (sendsubject.length() == 0) {
                    UtilityFunction.showBottomSnackbar(vButton, getResources().getString(R.string.error_subject));
                } else if (etbody_str.length() == 0) {
                    UtilityFunction.showBottomSnackbar(vButton, getResources().getString(R.string.error_desc));
                } else {
                    getApplicationComplain(prefManager.getCheckUser_id(), sendsubject,etbody_str);

                }


            }
        });




        return view;
    }


    public void getApplicationComplain(String st_id, String l_sub, String body) {
        final ApiIHandler apiService = ApiClient.getClient().create(ApiIHandler.class);
        final ProgressDialog pDialog = ProgressDialog.show(getActivity(), "", "please wait ...", true);
        Call<ResSingle> call = apiService.addComplainData(st_id, l_sub, body);
        call.enqueue(new Callback<ResSingle>() {
            @Override
            public void onResponse(Call<ResSingle> call, retrofit2.Response<ResSingle> response) {
                pDialog.dismiss();
                if (response.code() == 200) {
                    ResSingle data = response.body();
                    pDialog.hide();
                    if(data.getResult() !=null)
                    {
                        if (data.getResult().equalsIgnoreCase("1")) {
                            UtilityFunction.showCenteredToast(getActivity(),data.getMessage());
                            switchFragment(new StudentComplain());
                        } else {
                            pDialog.hide();
                        }
                    }
                    else
                    {

                    }

                }
            }

            @Override
            public void onFailure(Call<ResSingle> call, Throwable t) {
                // Log error here since request failed
                Log.e("", t.toString());
                if (pDialog != null && pDialog.isShowing())
                    pDialog.hide();
                Toast.makeText(getActivity(), getResources().getString(R.string.error_tryagain), Toast.LENGTH_SHORT).show();
            }
        });
    }
    // switching fragment
    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainFrame, fragment).commit();
    }
}
