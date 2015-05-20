package com.ecc.signature;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * This class echoes a string called from JavaScript.
 */
public class signaturePlugin extends CordovaPlugin {

    private String TAG = "signaturePlugin";
    private CallbackContext callbackContext;
    private JSONArray buttonNames;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        if (action.equals("init")) {
            
            if (!args.equals(null) &&  args.length() == 3 && !args.get(0).equals(null)) {
                buttonNames = args;
            }
            //buttonNames = args.toString();
            callbackContext.success("Success");
            return true;
        } else if (action.equals("getSignature")) {
            startMyActivity(false);
            return true;
        } else if (action.equals("getTransparentSignature")) {
            startMyActivity(true);
            return true;
        }
        return false;
    }
    
    private void startMyActivity(boolean isTransparent){
        PluginResult r = new PluginResult(PluginResult.Status.NO_RESULT);
        r.setKeepCallback(true);
        callbackContext.sendPluginResult(r);
        Intent i = new Intent(this.cordova.getActivity().getApplicationContext(), myActivity.class);
        //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("isTransparent", isTransparent);
        if (!buttonNames.equals(null) &&  buttonNames.length() == 3){
            i.putExtra("buttonNames", buttonNames.toString());
        }
        cordova.startActivityForResult(this,i,90);
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Log.d(TAG, "activity result in plugin: requestCode(" + requestCode + "), resultCode(" + resultCode + ")");
        if(requestCode == 90) {
             if (resultCode == this.cordova.getActivity().RESULT_OK) {
                 Bundle res = intent.getExtras();
                 String result = res.getString("results");
                 Log.d("FIRST", "result:"+result);
                 this.callbackContext
                 .success(result.toString());
             } else {
                 this.callbackContext.error("Error");
             }
     }
    }
}
