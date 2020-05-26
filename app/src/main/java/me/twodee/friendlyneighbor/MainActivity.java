package me.twodee.friendlyneighbor;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.ibm.mobilefirstplatform.clientsdk.android.push.api.MFPPush;
import com.ibm.mobilefirstplatform.clientsdk.android.push.api.MFPPushNotificationListener;

public class MainActivity extends AppCompatActivity
{
    //Button next, signInPage, dashboard, register, discover, payment, profile, postDetails, historyButton, postReqButton;
//    LoadingButton loadingButton;

    private static int SPLASH_SCREEN = 5000;

    Animation bottomAnim;
    ImageView image;
    TextView logo, slogan;

    private MFPPush push; // Push client
    private MFPPushNotificationListener notificationListener; // Notification listener to handle a push sent to the phone
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_splash);

        createNotificationChannel();

        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.splash_bottom_animation);

        //Hooks
        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.textView);
        slogan = findViewById(R.id.textView2);

        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        }, SPLASH_SCREEN);



//        discover = findViewById(R.id.discover);
//        discover.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent intent = new Intent(MainActivity.this, DiscoverActivity.class);
//                startActivity(intent);
//            }
//        });

//        next = findViewById(R.id.next);
//        next.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//
//
//                Intent intent = new Intent(MainActivity.this, RespondToRequestActivity.class);
//
//                startActivity(intent);
//            }
//        });

//        signInPage = findViewById(R.id.sign_in_page);
//        signInPage.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
//                startActivity(intent);
//            }
//        });

//        dashboard = findViewById(R.id.dashoard);
//        dashboard.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
//                startActivity(intent);
//            }
//        });

//        register = findViewById(R.id.register);
//        register.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
//                startActivity(intent);
//            }
//        });

//        payment = findViewById(R.id.payment);
//        payment.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent intent = new Intent(MainActivity.this, PaymentActivity.class);
//                startActivity(intent);
//            }
//        });

//        profile = findViewById(R.id.profile);
//        profile.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);
//                startActivity(intent);
//            }
//        });

//        postDetails = findViewById(R.id.postDetails);
//        postDetails.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent intent = new Intent(MainActivity.this, PostDetailsActivity.class);
//                startActivity(intent);
//            }
//        });

//        historyButton = findViewById(R.id.historyButton);
//        historyButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
//                startActivity(intent);
//            }
//        });

//        postReqButton = findViewById(R.id.post_req_button);
//        postReqButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, postRequirementActivity.class);
//                startActivity(intent);
//            }
//        });

//        loadingButton = (LoadingButton) findViewById(R.id.loadingButton);
//
//        final Boolean[] isLoading = {false};
//
//        loadingButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(!isLoading[0]) {
//                    loadingButton.showLoading();
//                    isLoading[0] = true;
//                }
//                else {
//                    loadingButton.hideLoading();
//                    isLoading[0] = false;
//                    loadingButton.setButtonText("Completed");
//                    loadingButton.setEnabled(false);
//                }
//            }
//        });

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.default_channel_name);
            String description = getString(R.string.default_channel_description);
            int importance = NotificationManager.IMPORTANCE_MAX;
            NotificationChannel channel = new NotificationChannel(getString(R.string.notification_channel_id), name, importance);
            channel.setDescription(description);
            channel.enableVibration(true);
            channel.enableLights(true);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
