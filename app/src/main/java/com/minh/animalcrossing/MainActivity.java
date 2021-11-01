package com.minh.animalcrossing;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private CheckBox cb1, cb2, cb3;
    private SeekBar sb1, sb2, sb3;
    private TextView ponit;
    private ImageButton btPlay;
    private Button btAgain;
    private int player_point = 100;
    private int idBet = 0;
    private int idWin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        cb1 = (CheckBox) findViewById(R.id.cb_1);
        cb2 = (CheckBox) findViewById(R.id.cb_2);
        cb3 = (CheckBox) findViewById(R.id.cb_3);
        sb1 = (SeekBar) findViewById(R.id.sb_1);
        sb2 = (SeekBar) findViewById(R.id.sb_2);
        sb3 = (SeekBar) findViewById(R.id.sb_3);
        ponit = (TextView) findViewById(R.id.textViewPoint);
        btPlay = (ImageButton) findViewById(R.id.imageButtonPlay);
        btAgain = (Button) findViewById(R.id.button);

        sb1.setMax(200);
        sb2.setMax(200);
        sb3.setMax(200);

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    idBet = 1;
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    idBet = 2;
                    cb1.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    idBet = 3;
                    cb2.setChecked(false);
                    cb1.setChecked(false);
                }
            }
        });

        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb1.isChecked() || cb2.isChecked() || cb3.isChecked()){
                    btPlay.setEnabled(false);


                    CountDownTimer countDownTimer = new CountDownTimer(100000,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            Random random = new Random();
                            int speed1 = random.nextInt(40) + 10;
                            int speed2 = random.nextInt(40) + 10;
                            int speed3 = random.nextInt(40) + 10;

                            sb1.setProgress(sb1.getProgress()+speed1);
                            sb2.setProgress(sb2.getProgress()+speed2);
                            sb3.setProgress(sb3.getProgress()+speed3);
                            if(sb1.getProgress() >= sb1.getMax() || sb2.getProgress() >= sb2.getMax() || sb3.getProgress() >= sb3.getMax()) {
                                if (sb1.getProgress() >= sb1.getMax()) {
                                    idWin = 1;
                                }
                                if (sb2.getProgress() >= sb2.getMax()) {
                                    idWin = 2;
                                }
                                if (sb3.getProgress() >= sb3.getMax()) {
                                    idWin = 3;
                                }
                                this.cancel();
                                this.onFinish();
                            }
                        }

                        @Override
                        public void onFinish() {
                            switch (idWin){
                                case 1:{
                                    Toast.makeText(MainActivity.this,"Hammer won",Toast.LENGTH_SHORT).show();
                                    if(idBet == idWin){
                                        Toast.makeText(MainActivity.this,"You won the Bet + 10pts",Toast.LENGTH_SHORT).show();
                                        player_point += 10;
                                        ponit.setText(String.valueOf(player_point));
                                    }else{
                                        Toast.makeText(MainActivity.this,"You lost the Bet - 10pts",Toast.LENGTH_SHORT).show();
                                        player_point -= 10;
                                        ponit.setText(String.valueOf(player_point));
                                    }
                                    break;
                                }
                                case 2:{
                                    Toast.makeText(MainActivity.this,"Hand won",Toast.LENGTH_SHORT).show();
                                    if(idBet == idWin){
                                        Toast.makeText(MainActivity.this,"You won the Bet + 10pts",Toast.LENGTH_SHORT).show();
                                        player_point += 10;
                                        ponit.setText(String.valueOf(player_point));
                                    }else{
                                        Toast.makeText(MainActivity.this,"You lost the Bet - 10pts",Toast.LENGTH_SHORT).show();
                                        player_point -= 10;
                                        ponit.setText(String.valueOf(player_point));
                                    }
                                    break;
                                }
                                case 3:{
                                    Toast.makeText(MainActivity.this,"Dollar won",Toast.LENGTH_SHORT).show();
                                    if(idBet == idWin){
                                        Toast.makeText(MainActivity.this,"You won the Bet + 10pts",Toast.LENGTH_SHORT).show();
                                        player_point += 10;
                                        ponit.setText(String.valueOf(player_point));
                                    }else{
                                        Toast.makeText(MainActivity.this,"You lost the Bet - 10pts",Toast.LENGTH_SHORT).show();
                                        player_point -= 10;
                                        ponit.setText(String.valueOf(player_point));
                                    }
                                }
                            }
                            btAgain.setVisibility(View.VISIBLE);
                        }
                    };

                    countDownTimer.start();
                }else{
                    Toast.makeText(MainActivity.this,"You have to bet at 1 icon to play",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPlay.setEnabled(true);
                sb1.setProgress(0);
                sb2.setProgress(0);
                sb3.setProgress(0);
                btAgain.setVisibility(View.INVISIBLE);
            }
        });
    }

}