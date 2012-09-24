package coinFlipV1.gitmad.app;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
    private static final String COUNT_VAR     = "count";
    private static final String STARTDATE_VAR = "startDate";

    private Date startDate;
    private int counter = 0;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        View flipCoinButton = findViewById(R.id.flip_coin_button);
        flipCoinButton.setOnClickListener(this);
       
        View superFlipCoinButton = findViewById(R.id.super_flip_coin_button);
        superFlipCoinButton.setOnClickListener(this);
        
        if (savedInstanceState != null && savedInstanceState.containsKey(COUNT_VAR)) {
            this.counter   =       savedInstanceState.getInt(MainActivity.COUNT_VAR);
            this.startDate = (Date)savedInstanceState.getSerializable(MainActivity.STARTDATE_VAR);
        } else {
            this.startDate = new Date();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Demo", "onStart called.");
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Demo", "onPause called.");
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Demo", "onResume called.");
        //set the text with the new counter value
        ((TextView)this.findViewById(R.id.text_counter)).setText(String.format("%d flips since %s", this.counter, this.startDate));
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Demo", "onStop called.");
    }
    
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Demo", "onRestart called.");
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(         MainActivity.COUNT_VAR,     this.counter);
        outState.putSerializable(MainActivity.STARTDATE_VAR, this.startDate);
    }
    
	@Override
	public void onClick(View v) {
	    this.counter++;
		Intent i = new Intent(this, ResultActivity.class); //private intent
		startActivity(i);
		Log.d("Demo", "flip coin button pressed");
		if (v.getId() == R.id.super_flip_coin_button) {
		    this.finish();
		}
	}
}