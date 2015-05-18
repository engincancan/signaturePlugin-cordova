package com.ecc.signature;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * This class echoes a string called from JavaScript.
 */
public class signaturePlugin extends CordovaPlugin {

    private String TAG = "signaturePlugin";
    private CallbackContext callbackContext;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        if (action.equals("getSignature")) {
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
        i.putExtra("isTransparent", isTransparent);
        cordova.startActivityForResult(this,i,90);
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(requestCode == 90) {
             if (resultCode == this.cordova.getActivity().RESULT_OK) {
                 Bundle res = intent.getExtras();
                 String result = res.getString("results");
                 this.callbackContext
                 .success(result.toString());
             } else {
                 this.callbackContext.error("Error:"+resultCode);
             }
     }
    }
}
