import controlers.ConsoleManager;
import models.Notepad;

/*по заданию проверку введенной информации на валидность должен осуществлять код,
непосредственно добавляющий информацию.
Я с этим не согласна, делаю проверку вводимых значений сразу при вводе пользователем.
Иначе при вводе даты для поиска заметки придется писать код проверки повторно либо позволять
пользователю вводить неверный формат дат, т.к. эта информация никуда не добавляется
 */
public class Main {
    public static void main(String[] args) {
        ConsoleManager console = new ConsoleManager();
        Notepad notepad = new Notepad();
        notepad.init();
        console.printStartMenu();
        System.out.println("Что Вы хотите сделать?");
        int command = console.getNumber();
        while (true) {
            while (command < 0 || command > 8) {
                System.out.println("Введите число от 1 до 8");
                command = console.getNumber();
            }
            switch (command) {
                case  0:
                    console.printStartMenu();
                    break;
                case 1:
                    notepad.addNote();
                    break;
                case 2:
                    notepad.viewNotes();
                    break;
                case 3:
                    notepad.findByTitle();
                    break;
                case 4:
                    notepad.findByDate();
                    break;
                case 5:
                    notepad.findByEmail();
                    break;
                case 6:
                    notepad.findByEmailAndTitle();
                    break;
                case 7:
                    notepad.findByWord();
                    break;
                case 8:
                    notepad.exit();
                    System.exit(0);
                    break;
            }
            System.out.println("Что Вы хотите сделать? (Нажмите 0, чтобы вывести список действий)");
            command = console.getNumber();
        }
    }
}