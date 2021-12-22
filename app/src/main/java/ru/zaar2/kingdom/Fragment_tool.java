package ru.zaar2.kingdom;

import android.content.Context;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import ru.zaar2.kingdom.core_second.EntryToCore;

public class Fragment_tool extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private View v;
    private String title;
    private final boolean isOver = false;

    private String info_text;
    private int maxValue_seekBar = 0;
    private int currentValue_seekBar_approved;

    private EditText editText_frag;
    private SeekBar seekBar_frag;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_tool, container, false);

        initView();
        settingSeekBar();
        settingView();

        editText_setOnEditorActionListener();
        seekBar_frag.setOnSeekBarChangeListener(this);
        return v;
    }

    private void initView() {
        editText_frag = (EditText) v.findViewById(R.id.valueOfSeekBar);
        seekBar_frag = (SeekBar) v.findViewById(R.id.seekBar_subsectionOfTool);
    }

    private void settingSeekBar() {
        seekBar_frag.setMax(maxValue_seekBar);
        currentValue_seekBar_approved = 0;
        seekBar_frag.setProgress(currentValue_seekBar_approved);
    }

    private void settingView() {
        ((TextView) v.findViewById(R.id.title_fragment_tool)).setText(title);
        ((TextView) v.findViewById(R.id.infoText_fragment)).setText(info_text);
        editText_frag.setText(
                String.valueOf(seekBar_frag.getProgress())
        );
    }

    public void btn_solved_onClick(Context context) {
        new EntryToCore().calcDB_forQuestions(context, seekBar_frag.getProgress());
    }


    private void editText_setOnEditorActionListener() {
        editText_frag.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == KeyEvent.KEYCODE_ENTER
                                || actionId == EditorInfo.IME_ACTION_DONE) {
                            String progress = editText_frag.getText().toString();
                            if (progress.equals("")) {
                                progress = "0";
                                editText_frag.setText("0");
                            }
                            seekBar_frag.setProgress(Integer.parseInt(progress));
                        }
                        return false;
                    }
                });
    }

    public void setTextTitle(String t) {
        title = t;
    }

    public void setInfo_text(String info_text) {
        this.info_text = info_text;
    }

    public void setMaxValueOfSeekBar_dependingOnActionType(int maxValue_seekBar) {
        this.maxValue_seekBar = maxValue_seekBar;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar_frag) {
        editText_frag.setText(String.valueOf(seekBar_frag.getProgress()));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        editText_frag.setText(String.valueOf(progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }
}