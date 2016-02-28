package com.example.saito.prototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Human[] buttersClone;
    private HumanSecond[] kyleClone;
    private TextView textMessageButters;
    private TextView textMessageKyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonButters = (Button)findViewById(R.id.button_butters);
        buttonButters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCloneButters();
            }
        });

        Button buttonKyle = (Button)findViewById(R.id.button_kyle);
        buttonKyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCloneKyle();
            }
        });

        textMessageButters = (TextView)findViewById(R.id.text_message_butters);
        Button buttonhiButters = (Button)findViewById(R.id.button_hi_butters);
        buttonhiButters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "";
                int cnt = 1;
                if (buttersClone == null) {
                    message = "Don't createClone...";
                }
                else {
                    for (Human butters : buttersClone) {
                        message += butters.getMessage() + "  CLONE：" + cnt++ + "\n";
                    }
                }
                textMessageButters.setText(message);
            }
        });

        textMessageKyle = (TextView)findViewById(R.id.text_message_kyle);
        Button buttonhiKyle = (Button)findViewById(R.id.button_hi_kyle);
        buttonhiKyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "";
                int cnt = 1;
                if (kyleClone == null) {
                    message = "Don't createClone...";
                }
                else {
                    for (HumanSecond kyle : kyleClone) {
                        message += kyle.getMessage() + "  CLONE：" + cnt++ + "\n";
                    }
                }

                textMessageKyle.setText(message);
            }
        });
    }


    private int cloneCount = 100;

    private void makeCloneButters() {

        // バターズをクローン製造機１でクローン
        Human butters = new Human("butters", "men");
        buttersClone = new Human[cloneCount];

        for (int i = 0; i < cloneCount; i++) {
            // クローンしたプロトタイプ（バターズ）を人型にキャスト
            buttersClone[i] = (Human)butters.createClone();
        }
        Toast.makeText(getApplicationContext(), "makeCloneButters Complete!", Toast.LENGTH_SHORT).show();
    }

    private void makeCloneKyle() {

        HumanSecond kyle = new HumanSecond("kyle", "men");
        kyleClone = new HumanSecond[cloneCount];
        for (int i = 0; i < cloneCount; i++) {
            // クローンしたプロトタイプ（カイル）を人型にキャスト
            kyleClone[i] = (HumanSecond)kyle.createClone();
        }
        Toast.makeText(getApplicationContext(), "makeCloneKyle Complete!", Toast.LENGTH_SHORT).show();
    }



    public abstract class ProtoType implements Cloneable{
        public abstract ProtoType createClone();
    }

    public interface Cloneable {
       public Cloneable createClone();
    }

    /***
     * クローン人間製造機１
     */
    public class Human extends ProtoType {

        private String name;
        private String sex;
        private String message = "Hi! My name is ";

        public String getMessage() {
            return message + name;
        }

        public Human() {}
        public Human(String name, String sex) {
            this.name = name;
            this.sex = sex;
        }

        @Override
        public ProtoType createClone() {

            Human cloneHuman = new Human();
            cloneHuman.name = this.name;
            cloneHuman.sex = this.sex;

            return cloneHuman;
        }
    }

    /***
     * クローン人間製造機２
     */
    public class HumanSecond implements Cloneable {

        private String name;
        private String sex;
        private String message = "Hi! My name is ";

        public String getMessage() {
            return message + name;
        }

        public HumanSecond() {}
        public HumanSecond(String name, String sex) {
            this.name = name;
            this.sex = sex;
        }

        @Override
        public Cloneable createClone() {

            HumanSecond cloneHuman = new HumanSecond();
            // クローン人間作成
            cloneHuman.name = this.name;
            cloneHuman.sex = this.sex;

            return cloneHuman;
        }
    }
}
