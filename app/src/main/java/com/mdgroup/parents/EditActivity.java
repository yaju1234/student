package com.mdgroup.parents;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mdgroup.parents.R;
import com.mdgroup.parents.api.urlsApimanage.ApiClient;
import com.mdgroup.parents.api.urlsApimanage.ConnectionManager;
import com.mdgroup.parents.interfaces.custom.ApiIHandler;
import com.mdgroup.parents.popupcontrol.PassChangerManager;
import com.mdgroup.parents.prefControl.PrefManager;
import com.mdgroup.parents.schoolmodel.ForgotPasswordModal;
import com.mdgroup.parents.schoolmodel.ModelUser;
import com.mdgroup.parents.utilityschool.UtilityFunction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditActivity extends AppCompatActivity {

    Button btneditprofile, btnskip;
    TextView textview_changepassword;
    EditText st_name, stemail, st_mobilenumber, staddress_1, staddress_2;
    ApiIHandler apiService;
    PrefManager prefManager;

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
    }

    @Override
    public boolean supportRequestWindowFeature(int featureId) {
        return super.supportRequestWindowFeature(featureId);
    }

    @Override
    public void supportInvalidateOptionsMenu() {
        super.supportInvalidateOptionsMenu();
    }

    @Override
    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
    }

    @Override
    public void onSupportActionModeStarted(ActionMode mode) {
        super.onSupportActionModeStarted(mode);
    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {
        super.onSupportActionModeFinished(mode);
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return super.onWindowStartingSupportActionMode(callback);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edti_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        prefManager = new PrefManager(EditActivity.this);

        st_name = (EditText) findViewById(R.id.st_name);
        stemail = (EditText) findViewById(R.id.stemail);
        st_mobilenumber = (EditText) findViewById(R.id.f_mobilenumber);
        staddress_1 = (EditText) findViewById(R.id.staddress_1);
        staddress_2 = (EditText) findViewById(R.id.staddress_2);
        String login_data = prefManager.getChecklogindata();

        Gson gson = new Gson();
        ModelUser modelstudent = gson.fromJson(login_data, ModelUser.class);


        if (modelstudent != null) {
            st_name.setText(modelstudent.getSTUDENT_FULL_NAME());
            st_name.setSelection(modelstudent.getSTUDENT_FULL_NAME().length());
            stemail.setText(modelstudent.getSTUDENT_FATHER_EMAIL());
            //stemail.setSelection(modelstudent.getSTUDENT_FATHER_EMAIL().length());
            st_mobilenumber.setText(modelstudent.getSTUDENT_FATHER_MOBILE_NO());
            // st_mobilenumber.setSelection(modelstudent.getSTUDENT_FATHER_MOBILE_NO().length());


            staddress_1.setText(modelstudent.getSTUDENT_FATHER_ADDRESS1());
            staddress_1.setSelection(modelstudent.getSTUDENT_FATHER_ADDRESS1().length());

            staddress_2.setText(modelstudent.getSTUDENT_FATHER_ADDRESS2());
            staddress_2.setSelection(modelstudent.getSTUDENT_FATHER_ADDRESS2().length());

        }

        textview_changepassword = (TextView) findViewById(R.id.textview_changepassword);
        btnskip = (Button) findViewById(R.id.btnskip);

        btneditprofile = (Button) findViewById(R.id.btneditprofile);
        btneditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name_student = st_name.getText().toString();
                String f_email = stemail.getText().toString();
                String f_mn = st_mobilenumber.getText().toString();
                String address1 = staddress_1.getText().toString();
                String staddress2 = staddress_2.getText().toString();


                if (f_email.isEmpty()) {
                    UtilityFunction.showCenteredToast(getApplicationContext(), getResources().getString(R.string.error_email));
                } else if (f_mn.isEmpty()) {
                    UtilityFunction.showCenteredToast(getApplicationContext(), getResources().getString(R.string.error_mn));
                } else if (address1.isEmpty()) {
                    UtilityFunction.showCenteredToast(getApplicationContext(), getResources().getString(R.string.error_address1));
                } else if (staddress2.isEmpty()) {
                    UtilityFunction.showCenteredToast(getApplicationContext(), getResources().getString(R.string.error_address2));
                } else {
                    // Show a progress spinner, and kick off a background task to
                    // perform the user login attempt.
                    if (new ConnectionManager(EditActivity.this).isConnectedToInternet()) {

                        btneditprofile(prefManager.getCheckUser_id(), f_email, f_mn, address1, staddress2, "update", name_student);
                    } else {
                        // show toast message , Network not available
                        UtilityFunction.showCenteredToast(getApplicationContext(), getResources().getString(R.string.app_networkError));
                    }
                }

            }
        });

        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("Call :");
                finish();
            }
        });
        textview_changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                PassChangerManager dialogFragment = new PassChangerManager();
                dialogFragment.show(fm, "ChanngePwd");
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void btneditprofile(String studentid, String f_email, String f_mn, String address1, String staddress2, String action, String name_student) {

        final ProgressDialog pDialog = ProgressDialog.show(EditActivity.this, "", "Please wait ...", true);
        apiService = ApiClient.getClient().create(ApiIHandler.class);
        Call<ForgotPasswordModal> call = apiService.get_updates_profiledata(studentid, f_mn, f_email, address1, staddress2, name_student, action);


        call.enqueue(new Callback<ForgotPasswordModal>() {
            @Override
            public void onResponse(Call<ForgotPasswordModal> call, Response<ForgotPasswordModal> response) {
                if (response.code() == 200) {
                    ForgotPasswordModal data = response.body();
                    pDialog.hide();
                    if (data.getResult().equalsIgnoreCase("1")) {
                        Toast.makeText(getApplicationContext(), data.getMessage(), Toast.LENGTH_SHORT).show();
                        if (new ConnectionManager(EditActivity.this).isConnectedToInternet()) {
                            LoginConfiguration  loginConfiguration = new LoginConfiguration(EditActivity.this, prefManager.getCheckloginusername(), prefManager.getCheckloginpassword(), "1");
                            loginConfiguration.execute();
                        } else {
                            // show toast message , Network not available
                            UtilityFunction.showCenteredToast(getApplicationContext(), getResources().getString(R.string.app_networkError));
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), data.getMessage(), Toast.LENGTH_SHORT).show();
                        pDialog.hide();
                    }
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordModal> call, Throwable t) {
                // Log error here since request failed
                UtilityFunction.showCenteredToast(getApplicationContext(), getResources().getString(R.string.error_tryagain));
                pDialog.dismiss();

            }
        });
    }


    class LoginConfiguration extends AsyncTask<Void, Void, Void> {

        private Context mContext;

        private String login_mod, userid, userpassword;

        private ProgressDialog mProgressDialog;

        private PrefManager appPreferences;


        public LoginConfiguration(Context context, String user_id, String password, String login_mode) {
            mContext = context;
            login_mod = login_mode;
            userid = user_id;
            userpassword = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = ProgressDialog.show(mContext, "", mContext.getResources().getString(R.string.load_singin), false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            appPreferences = new PrefManager(mContext);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            apiService = ApiClient.getClient().create(ApiIHandler.class);
            Call<ModelUser> call = apiService.getLoginAuthenticationStudent(userid, userpassword);
            call.enqueue(new Callback<ModelUser>() {
                @Override
                public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                    ModelUser login_response_data = response.body();

                    if (login_response_data != null) {
                        if (login_response_data.getMessage().equalsIgnoreCase("Login Failure")) {
                            UtilityFunction.showCenteredToast(getApplicationContext(), login_response_data.getMessage());
                            mProgressDialog.dismiss();
                        } else {
                            appPreferences.setCheckUser_id(login_response_data.getSTUDENT_ADMISSION_NO());
                            Gson gson = new Gson();
                            String jsontbl_logindata = gson.toJson(login_response_data);
                            appPreferences.setChecklogindata(jsontbl_logindata);
                            mProgressDialog.dismiss();
                            finish();
                        }

                    }
                }

                @Override
                public void onFailure(Call<ModelUser> call, Throwable t) {
                    // Log error here since request failed
                    UtilityFunction.showCenteredToast(getApplicationContext(), getResources().getString(R.string.error_tryagain));
                    mProgressDialog.dismiss();

                }
            });
        }
    }
}