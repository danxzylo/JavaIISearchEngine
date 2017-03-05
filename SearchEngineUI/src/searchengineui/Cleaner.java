package searchengineui;
/**
 *
 * @author nestorarzola
 */
public class Cleaner {
    public String clean (String text) {   
        // Replace new line characters with a space
        text = text.replaceAll("\\r\\n|[\\n\\x0B\\x0C\\r\\u0085\\u2028\\u2029]", " ");
        // Delete all non-alphanumerics
        text = text.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();        
        return text;
    }
    
    public static void main (String[] args) {
                
    }
}
