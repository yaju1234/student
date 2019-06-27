package com.mdgroup.parents.api.urlsApimanage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @Gaurav Mangal
 */
public class ConnectionManager
{
	private Context context;
	
	public ConnectionManager(Context mContext){
		context = mContext;
	}

	public boolean isConnectedToInternet()
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager != null) {
			NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null){
            	for (int i = 0; i < info.length; i++){
            		if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
            	}
            }
        }
		return false;
	}
}