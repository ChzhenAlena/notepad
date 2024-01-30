package controlers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleManager {
    Scanner scanner;
    public ConsoleManager(){
        scanner = new Scanner(System.in);
    }
    public void printStartMenu(){
        String menu = "1. Добавить запись\n" +
                "2. Показать все записи\n" +
                "3. Найти запись по заголовку\n" +
                "4. Найти запись по дате\n" +
                "5. Найти запись по email\n" +
                "6. Найти запись по email-у и заголовку\n" +
                "7. Найти запись, содержащую определенное слово\n" +
                "8. Завершить";
        System.out.println(menu);
    }
    public String getString(){
        String line = scanner.nextLine();
        return line;
    }
    public int getNumber(){
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }
    public String getEmail(){
        String line;
        Pattern pattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");//for RFC5322
        Matcher matcher;
        while(true) {
            line = scanner.nextLine();
            matcher = pattern.matcher(line);
            if(matcher.matches())
                return line;
            else {
                System.out.println("веденный email не соответствует стандарту RFC5322. Введите email заново");
            }
        }
    }
    public Date getDate(){
        String str;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date parsingDate = null;
        dateFormat.setLenient(false);
        while(true) {
            str = scanner.nextLine();
            if(isPossibleDate(str)) {
                try {
                    parsingDate = dateFormat.parse(str);
                    return parsingDate;
                } catch (ParseException e) {
                    System.out.println("Такой даты не существует. Введите заново: ");
                }
            }
        }
    }
    private boolean isPossibleDate(String str) {
        Pattern pattern = Pattern.compile("\\d{2}.\\d{2}.\\d{4}");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches())
            return true;
        else {
            System.out.println("Дата не соответствует требуемому формату, введите дату в формате dd.MM.yyyy - день, месяц, год");
            return false;
        }
    }


}
