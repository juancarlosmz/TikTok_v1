package info.androidhive.viewpager2.fragments;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import info.androidhive.viewpager2.R;
public class EventsFragment extends Fragment {
    private VideoView myvideo;
    private String URLvideos;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            URLvideos = getArguments().getString(FragmentViewPagerActivity.URLVIDEOS_KEY,"No Url");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Uri uri=Uri.parse(URLvideos);
        myvideo = (VideoView)view.findViewById(R.id.videoView2);
        myvideo.setVideoURI(uri);
        myvideo.requestFocus();
        myvideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mp.start();
            }
        });

    }
}
