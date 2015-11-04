package test;

/**
 * @author lam
 * 10-10-2012 hau: 
 * Direct file manipulation removed (the test should not require more than is 
 * expressed in the interface  - ex: a specific file format)
 * Use of special danish characters omitted
 * Added negative tests
 * 9-10-2013 hau: 
 * automatic filename generation deleted (filename is not part of interface)
 * 3-3-2014 pelo:
 * Added extensive javadoc explanation. 
 * Increased testTries from 30 to 300 in testGetRandomQuestion()
 * 12-4-2014 Stefan (student at Cphbusiness.dk) 
 * Added code to make this test run on a unix like filesystem
 * 27-11-2014 pelo
 * Added test to break code that changes probability number on words.
 */

//import Entity.Engine;
import interfaces.Translator;
import interfaces.WordPairControlInterface;
import interfaces.WordPairControlInterface;
import interfaces.WordPairControlInterface;
import java.util.UUID;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Unit test for the WordPairDemo interface
 */
public class WordPair_Test_v3
{

    private WordPairControlInterface wordPairDemo;    
    private static String fileName = getFilenameGuid();


    /**
     * Utility method to generate a random filename which can't exist before on
     * users computer. It generates a random number and adds the .txt
     * extension to it. The method asks for the current users home directory and
     * saves the file there. A new file is created each time the test is run.
     * You can find them in your own home directory: "C:\User\Peter\"
     */
    private static String getFilenameGuid()
    {
        // Value to use a Windows or Unix like filesystem.
        // Set to false if you use a Mac or linux variant.
    
        
        String path = System.getProperty("user.home");
        return  UUID.randomUUID().toString() + ".txt";
    }


    /**
     * This method is executed EVERY TIME just before each of the test methods
     * are executed. Thus 3 new words are entered into the collection so that
     * the every testmethod has some content to test on.
     */
    @Before
    public void setUp()
    {
        //REPLACE WordPairDemoV1    WITH YOUR OWN CLASS THAT IMPLEMENTS THE INTERFACE
        wordPairDemo = new Translator();// !!!!! REPLACE HERE !!!!!
        assertTrue(wordPairDemo.size() == 0);
        wordPairDemo.add("hest", "horse");      //We trust the add() method here!
        wordPairDemo.add("hus", "house");
        wordPairDemo.add("bord", "table");
    }

    @After
    public void tearDown()
    {
    }

//////////////////////////////////////////////// THE ACTUAL TEST METHODS  ////////////////////////////////////


    @Test
    /**
     * This test requires that both save and load are implemented, since the
     * test strategy is: Save the file, clear the content of the list, and read
     * the file back in and check the result.
     */
    public void testLoad()
    {
        System.out.println(fileName);
        assertTrue(wordPairDemo.save(fileName));
        wordPairDemo.clear();
        assertTrue(wordPairDemo.load(fileName));
        assertTrue(wordPairDemo.size() == 3);
        assertEquals(wordPairDemo.lookup("hest"), "horse");
        assertEquals(wordPairDemo.lookup("hus"), "house");
        assertEquals(wordPairDemo.lookup("bord"), "table");
    }

    @Test
    /**
     * This test is redundant (covered by testLoad).
     */
    public void testSave()
    {
    }

    /**
     * This test first tries to find an existing word pair.
     */
    @Test
    public void testLookup_1()
    {
        assertEquals("Looked up hest. Expected: horse. Got: " + wordPairDemo.lookup("hest"),
                wordPairDemo.lookup("hest"), "horse");
    }

    /**
     * However, this is not if the first word exist. If the first word is found
     * (hest) then the returning word must also match. In other words :-) an
     * implementation that simply returns true on the first word and ignores the
     * second will fail the second part this test case.
     */
    @Test
    public void testLookup_SecondWord()
    {
        assertNotSame("Expected: something different from Cow. Got: " + wordPairDemo.lookup("hest"),
                wordPairDemo.lookup("hest"), "Cow");
    }

    @Test
    public void testClear()
    {
        wordPairDemo.clear();
        int collectionSize = wordPairDemo.size();
        assertTrue("Cleared the collection of word pairs. Expected size: 0. Got size: " + collectionSize, collectionSize == 0);
    }

    /**
     * Since the setUp() method already added 3 word pairs, there should be 4
     * after this method adds the ko-cow pair.
     */
    @Test
    public void testAdd_4()
    {
        wordPairDemo.add("ko", "cow");
        int resultSize = wordPairDemo.size();
        assertTrue("Added a 4th wordpair. Expected size: 4. Got size: " + resultSize, resultSize == 4);

        assertEquals(wordPairDemo.lookup("ko"), "cow");
    }

    /**
     * Checking to see if a wordpair that we add is actually there.
     */
    @Test
    public void testAdd_GotItRight()
    {
        wordPairDemo.add("ko", "cow");
        String lookUpResult = wordPairDemo.lookup("ko");
        assertEquals("Added wordpair ko-cow and called loookup(). Expected cow. Got: " + lookUpResult, lookUpResult, "cow");
    }

    @Test
    /**
     * This method simply calls the getEandomQuestion() 300 times. Becuase the
     * setUp() method (see above) has added 3 word pairs we should be able to
     * get them all with 300 tries. That is what is asserted at the bottom of
     * this method.
     *
     * This does not test the distribution, but if we belive in the random
     * generators it tests the "border values" in that all possible values must
     * be generated.
     *
     */
    public void testGetRandomQuestion()
    {
        int testTries = 300;
        boolean horseFound = false;
        boolean houseFound = false;
        boolean tabelFound = false;

        for (int i = 0; i < testTries; i++)
        {
            
            String question = wordPairDemo.getRandomQuestion();
            System.out.println("size is: " + wordPairDemo.size());
            if (question.equals("hest"))
            {
                horseFound = true;
            }
            if (question.equals("hus"))
            {
                houseFound = true;
            }
            if (question.equals("bord"))
            {
                tabelFound = true;
            }
        }
        String errorMessage = "Expected horseFound: true, houseFound: true, tableFound: true. Got: hoserFound: " + horseFound
                + ", houseFound: " + houseFound + ", tableFound: " + tabelFound;

        assertTrue(errorMessage, horseFound && houseFound && tabelFound);
    }

    @Test
    public void testCheckGuessTrue()
    {
        assertTrue("Expected true. Got: " + wordPairDemo.checkGuess("hest", "horse") + " for the wordpair hest, horse",
                wordPairDemo.checkGuess("hest", "horse"));
    }

    /**
     * It's not enough that the word "hest" exists. The answer "cow" is wrong
     * and the method checkGuess should return false for the question-answer
     * pair "hest"-"cow".
     */
    @Test
    public void testCheckGuessBoth()
    {
        assertFalse("Expected false. Got: " + wordPairDemo.checkGuess("hest", "cow") + " for the wordpair hest,cow",
                wordPairDemo.checkGuess("hest", "cow"));
    }

    @Test
    public void testGetSize()
    {
        assertTrue("Expected size 3. Got: " + wordPairDemo.size(), wordPairDemo.size() == 3);
    }

    /**
     * This test attempt to break the code by guessing every word correctly 
     * many times. 
     * Some programs assign a value to the wordpair when correctly guessed and
     * change this value when user guesses correctly. This test breaks those programs
     * were all words have their values changed in the extreme and then can not
     * pick a single word; or end up in an endless loop.
     * 
     * Using the 3 wordpairs that SetUp puts into the file:
     * "hest", "horse"
     * "hus", "house"
     * "bord", "table"
     *
     * Guessing them correctly 500 times and then calling getRandomQuestion();
     * 
     * If this test fails, it will be with an ERROR. 
     * Probably a java.lang.StackOverflowError
     */
    @Test
    public void testGettingEverythingCorrect_MultipleTimes()
    {         
        for (int i = 0; i < 500; i++) { wordPairDemo.checkGuess("hest", "horse"); }           
        for (int i = 0; i < 500; i++) { wordPairDemo.checkGuess("hus", "house"); }           
        for (int i = 0; i < 500; i++) { wordPairDemo.checkGuess("bord", "table"); }           
        
        wordPairDemo.getRandomQuestion();  //That's it. 
    }
}
 