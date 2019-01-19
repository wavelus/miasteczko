package com.wavelus.miasteczko.activities.login;


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
public class LoginEmailActivityTest {
    private AppCompatActivity mAppCompatActivity;
    private LoginEmailActivity loginEmailActivity;
//    private LoginEmailActivity mockLoginEmailActivity;
    private Button loginButton;
    private EditText email;
    private EditText password;
    private Button button;


    @Before
    public void setUp(){
        loginEmailActivity = Robolectric.setupActivity(LoginEmailActivity.class);
        mAppCompatActivity = Robolectric.setupActivity(LoginEmailActivity.class);
        email = mAppCompatActivity.findViewById(R.id.loginEmailEt);
        password = mAppCompatActivity.findViewById(R.id.loginEmailPasswordEt);
        loginButton = mAppCompatActivity.findViewById(R.id.loginEmailButtonId);
//        mockLoginEmailActivity = mock(LoginEmailActivity.class);

    }

    @Test
    public void onCreateTest(){
        assertEquals(R.id.activityLoginEmailLayout, shadowOf(mAppCompatActivity).getContentView().getId());
//        LoginEmailActivity activity = Robolectric.setupActivity(LoginEmailActivity.class);
//        loginButton.performClick();
//        verify(activity.loginUser(email.getText(),password.getText()));
    }


//    @Test
//    public void callMethodLoginUser() {
//        String emailText = "name@o8.pl";
//        String passwordText = "advancedPassword";
//
//        loginButton.performClick();
////        verify(mockLoginEmailActivity, times(1)).loginUser(emailText,passwordText);
//    }

    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        String emailText = "name@o8.pl";
        String passwordText = "advancedPassword";
        assertThat(loginEmailActivity.isEmailAndPasswordValid(emailText,passwordText)).isTrue();
    }



}
