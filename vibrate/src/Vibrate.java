package org.godotengine.godot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.godot.game.R;
import javax.microedition.khronos.opengles.GL10;
import android.os.Vibrator;
import android.os.VibrationEffect;
import android.os.Build;
import java.util.Arrays;

public class Vibrate extends Godot.SingletonBase {
    protected Activity appActivity;
    protected Context appContext;
    private int instanceId = 0;
    Vibrator v;
    boolean can_vibrate = false;

    public void vibrate(int length) {
        if (this.can_vibrate) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(length, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(length);
            }
	
        }
    }

    public void pattern(int[] pattern){
        // vibrate pattern should be an array of long not int
        // Godot passes an array of int. 
        if (this.can_vibrate) {
            long[] vibrate_pattern = Arrays.stream(pattern).asLongStream().toArray();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createWaveform(vibrate_pattern, -1));
            } else {
                // deprecated in API 26
                v.vibrate(vibrate_pattern, -1);
            }
        }
    }

    public void getInstanceId(int pInstanceId) {
        instanceId = pInstanceId;
    }

    static public Godot.SingletonBase initialize( Activity p_activity) {
        return new Vibrate(p_activity);
    }

    public Vibrate( Activity p_activity){
        // Register class name and functions to bind.
        registerClass("Vibrate", new String[] {
            "vibrate",
            "pattern",
            "getInstanceId"
        });

        appActivity = p_activity;
        appContext = appActivity.getApplicationContext();
        v = (Vibrator) appContext.getSystemService(Context.VIBRATOR_SERVICE);
        this.can_vibrate = v.hasVibrator();
    }
}
