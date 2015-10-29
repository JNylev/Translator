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
    private String filename; 

    public void setFile(String aFileName)
    
{
        
	filename = aFileName;
    
}
    
    
public ArrayList<WordPairs> loadComplexItems()
    
{
        
ArrayList<WordPairs> ComplexItems = new ArrayList();
        
String NewLine;
        
FileReader TheFileReader;
        
BufferedReader TheBufferedReader;
        
        
try
       
 {
            
TheFileReader = new FileReader(new File(filename));
            
TheBufferedReader = new BufferedReader(TheFileReader);
            
            
while((NewLine = TheBufferedReader.readLine()) != null)
            
{
                
String[] ComplexItemArray = NewLine.split(",");
                
ComplexItems.add(new WordPairs(ComplexItemArray[0],ComplexItemArray[1]));
            }
                        
            TheBufferedReader.close();
            
            return ComplexItems;
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Could not find file!");
            System.out.println(ex.toString());
            return ComplexItems;
        }
        catch (IOException ex)
        {
            System.out.println("Could not read from file!");
            System.out.println(ex.toString());
            return ComplexItems;
        }
    }
    
    public void saveComplexItems(ArrayList<WordPairs> ComplexItems, int choice)
    {       
        FileWriter TheFileWriter;
        String content = "";
                
        try
        {    

        
        for(int i = 0; i < ComplexItems.size(); i++)
        {
            content += ComplexItems.get(i).getName() + "," + ComplexItems.get(i).getDescription() + "\n";
        }     
          
            
         if( choice == 0 )
        {
            TheFileWriter = new FileWriter(new File(filename),true);
            TheFileWriter.write(content);
            TheFileWriter.close();  

        }
         else if( choice == 1 )
            {
                TheFileWriter = new FileWriter(new File(filename));
                TheFileWriter.write(content);
                TheFileWriter.close();  

            } 
         else if( choice == 999 )
         {
                TheFileWriter = new FileWriter(new File(filename));
                TheFileWriter.flush();
                TheFileWriter.close();  
         }
         
         
         
         
        }
        

        
          catch (IOException ex)

            {

            System.out.println("Could not write to file!");

            System.out.println(ex.toString());

            }
                }
   
    
}