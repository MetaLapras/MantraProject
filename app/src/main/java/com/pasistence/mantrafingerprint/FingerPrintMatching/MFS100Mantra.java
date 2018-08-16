package com.pasistence.mantrafingerprint.FingerPrintMatching;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.mantra.mfs100.FingerData;
import com.mantra.mfs100.MFS100;
import com.mantra.mfs100.MFS100Event;
import com.pasistence.mantrafingerprint.Main.MFS100TestActivity;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.database.Database;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MFS100Mantra implements MFS100Event {

    Activity activity;

    ImageView imgFinger;
    String scanFingerprint;
    ArrayList<String> list = new ArrayList<String>();

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
    List<WorkerModel> fingerprint ;

    WorkerModel workerModel ;


    public MFS100Mantra(Activity activity ) {
        this.activity = activity;

    }

    public MFS100Mantra(Activity activity,ImageView imgFinger,WorkerModel workerModel) {
        this.activity = activity;
        this.imgFinger = imgFinger;
        this.workerModel = workerModel;
    }

    @Override
    public void OnDeviceAttached(int vid, int pid, boolean hasPermission) {
        int ret;
        if (!hasPermission) {
            SetTextOnUIThread("Permission denied");
            return;
        }
        if (vid == 1204 || vid == 11279) {
            if (pid == 34323) {
                ret = mfs100.LoadFirmware();
                if (ret != 0) {
                    SetTextOnUIThread(mfs100.GetErrorMsg(ret));
                } else {
                    SetTextOnUIThread("Load firmware success");
                }
            } else if (pid == 4101) {
                String key = "Without Key";
                ret = mfs100.Init();
                if (ret == 0) {
                    showSuccessLog(key);
                } else {
                    SetTextOnUIThread(mfs100.GetErrorMsg(ret));
                }

            }
        }
    }

    @Override
    public void OnDeviceDetached() {
        UnInitScanner();
        SetTextOnUIThread("Device removed");
    }

    @Override
    public void OnHostCheckFailed(String err) {
        try {
            SetLogOnUIThread(err);
            Toast.makeText(activity, err, Toast.LENGTH_LONG).show();
        } catch (Exception ignored) {
        }
    }

    public void onStart() {
        if (mfs100 == null) {
            mfs100 = new MFS100(this);
            mfs100.SetApplicationContext(activity);
        } else {
            InitScanner();
        }
    }

    public void onStop() {
        UnInitScanner();
    }

    public void onDestroy() {
        if (mfs100 != null) {
            mfs100.Dispose();
        }
    }

    public void startCapturing(ImageView imgFinger){
        this.imgFinger = imgFinger;
        scannerAction = ScannerAction.Capture;
        //if false then go..
        if (!isCaptureRunning) {
            //starting to capture the finger print
            StartSyncCapture();
        }
    }

    public void startMatching(){
        scannerAction = ScannerAction.Verify;
        //if false then go..
        if (!isCaptureRunning) {
            //starting to capture the finger print
            StartSyncCapture();
        }
    }

    //starting to capture the finger print
    private void StartSyncCapture() {

        new Thread(new Runnable() {

            @Override
            public void run() {
                SetTextOnUIThread("");
                isCaptureRunning = true;
                try {
                    FingerData fingerData = new FingerData();//predefined class to get result data

                    //Method to capture finger print with in time out session
                    int ret = mfs100.AutoCapture(fingerData, timeout,false);
                    Log.e("StartSyncCapture.RET", ""+ret);
                    if (ret != 0) {
                        SetTextOnUIThread(mfs100.GetErrorMsg(ret));
                    } else {
                        lastCapFingerData = fingerData;
                        final Bitmap bitmap = BitmapFactory.decodeByteArray(fingerData.FingerImage(), 0,
                                fingerData.FingerImage().length);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //set bitmap image to the image view
                                imgFinger.setImageBitmap(bitmap);
                            }
                        });

                        SetTextOnUIThread("Capture Success");
                        String log = "\nQuality: " + fingerData.Quality()
                                + "\nNFIQ: " + fingerData.Nfiq()
                                + "\nWSQ Compress Ratio: "
                                + fingerData.WSQCompressRatio()
                                + "\nImage Dimensions (inch): "
                                + fingerData.InWidth() + "\" X "
                                + fingerData.InHeight() + "\""
                                + "\nImage Area (inch): " + fingerData.InArea()
                                + "\"" + "\nResolution (dpi/ppi): "
                                + fingerData.Resolution() + "\nGray Scale: "
                                + fingerData.GrayScale() + "\nBits Per Pixal: "
                                + fingerData.Bpp() + "\nWSQ Info: "
                                + fingerData.WSQInfo();
                        SetLogOnUIThread(log);

                        SetData2(fingerData);

                    }
                } catch (Exception ex) {
                    SetTextOnUIThread(ex.toString());
                } finally {
                    isCaptureRunning = false;
                }
            }
        }).start();
    }

    public void SetData2(FingerData fingerData) {

        if (scannerAction.equals(ScannerAction.Capture)) {

            Enroll_Template = new byte[fingerData.ISOTemplate().length];

            System.arraycopy(fingerData.ISOTemplate(), 0, Enroll_Template, 0,
                    fingerData.ISOTemplate().length);
            Log.e("capture-->", Enroll_Template.toString());

            String fp1 = Base64.encodeToString(Enroll_Template,Base64.DEFAULT);

            //Adding finger print
            list.add(fp1);
            setScanFingerprint(fp1);
            Log.e("-->", fp1.toString() );
            Log.e("-->", list.toString() );


        } else if (scannerAction.equals(ScannerAction.Verify)) {

            Verify_Template = new byte[fingerData.ISOTemplate().length];

            System.arraycopy(fingerData.ISOTemplate(), 0, Verify_Template, 0,
                    fingerData.ISOTemplate().length);

            Log.e("verify-->", Verify_Template.toString());

            fingerprint = new Database(activity).getAllWorkers();

           for(WorkerModel workerModel : fingerprint)
            {
                byte[] byt1 = Base64.decode(workerModel.getFingerprint1(),Base64.DEFAULT);
                byte[] byt2 = Base64.decode(workerModel.getFingerprint2(),Base64.DEFAULT);

                //for first finger print matching
                int ret = mfs100.MatchISO(byt1, Verify_Template);
                if (ret < 0) {
                    SetTextOnUIThread("Error1: " + ret + "(" + mfs100.GetErrorMsg(ret) + ")");
                } else {
                    //if first finger print match
                    if (ret >= 1400) {
                        SetTextOnUIThread("Finger1 matched with score: " + ret);
                        break;
                    } else {
                        //if second finger print match
                        int ret1 = mfs100.MatchISO(byt2, Verify_Template);
                        if (ret1 < 0) {
                            SetTextOnUIThread("Error2: " + ret1 + "(" + mfs100.GetErrorMsg(ret1) + ")");
                        } else {
                            if (ret1 >= 1400) {
                                SetTextOnUIThread("Finger2 matched with score: " + ret1);
                                break;
                            } else {
                                SetTextOnUIThread("Finger not matched, score: " + ret1);
                            }
                        }
                    }
                }
            }
          /*  int ret = mfs100.MatchISO(byt2, Verify_Template);
            if (ret < 0) {
                SetTextOnUIThread("Error: " + ret + "(" + mfs100.GetErrorMsg(ret) + ")");
            } else {
                if (ret >= 1400) {
                    SetTextOnUIThread("Finger matched with score: " + ret);
                } else {
                    SetTextOnUIThread("Finger not matched, score: " + ret);
                }
            }*/
        }

        WriteFile("Raw.raw", fingerData.RawData());
        WriteFile("Bitmap.bmp", fingerData.FingerImage());
        WriteFile("ISOTemplate.iso", fingerData.ISOTemplate());

 }

    private void WriteFile(String filename, byte[] bytes) {
        try {
            String path = Environment.getExternalStorageDirectory()
                    + "//FingerData";
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            path = path + "//" + filename;
            file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream stream = new FileOutputStream(path);
            stream.write(bytes);
            stream.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void UnInitScanner() {
        try {
            int ret = mfs100.UnInit();
            if (ret != 0) {
                SetTextOnUIThread(mfs100.GetErrorMsg(ret));
            } else {
                SetLogOnUIThread("Uninit Success");
                SetTextOnUIThread("Uninit Success");
                lastCapFingerData = null;
            }
        } catch (Exception e) {
            Log.e("UnInitScanner.EX", e.toString());
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
                SetLogOnUIThread(info);
            }
        } catch (Exception ex) {
            Toast.makeText(activity, "Init failed, unhandled exception",
                    Toast.LENGTH_LONG).show();
            SetTextOnUIThread("Init failed, unhandled exception");
        }
    }

    private void SetTextOnUIThread(final String str) {
                //lblMessage.setText(str);
        Log.e(TAG, str);
    }

    private void SetLogOnUIThread(final String str) {

     /*   txtEventLog.post(new Runnable() {
            public void run() {
                txtEventLog.append("\n" + str);
            }
        });*/
        Log.e(TAG, str);
    }

    private void showSuccessLog(String key) {
        SetTextOnUIThread("Init success");
        String info = "\nKey: " + key + "\nSerial: "
                + mfs100.GetDeviceInfo().SerialNo() + " Make: "
                + mfs100.GetDeviceInfo().Make() + " Model: "
                + mfs100.GetDeviceInfo().Model()
                + "\nCertificate: " + mfs100.GetCertification();
        SetLogOnUIThread(info);
    }

    public String getScanFingerprint() {
        return scanFingerprint;
    }

    public void setScanFingerprint(String scanFingerprint) {
        this.scanFingerprint = scanFingerprint;
    }

    public ArrayList<String> getList() {
        return list;
    }
}
