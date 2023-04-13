import java.util.Scanner;

public class Skeleton {
    static public int indent = 0;
    static private final Scanner scanner = new Scanner(System.in);
    static private void printIndent() {
        for(int i=0; i<indent; i++) {
            System.out.print("\t");
        }
    }
    static public void callFunction(Object object, String variableName, String functionName) {
        System.out.print(">");
        printIndent();
        System.out.printf("[%s: %s].%s()", variableName, object.getClass().getName(), functionName);
        indent++;
    }

    static public void endFunction() {
        indent--;
        printIndent();
        System.out.println("<");
    }
    static public boolean yesNoQuestion(String message) {
        while(true) {
            System.out.print("+");
            printIndent();
            System.out.print(message + " Y/N: ");
            String input = scanner.nextLine().toLowerCase().trim();
            if(input.equals("y"))
                return true;
            if(input.equals("n"))
                return false;
        }
    }
    static public String question(String message) {
        System.out.print("+");
        printIndent();
        System.out.print(message + ": ");
        return scanner.nextLine();
    }
    static public int numberQuestion(String message) {
        while(true) {
            System.out.print("+");
            printIndent();
            System.out.print(message + ": ");
            if(scanner.hasNextInt())
                return scanner.nextInt();
        }
    }

    static public void printSequenceDiagram() {
        while(true){
            int response = numberQuestion("adja meg az utasítás számát");
            switch(response){
                case 1:
                    String letter = question("adja meg az utasítás számát");
                    break;
                case 2:

                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    continue;
            }
        }

    }
    public static void main(String[] args) {
        Skeleton s = new Skeleton();
        //kiir kerdes
        //kiir szekvencia nev
        callFunction(s, "mechanic", "PickupPipe");

    }
}