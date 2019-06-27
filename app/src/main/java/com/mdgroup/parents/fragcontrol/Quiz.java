package com.mdgroup.parents.fragcontrol;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
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
import android.widget.RelativeLayout;
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
import com.mdgroup.parents.AppController;
import com.mdgroup.parents.R;
import com.mdgroup.parents.prefControl.PrefManager;
import com.mdgroup.parents.schoolmodel.ModelUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by sohansingh on 3/23/2017.
 */

public class Quiz extends Fragment {
    private ViewPager mViewPag;
    public TabLayout tabLayout;
    Toolbar toolbar;
    RelativeLayout rv;
    private PrefManager prefManager;
    ProgressDialog pd;
    int no_of_categories = -1;
    String class_id="";
    ModelUser modelstudent;
    View v;
    ArrayList<HashMap<String, String>> CatlistDes = new ArrayList<>();

    public Quiz (){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.activity_quiz, container, false);

        mViewPag = (ViewPager)v.findViewById(R.id.viewpr);
        mViewPag.requestDisallowInterceptTouchEvent(true);
        tabLayout = (TabLayout)v.findViewById(R.id.tabbsss);
        prefManager = new PrefManager(getActivity());
        String data = prefManager.getChecklogindata();
        Gson gson = new Gson();
        modelstudent = gson.fromJson(data, ModelUser.class);
        //av = (AVLoadingIndicatorView)v.findViewById(R.id.avi);
        return v;
    }
    private void MenuChild() {
        final ProgressDialog pDialog = ProgressDialog.show(getActivity(), "", "Please wait ...", true);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://mdgroupofeducation.com/schoolApi/get-quiz-categories.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Resopnce Show Cart----", response);
                        if (!CatlistDes.isEmpty())
                            CatlistDes.clear();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.getString("message").equals("Quiz Categories List")) {
                                JSONArray jsonArray = obj.getJSONArray("result");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject ob = jsonArray.getJSONObject(i);
                                    HashMap<String, String> map = new HashMap<>();
                                    map.put("id", ob.getString("id"));
                                    map.put("name", ob.getString("name"));
                                    CatlistDes.add(map);
                                }
                                pDialog.hide();
                                setupViewPager(mViewPag);
                                tabLayout.setupWithViewPager(mViewPag);
                                // setCateg();
                            }
                            else if (obj.getString("status").equals("error")){
                                //rv.setBackgroundResource(R.drawable.norecord);
                            }
                            else if (obj.getString("error").equals("No Data Found")){
                                //rv.setBackgroundResource(R.drawable.norecord);
                            }
                        } catch (JSONException e) {
                            Log.d("json Exception", e.toString());
                            // avloader.hide();
                            pDialog.hide();
                            Toast.makeText(getActivity(), "Server Error Try Again !!!", Toast.LENGTH_SHORT).show();

                        }
                        pDialog.hide();
                        // hideProgressDialog();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // avloader.hide();
                        pDialog.hide();
                        Toast.makeText(getActivity(), "Connection Time Out Error Try Again !!", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("class_id",  modelstudent.getClass_id());
                return map;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.print("yes ------------------------------------------- resume atteched");
        getActivity().setTitle("Quiz");
            MenuChild();

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.print("yes ----------------------------------------- atteched");
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.print("yes ------------------------------------------- start atteched");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.print("yes ------------------------------------------- pause atteched");
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

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        //  adapter.addFragment(new FragToDoList(), "TO DO LIST");
        for (int i = 0; i < CatlistDes.size(); i++) {
            final HashMap<String, String> mk = CatlistDes.get(i);
            //String id=mk.get("cat_id");
            Log.d("id_mk",mk.get("id"));
           final Quiz_Cat ldf = new Quiz_Cat ();
            Bundle args = new Bundle();
            args.putString("YourKey", mk.get("id"));
            ldf.setArguments(args);
            adapter.addFrag(ldf, mk.get("name"));
          /*  OneFragment fView = new OneFragment();
            View view = fView.getView();

            TextView txtTabItemNumber = (TextView)view.findViewById(R.id.txtTabItemNumber);
            txtTabItemNumber.setText("TAB " + mk.get("name"));
            adapter.addFrag(fView,"TAB " + i);*/

            // adapter.addFragment(new FragShowAds(), "SHOW ADS");
            // adapter.addFragment(new FragClicklinks(), "CLICK LINKS");
            // adapter.addFragment(new FragWatchVideos(), "WATCH VIDEOS");
            //adapter.addFragment(new FragTasks(), "TASKS");
            //   adapter.addFragment(new Services(), "SERVICES");
            viewPager.setAdapter(adapter);
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFrag(final Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
