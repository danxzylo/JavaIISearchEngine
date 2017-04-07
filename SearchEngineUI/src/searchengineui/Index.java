package searchengineui;

/**
 *
 * @author Nestor Arzola
 * @author Daniel Zacarias
 * @author Ricci Ruiz
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
import searchengineui.GUI.*;

public class Index {
    Map<String, Map<Integer, ArrayList<Integer>>> map = new HashMap<>();
    ArrayList<String> indexedFilesList;
    
    public void index () {  
        // Get list of files to be indexed from last program run
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
        
        //Iterate through list of files passed in as parameter
        int i;
        for(i = 0; i < list.getSize(); ++i){
            //Save file path in Path object
            Path filePath = Paths.get(list.getElementAt(i).toString());
            try {
                //Put text file into one string
                String content = new String(Files.readAllBytes(filePath));
                
                //Take out special characters and make all lowercase
                Cleaner cleaner = new Cleaner();                
                content = cleaner.clean(content);
                
                //Create array of strings word by word.
                String[] wordList = content.split("\\W+");
                
                //Iterate through string array of words and insert into HashMap
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
        
        //Print map contents to files
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
    
   // public static String[] SearchFunctions(String query){

        
        
        //String cleanedQuery = query;            
        //Take out special characters and make all lowercase
        //Cleaner cleaner = new Cleaner();                
        //cleanedQuery = cleaner.clean(cleanedQuery);
                
        //Create array of strings word by word.
        //String[] wordList = cleanedQuery.split("\\W+");

//        Set<document> results = new HashSet<>();
//        HashSet results = new HashSet();
//        for(String temp : wordList){
//            for(String temp : wordList){
//            if ( wordList[] =!  )
//                continue;  // No document contains this word
//            // Add all documents containing this word to the results:
//            Set<Integer> documentsWithWord = Map.get(word).keySet();
//            results.addAll( documentsWithWord );
//        }
//        
//        return results;

   // }
    public List<String> allSearchTerms (String userSearch) {        
        List<String> fileList = new ArrayList<>();
        String[] searchTerms = userSearch.split(" "); 
                
        fileList.clear();
        
        
        
        int i;
        for(i = 0; i < this.indexedFilesList.size(); ++i) {
            for(String term : searchTerms) {               
                if(!(this.map.containsKey(term) && this.map.get(term).containsKey(i))) {                    
                    continue; 
                }
                fileList.add(indexedFilesList.get(i));
            }
        }                
        return fileList;
    }
    
    public List<String> includeTerms (String userSearch) {
        List<String> fileList = new ArrayList<>();
        String[] searchTerms = userSearch.split(" ");              
        fileList.clear();
        
        int i;
        for(i = 0; i < indexedFilesList.size(); ++i) {
            for(String term : searchTerms) {               
                if(this.map.containsKey(term) && this.map.get(term).containsKey(i)) {
                    if(fileList.contains(indexedFilesList.get(i))){
                        continue;
                    }
                    fileList.add(indexedFilesList.get(i));                   
                }                
            }
        }        
        return fileList;
    }
    
    public List<String> exactPhrase (String userSearch) {
        List<String> fileList = new ArrayList<>();
        List<Integer> firstTermPositions;       
        String[] searchTerms = userSearch.split(" ");               
        fileList.clear();
                
        int i;
        for(i = 0; i < indexedFilesList.size(); ++i) {    
            if(!this.map.containsKey(searchTerms[0])) {
                continue;
            }
            firstTermPositions = this.map.get(searchTerms[0]).get(i);
            for(Integer position : firstTermPositions) {
                for(String term : searchTerms) {
                    if(term.equals(searchTerms[0])) {
                        continue;
                    }
                    if(!this.map.get(term).get(i).contains(position + 1)) {
                        break;
                    }
                }
                fileList.add(indexedFilesList.get(i));
                break;
            }
        }        
        return fileList;
    }

}


