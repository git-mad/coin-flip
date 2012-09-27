package coinFlipV1.gitmad.app;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * This is the Main Activity for the Coin Flip App.
 * We flip coins epicly.  We hope the Coin Flip App will
 * help people understand how an Android Project works.
 * 
 * For more help check out these resources:
 * 
 * http://developer.android.com/guide/components/index.html
 * http://stackoverflow.com/
 * http://www.youtube.com/course?list=EC2F07DBCDCC01493A&feature=plcp
 */
public class MainActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // creates this Activity.  DO NOT TOUCH
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Instantiate the "FLIP" Button.
        View flipCoinButton = findViewById(R.id.flip_coin_button);
        
        // What happens when you click on "FLIP" button?
        flipCoinButton.setOnClickListener(this);
    }

    /**
     * This method is where you specify what
     * will happen when you click the button.
     */
	@Override
	public void onClick(View v) {
		Intent i = new Intent(this, ResultActivity.class); //private intent
		startActivity(i);
		
		Log.d("Demo", "flip coin button pressed");
		// This logs the message on LogCat.  Found at:
		// Window -> Show View -> Other -> Android -> LogCat
	}
}