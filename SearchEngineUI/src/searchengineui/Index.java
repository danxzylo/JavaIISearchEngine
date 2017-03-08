package searchengineui;

/**
 *
 * @author Nestor Arzola
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.io.OutputStream;
import javax.swing.DefaultListModel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import javax.swing.JOptionPane;
import java.io.IOException;

public class Index {
    Map<String, Map<Integer, ArrayList<Integer>>> map = new HashMap<>();
    ArrayList<String> indexedFilesList;
    
    public void index () {        
        String str;
        try {
            BufferedReader in = new BufferedReader(new FileReader("indexedFiles.txt"));
            while((str = in.readLine()) != null){
                indexedFilesList.add(str);
            }   
        } catch (IOException e) {
            
        }
    }
    
    public Path indexIt (DefaultListModel list){        
        Path index = Paths.get("index.txt");        
        
        int i;
        for(i = 0; i < list.getSize(); ++i){
            Path filePath = Paths.get(list.getElementAt(i).toString());
            try {
                String content = new String(Files.readAllBytes(filePath));
                Cleaner cleaner = new Cleaner();
                content = cleaner.clean(content);
                String[] wordList = content.split("\\W+");
                int position;
                for(position = 0; position < wordList.length; ++position){
                    if(!map.containsKey(wordList[position])){
                        map.put(wordList[position], new HashMap<>());                                                
                        map.get(wordList[position]).put(i, new ArrayList<>());
                        map.get(wordList[position]).get(i).add(position);
                    }
                    else if (!map.get(wordList[position]).containsKey(i)) {                        
                        map.get(wordList[position]).put(i, new ArrayList<>()); 
                        map.get(wordList[position]).get(i).add(position);
                    }
                    else {
                        map.get(wordList[position]).get(i).add(position);
                    }
                }
                
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e);
            } 
        }
        try{
            OutputStream out = Files.newOutputStream(index);
            List<String> keys = new ArrayList<>(map.keySet());
            for(String key : keys) {
                StringBuilder string = new StringBuilder();
                string.append(key);                
                for(i = 0; i < list.getSize(); ++i){
                    if(!map.get(key).containsKey(i)) {
                        continue;
                    }
                    for(Integer position : map.get(key).get(i)) {
                        string.append(" {" + i + ", " + position + "} ");
                    }                    
                }
                string.append("\n");
                out.write(string.toString().getBytes());
            }
            out.close();
        } catch (IOException e) {
            
        }               
        return index;
    }   
}
