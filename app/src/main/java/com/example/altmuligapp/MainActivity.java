package com.example.altmuligapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private boolean torch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String cameraId = null;

        try {
            cameraId = camManager.getCameraIdList()[0];

            camManager.setTorchMode(cameraId, false);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void ToggleFlashlight(View v) {
        CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String cameraId = null;

        try {
            cameraId = camManager.getCameraIdList()[0];

            camManager.setTorchMode(cameraId, !this.torch);

            this.torch = !this.torch;
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void OpenYoutube(View v) {
        Uri uri = Uri.parse("http://www.youtube.com"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void OpenNextActivity(View v) {
        Intent launchIntent = new Intent(MainActivity.this, MainActivity2.class);

        EditText e = this.findViewById(R.id.editTextTextPersonName);

        launchIntent.putExtra("data", e.getText().toString());
        startActivity(launchIntent);
    }
}