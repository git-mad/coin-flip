package coinFlipV1.gitmad.app;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ResultActivity extends Activity implements OnClickListener {
	private Coin coin = new Coin();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		TextView resultView = (TextView) this.findViewById(R.id.result_value_label);
		resultView.setText(this.coin.flip());
		Log.d("Demo", this.coin.getResult());

		View flipCoinButton = findViewById(R.id.back_to_menu_button);
		flipCoinButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		this.finish();
	}
}
