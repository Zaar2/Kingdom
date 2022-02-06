package ru.zaar2.kingdom;

import static android.content.ContentValues.TAG;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import ru.zaar2.kingdom.core_second.EntryToCore;

public class MainActivity extends AppCompatActivity {

    Button newGame_btn;
    Button continueGame_btn;
    Button rules_btn;
    Button recordTable_btn;
    Button closeAchievements_btn;
    Button clearAchievements_btn;
    Button btn_goIn;
    Button btn_back;

    ScrollView scrlView_prompt;
    RelativeLayout relativeLayout_mainMenu;
    LinearLayout linearRules;
    LinearLayout linearAchievements;

    RecordTable recordTable;

    boolean flag_NewGame = false;
    boolean flag_firstGame = true;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDate();
        //-------------------------------------------------------------
        initCallBack();
        //-------------------------------------------------------------
        startMobileAds();
        loadMobileAd();
    }

    void initView() {
        newGame_btn = (Button) findViewById(R.id.btn_newGame);
        continueGame_btn = (Button) findViewById(R.id.btn_continueGame);
        rules_btn = (Button) findViewById(R.id.btn_rulesGame);
        recordTable_btn = (Button) findViewById(R.id.btn_recordTable);
        closeAchievements_btn = (Button) findViewById(R.id.btn_closeAchievements);
        clearAchievements_btn = (Button) findViewById(R.id.btn_clearAchievements);
        btn_goIn = (Button) findViewById(R.id.btn_goIn);
        btn_back = (Button) findViewById(R.id.btn_back);

        scrlView_prompt = (ScrollView) findViewById(R.id.scrlView_prompt);
        relativeLayout_mainMenu = (RelativeLayout) findViewById(R.id.relatLoy_main_menu);
        linearRules = (LinearLayout) findViewById(R.id.linearLay_rules);
        linearAchievements = (LinearLayout) findViewById(R.id.linearLay_achievements);

        recordTable = new RecordTable((TextView) findViewById(R.id.tv_recordTable));
    }

    private void initDate() {
        recordTable.init_recordTable(
                getResources(),
                new EntryToCore().readFromRecordTable(this, 1)
        );
    }

    private void initCallBack() {
        btnNewGame_onClick();
        btnContinueGame_onClick();
        btn_recordTable_onClick();
        btn_rulesGame_onClick();

        btn_closeAchievements_onClick();
        btn_clearAchievements_onClick();

        btn_goIn_onClick();
        btn_back_onClick();
    }

    void btnNewGame_onClick() {
        newGame_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                relativeLayout_mainMenu.setVisibility(View.GONE);
//                scrlView_prompt.setVisibility(View.VISIBLE);
                loadMobileAd();

                flag_NewGame = true;
                flag_firstGame = false;
                Intent intent_newGame = new Intent(getApplicationContext(), GameActivity.class);
                intent_newGame.putExtra("new_game", 1);

                startGame(intent_newGame);
            }
        });
    }

    void btnContinueGame_onClick() {
        continueGame_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMobileAd();

                flag_NewGame = true;
                flag_firstGame = false;
                Intent intent_newGame = new Intent(getApplicationContext(), GameActivity.class);
                intent_newGame.putExtra("new_game", 0);

                startGame(intent_newGame);
            }
        });
    }

    private void startGame(Intent intent_newGame) {
        startActivityForResult(intent_newGame, 1);
        showInterstitial();
    }

    private void btn_recordTable_onClick() {
        recordTable_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearRules.setVisibility(View.GONE);
                relativeLayout_mainMenu.setVisibility(View.GONE);
                linearAchievements.setVisibility(View.VISIBLE);
            }
        });
    }

    void btn_rulesGame_onClick() {
        rules_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearRules.setVisibility(View.VISIBLE);
                relativeLayout_mainMenu.setVisibility(View.GONE);
            }
        });
    }

    private void btn_closeAchievements_onClick() {
        closeAchievements_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearRules.setVisibility(View.VISIBLE);
                relativeLayout_mainMenu.setVisibility(View.VISIBLE);
                linearAchievements.setVisibility(View.GONE);
            }
        });
    }

    private void btn_clearAchievements_onClick() {
        clearAchievements_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EntryToCore().clearTable(
                        getResources().getString(R.string.strDB_record),
                        getApplicationContext(),
                        1
                );
                recordTable.init_recordTable(
                        getResources(),
                        new EntryToCore().readFromRecordTable(getApplicationContext(), 1)
                );
            }
        });
    }

    void btn_goIn_onClick() {
        btn_goIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag_NewGame = true;
                flag_firstGame = false;
                Intent intent_newGame = new Intent(getApplicationContext(), GameActivity.class);
                intent_newGame.putExtra("new_game", 1);
                startGame(intent_newGame);
            }
        });
    }

    void btn_back_onClick() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout_mainMenu.setVisibility(View.VISIBLE);
                scrlView_prompt.setVisibility(View.GONE);
            }
        });
    }

    private void startMobileAds() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });
    }

    private void loadMobileAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
                this,
                "ca-app-pub-3940256099942544/1033173712",
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");

                        init_fullScreenContentCallback();
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                }
        );
    }

    private void init_fullScreenContentCallback() {
        mInterstitialAd.setFullScreenContentCallback(
                new FullScreenContentCallback() {
                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        Log.d("TAG", "The ad was dismissed.");
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        Log.d("TAG", "The ad failed to show.");
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        Log.d("TAG", "The ad was shown.");
                    }
                });
    }

    private void showInterstitial() {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(this);
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        recordTable.init_recordTable(
                getResources(),
                new EntryToCore().readFromRecordTable(this, 1)
        );
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
}