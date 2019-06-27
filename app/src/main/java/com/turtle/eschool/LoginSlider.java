package com.turtle.eschool;


import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.turtle.eschool.api.urlsApimanage.ApiClient;
import com.turtle.eschool.api.urlsApimanage.ConnectionManager;
import com.turtle.eschool.interfaces.custom.ApiIHandler;
import com.turtle.eschool.popupcontrol.ForgottonManagerDialog;
import com.turtle.eschool.prefControl.PrefManager;
import com.turtle.eschool.schoolmodel.ModelUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginSlider extends AppCompatActivity {

    ApiIHandler apiService;
    Button btn_loginsubmit;
    TextView textview_forgotpwd;
    EditText et_user_name, et_password;
    LoginConfiguration loginConfiguration;
    PrefManager appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_activity);
        textview_forgotpwd = (TextView) findViewById(R.id.textview_forgotpwd);
        btn_loginsubmit = (Button) findViewById(R.id.btn_loginsubmit);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_password = (EditText) findViewById(R.id.et_password);
        appPreferences = new PrefManager(LoginSlider.this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#E6E6E6"));
        }
        textview_forgotpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                ForgottonManagerDialog dialogFragment = new ForgottonManagerDialog();
                dialogFragment.show(fm, "Forgot Fragment");
            }
        });
        btn_loginsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
               /*Intent i = new Intent(LoginSlider.this, DrawerActivity.class);
                startActivity(i);*/
            }
        });
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
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void login() {

        String user_name = et_user_name.getText().toString();
        String password = et_password.getText().toString();

        if (user_name.isEmpty()) {
            showCenteredToast(getApplicationContext(), getResources().getString(R.string.error_un));
        } else if (password.isEmpty()) {
            showCenteredToast(getApplicationContext(), getResources().getString(R.string.error_ps));
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            if (new ConnectionManager(this).isConnectedToInternet()) {
                loginConfiguration = new LoginConfiguration(LoginSlider.this, user_name, password, "1");
                loginConfiguration.execute();
            } else {
                // show toast message , Network not available
                showCenteredToast(getApplicationContext(), getResources().getString(R.string.app_networkError));
            }
        }
    }

    public static void showBottomSnackbar(View viewBar, String msg) {
        Snackbar.make(viewBar, msg, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }

    public static void showCenteredToast(Context ctx, String msg) {
        Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_LONG);
        toast.show();
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
                            showCenteredToast(getApplicationContext(), login_response_data.getMessage());
                            mProgressDialog.dismiss();
                        } else {
                            appPreferences.setCheckUser_id(login_response_data.getSTUDENT_ADMISSION_NO());
                            appPreferences.setImage(login_response_data.getIMAGE());
                            appPreferences.setClass_id(login_response_data.getClass_id());
                            Gson gson = new Gson();
                            String jsontbl_logindata = gson.toJson(login_response_data);
                            appPreferences.setChecklogindata(jsontbl_logindata);
                            appPreferences.setClass_id(jsontbl_logindata);
                            appPreferences.setCheckloginusername(userid);
                            appPreferences.setCheckloginpassword(userpassword);
                            mProgressDialog.dismiss();
                            Intent i = new Intent(LoginSlider.this, DrawerActivity.class);
                            startActivity(i);
                            finish();
                        }

                    }
                }
                @Override
                public void onFailure(Call<ModelUser> call, Throwable t) {
                    // Log error here since request failed
                    showCenteredToast(getApplicationContext(), getResources().getString(R.string.error_tryagain));
                    mProgressDialog.dismiss();

                }
            });
        }
    }


}