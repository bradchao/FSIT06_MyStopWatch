package tw.org.iii.appps.mystopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private boolean isRunning;
    private Button leftBtn, rightBtn;
    private Timer timer;
    private CounterTask task;
    private int iCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = new Timer();

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
        }
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
        }
    }
}
