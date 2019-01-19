package com.wavelus.miasteczko.activities;

        import android.support.v7.app.AppCompatActivity;
        import android.widget.Button;
        import android.widget.EditText;
        import com.wavelus.miasteczko.R;
        import org.junit.Before;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.robolectric.Robolectric;
        import org.robolectric.RobolectricTestRunner;

        import static com.google.common.truth.Truth.assertThat;
        import static org.junit.Assert.assertEquals;
        import static org.mockito.Mockito.mock;
        import static org.mockito.Mockito.times;
        import static org.mockito.Mockito.verify;
        import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class, packageName = "org.khanacademy.android", sdk = 28)
public class AboutAppActivityTest {
    private AppCompatActivity activityy;

    @Before
    public void setUp(){
        activityy = Robolectric.setupActivity(AboutAppActivity.class);
    }

    @Test
    public void onCreateTest(){
        assertEquals(R.id.activity_about_app_root, shadowOf(activityy).getContentView().getId());
    }
}