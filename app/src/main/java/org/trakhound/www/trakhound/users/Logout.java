package org.trakhound.www.trakhound.users;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.trakhound.www.trakhound.MainActivity;
import org.trakhound.www.trakhound.MyApplication;
import org.trakhound.www.trakhound.UserHome;


public class Logout extends AsyncTask<String,Void,String> {

    private Context context;
    private ProgressDialog progressDialog;

    public Logout(Context context, ProgressDialog progressDialog) {

        this.context = context;
        this.progressDialog = progressDialog;
    }


    @Override
    protected String doInBackground(String... arg0) {

        if (arg0.length == 1) {

            String senderId = arg0[0];

            UserManagement.deleteToken(senderId);
        }

        return null;
    }

    @Override
    protected void onPostExecute(String dummy){

        UserHome.clearData();

        // Clear MyApplication variables
        ((MyApplication)((Activity)context).getApplication()).User = null;
        ((MyApplication)((Activity)context).getApplication()).Devices = null;
        ((MyApplication)((Activity)context).getApplication()).LoggedIn = false;

        // Clear the Remember Me Token in SharedPreferences
        UserManagement.clearRememberToken();

        // Open the Main Login Screen
        context.startActivity(new Intent(context, MainActivity.class));

        progressDialog.dismiss();
    }

}