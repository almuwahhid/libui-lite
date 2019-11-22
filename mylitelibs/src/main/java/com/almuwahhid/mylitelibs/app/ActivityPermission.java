package com.almuwahhid.mylitelibs.app;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.almuwahhid.mylitelibs.R;
import com.almuwahhid.mylitelibs.app.Interfaces.PermissionResultInterface;


import java.util.ArrayList;


/**
 * Created by root on 2/26/18.
 */

public class ActivityPermission extends AppCompatActivity {
    private int KEY_PERMISSION = 0;
    private PermissionResultInterface permissionResultInterface;
    private String permissionsAsk[];

    public Context getContext(){
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * Does some thing in old style.
     *
     * @deprecated use {@link #askCompactPermission(String, PermissionResultInterface)}  instead.
     */
    @Deprecated
    public ActivityPermission askPermission(String permission) {
        this.permissionsAsk = new String[]{permission};
        return ActivityPermission.this;
    }

    /**
     * Does some thing in old style.
     *
     * @deprecated use {@link #askCompactPermissions(String[], PermissionResultInterface)} instead.
     */
    @Deprecated
    public ActivityPermission askPermissions(String permissions[]) {
        this.permissionsAsk = permissions;
        return ActivityPermission.this;
    }

    public ActivityPermission setPermissionResult(PermissionResultInterface permissionResultInterface) {
        this.permissionResultInterface = permissionResultInterface;
        return ActivityPermission.this;
    }

    public ActivityPermission requestPermission(int keyPermission) {
        KEY_PERMISSION = keyPermission;
        internalRequestPermission(permissionsAsk);
        return ActivityPermission.this;
    }


    public boolean isPermissionGranted(Context context, String permission) {
        return (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) || (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED);
    }


    private void internalRequestPermission(String[] permissionAsk) {
        String arrayPermissionNotGranted[];
        ArrayList<String> permissionsNotGranted = new ArrayList<>();

        for (int i = 0; i < permissionAsk.length; i++) {
            if (!isPermissionGranted(ActivityPermission.this, permissionAsk[i])) {
                permissionsNotGranted.add(permissionAsk[i]);
            }
        }


        if (permissionsNotGranted.isEmpty()) {

            if (permissionResultInterface != null)
                permissionResultInterface.permissionGranted();

        } else {

            arrayPermissionNotGranted = new String[permissionsNotGranted.size()];
            arrayPermissionNotGranted = permissionsNotGranted.toArray(arrayPermissionNotGranted);
            ActivityCompat.requestPermissions(ActivityPermission.this, arrayPermissionNotGranted, KEY_PERMISSION);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == KEY_PERMISSION) {
            boolean granted = true;

            for (int grantResult : grantResults) {
                if (!(grantResults.length > 0 && grantResult == PackageManager.PERMISSION_GRANTED))
                    granted = false;
            }
            if (permissionResultInterface != null) {
                if (granted) {
                    permissionResultInterface.permissionGranted();
                } else {
                    permissionResultInterface.permissionDenied();
                }
            }
        } else {
            Log.e("ManagePermission", "permissionResultInterface callback was null");
        }
    }

    public void askCompactPermission(String permission, PermissionResultInterface permissionResultInterface) {
        KEY_PERMISSION = 200;
        permissionsAsk = new String[]{permission};
        this.permissionResultInterface = permissionResultInterface;
        internalRequestPermission(permissionsAsk);
    }

    public void askCompactPermissions(String permissions[], PermissionResultInterface permissionResultInterface) {
        KEY_PERMISSION = 200;
        permissionsAsk = permissions;
        this.permissionResultInterface = permissionResultInterface;
        internalRequestPermission(permissionsAsk);
    }


    @Override
    protected void onPause() {
        overridePendingTransition(R.anim.pull_stay, R.anim.push_out_right);
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        Runtime.getRuntime().gc();
        System.gc();
        super.onDestroy();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
