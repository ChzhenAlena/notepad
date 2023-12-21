import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Notepad {
    private List<Note> notes;
    FileManager fileManager;
    ConsoleManager consoleManager;
    public void init(){
        fileManager = new FileManager();
        notes = fileManager.readFile();
        consoleManager = new ConsoleManager();
    }
    public void addNote(){
        Note note = new Note();
        System.out.print("Введите Ваш email: ");
        note.setEmail(consoleManager.getEmail());
        System.out.print("Введите заголовок: ");
        note.setTitle(consoleManager.getString());
        System.out.print("Введите заметку: ");
        note.setText(consoleManager.getString());
        note.setDate(new Date());
        notes.add(note);
        System.out.println("Запись добавлена");
    }
    public void viewNotes(){
        for(Note note : notes)
            System.out.println(note.toString());
        if(notes.isEmpty())
            System.out.println("Записей ещё нет");
    }
    public void findByTitle(){
        System.out.print("Введите заголовок: ");
        String title = consoleManager.getString();
        for(Note note : notes)
            if(note.getTitle().equals(title))
                System.out.println(note.toString());
    }
    public void findByDate(){
        System.out.print("Введите дату в формате dd.MM.yyyy: ");
        Date date = consoleManager.getDate();
        for(Note note : notes)
            if(note.isDateEqual(date))
                System.out.println(note.toString());
    }
    public void findByEmail(){
        System.out.print("Введите email: ");
        String email = consoleManager.getEmail();
        for(Note note : notes)
            if(note.getEmail().equals(email))
                System.out.println(note.toString());
    }
    public void findByEmailAndTitle(){
        System.out.print("Введите email: ");
        String email = consoleManager.getEmail();
        System.out.print("Введите заголовок: ");
        String title = consoleManager.getString();
        for(Note note : notes)
            if(note.getEmail().equals(email) && note.getTitle().equals(title))
                System.out.println(note.toString());
    }
    public void findByWord(){
        System.out.print("Введите слово: ");
        String word = consoleManager.getString();
        for(Note note : notes)
            if(note.contains(word))
                System.out.println(note.toString());
    }
    public void exit(){
        fileManager.writeFile(notes);
    }
}
