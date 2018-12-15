//package com.wavelus.miasteczko.activities;
//
//import android.view.ViewGroup;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.firebase.database.DatabaseReference;
//import com.wavelus.miasteczko.adapters.EventsAdapter;
//import com.wavelus.miasteczko.models.MyEvent;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//
//public class EventsAdapterTest {
//    private static final Object EventsAdapter = ;
//    private EventsAdapter mEventsAdapter;
//
//    @Mock
//    private DatabaseReference eventUserDatabaseReference;
//    @Mock
//    private FirebaseRecyclerOptions<MyEvent> options;
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
//        mEventsAdapter = new EventsAdapter(options);
//    }
//
//    @Test
//    public void onCreateVievHolderTest(){
//        int vievType = mock(Integer.class);
//        ViewGroup parent = mock(ViewGroup.class);
//        mEventsAdapter.onCreateViewHolder(parent, vievType);
//
//    }
//}
