package in.co.ikai.retrofitrecyclerviewandruntimepermissions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageFullScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_full_screen);

        ImageView imageView = findViewById(R.id.img_single_image);
        String url = getIntent().getExtras().getString("url");
        Glide.with(this).load(url).into(imageView);
    }
}
