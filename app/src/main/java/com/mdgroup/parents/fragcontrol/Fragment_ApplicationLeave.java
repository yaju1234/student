package com.mdgroup.parents.fragcontrol;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mdgroup.parents.R;
import com.mdgroup.parents.api.urlsApimanage.ApiClient;
import com.mdgroup.parents.interfaces.custom.ApiIHandler;
import com.mdgroup.parents.prefControl.PrefManager;
import com.mdgroup.parents.schoolmodel.ForgotPasswordModal;
import com.mdgroup.parents.utilityschool.UtilityFunction;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * @Gaurav Mangal
 */
public class Fragment_ApplicationLeave extends Fragment {

    EditText et_sentto, etsubject, etbody;
    Button  senddatabutton, attchment_btn;
    private RadioGroup radiogroupleave;
    private RadioButton radio_oneday, radio_multipleday;
    static final int DATE_PICKER_ID = 1111;
    static String  selected_todatemultiple = "0000-00-00";
    static String selected_todate = "", selected_fromdate = "0000-00-00";
    String radio_button_check = "";
    PrefManager prefManager;
    static Button from_date, todate,todate_multiple;
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

    public Fragment_ApplicationLeave() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Leave Application");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.leave__formdata, container, false);
        radiogroupleave = (RadioGroup) view.findViewById(R.id.radiogroupleave);
        // find the radiobutton by returned id

        et_sentto = (EditText) view.findViewById(R.id.et_sentto);
        etsubject = (EditText) view.findViewById(R.id.etsubject);
        etbody = (EditText) view.findViewById(R.id.etbody);
        radio_oneday = (RadioButton) view.findViewById(R.id.radio_oneday);
        radio_multipleday = (RadioButton) view.findViewById(R.id.radio_multipleday);
        from_date = (Button) view.findViewById(R.id.from_date);
        todate = (Button) view.findViewById(R.id.todate);
        todate_multiple = (Button) view.findViewById(R.id.todate_multiple);
        senddatabutton = (Button) view.findViewById(R.id.senddatabutton);
        prefManager = new PrefManager(getActivity());

        from_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*DialogFragment newFragment = new DOBFragment();
                newFragment.show(getFragmentManager(), "DatePicker");*/
                DialogFragment newFragment = new FROMDATEFRAGMENT();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });

        todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TODATEFRAGMENT();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });

        todate_multiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TODATEMULTIPLEFRAGMENT();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });


        senddatabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vButton) {
                //UtilityFunction.showMessage_S(getActivity(), "Your have applied leave successfully.your teacher will approve it urgently.");

                String sendto = et_sentto.getText().toString().trim();
                if (sendto != null) sendto = sendto;
                else
                    sendto = "";

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
                if (sendto.length() == 0 || sendto.equalsIgnoreCase("")) {
                    UtilityFunction.showBottomSnackbar(vButton, getResources().getString(R.string.error_sentto));
                } else if (sendsubject.length() == 0 || sendto.equalsIgnoreCase("")) {
                    UtilityFunction.showBottomSnackbar(vButton, getResources().getString(R.string.error_subject));
                } else if (etbody_str.length() == 0 || sendto.equalsIgnoreCase("")) {
                    UtilityFunction.showBottomSnackbar(vButton, getResources().getString(R.string.error_mesage));
                } else if (radio_button_check.length() == 0 || sendto.equalsIgnoreCase("")) {
                    UtilityFunction.showBottomSnackbar(vButton, getResources().getString(R.string.error_radio));
                } else {
                    System.out.println("Cahll:"+radio_button_check);
                    System.out.println("Cahll:--"+sendto +sendsubject+etbody_str+selected_todate+UtilityFunction.currentUTCTimeFormat());
                    if (radio_button_check.equalsIgnoreCase("1")) {
                        getApplicationLeave(prefManager.getCheckUser_id(), sendto, sendsubject, "0000-00-00", "0000-00-00", etbody_str, selected_todate, UtilityFunction.currentUTCTimeFormat());
                    } else {
                        //2
                        getApplicationLeave(prefManager.getCheckUser_id(), sendto, sendsubject, selected_fromdate, selected_todatemultiple, etbody_str, "0000-00-00", UtilityFunction.currentUTCTimeFormat());
                    }

                }


            }
        });

        radiogroupleave.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                if (checkedId == R.id.radio_oneday) {
                    radio_button_check = "1";
                    from_date.setVisibility(View.GONE);
                    todate.setVisibility(View.VISIBLE);
                    todate_multiple.setVisibility(View.GONE);
                } else if (checkedId == R.id.radio_multipleday) {
                    radio_button_check = "2";
                    from_date.setVisibility(View.VISIBLE);
                    todate.setVisibility(View.GONE);
                    todate_multiple.setVisibility(View.VISIBLE);
                }


              /*  switch (checkedId) {
                    case 0:

                        break;
                    case 1:
                        Toast.makeText(getActivity(), "You have Clicked RadioButton 2",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(), "You have Clicked RadioButton 3",
                                Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        //The default selection is RadioButton 1
                        Toast.makeText(getActivity(), "You have Clicked RadioButton 1",
                                Toast.LENGTH_SHORT).show();
                        break;
                }*/
            }
        });


        return view;
    }

    @SuppressLint("ValidFragment")
    public static class TODATEFRAGMENT extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm + 1, dd);
        }

        public void populateSetDate(int year, int month, int day) {
            selected_todate = year + "-" + month + "-" + day;
            String selected_buttondate = day + "-" + month + "-" + year;
            todate.setText(selected_buttondate);
        }
    }

    @SuppressLint("ValidFragment")
    public static class FROMDATEFRAGMENT extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm + 1, dd);
        }

        public void populateSetDate(int year, int month, int day) {
            selected_fromdate = year + "-" + month + "-" + day;
            String selected_buttondate = day + "-" + month + "-" + year;
            from_date.setText(selected_buttondate);
        }
    }

    @SuppressLint("ValidFragment")
    public static class TODATEMULTIPLEFRAGMENT extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm + 1, dd);
        }

        public void populateSetDate(int year, int month, int day) {
            selected_todatemultiple = year + "-" + month + "-" + day;
            String selected_buttondate = day + "-" + month + "-" + year;
            todate_multiple.setText(selected_buttondate);
        }
    }

    public void getApplicationLeave(String st_id, String l_receiver, String l_sub, String from_date, String to_date, String body, String singleday, String curr_date) {
        final ApiIHandler apiService = ApiClient.getClient().create(ApiIHandler.class);
        final ProgressDialog pDialog = ProgressDialog.show(getActivity(), "", "Leave please wait ...", true);
        Call<ForgotPasswordModal> call = apiService.addleavesData(st_id, l_receiver, l_sub, from_date, to_date, body, singleday, curr_date);
        call.enqueue(new Callback<ForgotPasswordModal>() {
            @Override
            public void onResponse(Call<ForgotPasswordModal> call, retrofit2.Response<ForgotPasswordModal> response) {
                pDialog.dismiss();
                if (response.code() == 200) {
                    ForgotPasswordModal data = response.body();
                    pDialog.hide();
                    if(data.getResult() !=null)
                    {
                        if (data.getResult().equalsIgnoreCase("1")) {
                            UtilityFunction.showMessage_S(getActivity(), data.getMessage());
                        } else {
                            pDialog.hide();
                        }
                    }
                    else
                    {
                        UtilityFunction.showMessage_S(getActivity(), data.getMessage());
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

}
