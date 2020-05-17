package com.pramu.mymovies.TV;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.pramu.mymovies.R;

public class MyTVDetail extends AppCompatActivity {

    public static final String EXTRA_TV = "test_extra_tv";

    TextView tViewjudulTV,tViewreleaseTV,
            tViewskorPengunjungTV,
            tViewOverviewTV;

    ImageView backdrop_path;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tvdetail);

        tViewjudulTV = findViewById(R.id.txt_judul);
        tViewskorPengunjungTV = findViewById(R.id.skorPengunjung);
        tViewreleaseTV = findViewById(R.id.statusTV);
        tViewOverviewTV = findViewById(R.id.overview);
        backdrop_path = findViewById(R.id.posterTV);
        progressBar = findViewById(R.id.progressBar);
        progressBar.bringToFront();

        final MyTV tv = getIntent().getParcelableExtra(EXTRA_TV);
        tViewjudulTV.setText(tv.getJudulTV());
        tViewOverviewTV.setText(tv.getOverviewTV());
        tViewreleaseTV.setText(tv.getrelease());
        tViewskorPengunjungTV.setText(tv.getSkorPengunjungTV());

        String url = "https://image.tmdb.org/t/p/w500" + tv.getposterTV();
        Glide.with(MyTVDetail.this)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(backdrop_path);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
