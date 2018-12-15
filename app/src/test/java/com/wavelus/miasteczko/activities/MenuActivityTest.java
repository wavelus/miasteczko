//package com.wavelus.miasteczko.activities;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.Bundle;
//import android.support.transition.Visibility;
//import android.view.ContextMenu;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import com.wavelus.miasteczko.R;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import org.junit.Assert.*;
//import org.junit.runner.RunWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.robolectric.RobolectricTestRunner;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//
//@RunWith(RobolectricTestRunner.class)
//public class MenuActivityTest {
//
//
//
//    private MenuActivity mMenuActivity;
//    private MenuInflater menuInflater;
//    //private Menu menu;
//
//
//    @Captor
//    private ArgumentCaptor mLoadNotesCallbackCaptor;
//
//
//    @Before
//    public void setUp() {
//
//
//        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
//        // inject the mocks in the test the initMocks method needs to be called.
//        MockitoAnnotations.initMocks(this);
//
//        // Get a reference to the class under test
//        mMenuActivity = new MenuActivity();
//
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    @Test
//    public void onCreate() {
//        //mMenuActivity.onCreate((Bundle) mLoadNotesCallbackCaptor.capture());
//
//    }
//
//    @Test
//    public void onCreateOptionsMenuTest() {
//        MenuActivity mMenuActivity = new MenuActivity();
//        mMenuActivity = mock(MenuActivity.class);
//        Menu menu = mock(Menu.class);
//        mMenuActivity.onCreateOptionsMenu(menu);
//        verify(menuInflater).inflate(R.menu.main_menu, menu);
//    }
//
//    @Test
//    public void onOptionsItemSelected() {
////        mMenuActivity = mock(MenuActivity.class);
////        mMenuActivity.onOptionsItemSelected(item);
//    }
//}