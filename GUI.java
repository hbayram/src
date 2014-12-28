import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

//import javafx.beans.property.SetProperty;


import java.text.DecimalFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class GUI extends JFrame{
	
	private static int Score = 0;
	private static int Target = 0;
	private static int Moves = 50;
	private static DefaultComboBoxModel LevelModels;
	private static DefaultComboBoxModel LoadedLevelModels;
	private static JComboBox SelectLevel;
	private static JComboBox LoadedLevel;
	private Board board;
	private Level level;
	private JTextField field;
	private JTextField moveField;
	private JTextField targetField;
	private JTextField timerField;
	
	
	private JLabel timeLabel;
	private DecimalFormat timeFormatter = new DecimalFormat("00");
	private Choronometer a= new Choronometer();

	
	public GUI (Board board){
		targetField = new JTextField();
		moveField = new JTextField();
		field = new JTextField();
		this.board = board;
		board.initBoard();
		level = board.getGameLevel();
		
		LevelModels = new DefaultComboBoxModel();
		SelectLevel = new JComboBox(LevelModels);    
		SelectLevel.setPreferredSize(new Dimension(100,50));
		addCompletedLevel("Select Level");
		
		LoadedLevelModels = new DefaultComboBoxModel();
		LoadedLevel = new JComboBox(LoadedLevelModels);    
		LoadedLevel.setPreferredSize(new Dimension(100,50));
		addSavedLevel("Saved Levels");
	}
	
	public void createAskingSave() {
		JFrame frame = new JFrame();
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		
		JButton save =createButton("SAVE");
		save.addActionListener(new SaveAction());
		buttonPanel.add(save);
		
		JButton exit = createButton("EXIT");
		exit.addActionListener(new ExitAction());
		buttonPanel.add(exit);
		
		JLabel label = createLabel("SAVE ?");
		label.setLocation(50, 0);
		frame.add(label,BorderLayout.NORTH);
		frame.add(buttonPanel);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(150, 150);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
	}

	
	public void createPlayFrame() {
		level.addPropertyChangeListener(new ChangeLevelListener());
		board.addPropertyChangeListener(new ChangeScoreListener());
		field.setText(""+0);
		moveField.setEditable(false);
		moveField.setText(""+level.getNumberOfMovement());
		targetField.setEditable(false);
		targetField.setText(""+level.getTarget());
		JFrame frame = new JFrame();
		JPanel showingPanel = new JPanel();
		JPanel exitPanel = new JPanel();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Chewy Lokum Legend");
		frame.setSize(460,600);
		showingPanel.setLayout(new GridLayout(1,6));
		showingPanel.add(createLabel("Moves: "));
		showingPanel.add(moveField);
		showingPanel.add(createLabel("Target: "));
		showingPanel.add(targetField);
		showingPanel.add(createLabel("Scores: "));
		showingPanel.add(field);
		timeLabel = new JLabel();
        timeLabel.setFont(new Font("Consolas", Font.PLAIN, 13));
      
        timeLabel.setText(timeFormatter.format(a.getMinutes() )+ ":"
                + timeFormatter.format(a.getSeconds()) + "."
                + timeFormatter.format(a.getCentiseconds()));

       
		showingPanel.add(timeLabel);
		exitPanel.add(createLabel(""));
		
		
		
		JButton exitButton = createButton("Exit");
		exitButton.addActionListener(new CreatingExitFrame());
		exitPanel.add(exitButton);
		exitPanel.add(createLabel(""));
		frame.add(showingPanel,BorderLayout.NORTH);
		frame.add(board.getWindow(),BorderLayout.CENTER);
		frame.add(exitPanel,BorderLayout.SOUTH);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
public void createSettingsFrame() {
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Chewy Lokum Legend");
		frame.setSize(400,100);	
		
		JButton gameButton = createButton("Play New Game");
		panel.add(gameButton);
		gameButton.addActionListener(new creatingPlayFrameAction());
		gameButton.addActionListener(new startKronometre());
		
		
		panel.add(SelectLevel);
		panel.add(LoadedLevel);
		frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	
	public static JButton createButton(String string){
		JButton button =  new JButton(string);
		button.setPreferredSize(new Dimension(150,50));
		//button.addActionListener(ActionListener l);
		return button;
	}

	public static JLabel createLabel(String string){
		JLabel label = new JLabel(string);
		label.setPreferredSize(new Dimension(50,50));
		return label;
	}
	
	public static void addCompletedLevel(String string) {
		LevelModels.addElement(string);
	}
	public static void addSavedLevel(String string) {
		LoadedLevelModels.addElement(string);
	}
	
	public int getTarget() {
		return Target;
	}

	public void setTarget() {
		Target = level.getTarget();
	}

	public int getUpdateScore() {
		return Score;
	}

	public void setScore() {
		GUI.Score = board.getScore();
	}

	public int getMoves() {
		return Moves;
	}


	public void setNumOfMoves(int move) {
		// TODO Auto-generated method stub
		Moves = level.getNumberOfMovement();
	}
	
	//ActionListenerlar buradan baþlýyor. 
	private class ChangeScoreListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			// TODO Auto-generated method stub
			System.out.println("PCE " + evt.getPropertyName()
					+ " " + evt.getNewValue());
			
	        field.setText(""+board.getScore());
		}
		
	}
	
	private class ChangeLevelListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			// TODO Auto-generated method stub
			System.out.println("PCE " + evt.getPropertyName()
					+ " " + evt.getNewValue());
			
	        moveField.setText(""+level.getNumberOfMovement());
		}
		
	}
	
	private class ExitAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}		
	}
	
	private class SaveAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			board.saveGameBoard();
		}		
	}
	
	private class CreatingExitFrame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			createAskingSave();
		}		
	}
	
	private class creatingPlayFrameAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			createPlayFrame();			
		}		
	}
	
	

	private class startKronometre implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			a.startChronometer() ;			
		}		
	}
	

}
