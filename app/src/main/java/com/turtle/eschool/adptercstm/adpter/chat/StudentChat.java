package com.turtle.eschool.adptercstm.adpter.chat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.turtle.eschool.R;
import com.turtle.eschool.api.urlsApimanage.ApiClient;
import com.turtle.eschool.interfaces.custom.ApiIHandler;
import com.turtle.eschool.prefControl.PrefManager;
import com.turtle.eschool.schoolmodel.ForgotPasswordModal;
import com.turtle.eschool.schoolmodel.ModelTeacher;
import com.turtle.eschool.schoolmodel.ResponseAllChat;
import com.turtle.eschool.utilityschool.UtilityFunction;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class StudentChat extends Activity {
    private static final String TAG = "ChatActivity";

    private ChatArrayAdapter chatArrayAdapter;
    private ListView listView;
    private EditText chatText;
    private Button buttonSend;
    ImageView ic_back;
    TextView t_name, t_name_sub;
    PrefManager prefManager;

    Intent intent;
    private String side = "s";
    ModelTeacher modelTeacher;
    TextView chatdata_not_availabe;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        setContentView(R.layout.activity_chat);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#1a458c"));
        }

        prefManager = new PrefManager(StudentChat.this);
        buttonSend = (Button) findViewById(R.id.buttonSend);
        setTitle("Chat");
        listView = (ListView) findViewById(R.id.listView1);
        ic_back = (ImageView) findViewById(R.id.ic_back);
        t_name = (TextView) findViewById(R.id.t_name);
        t_name_sub = (TextView) findViewById(R.id.t_name_sub);
        chatdata_not_availabe= (TextView) findViewById(R.id.chatdata_not_availabe);
        chatdata_not_availabe.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        modelTeacher = (ModelTeacher) intent.getSerializableExtra("chatobjectdata");

        if (modelTeacher != null) {
            t_name.setText(modelTeacher.getTEACHERS_NAME());
            t_name_sub.setText(modelTeacher.getCLASSES_NAME());
        }
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });




        chatText = (EditText) findViewById(R.id.chatText);
       /* chatText.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    sendChatMessage();
                }
                return false;
            }
        });*/
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage();
            }
        });



       /* //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });*/

        getTeacherChat(prefManager.getCheckUser_id(),"1");
    }

    public void getchatTeacherData(String sid, String tid) {

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

    void getTeacherChat(String checkUser_id, String s)
    {
        final ApiIHandler apiService =
                ApiClient.getClient().create(ApiIHandler.class);

        final ProgressDialog pDialog = ProgressDialog.show(StudentChat.this, "", "Please wait ...", true);

        Call<ResponseAllChat> call = apiService.getChatdata(checkUser_id, s);
        call.enqueue(new Callback<ResponseAllChat>() {
            @Override
            public void onResponse(Call<ResponseAllChat> call, retrofit2.Response<ResponseAllChat> response) {
                pDialog.dismiss();
                if (response.code() == 200) {
                    ResponseAllChat data = response.body();
                    pDialog.hide();

                    if(!data.getMessage().equalsIgnoreCase("No records found."))
                    {
                        if (data.getResult().size() > 0) {
                            listView.setVisibility(View.VISIBLE);
                            chatdata_not_availabe.setVisibility(View.GONE);
                            List<ChatMessage> chatMessageList  = data.getResult();
                           // Collections.reverse(chatMessageList);
                            chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.activity_chat_singlemessage,chatMessageList);
                            listView.setAdapter(chatArrayAdapter);
                            listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
                        }
                    }
                    else {
                        pDialog.hide();
                        chatdata_not_availabe.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseAllChat> call, Throwable t) {
               if (pDialog != null && pDialog.isShowing())
                    pDialog.hide();


            }
        });
    }
    private void sendChatMessage() {
        String msg_chat = chatText.getText().toString();
        if (msg_chat.length() > 0) {
            chatdata_not_availabe.setVisibility(View.GONE);
            prefManager = new PrefManager(getApplicationContext());
            String ss=prefManager.getCheckUser_id();


            System.out.println("CAS:"+prefManager.getCheckUser_id());

            sendmessagetoServer(msg_chat, prefManager.getCheckUser_id(), modelTeacher.getTEACHERS_ID());
           // chatArrayAdapter.add(new ChatMessage("", chatText.getText().toString(),"","","",side));
            chatText.setText("");
           // side = !side;
          //  return true;
        } else {
            UtilityFunction.showCenteredToast(getApplicationContext(), "Please enter chat message.");
            //return false;
        }
    }

    public void sendmessagetoServer(String chatmessage, String sid, String tid) {
        final ApiIHandler apiService =
                ApiClient.getClient().create(ApiIHandler.class);

       // final ProgressDialog pDialog = ProgressDialog.show(StudentChat.this, "", "Please wait ...", true);

        Call<ForgotPasswordModal> call = apiService.sendmessagedataobject(chatmessage, sid, tid,"s");
        call.enqueue(new Callback<ForgotPasswordModal>() {
            @Override
            public void onResponse(Call<ForgotPasswordModal> call, retrofit2.Response<ForgotPasswordModal> response) {
                //pDialog.dismiss();
                if (response.code() == 200) {
                    ForgotPasswordModal data = response.body();
                   // pDialog.hide();
                    if (data.getResult().equalsIgnoreCase("1")) {
                      //  pDialog.hide();
                        Toast.makeText(getApplicationContext(), data.getMessage(), Toast.LENGTH_SHORT).show();
                        getTeacherChat(prefManager.getCheckUser_id(), "1");
                    } else {
                        Toast.makeText(getApplicationContext(), data.getMessage(), Toast.LENGTH_SHORT).show();
                      //  pDialog.hide();
                    }
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordModal> call, Throwable t) {
                // Log error here since request failed
                Log.e("", t.toString());
               /* if (pDialog != null && pDialog.isShowing())
                    pDialog.hide();*/
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_tryagain), Toast.LENGTH_SHORT).show();
            }
        });
    }

}