#Sensors
##Introduction
Sensors is one of the main aspects of computing that separates mobile development from desktop development. Mobile devices have cameras, accelerometers, GPS, etc. that allows for even more creativity when designing applications.

For the purposes of coin-flip, we'll be taking advantage of the accelerometer to flip our virtual coin.

###Libraries
There are a few libraries we'll need to import to read data from the accelerometer, so we'll just import android.hardware.* for now. The libraries are

```
android.hardware.SensorEventListener
android.hardware.Sensor
android.hardware.SensorEvent
android.hardware.SensorManager
```

###MainActivity class changes
We'll first need to implement our SensorEventListener in addition from our OnClickListener from a few weeks ago. SensorEventListener contains two methods, onSensorChanged (which we'll use) and onAccuracyChanged (which we won't).
Then, instantiate a SensorManager and a Sensor **outside** of the onCreate() method:

```
private SensorManager mSensorManager;
private Sensor mSensor;
```

###onCreate()
We'll also need to make a few updates to our onCreate() method from last time. Inside the onCreate() method, fill out mSensorManager and mSensor:

```
mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
```

###onSensorChanged()
This is the first of the methods in our SensorEventListener. It calls our accelerometer when there are changes.

```
public void onSensorChanged(SensorEvent event) {
	if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
		getAccelerometer(event);
}
```

###getAccelerometer()
This is where the meat of our accelerometer math happens. First, read in the coordinate values provided by the accelerometer, then find the overall acceleration (correcting for gravity):

```
float[] values = event.values;
		
float x = values[0];
float y = values[1];
float z = values[2];
		
float accelerationSquareRoot = (x*x + y*y + z*z) /
(SensorManager.GRAVITY_EARTH*SensorManager.GRAVITY_EARTH);
```

If the overall acceleration is large enough within a reasonable time range, flip the coin.

```		
long timestamp = System.currentTimeMillis();
		
if(accelerationSquareRoot >= 2) {
	if(timestamp - lastTime < 200)
		return;
			
	lastTime = timestamp;
			
	this.counter++;
	Intent i = new Intent(this, ResultActivity.class);
	startActivity(i);
}
```
