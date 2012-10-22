package coinFlipV1.gitmad.app;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewAnimator;

public class ResultActivity extends Activity implements OnClickListener {
	private String result = "not set";
	private int resultImage = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		
		setResult(flipCoin());
		
		Log.d("Demo", getResult());
		
		int images[] = {R.drawable.heads, R.drawable.tails};
		ImageView resultImageView = (ImageView) this.findViewById(R.id.result_value_image);
		resultImageView.setBackgroundResource(images[0]);
		
		//TextView text = (TextView) this.findViewById(R.id.result_value_label);
		//text.setText(getResult());
				
		if (getResult() == "heads")
		{
			resultImage = R.drawable.heads;
			flipAnimate(resultImageView, images, 0, 9, true);
		}
		else
		{
			resultImage = R.drawable.tails;
			flipAnimate(resultImageView, images, 0, 7, true);
		}

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
	
	private void flipAnimate(final ImageView imageView, final int images[], final int imageIndex, final int iterations, final boolean isShrunk) {
		
		if (iterations > 0) {
		
			Animation curAnim = null;
			ScaleAnimation shrink = new ScaleAnimation(1, 0, 1, 1, Animation.RELATIVE_TO_SELF, (float) 0.5, Animation.RELATIVE_TO_SELF, (float) .5);
			ScaleAnimation expand = new ScaleAnimation(0, 1, 1, 1, Animation.RELATIVE_TO_SELF, (float) 0.5, Animation.RELATIVE_TO_SELF, (float) .5);
			
			if (isShrunk)
			{
				curAnim = expand;
				imageView.setBackgroundResource(images[imageIndex]);
			}
			else{
				curAnim = shrink;
			}
			
			curAnim.setDuration(300);
			
			imageView.setAnimation(curAnim);
			
			curAnim.setAnimationListener(new AnimationListener() {
				public void onAnimationEnd(Animation animation) {
					int newIndex = imageIndex;
					if (isShrunk)
					{
						if (imageIndex == 1)
						{
							newIndex = 0;
						}
						else
						{
							newIndex = 1;
						}
					}
					
					flipAnimate(imageView, images, newIndex, iterations-1, !isShrunk);
		        }
		        public void onAnimationRepeat(Animation animation) {
		            // TODO Auto-generated method stub
		        }
		        public void onAnimationStart(Animation animation) {
		            // TODO Auto-generated method stub
		        }
			});
		}
	}

	@Override
	public void onClick(View v) {
		this.finish();
	}
}
