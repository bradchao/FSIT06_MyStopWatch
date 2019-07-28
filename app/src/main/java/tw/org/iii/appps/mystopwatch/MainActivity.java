package tw.org.iii.appps.mystopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private boolean isRunning;
    private Button leftBtn, rightBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftBtn = findViewById(R.id.leftBtn);
        rightBtn = findViewById(R.id.rightBtn);
    }

    public void doLeft(View view) {
    }

    public void doRight(View view) {
        isRunning = !isRunning;
        leftBtn.setText(isRunning?"Lap":"Reset");
        rightBtn.setText(isRunning?"Stop":"Start");
    }
}
