package ru.zaar2.kingdom;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import ru.zaar2.kingdom.core_second.EntryToCore;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private PanelOfIndicators panelOfIndicators;
    private PanelOfResources panelOfResources;
    private Fragment_tool fragment_tool;

    private ScrollView view_activityGame;
    private LinearLayout event;
    private int[] eventsList;
    private int step, maxSteps;
    private boolean endGame;

    private final Handler gameOver_handler = new Handler();

    private boolean panel_ready;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initVariables();
        initClass();
        initView();

        event.setOnClickListener(this);

        initData();
    }

    private void initVariables() {
        panel_ready = true;
        endGame = false;
    }

    private void initClass() {
//        launchHandler_gameOver();
    }

    private void initView() {
        panelOfIndicators = new PanelOfIndicators(findViewById(R.id.linearLayout_panelOfIndicators));
        panelOfResources = new PanelOfResources(
                findViewById(R.id.linearLayout_resources),
                findViewById(R.id.tv_timeOfGame)
        );
        panelOfIndicators.setVisibility(panel_ready);
        view_activityGame = (ScrollView) findViewById(R.id.scrollView_activityGame_parent);
        event = (LinearLayout) findViewById(R.id.event_linLayout);
//        event.setVisibility(View.GONE);
    }

    private void initData() {        //if first launch <new game,load game>
        int value_arguments;
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            value_arguments = arguments.getInt("new_game");
            if (value_arguments == 1) {
                startNewGame();
            } else {
                loadGame();
            }
        }
    }


    private void startNewGame() {
        //устанавливаем in BD начальные значения
        new EntryToCore().restartDB(this, 1);
        //заполняем view from BD
        panelOfResources.updateView_Resources(this);
        panelOfIndicators.initViews_panel(this);
        panelOfIndicators.updateView_Indicators(this);

        game();
    }

    private void loadGame() {
        //заполняем view from BD
        panelOfResources.updateView_Resources(this);
        panelOfIndicators.initViews_panel(this);
        panelOfIndicators.updateView_Indicators(this);

        game();
    }

    private void game() {
        openFragment_toolActivity();
    }

    public void btnNext_onClick(View v) {
        fragment_tool.btn_solved_onClick(this);
        closeFragment_toolActivity(fragment_tool);
        updNumberOfIssuesResolved();
        openFragment_toolActivity();

        panelOfIndicators.updateView_Indicators(this);
        panelOfResources.updateView_Resources(this);
    }

    @SuppressLint("NonConstantResourceId")
    public void btn_panel_onClick(View v) //press button of menu
    {
        if (panel_ready) {
            panelOfIndicators.setVisibility(false);
            panel_ready = false;
        } else {
            panelOfIndicators.setVisibility(true);
            panel_ready = true;
        }
    }

    private void openFragment_toolActivity() {
        int question = getNumberOfIssuesResolved();
        fragment_tool = new Fragment_tool();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragmentOfTool, fragment_tool);
        ft.commit();

        fragment_tool.setTextTitle(
                panelOfIndicators.getTitle_for_fragment(question, this)
        );
        fragment_tool.setMaxValueOfSeekBar_dependingOnActionType(
                panelOfIndicators.getMaxValue_seekBarProgress_byFragmentTools(question, this)
        );
        fragment_tool.setInfo_text(
                panelOfIndicators.getText_for_fragment(question, this)
        );
    }

    private void closeFragment_toolActivity(Fragment_tool frag) {
        if (frag != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.remove(frag);
            ft.commit();
            fragment_tool = null;
//            fragmentTool_ready = false;
        }
    }

    private int getNumberOfIssuesResolved() {
        return new EntryToCore().findCurrentlyValue_ofSpecifiedParameter(
                getResources().getString(R.string.strDB_currently_question_accessory),
                getResources().getString(R.string.strDB_accessory),
                this,
                1
        );
    }

    private void updNumberOfIssuesResolved() {
        int question = getNumberOfIssuesResolved();
        if (++question < getResources().getStringArray(R.array.questions_arr).length) {
            new EntryToCore().updateParameter(
                    question,
                    getResources().getString(R.string.strDB_currently_question_accessory),
                    getResources().getString(R.string.strDB_accessory),
                    this,
                    1
            );
        } else {
            int year = Integer.parseInt((String) ((TextView) findViewById(R.id.tv_timeOfGame)).getText());
            panelOfResources.incrementAndUpdateDB_Years(year, this);
            new EntryToCore().updateParameter(
                    0,
                    getResources().getString(R.string.strDB_currently_question_accessory),
                    getResources().getString(R.string.strDB_accessory),
                    this,
                    1
            );
            calcDB_forYear();
        }
    }

    public void calcDB_forYear() {
        eventsList = new EntryToCore().calcDB_forYear(this);
        if (eventsList.length > 0) {
            step = 0;
            maxSteps = eventsList.length;

            new Events(
                    event,
                    eventsList[step],
                    this
            );
            view_activityGame.setVisibility(View.GONE);
            event.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        if (
                eventsList[step] == R.string.event_defeat_dueTo_hunger
                        || eventsList[step] == R.string.event_defeat_dueTo_rebellion
                        || eventsList[step] == R.string.event_defeat_in_war
        ) {
            endGame = true;
        } else {
            step++;
            if (step < maxSteps) {
                new Events(
                        event,
                        eventsList[step],
                        this
                );
            } else {
                event.setVisibility(View.GONE);
                view_activityGame.setVisibility(View.VISIBLE);
            }
        }
    }

    private void launchHandler_gameOver() {
        gameOver_handler.removeCallbacks(timer_gameOver);
        gameOver_handler.postDelayed(timer_gameOver, 100);
    }

    private void handler_gameOver_onPaused() {
        gameOver_handler.removeCallbacksAndMessages(null);
    }

    private final Runnable timer_gameOver = new Runnable() {
        @Override
        public void run() {
            if (endGame) {
                gameOver();
            } else {
                gameOver_handler.postDelayed(this, 100);
            }
        }
    };

    private void gameOver() {
        new EntryToCore().restartDB(this, 1);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler_gameOver_onPaused();
    }

    @Override
    protected void onResume() {
        super.onResume();
        launchHandler_gameOver();
    }
}