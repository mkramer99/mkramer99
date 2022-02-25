import java.util.ArrayList;

public class Calculate {

    String s;
    ArrayList<Integer> list = new ArrayList<>();
    StringBuilder intChars = new StringBuilder();
    ArrayList<Character> operators = new ArrayList<>();
    // int answer;

    Calculate(String ss) {
        s = ss;
    }

    public int calculate() {
        // if char is digit, add to list of ints
        // if char is not digit, add to operators list
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                intChars.append(s.charAt(i));
            }
            if (!Character.isDigit(s.charAt(i))) {
                list.add(Integer.parseInt(intChars.toString()));
                intChars.setLength(0);
                operators.add(s.charAt(i));
            }
            // add last int
            if (i == s.length() - 1) {
                list.add(Integer.parseInt(intChars.toString()));
            }
        }
        int tempValue = 0;
        //
        for (int k = 0; k < operators.size() - 1; k++) {
            if (operators.get(k) == '^') {
                tempValue = (int) Math.pow(list.get(k), list.get(k + 1));
                operators.remove(k);
                list.remove(k);
                list.remove(k + 1);
                list.add(k, tempValue);
                tempValue = 0;
            } else {
                continue;
            }
        }
        // remove nulls
        while (list.remove(null)) {
        }
        while (operators.remove(null)) {
        }
        // CALL RECURSIVE FUNC W/ INT LIST AND OPERATOR LIST
        multiply(list, operators);
        // code in order of operations
        // remove nulls
        while (list.remove(null)) {
        }
        while (operators.remove(null)) {
        }
        tempValue = list.get(0);
        for (int k = 0; k < operators.size(); k++) {
            if (operators.get(k) == '+') {
                tempValue += list.get(k + 1);
            }
            else {
                tempValue -= list.get(k + 1);
            }
        }
        return tempValue;
    }
    // recursive function for multiplication/division
    // iterates calculation left to right
    public void multiply(ArrayList<Integer> a, ArrayList<Character> b) {
        int temp = 0;
        for (int k = 0; k < b.size(); k++) {
            if (b.get(k) == '*') {
                temp = a.get(k) * a.get(k + 1);
                b.remove(k);
                a.remove(k);
                a.add(k, temp);
                a.remove(k + 1);
                temp = 0;
                multiply(a,b);
            }
            else { continue; }
        }
    }
}
