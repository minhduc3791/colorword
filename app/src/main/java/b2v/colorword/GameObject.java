package b2v.colorword;

import android.graphics.Color;
import android.widget.Button;

import java.sql.Timestamp;
import java.util.Random;

/**
 * Created by CPU10285-local on 3/28/2015.
 */
public class GameObject {
    int score, nowScore;
    int life;
    int time, nowTime;
    public static GameObject initGame(){
        GameObject gameObject = new GameObject();

        gameObject.score = 0;
        gameObject.nowScore = 0;

        gameObject.life = 3;

        gameObject.time = 0;
        gameObject.nowTime = 0;

        return gameObject;
    }

    public static void run(){

    }

    public void makeGame(){

    }

    public int randInt() {
        Long tsLong = System.currentTimeMillis()/1000;
        int max = (int) (tsLong % 10000);

        Random rand = new Random();
        int randomNum = rand.nextInt(max);
        return randomNum;
    }

    public int getLife(){
        return this.life;
    }

    public void setLife(Integer life){ this.life = life; }

    public int getScore(){ return this.score; }

    public void setScore(Integer score){ this.score = score; }

    public int getTime(){
        return this.time;
    }

    public void setTime(Integer time){ this.time = time; }

    public String getGameMode(){
        int wut = randInt();

        return wut % 2 == 0 ? "Text" : "Color";
    }

    public String getQuestionString(){
        int wut = randInt();
        String[] questions = {"YELLOW", "GREEN", "BLACK", "WHITE", "AQUA"};

        return questions[wut % 5];
    }

    public int getQuestionColor(){
        int wut = randInt();
        int[] questions = {Color.YELLOW, Color.GREEN, Color.BLACK, Color.WHITE, Color.CYAN};

        return questions[wut % 5];
    }
}
