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
public class WordPairs 
{
    public String dkWord;
    public String engWord;
    public int difficulty;
    
    public WordPairs(String dkWord, String engWord)
    {
        this.dkWord = dkWord;
        this.engWord = engWord;
        
        if(engWord.length()>6)
        {
            difficulty = 3;    
        }
        else if(engWord.length()>3)
        {
            difficulty = 2;
        }
        else
        {
            difficulty = 1;
        }
        
    }
    
    public WordPairs(boolean language, String word)
    {  
        if(language == true)
        {
            dkWord = word;
        }
        else
        {
            engWord = word;
        }
        
    } 
    
    public int getDifficulty()
    {
        return difficulty;
    }
    
    public void setDifficulty(int difficulty)
    {
        this.difficulty = difficulty;
    }
    
    public String getDanishWord()
    {
        return dkWord;
    }
    
    public String getEnglishWord()
    {
        return engWord;
    }
    

    
}
