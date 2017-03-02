package searchengineui;

/**
 *
 * @author nestorarzola
 */
public class Cleaner {
    public String clean (String Text) {
        Text = Text.replaceAll("[^a-zA-Z0-9\\\\s]", "").toLowerCase();        
        return Text;
    }
    
    public static void main (String[] args) {
        String s = "bd83*&^TG#bfui(*..";
        
        Cleaner object = new Cleaner();
        s = object.clean(s);
        System.out.println(s);        
    }
}
