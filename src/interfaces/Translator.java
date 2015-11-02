/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Henrik & Jakob 
 */
public class Translator implements WordPairControlInterface {

      boolean pairExists;
      boolean loaded;
      boolean saved;
      int size;
      String question;
      String answer;
      String fileName = "Filename.txt";
      public ArrayList<WordPairs> wordList = new ArrayList();  
      FileHandlerComplex fileHandler = new FileHandlerComplex();
     
       
        
    /**
     * @param args the command line arguments
     */
 
  /**
     * Pre: Post: A new word pair is added to the existing collection of word
     * pairs. This method does not save to file!
     */
    public void add(String question, String answer)
    {
        boolean add = true;
        for(int i = 0; i < size(); i++)
        {
            if(question.equalsIgnoreCase(wordList.get(i).getDanishWord()))
            {
             add = false;
            }
        }
        
        if(add)
        {
        clear();
        WordPairs tempPair = new WordPairs(question, answer); 
        wordList.add(tempPair);  
        }
        else
        {
            System.out.println("The word allready exists in the dictionary");
        }
        
        
        
    }
    
    // Return the number of wordpairs in the collection (not the file).
    public int size()
    {
        
        if(wordList!=null)
        {
            size = wordList.size();
        }
        else
        {
            size = 0;
        }
        return size;
    }
    
    
    public String getRandomQuestion()
    {
        Random rGen = new Random();
        int randNr = rGen.nextInt(size());
        String question = wordList.get(randNr).getDanishWord();
        return question;
    }
    
     /**
     * Pre: Post: Returns true if (question, quess) exists as a word pair in the
     * collection, otherwise false.
     */
    public boolean checkGuess(String question, String quess)
    {
        pairExists = false;
        for(int i = 0; i < size(); i++)
        {
            if(question.equalsIgnoreCase(wordList.get(i).getDanishWord()))
            {
                if(quess.equalsIgnoreCase(wordList.get(i).getEnglishWord()))
                {
                pairExists = true;
                }
            }
        }
        return pairExists;
    }
    
    /*
     Returns the answer corresponding to the question if this
     exists in the collection. Otherwise returns null.
    */
    public String lookup(String question)
    {
        for(int i = 0; i < size(); i++)
        {
            if(question.equalsIgnoreCase(wordList.get(i).getDanishWord()))
            {
                return wordList.get(i).getEnglishWord();
            }
        }
        return null;
    }
    
    /*
     load all word pairs from file "filename.txt" 
     Return true if successfully done.
    */
    public boolean load(String filename)
    {
        
          if(fileHandler.loadWordList() != null ) 
          {
              wordList = fileHandler.loadWordList();
              return true;
          }
          
        
        return false;
    }
    
    /*
     *Write all words from the collection to the file "filename.txt"
     *Return true if successfully done.
     */
    
    public boolean save(String filename)
    {

            //Saving
           
         
         fileHandler.saveWordList(wordList);     
         
        return saved;
    }
    
    //The existing collection of word pairs is cleared
    public void clear()
    {
        wordList.clear();
    }
    
    public ArrayList<WordPairs> getList()
    {  
        return wordList;
    }
    
    public String displayAll()
    {
        String content = "";
        for(int i = 0; i < size(); i++)
        {
            content += "The question is: " + wordList.get(i).getDanishWord() + " And the answer is: " + wordList.get(i).getEnglishWord() + "\n";
        }
        return content;
    }
    
}
