package coinFlipV1.gitmad.app;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.*;
import coinFlipV1.gitmad.app.database.LeaderboardDAO;
import coinFlipV1.gitmad.app.models.Coin;
import coinFlipV1.gitmad.app.models.Streak;

public class ResultActivity extends Activity implements OnClickListener {
	private boolean result = false;
	private int resultImage = 0;
    private Streak streak;
    public static final String PREFS_NAME = "PrefsFile";
    private LeaderboardDAO leaderboardDAO;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);

        leaderboardDAO = new LeaderboardDAO(getApplicationContext());
		
		setResult(Coin.flip());

		Log.d("Demo", "" + getResult());
		
		int images[] = {R.drawable.heads, R.drawable.tails};
		ImageView resultImageView = (ImageView) this.findViewById(R.id.result_value_image);
		resultImageView.setBackgroundResource(images[0]);
		
		//TextView text = (TextView) this.findViewById(R.id.result_value_label);
		//text.setText(getResult());
				
		if (result)
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

        updateStreak(result);

        TextView streakText = (TextView) this.findViewById(R.id.streak_label);
        streakText.setText(createStreakText(streak));
	}

    private String createStreakText(Streak streak) {
        String result = "The current streak is: ";

        result += streak.getLength();
        if (streak.getType()) {
            result += " Head";
        } else {
            result += " Tail";
        }

        if (streak.getLength() > 1) {
            result += "s";
        }

        return result;
    }

    private void promptForHighScore() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Title");
        alert.setMessage("Message");

        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                leaderboardDAO.createLeaderboardEntry(value, streak.getLength(), System.currentTimeMillis());
                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_LONG).show();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();
    }

    private void updateStreak(boolean result) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        streak = new Streak(settings.getBoolean("streakType", false), settings.getInt("streakLength", 0));

        if (result == streak.getType()) {
            streak.incrementLength();
        } else {
            promptForHighScore();
            streak = new Streak(result);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("streakType", streak.getType());
        editor.putInt("streakLength", streak.getLength());

        editor.commit();

    }

	public void setResult(boolean result) {
		this.result = result;
	}

	public boolean getResult() {
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
