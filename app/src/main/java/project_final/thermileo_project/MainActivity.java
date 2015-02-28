package project_final.thermileo_project;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Handler;


public class MainActivity extends Activity {

    private static final String LOG_TAG = "TempRecordTest";
    private static final int DataSampleInterval = 300; // sets the data scan to 0.3 seconds
    //private Handler mHandler = new Handler();
    private PowerManager.WakeLock mWakeLock;
    private TextView TempReading;
    private TempReading mTempReading;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        mTempReading = new TempReading();
        PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"Temp");
        TempReading = (TextView) findViewById(R.id.textView3);
    }

    private Runnable Reading = new Runnable() {
        @Override
        public void run() {
            double amp = mTempReading.getAmplitude();
            TempReading.setText("Current"+String.valueOf(amp));
            ((TextView)findViewById(R.id.textView3)).setText("Current Temp: "+String.valueOf(amp)+(char)0x00B0);//0x00B0 is a hex code for degree celsius symbol

        }

    };

}