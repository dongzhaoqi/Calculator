package sky.com.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tv_print)
    TextView tvPrint;
    @Bind(R.id.btn_mr)
    Button btnMr;
    @Bind(R.id.btn_mc)
    Button btnMc;
    @Bind(R.id.btn_mplus)
    Button btnMplus;
    @Bind(R.id.btn_mminus)
    Button btnMminus;
    @Bind(R.id.btn_1)
    Button btn1;
    @Bind(R.id.btn_2)
    Button btn2;
    @Bind(R.id.btn_3)
    Button btn3;
    @Bind(R.id.btn_4)
    Button btn4;
    @Bind(R.id.btn_5)
    Button btn5;
    @Bind(R.id.btn_6)
    Button btn6;
    @Bind(R.id.btn_7)
    Button btn7;
    @Bind(R.id.btn_8)
    Button btn8;
    @Bind(R.id.btn_9)
    Button btn9;
    @Bind(R.id.btn_0)
    Button btn0;
    @Bind(R.id.btn_plus)
    Button btnPlus;
    @Bind(R.id.btn_minus)
    Button btnMinus;
    @Bind(R.id.btn_multiply)
    Button btnMultiply;
    @Bind(R.id.btn_division)
    Button btnDivision;
    @Bind(R.id.btn_c)
    Button btnC;
    @Bind(R.id.btn_equals)
    Button btnEquals;
    String str, result;
    /**
     * registerNum: The value in M register
     */
    double operand1, operand2, registerNum;
    private Operator operator;
    private boolean hasOperator = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_mr, R.id.btn_mc, R.id.btn_mplus, R.id.btn_mminus,
            R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6,
            R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_0, R.id.btn_plus,
            R.id.btn_minus, R.id.btn_multiply, R.id.btn_division, R.id.btn_c, R.id.btn_equals})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_mr:
                tvPrint.setText(MyMath.remove(String.valueOf(registerNum)));
                break;
            case R.id.btn_mc:
                registerNum = 0;
                break;
            case R.id.btn_mplus:
                if (MyMath.isNumeric(tvPrint.getText().toString())) {
                    registerNum = Double.valueOf(MyMath.add(registerNum, Double.valueOf(tvPrint.getText().toString())));
                }
                break;
            case R.id.btn_mminus:
                if (MyMath.isNumeric(tvPrint.getText().toString())) {
                    registerNum = Double.valueOf(MyMath.subtract(registerNum, Double.valueOf(tvPrint.getText().toString())));
                }
                break;
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_0:
                str = tvPrint.getText().toString();
                if (!hasOperator) {
                    if ("0".equals(str)) {
                        str = ((Button) view).getText().toString();
                    } else {
                        str += ((Button) view).getText().toString();
                    }
                    tvPrint.setText(str);
                } else {
                    tvPrint.setText(((Button) view).getText().toString());
                }
                break;
            case R.id.btn_c:
                tvPrint.setText("0");
                hasOperator = false;
                break;
            case R.id.btn_plus:
                operator = Operator.PLUS;
                hasOperator = true;
                operand1 = Double.parseDouble(tvPrint.getText().toString());
                break;
            case R.id.btn_minus:
                operator = Operator.MINUS;
                hasOperator = true;
                operand1 = Double.parseDouble(tvPrint.getText().toString());
                break;
            case R.id.btn_multiply:
                operator = Operator.MULTIPLY;
                hasOperator = true;
                operand1 = Double.parseDouble(tvPrint.getText().toString());
                break;
            case R.id.btn_division:
                operator = Operator.DIVISION;
                hasOperator = true;
                operand1 = Double.parseDouble(tvPrint.getText().toString());
                break;
            case R.id.btn_equals:
                if (hasOperator) {
                    operand2 = Double.parseDouble(tvPrint.getText().toString());
                    switch (operator) {
                        case PLUS:
                            result = MyMath.add(operand1, operand2);
                            break;
                        case MINUS:
                            result = MyMath.subtract(operand1, operand2);
                            break;
                        case MULTIPLY:
                            result = MyMath.multiply(operand1, operand2);
                            break;
                        case DIVISION:
                            result = MyMath.divide(operand1, operand2);
                            if ("error".equals(result)) {
                                result = "错误";
                            }
                            break;
                    }
                    tvPrint.setText(result);
                    if (!"错误".equals(result)) {
                        operand1 = Double.parseDouble(result);
                    }
                    hasOperator = false;
                }
                break;
        }
    }

    public enum Operator {
        PLUS, MINUS, MULTIPLY, DIVISION
    }
}
