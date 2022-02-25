import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Home {

    private JLabel headerLabel;
    public JPanel calculatorView;
    public JTextField textField;
    public JButton enterButton;
    public JButton clearButton;
    public String input;
    public int answer;


    public Home() {

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input = textField.getText();
                Calculate c = new Calculate(input);
                textField.setText("");
                textField.setText(Integer.toString(c.calculate()));
            }
        });
    }

    public static void main(String args[]) {

        // load form
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Home().calculatorView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        // ADDITIONAL FUNCTIONALITY
        // storing the string operation and result into database
        // be able to search for string operations based on the results
        // ex. between 50-100
        // scroll previous calculations like a sci calculator

        // READ INPUT FROM LABEL, STORE AS STRING
        // AFTER PRESSING EQUALS.. CALL THIS ALL AS A FUNCTION
        String s;
        StringBuilder sb = new StringBuilder();
        StringBuilder sbDeci = new StringBuilder();
        Boolean b = true;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a calculation with hex/binary numbers to get a decimal result");
        s = input.next();
        // + - * / ^
        // run through whole string
        for (int i = 0; i < s.length(); i++) {
            // 4 if statements to determine if binary input
            // bounds check
            if (!(i + 3 > s.length() - 1)) {
                if (s.charAt(i) == '1' || s.charAt(i) == '0') {
                    if (s.charAt(i + 1) == '1' || s.charAt(i + 1) == '0') {
                        if (s.charAt(i + 2) == '1' || s.charAt(i + 2) == '0') {
                            if (s.charAt(i + 3) == '1' || s.charAt(i + 3) == '0') {
                                sb.append(s.charAt(i));
                                sb.append(s.charAt(i + 1));
                                sb.append(s.charAt(i + 2));
                                sb.append(s.charAt(i + 3));
                                sbDeci.append(binaryToDecimal(sb.toString()));
                                sb.setLength(0);
                                continue;
                            }
                        }
                    }
                }
            }
            if (s.charAt(i) == '+') {
                sbDeci.append("+");
            }
            if (s.charAt(i) == '-') {
                sbDeci.append("-");
            }
            if (s.charAt(i) == '*') {
                sbDeci.append("*");
            }
            if (s.charAt(i) == '/') {
                sbDeci.append("/");
            }
            if (s.charAt(i) == '^') {
                sbDeci.append("^");
            }
        }
        System.out.println(sbDeci);
        Calculate answer = new Calculate(sbDeci.toString());
        System.out.print(answer.calculate());
        //MainFrame.getAnswer(answer.calculate());
    }


    public static String binaryToDecimal(String binary) {
        int decimal = 0;
        int p = binary.length() - 1;
        char[] charArray = binary.toCharArray();
        // iterate through each character
        // if value is 1, compute decimal value
        // subtract 1 from p which holds the power values
        for (char c : charArray) {
            if (c == '1') {
                decimal += Math.pow(2, p);
                p--;
            }
            if (c == '0') {
                p--;
            }
        }
        return Integer.toString(decimal);
    }


    // return string input to calculate
    public String returnInput() {
        return input;
    }

    // set input to the answer
    public void getAnswer(int a) {
        answer = a;
    }

    // display answer to text field
    public void displayAnswer() {
        textField.setText(Integer.toString(answer));
    }
}