package com.bcits.roomdbexample;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

@TargetApi(23)
public class Splash extends Activity {
    private static final int REQUEST_CODE = 185;
    private String[] permissions = new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    private boolean showedPermission = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

      /*  try {//REGISTER TO FCM WITH THE PACKAGE AS TOPIC
            FirebaseMessaging.getInstance().subscribeToTopic(getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
       /* try { //SETTING SCHEDULER FOR SENDING DEVICE STATUS
       //     Intent myIntent = new Intent(Splash.this, StatusUpdater.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(Splash.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, 30);
            long interval = 30 * 60 * 1000;//30 MINUTES
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval, pendingIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!showedPermission && !checkIfAlreadyHavePermission()) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);
                showedPermission = true;
                return;
            }
        }
        try {
            //   AmrMethods.addActivityShortCuts(Splash.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //   setAllKioskSettings();
        openMainScreen();
        //   AmrMethods.checkForUpdate(Splash.this);

    }
   /* @SuppressLint("WrongConstant")
    private void setAllKioskSettings(){
        try {
            EnterpriseDeviceManager edm = (EnterpriseDeviceManager) getSystemService(EnterpriseDeviceManager.ENTERPRISE_POLICY_SERVICE);
            ApplicationPolicy appPolicy = edm.getApplicationPolicy();
            RestrictionPolicy restrictionPolicy = edm.getRestrictionPolicy();
            new RunSingleSetup(false,Splash.this,restrictionPolicy,appPolicy).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/


    boolean checkIfAlreadyHavePermission() {
        for (String permission : permissions) {
            int result = ContextCompat.checkSelfPermission(this, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0) {
                    for (int grantResult : grantResults) {
                        if (!(grantResult == PackageManager.PERMISSION_GRANTED)) { // IF ANY PERMISSION NOT GRANTED WILL ASK AGAIN
                            System.out.println("###############NO PERMISSION  #######################" + grantResult);
                            Toast.makeText(getApplicationContext(), "Please Grant Permissions", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Splash.this, MainActivity.class));

                            finish();
                            return;
                        }
                    }
                    System.out.println("######################################");
                    openMainScreen();
                } else {
                    System.out.println("###############NO PERMISSION SIZE 0 #######################");
                    ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);
                }
                break;
            default:
//		            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void openMainScreen() {
        startActivity(new Intent(Splash.this, MainActivity.class));
        finish();
    }
}

