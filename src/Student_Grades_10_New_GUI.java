/********************************************************
* This program will use the Windows environment to input
* Student names, scores, and calculate the total score,
* average score, and the letter grade. It will display the 
* Student info.
* 
* Mike Hostetler
* 7/27/2012
* 
***********************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Student_Grades_10_New_GUI extends JFrame
{
  private static final int WIDTH = 850;
  private static final int HEIGHT = 300;
   
  private JPanel namePanel;
  private JPanel scorePanel; 
  private JPanel studentPanel;
  private JPanel buttonPanel;  
  private JPanel instructionPanel;
  private JPanel studentGradePanel; 
  private JPanel findPanel; 
  private JPanel findPanelError; 
  private JPanel savePanel; 
  private JPanel saveErrorPanel; 
  private JPanel studentInfoPanel;
  
  public JLabel namePrompt;
  public JLabel scorePrompt;
  public JLabel findStudentPrompt;
  public JLabel findErrorLabel;
  public JLabel findErrorLabel1;
  private JLabel finalName;    
  private JLabel finalScore;
  private JLabel finalShowTotal;
  private JLabel finalShowAverage;
  private JLabel finalShowLetterGrade; 
  private JLabel saveLabel;
  private JLabel saveLabel1;
    
  public JTextArea instructionText;
  
  public JTextField studentName;
  private JTextField scoreText;
  private JTextField finalNameText;
  private JTextField finalScoresText;
  private JTextField finalTotalText;
  private JTextField finalAverageText;
  private JTextField finalLetterGradeText;
  private JTextField findStudentName;
 
  
  int total = 0, average = 0, score1, count = 1;
  String lettergrade, name, newscore;
  String findName, foundStudentName = "n";
  String errorMessage, errorMessage1, errorMessage2;
  String xtotal = "0", totalaverage = "0";
  String score, xscore, scoreList = "";
  String nameNotFound = "Did not find Student\'s Name! ";
  String nameNotFound1 = "Click \'Find a Student\' button" +
  													" to enter another name!";
  
  Scanner studentFileIn; 
  
  /****************************************************************
   * Sets the window frame, title, layout, resizing buttons,
   * and makes the window visible. Invokes the createContents method
   * 
   ******************************************************************/  
  public Student_Grades_10_New_GUI()
  {
    setTitle("Student Test Information");
    setSize(WIDTH, HEIGHT);
    setLayout(new BorderLayout());
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    createContents();
    setVisible(true);    
  }
  
  private void createContents()
  {     
    /*************  STUDENT INFORMATION PANEL ***********************/
    studentInfoPanel = new JPanel();
    studentInfoPanel.setLayout(new GridLayout(2,1));
    
    studentGradePanel = new JPanel();
    studentGradePanel.setLayout(new GridLayout(5,2)); 
    
    finalName = new JLabel("Name:  ", SwingConstants.LEFT); 
    studentGradePanel.add(finalName);
    finalNameText = new JTextField(25);
    studentGradePanel.add(finalNameText);
    
    finalScore = 
    				new JLabel("Test Scores:  ", SwingConstants.CENTER);
    studentGradePanel.add(finalScore); 
    finalScoresText = new JTextField(5); 
    studentGradePanel.add(finalScoresText);  
    
    finalShowTotal = 
    				new JLabel("Total Score:  ", SwingConstants.RIGHT);
    studentGradePanel.add(finalShowTotal);
    finalTotalText = new JTextField(5);
    studentGradePanel.add(finalTotalText);
    
    finalShowAverage = 
    				new JLabel("Average Score:  ", SwingConstants.CENTER);
    studentGradePanel.add(finalShowAverage); 
    finalAverageText = new JTextField(5);
    studentGradePanel.add(finalAverageText);
    
    finalShowLetterGrade = 
    				new JLabel("Letter Grade:  ", SwingConstants.LEFT);
    studentGradePanel.add(finalShowLetterGrade); 
    finalLetterGradeText = new JTextField(1);
    studentGradePanel.add(finalLetterGradeText);
    
    studentInfoPanel.add(studentGradePanel);
    
    saveErrorPanel = new JPanel();
    saveErrorPanel.setLayout(new GridLayout(2,1));
    
    findPanelError = new JPanel();
    findErrorLabel = new JLabel(nameNotFound, SwingConstants.CENTER);
    findErrorLabel1 = new JLabel(nameNotFound1, SwingConstants.CENTER); 
    findPanelError.add(findErrorLabel);
    findPanelError.add(findErrorLabel1);
    findPanelError.setBackground(Color.RED);
    findPanelError.setVisible(false);
    saveErrorPanel.add(findPanelError); 
    
    savePanel = new JPanel();
    saveLabel = new JLabel("The student\'s grade information was saved");   
    saveLabel1 = new JLabel("to the \"Student_Grades.txt\" file."); 
    savePanel.add(saveLabel);
    savePanel.add(saveLabel1);
    savePanel.setBackground(Color.GREEN);
    savePanel.setVisible(false);
    saveErrorPanel.add(savePanel);
    
    studentInfoPanel.add(saveErrorPanel);
    
    add(studentInfoPanel, BorderLayout.CENTER);
    studentInfoPanel.setVisible(true);
        
    /*************  INSTRUCTION PANEL *************************/
    
    instructionPanel = new JPanel();
    instructionText = new JTextArea("**** INSTRUCTIONS ****\n\n" +
        "To enter a student name, click the \'Student Name\' button.\n" +
        "To enter a test score, click the appropriate \'Test\' button.\n" +
        "To view the Scores, Total, Average, or Letter Grade, " +
        "click the appropriate button.\n" +        
        "To clear the text window, click \'Clear\'.\n" +
        "To exit the program, click the \'Exit Program\' button.\n" +
        "To find a student\'s grade information, " +
        "click the \'Find a Student\' button. \n");
    
    instructionText.setEditable(false);
    instructionPanel.setBackground(Color.LIGHT_GRAY);
    instructionPanel.add(instructionText);
    add(instructionPanel, BorderLayout.WEST);
       
    /*************  BUTTON PANEL *************************/
    
    buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.WHITE);
    buttonPanel.setLayout(new GridLayout(2,7));	//set num of columns to correct value even tho it doesn't matter

    JButton StuNameButton = new JButton("Student Name");
    StuNameButton.addActionListener(new Listener());
    buttonPanel.add(StuNameButton);

    JButton Test1Button = new JButton("Test 1");
    Test1Button.addActionListener(new Listener());
    buttonPanel.add(Test1Button);

    JButton Test2Button = new JButton("Test 2");
    Test2Button.addActionListener(new Listener());
    buttonPanel.add(Test2Button);

    JButton Test3Button = new JButton("Test 3");
    Test3Button.addActionListener(new Listener());
    buttonPanel.add(Test3Button);

    JButton Test4Button = new JButton("Test 4");
    Test4Button.addActionListener(new Listener());
    buttonPanel.add(Test4Button);

    JButton Test5Button = new JButton("Test 5");
    Test5Button.addActionListener(new Listener());
    buttonPanel.add(Test5Button);
    
    JButton SaveButton = new JButton("Save to File");
    SaveButton.addActionListener(new Listener());
    buttonPanel.add(SaveButton);
        
    JButton ScoresButton = new JButton("Test Scores");
    ScoresButton.addActionListener(new Listener());
    buttonPanel.add(ScoresButton);

    JButton TotalButton = new JButton("Total Score");
    TotalButton.addActionListener(new Listener());
    buttonPanel.add(TotalButton);

    JButton AverageButton = new JButton("Average Score");
    AverageButton.addActionListener(new Listener());
    buttonPanel.add(AverageButton);

    JButton LetterButton = new JButton("Letter Grade");
    LetterButton.addActionListener(new Listener());
    buttonPanel.add(LetterButton);

    JButton ClearButton = new JButton("Clear Screen");
    ClearButton.addActionListener(new Listener());
    buttonPanel.add(ClearButton);

    JButton ExitButton = new JButton("Exit Program");
    ExitButton.addActionListener(new Listener());
    buttonPanel.add(ExitButton);
    
    JButton PrintButton = new JButton("Find a Student");
    PrintButton.addActionListener(new Listener());
    buttonPanel.add(PrintButton);

    add(buttonPanel, BorderLayout.SOUTH);  
    
    /*************  STUDENT DATA ENTRY PANEL *******************/
    
    studentPanel = new JPanel();
    studentPanel.setLayout(new FlowLayout());
    
    namePanel = new JPanel();
    namePrompt = new JLabel("Student Name:");    
    namePanel.add(namePrompt);
    studentName = new JTextField(25);
    studentName.setBackground(Color.CYAN);
    studentName.addActionListener(new Listener());
    namePanel.add(studentName);    
    namePanel.setVisible(false);
    studentPanel.add(namePanel);
    
    scorePanel = new JPanel();
    scorePrompt = new JLabel("Student Test Score:");
    scorePanel.add(scorePrompt);
    scoreText = new JTextField(3);
    scoreText.setBackground(Color.CYAN);
    scoreText.addActionListener(new Listener());
    scorePanel.add(scoreText);    
    scorePanel.setVisible(false);
    studentPanel.add(scorePanel);
    
    findPanel = new JPanel();
    findStudentPrompt = new JLabel("To find student\'s grade " +
    											"information, Enter first and last name:"); 
    findPanel.add(findStudentPrompt);
    findStudentName = new JTextField(25);
    findStudentName.setBackground(Color.CYAN);
    findStudentName.addActionListener(new Listener());
    findPanel.add(findStudentName);    
    findPanel.setVisible(false);
    studentPanel.add(findPanel);  
    
    studentPanel.setBackground(Color.BLUE);
    add(studentPanel, BorderLayout.NORTH);
  }	// end of creating window contents
  
  /********************************************************************
   * This class listens for any button clicked. When a button is 
   * clicked the corresponding method is invoked and the statements
   * are processed.
   ********************************************************************/
  
  private class Listener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      try
      {
        PrintWriter studentFileOut = new PrintWriter(
            new FileWriter("Student_Grades.txt", true));
        
        /*************** BUTTON LISTENER **************/
        
        if (e.getActionCommand().equals("Student Name"))
        {       
          studentName.setText("");
          scoreList = "";
          count = 1;
          total = 0;
          xtotal = "";
          average = 0;
          totalaverage = "";
          lettergrade = "";
          namePanel.setVisible(true);                 
        }
        else if (e.getSource() == studentName)
        {
          name = studentName.getText();
          finalNameText.setText(name);
          namePanel.setVisible(false);
        }       
        else if (e.getActionCommand().equals("Test 1"))
        {                
          namePanel.setVisible(false);
          scoreText.setText("");
          scorePanel.setVisible(true);       
        } 
        else if (e.getActionCommand().equals("Test 2"))
        {                  
          namePanel.setVisible(false);
          scoreText.setText("");
          scorePanel.setVisible(true);        
        }
        else if (e.getActionCommand().equals("Test 3"))
        {        
          namePanel.setVisible(false);
          scoreText.setText("");
          scorePanel.setVisible(true);       
        }
        else if (e.getActionCommand().equals("Test 4"))
        {
          namePanel.setVisible(false);
          scoreText.setText("");
          scorePanel.setVisible(true);
        }
        else if (e.getActionCommand().equals("Test 5"))
        {       
          namePanel.setVisible(false);
          scoreText.setText("");
          scorePanel.setVisible(true);
        }
        else if (e.getSource() == scoreText)
        { 
          namePanel.setVisible(false);
          score = scoreText.getText();
          score1 = Integer.parseInt(score);
          
          if (score1 < 0)
          { 
            errorMessage = "Score Error: You entered a score < 0!\n";
            errorMessage1 = "Click OK, then click the appropriate " +
            		"\'Test\' button,\n";
            errorMessage2 = "Enter the score > 0!";
            
            JOptionPane.showMessageDialog(null, errorMessage + 
                errorMessage1 + errorMessage2);
            scorePanel.setVisible(false);
          }
          else
          {
            totalScores(score1);
          }           
        }
        else if (e.getActionCommand().equals("Save to File"))
        {      
          studentFileOut.println(name);
          studentFileOut.println(scoreList);
          studentFileOut.println(xtotal);
          studentFileOut.println(totalaverage);
          studentFileOut.println(lettergrade);
                    
          finalScoresText.setBackground(Color.WHITE);
          finalTotalText.setBackground(Color.WHITE);
          finalAverageText.setBackground(Color.WHITE);
          finalLetterGradeText.setBackground(Color.WHITE);
          
          finalNameText.setText(name);
          finalScoresText.setText(scoreList);
          finalTotalText.setText(xtotal);
          finalAverageText.setText(totalaverage);
          finalLetterGradeText.setText(lettergrade);
          
          findPanelError.setVisible(false);
          findPanel.setVisible(false);
          namePanel.setVisible(false);
          scorePanel.setVisible(false); 
          savePanel.setVisible(true); 
        }
        else if (e.getActionCommand().equals("Test Scores"))
        {
          finalScoresText.setBackground(Color.YELLOW);
          finalTotalText.setBackground(Color.WHITE);
          finalAverageText.setBackground(Color.WHITE);
          finalLetterGradeText.setBackground(Color.WHITE);
        }
        else if (e.getActionCommand().equals("Total Score"))
        {        
          finalScoresText.setBackground(Color.WHITE);
          finalTotalText.setBackground(Color.YELLOW);
          finalAverageText.setBackground(Color.WHITE);
          finalLetterGradeText.setBackground(Color.WHITE);        
        }
        else if (e.getActionCommand().equals("Average Score"))
        {       
          finalScoresText.setBackground(Color.WHITE);
          finalTotalText.setBackground(Color.WHITE);
          finalAverageText.setBackground(Color.YELLOW);
          finalLetterGradeText.setBackground(Color.WHITE);    
        }
        else if (e.getActionCommand().equals("Letter Grade"))
        {       
          finalScoresText.setBackground(Color.WHITE);
          finalTotalText.setBackground(Color.WHITE);
          finalAverageText.setBackground(Color.WHITE);
          finalLetterGradeText.setBackground(Color.YELLOW); 
        }
        else if (e.getActionCommand().equals("Find a Student"))
        {       
          findStudentName.setText("");
          finalNameText.setText("");
          finalScoresText.setText("");
          finalTotalText.setText("");
          finalAverageText.setText("");
          finalLetterGradeText.setText("");
          
          findPanelError.setVisible(false);
          findPanel.setVisible(true);
          namePanel.setVisible(false);
          scorePanel.setVisible(false);
          savePanel.setVisible(false);
        }
        else if(e.getSource() == findStudentName)
        {       
          findName = findStudentName.getText();
          
          findStudentInfo(findName);           
        }      
        else if (e.getActionCommand().equals("Clear Screen"))
        {       
          finalNameText.setBackground(Color.WHITE);
          finalScoresText.setBackground(Color.WHITE);
          finalTotalText.setBackground(Color.WHITE);
          finalAverageText.setBackground(Color.WHITE);
          finalLetterGradeText.setBackground(Color.WHITE);
          
          finalNameText.setText("");
          finalScoresText.setText("");
          finalTotalText.setText("");
          finalAverageText.setText("");
          finalLetterGradeText.setText("");
          
          findPanelError.setVisible(false);
          findPanel.setVisible(false);
          namePanel.setVisible(false);
          scorePanel.setVisible(false); 
          savePanel.setVisible(false);
        }
        else if (e.getActionCommand().equals("Exit Program"))
        {
          System.exit(0);
        }            
        else 
        {             
          namePanel.setVisible(false);
          scorePanel.setVisible(false);
          findPanel.setVisible(false);
          findPanelError.setVisible(false);
          savePanel.setVisible(false);
        }	// end of If...Else
        validate();
        studentFileOut.close();
      }	// end of try	
            
      catch (IOException f)
      {
        JOptionPane.showMessageDialog(null, 
            "ERROR: Cannot find \'Student_Grades.txt\' file!");
        System.exit(0);
      } // end of catch
    }	// end of method
  }	// end of class
  
  /*******************************************************************
   *  The argument score1 is passed to this method to be totaled. The
   *  is converted to a string value and placed in the text field.
   *  
   ******************************************************************/  
  public void totalScores(int score1)
  {   
    total = total + score1;
    xtotal = Integer.toString(total);
    finalTotalText.setText(xtotal);
    
    xscore = Integer.toString(score1);
    scoreList = scoreList + xscore + " ";
    finalScoresText.setText(scoreList);
   
    calcAverage(total, count);
    count++; 
    scorePanel.setVisible(false);
  }  // end of method
    
  /*******************************************************************
   *  If statements used to determine the letter grade before being
   *  placed into the Text field.  
   ******************************************************************/  
  public void calcAverage(int total, int count)
  {
    average = total / count;
    totalaverage = Integer.toString(average);
    
    if (average >= 90)
    {
      lettergrade = "A";
    }

    else if  (average >= 80)
    {
      lettergrade = "B";
    }
    
    else if  (average >= 70)
    {
      lettergrade = "C";
    }

    else if  (average >= 60)
    {
      lettergrade = "D";
    }
    else 
    {
      lettergrade = "F";
    }
    finalAverageText.setText(totalaverage);
    finalLetterGradeText.setText(lettergrade);    
  } // end of method
    
/************************************************************************ 
 * Searches for student info when find button is clicked. Reads the
 * text file until the student is found or the end of the file. Will
 * display the student's info when found. Gives error message when 
 * info is not found.
 **********************************************************************/ 
  public void findStudentInfo(String findName)
  {
    try
    {   
      foundStudentName = "n";
      
      finalNameText.setBackground(Color.WHITE);
      finalScoresText.setBackground(Color.WHITE);
      finalTotalText.setBackground(Color.WHITE);
      finalAverageText.setBackground(Color.WHITE);
      finalLetterGradeText.setBackground(Color.WHITE);
      
      studentFileIn = new Scanner(new FileReader("Student_Grades.txt"));
     
      while (studentFileIn.hasNextLine())
      {              
        name = studentFileIn.nextLine();
        scoreList = studentFileIn.nextLine();       
        xtotal = studentFileIn.nextLine();
        totalaverage = studentFileIn.nextLine();
        lettergrade = studentFileIn.nextLine();        
        
        if (findName.equalsIgnoreCase(name))
        {          
          finalNameText.setText(name);
          finalScoresText.setText(scoreList);
          finalTotalText.setText(xtotal);
          finalAverageText.setText(totalaverage);
          finalLetterGradeText.setText(lettergrade);
          foundStudentName = "y";          
        } 
      }		// end of While loop
      if (foundStudentName.equalsIgnoreCase("n"))
      {
        finalNameText.setText("");
        finalScoresText.setText("");
        finalTotalText.setText("");
        finalAverageText.setText("");
        finalLetterGradeText.setText("");
        
        namePanel.setVisible(false);
        scorePanel.setVisible(false);
        findPanel.setVisible(false);
        findPanelError.setVisible(true);
        savePanel.setVisible(false);
      }
      
      studentFileIn.close();
    }		// end of method
    
 /**** Error if file not found or cannot open **********************/     
    catch (FileNotFoundException e)
    {
      System.out.println("Error: " + e.getMessage());
    }   
  }		// end of catch
/**** Main method starting point of program **********************/  
  public static void main(String[]args)
  {
    new Student_Grades_10_New_GUI();
  }  // end of main method
}		// end of program








  