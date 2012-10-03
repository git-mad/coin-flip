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

public class ResultActivity extends Activity implements OnClickListener {
	private String result = "not set";
	private int resultImage = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		
		setResult(flipCoin());
		
		if (getResult() == "heads")
		{
			resultImage = R.drawable.heads;
		}
		else
		{
			resultImage = R.drawable.tails;
		}
		
		Log.d("Demo", getResult());
		
		ImageView resultImageView = (ImageView) this.findViewById(R.id.result_value_image);
		resultImageView.setBackgroundResource(resultImage);
		
		TextView resultView = (TextView) this.findViewById(R.id.result_value_label);
		resultView.setText(this.getResult());

		View flipCoinButton = findViewById(R.id.back_to_menu_button);
		flipCoinButton.setOnClickListener(this);
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

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

	@Override
	public void onClick(View v) {
		this.finish();
	}
}
