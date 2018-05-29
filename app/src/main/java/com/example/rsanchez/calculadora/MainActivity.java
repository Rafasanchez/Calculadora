package com.example.rsanchez.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView twresultado;
    Button btndel;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btndiv;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btnmul;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btnres;
    Button btnpunto;
    Button btn0;
    Button btneq;
    Button btnmas;

    /* Variables Globales */
    private String[] numbers = new String[2];
    private String Operacion = "";
    private String Resultado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btndel = findViewById(R.id.btndel);
        btndel.setOnClickListener(this);
        btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(this);
        btn8 = findViewById(R.id.btn8);
        btn8.setOnClickListener(this);
        btn9 = findViewById(R.id.btn9);
        btn9.setOnClickListener(this);
        btndiv = findViewById(R.id.btndiv);
        btndiv.setOnClickListener(this);
        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(this);
        btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(this);
        btnmul = findViewById(R.id.btnmul);
        btnmul.setOnClickListener(this);
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        btnres = findViewById(R.id.btnres);
        btnres.setOnClickListener(this);
        btnpunto = findViewById(R.id.btnpto);
        btnpunto.setOnClickListener(this);
        btn0 = findViewById(R.id.btn0);
        btn0.setOnClickListener(this);
        btneq = findViewById(R.id.btneq);
        btneq.setOnClickListener(this);
        btnmas = findViewById(R.id.btnmas);
        btnmas.setOnClickListener(this);

        twresultado = findViewById(R.id.calculator_text_view);

        Borrar();
    }

    public void AgregarDigito(String value) {
        int index;
        if (Operacion=="") index = 0;
        else index = 1;

        if (value =="." && numbers[index].contains("."))
            return;

        numbers[index] += value;

        MostrarResultado();
    }

    public void AregarOperador(String value) {
        if (numbers[1] != "") {
            Calcular(value);
            return;
        }

        Operacion = value;

        MostrarResultado();
    }

    public void Calcular(String nOperador) {
        double dresult = 0;
        double numero1 = numbers[0] == "" ? 0 : Double.parseDouble(numbers[0].toString());
        double numero2 = numbers[1] == "" ? 0 : Double.parseDouble(numbers[1].toString());

        switch (Operacion) {
            case "+":
                dresult = numero1 + numero2;
                break;
            case "-":
                dresult = numero1 - numero2;
                break;
            case "×":
                dresult = numero1 * numero2;
                break;
            case "÷":
                dresult = numero1 / numero2;
                break;
        }

        if (dresult != 0) {
            numbers[0] = String.valueOf(dresult);
            Operacion = "";
            numbers[1]= "";
            MostrarResultado();
        }

    }

    public void Borrar(){
        numbers[0] = numbers[1] = "";
        Operacion = "";
        MostrarResultado();
    }

    public void MostrarResultado(){
        Resultado = numbers[0] + " " + Operacion + " " + numbers[1];
        twresultado.setText(Resultado);
    }

    @Override
    public void onClick(View v) {
        Button btncalc;
        btncalc = (Button) v;
        String textobotton;

        twresultado.setText("0");

        textobotton = btncalc.getText().toString();

        if ("0123456789.".contains(textobotton)) AgregarDigito(textobotton);
        else if ("÷×+-".contains(textobotton)) AregarOperador(textobotton);
        else if ("=".equals(textobotton.toString())) Calcular(textobotton);
        else if ("Del".equals(textobotton.toString())) Borrar();
        else Borrar();
        }

}
