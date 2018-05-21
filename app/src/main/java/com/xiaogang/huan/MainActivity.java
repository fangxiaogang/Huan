package com.xiaogang.huan;

import android.app.ActionBar;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.VideoView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    private TypeTextView mTypeTextView;
    private ScrollView scrollView;
    private Banner mbanner;
    private VideoView main_videoview;
    private List<String> picurl = new ArrayList<>();
    private   String TEST_DATA = " //自从看到你之后就爱上了单纯的你/\n" +
            "        long love = 0;\n" +
            "        //时光在增加，爱你的心也在升温/\n" +
            "        for (long time = 1 ; time<love ; ++love, ++time){\n" +
            "            //如果你不爱我，我将会等到时间的尽头，独自心碎/\n" +
            "            if (love < 0) break;\n" +
            "        }\n" +
            "        //抽象出我的爱注入你的心中/\n" +
            "        abstract class MyLove implements YourHeart {\n" +
            "            //你若愿意/\n" +
            "            String MyLove(String I_ do) {\n" +
            "                //我便爱你直至永久/\n" +
            "                return \"Always and Forever\";\n" +
            "            }\n" +
            "        }";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        picurl.add("http://onfkdy4l9.bkt.clouddn.com/IMG_20170820_165757%281%29.jpg");
        picurl.add("http://onfkdy4l9.bkt.clouddn.com/IMG_20171020_155709%281%29.jpg");
        picurl.add("http://onfkdy4l9.bkt.clouddn.com/IMG_20170227_170446.jpg");
        picurl.add("http://onfkdy4l9.bkt.clouddn.com/-69ed7081bf064201%281%29.jpg");
        picurl.add("http://onfkdy4l9.bkt.clouddn.com/IMG_20161231_170658_01.jpg");

        main_videoview = (VideoView) findViewById(R.id.main_videoview);
        main_videoview.setVideoURI(Uri.parse("android.resource://com.xiaogang.huan/"+R.raw.gaobai));
        main_videoview.start();
        main_videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });


        mTypeTextView = (TypeTextView) findViewById(R.id.typeTxtId);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        mTypeTextView.setOnTypeViewListener(new TypeTextView.OnTypeViewListener() {
            @Override
            public void onTypeStart() {
                Log.d("", "onTypeStart");
            }
            //打字机效果的TextView

            @Override
            public void onTypeOver() {
                Log.d("", "onTypeOver");
            }
        });
        mTypeTextView.start(scrollView,TEST_DATA);

        mbanner = (Banner) findViewById(R.id.mBanner);
        mbanner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        LinearLayout.LayoutParams bannerparams = new LinearLayout.LayoutParams(Gallery.LayoutParams.MATCH_PARENT,
                 Gallery.LayoutParams.WRAP_CONTENT);
        bannerparams.height = ScreenUtil.getWith(this);
        mbanner.setLayoutParams(bannerparams);
        if (picurl.size() > 0) {
            mbanner.setImages(picurl)
                    .setImageLoader(new GlideImageLoader())
                    .setDelayTime(3000)
                    .start();
        }



    }


    @Override
    protected void onStop() {
        super.onStop();
        mTypeTextView.stop();
        main_videoview.stopPlayback();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        main_videoview.start();
        mTypeTextView.start(scrollView,TEST_DATA);
    }

}
