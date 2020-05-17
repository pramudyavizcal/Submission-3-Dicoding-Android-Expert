package com.pramu.mymovies.Movies;


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
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.pramu.mymovies.R;

public class MyMoviesDetail extends AppCompatActivity {


    public static final String EXTRA_MOVIE = "test_extra_movie";
    TextView tViewjudul,tViewreleaseFilm,
     tViewskorPengunjung,tViewOverview;

    ImageView imagePoster;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_movies_detail);

        tViewjudul = findViewById(R.id.txt_judul);
        tViewreleaseFilm = findViewById(R.id.statusFilm);
        tViewskorPengunjung = findViewById(R.id.skorPengunjung);
        tViewOverview = findViewById(R.id.overview);
        imagePoster = findViewById(R.id.posterFilm);
        progressBar = findViewById(R.id.progressBar);
        progressBar.bringToFront();

        final MyMovies movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        tViewjudul.setText(movie.getTitle());
        tViewreleaseFilm.setText(movie.getRelease());
        tViewOverview.setText(movie.getOverview());
        tViewskorPengunjung.setText(movie.getVote());
        String url = "https://image.tmdb.org/t/p/w500" + movie.getPoster();
        Glide.with(MyMoviesDetail.this).load(url)
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
                }).into(imagePoster);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
