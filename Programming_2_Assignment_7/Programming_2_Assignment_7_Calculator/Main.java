import java.util.Scanner;
 
public class Main{ 
    public static void printResult(Calculator calculator, Scanner scan){
            System.out.print("Result:");
            System.out.println(calculator.calculate());
    }

    public static Calculator menu(Scanner scan){
        System.out.println("The following operations are available:");
        System.out.println("1) Only Sqaure Root");
        System.out.println("2) Other (+, -, *, /)");
        System.out.print("Select the type of operation:");
        int choice = scan.nextInt();
        Calculator calc;
        System.out.println();
        switch (choice){
            case (1):
            System.out.println("The following operations are available:");
            System.out.println("1) SQRT (square root)");
            calc = new Calculator(scan, true);
            return calc;

            case (2):
            System.out.println("The following operations are available:");
            System.out.println("+ (addition)");
            System.out.println("- (subtraction)");
            System.out.println("* (multiplication)");
            System.out.println("/ (division)");
        
            calc = new Calculator(scan, false);
            return calc;
            
            default:
            System.out.println("Incorrect input. Try again");    
            return menu(scan);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Calculator calculator = menu(scan);
        printResult(calculator, scan);
        
    }   
}