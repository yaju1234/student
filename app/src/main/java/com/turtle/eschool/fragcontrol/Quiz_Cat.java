package com.turtle.eschool.fragcontrol;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.turtle.eschool.AppController;
import com.turtle.eschool.FullImageActivity;
import com.turtle.eschool.R;
import com.turtle.eschool.ResultActivity;
import com.turtle.eschool.prefControl.PrefManager;
import com.turtle.eschool.schoolmodel.ModelUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.turtle.eschool.fragcontrol.FragWatchVideos.longLog;

/**
 * Created by sohansingh on 3/23/2017.
 */

public final class Quiz_Cat extends Fragment {

    Toolbar toolbar;
    RelativeLayout rv;
    ArrayList<HashMap<String,String>> arraylist=new ArrayList<>();
    ProgressDialog pd;
    String class_id="";
    private PrefManager prefManager;
   // HomeGridAdapter adapter;
    int no_of_categories = -1;
    int score = 0;
    TextView txtQuestion,time;
    ModelUser modelstudent;
    public int counter;
    RadioButton rda,rdb,rdc,rd4;
    Button butNext,butPre;
    String ans;
    View v;
    ListView list;
    int quid = 0;
    int vv = 0;

  public Quiz_Cat(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.quiz_cat, container, false);
        txtQuestion = (TextView)v.findViewById(R.id.question);
        rda = (RadioButton)v.findViewById(R.id.radio0);
        rdb = (RadioButton)v.findViewById(R.id.radio1);
        rdc = (RadioButton)v.findViewById(R.id.radio2);
        rd4 = (RadioButton)v.findViewById(R.id.radio3);
        butNext = (Button)v.findViewById(R.id.button1);
        prefManager = new PrefManager(getActivity());
        String data = prefManager.getChecklogindata();
        Gson gson = new Gson();
        modelstudent = gson.fromJson(data, ModelUser.class);
       // String value = ;
       // Log.d("id_ok_value",value+"");

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup grp = (RadioGroup)v.findViewById(R.id.radioGr);
                RadioButton answer = (RadioButton)v.findViewById(grp.getCheckedRadioButtonId());
                Log.d("answer", "answer: "+answer);
                Log.d("ans", "ans: "+ans);
                final HashMap<String,String> map = arraylist.get(quid);
                if(map.get("answer").toString().equals(answer.getText())){
                    score++;
                    Log.d("Score", "Your score: "+score);
                }
                if(quid == arraylist.size()-1){
                    Intent intent = new Intent(getActivity(), ResultActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("score",score);
                    b.putInt("vv",vv);
                    intent.putExtras(b);
                    startActivity(intent);
                    //currentQuestion = questionList.get(quid);
                }else{
                    quid = quid + 1;
                    setQuestionView(quid);
                }
            }
        });

        return v;
    }
    private void ourHomeCategory(final String value) {
       // final ProgressDialog pDialog = ProgressDialog.show(getActivity(), "", "Please wait ...", true);
        //pDialog.setCancelable(true);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://mdgroupofeducation.com/schoolApi/get-questions.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        longLog("to do list Resopnce------------------------------------------------"+response);

                        try {

                            JSONObject obj=new JSONObject(response);
                            if(obj.getString("message").equals("Question List")){
                                JSONArray array = obj.getJSONArray("result");
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject object = array.getJSONObject(i);
                                    HashMap<String, String> map = new HashMap<>();
                                    map.put("ques_id", object.getString("ques_id"));
                                    map.put("question", object.getString("question"));
                                    map.put("option1", object.getString("option1"));
                                    map.put("option2", object.getString("option2"));
                                    map.put("option3", object.getString("option3"));
                                    map.put("option4", object.getString("option4"));
                                    map.put("answer", object.getString("answer"));
                                   // ans=object.getString("answer");
                                    arraylist.add(map);

                                }
                               // pDialog.hide();
                                setQuestionView(quid);
                               /* adapter = new HomeGridAdapter(getActivity(), arraylist);
                                // Binds the Adapter to the ListView
                                list.setAdapter(adapter);*/

                            }else if ("message".equals("No records found.")){
                                // av.hide();
                                //pDialog.hide();
                                Toast.makeText(getActivity(), obj.getString("No records found.").toString(), Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            Log.d("json Exception",e.toString());
                            // av.hide();
                            //pDialog.hide();
                            Toast.makeText(getActivity(),"Server Data Error Try Again !!!", Toast.LENGTH_LONG).show();
                        }
                        //pDialog.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // av.hide();
                        //pDialog.hide();
                        Log.d("volly error",error.toString());
                        Toast.makeText(getActivity(), "Connection Time Out Error !!", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                //value = getArguments().getString("YourKey");
                map.put("category_id", value);
                map.put("class_id", modelstudent.getClass_id());
                return map;
            }



        };
        AppController.getInstance().addToRequestQueue(stringRequest, "frag");
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                55000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    private void setQuestionView( int pos) {
       // for (int i = 0; i < arraylist.size(); i++) {
            final HashMap<String, String> mk = arraylist.get(pos);
            txtQuestion.setText(mk.get("question"));
            rda.setText(mk.get("option1"));
            rdb.setText(mk.get("option2"));
            rdc.setText(mk.get("option3"));
            rd4.setText(mk.get("option4"));
            ans=mk.get("answer");
      //  }
      //  quid++;
        vv++;
    }
    @Override
    public void onResume() {
        super.onResume();
        score = 0;
        quid = 0;
        vv = 0;
        ourHomeCategory(getArguments().getString("YourKey"));
    }


    @Override
    public void onPause() {
        super.onPause();
      //  System.out.print("yes fragment atteached called");
    }

    @Override
    public void onStop() {
        AppController.getInstance().getRequestQueue().cancelAll("frag");
        super.onStop();
    }







    @Override
    public void onDestroy() {
        super.onDestroy();
    }

/*
    public class HomeGridAdapter extends BaseAdapter {

        // Declare Variables
        Context mContext;
        LayoutInflater inflater;
        private ArrayList<HashMap<String,String>> datalist;

        public HomeGridAdapter(Context context, ArrayList<HashMap<String,String>> worldpopulationlist) {
            mContext = context;
            this.datalist=new ArrayList<>();
            this.datalist = worldpopulationlist;
            inflater = LayoutInflater.from(mContext);
        }

        public class ViewHolder {
            ImageView iv;
            TextView title,rs;
            RadioButton rd1,rd2,rd3,rd4;
        }

        @Override
        public int getCount() {
            return datalist.size();
        }

        @Override
        public HashMap<String,String> getItem(int position) {
            return datalist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View view, ViewGroup parent) {
            final ViewHolder holder;
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.custom_quiz, null);
            view.setTag(holder);
            holder.title=(TextView) view.findViewById(R.id.textView1);
            holder.rd1=(RadioButton) view.findViewById(R.id.radio0);
            holder.rd2=(RadioButton) view.findViewById(R.id.radio1);
            holder.rd3=(RadioButton) view.findViewById(R.id.radio2);
            holder.rd4=(RadioButton) view.findViewById(R.id.radio3);
            final HashMap<String,String> map=datalist.get(position);
            holder.title.setText(map.get("question"));
            holder.rd1.setText(map.get("option1"));
            holder.rd2.setText(map.get("option2"));
            holder.rd3.setText(map.get("option3"));
            holder.rd4.setText(map.get("option4"));

           */
/* if (map.get("image").equals(""))
            {
                holder.iv.setImageResource(R.drawable.app_icon);
            }else{
                Picasso.with(getActivity()).load(map.get("image")).into(holder.iv);
            }*//*

*/
/*
            holder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    startActivity(new Intent(getActivity(), FullImageActivity.class).putExtra("category_id",map.get("image")));
                }
            });
*//*

            return view;
        }

}
*/
}
