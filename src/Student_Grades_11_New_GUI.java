/********************************************************
* This program will use the Windows environment to input
* Student names, scores, and calculate the total score,
* average score, and the lettergrade. It will display the 
* Student info.
* 
* Mike Hostetler
* 4/1/2009
* 
***********************************************************/

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class Student_Grades_11_New_GUI extends JFrame
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
  private JPanel findErrorPanel;
  private JPanel morePanel;
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
  private JLabel moreScorePrompt; 
  private JLabel saveLabel;
  private JLabel saveLabel1;
    
  public JTextArea instructionText;
  
  private JTextField studentName;
  private JTextField scoreText;
  private JTextField finalNameText;
  private JTextField finalScoresText;
  private JTextField finalTotalText;
  private JTextField finalAverageText;
  private JTextField finalLetterGradeText;
  private JTextField findStudentName;  
  private JTextField moreScoreText;
  
  int total = 0, average = 0, score1, count = 1;
  String lettergrade, name, findName, moreScores = "y";
  String errorMessage, errorMessage1, errorMessage2;
  String xtotal = "0", totalaverage = "0", more = "";
  String score, xscore, scoreList = "", foundStudentName = "n";
  String nameNotFound = "Did not find Student\'s name!";
  String nameNotFound1 = "Click \'Find a Student\' button" +
      " to enter another name!";
  
  Scanner studentFileIn;
  
  public static String[] StudentInfo = new String[10];
  
  
  public Student_Grades_11_New_GUI()
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
    /********* STUDENT INFORMATION PANEL ********/
    
    studentInfoPanel = new JPanel();
    studentInfoPanel.setLayout(new GridLayout(2,1));
    
    /********* STUDENT GRADE PANEL ****************/
    studentGradePanel = new JPanel();
    studentGradePanel.setLayout(new GridLayout(5,2)); 
    
    finalName = new JLabel(
        "Name:  ", SwingConstants.RIGHT); 
    studentGradePanel.add(finalName);
    finalNameText = new JTextField(25);
    studentGradePanel.add(finalNameText);
    
    finalScore = new JLabel(
        "Test Scores:  ", SwingConstants.RIGHT);
    studentGradePanel.add(finalScore); 
    finalScoresText = new JTextField(5); 
    studentGradePanel.add(finalScoresText);  
    
    finalShowTotal = new JLabel(
        "Total Score:  ", SwingConstants.RIGHT);
    studentGradePanel.add(finalShowTotal);
    finalTotalText = new JTextField(5);
    studentGradePanel.add(finalTotalText);
    
    finalShowAverage = new JLabel(
        "Average Score:  ", SwingConstants.RIGHT);
    studentGradePanel.add(finalShowAverage); 
    finalAverageText = new JTextField(5);
    studentGradePanel.add(finalAverageText);
    
    finalShowLetterGrade = new JLabel(
        "Letter Grade:  ", SwingConstants.RIGHT);
    studentGradePanel.add(finalShowLetterGrade); 
    finalLetterGradeText = new JTextField(1);
    studentGradePanel.add(finalLetterGradeText);
    
    studentInfoPanel.add(studentGradePanel);
    
    /********* FIND ERROR PANEL ****************/
    saveErrorPanel = new JPanel();
    saveErrorPanel.setLayout(new GridLayout(2,1));
    
    findErrorPanel = new JPanel();
    findErrorLabel = new JLabel(nameNotFound, 
        SwingConstants.CENTER);
    findErrorLabel1 = new JLabel(nameNotFound1,
        SwingConstants.CENTER); 
    findErrorPanel.add(findErrorLabel);
    findErrorPanel.add(findErrorLabel1);
    findErrorPanel.setBackground(Color.RED);
    findErrorPanel.setVisible(false);
    saveErrorPanel.add(findErrorPanel); 
    
    /********* SAVE INFORMATION PANEL ****************/
    savePanel = new JPanel();
    saveLabel = new JLabel("The student\'s grade " +
        "information was saved");   
    saveLabel1 = new JLabel("to the " +
        "\"Student_Grades.txt\" file."); 
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
        "To enter a student\'s name and test scores, " +
        "click the \'New Student\' button.\n" +
        "To view the Scores, Total, Average, or Letter Grade, " +
        "click the appropriate button.\n" +        
        "To clear the text window, click \'Clear\'.\n" +
        "To exit the program, click the " +
        "\'Exit Program\' button.\n" +
        "To save a student\'s grade information, " +
        "click the \'Save to File\' button. \n" +
        "To find a student\'s grade information, " +
        "click the \'Find a Student\' button. \n");
    
    instructionText.setEditable(false);
    instructionPanel.setBackground(Color.LIGHT_GRAY);
    instructionPanel.add(instructionText);
    add(instructionPanel, BorderLayout.WEST);
       
    /*************  BUTTON PANEL ******************/
    
    buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.WHITE);
    buttonPanel.setLayout(new GridLayout(2,6));

    JButton StuNameButton = new JButton("New Student");
    StuNameButton.addActionListener(new Listener());
    buttonPanel.add(StuNameButton);

    JButton SaveButton = new JButton("Save to File");
    SaveButton.addActionListener(new Listener());
    buttonPanel.add(SaveButton);        
   
    JButton PrintButton = new JButton("Find a Student");
    PrintButton.addActionListener(new Listener());
    buttonPanel.add(PrintButton);
    
    JButton ClearButton = new JButton("Clear Screen");
    ClearButton.addActionListener(new Listener());
    buttonPanel.add(ClearButton);

    JButton ExitButton = new JButton("Exit Program");
    ExitButton.addActionListener(new Listener());
    buttonPanel.add(ExitButton);
    
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
    
    add(buttonPanel, BorderLayout.SOUTH);
  
    /*************  STUDENT DATA ENTRY PANEL **********/
       
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
    findStudentPrompt = new JLabel("To find grade information, " +
        "Enter Student\'s Name:"); 
    findPanel.add(findStudentPrompt);
    findStudentName = new JTextField(25);
    findStudentName.setBackground(Color.CYAN);
    findStudentName.addActionListener(new Listener());
    findPanel.add(findStudentName);    
    findPanel.setVisible(false);
    studentPanel.add(findPanel);
    
    morePanel = new JPanel();
    moreScorePrompt = new JLabel("Do you have " +
        "more scores?  Y / N"); 
    morePanel.add(moreScorePrompt);
    moreScoreText = new JTextField(2);
    moreScoreText.setBackground(Color.CYAN);
    moreScoreText.addActionListener(new Listener());
    morePanel.add(moreScoreText);    
    morePanel.setVisible(false);
    studentPanel.add(morePanel);
    
    studentPanel.setBackground(Color.BLUE);
    add(studentPanel, BorderLayout.NORTH);
  }
  
  private class Listener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      try
      {
        PrintWriter studentFileOut = new PrintWriter(
            new FileWriter("Student_Grades.txt", true));
        
        /*************** BUTTON LISTENER **************/
        
        if (e.getActionCommand().equals("New Student"))
        {       
          studentName.setText("");
          namePanel.setVisible(true);  
          setNewStudent();
        }
        else if (e.getSource() == studentName)
        {
          name = studentName.getText();
          finalNameText.setText(name);
          namePanel.setVisible(false);
        
          scoreText.setText("");
          scorePanel.setVisible(true);       
        }
        else if (e.getSource() == scoreText)
        { 
          namePanel.setVisible(false);
          score = scoreText.getText();
          score1 = Integer.parseInt(score);        
        
          while (score1 < 0)
          {  
            scorePanel.setVisible(false);
            
            errorMessage = "Score Error: You entered a score < 0!\n";
            errorMessage1 = "Click OK, \n";
            errorMessage2 = "Enter a score > 0!";            
            JOptionPane.showMessageDialog(
                      null, errorMessage + errorMessage1 + errorMessage2);
            
            scoreText.setText("");
            scorePanel.setVisible(true);
            score = scoreText.getText();
            score1 = Integer.parseInt(score);
          }
          totalScores(score1); 
          morePanel.setVisible(true);
        }
        else if (e.getSource() == moreScoreText)
        { 
          more = moreScoreText.getText();
          namePanel.setVisible(false);
          moreScoreText.setText("");
          morePanel.setVisible(false);
          scoreText.setText("");
          if (more.equalsIgnoreCase("n"))
          {
            scorePanel.setVisible(false); 
          }
          else
          {
            scorePanel.setVisible(true); 
          }                    
        }
        else if (e.getActionCommand().equals("Save to File"))
        {             
          studentFileOut.println(name);
          studentFileOut.println(scoreList);
          studentFileOut.println(xtotal);
          studentFileOut.println(totalaverage);
          studentFileOut.println(lettergrade);                    

          clearFieldColor();
          
          finalNameText.setText(name);
          finalScoresText.setText(scoreList);
          finalTotalText.setText(xtotal);
          finalAverageText.setText(totalaverage);
          finalLetterGradeText.setText(lettergrade);
          
          hideAllPanels();
          savePanel.setVisible(true); 
        }
        else if (e.getActionCommand().equals("Test Scores"))
        {
          clearFieldColor();
          finalScoresText.setBackground(Color.YELLOW);
          
        }
        else if (e.getActionCommand().equals("Total Score"))
        {        
          clearFieldColor();
          finalTotalText.setBackground(Color.YELLOW);               
        }
        else if (e.getActionCommand().equals("Average Score"))
        {       
          clearFieldColor();
          finalAverageText.setBackground(Color.YELLOW);          
        }
        else if (e.getActionCommand().equals("Letter Grade"))
        {       
          clearFieldColor();
          finalLetterGradeText.setBackground(Color.YELLOW); 
        }
        else if (e.getActionCommand().equals("Find a Student"))
        {       
          clearTextFields();
          hideAllPanels();
          findPanel.setVisible(true);             
        }
        else if(e.getSource() == findStudentName)
        {                 
          findName = findStudentName.getText();        
          findPanel.setVisible(false); 
          findStudentInfo(findName);           
        }      
        else if (e.getActionCommand().equals("Clear Screen"))
        {       
          clearTextFields(); 
          clearFieldColor();
          hideAllPanels();
        }
        else if (e.getActionCommand().equals("Exit Program"))
        {
          System.exit(0);
        }            
        else 
        {             
          System.exit(0);
        }
        validate();
        studentFileOut.close();
      }
            
      catch (IOException f)
      {
        JOptionPane.showMessageDialog(null, "ERROR: Cannot find " +
            "\'Student_Grades.txt\' file!");
        System.exit(0);
      }
    }
  }
  public void setNewStudent()
  {
    total = 0; average = 0; score1 = 0; count = 1;
    lettergrade = "";moreScores = "y";
    xtotal = "0"; totalaverage = "0"; more = "";
    score = ""; xscore = ""; scoreList = "";
  }
  
  
  public void totalScores(int score1)
  {   
    total = total + score1;
    xtotal = Integer.toString(total);
    finalTotalText.setText(xtotal);
    
    xscore = Integer.toString(score1);
    scoreList = scoreList + xscore + ", ";
    finalScoresText.setText(scoreList);
   
    calcAverage(total, count);
    count++; 
    scorePanel.setVisible(false);
  }
    
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
  } 
  
  public void findStudentInfo(String findName)
  {
    try
    {   
      foundStudentName = "n";
      clearFieldColor();
      
      studentFileIn = new Scanner(new FileReader
          ("Student_Grades.txt"));
         
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
      }
      if (foundStudentName.equalsIgnoreCase("n"))
      {
        clearTextFields();
        hideAllPanels();        
        findErrorPanel.setVisible(true);       
      }      
      studentFileIn.close();
    }
    catch (FileNotFoundException e)
    {
      System.out.println("Error: " + e.getMessage());
    }   
  }
  
  public void clearTextFields()
  {
    finalNameText.setText("");
    finalScoresText.setText("");
    finalTotalText.setText("");
    finalAverageText.setText("");
    finalLetterGradeText.setText("");
    findStudentName.setText("");
  }  
  
  public void hideAllPanels()
  {  
    namePanel.setVisible(false);
    scorePanel.setVisible(false);
    findErrorPanel.setVisible(false);
    findPanel.setVisible(false);
    savePanel.setVisible(false);    
  }
  
  public void clearFieldColor()
  {    
    finalNameText.setBackground(Color.WHITE);
    finalScoresText.setBackground(Color.WHITE);
    finalTotalText.setBackground(Color.WHITE);
    finalAverageText.setBackground(Color.WHITE);
    finalLetterGradeText.setBackground(Color.WHITE);
  }  
  
  public static void main(String[]args)
  {
    new Student_Grades_11_New_GUI();
  }  
}








  