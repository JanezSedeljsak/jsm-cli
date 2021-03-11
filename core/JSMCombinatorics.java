package jsm.core;

import java.util.HashMap;

public class JSMCombinatorics {
    public static int combinations(int n, int r) {
        HashMap<String, Number> values = new HashMap<String, Number>() {{
            put("n", n);
            put("r", r);
        }};
        return JSMEvaluate.exectute("fac(n)/(fac(r)*fac(n-r))", values);
    }

    public static void main(String[] args) {
        System.out.println(combinations(5,2));
    }
}
