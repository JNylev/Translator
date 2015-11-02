package interfaces;


import java.io.BufferedReader;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.FileWriter;

import java.io.IOException;

import java.util.ArrayList;



public class FileHandlerComplex
{
    private final String filename = "Filename.txt"; 

    public ArrayList<WordPairs> loadWordList()    
    {

        ArrayList<WordPairs> WordList = new ArrayList();

        String NewLine;

        FileReader TheFileReader;

        BufferedReader TheBufferedReader;


        try      
        {
            TheFileReader = new FileReader(new File(filename));
            TheBufferedReader = new BufferedReader(TheFileReader);

            while((NewLine = TheBufferedReader.readLine()) != null)           
            {
                String[] WordListArray = NewLine.split(",");
                WordList.add(new WordPairs(WordListArray[0],WordListArray[1]));
            }

            TheBufferedReader.close();

            return WordList;
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Could not find file!");
            System.out.println(ex.toString());
            return WordList;
        }
        catch (IOException ex)
        {
            System.out.println("Could not read from file!");
            System.out.println(ex.toString());
            return WordList;
        }
    }

    public void saveWordList(ArrayList<WordPairs> WordList)
    {       
        FileWriter TheFileWriter;
        String content = "";

        try
        {    
            for(int i = 0; i < WordList.size(); i++)
            {
                content += "\n" + WordList.get(i).getDanishWord() + "," + WordList.get(i).getEnglishWord();
            }     
            TheFileWriter = new FileWriter(new File(filename),true);
            TheFileWriter.write(content);
            TheFileWriter.close();  
        }
        
        catch (IOException ex)
        {
            System.out.println("Could not write to file!");
            System.out.println(ex.toString());
        }
    }

} // End of class.