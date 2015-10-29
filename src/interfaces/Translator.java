/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

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
        
        
    /**
     * @param args the command line arguments
     */
 
  /**
     * Pre: Post: A new word pair is added to the existing collection of word
     * pairs. This method does not save to file!
     */
    public void add(String question, String answer)
    {
        
    }
    
    // Return the number of wordpairs in the collection (not the file).
    public int size()
    {
        
        return size;
    }
    
    
    public String getRandomQuestion()
    {
        
        return question;
    }
    
     /**
     * Pre: Post: Returns true if (question, quess) exists as a word pair in the
     * collection, otherwise false.
     */
    public boolean checkGuess(String question, String quess)
    {
        
        return pairExists;
    }
    
    /*
     Returns the answer corresponding to the question if this
     exists in the collection. Otherwise returns null.
    */
    public String lookup(String question)
    {
        return answer;
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
        
        
    }
    
    
    

            public static void main(String[] args) 
        {
          // TODO code application logic here
          
        }
}
