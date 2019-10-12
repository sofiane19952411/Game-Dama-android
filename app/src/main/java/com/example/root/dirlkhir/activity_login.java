package com.example.root.dirlkhir;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Display;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class activity_login extends AppCompatActivity {

    private ImageView bookIconImageView;
    private TextView bookITextView,tv_sign_up,forget_password;
    private ProgressBar loadingProgressBar;
    private RelativeLayout rootView, afterAnimationView,sign_up;
    private Button buttonLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        initViews();
        new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                bookITextView.setVisibility(GONE);
                loadingProgressBar.setVisibility(GONE);



            }

            @Override
            public void onFinish() {
                bookIconImageView.setImageResource(R.mipmap.lo3);
                rootView.setBackgroundColor(ContextCompat.getColor(activity_login.this, R.color.colorSplashText));
                startAnimation();
            }
        }.start();
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity_login.this,MainActivity.class);
                startActivity(intent);
            }
        });


        customTextView(tv_sign_up);
        customTextView(forget_password);
    }


    private void visible_signUp(){
        Toast.makeText(getApplicationContext(),"انشاء حساب جيد",Toast.LENGTH_SHORT).show();
        afterAnimationView.setVisibility(View.INVISIBLE);
        sign_up.setVisibility(View.VISIBLE);
    }
    private void invisible_signUp(){
        afterAnimationView.setVisibility(View.VISIBLE);
        sign_up.setVisibility(View.INVISIBLE);
    }
    private void initViews() {
        bookIconImageView = findViewById(R.id.bookIconImageView);
        bookITextView = findViewById(R.id.bookITextView);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);
        rootView = findViewById(R.id.rootView);
        afterAnimationView = findViewById(R.id.afterAnimationView);
        buttonLogin = findViewById(R.id.loginButton);
        tv_sign_up = findViewById(R.id.tv_sign_up);
        forget_password = findViewById(R.id.forget_password);
        sign_up = findViewById(R.id.sign_up);
    }

    private void customTextView(final TextView view) {
        SpannableStringBuilder spanTxt = new SpannableStringBuilder(
                "");

        spanTxt.append(view.getText());
        spanTxt.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                try {


                    if (view.getId()==R.id.tv_sign_up){
                        visible_signUp();
                    }

                    view.setBackgroundColor(Color.TRANSPARENT);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        }, spanTxt.length() - view.getText().length(), spanTxt.length(), 0);
        view.setMovementMethod(LinkMovementMethod.getInstance());
        view.setText(spanTxt, TextView.BufferType.SPANNABLE);

    }
    private void startAnimation() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x-bookIconImageView.getWidth()-40;
        int height = 70;
        ViewPropertyAnimator viewPropertyAnimator = bookIconImageView.animate();
        viewPropertyAnimator.x(width);
        viewPropertyAnimator.y(height);
        viewPropertyAnimator.setDuration(1000);
        viewPropertyAnimator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                afterAnimationView.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }
    @Override
    public void onBackPressed() {
        if (sign_up.getVisibility()==View.VISIBLE){
            invisible_signUp();
        }else {
            super.onBackPressed();
        }

    }
}
