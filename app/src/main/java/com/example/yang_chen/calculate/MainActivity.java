package com.example.yang_chen.calculate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;

    private Button btn_sin;
    private Button btn_cos;
    private Button btn_tan;
    private Button btn_lg;
    private Button btn_ln;
    private Button btn_add;
    private Button btn_sub;
    private Button btn_mult;
    private Button btn_div;
    private Button btn_equal;
    private Button btn_clear;
    private Button btn_del;
    private Button btn_dot;

    private EditText showview;//显示
    private double answer;//显示结果；
    private boolean flag;//添加标记，每次输入运算符，再输入数字，显示应该刷新；


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maindesign);
        initView();


    }

    private void initView() {
        btn_0 = (Button) findViewById(R.id.num_0);
        btn_0.setOnClickListener(this);
        btn_1 = (Button) findViewById(R.id.num_1);
        btn_1.setOnClickListener(this);
        btn_2 = (Button) findViewById(R.id.num_2);
        btn_2.setOnClickListener(this);
        btn_3 = (Button) findViewById(R.id.num_3);
        btn_3.setOnClickListener(this);
        btn_4 = (Button) findViewById(R.id.num_4);
        btn_4.setOnClickListener(this);
        btn_5 = (Button) findViewById(R.id.num_5);
        btn_5.setOnClickListener(this);
        btn_6 = (Button) findViewById(R.id.num_6);
        btn_6.setOnClickListener(this);
        btn_7 = (Button) findViewById(R.id.num_7);
        btn_7.setOnClickListener(this);
        btn_8 = (Button) findViewById(R.id.num_8);
        btn_8.setOnClickListener(this);
        btn_9 = (Button) findViewById(R.id.num_9);
        btn_9.setOnClickListener(this);
        btn_sin = (Button) findViewById(R.id.sin);
        btn_sin.setOnClickListener(this);
        btn_cos = (Button) findViewById(R.id.cos);
        btn_cos.setOnClickListener(this);
        btn_tan = (Button) findViewById(R.id.tan);
        btn_tan.setOnClickListener(this);
        btn_lg = (Button) findViewById(R.id.lg);
        btn_lg.setOnClickListener(this);
        btn_ln = (Button) findViewById(R.id.ln);
        btn_ln.setOnClickListener(this);
        btn_add = (Button) findViewById(R.id.add);
        btn_add.setOnClickListener(this);
        btn_sub = (Button) findViewById(R.id.sub);
        btn_sub.setOnClickListener(this);
        btn_mult = (Button) findViewById(R.id.mult);
        btn_mult.setOnClickListener(this);
        btn_div = (Button) findViewById(R.id.div);
        btn_div.setOnClickListener(this);
        btn_equal = (Button) findViewById(R.id.equal);
        btn_equal.setOnClickListener(this);

        btn_del = (Button) findViewById(R.id.del);
        btn_del.setOnClickListener(this);
        btn_dot = (Button) findViewById(R.id.dot);
        btn_dot.setOnClickListener(this);
        btn_clear = (Button) findViewById(R.id.c);
        btn_clear.setOnClickListener(this);
        showview = (EditText) findViewById(R.id.showview);

    }


    public void onClick(View v) {
        //我们在这里实现业务逻辑
        String str = showview.getText().toString();
        switch (v.getId()) {
            //我们点击数字键，输入框就收到我们的计算数字
            case R.id.num_0:
            case R.id.num_1:
            case R.id.num_2:
            case R.id.num_3:
            case R.id.num_4:
            case R.id.num_5:
            case R.id.num_6:
            case R.id.num_7:
            case R.id.num_8:
            case R.id.num_9:
            case R.id.dot:
                //再次输入的时候判断，把前面的清空，再把点击的按钮放在输入框上
                if (flag) {
                    flag = false;
                    str = "";
                    showview.setText("");
                }
                //我们只要点击键盘，相应的数字添加在EditText上
                showview.setText(str + ((Button) v).getText());
                break;
            //运算符也是一样需要添加的
            case R.id.add:
            case R.id.mult:
            case R.id.sub:
            case R.id.div:
                //再次输入的时候判断，把前面的清空，再把点击的按钮放在输入框上
                if (flag) {
                    flag = false;
                    str = "";
                    showview.setText("");
                }
                //为了计算方便，我们可以在前后添加空格
                showview.setText(str + " " + ((Button) v).getText() + " ");
                break;
            case R.id.del:
                //再次输入的时候判断，把前面的清空，再把点击的按钮放在输入框上
                if (flag) {
                    flag = false;
                    str = "";
                    showview.setText("");
                } else if (str != null && !str.equals(" ")) {//判断，因为我们需要一个个删，首先确定是不是空或者""
                    //用substring()截取字符长度-1 再设置回去 形成删一个的效果
                    showview.setText(str.substring(0, str.length() - 1));
                }
                break;
            //清除按钮，直接把输入框设置成空
            case R.id.c:
                flag = false;
                str = "";
               showview.setText(" ");
                break;
            //计算结果
            case R.id.equal:
                getResult();
                break;
        }
    }

    //等号计算结果
    private void getResult() {
        //首先取一下你输入完后现在输入框的内容
        String result = showview.getText().toString();
        //当我们的输入框是null或者""时我们不进行操作
        if (result == null || result.equals(" ")) {
            return;
        }
        //我们要计算结果，必须知道输入框是否有操作符，判断条件就是前后是否在空格，否则不进行操作
        if (!result.contains(" ")) {
            //没有运算符，所以不用运算
            return;
        }
        //当我点击等号的时候，清空标识设置为true,但是如果之前有的话，设置成false
        if (flag) {
            flag = false;
            return;
        }
        flag = true;
        //如果有空格，我们就截取前后段再获取运算符进行计算
        String str1 = result.substring(0, result.indexOf(" "));  //运算符前面字段
        String op = result.substring(result.indexOf(" ") + 1, result.indexOf(" ") + 2);  //截取运算符
        String str2 = result.substring(result.indexOf(" ") + 3);  //运算符后面字段

        //这里我们还是要判断str1和str2都不是空才
        if (!str1.equals("") && !str2.equals("")) {  //第一种情况：都不是空
            //强转
            double d1 = Double.parseDouble(str1);
            double d2 = Double.parseDouble(str2);
            //开始计算，定义一个double变量接收结果，定义为全局
            if (op.equals("+")) {  //加
                answer = d1 + d2;
            } else if (op.equals("-")) { //减
                answer = d1 - d2;
            } else if (op.equals("*")) { //乘
                answer = d1 * d2;
            } else if (op.equals("/")) {  //除
                //除数为0不计算
                if (d2 == 0) {
                    answer = 0;
                } else {
                    answer = d1 / d2;
                }
            }
            //我们之前把他强转为double类型了，但是如果没有小数点，我们就是int类型
            if (!str1.contains(".") && !str2.contains(".") && !op.equals("÷")) {
                int i = (int) answer;
                showview.setText(i + "");
            } else {  //如果有小数点
                showview.setText(answer + "");
            }
        } else if (!str1.equals("") && str2.equals("")) {  //第二种情况:str2是空
            //这种情况就不运算了
            showview.setText(result);
        } else if (str1.equals("") && !str2.equals("")) {  //第三种情况:str1是空
            //这种情况我们就需要判断了，因为此时str1 = 0;
            double d3 = Double.parseDouble(str2);
            if (op.equals("+")) {  //加
                answer = 0 + d3;
            } else if (op.equals("-")) { //减
                answer = 0 - d3;
            } else if (op.equals("*")) { //乘
                answer = 0;
            } else if (op.equals("/")) {  //除
                //除数为0不计算
                answer = 0;
            }
            //我们之前把他强转为double类型了，但是如果没有小数点，我们就是int类型
            if (!str2.contains(".")) {
                int i = (int)answer;
                showview.setText(i + "");
            } else {  //如果有小数点
                showview.setText(answer + "");
            }
        } else {  //最后一种情况，他们两个都是空
            //我们清空掉
            showview.setText("");
        }
    }
}
