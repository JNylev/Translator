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
      public ArrayList<WordPairs> wordList = new ArrayList();  
        
    /**
     * @param args the command line arguments
     */
 
  /**
     * Pre: Post: A new word pair is added to the existing collection of word
     * pairs. This method does not save to file!
     */
    public void add(String question, String answer)
    {
        WordPairs tempPair = new WordPairs(question, answer); 
        wordList.add(tempPair);
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
        return loaded;
    }
    
    /*
     *Write all words from the collection to the file "filename.txt"
     *Return true if successfully done.
     */
    
    public boolean save(String filename)
    {
        return saved;
    }
    
    //The existing collection of word pairs is cleared
    public void clear()
    {
        wordList.clear();
    }
    
    public ArrayList getList()
    {
        return wordList;
    }
    
    

            public static void main(String[] args) 
        {
          // TODO code application logic here
          
        }
}
