import java.io.IOException;
import java.util.Scanner;

public class Main {
    static char action;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input:");
        String inputStr = scanner.nextLine();

        while (calc(inputStr) != null) {
            System.out.println("Output:");
            System.out.println(calc(inputStr));
            System.out.println("");
            System.out.println("Input:");
            inputStr = scanner.nextLine();
        }
        scanner.close();
    }

    //Метод принимает введенную строку и возвращает строку с ответом
    public static String calc(String input) {
        String result = null;
        String operand [];
        try {
            operand = splitString(testInputString(input));
            result = numberAction(operand[0].trim(),operand[1].trim());


        } catch (Exception e) {
            System.out.println("Output:");
            System.out.println(e.getMessage());



        }

        return result;
    }

    // Метод проверяет нет ли в начале и конце строки спец символов
    public static String testInputString(String str) throws Exception{
        char firstChar = str.charAt(0);
        char lastChar = str.charAt(str.length() - 1);
        if (!"-+/*".contains(String.valueOf(firstChar)) && !"-+/*".contains(String.valueOf(lastChar))) {
            return str;
        } else {
            throw new Exception();
        }
    }

    //Метод возвращает массив из двух строк
    public static String [] splitString(String str) throws Exception {
        String[] operand;
        if (str.contains("+")) {
            operand = str.split("\\+");
            if (operand.length != 2) throw new Exception();
            else {
                action = '+';
            }
        } else if (str.contains("-")) {
            operand = str.split("\\-");
            if (operand.length != 2) throw new Exception();
            else {
                action = '-';
            }
        } else if (str.contains("*")) {
            operand = str.split("\\*");
            if (operand.length != 2) throw new Exception();
            else {
                action = '*';
            }
        } else if (str.contains("/")) {
            operand = str.split("\\/");
            if (operand.length != 2) throw new Exception();
            else {
                action = '/';
            }
        } else {
            throw new Exception();
        }

        return operand;
    }

    //Класс с операндами принадлежащими римской или арабской системе.
    static class Operand{
        Number num1, num2;
        static class Number{
            int num;
            RomNumerals rNum;
        }

        Operand(){
            num1 = new Number();
            num2 = new Number();
        }
        //Метод сложения арабских чисел
        String arabArithmeticOperation() throws Exception {
            String result;
            if (((num1.num > 0) && (num1.num<11)) && ((num2.num > 0) && (num2.num<11))) {
                switch (action) {
                    case '+':
                        return String.valueOf(num1.num + num2.num);
                    case '-':
                        return String.valueOf(num1.num - num2.num);
                    case '*':
                        return String.valueOf(num1.num * num2.num);
                    case '/':
                        return String.valueOf(num1.num / num2.num);
                    default:
                        return "";
                }
            } else {
                throw new Exception();
            }
        }

        //Метод сложения римских чисел
        String romArithmeticOperation() throws Exception {
            String result;
            //if (((num1.rNum.getArabNumerals() > 0) && (num1.rNum.getArabNumerals()<11)) && ((num2.rNum.getArabNumerals() > 0) && (num2.rNum.getArabNumerals()<11))) {
                switch (action) {
                    case '+':
                        //return String.valueOf(num1.rNum.getArabNumerals() + num2.rNum.getArabNumerals());
                        return arabToRom(num1.rNum.getArabNumerals() + num2.rNum.getArabNumerals());
                    case '-':
                        return arabToRom(num1.rNum.getArabNumerals() - num2.rNum.getArabNumerals());
                    case '*':
                        return arabToRom(num1.rNum.getArabNumerals() * num2.rNum.getArabNumerals());
                    case '/':
                        return arabToRom(num1.rNum.getArabNumerals() / num2.rNum.getArabNumerals());
                    default:
                        return "";
                }
        }

        //Метод перевода арабских чисел в римские
        //Логику с массивами подсмотрел, метод написал сам
        String arabToRom(int num){
            String romNum ="";
            String [] rom1Dec = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
            String [] rom2Dec = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C" };
            int arab2Dec = num/10;
            int arab1Dec = num%10;
            romNum = rom2Dec[arab2Dec]+rom1Dec[arab1Dec];
            return romNum;
        }

    }

    //Метод анализа операндов и действия с ними
    public static String numberAction (String num1, String num2) throws Exception {
        Operand operand = new Operand();
        String result = null;
        try {
            operand.num1.rNum = RomNumerals.valueOf(num1);
            operand.num2.rNum = RomNumerals.valueOf(num2);
            result = operand.romArithmeticOperation();
        } catch (Exception e){
            try {
                operand.num1.num = Integer.parseInt(num1);
                operand.num2.num = Integer.parseInt(num2);
                result = operand.arabArithmeticOperation();
            }catch (Exception e1){
              throw new Exception();
            }
        }

        return result;
    }

//Перечисление с римскими цифрами
    enum RomNumerals{
        I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10);
        private int arabNumerals;
        private RomNumerals(int arabNumerals){
            this.arabNumerals = arabNumerals;
        }
        public String getRomNumerals(int arabNum){
            switch (arabNum){
                case 1:
                    return RomNumerals.I.name();
                case 2:
                    return RomNumerals.II.name();
                case 3:
                    return RomNumerals.III.name();
                case 4:
                    return RomNumerals.IV.name();
                case 5:
                    return RomNumerals.V.name();
                case 6:
                    return RomNumerals.VI.name();
                case 7:
                    return RomNumerals.VII.name();
                case 8:
                    return RomNumerals.VIII.name();
                case 9:
                    return RomNumerals.IX.name();
                case 10:
                    return RomNumerals.X.name();
                default:
                    return "";
            }

        }
        public int getArabNumerals(){
            return arabNumerals;
        }
    }
}
