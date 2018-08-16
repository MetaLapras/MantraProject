package com.pasistence.mantrafingerprint.FingerPrintMatching;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.mantra.mfs100.FingerData;
import com.mantra.mfs100.MFS100;
import com.mantra.mfs100.MFS100Event;
import com.pasistence.mantrafingerprint.Main.MFS100TestActivity;

public class MFS100Mantra implements MFS100Event {

    Activity activity;



    public MFS100Mantra(Activity activity) {
        this.activity = activity;
    }

    private enum ScannerAction {
        Capture, Verify
    }

    byte[] Enroll_Template;
    byte[] Verify_Template;
    private FingerData lastCapFingerData = null;
    ScannerAction scannerAction = ScannerAction.Capture;
    private static final String TAG = "MFS-->";

    int timeout = 10000;
    MFS100 mfs100 = null;

    private boolean isCaptureRunning = false;

    @Override
    public void OnDeviceAttached(int i, int i1, boolean b) {

    }

    @Override
    public void OnDeviceDetached() {

    }

    @Override
    public void OnHostCheckFailed(String s) {

    }

    public void onStart()
    {
        if (mfs100 == null) {
            mfs100 = new MFS100(this);
            mfs100.SetApplicationContext(activity);
        } else {
            InitScanner();
        }
    }

    private void InitScanner() {
        try {
            int ret = mfs100.Init();
            if (ret != 0) {
                SetTextOnUIThread(mfs100.GetErrorMsg(ret));
            } else {
                SetTextOnUIThread("Init success");
                String info = "Serial: " + mfs100.GetDeviceInfo().SerialNo()
                        + " Make: " + mfs100.GetDeviceInfo().Make()
                        + " Model: " + mfs100.GetDeviceInfo().Model()
                        + "\nCertificate: " + mfs100.GetCertification();
                //SetLogOnUIThread(info);
            }
        } catch (Exception ex) {
         /*   Toast.makeText(this, "Init failed, unhandled exception",
                    Toast.LENGTH_LONG).show();*/
            SetTextOnUIThread("Init failed, unhandled exception");
        }
    }

    private void SetTextOnUIThread(final String str) {
                //lblMessage.setText(str);
        Log.e(TAG, str);
    }
}
