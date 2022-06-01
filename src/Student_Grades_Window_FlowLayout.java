/************************************************************
* This program will use the windows frame environment using 
* FlowLayout to input Student names, scores, and calculate 
* the total score, average score, and the letter grade. 
* 
* Mike Hostetler
* 7/31/2012
* 
***********************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Student_Grades_Window_FlowLayout extends JFrame
{
  private static final int WIDTH = 850;
  private static final int HEIGHT = 300;
  private JTextField studentName, scoreText, totalText;
  private JTextField AverageText, LetterGradeText;
  private JLabel instructionTextScore = new JLabel("Please enter student name.");
  private JPanel TextPanel = new JPanel();
  int total = 0, average = 0, score1, count = 1;
  String lettergrade, xtotal = "0", name, totalaverage = "0";
  
  /****************************************************************
   * Sets the window frame, title, layout, resizing buttons,
   * and makes the window visible. Invokes the createContents method.
   * 
   ******************************************************************/  
  public Student_Grades_Window_FlowLayout()
  {
    setTitle("Student Test Information");
    setSize(WIDTH, HEIGHT);
    setLayout(new FlowLayout());
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    createContents();
    setVisible(true);    
  }	// end of method
  
  /*****************************************************************
   * This method creates the contents of the window. Buttons are on
   * the left side of the window. Text fields are on the right side.
   * 
   ******************************************************************/  
  private void createContents()
  {            
    JButton namePrompt = new JButton("Student Name:");    
    JButton scorePrompt = new JButton("Student Score:");
    JButton showTotal = new JButton("Student Total Score:");
    JButton showAverage = new JButton("Student Average Score:");
    JButton showLetterGrade = new JButton("Student Letter Grade:");
    studentName = new JTextField(25);
    scoreText = new JTextField(3);
    totalText = new JTextField(4);
    AverageText = new JTextField(4);
    LetterGradeText = new JTextField(2);
     
    add(namePrompt); 
    add(studentName);
    namePrompt.addActionListener(new Listener());
    studentName.addActionListener(new Listener());
    
    add(scorePrompt);
    add(scoreText);
    scorePrompt.addActionListener(new Listener());
    scoreText.addActionListener(new Listener());
    
    add(showTotal);
    add(totalText);
    showTotal.addActionListener(new Listener());
    
    add(showAverage);
    add(AverageText);
    showAverage.addActionListener(new Listener());
    
    add(showLetterGrade);
    add(LetterGradeText);
    showLetterGrade.addActionListener(new Listener());    
  }	// end of method
      
  /********************************************************************
   * This class listens for any button clicked. When a button is 
   * clicked the corresponding method is invoked and the statements
   * are processed.
   ********************************************************************/
  private class Listener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      if(e.getActionCommand().equals("Student Name:"))
      {              
        studentName.setBackground(Color.LIGHT_GRAY);
        
        instructionTextScore.setForeground(Color.RED);
        TextPanel.add(instructionTextScore);
        add(TextPanel, BorderLayout.CENTER);
        setVisible(true); 
      }
      
      if (e.getSource() == scoreText)
      {          
        score1 = Integer.parseInt(scoreText.getText()); 
        total = total + score1;         
        xtotal = Integer.toString(total);
        scoreText.setText(""); 
        calcAverage(total, count);
        count++;
      }      
      
      name = studentName.getText();
      studentName.setText(name);      
      totalText.setText(xtotal);
      AverageText.setText(totalaverage);
      LetterGradeText.setText(lettergrade);   
    }    // end of method
  }	// end of class
    
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
    }	// end of If...Else
  } 	// end of method

/**** Main method starting point of program **********************/  
  public static void main(String[]args)
  {
    new Student_Grades_Window_FlowLayout();
  }	// end of main method
}	// end of program