/********************************************************
* This program will use menus in the windows environment 
* to input Student names, scores, and calculate the total
* score, average score, and the letter grade. It will 
* display the Student info.
* 
* Mike Hostetler
* 7/27/2012
* 
***********************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class Student_Grades_12_MenuBar extends JFrame
{
  private static final int WIDTH = 850;
  private static final int HEIGHT = 300;
   
  private JMenuBar studentMenuBar;
  
  private JMenu studentMenu;
  private JMenu scoresMenu;
  private JMenu clearMenu;
  private JMenu exitMenu;
 
  private JMenuItem StuName;
  private JMenuItem Save;
  private JMenuItem Find;  
  private JMenuItem Test1;
  private JMenuItem Test2;
  private JMenuItem Test3;
  private JMenuItem Test4;
  private JMenuItem Test5;
  private JMenuItem Clear;
  private JMenuItem Exit;  
  
  private JPanel namePanel;
  private JPanel scorePanel; 
  private JPanel studentPanel;
  private JPanel instructionPanel;
  private JPanel studentGradePanel; 
  private JPanel findPanel; 
  private JPanel findErrorPanel; 
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
  String lettergrade, name, newscore, findName, foundStudentName = "n";
  String errorMessage, errorMessage1, errorMessage2;
  String xtotal = "0", totalaverage = "0";
  String score, xscore, scoreList = "";
  String nameNotFound = "Did not find Student\'s Name! ";
  String nameNotFound1 = "Click \'Find a Student\' button " +
  		"											to enter another name!";
  
  Scanner studentFileIn; 
  
  /****************************************************************
   * Sets the window frame, title, layout, resizing buttons,
   * and makes the window visible. Invokes the createContents method
   * 
   ******************************************************************/  
  public Student_Grades_12_MenuBar()
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
    /*************  MENU BAR *************************/
    
    studentMenu = new JMenu("Student");
    scoresMenu = new JMenu("Scores");
    clearMenu = new JMenu("Clear");
    exitMenu = new JMenu("Exit");

    StuName = new JMenuItem("Student Name");
    StuName.addActionListener(new StudentListener());
    studentMenu.add(StuName);
    
    Save = new JMenuItem("Save to File");
    Save.addActionListener(new StudentListener());
    studentMenu.add(Save);
    
    Find = new JMenuItem("Find a Student");
    Find.addActionListener(new StudentListener());
    studentMenu.add(Find);

    Test1 = new JMenuItem("Test 1");
    Test1.addActionListener(new ScoresListener());
    scoresMenu.add(Test1);    

    Test2 = new JMenuItem("Test 2");
    Test2.addActionListener(new ScoresListener());
    scoresMenu.add(Test2);    

    Test3 = new JMenuItem("Test 3");
    Test3.addActionListener(new ScoresListener());
    scoresMenu.add(Test3);  
    
    Test4 = new JMenuItem("Test 4");
    Test4.addActionListener(new ScoresListener());
    scoresMenu.add(Test4);    

    Test5 = new JMenuItem("Test 5");
    Test5.addActionListener(new ScoresListener());
    scoresMenu.add(Test5);   
        
    Clear = new JMenuItem("Clear Screen");
    Clear.addActionListener(new ClearListener());
    clearMenu.add(Clear); 
    
    Exit = new JMenuItem("Exit Program");
    Exit.addActionListener(new ExitListener());
    exitMenu.add(Exit);    
    
    studentMenuBar = new JMenuBar();
    studentMenuBar.add(studentMenu);
    studentMenuBar.add(scoresMenu);
    studentMenuBar.add(clearMenu);
    studentMenuBar.add(exitMenu);
    
    setJMenuBar(studentMenuBar);    
    
    /*************  STUDENT INFORMATION PANEL *************************/
    studentInfoPanel = new JPanel();
    studentInfoPanel.setLayout(new GridLayout(2,1));
    
    studentGradePanel = new JPanel();
    studentGradePanel.setLayout(new GridLayout(5,2)); 
    
    finalName = new JLabel("Name:  ", SwingConstants.RIGHT); 
    studentGradePanel.add(finalName);
    finalNameText = new JTextField(25);
    studentGradePanel.add(finalNameText);
    
    finalScore = 
    					new JLabel("Test Scores:  ", SwingConstants.RIGHT);
    studentGradePanel.add(finalScore); 
    finalScoresText = new JTextField(5); 
    studentGradePanel.add(finalScoresText);  
    
    finalShowTotal = 
    					new JLabel("Total Score:  ", SwingConstants.RIGHT);
    studentGradePanel.add(finalShowTotal);
    finalTotalText = new JTextField(5);
    studentGradePanel.add(finalTotalText);
    
    finalShowAverage = 
    					new JLabel("Average Score:  ", SwingConstants.RIGHT);
    studentGradePanel.add(finalShowAverage); 
    finalAverageText = new JTextField(5);
    studentGradePanel.add(finalAverageText);
    
    finalShowLetterGrade = 
    					new JLabel("Letter Grade:  ", SwingConstants.RIGHT);
    studentGradePanel.add(finalShowLetterGrade); 
    finalLetterGradeText = new JTextField(1);
    studentGradePanel.add(finalLetterGradeText);
    
    studentInfoPanel.add(studentGradePanel);
    
    saveErrorPanel = new JPanel();
    saveErrorPanel.setLayout(new GridLayout(2,1));
    
    findErrorPanel = new JPanel();
    findErrorLabel = new JLabel(nameNotFound, SwingConstants.CENTER);
    findErrorLabel1 = new JLabel(nameNotFound1, SwingConstants.CENTER); 
    findErrorPanel.add(findErrorLabel);
    findErrorPanel.add(findErrorLabel1);
    findErrorPanel.setBackground(Color.RED);
    findErrorPanel.setVisible(false);
    saveErrorPanel.add(findErrorPanel); 
    
    savePanel = new JPanel();
    saveLabel = new JLabel("The student\'s grade " +
    													"information was saved");   
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
    
    /*************  STUDENT DATA ENTRY PANEL *******************/
    
    studentPanel = new JPanel();
    studentPanel.setLayout(new FlowLayout());
    
    namePanel = new JPanel();
    namePrompt = new JLabel("ENTER Student\'s First and Last Name:");    
    namePanel.add(namePrompt);
    studentName = new JTextField(25);
    studentName.setBackground(Color.CYAN);
    studentName.addActionListener(new StudentListener());
    namePanel.add(studentName);    
    namePanel.setVisible(false);
    studentPanel.add(namePanel);
    
    scorePanel = new JPanel();
    scorePrompt = new JLabel("ENTER Student\'s Test Score:");
    scorePanel.add(scorePrompt);
    scoreText = new JTextField(3);
    scoreText.setBackground(Color.CYAN);
    scoreText.addActionListener(new ScoresListener());
    scorePanel.add(scoreText);    
    scorePanel.setVisible(false);
    studentPanel.add(scorePanel);
    
    findPanel = new JPanel();
    findStudentPrompt = new JLabel("To find student\'s grade " +
    											"information, ENTER First and Last Name:"); 
    findPanel.add(findStudentPrompt);
    findStudentName = new JTextField(25);
    findStudentName.setBackground(Color.CYAN);
    findStudentName.addActionListener(new StudentListener());
    findPanel.add(findStudentName);    
    findPanel.setVisible(false);
    studentPanel.add(findPanel);  
    
    studentPanel.setBackground(Color.BLUE);
    add(studentPanel, BorderLayout.SOUTH);
  }// end of creating window contents
  
  /********************************************************************
   * This class listens for any command in the Student menu. When the
   * command is clicked the corresponding method is invoked and the 
   * actions are processed.
   ********************************************************************/
  
  private class StudentListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      try
      {
        PrintWriter studentFileOut = new PrintWriter(
            new FileWriter("Student_Grades.txt", true));
        
        if (e.getSource() == StuName)
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
        else if (e.getSource() == Save)
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
        else if (e.getSource() == Find)
        {       
          clearTextFields();
          hideAllPanels();  
          findPanel.setVisible(true);
        }
        else if(e.getSource() == findStudentName)
        {       
          findName = findStudentName.getText();          
          findStudentInfo(findName);           
        }       
        else 
        {             
          System.exit(0);
        }
        studentFileOut.close();
      }   // end of try
      
      catch (IOException f)
      {
        JOptionPane.showMessageDialog(null, 
            "ERROR: Cannot find \'Student_Grades.txt\' file!");
        System.exit(0);
      } // end of catch
    }	// end of method
  }	// end of class
  
  /********************************************************************
   * This class listens for any command in the Scores menu. When the
   * command is clicked the corresponding method is invoked and the 
   * actions are processed.
   ********************************************************************/
  
  private class ScoresListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      if (e.getSource() == Test1)
      {                
        namePanel.setVisible(false);
        scoreText.setText("");
        scorePanel.setVisible(true);       
      } 
      if (e.getSource() == Test2)
      {                  
        namePanel.setVisible(false);
        scoreText.setText("");
        scorePanel.setVisible(true);        
      }
      if (e.getSource() == Test3)
      {        
        namePanel.setVisible(false);
        scoreText.setText("");
        scorePanel.setVisible(true);       
      }
      if (e.getSource() == Test4)
      {
        namePanel.setVisible(false);
        scoreText.setText("");
        scorePanel.setVisible(true);
      }
      if (e.getSource() == Test5)
      {       
        namePanel.setVisible(false);
        scoreText.setText("");
        scorePanel.setVisible(true);
      }
      if (e.getSource() == scoreText)
      { 
        namePanel.setVisible(false);
        score = scoreText.getText();
        score1 = Integer.parseInt(score);
        
        if (score1 < 0)
        { 
          errorMessage = "Score Error: You entered a score < 0!\n";
          errorMessage1 = "Click OK, then click the appropriate " +
              "\'Test\' command,\n";
          errorMessage2 = "Enter the score > 0!";
          
          JOptionPane.showMessageDialog(null, errorMessage + 
              errorMessage1 + errorMessage2);
          scorePanel.setVisible(false);
        }
        else
        {
          totalScores(score1);
        }           
      } // end of If
    }	// end of method
  }	// end of class
    
  /********************************************************************
   * This class listens for any command in the Clear menu. When the
   * command is clicked the corresponding method is invoked and the 
   * actions are processed.
   ********************************************************************/
  private class ClearListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      if (e.getSource() == Clear)
      {       
        clearFieldColor();          
        clearTextFields();          
        hideAllPanels();
      }
    }   
  }      

  /********************************************************************
   * This class listens for any command in the Exit menu. When the
   * command is clicked the window will close and the program will quit.
   ********************************************************************/
  private class ExitListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      if (e.getSource() == Exit)
      {
        System.exit(0);
      }      
    }   //end of method
  }  	// end of class  
  
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
  }	// end of method
    
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
  } 	// end of method
  
  /******************************************************************** 
   * Searches for student info when find button is clicked. Reads the
   * text file until the student is found or the end of the file. Will
   * display the student's info when found. Gives error message when 
   * info is not found.
   *******************************************************************/   
  public void findStudentInfo(String findName)
  {
    try
    {   
      foundStudentName = "n";
      clearFieldColor();     
      
      studentFileIn = 
      			new Scanner(new FileReader("Student_Grades.txt"));
     
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
    }		// end of try
    
  /**** Error if file not found or cannot open *******************/     
    catch (FileNotFoundException e)
    {
      System.out.println("Error: " + e.getMessage());
    } 	// end of catch  
  }		// end of method
  
  public void clearTextFields()
  {   
    finalNameText.setText("");
    finalScoresText.setText("");
    finalTotalText.setText("");
    finalAverageText.setText("");
    finalLetterGradeText.setText("");
    findStudentName.setText("");
  }		// end of method
  
  public void hideAllPanels()
  {   
    namePanel.setVisible(false);
    scorePanel.setVisible(false);
    findPanel.setVisible(false);
    findErrorPanel.setVisible(false);
    savePanel.setVisible(false);
  }		// end of method
  
  public void clearFieldColor()
  {
    finalNameText.setBackground(Color.WHITE);
    finalScoresText.setBackground(Color.WHITE);
    finalTotalText.setBackground(Color.WHITE);
    finalAverageText.setBackground(Color.WHITE);
    finalLetterGradeText.setBackground(Color.WHITE);
  }		// end of method
  
/**** Main method starting point of program **********************/     
  public static void main(String[]args)
  {
    new Student_Grades_12_MenuBar();
  }  // end of main method
}		// end of program








  