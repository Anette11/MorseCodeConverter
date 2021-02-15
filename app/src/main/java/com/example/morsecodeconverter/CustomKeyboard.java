package com.example.morsecodeconverter;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CustomKeyboard extends LinearLayout implements View.OnClickListener {
    private LinearLayout linear_layout_0;
    private LinearLayout linear_layout_1;
    private LinearLayout linear_layout_2;
    private LinearLayout linear_layout_3;
    private LinearLayout linear_layout_4;
    private LinearLayout linear_layout_5;
    private LinearLayout linear_layout_6;
    private LinearLayout linear_layout_7;
    private LinearLayout linear_layout_8;
    private LinearLayout linear_layout_9;
    private LinearLayout linear_layout_q;
    private LinearLayout linear_layout_w;
    private LinearLayout linear_layout_e;
    private LinearLayout linear_layout_r;
    private LinearLayout linear_layout_t;
    private LinearLayout linear_layout_y;
    private LinearLayout linear_layout_u;
    private LinearLayout linear_layout_i;
    private LinearLayout linear_layout_o;
    private LinearLayout linear_layout_p;
    private LinearLayout linear_layout_a;
    private LinearLayout linear_layout_s;
    private LinearLayout linear_layout_d;
    private LinearLayout linear_layout_f;
    private LinearLayout linear_layout_g;
    private LinearLayout linear_layout_h;
    private LinearLayout linear_layout_j;
    private LinearLayout linear_layout_k;
    private LinearLayout linear_layout_l;
    private LinearLayout linear_layout_z;
    private LinearLayout linear_layout_x;
    private LinearLayout linear_layout_c;
    private LinearLayout linear_layout_v;
    private LinearLayout linear_layout_b;
    private LinearLayout linear_layout_n;
    private LinearLayout linear_layout_m;
    private LinearLayout linear_layout_all_caps;
    private LinearLayout linear_layout_period;
    private LinearLayout linear_layout_comma;
    private LinearLayout linear_layout_question_mark;
    private LinearLayout linear_layout_apostrophe;
    private LinearLayout linear_layout_exclamation_mark;
    private LinearLayout linear_layout_slash;
    private LinearLayout linear_layout_parenthesis_open;
    private LinearLayout linear_layout_parenthesis_close;
    private LinearLayout linear_layout_ampersand;
    private LinearLayout linear_layout_colon;
    private LinearLayout linear_layout_space;
    private LinearLayout linear_layout_semicolon;
    private LinearLayout linear_layout_equals_sign;
    private LinearLayout linear_layout_plus_sign;
    private LinearLayout linear_layout_minus_sign;
    private LinearLayout linear_layout_underscore;
    private LinearLayout linear_layout_quotation_mark;
    private LinearLayout linear_layout_dollar_sign;
    private LinearLayout linear_layout_sos;
    private LinearLayout linear_layout_backspace;
    private LinearLayout linear_layout_at_sign;
    private TextView text_view_q;
    private TextView text_view_w;
    private TextView text_view_e;
    private TextView text_view_r;
    private TextView text_view_t;
    private TextView text_view_y;
    private TextView text_view_u;
    private TextView text_view_i;
    private TextView text_view_o;
    private TextView text_view_p;
    private TextView text_view_a;
    private TextView text_view_s;
    private TextView text_view_d;
    private TextView text_view_f;
    private TextView text_view_g;
    private TextView text_view_h;
    private TextView text_view_j;
    private TextView text_view_k;
    private TextView text_view_l;
    private TextView text_view_z;
    private TextView text_view_x;
    private TextView text_view_c;
    private TextView text_view_v;
    private TextView text_view_b;
    private TextView text_view_n;
    private TextView text_view_m;
    private final SparseArray<String> sparseArray = new SparseArray<>();
    private InputConnection inputConnection;
    private boolean isTextAllCapsSettingApplied;

    public CustomKeyboard(Context context) {
        super(context);
    }

    public CustomKeyboard(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomKeyboard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    @Override
    public void onClick(View v) {
        if (inputConnection != null) {
            if (checkIfTextViewIsALetter(v)) {
                if (isTextAllCapsSettingApplied) {
                    inputConnection.commitText(
                            sparseArray.get(v.getId()).toUpperCase(), 1);
                } else {
                    inputConnection.commitText(
                            sparseArray.get(v.getId()).toLowerCase(), 1);
                }
            } else {
                String value = sparseArray.get(v.getId());
                inputConnection.commitText(value, 1);
            }
        }
    }

    private boolean checkIfTextViewIsALetter(View view) {
        boolean isTextViewALetter;
        isTextViewALetter = view == linear_layout_q ||
                view == linear_layout_w ||
                view == linear_layout_e ||
                view == linear_layout_r ||
                view == linear_layout_t ||
                view == linear_layout_y ||
                view == linear_layout_u ||
                view == linear_layout_i ||
                view == linear_layout_o ||
                view == linear_layout_p ||
                view == linear_layout_a ||
                view == linear_layout_s ||
                view == linear_layout_d ||
                view == linear_layout_f ||
                view == linear_layout_g ||
                view == linear_layout_h ||
                view == linear_layout_j ||
                view == linear_layout_k ||
                view == linear_layout_l ||
                view == linear_layout_z ||
                view == linear_layout_x ||
                view == linear_layout_c ||
                view == linear_layout_v ||
                view == linear_layout_b ||
                view == linear_layout_n ||
                view == linear_layout_m;
        return isTextViewALetter;
    }

    private void initialize(Context context) {
        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true);
        isTextAllCapsSettingApplied = false;
        linearLayoutFindViewById();
        textViewFindViewById();
        linearLayoutSetOnClickListener();
        populateSparseArray();
    }

    private void textViewFindViewById() {
        text_view_q = findViewById(R.id.text_view_q);
        text_view_w = findViewById(R.id.text_view_w);
        text_view_e = findViewById(R.id.text_view_e);
        text_view_r = findViewById(R.id.text_view_r);
        text_view_t = findViewById(R.id.text_view_t);
        text_view_y = findViewById(R.id.text_view_y);
        text_view_u = findViewById(R.id.text_view_u);
        text_view_i = findViewById(R.id.text_view_i);
        text_view_o = findViewById(R.id.text_view_o);
        text_view_p = findViewById(R.id.text_view_p);
        text_view_a = findViewById(R.id.text_view_a);
        text_view_s = findViewById(R.id.text_view_s);
        text_view_d = findViewById(R.id.text_view_d);
        text_view_f = findViewById(R.id.text_view_f);
        text_view_g = findViewById(R.id.text_view_g);
        text_view_h = findViewById(R.id.text_view_h);
        text_view_j = findViewById(R.id.text_view_j);
        text_view_k = findViewById(R.id.text_view_k);
        text_view_l = findViewById(R.id.text_view_l);
        text_view_z = findViewById(R.id.text_view_z);
        text_view_x = findViewById(R.id.text_view_x);
        text_view_c = findViewById(R.id.text_view_c);
        text_view_v = findViewById(R.id.text_view_v);
        text_view_b = findViewById(R.id.text_view_b);
        text_view_n = findViewById(R.id.text_view_n);
        text_view_m = findViewById(R.id.text_view_m);
    }

    private void linearLayoutFindViewById() {
        linear_layout_0 = findViewById(R.id.linear_layout_0);
        linear_layout_1 = findViewById(R.id.linear_layout_1);
        linear_layout_2 = findViewById(R.id.linear_layout_2);
        linear_layout_3 = findViewById(R.id.linear_layout_3);
        linear_layout_4 = findViewById(R.id.linear_layout_4);
        linear_layout_5 = findViewById(R.id.linear_layout_5);
        linear_layout_6 = findViewById(R.id.linear_layout_6);
        linear_layout_7 = findViewById(R.id.linear_layout_7);
        linear_layout_8 = findViewById(R.id.linear_layout_8);
        linear_layout_9 = findViewById(R.id.linear_layout_9);
        linear_layout_q = findViewById(R.id.linear_layout_q);
        linear_layout_w = findViewById(R.id.linear_layout_w);
        linear_layout_e = findViewById(R.id.linear_layout_e);
        linear_layout_r = findViewById(R.id.linear_layout_r);
        linear_layout_t = findViewById(R.id.linear_layout_t);
        linear_layout_y = findViewById(R.id.linear_layout_y);
        linear_layout_u = findViewById(R.id.linear_layout_u);
        linear_layout_i = findViewById(R.id.linear_layout_i);
        linear_layout_o = findViewById(R.id.linear_layout_o);
        linear_layout_p = findViewById(R.id.linear_layout_p);
        linear_layout_a = findViewById(R.id.linear_layout_a);
        linear_layout_s = findViewById(R.id.linear_layout_s);
        linear_layout_d = findViewById(R.id.linear_layout_d);
        linear_layout_f = findViewById(R.id.linear_layout_f);
        linear_layout_g = findViewById(R.id.linear_layout_g);
        linear_layout_h = findViewById(R.id.linear_layout_h);
        linear_layout_j = findViewById(R.id.linear_layout_j);
        linear_layout_k = findViewById(R.id.linear_layout_k);
        linear_layout_l = findViewById(R.id.linear_layout_l);
        linear_layout_z = findViewById(R.id.linear_layout_z);
        linear_layout_x = findViewById(R.id.linear_layout_x);
        linear_layout_c = findViewById(R.id.linear_layout_c);
        linear_layout_v = findViewById(R.id.linear_layout_v);
        linear_layout_b = findViewById(R.id.linear_layout_b);
        linear_layout_n = findViewById(R.id.linear_layout_n);
        linear_layout_m = findViewById(R.id.linear_layout_m);
        linear_layout_period = findViewById(R.id.linear_layout_period);
        linear_layout_comma = findViewById(R.id.linear_layout_comma);
        linear_layout_all_caps = findViewById(R.id.linear_layout_all_caps);
        linear_layout_question_mark = findViewById(R.id.linear_layout_question_mark);
        linear_layout_apostrophe = findViewById(R.id.linear_layout_apostrophe);
        linear_layout_exclamation_mark = findViewById(R.id.linear_layout_exclamation_mark);
        linear_layout_slash = findViewById(R.id.linear_layout_slash);
        linear_layout_parenthesis_open = findViewById(R.id.linear_layout_parenthesis_open);
        linear_layout_parenthesis_close = findViewById(R.id.linear_layout_parenthesis_close);
        linear_layout_ampersand = findViewById(R.id.linear_layout_ampersand);
        linear_layout_colon = findViewById(R.id.linear_layout_colon);
        linear_layout_space = findViewById(R.id.linear_layout_space);
        linear_layout_semicolon = findViewById(R.id.linear_layout_semicolon);
        linear_layout_equals_sign = findViewById(R.id.linear_layout_equals_sign);
        linear_layout_plus_sign = findViewById(R.id.linear_layout_plus_sign);
        linear_layout_minus_sign = findViewById(R.id.linear_layout_minus_sign);
        linear_layout_underscore = findViewById(R.id.linear_layout_underscore);
        linear_layout_quotation_mark = findViewById(R.id.linear_layout_quotation_mark);
        linear_layout_dollar_sign = findViewById(R.id.linear_layout_dollar_sign);
        linear_layout_backspace = findViewById(R.id.linear_layout_backspace);
        linear_layout_at_sign = findViewById(R.id.linear_layout_at_sign);
        linear_layout_sos = findViewById(R.id.linear_layout_sos);
    }

    private void linearLayoutSetOnClickListener() {
        linear_layout_0.setOnClickListener(this);
        linear_layout_1.setOnClickListener(this);
        linear_layout_2.setOnClickListener(this);
        linear_layout_3.setOnClickListener(this);
        linear_layout_4.setOnClickListener(this);
        linear_layout_5.setOnClickListener(this);
        linear_layout_6.setOnClickListener(this);
        linear_layout_7.setOnClickListener(this);
        linear_layout_8.setOnClickListener(this);
        linear_layout_9.setOnClickListener(this);
        linear_layout_q.setOnClickListener(this);
        linear_layout_w.setOnClickListener(this);
        linear_layout_e.setOnClickListener(this);
        linear_layout_r.setOnClickListener(this);
        linear_layout_t.setOnClickListener(this);
        linear_layout_y.setOnClickListener(this);
        linear_layout_u.setOnClickListener(this);
        linear_layout_i.setOnClickListener(this);
        linear_layout_o.setOnClickListener(this);
        linear_layout_p.setOnClickListener(this);
        linear_layout_a.setOnClickListener(this);
        linear_layout_s.setOnClickListener(this);
        linear_layout_d.setOnClickListener(this);
        linear_layout_f.setOnClickListener(this);
        linear_layout_g.setOnClickListener(this);
        linear_layout_h.setOnClickListener(this);
        linear_layout_j.setOnClickListener(this);
        linear_layout_k.setOnClickListener(this);
        linear_layout_l.setOnClickListener(this);
        linear_layout_z.setOnClickListener(this);
        linear_layout_x.setOnClickListener(this);
        linear_layout_c.setOnClickListener(this);
        linear_layout_v.setOnClickListener(this);
        linear_layout_b.setOnClickListener(this);
        linear_layout_n.setOnClickListener(this);
        linear_layout_m.setOnClickListener(this);
        linear_layout_period.setOnClickListener(this);
        linear_layout_comma.setOnClickListener(this);
        linear_layout_question_mark.setOnClickListener(this);
        linear_layout_apostrophe.setOnClickListener(this);
        linear_layout_exclamation_mark.setOnClickListener(this);
        linear_layout_slash.setOnClickListener(this);
        linear_layout_parenthesis_open.setOnClickListener(this);
        linear_layout_parenthesis_close.setOnClickListener(this);
        linear_layout_ampersand.setOnClickListener(this);
        linear_layout_colon.setOnClickListener(this);
        linear_layout_space.setOnClickListener(this);
        linear_layout_semicolon.setOnClickListener(this);
        linear_layout_equals_sign.setOnClickListener(this);
        linear_layout_plus_sign.setOnClickListener(this);
        linear_layout_minus_sign.setOnClickListener(this);
        linear_layout_underscore.setOnClickListener(this);
        linear_layout_quotation_mark.setOnClickListener(this);
        linear_layout_dollar_sign.setOnClickListener(this);
        linear_layout_backspace.setOnClickListener(this);
        linear_layout_at_sign.setOnClickListener(this);
        linearLayoutAllCapsSetOnClickListener();
        linearLayoutBackSpaceSetOnClickListener();
        linearLayoutSosSetOnClickListener();
    }

    private void linearLayoutSosSetOnClickListener() {
        linear_layout_sos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                inputConnection.commitText(getResources().getString(R.string.sos), 1);
            }
        });
    }

    private void linearLayoutBackSpaceSetOnClickListener() {
        linear_layout_backspace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence charSequence = inputConnection.getSelectedText(0);
                if (TextUtils.isEmpty(charSequence)) {
                    inputConnection.deleteSurroundingText(1, 0);
                } else {
                    inputConnection.commitText(getResources().getString(R.string.empty), 1);
                }
            }
        });
    }

    private void linearLayoutAllCapsSetOnClickListener() {
        linear_layout_all_caps.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTextViewSetAllCaps(!isTextAllCapsSettingApplied);
            }
        });
    }

    private void changeTextViewSetAllCaps(boolean b) {
        text_view_q.setAllCaps(b);
        text_view_w.setAllCaps(b);
        text_view_e.setAllCaps(b);
        text_view_r.setAllCaps(b);
        text_view_t.setAllCaps(b);
        text_view_y.setAllCaps(b);
        text_view_u.setAllCaps(b);
        text_view_i.setAllCaps(b);
        text_view_o.setAllCaps(b);
        text_view_p.setAllCaps(b);
        text_view_a.setAllCaps(b);
        text_view_s.setAllCaps(b);
        text_view_d.setAllCaps(b);
        text_view_f.setAllCaps(b);
        text_view_g.setAllCaps(b);
        text_view_h.setAllCaps(b);
        text_view_j.setAllCaps(b);
        text_view_k.setAllCaps(b);
        text_view_l.setAllCaps(b);
        text_view_z.setAllCaps(b);
        text_view_x.setAllCaps(b);
        text_view_c.setAllCaps(b);
        text_view_v.setAllCaps(b);
        text_view_b.setAllCaps(b);
        text_view_n.setAllCaps(b);
        text_view_m.setAllCaps(b);
        isTextAllCapsSettingApplied = b;
    }

    private void populateSparseArray() {
        sparseArray.put(R.id.linear_layout_0, getResources().getString(R.string._0));
        sparseArray.put(R.id.linear_layout_1, getResources().getString(R.string._1));
        sparseArray.put(R.id.linear_layout_2, getResources().getString(R.string._2));
        sparseArray.put(R.id.linear_layout_3, getResources().getString(R.string._3));
        sparseArray.put(R.id.linear_layout_4, getResources().getString(R.string._4));
        sparseArray.put(R.id.linear_layout_5, getResources().getString(R.string._5));
        sparseArray.put(R.id.linear_layout_6, getResources().getString(R.string._6));
        sparseArray.put(R.id.linear_layout_7, getResources().getString(R.string._7));
        sparseArray.put(R.id.linear_layout_8, getResources().getString(R.string._8));
        sparseArray.put(R.id.linear_layout_9, getResources().getString(R.string._9));
        sparseArray.put(R.id.linear_layout_q, getResources().getString(R.string.q));
        sparseArray.put(R.id.linear_layout_w, getResources().getString(R.string.w));
        sparseArray.put(R.id.linear_layout_e, getResources().getString(R.string.e));
        sparseArray.put(R.id.linear_layout_r, getResources().getString(R.string.r));
        sparseArray.put(R.id.linear_layout_t, getResources().getString(R.string.t));
        sparseArray.put(R.id.linear_layout_y, getResources().getString(R.string.y));
        sparseArray.put(R.id.linear_layout_u, getResources().getString(R.string.u));
        sparseArray.put(R.id.linear_layout_i, getResources().getString(R.string.i));
        sparseArray.put(R.id.linear_layout_o, getResources().getString(R.string.o));
        sparseArray.put(R.id.linear_layout_p, getResources().getString(R.string.p));
        sparseArray.put(R.id.linear_layout_a, getResources().getString(R.string.a));
        sparseArray.put(R.id.linear_layout_s, getResources().getString(R.string.s));
        sparseArray.put(R.id.linear_layout_d, getResources().getString(R.string.d));
        sparseArray.put(R.id.linear_layout_f, getResources().getString(R.string.f));
        sparseArray.put(R.id.linear_layout_g, getResources().getString(R.string.g));
        sparseArray.put(R.id.linear_layout_h, getResources().getString(R.string.h));
        sparseArray.put(R.id.linear_layout_j, getResources().getString(R.string.j));
        sparseArray.put(R.id.linear_layout_k, getResources().getString(R.string.k));
        sparseArray.put(R.id.linear_layout_l, getResources().getString(R.string.l));
        sparseArray.put(R.id.linear_layout_z, getResources().getString(R.string.z));
        sparseArray.put(R.id.linear_layout_x, getResources().getString(R.string.x));
        sparseArray.put(R.id.linear_layout_c, getResources().getString(R.string.c));
        sparseArray.put(R.id.linear_layout_v, getResources().getString(R.string.v));
        sparseArray.put(R.id.linear_layout_b, getResources().getString(R.string.b));
        sparseArray.put(R.id.linear_layout_n, getResources().getString(R.string.n));
        sparseArray.put(R.id.linear_layout_m, getResources().getString(R.string.m));
        sparseArray.put(R.id.linear_layout_period, getResources().getString(R.string.period));
        sparseArray.put(R.id.linear_layout_comma, getResources().getString(R.string.comma));
        sparseArray.put(R.id.linear_layout_question_mark, getResources().getString(R.string.question_mark));
        sparseArray.put(R.id.linear_layout_apostrophe, getResources().getString(R.string.apostrophe));
        sparseArray.put(R.id.linear_layout_exclamation_mark, getResources().getString(R.string.exclamation_mark));
        sparseArray.put(R.id.linear_layout_slash, getResources().getString(R.string.slash));
        sparseArray.put(R.id.linear_layout_parenthesis_open, getResources().getString(R.string.parenthesis_open));
        sparseArray.put(R.id.linear_layout_parenthesis_close, getResources().getString(R.string.parenthesis_close));
        sparseArray.put(R.id.linear_layout_ampersand, getResources().getString(R.string.ampersand));
        sparseArray.put(R.id.linear_layout_colon, getResources().getString(R.string.colon));
        sparseArray.put(R.id.linear_layout_semicolon, getResources().getString(R.string.semicolon));
        sparseArray.put(R.id.linear_layout_equals_sign, getResources().getString(R.string.equals_sign));
        sparseArray.put(R.id.linear_layout_plus_sign, getResources().getString(R.string.plus_sign));
        sparseArray.put(R.id.linear_layout_minus_sign, getResources().getString(R.string.minus_sign));
        sparseArray.put(R.id.linear_layout_underscore, getResources().getString(R.string.underscore));
        sparseArray.put(R.id.linear_layout_quotation_mark, getResources().getString(R.string.quotation_mark));
        sparseArray.put(R.id.linear_layout_dollar_sign, getResources().getString(R.string.dollar_sign));
        sparseArray.put(R.id.linear_layout_at_sign, getResources().getString(R.string.at_sign));
        sparseArray.put(R.id.linear_layout_space, getResources().getString(R.string.space));
    }

    public void setInputConnection(InputConnection inputConnection) {
        this.inputConnection = inputConnection;
    }
}
