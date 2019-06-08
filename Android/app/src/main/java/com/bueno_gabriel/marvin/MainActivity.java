package com.bueno_gabriel.marvin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bueno_gabriel.marvin.marvin.Marvin;


@SuppressLint("ClickableViewAccessibility")
public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";
    private static final int BLUETOOTH_ENABLE_REQUEST = 1;

    private ImageButton btnForward;
    private ImageButton btnBackwards;
    private ImageButton btnLeft;
    private ImageButton btnRight;
    private ImageButton btnCurveLeft;
    private ImageButton btnCurveRight;

    private Marvin marvin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.marvin = new Marvin(this);

        this.btnCurveRight = findViewById(R.id.btn_curve_right);
        this.btnCurveLeft  = findViewById(R.id.btn_curve_left);
        this.btnBackwards  = findViewById(R.id.btn_backwards);
        this.btnForward    = findViewById(R.id.btn_forward);
        this.btnRight      = findViewById(R.id.btn_right);
        this.btnLeft       = findViewById(R.id.btn_left);

        btnCurveRight.setOnTouchListener(this.btnCurveRightListener());
        btnCurveLeft.setOnTouchListener(this.btnCurveLeftListener());
        btnBackwards.setOnTouchListener(this.btnBackwardsListener());
        btnForward.setOnTouchListener(this.btnForwardListener());
        btnRight.setOnTouchListener(this.btnRightListener());
        btnLeft.setOnTouchListener(this.btnLeftListener());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BLUETOOTH_ENABLE_REQUEST && resultCode == RESULT_CANCELED)
            Toast.makeText(this, "O dispositivo não suporta bluetooth!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Listener do botão de ir para frente
     * @return OnTouchListener com os comportamentos definidos
     */
    private View.OnTouchListener btnForwardListener() {
        return (view, motionEvent) -> {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                    marvin.enqueue(Marvin.FORWARD);

                else if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                    marvin.dequeue(Marvin.FORWARD);

                return true;
        };
    }

    /**
     * Listener do botão de ir para frente
     * @return OnTouchListener com os comportamentos definidos
     */
    private View.OnTouchListener btnBackwardsListener() {
        return (view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                marvin.enqueue(Marvin.BACKWARDS);

            else if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                marvin.dequeue(Marvin.BACKWARDS);

            return true;
        };
    }

    /**
     * Listener do botão de ir para frente
     * @return OnTouchListener com os comportamentos definidos
     */
    private View.OnTouchListener btnLeftListener() {
        return (view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                marvin.enqueue(Marvin.LEFT);

            else if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                marvin.dequeue(Marvin.LEFT);

            return true;
        };
    }

    /**
     * Listener do botão de ir para frente
     * @return OnTouchListener com os comportamentos definidos
     */
    private View.OnTouchListener btnRightListener() {
        return (view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                marvin.enqueue(Marvin.RIGHT);

            else if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                marvin.dequeue(Marvin.RIGHT);

            return true;
        };
    }

    /**
     * Listener do botão de ir para frente
     * @return OnTouchListener com os comportamentos definidos
     */
    private View.OnTouchListener btnCurveLeftListener() {
        return (view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                marvin.enqueue(Marvin.CURVE_RIGHT);

            else if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                marvin.dequeue(Marvin.CURVE_RIGHT);

            return true;
        };
    }

    /**
     * Listener do botão de ir para frente
     * @return OnTouchListener com os comportamentos definidos
     */
    private View.OnTouchListener btnCurveRightListener() {
        return (view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                marvin.enqueue(Marvin.CURVE_LEFT);

            else if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                marvin.dequeue(Marvin.CURVE_LEFT);

            return true;
        };
    }
}
