import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FileManager {
    private File directory;
    private File file;
    FileManager(String directory){
        this.directory = new File(directory);
        file = new File(directory + "/" + "notepad.txt");
    }
    FileManager(){
        this("src");
    }
    public List<Note> readFile() {
        if(!file.exists()) {
            System.out.println("Файл заметок не найден, данные не будут загружены");
            return new ArrayList<Note>();
        }
        List<Note> notes = new ArrayList<Note>();
        try(Scanner scanner = new Scanner(file))
        {
            String strDate;
            while(scanner.hasNext()){
                Note note = new Note();
                strDate = scanner.next() + ' ' + scanner.next();
                note.setDate(stringToDate(strDate));
                note.setEmail(scanner.next("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"));
                note.setTitle(scanner.next());
                scanner.nextLine();
                note.setText(scanner.nextLine());
                notes.add(note);
            }
            //return notes;
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        return notes;
    }
    public void writeFile(List<Note> notes) {
        if(!file.exists()) {
            try
            {
                file.createNewFile();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
        try(FileWriter writer = new FileWriter(file, false))
        {
            for(Note note : notes){
                writer.write(dateToString(note.getDate()));
                writer.append(' ');
                writer.write(note.getEmail());
                writer.append(' ');
                writer.write(note.getTitle());
                writer.append('\n');
                writer.write(note.getText());
                writer.append('\n');
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    private Date stringToDate(String strDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy");
        Date parsingDate;
        try {
            parsingDate = dateFormat.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return parsingDate;
    }
    private String dateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy");
        return dateFormat.format(date);
    }
}
