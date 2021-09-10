package com.example.flashlight;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ContentInfoCompat;

import android.app.StatusBarManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity {
    Button button,button2;
    Boolean on = true;
    LottieAnimationView lottieAnimationView,lottieAnimationView2;
    ConstraintLayout constraintLayout;
    MediaPlayer mediaPlayer,mediaPlayer1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        lottieAnimationView = findViewById(R.id.button);
        lottieAnimationView2 = findViewById(R.id.button2);
        constraintLayout = findViewById(R.id.kuch);
        mediaPlayer = MediaPlayer.create(this,R.raw.onoff);
        mediaPlayer1 = MediaPlayer.create(this,R.raw.crickets);


        lottieAnimationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                        try {
                            String cameraId =cameraManager.getCameraIdList()[0];
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                if(on==true){
                                    if(mediaPlayer1.isPlaying()){
                                        mediaPlayer1.pause();

                                    }
                                    constraintLayout.setBackgroundColor(getResources().getColor(R.color.blacktwo));
                                    lottieAnimationView.setMinAndMaxProgress(0.0f,0.5f);
                                    lottieAnimationView.setSpeed(3);
                                    lottieAnimationView.playAnimation();


                                    lottieAnimationView2.setMinAndMaxProgress(0.0f,0.6f);

                                    lottieAnimationView2.playAnimation();

                                    cameraManager.setTorchMode(cameraId,true);
                                    on=false;

                                }
                                else{
                                    mediaPlayer1.start();
                                    cameraManager.setTorchMode(cameraId,false);
                                    constraintLayout.setBackgroundColor(getResources().getColor(R.color.black));

                                    lottieAnimationView.setMinAndMaxProgress(0.5f,1.0f);
                                    lottieAnimationView.playAnimation();
                                    lottieAnimationView2.setMinAndMaxProgress(0.6f,1.0f);
                                    lottieAnimationView2.setSpeed(3);
                                    lottieAnimationView2.playAnimation();

                                    on=true;
                                }
                            }
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                }
            }
        });





    }
    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer1.pause();
    }
    
}