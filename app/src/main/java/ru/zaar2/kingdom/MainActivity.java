package ru.zaar2.kingdom;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import ru.zaar2.kingdom.R;

public class MainActivity extends AppCompatActivity {

    Button newGame;
    Button continueGame;
    Button rules;

    Button btn_goIn;
    Button btn_back;
    ScrollView scrlView_prompt;
    RelativeLayout relativeLayout_mainMenu;
    LinearLayout linearRules;

    boolean flag_NewGame =false;
    boolean flag_firstGame=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        btnNewGame_onClick();
        btnContinueGame_onClick();
        btn_rulesGame_onClick();
//        btn_goIn_onClick();
        btn_back_onClick();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!flag_firstGame) {
            if (flag_NewGame) {
                relativeLayout_mainMenu.setVisibility(View.VISIBLE);
                scrlView_prompt.setVisibility(View.GONE);
            } else {
                relativeLayout_mainMenu.setVisibility(View.GONE);
                scrlView_prompt.setVisibility(View.VISIBLE);
            }
        }
    }

    void initView(){
        newGame=(Button)findViewById(R.id.btn_newGame);
        continueGame=(Button)findViewById(R.id.btn_continueGame);
        rules=(Button)findViewById(R.id.btn_rulesGame);

        btn_goIn=(Button)findViewById(R.id.btn_goIn);
        btn_back=(Button)findViewById(R.id.btn_back);
        scrlView_prompt=(ScrollView)findViewById(R.id.scrlView_prompt);
        relativeLayout_mainMenu=(RelativeLayout)findViewById(R.id.relatLoy_main_menu);
        linearRules=(LinearLayout) findViewById(R.id.linearLay_rules);
    }

    void btnNewGame_onClick(){
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                relativeLayout_mainMenu.setVisibility(View.GONE);
//                scrlView_prompt.setVisibility(View.VISIBLE);
                btn_goIn_onClick();
            }
        });
    }

    void btnContinueGame_onClick(){
        continueGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag_NewGame = true;
                flag_firstGame = false;
                Intent intent_newGame = new Intent(getApplicationContext(), GameActivity.class);
                intent_newGame.putExtra("new_game",0);
                startActivity(intent_newGame);
            }
        });
    }

    void btn_rulesGame_onClick(){
        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearRules.setVisibility(View.VISIBLE);
                relativeLayout_mainMenu.setVisibility(View.GONE);
            }
        });
    }

    void btn_goIn_onClick() {
                flag_NewGame = true;
                flag_firstGame = false;
                Intent intent_newGame = new Intent(getApplicationContext(), GameActivity.class);
                intent_newGame.putExtra("new_game",1);
                startActivity(intent_newGame);
            }

//    void btn_goIn_onClick() {
//        btn_goIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                flag_NewGame = true;
//                flag_firstGame = false;
//                Intent intent_newGame = new Intent(getApplicationContext(), GameActivity.class);
//                intent_newGame.putExtra("new_game",1);
//                startActivity(intent_newGame);
//            }
//        });
//    }

    void btn_back_onClick(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout_mainMenu.setVisibility(View.VISIBLE);
                scrlView_prompt.setVisibility(View.GONE);
            }
        });
    }
}
