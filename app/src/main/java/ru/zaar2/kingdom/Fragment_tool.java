package ru.zaar2.kingdom;

import android.content.Context;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import ru.zaar2.kingdom.core_second.bcc.TextForToast_FragmentTool;
import ru.zaar2.kingdom.core_second.bcc.bcc001_value;
import ru.zaar2.kingdom.core_second.EntryToCore;

public class Fragment_tool extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private View v;
    private String title;
//    private final boolean isOver = false;

    private String info_text;
    private int maxValue_seekBar = 0;

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
        editText_addTextChangedListener();
        seekBar_frag.setOnSeekBarChangeListener(this);
        return v;
    }

    private void initView() {
        editText_frag = (EditText) v.findViewById(R.id.valueOfSeekBar);
        seekBar_frag = (SeekBar) v.findViewById(R.id.seekBar_subsectionOfTool);
    }

    private void settingSeekBar() {
        int currentValue_seekBar_approved = 0;

        seekBar_frag.setMax(maxValue_seekBar);
        seekBar_frag.setProgress(currentValue_seekBar_approved);
    }

    private void settingView() {
        ((TextView) v.findViewById(R.id.title_fragment_tool)).setText(title);
        ((TextView) v.findViewById(R.id.infoText_fragment)).setText(info_text);
        editText_frag.setText(
                String.valueOf(seekBar_frag.getProgress())
        );
    }

    public boolean btn_solved_onClick(Context context, int question) {
        String textForToast;
        boolean result;
        if (seekBar_frag.getProgress() >= 0 && seekBar_frag.getProgress() <= maxValue_seekBar) {
            textForToast = getText_forToast(question, context);
            new EntryToCore().calcDB_forQuestions(context, seekBar_frag.getProgress());
            result = true;
        } else {
            if (seekBar_frag.getProgress() < 0) {
                seekBar_frag.setProgress(0);
                textForToast = getText_forToast(TextForToast_FragmentTool.less_than_0, context);
            }
            if (seekBar_frag.getProgress() > maxValue_seekBar)
                seekBar_frag.setProgress(maxValue_seekBar);
            textForToast = getText_forToast(TextForToast_FragmentTool.not_enough, context);
            result = false;
        }
        if (!textForToast.equals("")) {
            Toast toast = Toast.makeText(
//                    context,
                    getContext(),
                    textForToast,
                    Toast.LENGTH_LONG
            );
            TextView textView = (TextView) toast.getView().findViewById(android.R.id.message);
            if (textView != null) textView.setGravity(Gravity.CENTER);
            toast.show();
        }
        return result;
    }

    private String getText_forToast(int question, Context context) {
        String outputString = "";
        TextForToast_FragmentTool textForToast = TextForToast_FragmentTool.not_defined;
        switch (question) {
            case 0: //Сколько купим земли?
                break;
            case 1: //Сколько продадим земли?
//                break;
            case 2: //Сколько человек определим в городовой полк?
                break;
            case 3: //Сколько зерна определим каждому горожанину для прокорма на год?
                if (seekBar_frag.getProgress() < ((int) (bcc001_value.FEEDING_PER_CITIZEN_NORMAL / 8))) {
                    textForToast = TextForToast_FragmentTool.headsman;
                    break;
                }
                if (seekBar_frag.getProgress() < ((int) (bcc001_value.FEEDING_PER_CITIZEN_NORMAL / 2))) {
                    textForToast = TextForToast_FragmentTool.greedy;
                    break;
                }
                if (seekBar_frag.getProgress() > ((int) (bcc001_value.FEEDING_PER_CITIZEN_NORMAL * 2))) {
                    textForToast = TextForToast_FragmentTool.benefactor;
                    break;
                }
                break;
            case 4: //Сколько земли засеем?
//                break;
            case 5: //Сколько пошлем в набег?
//                break;
            default:
                break;
        }

        switch (textForToast) {
            case benefactor:
                outputString = context.getResources().getString(R.string.textForToast_fragment_benefactor);
                break;
            case greedy:
                outputString = context.getResources().getString(R.string.textForToast_fragment_greedy);
                break;
            case headsman:
                outputString = context.getResources().getString(R.string.textForToast_fragment_headsman);
            case not_defined:
            default:
                break;
        }
        return outputString;
    }

    private String getText_forToast(TextForToast_FragmentTool textForToast, Context context) {
        String outputString = "";
        switch (textForToast) {
            case less_than_0:
                outputString = context.getResources().getString(R.string.textForToast_fragment_lessThan0);
                break;
            case not_enough:
                outputString = context.getResources().getString(R.string.textForToast_fragment_notEnough);
                break;
            default:
                break;
        }
        return outputString;
    }

//    private void editText_setOnKeyListener() {
//        editText_frag.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                String progress = editText_frag.getText().toString();
//                int valueProgress = Integer.parseInt(progress);
//                if (valueProgress < 0) valueProgress = 0;
//                if (valueProgress > maxValue_seekBar) valueProgress = maxValue_seekBar;
//                seekBar_frag.setProgress(valueProgress);
//                progress = String.valueOf(valueProgress);
//                editText_frag.setText(progress);
//                return true;
//            }
//        });
//    }

    private void editText_addTextChangedListener() {
        editText_frag.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void afterTextChanged(Editable editable) {
                        String progress = editable.toString();
                        if (!progress.equals("")) {
                            boolean changed = false;
                            int valueProgress = Integer.parseInt(progress);
                            if (valueProgress <= maxValue_seekBar && valueProgress >= 0) {
                                seekBar_frag.setProgress(valueProgress);
                                int position = String.valueOf(valueProgress).length();
                                editText_frag.setSelection(position);
                            } else {
                                if (valueProgress < 0) {
                                    valueProgress = 0;
                                    changed = true;
                                }
                                if (valueProgress > maxValue_seekBar) {
                                    valueProgress = maxValue_seekBar;
                                    changed = true;
                                }
                            }
                            if (changed) {
                                seekBar_frag.setProgress(valueProgress);
                                progress = String.valueOf(valueProgress);
                                editText_frag.setText(progress);
                            }
                        } else {
                            seekBar_frag.setProgress(0);
                        }

                    }

                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }
                }
        );
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
                                editText_frag.setText(progress);
                                int position = progress.length();
                                editText_frag.setSelection(position);
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
        int position = editText_frag.getText().length();
        editText_frag.setSelection(position);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }
}