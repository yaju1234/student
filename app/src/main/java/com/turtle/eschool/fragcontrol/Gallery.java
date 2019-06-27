package com.turtle.eschool.fragcontrol;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;
import com.turtle.eschool.AppController;
import com.turtle.eschool.FullImageActivity;
import com.turtle.eschool.R;

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

public class Gallery extends Fragment {
    View v;
    GridView gridView;
    HomeGridAdapter adapter;
    ArrayList<HashMap<String,String>> arraylist=new ArrayList<>();
    private ArrayList<HashMap<String,String>> hcategoryarray = new ArrayList<>();
    ArrayList<HashMap<String, String>> CatlistDes = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.gallery, container, false);
        gridView=(GridView)v.findViewById(R.id.gv);
        //av = (AVLoadingIndicatorView)v.findViewById(R.id.avi);
        ourHomeCategory();
        /*gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent ii = new Intent(getActivity(), FullImageActivity.class);
                // passing array index
                ii.putExtra("id", i);
                startActivity(ii);
            }
        });*/
        return v;
    }
    private void ourHomeCategory() {
        final ProgressDialog pDialog = ProgressDialog.show(getActivity(), "", "Please wait ...", true);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://mdgroupofeducation.com/schoolApi/get-gallery.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        longLog("to do list Resopnce------------------------------------------------"+response);

                        try {
                            if (arraylist.size()>0){
                                arraylist.clear();
                            }
                            JSONObject obj=new JSONObject(response);
                            if(obj.getString("message").equals("Gallary List")){
                                JSONArray array = obj.getJSONArray("result");
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject object = array.getJSONObject(i);
                                    HashMap<String, String> map = new HashMap<>();
                                    map.put("id", object.getString("id"));
                                    map.put("image", object.getString("image"));
                                    arraylist.add(map);
                                }
                                adapter = new HomeGridAdapter(getActivity(), arraylist);
                                // Binds the Adapter to the ListView
                                gridView.setAdapter(adapter);
                                pDialog.hide();
                            }else {
                                // av.hide();
                                pDialog.hide();
                                Toast.makeText(getActivity(), obj.getString("error").toString(), Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            Log.d("json Exception",e.toString());
                            // av.hide();
                            pDialog.hide();
                            Toast.makeText(getActivity(),"Server Data Error Try Again !!!", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // av.hide();
                        pDialog.hide();
                        Log.d("volly error",error.toString());
                        Toast.makeText(getActivity(), "Connection Time Out Error !!", Toast.LENGTH_LONG).show();
                    }
                }){


        };
        AppController.getInstance().addToRequestQueue(stringRequest, "frag");
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                55000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Gallery");

    }
    @Override
    public void onPause() {
        super.onPause();
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
            view = inflater.inflate(R.layout.listcategory, null);
            view.setTag(holder);
            holder.iv=(ImageView)view.findViewById(R.id.thumb);
            final HashMap<String,String> map=datalist.get(position);


            if (map.get("image").equals(""))
            {
                holder.iv.setImageResource(R.drawable.app_icon);
            }else{
                Picasso.with(getActivity()).load(map.get("image")).into(holder.iv);
            }
            holder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    startActivity(new Intent(getActivity(), FullImageActivity.class).putExtra("category_id",map.get("image")));
                }
            });
            return view;
        }
    }


}
