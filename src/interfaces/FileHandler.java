package interfaces;


import java.io.BufferedReader;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.FileWriter;

import java.io.IOException;


public class FileHandler
{
    private final String filename = "Filename.txt"; 

    public String loadWordList()    
    {

        String newLine;
        String stringList ="";

        FileReader TheFileReader;

        BufferedReader TheBufferedReader;

        try      
        {
            TheFileReader = new FileReader(new File(filename));
            TheBufferedReader = new BufferedReader(TheFileReader);

            while((newLine = TheBufferedReader.readLine()) != null)           
            {  
                stringList += newLine +",";
            }

            TheBufferedReader.close();

            return stringList;
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Could not find file!");
            System.out.println(ex.toString());
            return stringList;
        }
        catch (IOException ex)
        {
            System.out.println("Could not read from file!");
            System.out.println(ex.toString());
            return stringList;
        }
    }

    public void saveWordList(String content)
    {       
        FileWriter TheFileWriter;

        try
        {    
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