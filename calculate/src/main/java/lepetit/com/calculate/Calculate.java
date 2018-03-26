package lepetit.com.calculate;

public class Calculate {
    private Stack number;
    private Stack sign;

    private String formulate;
    private String num;

    public Calculate(String formulate){
        this.formulate = formulate;
        this.number = new Stack();
        this.sign = new Stack();
        this.num = "";

        initialize();
    }

    private void initialize(){
        for (int i = 0; i < formulate.length(); i++){
            if (isNumber(formulate.charAt(i))){
                num += formulate.charAt(i);
            }
            else if (formulate.charAt(i) == '%'){
                num = dealPercent(num);
            }
            else{
                number.push(num);
                dealSign(formulate.charAt(i));
                num = "";
            }
        }
        if (!num.isEmpty()){
            number.push(num);
        }
        else{
            number.push("0");
        }
    }

    public String getResult(){
        char keyTop;
        while (!sign.isEmpty()){
            keyTop = (char)sign.getTop();
            sign.pop();

            calculate(keyTop);
        }
        return number.getTop().toString();
    }

    private void dealSign(char key){
        char temp;

        if (sign.isEmpty() || isPriority(key, (char)sign.getTop())){
            sign.push(key);
        }
        else{
            temp = (char)sign.getTop();
            sign.pop();
            sign.push(key);
            sign.push(temp);
        }
    }

    private boolean isPriority(char key, char keyTop){
        if (key == '+' || key == '-')
        {
            if (keyTop == '*' || keyTop == '/'){
                return false;
            }
        }
        return true;
    }

    private boolean isNumber(char key){
        return key >= '0' && key <= '9' || key == '.';
    }

    private String dealPercent(String num){
        Arity arity = new Arity(num);
        return arity.percent();
    }

    private void calculate(char key){
        String a = number.getTop().toString();
        number.pop();
        String b = number.getTop().toString();
        number.pop();

        Arity arity = new Arity(a, b);

        switch (key){
            case '+': number.push(arity.plus());break;
            case '-': number.push(arity.minus());break;
            case '*': number.push(arity.multiply());break;
            case '/': number.push(arity.divid());break;
        }
    }
}
