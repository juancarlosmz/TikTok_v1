package info.androidhive.viewpager2.fragments;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import info.androidhive.viewpager2.R;
public class MoviesFragment extends Fragment {
    private VideoView myvideo;
    private ImageView myimage;
    private String URLvideos;
    private String URLimages;
    private TextView textViewtest;
    private static int oTime =0, sTime =0, eTime =0, fTime = 5000, bTime = 5000;
    private Handler hdlr = new Handler();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hide action bar in this fragment
        // ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        // comprobar si hay el bundle
        if (getArguments() != null) {
            URLvideos = getArguments().getString(FragmentViewPagerActivity.URLVIDEOS_KEY,"No Url");
            URLimages = getArguments().getString(FragmentViewPagerActivity.URLIMAGES_KEY,"No Url");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // CREANDO LAS IMAGENES
        myimage = (ImageView)view.findViewById(R.id.myimg);
        //Glide.with(this).load(URLimages).into(myimage);
        Glide.with(this).load(URLimages).diskCacheStrategy(DiskCacheStrategy.ALL).into(myimage);
        myimage.setVisibility(View.VISIBLE);

        // CREANDO LOS VIDEOS
        Uri uri=Uri.parse(URLvideos);
        myvideo = (VideoView)view.findViewById(R.id.videoView2);
        myvideo.setVideoURI(uri);

        myvideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //myimage.setVisibility(View.VISIBLE);
                mp.setLooping(true);
                mp.start();



                textViewtest = (TextView)view.findViewById(R.id.textViewtest);
                eTime = mp.getDuration();
                sTime = mp.getCurrentPosition();
                if(oTime == 0){
                    System.out.println(eTime);
                    oTime =1;
                }
                hdlr.postDelayed(UpdateVideoTime, 100);






/*
                if(mp.isPlaying()){
                    int currentPosition = mp.getCurrentPosition();
                    if (currentPosition == 0) {
                        myimage.setVisibility(View.GONE);
                        System.out.println("*******************************************************************tester " );
                    }
                }*/





                /*
                if(mp.isPlaying()){
                    mp.seekTo(0);
                    int currentPosition = mp.getCurrentPosition();
                    System.out.println("currentPosition es: " + currentPosition);
                    if (currentPosition == 0) {
                        myimage.setVisibility(View.GONE);
                    }
                }
                */

                if(FragmentViewPagerActivity.MY_TEXTO == "pause"){
                    myimage.setVisibility(View.VISIBLE);
                    mp.pause();
                }
            }
        });


    }

    private Runnable UpdateVideoTime = new Runnable() {
        @Override
        public void run() {
            sTime = myvideo.getCurrentPosition();
            textViewtest.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes(sTime),
                    TimeUnit.MILLISECONDS.toSeconds(sTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))) );

            //System.out.println("*************timer"+TimeUnit.MILLISECONDS.toMinutes(sTime) +" : " + (TimeUnit.MILLISECONDS.toSeconds(sTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime)) ));
            System.out.println("*************timer1   "+ TimeUnit.MILLISECONDS.toMillis(sTime) );
            if( TimeUnit.MILLISECONDS.toMillis(sTime) > 200){
                System.out.println("*************hola   ");
                myimage.setVisibility(View.GONE);
            }
            if( TimeUnit.MILLISECONDS.toMillis(sTime) == 0){
                myimage.setVisibility(View.VISIBLE);
            }
            hdlr.postDelayed(this, 100);
        }
    };


}
