package com.mdgroup.parents.utilityschool;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Toast;

import com.mdgroup.parents.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class UtilityFunction {

    // Define static String variable
    public static String imeiNumber;

    public static void showMessage(Context context, String message) {
        // Create an object of the AlertDialog.Builder class
        AlertDialog.Builder alertbox = new AlertDialog.Builder(context);

        // set the title for this Alert Dialog
        alertbox.setTitle(context.getResources().getString(R.string.error_message));
        // set the message for this Alert Dialog
        alertbox.setMessage(message);
        alertbox.setCancelable(false);
        alertbox.setNeutralButton(context.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Dismsss the Dialog on click on OK.
                dialog.dismiss();
            }
        });
        alertbox.show();
    }

    public static void showBottomSnackbar(View viewBar, String msg) {
        Snackbar.make(viewBar, msg, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }

    public static void showCenteredToast(Context ctx, String msg) {
        Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void showCenteredToastShort(Context ctx, String msg) {
        Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    @SuppressLint("NewApi")
    public static String getIMEINumber(Context context) {
        // Create an instance of the TelephonyManager
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        // get the device id via telephonyManage
        imeiNumber = telephonyManager.getDeviceId();

        if (imeiNumber == null) {
            imeiNumber = android.os.Build.SERIAL;
        }

        return imeiNumber;
    }

    // get current UTC time
    @SuppressLint("SimpleDateFormat")
    /**
     *
     * @return
     */


    public static String currentUTCTimeFormat() {
        // Create an instance of the SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        // set the TimeZone
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        // return date in the specified format
        return dateFormat.format(new Date());
    }

    public static String currentUTCTime() {
        // Create an instance of the SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MMM-yyyy HH:mm:ss");
        // set the TimeZone
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        // return date in the specified format
        return dateFormat.format(new Date());
    }

    public static String getDate(String time) {
        long t = Long.parseLong(time) * 1000;
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy hh:mm aa");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(t);
        String s = format.format(calendar.getTime());
        return s;
    }

    public static String GetLocalDateStringFromUTCString(String utcLongDateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String formatedDate = "";
        try {
            Date date = dateFormat.parse(utcLongDateTime);

            dateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm aa");
            formatedDate = dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return formatedDate;
    }

    public static String GetLocaTwittermUTCString(String utcLongDateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss Z yyyy");
        String formatedDate = "";
        try {
            Date date = dateFormat.parse(utcLongDateTime);

            dateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm aa");
            formatedDate = dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return formatedDate;
    }


//get current time without dash

    /**
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String currentUTCTimeWithoutDash() {
        // Create an instance of the SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd MMM yyyy HH:mm:ss");
        // set the TimeZone
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        // return date in the specified format
        return dateFormat.format(new Date());
    }

    public static void showMessage_S(Context context, String message) {
        // Create an object of the AlertDialog.Builder class
        AlertDialog.Builder alertbox = new AlertDialog.Builder(context);

        // set the title for this Alert Dialog
        alertbox.setTitle(context.getResources().getString(R.string.success_message));
        // set the message for this Alert Dialog
        alertbox.setMessage(message);
        alertbox.setCancelable(false);
        alertbox.setNeutralButton(context.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Dismsss the Dialog on click on OK.
                dialog.dismiss();
            }
        });
        alertbox.show();
    }

    public static String dateConvert(String date_data) {
        String newFormat = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);

            try {
                newFormat = formatter.format(new SimpleDateFormat("yyyy-MM-dd").parse(date_data));//"2016-06-03"
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newFormat;
    }
    public static String dateConvertUpdates(String date_data) {
        String newFormat = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

            try {
                newFormat = formatter.format(new SimpleDateFormat("yyyy-MM-dd").parse(date_data));//"2016-06-03"
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newFormat;
    }

}