import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Skeleton {
    static public int indent = 1;

    static public HashMap<Object, String> names = new HashMap<>();
    static private final Scanner scanner = new Scanner(System.in);

    static private void printIndent() {
        for (int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
    }

    static public void callFunction(Object object, String functionName, Object[] params) {
        System.out.print(">");
        printIndent();

        String paramsString = "";
        if (params != null) {
            paramsString = String.join(", ", Arrays.asList(params).stream()
                    .map((param) -> {
                        if(param == null) return "[null]";
                        return String.format("[%s: %s]", names.get(param), param.getClass().getName());
                    })
                    .toList());
        }

        if (names.get(object) == null) {
            System.out.printf("[%s].%s(%s)\n", object.getClass().getName(), functionName, paramsString);
        } else {
            System.out.printf("[%s: %s].%s(%s)\n", names.get(object), object.getClass().getName(), functionName,
                    paramsString);
        }

        indent++;
    }

    static public void endFunction() {
        indent--;
        printIndent();
        System.out.println("<");
    }

    static public boolean yesNoQuestion(String message) {
        while (true) {
            System.out.print("+");
            printIndent();
            System.out.print(message + " Y/N: ");
            String input = scanner.nextLine().toLowerCase().trim();
            if (input.equals("y"))
                return true;
            if (input.equals("n"))
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
        while (true) {
            System.out.print("+");
            printIndent();
            System.out.print(message + ": ");
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {

            }
        }
    }

    public static void main(String[] args) {
        Menu.runMenu(Menu.MAIN_MENU);
    }
}