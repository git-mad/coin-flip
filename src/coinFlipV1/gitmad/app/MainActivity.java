package coinFlipV1.gitmad.app;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        View flipCoinButton = findViewById(R.id.flip_coin_button);
        flipCoinButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		Intent i = new Intent(this, ResultActivity.class); //private intent
		startActivity(i);
		Log.d("Demo", "flip coin button pressed");		
	}
}