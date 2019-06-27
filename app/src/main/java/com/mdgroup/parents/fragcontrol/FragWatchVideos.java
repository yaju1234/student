package com.mdgroup.parents.fragcontrol;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;
import com.mdgroup.parents.AppController;
import com.mdgroup.parents.MainActivity;
import com.mdgroup.parents.R;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by sohansingh on 3/23/2017.
 */

public class FragWatchVideos extends Fragment {
    ListView list;
    Context ctx=getActivity();
    RelativeLayout body;
    String videoUrl="http://www.youtube.com/watch?v=";
    ListViewAdapter adapter;
    ArrayList<HashMap<String,String>> arraylist=new ArrayList<>();
    private int REQ_PLAYER_CODE     = 1;
    private static String YT_KEY    = "";
    String value;
    View v;
    AVLoadingIndicatorView av;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_watchvideo, container, false);
        list = (ListView)v.findViewById(R.id.list_video);

        String value = getArguments().getString("YourKey");
        Log.d("id_ok_value",value+"");
        //av = (AVLoadingIndicatorView)v.findViewById(R.id.avi);
        body=(RelativeLayout)v.findViewById(R.id.mainLayout);
        GetVideos(value);
        return v;
    }

    private void GetVideos(final String value){
       // av.show();
        final ProgressDialog pDialog = ProgressDialog.show(getActivity(), "", "Please wait ...", true);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://mdgroupofeducation.com/schoolApi/get-videos.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      longLog("to do list Resopnce------------------------------------------------"+response);

                        try {
                            if (arraylist.size()>0){
                                arraylist.clear();
                            }
                            JSONObject obj=new JSONObject(response);
                            if(obj.getString("message").equals("Videos List")){
                                    JSONArray array = obj.getJSONArray("result");
                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject object = array.getJSONObject(i);
                                        HashMap<String, String> map = new HashMap<>();
                                        map.put("id", object.getString("id"));
                                        map.put("name", object.getString("name"));
                                        map.put("URL", object.getString("URL"));
                                        arraylist.add(map);
                                    }
                                    adapter = new ListViewAdapter(getActivity(), arraylist);
                                    // Binds the Adapter to the ListView
                                    list.setAdapter(adapter);
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
                        Toast.makeText(ctx, "Connection Time Out Error !!", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                //value = getArguments().getString("YourKey");
                map.put("category_id", value);
                return map;
            }


        };
        AppController.getInstance().addToRequestQueue(stringRequest, "frag");
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                55000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    public static void longLog(String str) {
        if (str.length() > 4000) {
            Log.d("", str.substring(0, 4000));
            longLog(str.substring(4000));
        } else
            Log.d("", str);


    }


    public class ListViewAdapter extends BaseAdapter {

        // Declare Variables
        Context mContext;
        LayoutInflater inflater;
        private ArrayList<HashMap<String,String>> datalist;

        public ListViewAdapter(Context context, ArrayList<HashMap<String,String>> worldpopulationlist) {
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
            view = inflater.inflate(R.layout.listlayoutvideos, null);
            // Locate the TextViews in listview_item.xml
            holder.iv = (ImageView) view.findViewById(R.id.videoView);
            holder.title = (TextView) view.findViewById(R.id.title);
            holder.rs = (TextView) view.findViewById(R.id.amt);

         final HashMap<String,String> map=datalist.get(position);

            holder.title.setText(map.get("name"));
            //holder.rs.setText("Get "+map.get("points")+" Points");

            Log.d("watch video called","yes");

            final String id=map.get("URL");

            String thumb="http://img.youtube.com/vi/"+id+"/0.jpg";

            Picasso.with(getActivity()).load(thumb).into(holder.iv);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    videoUrl=videoUrl.concat(id);
                    startActivity(new Intent(getActivity(),MainActivity.class).putExtra("id",id).putExtra("tt",map.get("name")));
                }
            });

            view.setTag(holder);

            return view;
        }

    }

    @Override
    public void onResume() {
        super.onResume();

    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

}
