import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String opt = "";
        do {
            System.out.println("""
                    ======================================
                    1- Add, Update, and Delete tasks                
                    2- Mark a task as in progress or done                 
                    3- List all tasks                  
                    4- List all tasks that are done                   
                    5- List all tasks that are not done                   
                    6- List all tasks that are in progress                   
                    7- exit
                    ======================================""");
            opt = scanner.nextLine();

            switch (opt) {
                case "1" :
                    break;

                case "2" :
                    break;

                case "3" :
                    break;

                case "4" :
                    break;

                case "5" :
                    break;

                case "6" :
                    break;

                default:
                    break;

            }
        } while(!opt.equals("7"));
    }
}