package info.androidhive.viewpager2.fragments;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import info.androidhive.viewpager2.R;
import info.androidhive.viewpager2.databinding.ActivityFragmentViewPagerBinding;
import info.androidhive.viewpager2.transformers.SimpleTransformation;
public class FragmentViewPagerActivity extends AppCompatActivity {
    ActivityFragmentViewPagerBinding binding;
    // tab titles
    private String[] titles = new String[]{"Movies", "Events", "Tickets","test","test1","test2","Movies", "Events", "Tickets","test","test1"};
    public static final String URLVIDEOS_KEY = "URLvideos";
    public static final String URLIMAGES_KEY = "URLimages";
    public static String MY_TEXTO = "";
    public static float MY_POSITION;
    // implementando las imagenes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFragmentViewPagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }
    private void init() {
        // removing toolbar elevation
        getSupportActionBar().setElevation(0);
        binding.viewPager.setAdapter(new ViewPagerFragmentAdapter(this));
        binding.viewPager.setPageTransformer(new SimpleTransformation());
        // comentar para ocultar los attaching tab mediator
        // new TabLayoutMediator(binding.tabLayout, binding.viewPager,(tab, position) -> tab.setText(titles[position])).attach();
    }
    private class ViewPagerFragmentAdapter extends FragmentStateAdapter {public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {super(fragmentActivity);}
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            // creando la caja cotenedora de variables para movies, events
            MoviesFragment moviesFragment = new MoviesFragment();
            Bundle bundle = new Bundle();
            int j = 0;
            for(int i= 0; i < titles.length; i++){
                if(position == i){
                    j = i + 1;
                    bundle.putString( URLIMAGES_KEY, "https://apolomultimedia-server4.info/images/"+j+".jpg" );
                    bundle.putString( URLVIDEOS_KEY, "https://apolomultimedia-server4.info/videos/"+j+".mp4" );
                    moviesFragment.setArguments(bundle);
                }
            }
            return moviesFragment;
        }
        @Override
        public int getItemCount() {
            return titles.length;
        }
    }
}
