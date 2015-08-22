package com.example.android.skeletonapp;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.skeletonapp.R.id;
import com.example.android.sqlite.dao.PersonDAO;
import com.example.android.sqlite.entity.Person;

public class MainActivity extends Activity {
    
    @Override
    protected void onDestroy() {
        sPersonDAO.close();
        super.onDestroy();
    }

    private static String TAG = "MainActivity"; 
    private static PersonDAO sPersonDAO;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sPersonDAO = PersonDAO.getPersonDAO(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button selectBtn = (Button)findViewById(R.id.selectButton);
        Button insertBtn = (Button)findViewById(R.id.insertButton);
        
        selectBtn.setOnClickListener(selectBtnListener);
        insertBtn.setOnClickListener(insertBtnListener);
        

//        if (savedInstanceState == null) {
//            getFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment()).commit();
//        }
                
    }
    
    // Button Listener
    private View.OnClickListener selectBtnListener = new View.OnClickListener() {

        public void onClick(View v) {
            Log.d(TAG,"select");
            sPersonDAO.showPersons();
        }
    };

    private View.OnClickListener insertBtnListener = new View.OnClickListener() {

        public void onClick(View v) {
            Log.d(TAG,"insert");
            
            // get EditText
            EditText firstName = (EditText)findViewById(id.firstNameEditText);
            EditText lastName = (EditText)findViewById(id.lastNameEditText);
            EditText age = (EditText)findViewById(id.ageEditText);
            
            // create person
            Person person = new Person();
            person.setFirstName(firstName.getText().toString());
            person.setLastName(lastName.getText().toString());
            Log.d(TAG, age.getText().toString());
            person.setAge(Integer.valueOf(age.getText().toString()));
            
            // insert
            sPersonDAO.insert(person);
            
            // set empty
            firstName.setText("");
            lastName.setText("");
            age.setText("");
            
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container,
                    false);
            return rootView;
        }
    }

}
