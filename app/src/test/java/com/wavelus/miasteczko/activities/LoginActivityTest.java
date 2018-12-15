package com.wavelus.miasteczko.activities;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Button;
import com.google.firebase.FirebaseApp;
import com.wavelus.miasteczko.BuildConfig;
import static org.hamcrest.CoreMatchers.*;

import com.wavelus.miasteczko.R;
import com.wavelus.miasteczko.activities.login.LoginActivity;
import com.wavelus.miasteczko.activities.login.LoginEmailActivity;
import junit.framework.Assert;
//import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
//import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
//import org.robolectric.RuntimeEnvironment;
//import org.robolectric.annotation.Config;
//import org.robolectric.shadows.ShadowApplication;
//
//import static org.hamcrest.CoreMatchers.nullValue;
//import static org.hamcrest.core.Is.is;
//import static org.hamcrest.core.IsNull.notNullValue;
//import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;
import static org.robolectric.shadows.ShadowInstrumentation.getInstrumentation;

@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class, packageName = "org.khanacademy.android", sdk = 28)
public class LoginActivityTest {
    private AppCompatActivity activity;
    private Button button;

    @Before
    public void setUp(){
        activity = Robolectric.setupActivity(LoginActivity.class);
        button = activity.findViewById(R.id.loginEmailBtn);
    }

    @Test
    public void buttonTest(){
        Instrumentation.ActivityMonitor am = getInstrumentation().addMonitor(LoginEmailActivity.class.getName(), null, true);
        button.performClick();
        am.waitForActivityWithTimeout(1);
        assertEquals(1, am.getHits());
    }

    @Test
    public void onCreateTest(){
//        assertEquals(R.layout.activity_login,);
        assertEquals(R.id.activity_login_root, shadowOf(activity).getContentView().getId());
    }


}
