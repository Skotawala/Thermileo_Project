package project_final.thermileo_project;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.widget.TextView;

import java.nio.channels.Channels;
import java.util.logging.Handler;

/**
 * Created by Siddharth on 2/28/2015.
 */
public class TempReading {
    private MediaRecorder mRecorder = null;
    private Thread mThread = null;

   public void start() throws Exception{
       if (mRecorder == null){
           mRecorder = new MediaRecorder();
           mRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_RECOGNITION);
           mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
           mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
           mRecorder.setAudioSamplingRate(44100);
           mRecorder.setAudioChannels(1);
           mRecorder.prepare();
           mRecorder.start();
       }
       // I have put the Thread method, I think it is used to let the system know that we need continuous data flow
       // I am not using any button input. Let's try if we can start taking the data as soon as the app opens without any input. But I think there are some things missing here.
            mThread = new Thread(new Runnable() {
                @Override
                public void run() {

                    mThread.start();


                }

            });
   }

   public void stop(){
       if (mRecorder != null){
           mRecorder.stop();
           mRecorder.release();
           mRecorder = null;
       }
   }

    public double getAmplitude(){
        if (mRecorder != null){

           return mRecorder.getMaxAmplitude();

        }
        return 0;
    }


    //some calculations for converting the data to db values...
    //this is  not what we want, but if this gets working then we can manipulate some of the things to convert the values to degree celcius

    public double Calculation(){
        double amp = getAmplitude();
        amp = 20*Math.log(Math.abs(amp)/32767);
        return amp;
    }
}
