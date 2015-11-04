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
 * This is the controller.
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
    FileHandler fileHandler = new FileHandler();
    public int diffAmount=0;
       
        
    /*
     * @param args the command line arguments
     */
 
    /*
     * Pre: Post: A new word pair is added to the existing collection of word
     * pairs. This method does not save to file!
     */
    public void add(String question, String answer)
    {
        //clear();
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
        int rnd =0;
        while(rnd< 1000)
        {
            Random rGen = new Random();
            int randNr = rGen.nextInt(size());
            String question = wordList.get(randNr).getDanishWord();
          
            
            if(diffAmount > 20 && wordList.get(randNr).getDifficulty() == 3 || size() < 10)
            {
                System.out.println("diffAmount er: " + diffAmount + " og sværhedsgraden er: " + wordList.get(randNr).getDifficulty());
                return question;
            }
            else if(diffAmount > 10 && diffAmount < 21 && wordList.get(randNr).getDifficulty() == 2)
            {
                System.out.println("diffAmount er: " + diffAmount + " og sværhedsgraden er: " + wordList.get(randNr).getDifficulty());
                return question;
            }
            else if(diffAmount < 11 && wordList.get(randNr).getDifficulty() == 1)
            {
                System.out.println("diffAmount er: " + diffAmount + " og sværhedsgraden er: " + wordList.get(randNr).getDifficulty());
                return question;    
            }
            rnd++;
        }
        return question;
    }
    
     /*
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
                    diffAmount+=1;
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
    public boolean load(String fileName)
    {
        
        if(fileHandler.loadWordList(fileName).length() != 0 ) 
        {
              
            String stringList = fileHandler.loadWordList(fileName);
            String[] tempArray = stringList.split(",");
                
                for(int i = 0; i < tempArray.length; i+=2)
                {
                    WordPairs temp = new WordPairs(tempArray[i],tempArray[i+1]);
                    wordList.add(temp);
                }
              
            return true;
        }
        System.out.println("den er 0.");
        return false;
    }
    
    /*
     *Write all words from the collection to the file "filename.txt"
     *Return true if successfully done.
     */
    
    public boolean save(String fileName)
    {

            //Saving
        String content ="";
        for(int i = 0; i < wordList.size(); i++)
        {
            // Not adding \n for first save.
            if(i==0)
            {
                content += wordList.get(i).getDanishWord() + "," + wordList.get(i).getEnglishWord();

            }
            else
            {
                content += "\n" + wordList.get(i).getDanishWord() + "," + wordList.get(i).getEnglishWord();
            }
        }   
        saved = fileHandler.saveWordList(content, fileName);
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
