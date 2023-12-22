import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Note implements Serializable {
    private Date date;
    private String email;
    private String title;
    private String text;
    public Note(){}
    public Note(Date date, String email, String title, String text){
        this.date = date;
        this.email = email;
        this.title = title;
        this.text = text;
    }
    public void setDate(Date date){

        this.date = date;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Date getDate() {return date;}
    public String getEmail() {
        return email;
    }
    public String getText() {
        return text;
    }
    public String getTitle() {
        return title;
    }
    public String toString(){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm dd.MM.yyyy");
        String str = "Дата: " + df.format(date) + ", от кого: " + email + ", тема: " + title + "\n" + text;
        return str;
    }
    public boolean isDateEqual(Date date){
        if(this.date.getYear() == date.getYear() && this.date.getMonth() == date.getMonth()
                && this.date.getDay() == date.getDay())
            return true;
        else
            return false;
    }
    public boolean contains(String word){
        if(text.contains(word))
            return true;
        else
            return false;
    }

}
