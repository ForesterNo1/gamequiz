package saber.lesrodd.iqquiz;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class level1 extends AppCompatActivity {

    Dialog dialog;
    Dialog  dialogend;

    public int numleft;
    public int numright;
    Array array = new Array();
    Random random = new Random();
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //text levels
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level1);

        //левая картинка
        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        //скругление
        img_left.setClipToOutline(true);
        //правая картинка
        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        //скругление
        img_right.setClipToOutline(true);

        //развернуть игру на экран
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //dialog window
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog);//путь к макету
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);


        // кнопка закрытия
        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обработка нажатия
                try {
                    //назад к выбору уровня
                    Intent intent = new Intent(level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e) {

                }
                dialog.dismiss();
            }
        });

        //продолжить кнопка
        Button btncontinue = (Button)dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


                dialog.show();

                //__________________________________
        dialogend = new Dialog(this);
        dialogend.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogend.setContentView(R.layout.enddialog);//путь к макету
        dialogend.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogend.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                 WindowManager.LayoutParams.MATCH_PARENT);
        dialogend.setCancelable(false);


        // кнопка закрытия
        TextView btnclose2 = (TextView)dialogend.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обработка нажатия
                try {
                    //назад к выбору уровня
                    Intent intent = new Intent(level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e) {

                }
                dialogend.dismiss();
            }
        });

        //продолжить кнопка
        Button btncontinue2 = (Button)dialogend.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(level1.this, level2.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }


                dialogend.dismiss();
            }
        });




        //____________________________________________

               //назад кнопка
        Button btn_back = (Button)findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //назад к выбору уровня
                    Intent intent = new Intent(level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });

        //прогресс
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5, R.id.point6,
                R.id.point7, R.id.point8, R.id.point9, R.id.point10, R.id.point11, R.id.point12,
                R.id.point13, R.id.point14, R.id.point15, R.id.point16, R.id.point17, R.id.point18,
                R.id.point19, R.id.point20,
        };



        //анимация
        final Animation a = AnimationUtils.loadAnimation(level1.this,R.anim.alpha);

        numleft = random.nextInt(20);
        img_left.setImageResource(array.images1[numleft]);

        numright = random.nextInt(20);

        while (array.strong[numleft] == array.strong[numright]) {
            numright = random.nextInt(20);
        }

        img_right.setImageResource(array.images1[numright]);

        //обработка нажатий левая кнопка
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                img_right.setEnabled(false);
                if (array.strong[numleft]>array.strong[numright]){
                    img_left.setImageResource(R.drawable.imgtrue);
                }else{
                    img_left.setImageResource(R.drawable.imgfalse);
                }
                } else if (event.getAction() == MotionEvent.ACTION_UP){

                    if(array.strong[numleft]>array.strong[numright]){

                        if(count<20){
                        count=count+1;
                        }

                        for (int i=0; i<20; i++){
                            TextView tv =findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for(int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }else{

                        if(count>0){
                            if (count==1){
                                count=0;
                            }else{
                                count=count-2;
                            }
                        }
                        for (int i=0; i<19; i++){
                            TextView tv =findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);

                        }

                        for(int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }

                    if(count==20){
                        dialogend.show();
                    }else {
                        numleft = random.nextInt(20);
                        img_left.setImageResource(array.images1[numleft]);
                        img_left.startAnimation(a);

                        numright = random.nextInt(20);

                        while (array.strong[numleft]==array.strong[numright]);
                        numright = random.nextInt(20);

                        img_right.setImageResource(array.images1[numright]);
                        img_right.startAnimation(a);

                        img_right.setEnabled(true);
                    }

            }
                return true;
            }
        });

        //обработка нажатий правая кнопка
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_left.setEnabled(false);
                    if (array.strong[numleft]<array.strong[numright]){
                        img_right.setImageResource(R.drawable.imgtrue);
                    }else{
                        img_right.setImageResource(R.drawable.imgfalse);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP){

                    if(array.strong[numleft]<array.strong[numright]){

                        if(count<20){
                            count=count+1;
                        }

                        for (int i=0; i<20; i++){
                            TextView tv =findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for(int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }else{

                        if(count>0){
                            if (count==1){
                                count=0;
                            }else{
                                count=count-2;
                            }
                        }
                        for (int i=0; i<19; i++){
                            TextView tv =findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for(int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }

                    if(count==20){
                        dialogend.show();
                    }else {
                        numleft = random.nextInt(20);
                        img_left.setImageResource(array.images1[numleft]);
                        img_left.startAnimation(a);

                        numright = random.nextInt(20);

                        while (true) {
                            if (array.strong[numleft] != array.strong[numright]) break;
                            numright = random.nextInt(20);                         }


                        img_right.setImageResource(array.images1[numright]);
                        img_right.startAnimation(a);

                        img_left.setEnabled(true);
                    }

                }
                return true;
            }
        });

    }
    //сист кнопка
    @Override
    public void onBackPressed(){
        try {
            //назад к выбору уровня
            Intent intent = new Intent(level1.this, GameLevels.class);
            startActivity(intent);
            finish();
        }catch (Exception e){

        }
    }
}