package com.pasistence.mantrafingerprint.Remote;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.pasistence.mantrafingerprint.Interface.UploadCallBack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class ProgressRequestBody extends RequestBody {

    public File file;
    public UploadCallBack listner ;
    private static final int DEFAULT_BUFFER_SIZE = 4096;


    public ProgressRequestBody(File file, UploadCallBack listner) {
        this.file = file;
        this.listner = listner;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return MediaType.parse("image/*"); //only image
    }

    @Override
    public long contentLength() throws IOException {
        return file.length();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        long fileLength = file.length();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        FileInputStream in = new FileInputStream(file);
        long uploaded = 0;

        try{
            int read;
            Handler handler = new Handler(Looper.getMainLooper());
            while ((read = in.read(buffer))!= -1)
            {
                handler.post(new ProgressUpdater(uploaded,fileLength));
                uploaded+=read;
                sink.write(buffer,0,read);
            }

        }finally {
           in.close();
        }
    }

    private class ProgressUpdater implements Runnable {
        private long uploaded;
        private long fileLength;
        public ProgressUpdater(long uploaded, long fileLength) {
            this.uploaded = uploaded;
            this.fileLength = fileLength;
        }


        @Override
        public void run() {
            listner.onProgressUpdate((int)(100*uploaded/fileLength));
        }
    }
}
