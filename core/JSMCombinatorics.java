package core;

import java.util.HashMap;
import core.JSMEvaluate;

public class JSMCombinatorics {
    public static void combinations(final int n, final int r) {
        //JSMEvaluate smth = new JSMEvaluate();
        HashMap<String, Number> values = new HashMap<String, Number>() {{
            put("n", n);
            put("r", r);
        }};
        System.out.print(JSMEvaluate.exectute("fac(n)/(fac(r)*fac(n-r))", values));
    }

    public static void main(String[] args) {
        combinations(5,2);
    }
}
