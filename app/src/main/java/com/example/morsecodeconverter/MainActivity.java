package com.example.morsecodeconverter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MorseCode morseCode;
    private ImageView imageViewClear;
    private ImageView imageViewInfo;
    private ImageView imageViewRefresh;
    private TextView textViewMorseCode;
    private EditText editTextEnglish;
    private LinearLayout linearLayoutVibration;
    private LinearLayout linearLayoutTone;
    private LinearLayout linearLayoutFlash;
    private CustomKeyboard customKeyboard;
    private String convertedText;
    private boolean cameraHasFlash;
    private boolean isTorchModeEnabled;
    private boolean isVibrationPlaying;
    private boolean isFlashPlaying;
    private boolean isTonePlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void editTextSetOnClickListener() {
        editTextEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!customKeyboard.isShown()) {
                    makeButtonsVibrationToneFlashNotClickableNotEnabled();
                    customKeyboard.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void makeButtonsVibrationToneFlashNotClickableNotEnabled() {
        linearLayoutVibration.setEnabled(false);
        linearLayoutVibration.setClickable(false);
        linearLayoutTone.setEnabled(false);
        linearLayoutTone.setClickable(false);
        linearLayoutFlash.setEnabled(false);
        linearLayoutFlash.setClickable(false);
    }

    private void makeButtonsVibrationToneFlashClickableEnabled() {
        linearLayoutVibration.setEnabled(true);
        linearLayoutVibration.setClickable(true);
        linearLayoutTone.setEnabled(true);
        linearLayoutTone.setClickable(true);
        linearLayoutFlash.setEnabled(true);
        linearLayoutFlash.setClickable(true);
    }

    private void hideSoftKeyboard() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void preventSoftKeyboardBeShownWhenEditTextIsClicked() {
        editTextEnglish.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editTextEnglish.setTextIsSelectable(true);
    }

    private void passInputConnectionFromEditTextToCustomKeyboard() {
        InputConnection inputConnection = editTextEnglish.onCreateInputConnection(new EditorInfo());
        customKeyboard.setInputConnection(inputConnection);
    }

    private void initialize() {
        hideSoftKeyboard();
        morseCode = new MorseCode(this);
        imageViewClear = findViewById(R.id.image_view_clear);
        imageViewInfo = findViewById(R.id.image_view_info);
        imageViewRefresh = findViewById(R.id.image_view_refresh);
        textViewMorseCode = findViewById(R.id.text_view_morse_code);
        editTextEnglish = findViewById(R.id.edit_text_english);
        linearLayoutVibration = findViewById(R.id.linear_layout_vibration);
        linearLayoutTone = findViewById(R.id.linear_layout_tone);
        linearLayoutFlash = findViewById(R.id.linear_layout_flash);
        customKeyboard = new CustomKeyboard(this);
        customKeyboard = findViewById(R.id.custom_keyboard);
        checkIfCameraHasFlash();
        editTextSetOnClickListener();
        preventSoftKeyboardBeShownWhenEditTextIsClicked();
        passInputConnectionFromEditTextToCustomKeyboard();
        convertedText = getString(R.string.empty);
        isTorchModeEnabled = false;
        isVibrationPlaying = false;
        isFlashPlaying = false;
        isTonePlaying = false;
        imageViewRefreshSetOnClickListener();
        imageViewClearSetOnClickListener();
        imageViewInfoSetOnClickListener();
        linearLayoutToneSetOnClickListener();
        linearLayoutVibrationSetOnClickListener();
        linearLayoutFlashSetOnClickListener();
        makeButtonsVibrationToneFlashClickableEnabled();
    }

    private void checkIfCameraHasFlash() {
        cameraHasFlash = getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    private void imageViewInfoSetOnClickListener() {
        imageViewInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogMorseCodeInfo();
            }
        });
    }

    private void showAlertDialogMorseCodeInfo() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog
                .Builder(MainActivity.this);

        View view = getLayoutInflater()
                .inflate(R.layout.alert_dialog_info_layout, null);

        alertDialogBuilder.setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        alertDialogBuilder.setView(view);
        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.getWindow().setBackgroundDrawable(
                ContextCompat.getDrawable(getApplicationContext(),
                        R.drawable.linear_layout_background_1));

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(25);
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                        .setTextColor(getResources().getColor(R.color.color_6, null));
            }
        });

        alertDialog.show();
        setAlertDialogWidthAndHeight(alertDialog);

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
    }

    private void setAlertDialogWidthAndHeight(AlertDialog alertDialog) {
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.9);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.9);
        alertDialog.getWindow().setLayout(width, height);
    }

    private void linearLayoutFlashSetOnClickListener() {
        linearLayoutFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cameraHasFlash) {
                    if (convertedText.equals(getString(R.string.empty))) {
                        showToastMessageNoMorseCodeToPlay();
                    } else {
                        setFlash();
                    }
                } else {
                    showToastMessageFlashNotAvailable();
                }
            }
        });
    }

    private void showToastMessageFlashNotAvailable() {
        Toast.makeText(
                MainActivity.this,
                getResources().getString(R.string.flash_not_available),
                Toast.LENGTH_SHORT)
                .show();
    }

    private void setFlash() {
        if (!isFlashPlaying) {
            isFlashPlaying = true;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                    if (cameraManager != null) {
                        try {
                            String cameraId = cameraManager.getCameraIdList()[0];

                            char[] charArray = convertedText.toCharArray();

                            int counter = -1;

                            for (char c : charArray) {
                                counter++;
                                if (Character.toString(c).matches(getResources().getString(R.string.period_2))
                                        & (counter != charArray.length - 1)) {
                                    isTorchModeEnabled = !isTorchModeEnabled;
                                    cameraManager.setTorchMode(cameraId, isTorchModeEnabled);
                                    try {
                                        Thread.sleep(50);
                                    } catch (InterruptedException e) {
                                        showInLogThreadSleepInterruptedException(e);
                                    }
                                    isTorchModeEnabled = !isTorchModeEnabled;
                                    cameraManager.setTorchMode(cameraId, isTorchModeEnabled);
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        showInLogThreadSleepInterruptedException(e);
                                    }
                                } else if (Character.toString(c).matches(getResources().getString(R.string.period_2))
                                        & (counter == charArray.length - 1)) {
                                    isTorchModeEnabled = !isTorchModeEnabled;
                                    cameraManager.setTorchMode(cameraId, isTorchModeEnabled);
                                    try {
                                        Thread.sleep(50);
                                    } catch (InterruptedException e) {
                                        showInLogThreadSleepInterruptedException(e);
                                    }
                                    isTorchModeEnabled = !isTorchModeEnabled;
                                    cameraManager.setTorchMode(cameraId, isTorchModeEnabled);
                                } else if (Character.toString(c).matches(getResources().getString(R.string.minus_sign_2))
                                        & (counter != charArray.length - 1)) {
                                    isTorchModeEnabled = !isTorchModeEnabled;
                                    cameraManager.setTorchMode(cameraId, isTorchModeEnabled);
                                    try {
                                        Thread.sleep(400);
                                    } catch (InterruptedException e) {
                                        showInLogThreadSleepInterruptedException(e);
                                    }
                                    isTorchModeEnabled = !isTorchModeEnabled;
                                    cameraManager.setTorchMode(cameraId, isTorchModeEnabled);
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        showInLogThreadSleepInterruptedException(e);
                                    }
                                } else if (Character.toString(c).matches(getResources().getString(R.string.minus_sign_2))
                                        & (counter == charArray.length - 1)) {
                                    isTorchModeEnabled = !isTorchModeEnabled;
                                    cameraManager.setTorchMode(cameraId, isTorchModeEnabled);
                                    try {
                                        Thread.sleep(400);
                                    } catch (InterruptedException e) {
                                        showInLogThreadSleepInterruptedException(e);
                                    }
                                    isTorchModeEnabled = !isTorchModeEnabled;
                                    cameraManager.setTorchMode(cameraId, isTorchModeEnabled);
                                } else if (Character.toString(c).matches(getResources().getString(R.string.space))) {
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        showInLogThreadSleepInterruptedException(e);
                                    }
                                }
                            }
                        } catch (CameraAccessException e) {
                            showInLogCameraAccessException(e);
                        }
                    }
                    isFlashPlaying = false;
                }
            });
            thread.start();
        } else {
            showToast(getResources().getString(R.string.flash_is_playing));
        }
    }

    private void showInLogCameraAccessException(CameraAccessException e) {
        Log.e(getString(R.string.camera_access_exception), Log.getStackTraceString(e));
    }

    private void linearLayoutVibrationSetOnClickListener() {
        linearLayoutVibration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (convertedText.equals("")) {
                    showToastMessageNoMorseCodeToPlay();
                } else {
                    setVibration();
                }
            }
        });
    }

    private void showToastMessageNoMorseCodeToPlay() {
        Toast.makeText(
                MainActivity.this,
                getResources().getString(R.string.no_morse_code_to_play),
                Toast.LENGTH_SHORT)
                .show();
    }

    private void linearLayoutToneSetOnClickListener() {
        linearLayoutTone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (convertedText.equals(getString(R.string.empty))) {
                    showToastMessageNoMorseCodeToPlay();
                } else {
                    setTone();
                }
            }
        });
    }

    private void imageViewClearSetOnClickListener() {
        imageViewClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextEnglish.setText(getString(R.string.empty));
                textViewMorseCode.setText(getString(R.string.empty));
                convertedText = getString(R.string.empty);
            }
        });
    }

    private void imageViewRefreshSetOnClickListener() {
        imageViewRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToConvertToMorseCode = editTextEnglish.getText().toString();
                convertedText = morseCode.convertSymbolsToMorseCode(
                        textToConvertToMorseCode,
                        morseCode.getHashMapSymbolsToMorseCodes());
                textViewMorseCode.setText(convertedText);
            }
        });
    }

    private void setVibration() {
        if (!isVibrationPlaying) {
            isVibrationPlaying = true;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                    if (vibrator != null) {
                        char[] charArray = convertedText.toCharArray();

                        int counter = -1;

                        for (char c : charArray) {
                            counter++;
                            if (Character.toString(c).matches(getResources().getString(R.string.period_2))
                                    & (counter != charArray.length - 1)) {
                                vibrator.vibrate(100);
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    showInLogThreadSleepInterruptedException(e);
                                }
                            } else if (Character.toString(c).matches(getResources().getString(R.string.period_2))
                                    & (counter == charArray.length - 1)) {
                                vibrator.vibrate(100);
                            } else if (Character.toString(c).matches(getResources().getString(R.string.minus_sign_2))
                                    & (counter != charArray.length - 1)) {
                                vibrator.vibrate(300);
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    showInLogThreadSleepInterruptedException(e);
                                }
                            } else if (Character.toString(c).matches(getResources().getString(R.string.minus_sign_2))
                                    & (counter == charArray.length - 1)) {
                                vibrator.vibrate(300);
                            } else if (Character.toString(c).matches(getResources().getString(R.string.space))) {
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    showInLogThreadSleepInterruptedException(e);
                                }
                            }
                        }
                    } else {
                        showToast(getResources().getString(R.string.vibration_is_not_available));
                    }
                    isVibrationPlaying = false;
                }
            });
            thread.start();
        } else {
            showToast(getResources().getString(R.string.vibration_is_playing));
        }
    }

    private void showInLogThreadSleepInterruptedException(InterruptedException e) {
        Log.e(getString(R.string.interrupted_exception), Log.getStackTraceString(e));
    }

    private void setTone() {
        if (!isTonePlaying) {
            isTonePlaying = true;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    ToneGenerator toneGenerator = new ToneGenerator(
                            AudioManager.STREAM_ALARM,
                            ToneGenerator.MAX_VOLUME
                    );

                    char[] charArray = convertedText.toCharArray();

                    int counter = -1;

                    for (char c : charArray) {
                        counter++;
                        if (Character.toString(c).matches(getResources().getString(R.string.period_2))
                                & (counter != charArray.length - 1)) {
                            toneGenerator.startTone(ToneGenerator.TONE_CDMA_MED_SS_2, 100);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                showInLogThreadSleepInterruptedException(e);
                            }
                        } else if (Character.toString(c).matches(getResources().getString(R.string.period_2))
                                & (counter == charArray.length - 1)) {
                            toneGenerator.startTone(ToneGenerator.TONE_CDMA_MED_SS_2, 100);
                        } else if (Character.toString(c).matches(getResources().getString(R.string.minus_sign_2))
                                & (counter != charArray.length - 1)) {
                            toneGenerator.startTone(ToneGenerator.TONE_CDMA_MED_SS_2, 300);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                showInLogThreadSleepInterruptedException(e);
                            }
                        } else if (Character.toString(c).matches(getResources().getString(R.string.minus_sign_2))
                                & (counter == charArray.length - 1)) {
                            toneGenerator.startTone(ToneGenerator.TONE_CDMA_MED_SS_2, 300);
                        } else if (Character.toString(c).matches(getResources().getString(R.string.space))) {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                showInLogThreadSleepInterruptedException(e);
                            }
                        }
                    }
                    toneGenerator.stopTone();
                    toneGenerator.release();
                    isTonePlaying = false;
                }
            });
            thread.start();
        } else {
            showToast(getResources().getString(R.string.tone_is_playing));
        }
    }

    private void showToast(String toastMessage) {
        Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (customKeyboard.isShown()) {
            makeButtonsVibrationToneFlashClickableEnabled();
            customKeyboard.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }
}
