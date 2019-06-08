package com.bueno_gabriel.marvin.transmitter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class BluetoothTransmitter {
    private static final String LOG_TAG      = "BluetoothTransmitter";

    private final UUID connectionUUID;
    private final BluetoothTransmitter context;

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice  device;
    private BluetoothSocket  socket;
    private OutputStream     outStream;

    public BluetoothTransmitter() {
        this.connectionUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        this.context = this;
    }

    public void setup(final TransmitterCallback callback) {
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (this.bluetoothAdapter == null || !this.bluetoothAdapter.isEnabled())
            callback.onError("Não foi possível conectar ao bluetooth");

        callback.onSuccess();
    }

    public void connectToDevice(final TransmitterCallback callback) {
        new Thread() {
            @Override
            public void run() {
                context.searchDevice();

                if (context.device == null) {
                    callback.onError("Não foi possível encontrar o dispositivo");
                    return;
                }

                try {
                    socket = context.device.createInsecureRfcommSocketToServiceRecord(context.connectionUUID);
                    socket.connect();

                    outStream = socket.getOutputStream();

                    callback.onSuccess();
                } catch (IOException e) {
                    Log.e(LOG_TAG, e.getMessage());
                    callback.onError(e.getMessage());

                    context.close();
                }
            }
        }.start();
    }

    public void write(int b) {
        try {
            if (this.outStream != null)
                this.outStream.write(b);
        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    public void close() {
        try {
            if (this.socket != null)
                this.socket.close();
        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    private void searchDevice() {
        Set<BluetoothDevice> bonded = this.bluetoothAdapter.getBondedDevices();

        for (BluetoothDevice device : bonded) {
            //Log.d(LOG_TAG, device.getName());

            if (device.getName().equals("Marvin")) {
                this.device = device;
                break;
            }
        }
    }
}
