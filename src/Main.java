import java.lang.Exception;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        System.out.println(Main.calc(expression));
    }
    public static String calc(String input) throws Exception {
        String[] unit = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] ten = {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};

        if (!input.matches("([IVX]+\\s[+\\-/*]\\s[IVX]+)|(\\d+\\s[+\\-/*]\\s\\d+)"))
            throw new Exception();

        int num1 = 0;
        int num2 = 0;
        int result;
        boolean rimNum = false;
        String answer = "";

        String[] paths = input.split(" ");
        String sign = paths[1];

        if (paths[0].matches("\\d+")) {
            num1 = Integer.parseInt(paths[0]);
            num2 = Integer.parseInt(paths[2]);
            if (num1 < 1 | num1 > 10 | num2 < 1 | num2 > 10) throw new Exception();
        } else {
            rimNum = true;
            for (int i = 0; i < unit.length; i++) {
                if (paths[0].equals(unit[i])) num1 = i + 1;
                if (paths[2].equals(unit[i])) num2 = i + 1;
            }
        }

        switch (sign) {
            case "+" -> result = num1 + num2;
            case "-" -> result = num1 - num2;
            case "*" -> result = num1 * num2;
            case "/" -> result = num1 / num2;
            default -> throw new Exception();
        }

        if (rimNum) {
            if (result <= 0) throw new Exception();
            if (result / 10 >= 1) answer = ten[result / 10 - 1];
            if (result % 10 >= 1) answer = answer.concat(unit[result % 10 - 1]);
        } else answer = Integer.toString(result);

        return answer;
    }
}
