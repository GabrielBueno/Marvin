package com.bueno_gabriel.marvin.marvin;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.bueno_gabriel.marvin.transmitter.BluetoothTransmitter;
import com.bueno_gabriel.marvin.transmitter.TransmitterCallback;

import java.util.Stack;

public class Marvin {
    public static final int FORWARD     = 0x11;
    public static final int BACKWARDS   = 0x99;
    public static final int LEFT        = 0x10;
    public static final int RIGHT       = 0x01;
    public static final int CURVE_LEFT  = 0x91;
    public static final int CURVE_RIGHT = 0x19;
    public static final int STOP        = 0x00;

    private static final String LOG_TAG = "Marvin";

    private final BluetoothTransmitter transmitter;
    private final Context context;

    private Stack<Integer> byteStack;

    public Marvin(Context context) {
        this.context = context;
        this.transmitter = new BluetoothTransmitter();
        this.byteStack   = new Stack<>();

        this.setupTransmitter();
    }

    public void enqueue(int byteToEnqueue) {
        Log.d(LOG_TAG, "Empilhando e escrevendo: " + byteToEnqueue);

        byteStack.push(byteToEnqueue);
        transmitter.write(byteToEnqueue);
    }

    public void dequeue(int byteToDequeue) {
        byteStack.remove((Integer)byteToDequeue);

        int byteToWrite = byteStack.isEmpty() ? Marvin.STOP : byteStack.peek();

        Log.d(LOG_TAG, "Escrevendo: " + byteToWrite);

        transmitter.write(byteToWrite);
    }

    /**
     *
     */
    private void setupTransmitter() {
        this.transmitter.setup(new TransmitterCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(String message) {
                ((Activity) context).runOnUiThread(() -> Toast.makeText(context, message, Toast.LENGTH_SHORT).show());
            }
        });

        this.transmitter.connectToDevice(new TransmitterCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(String message) {
                ((Activity) context).runOnUiThread(() -> Toast.makeText(context, message, Toast.LENGTH_SHORT).show());
            }
        });
    }
}
