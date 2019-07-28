package tw.org.iii.appps.mystopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.strictmode.UnbufferedIoViolation;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private boolean isRunning;
    private TextView clock;
    private Button leftBtn, rightBtn;
    private Timer timer;
    private CounterTask task;
    private int iCounter;
    private UIHandler uiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clock = findViewById(R.id.clock);

        timer = new Timer();
        uiHandler = new UIHandler();

        leftBtn = findViewById(R.id.leftBtn);
        rightBtn = findViewById(R.id.rightBtn);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null){
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }

    private class CounterTask extends TimerTask {
        @Override
        public void run() {
            iCounter++;
            uiHandler.sendEmptyMessage(0);
        }
    }

    private class UIHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            clock.setText(toClockString(iCounter));
        }
    }

    private static String toClockString(int i){
        int hs = i % 100;
        int ts = i / 100;
        
        return ts + "." + hs;
    }

    public void doLeft(View view) {
    }

    public void doRight(View view) {
        isRunning = !isRunning;
        leftBtn.setText(isRunning?"Lap":"Reset");
        rightBtn.setText(isRunning?"Stop":"Start");

        if (isRunning){
            task = new CounterTask();
            timer.schedule(task, 10, 10);
        }else if (task != null){
            task.cancel();
        }
    }
}
