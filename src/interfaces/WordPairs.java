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
    
    public boolean firstLetterCheckDK(String index)
    {
        return dkWord.startsWith(index);
    }
    
    public boolean firstLetterCheckEng(String index)
    {
        return engWord.startsWith(index);
    }
    
    public String getDanishWord()
    {
        return dkWord;
    }
    
    public String getEnglishWord()
    {
        return engWord;
    }
    
    public void setDanishWord(String dkWord)
    {
        this.dkWord = dkWord;
    }
    
    public void setEnglishWord(String engWord)
    {
        this.engWord = engWord;
    }
    
}
