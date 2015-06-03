package b2v.colorword;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.CountDownTimer;

import java.lang.reflect.Array;

import b2v.colorword.GameObject;


public class GameActivity extends ActionBarActivity {
    private GameActivity gameActivity;
    GameObject gameObject = GameObject.initGame();
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        //while(gameObject.getLife() > 0){
        countDownTimer = MakeCountDownTimer(5000);
        show(gameObject);
        makeButton(gameObject);
        //}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public CountDownTimer MakeCountDownTimer(int dynamicTime){
        final Intent intent = new Intent(this, MyActivity.class);

        CountDownTimer tempCountDownTimer = new CountDownTimer(dynamicTime, 1) { // adjust the milli seconds here
            public void onTick(long millisUntilFinished) {
                final TextView innerTextView;
                innerTextView = (TextView) findViewById(R.id.textTime);
                innerTextView.setText("" + millisUntilFinished/10);
            }

            public void onFinish() {
                //nowLife--;
                //if(nowLife <= 0)
                final TextView timer, life;
                gameObject.setLife(gameObject.getLife() - 1);

                if(gameObject.getLife() <= 0){
                    startActivity(intent);

                    return;
                }


                life = (TextView) findViewById(R.id.textLife);
                life.setText("" + gameObject.getLife());

                //countDownTimer.start();
                show(gameObject);
            }
        };

        return tempCountDownTimer;
    }


    public void show(final GameObject gameObject){
        TextView tempText;

        countDownTimer.cancel();
        countDownTimer = MakeCountDownTimer(5000);

        tempText = (TextView) findViewById(R.id.textGameMode);
        tempText.setText(gameObject.getGameMode());//

        tempText = (TextView) findViewById(R.id.textQuestion);
        tempText.setText(gameObject.getQuestionString());
        tempText.setTextColor(gameObject.getQuestionColor());//

        tempText = (TextView) findViewById(R.id.textLife);
        tempText.setText(Integer.toString(gameObject.getLife()));

        tempText = (TextView) findViewById(R.id.textScore);
        tempText.setText(Integer.toString(gameObject.getScore()));

        tempText = (TextView) findViewById(R.id.textTime);
        tempText.setText(Integer.toString(gameObject.getTime()));

        countDownTimer.start();
    }

    public void makeButton(GameObject gameObject){
        Button button;
        int[] buttonIds = {R.id.YELLOW, R.id.GREEN, R.id.BLACK, R.id.WHITE, R.id.AQUA};
        int[] buttonColors = {Color.YELLOW, Color.GREEN, Color.BLACK, Color.WHITE, Color.CYAN};
        String[] buttonNames = {"YELLOW", "GREEN", "BLACK", "WHITE", "AQUA"};

        for(int i = 0 ; i < buttonIds.length; i++){
            final String tempButtonName = buttonNames[i];
            final Integer tempButtonColor = buttonColors[i];

            button = (Button) findViewById(buttonIds[i]);
            button.setText(tempButtonName);
            button.setTextColor(tempButtonColor);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    TextView nowGameMode = (TextView) findViewById(R.id.textGameMode);
                    TextView nowQuestion = (TextView) findViewById(R.id.textQuestion);
                    String questionString = nowQuestion.getText().toString();
                    Integer questionStringColor  = nowQuestion.getCurrentTextColor();
                    String gameModeString = nowGameMode.getText().toString();
                    checkAnswer(questionString, questionStringColor, tempButtonName, tempButtonColor, gameModeString);
                }
            });
        }
    }

    public void checkAnswer(String text, Integer color, String sText, Integer sColor, String gameModeString){
       if(gameModeString.equalsIgnoreCase("Color")) {
            if(color.intValue() == sColor.intValue()) {
                // score ++
                gameObject.setScore(gameObject.getScore() + 10);
                show(gameObject);
            } else {
                // lose ??
                gameObject.setScore(gameObject.getScore() + 9);
                gameObject.setLife(gameObject.getLife() - 1);
                if(gameObject.getLife() <= 0){
                    Intent intent = new Intent(this, MyActivity.class);
                    startActivity(intent);
                }
                else
                    show(gameObject);
            }
        } else {
            if(text.equalsIgnoreCase(sText)) {
                // score ++
                gameObject.setScore(gameObject.getScore() + 10);
                show(gameObject);
            } else {
                // lose ??
                gameObject.setScore(gameObject.getScore() + 9);
                gameObject.setLife(gameObject.getLife() - 1);
                if(gameObject.getLife() <= 0) {
                    Intent intent = new Intent(this, MyActivity.class);
                    startActivity(intent);
                }
                else
                    show(gameObject);
            }
        }
    }
}
