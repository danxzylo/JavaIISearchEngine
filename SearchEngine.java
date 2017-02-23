import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;


import javax.swing.*;

public class SearchEngine extends JFrame {

	public SearchEngine () {

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 600));
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(390, 20));
        buttonPanel.setOpaque(false);
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.GRAY);
        headerPanel.setPreferredSize(new Dimension(390, 40));
        headerPanel.setOpaque(false);
        
        JPanel inputLabelPanel = new JPanel();
        inputLabelPanel.setBackground(Color.GRAY);
        inputLabelPanel.setPreferredSize(new Dimension(390, 1));
        
        JPanel outputLabelPanel1 = new JPanel();
        outputLabelPanel1.setBackground(Color.GRAY);
        outputLabelPanel1.setPreferredSize(new Dimension(390, 1));
        
        JPanel outputLabelPanel2 = new JPanel();
        outputLabelPanel2.setBackground(Color.GRAY);
        outputLabelPanel2.setPreferredSize(new Dimension(390, 1));
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.setPreferredSize(new Dimension(390, 40));
                
        
        // Header Label
        JLabel headerLabel = new JLabel("CnR Search Engine",
                SwingConstants.CENTER);
        headerLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        headerLabel.setForeground(Color.BLACK);
        headerLabel.setPreferredSize(new Dimension(380, 25));
        
        

        headerPanel.add(headerLabel);
  
        
        // Input Label         
        JLabel inputLabel = new JLabel("Files Indexed:", SwingConstants.LEFT); 
        inputLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        inputLabel.setForeground(Color.BLACK);
        inputLabel.setPreferredSize(new Dimension(380, 25));
        
        inputLabelPanel.add(inputLabel);
       
        // Input text area                      
        JTextArea inputTextArea = new JTextArea();
        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);
        inputScrollPane.getViewport().setPreferredSize(new Dimension(390,20));
        
        //Output Label
        JLabel outputLabel1 = new JLabel("Search Terms:", SwingConstants.LEFT);
        outputLabel1.setFont(new Font("Century Gothic", Font.BOLD, 15));
        outputLabel1.setForeground(Color.BLACK);
        outputLabel1.setPreferredSize(new Dimension(380, 25));
        
        outputLabelPanel1.add(outputLabel1);
        
        JTextArea outputTextArea1 = new JTextArea();
        JScrollPane outputScrollPane1 = new JScrollPane(outputTextArea1);
        outputScrollPane1.getViewport().setPreferredSize(new Dimension(50,10));
        
        JRadioButton option1 = new JRadioButton("All of the search terms");
        option1.setBackground(Color.GRAY);
        JRadioButton option2 = new JRadioButton("Any of the search terms");
        option2.setBackground(Color.GRAY);
        JRadioButton option3 = new JRadioButton("Exact phrase");
        option3.setBackground(Color.GRAY);
 
        ButtonGroup group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);
        
        JButton searchButton = new JButton("Search");
        
        
        // Output Label 2
        JLabel outputLabel2 = new JLabel("Results:", SwingConstants.LEFT);
        outputLabel2.setFont(new Font("Century Gothic", Font.BOLD, 15));
        outputLabel2.setForeground(Color.BLACK);
        outputLabel2.setPreferredSize(new Dimension(380, 25));
        
        
        
        
        outputLabelPanel2.add(outputLabel2);
        
        // Output text area 2
        JTextArea outputTextArea2 = new JTextArea();
        JScrollPane outputScrollPane2 = new JScrollPane(outputTextArea2);
        outputScrollPane2.getViewport().setPreferredSize(new Dimension(390,20));
        
                   
        JButton addFilesButton = new JButton("Add Files...");
       
        buttonPanel.add(addFilesButton);
        
        // Clear Button
        JButton clearButton = new JButton("Remove Selected Files");
     
        buttonPanel.add(clearButton);  
        
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.setPreferredSize(new Dimension(430, 10));
        
        JButton maintenance = new JButton("Maintenance");

        
        // Bottom Panel Label           
        JLabel bottomLabel = new JLabel("Number of Files Indexed: ");
        bottomLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        bottomLabel.setForeground(Color.BLACK);
        bottomLabel.setPreferredSize(new Dimension(380, 25));
        
        JButton about = new JButton("About...");
       
       
                
        bottomPanel.add(maintenance);
        bottomPanel.add(bottomLabel);
        bottomPanel.add(about);
  
        //Main panel layout
        LayoutManager layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        
        panel.add(headerPanel);
        panel.add(inputLabelPanel);
        panel.add(inputScrollPane);
        panel.add(buttonPanel);
        panel.add(outputLabelPanel1);
        panel.add(outputScrollPane1);
        panel.add(option1);
        panel.add(option2);
        panel.add(option3);
        panel.add(searchButton);
        panel.add(outputLabelPanel2);
        panel.add(outputScrollPane2);
        panel.add(bottomPanel);
        
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.setBackground(Color. GRAY);

        add(panel);
		
	}
	
	
	public static void main (String[] args) {
        SearchEngine se = new SearchEngine();
        se.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        se.setSize(600, 600);
        se.setVisible(true);
		
	}
}