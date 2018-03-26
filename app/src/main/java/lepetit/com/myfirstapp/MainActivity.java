package lepetit.com.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import lepetit.com.calculate.Calculate;

public class MainActivity extends AppCompatActivity {

    private TextView resultScreen;

    private String formulate;
    private String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultScreen = (TextView)findViewById(R.id.resultScreen);
        formulate = "";
        temp = "";

        initButton();
    }

    private void initButton(){
        setButtonClick((Button)findViewById(R.id.one));
        setButtonClick((Button)findViewById(R.id.two));
        setButtonClick((Button)findViewById(R.id.three));
        setButtonClick((Button)findViewById(R.id.four));
        setButtonClick((Button)findViewById(R.id.five));
        setButtonClick((Button)findViewById(R.id.six));
        setButtonClick((Button)findViewById(R.id.seven));
        setButtonClick((Button)findViewById(R.id.eight));
        setButtonClick((Button)findViewById(R.id.nine));
        setButtonClick((Button)findViewById(R.id.zero));
        setButtonClick((Button)findViewById(R.id.plus));
        setButtonClick((Button)findViewById(R.id.minus));
        setButtonClick((Button)findViewById(R.id.multiply));
        setButtonClick((Button)findViewById(R.id.divid));
        setButtonClick((Button)findViewById(R.id.point));
        setButtonClick((Button)findViewById(R.id.percent));
        setSpecialButton();
    }

    private void setButtonClick(Button button){
        final String string = button.getText().toString();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isIllegal(string)){
                    getFormulation(string);
                    resultScreen.setText(formulate);
                }
            }
        });
    }

    private void getFormulation(String string){
        if (!temp.isEmpty()){
            if (isNumber(string)){
                formulate = "";
            }
            temp = "";
        }

        if (formulate.isEmpty()){
            formulate = string;
        }
        else{
            formulate += string;
        }
    }

    private boolean isIllegal(String string){
        boolean flag = isNumber(string);

        if (!flag){
            if (formulate.isEmpty()){
                return false;
            }
            else{
                String temp = formulate.substring(formulate.length() - 1);
                if (!isNumber(temp) && !temp.equals("%")){
                  formulate = formulate.substring(0, formulate.length() - 1);
                }
            }
        }
        return true;
    }

    private boolean isNumber(String string){
        return string.charAt(0) >= '0' && string.charAt(0) <= '9';
    }

    private String stringFormat(String string){
        if (string.indexOf(".") > 0){
            string = string.replaceAll("0+?$", "");
            string = string.replaceAll("[.]$", "");
        }
        string = string.replaceAll("^0+", "");

        if (string.indexOf(".") == 0){
            string = "0" + string;
        }

        return string;
    }

    private void setSpecialButton(){
        Button equalButton = (Button)findViewById(R.id.equal);
        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!formulate.isEmpty()){
                    temp = stringFormat(new Calculate(formulate).getResult());
                    resultScreen.setText(temp);
                    formulate = temp;
                }
            }
        });

        Button clearBbutton = (Button)findViewById(R.id.clear);
        clearBbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formulate = "";
                resultScreen.setText(formulate);
            }
        });

        Button deleteButton = (Button)findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!formulate.isEmpty()){
                    formulate = formulate.substring(0, formulate.length() - 1);
                    resultScreen.setText(formulate);
                }
            }
        });
    }
}
