package core;

import java.util.HashMap;

public class JSMEvaluate {
    /**
     * @param  type - S(sum) | P(product) math operation for evaluation
     * @param  i - start index
     * @param  n - end index 
     * @param func - mathematical expression to be executed
    */
    public static <T extends Number> double seriesOperation(char type, final int i, int n, String... func) {
        String tmpFnc = func.length > 0 ? func[0] : null;
        double val = .0;
        HashMap<String, Number> values = new HashMap<String, Number>() {{
            put("n", n);
            put("i", i);
        }};
        if (type == 'S') for (int j = i; j <= n; j++) {
            values.put("i", j);
            val += tmpFnc != null ? exectute(tmpFnc, values) : j;
        }
        else if (type == 'P') {
            ++val;
            for (int j = i; j <= n; j++) {
                values.put("i", j);
                val *= tmpFnc != null ? exectute(tmpFnc, values) : j;
            }
        }
        else throw new RuntimeException(String.format("Invalid function called - %c", type));
        return val;
    }

    /**
     * function seriesOperations a math expression with 2 paramters (i and n)
     * 
     * @param  equation - arithmetic expression
     * @param  i - current value of I
     * @param  n - current value of N 
     * 
     * @credits https://stackoverflow.com/a/26227947
    */
    public static double exectute(final String equation, HashMap<String, Number> mappedParams) {
        String eq = equation;
        for (String key: mappedParams.keySet()) {
            eq = eq.replaceAll(key, String.valueOf(mappedParams.get(key)));
        }
        final String finalEq = eq;
        return new Object() {
            int pos = -1, ch;
    
            void nextChar() {
                ch = (++pos < finalEq.length()) ? finalEq.charAt(pos) : -1;
            }
    
            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }
    
            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < finalEq.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }
    
            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor
    
            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }
    
            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }
    
            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus
    
                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(finalEq.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = finalEq.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else if (func.equals("fac")) x = factorial(x);
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }
    
                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation
    
                return x;
            }
        }.parse();
    }

    /**
     * @param num -> num!
    */
    public static <T extends Number> double factorial(T num) {
        return seriesOperation('P', 1, num.intValue(), "i");
    }

    public static void main(String[] args) {
        double val = seriesOperation('S', 1, 10, "i*i");
        System.out.print(val);
    }
}