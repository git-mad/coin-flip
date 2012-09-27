package coinFlipV1.gitmad.app;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This is the ResultActivity.  This Activity was called
 * in MainActivity when you clicked the button.  This
 * Activity will show the results.
 * 
 */
public class ResultActivity extends Activity implements OnClickListener {
	private String result = "not set";
	private int resultImage = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		// creates this Activity
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		
		// flips coin, sets current result to new result
		setResult(flipCoin());
		
		// if heads, set resultImage ID to "heads"
		// else, set the ID to "tails"
		if ( getResult()=="heads" ){
			resultImage = R.drawable.heads;
		}
		else {
			resultImage = R.drawable.tails;
		}
		
		Log.d("Demo", getResult());
		
		// Instantiates an ImageView to display the image result
		ImageView resultImageView = (ImageView) this.findViewById(R.id.result_image);
		resultImageView.setBackgroundResource(resultImage);
		
		// Instantiates a textView to display textual result
		TextView resultView = (TextView) this.findViewById(R.id.result_value_label);
		resultView.setText(this.getResult());

		// Instantiates button, when clicked will go back to Main Activity
		View flipCoinButton = findViewById(R.id.back_to_menu_button);
		flipCoinButton.setOnClickListener(this);
	}

	public int getResultImage() {
		return resultImage;
	}

	public void setResultImage(int resultImage) {
		this.resultImage = resultImage;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	/**
	 * This method randomly flips a coin
	 * and returns String result.
	 */
	private static String flipCoin() {
		Random rand = new Random();
		Double flip = rand.nextDouble();
		String result = "";

		if (flip < 0.5)
			result = "heads";
		else
			result = "tails";

		return result;
	}

	/**
	 * When back to menu button is clicked,
	 * this activity will be destroyed.
	 * Then the next activity on the stack
	 * (MainActivity) will appear.
	 */
	@Override
	public void onClick(View v) {
		this.finish();
	}
}
