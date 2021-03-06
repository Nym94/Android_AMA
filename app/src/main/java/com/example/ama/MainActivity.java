package com.example.ama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Fragment fragment1;
    //Fragment fragment2;
    Fragment fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1AddApp();
        //fragment3 = new Fragment3MainMenu();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        setAccessibilityPermissions();
        //setOverlayPermission();
    }

//    public boolean checkAccessibilityPermissions() {
//        AccessibilityManager accessibilityManager = (AccessibilityManager)this.getSystemService(Context.ACCESSIBILITY_SERVICE);
//
//        List<AccessibilityServiceInfo> list =
//                accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC);
//
//        Log.d("service_test", "size : " + list.size());
//        for(int i = 0; i < list.size(); i++){
//            AccessibilityServiceInfo info = list.get(i);
//            if(info.getResolveInfo().serviceInfo.packageName.equals(this.getApplication().getPackageName())){
//                return true;
//            }
//        }
//        return false;
//    }

    public void setAccessibilityPermissions(){
        // Check accessibility permission
        AccessibilityManager accessibilityManager = (AccessibilityManager)this.getSystemService(Context.ACCESSIBILITY_SERVICE);

        List<AccessibilityServiceInfo> list =
                accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC);

        Log.d("service_test", "size : " + list.size());
        for(int i = 0; i < list.size(); i++){
            AccessibilityServiceInfo info = list.get(i);
            if(info.getResolveInfo().serviceInfo.packageName.equals(this.getApplication().getPackageName())){
                return;
            }
        }

        AlertDialog.Builder permissionDialog = new AlertDialog.Builder(this);
        permissionDialog.setTitle("????????? ?????? ??????");
        permissionDialog.setMessage("?????? ???????????? ?????? ????????? ????????? ???????????????.");
        permissionDialog.setPositiveButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
                return;
            }
        });
        permissionDialog.setNegativeButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"????????? ????????? ?????????????????????.", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        permissionDialog.show();
    }

    /*
    public void setOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {   // ??????????????? ????????? ??????
            if (!Settings.canDrawOverlays(this)) {              // ????????? ?????? ????????? ??????
                AlertDialog.Builder permissionDialog = new AlertDialog.Builder(this);
                permissionDialog.setTitle("????????? ?????? ????????? ?????? ??????");
                permissionDialog.setMessage("?????? ???????????? ?????? ????????? ?????? ????????? ????????? ???????????????.");
                permissionDialog.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION));
                    }
                });
                permissionDialog.setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                permissionDialog.show();
            }
        }
    }
     */
}