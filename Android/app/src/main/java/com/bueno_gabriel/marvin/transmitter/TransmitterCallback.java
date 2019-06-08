package com.bueno_gabriel.marvin.transmitter;

public interface TransmitterCallback {
    void onSuccess();
    void onError(String message);
}
