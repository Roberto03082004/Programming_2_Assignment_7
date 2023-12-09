package util;

import java.util.*;

public class Calculator {
    private static Scanner scan = new Scanner(System.in);

    private static int getOperator(String exp, int cur) {
        Scanner scan = new Scanner(exp.substring(cur));
        scan.useDelimiter("[^0-9]");
        return scan.nextInt();
    }

    private static int doOperator(int op1, int op2, char operator) throws Exception {
        return switch (operator) {
            case '+' -> op1 + op2;
            case '-' -> op1 - op2;
            case '*' -> op1 * op2;
            case '/' -> op1 / op2;
            default -> throw new Exception("Unbalanced parentheses");
        };
    }

    private static int comparePrecedence(char op1, char op2) {
        if (op1 == '+' || op1 == '-')
            if (op2 == '+' || op2 == '-')
                return 0;
            else
                return -1;
        else if (op2 == '+' || op2 == '-')
            return 1;
        else
            return 0;
    }

    public static void main(String[] args) throws Exception {
        Stack<Character> operators = new Stack<Character>();
        Stack<Integer> operands = new Stack<Integer>();
        while (true) {
            System.out.println("Please enter a new expression: ");
            String exp = scan.nextLine();
            if (exp.equals("exit"))
                break;
            char prev = 0;
            for (int cur = 0; cur < exp.length(); cur++) {
                switch (exp.charAt(cur)) {
                    case '(':
                        operators.push(exp.charAt(cur));
                        break;
                    case '+':
                    case '-':
                        if (prev == 0 || prev == '(')//treat + and - as the explicit sign of an integer!
                            operands.push(0);
                    case '*':
                    case '/':
                        //Don't push +,- over +,-,*,/ & Don't push *,/ over *,/
                        while (!operators.isEmpty() && operators.peek() != '(')
                            if (comparePrecedence(operators.peek(), exp.charAt(cur)) >= 0) {
                                int op2 = operands.pop();//pop the second operand first
                                int result = doOperator(operands.pop(), op2, operators.pop());
                                operands.push(result);
                            } else
                                break;
                        operators.push(exp.charAt(cur));
                        break;
                    case ')':
                        while (!operators.isEmpty() && operators.peek() != '(') {
                            int op2 = operands.pop();
                            operands.push(doOperator(operands.pop(), op2, operators.pop()));
                        }
                        if (operators.empty())
                            throw new Exception("Unbalanced parentheses");
                        operators.pop(); //pop the matching open parenthesis
                        break;
                    case ' ':
                    case '\t'://skip the white space characters
                        break;
                    default:
                        if (Character.isDigit(exp.charAt(cur))) {
                            int temp = getOperator(exp, cur);
                            while (cur < exp.length() && Character.isDigit(exp.charAt(cur)))//skip the whole number
                                cur++;
                            cur--;
                            operands.push(temp);//push it to operand's stack
                        } else
                            throw new Exception("bad input");
                }
                if (!Character.isWhitespace(exp.charAt(cur)))
                    prev = exp.charAt(cur);
            }
            while (!operators.isEmpty()) {
                int op2 = operands.pop();
                operands.push(doOperator(operands.pop(), op2, operators.pop()));
            }
            System.out.println("The result is: " + operands.pop());
        }
    }
}