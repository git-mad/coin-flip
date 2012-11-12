package coinFlipV1.gitmad.app;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import coinFlipV1.gitmad.app.service.RollService;
import coinFlipV1.gitmad.app.service.RollService.RollServiceReceiver;

public class MainActivity extends Activity implements OnClickListener, SensorEventListener {
    private static final String COUNT_VAR     = "count";
    private static final String STARTDATE_VAR = "startDate";

    private Date startDate;
    private int counter = 0;
            
    private SensorManager mSensorManager; 
    private Sensor mSensor; 
    
    private long lastTime;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        View flipCoinButton = findViewById(R.id.flip_coin_button);
        flipCoinButton.setOnClickListener(this);
       
        View superFlipCoinButton = findViewById(R.id.super_flip_coin_button);
        superFlipCoinButton.setOnClickListener(this);
        
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lastTime = System.currentTimeMillis();
        
        if (savedInstanceState != null && savedInstanceState.containsKey(COUNT_VAR)) {
            this.counter   =       savedInstanceState.getInt(MainActivity.COUNT_VAR);
            this.startDate = (Date)savedInstanceState.getSerializable(MainActivity.STARTDATE_VAR);
        } else {
            this.startDate = new Date();
        }
        
        RollService.registerReceiver(this, new RollServiceReceiver() {

            @Override
            protected void receiveRoll(String roll) {
                showResults(roll);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Demo", "onStart called.");
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
        Log.d("Demo", "onPause called.");
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Demo", "onResume called.");
        //set the text with the new counter value
        ((TextView)this.findViewById(R.id.text_counter)).setText(String.format("%d flips since %s", this.counter, this.startDate));
        
        //Register accelerometer listener
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
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
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(         MainActivity.COUNT_VAR,     this.counter);
        outState.putSerializable(MainActivity.STARTDATE_VAR, this.startDate);
    }
    
	@Override
	public void onClick(View v) {
	    flipTheCoin();
		Log.d("Demo", "flip coin button pressed");
		if (v.getId() == R.id.super_flip_coin_button) {
		    this.finish();
		}
	}
	
	private void flipTheCoin() {
        this.counter++;
        this.startService(new Intent(this, RollService.class));
    }

    public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
			getAccelerometer(event);
	}
	
	public void getAccelerometer(SensorEvent event) {
		float[] values = event.values;
		
		//break up into coordinates
		float x = values[0];
		float y = values[1];
		float z = values[2];
		
		float accelerationSquareRoot = (x*x + y*y + z*z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
		
		long timestamp = System.currentTimeMillis();
		
		if(accelerationSquareRoot >= 2) {
			if(timestamp - lastTime < 200)
				return;
			
			lastTime = timestamp;
			Log.d("Demo", "Device shuffled");
			
			flipTheCoin();
		}
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}
	
	private void showResults(String results) {
        Intent i = new Intent(this, ResultActivity.class);
        i.putExtra(ResultActivity.PARAM_RESULT, results);
        startActivity(i);
	}	
}