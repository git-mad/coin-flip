package coinFlipV1.gitmad.app.service;

import java.util.Random;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class RollService extends IntentService {

    public abstract static class RollServiceReceiver extends BroadcastReceiver {

        public static final String ACTION_RESP =
                "coinFlipV1.gitmad.app.service.MESSAGE_PROCESSED";
        @Override
        public void onReceive(Context context, Intent intent) {
            String roll = intent.getExtras().getString(RollService.PARAM_ROLL);
            receiveRoll(roll);
        }
        protected abstract void receiveRoll(String roll);
    }
    
    private static String PARAM_ROLL = "roll";

    public RollService() {
        super("RollService");
    }

    @Override
    protected void onHandleIntent(Intent arg0) {
        String results = flipCoin();
        
        //send the alerts out via broadcast
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(RollServiceReceiver.ACTION_RESP);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra(RollService.PARAM_ROLL, results);
        sendBroadcast(broadcastIntent);
        
    }

    public static void registerReceiver(Context context, RollServiceReceiver receiver) {
        IntentFilter filter = new IntentFilter(RollServiceReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        context.registerReceiver(receiver, filter);
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

}
