
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*; 
import javax.swing.JFrame;
import javax.swing.*;    
public class FrmStatistics extends JFrame {  
    JLabel scoreCompliment,scoreLabel,scoreBoard;
    JButton resume,nextLevel,quit,mainMenu,nextGame,scorePoint;
	int GameOverFrameWidth=500,GameOverFrameHeight=500;
	Font fontStyle;
	Color fontClr;
	JPanel p1;
	Insets gapBtwMenuQuitNext;
	static int level=0;
	FrmStatistics(BowArrow obj,int level){
		this.level=level;
		setSize(GameOverFrameWidth,GameOverFrameHeight);
		Dimension d= Toolkit.getDefaultToolkit().getScreenSize();
		d.width=(d.width-getSize().width)/2;
		d.height=(d.height-getSize().height)/2;
		setLocation(d.width,d.height);      // to locate at center of screen
		setResizable(false);
		setUndecorated(true);
	    setLayout(null);
		setContentPane(new JLabel((new ImageIcon(new ImageIcon("images/GameOver.png").getImage().getScaledInstance(GameOverFrameWidth,GameOverFrameHeight,Image.SCALE_DEFAULT)))));  // to add image in back ground
	    fontStyle= new Font("Chiller",Font.BOLD,44);  // chiller to give text style
	    scoreCompliment = new JLabel();
	    if(BowArrow.score>=750)
	       scoreCompliment.setText("You Played Excellent!!!");
	    else if(BowArrow.score>=500 && BowArrow.score<750)
		       scoreCompliment.setText("Well Done!!!");
	    else if(BowArrow.score>100 && BowArrow.score<=500)
		       scoreCompliment.setText("You can do Better!!!");
	    else 
	    	scoreCompliment.setText("You need a Eye Check-Up");
	    
		scoreCompliment.setBounds(10,10,500,150);
		scoreCompliment.setFont( fontStyle);
		fontClr= new Color(0,0,255);
		scoreCompliment.setForeground(Color.blue);
		
	    add(scoreCompliment,BorderLayout.NORTH);
		
	    scoreLabel= new JLabel("Your Score :");
	    fontStyle= new Font("Chiller",Font.BOLD,44);
	    scoreLabel.setFont(fontStyle);
	    fontClr= new Color(187,182,165);
	    scoreLabel.setForeground(fontClr);
	    scoreLabel.setBounds(20,100,200,100);
	    add(scoreLabel);
	    
	    scoreBoard = new JLabel(""+BowArrow.score);
	    fontStyle= new Font("Chiller",Font.BOLD,44);
	    scoreBoard.setFont(fontStyle);
	    fontClr= new Color(147,82,115);
	    scoreBoard.setForeground(Color.RED); 
	    scoreBoard.setBounds(300,100,200,100);
	    add(scoreBoard);
	    
	    mainMenu= new JButton("Main Menu");
	    fontStyle= new Font("Chiller",Font.BOLD,44);
	    mainMenu.setFont(fontStyle);
	    fontClr= new Color(147,82,115);
	    mainMenu.setForeground(Color.RED); 
	    mainMenu.setBounds(0,400,205,100);
	
	    mainMenu.setContentAreaFilled(false);
		mainMenu.setBorderPainted(false);
		add(mainMenu);
	    mainMenu.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent ae){	
	    		dispose();
	    		obj.dispose();
	    		new MainMenuOfArrow(); // to go main menu after playing game   
	    	}
	    });
	    
	    quit= new JButton("Quit");
	    fontStyle= new Font("Chiller",Font.BOLD,44);
	    quit.setFont(fontStyle);
	    fontClr= new Color(147,82,115);
	    quit.setForeground(Color.RED); 
	    quit.setBounds(210,400,165,100);
	    quit.setContentAreaFilled(false);
	    quit.setBorderPainted(false);
	  	quit.addActionListener(new ActionListener() {
	  	   	public void actionPerformed(ActionEvent ae){
	  	   		System.exit(0);
	  	   	}
	  	});
	    add(quit);
	   
	    nextLevel= new JButton("Next");
	    fontStyle= new Font("Chiller",Font.BOLD,44);
	    nextLevel.setFont(fontStyle);
	    fontClr= new Color(147,82,115);
	    nextLevel.setForeground(Color.RED); 
	    nextLevel.setBounds(390,400,110,100); 
	    nextLevel.setContentAreaFilled(false);
	  	nextLevel.setBorderPainted(false);
	  	nextLevel.addActionListener(new ActionListener() {
	  	   	public void actionPerformed(ActionEvent ae){
	  	   		FrmStatistics.level++;
	  	   		obj.dispose();
	  	   		dispose();
	  	   		new BowArrow(FrmStatistics.level);
	  	   	}
	  	});
	  	if(level<6)
	  		add(nextLevel);
	    setVisible(true);
    }   
} 
