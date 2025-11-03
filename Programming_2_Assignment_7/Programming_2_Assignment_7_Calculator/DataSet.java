import java.util.Scanner;

public class DataSet extends SingleDataSet{
    private double num2;

    public DataSet(Scanner scanner){
        super(scanner);
        readNum2();
    }

    public DataSet(double number1, double number2, char newOperation){
        super(number1, newOperation);
        num2 = number2;
    }

    @Override
    protected void readOperation() {
        System.out.print("Enter the operation:");
        operation = scan.next().charAt(0);
    }

    private void readNum2(){
        System.out.print("Enter the second number:");
        num2 = scan.nextDouble();
    }

    public double getNum2(){
        return num2;
    }

    public boolean isOperationWrong(){
        return (operation == '/' && num2 == 0) ||
         (operation != '+' && operation != '-'  && operation != '*'  && operation != '/' && operation != '%');
    }
}