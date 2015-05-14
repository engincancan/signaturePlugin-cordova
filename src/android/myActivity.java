package com.ecc.signature;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.*;

public class myActivity extends Activity
{

    private SignaturePad mSignaturePad;
    private Button mClearButton;
    private Button mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getApplication().getResources().getIdentifier(
                "activity_signature", "layout",
                getApplication().getPackageName()));

        mSignaturePad = (SignaturePad) findViewById(getApplication()
                .getResources().getIdentifier("signature_pad", "id",
                        getApplication().getPackageName()));
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onSigned()
            {
                mSaveButton.setEnabled(true);
                mClearButton.setEnabled(true);
            }

            @Override
            public void onClear()
            {
                mSaveButton.setEnabled(false);
                mClearButton.setEnabled(false);
            }
        });

        mClearButton = (Button) findViewById(getApplication().getResources()
                .getIdentifier("clear_button", "id",
                        getApplication().getPackageName()));
        mSaveButton = (Button) findViewById(getApplication().getResources()
                .getIdentifier("save_button", "id",
                        getApplication().getPackageName()));

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mSignaturePad.clear();
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                String base64Image = convertBitmapToBase64(signatureBitmap);
                finishWithResult(base64Image, Activity.RESULT_OK);
            }
        });
    }

    public String convertBitmapToBase64(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return "data:image/png;base64," + encoded;
    }

    private void finishWithResult(String result, int status)
    {
        Bundle conData = new Bundle();
        conData.putString("results", result);
        Intent intent = new Intent();
        intent.putExtras(conData);
        setResult(status, intent);
        finish();
    }
}
