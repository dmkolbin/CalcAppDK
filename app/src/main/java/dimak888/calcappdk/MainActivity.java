package dimak888.calcappdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText inputs;
    Button goButton;
    TextView result;
    ArbeitenRPN schnelle = new ArbeitenRPN();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputs = (EditText) findViewById(R.id.input);
        goButton = (Button) findViewById(R.id.go_button);
        result = (TextView) findViewById(R.id.result);
        goButton.setOnClickListener(viewClickListener);
    }

    View.OnClickListener viewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String exp = inputs.getText().toString();
            if (exp.isEmpty()){
                result.setText("Поле пустое. Введите выражение.");
            }
            else {
                ArrayList<String> asPostfix;
                exp = exp.replace("\\,", "\\.");//.replaceAll("^[\\.\\^\\*\\+\\-\\d/\\s]", "");
                asPostfix = schnelle.infixToPostfix(exp);
                String postfixExp = asPostfix.toString().replaceAll(",", " ");
                postfixExp = postfixExp.substring(1, postfixExp.length() - 1);
                schnelle.parseRPN(postfixExp);
                result.setText(postfixExp + " = " + schnelle.getResult());}
            }
    };
}