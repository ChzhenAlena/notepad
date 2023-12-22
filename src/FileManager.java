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
        file = new File(directory + "notepad.ser");
    }
    FileManager(){
        this("src\\");
    }
    public List<Note> readFile() {
        List<Note> notes = new ArrayList<>();
        Note note;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            while(true) {
                note = (Note) objectInputStream.readObject();
                System.out.println(note.toString());
                notes.add(note);
            }
        } catch (EOFException e){
            return notes;
        } catch (IOException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
    public void writeFile(List<Note> notes) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for(Note note : notes)
                objectOutputStream.writeObject(note);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
