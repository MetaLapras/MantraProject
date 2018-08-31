package com.pasistence.mantrafingerprint.FingerPrintMatching;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mantra.mfs100.FingerData;
import com.mantra.mfs100.MFS100;
import com.mantra.mfs100.MFS100Event;
import com.pasistence.mantrafingerprint.Common.PreferenceUtils;
import com.pasistence.mantrafingerprint.Main.MFS100TestActivity;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.Attendance;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.database.Database;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static java.lang.System.exit;

public class MFS100Mantra implements MFS100Event {

    Activity activity;

    ImageView imgFinger;
    CircleImageView imgPicture;
    String scanFingerprint;
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<WorkerModel> workerList = new ArrayList<WorkerModel>();
    Database database;
    String RadioCheck;

    private enum ScannerAction {
        Capture, Verify
    }

    byte[] Enroll_Template;
    byte[] Verify_Template;
    private FingerData lastCapFingerData = null;
    ScannerAction scannerAction = ScannerAction.Capture;
    private static final String TAG = "MFS-->";

    public boolean isVerfiy = false;

    int timeout = 10000;
    MFS100 mfs100 = null;

    private boolean isCaptureRunning = false;
    List<WorkerModel> fingerprint ;

    WorkerModel workerModel ;
    public boolean checkDevice;
    TextView lblMessage,txtName,txtWorkerId;


    public MFS100Mantra(Activity activity,TextView lblMessage) {
        this.activity = activity;
        this.lblMessage = lblMessage;
    }

    public MFS100Mantra(Activity activity, ImageView imgFinger, CircleImageView imgPicture,TextView txtName,TextView txtWorkerId, TextView lblMessage) {
        this.activity = activity;
        this.imgFinger = imgFinger;
        this.lblMessage = lblMessage;
        this.txtWorkerId = txtWorkerId;
        this.imgPicture = imgPicture;
        this.txtName = txtName;
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

            //get all data from database of workers
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
                        SetTextOnUIThread1(workerModel);
                       // setWorkerModel(workerModel);
                       // setVerfiy(true);
                        break;
                    } else {
                        //if second finger print match
                        int ret1 = mfs100.MatchISO(byt2, Verify_Template);
                        if (ret1 < 0) {
                            SetTextOnUIThread("Error2: " + ret1 + "(" + mfs100.GetErrorMsg(ret1) + ")");
                        } else {
                            if (ret1 >= 1400) {
                                SetTextOnUIThread("Finger2 matched with score: " + ret1);
                                SetTextOnUIThread1(workerModel);
                                //setWorkerModel(workerModel);
                                //setVerfiy(true);
                                break;
                            } else {
                                SetTextOnUIThread("Finger not matched, score: " + ret1);
                                SetTextOnUINotMatch();
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

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lblMessage.setText(str);
               // Log.e(TAG, str);
            }
        });
    }

    public void SetTextOnUIThread1(final WorkerModel workerModel) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Log.e(TAG, str);

                txtName.setText(workerModel.getName());
                txtWorkerId.setText(workerModel.getAdharcard_id());

                Glide.with(activity)
                        .load(workerModel.getImageUrl().toString())
                        .into(imgPicture);

               workerList.add(workerModel);
               String id,worker_id,worker_assignment_id,project_id,check_in_date,check_in_time,overtime,fulltime,halfday,
                        check_out_time,wages,created_at,updated_at;
               worker_id = workerModel.getWorkerId().toString();
               worker_assignment_id = "1";
               project_id = PreferenceUtils.getProject_id(activity);
               check_in_date = getCurrentDate();
               wages = workerModel.getSalary();
               check_in_time = getCurrentTime();

               Attendance attendance = new Attendance();

                attendance.setWorkerId(worker_id);
                attendance.setWorkerAssignmentId(worker_assignment_id);
                attendance.setProjectId(project_id);
                attendance.setCheckInDate(check_in_date);
                attendance.setWages(wages);
                //attendance.setCheckInTime(check_in_time);

                database = new Database(activity);

                if(getRadioCheck().equals("checkIn")){
                   check_in_time = getCurrentTime();
                   attendance.setCheckInTime(check_in_time);
                   database.addToTempAttendance(attendance);
               }else if(getRadioCheck().equals("checkOut")) {
                   check_out_time = getCurrentTime();
                   attendance.setCheckOutTime(check_out_time);
                   database.updateToTempAttendance(attendance);
               }else if(getRadioCheck().equals("halfDay")){
                   check_out_time = getCurrentTime();
                   attendance.setCheckOutTime(check_out_time);
                   database.updateToTempAttendance(attendance);
               }else{
                    check_in_time = getCurrentTime();
                    attendance.setCheckInTime(check_in_time);
                    database.addToTempAttendance(attendance);
                }

            }
        });
    }


    private void SetTextOnUINotMatch() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Log.e(TAG, str);

                txtName.setText("No Match Found");
                txtWorkerId.setText("Worker not present");

                Glide.with(activity)
                        .load(getImage("images"))
                        .into(imgPicture);

                Glide.with(activity)
                        .load(getImage("ic_fingerprint_1"))
                        .into(imgFinger);

            }
        });
    }

    public int getImage(String imageName) {

        int drawableResourceId = activity.getResources().getIdentifier(imageName, "drawable", activity.getPackageName());

        return drawableResourceId;
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

    public WorkerModel getWorkerModel() {
        return workerModel;
    }

    public ArrayList<WorkerModel> getAttendanceList() {
        return workerList;
    }

    public String getCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate = mdformat.format(calendar.getTime());
        return strDate;
    }

    public String getCurrentDate(){
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        String formattedDate = df.format(c);
        return formattedDate;
    }

    public String getRadioCheck() {
        return RadioCheck;
    }

    public void setRadioCheck(String radioCheck) {
        RadioCheck = radioCheck;
    }
}
