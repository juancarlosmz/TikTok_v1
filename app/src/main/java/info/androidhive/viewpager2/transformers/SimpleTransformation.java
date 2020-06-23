package info.androidhive.viewpager2.transformers;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;
import androidx.viewpager2.widget.ViewPager2;
import info.androidhive.viewpager2.R;
import info.androidhive.viewpager2.fragments.FragmentViewPagerActivity;
public class SimpleTransformation implements ViewPager2.PageTransformer {
    private VideoView myvideo;
    private ImageView myimage;
    @Override
    public void transformPage(View page, float position) {
        FragmentViewPagerActivity.MY_POSITION = position;
        myvideo = (VideoView)page.findViewById(R.id.videoView2);
        myimage = (ImageView)page.findViewById(R.id.myimg);

        if (position == 0.0 ){
            FragmentViewPagerActivity.MY_TEXTO = "play";
            myimage.setVisibility(View.VISIBLE);
            myvideo.start();
            if(myvideo.isPlaying()){
                myimage.setVisibility(View.GONE);
            }


            /*
            if(myvideo.isPlaying()){
                myvideo.seekTo(0);
                int currentPosition = myvideo.getCurrentPosition();
                System.out.println("currentPosition 2 es: " + currentPosition);
                if (currentPosition == 0) {
                    myimage.setVisibility(View.GONE);
                }
            }*/

        }else{
            FragmentViewPagerActivity.MY_TEXTO = "pause";
            //myimage.setVisibility(View.VISIBLE);
        }
    }
}