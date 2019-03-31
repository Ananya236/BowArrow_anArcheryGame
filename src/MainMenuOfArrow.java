import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class MainMenuOfArrow extends JFrame{
	JLabel lblMainMenu;
	Dimension desktop;
	Font fontStyle;
	Color fontClr;
	JButton quit,btnStart,btnLevel,btnHighScore,btnHelp,btnBack;
	static int btnWidth=250,btnHeight=50;
	MainMenuOfArrow(){
		setLayout(null);
		desktop=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(desktop.width,desktop.height);
		setResizable(false);
		Dimension d= Toolkit.getDefaultToolkit().getScreenSize();
		d.width=(d.width-getSize().width)/2;
		d.height=(d.height-getSize().height)/2;
		setLocation(d.width,d.height);      // to locate at center of screen
		setResizable(false);
		setUndecorated(true);
	    setLayout(null);
		setContentPane(new JLabel((new ImageIcon(new ImageIcon("images/menuImage.gif").getImage().getScaledInstance(desktop.width,desktop.height,Image.SCALE_DEFAULT)))));  // to add image in back ground

		lblMainMenu = new JLabel("MAIN MENU");
		fontStyle= new Font("AR HERMANN",Font.BOLD,44);
		lblMainMenu.setFont(fontStyle);
		lblMainMenu.setBounds(550,50,500,50);
		fontClr= new Color(4, 242, 226);
		lblMainMenu.setForeground(fontClr);
		add(lblMainMenu);
		
		btnStart= new JButton("Start");
	    fontStyle= new Font("Ravie",Font.BOLD,44);
	    btnStart.setFont(fontStyle);
	    fontClr= new Color(66, 4, 158);
	    btnStart.setForeground(fontClr); 
	    btnStart.setBounds(540,110,btnWidth,btnHeight);
	   //fontClr= new Color(112, 34, 37);
	   //btnStart.setBackground(fontClr);
	   // btnStart.setContentAreaFilled(false);
	    btnStart.setBorderPainted(false);
	  	    
	    btnStart.addActionListener(new ActionListener() {
	  	  	public void actionPerformed(ActionEvent ae)
	  	   	{
	  	   		new BowArrow(1);
	  	   		dispose();
	  	   	}
	  	});
	    btnStart.addMouseListener(new A());
	    add(btnStart);
	    
	    btnLevel= new JButton("Level");
	    fontStyle= new Font("Ravie",Font.BOLD,44);
	    btnLevel.setFont(fontStyle);
	    fontClr= new Color(66, 4, 158);
	    btnLevel.setForeground(fontClr); 
	    btnLevel.setBounds(540,210,btnWidth,btnHeight);
	    //fontClr= new Color(112, 34, 37);
	    //btnLevel.setBackground(fontClr);
	    //btnLevel.setContentAreaFilled(false);
	    btnLevel.setBorderPainted(false);
	    btnLevel.addActionListener(new ActionListener() {
	  	    public void actionPerformed(ActionEvent ae)
	  	    {		
	  	    	deactivateButtons();
	  	    	backButton();
	  	    }
	  	});
	    btnLevel.addMouseListener(new A());
	    add(btnLevel);
	    
	    btnHighScore= new JButton("High Score");
	    fontStyle= new Font("Ravie",Font.BOLD,30);
	    btnHighScore.setFont(fontStyle);
	    fontClr= new Color(66, 4, 158);
	    btnHighScore.setForeground(fontClr); 
	    btnHighScore.setBounds(540,310,btnWidth,btnHeight);
	   // fontClr= new Color(112, 34, 37);
	    //btnHighScore.setBackground(fontClr);
	    btnHighScore.setBorderPainted(false);
	    btnHighScore.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent ae){
	  	    	deactivateButtons();
	  	    	backButton();		
	  	    }
	  	});
	    btnHighScore.addMouseListener(new A());
	    add(btnHighScore);
	
	    btnHelp= new JButton("Help");
	    fontStyle= new Font("Ravie",Font.BOLD,30);
	    btnHelp.setFont(fontStyle);
	    fontClr= new Color(66, 4, 158);
	    btnHelp.setForeground(fontClr); 
	    btnHelp.setBounds(540,410,btnWidth,btnHeight);
	    //fontClr= new Color(112, 34, 37);
	    //btnHelp.setBackground(fontClr);
	    btnHelp.setBorderPainted(false);
	  	    
	    btnHelp.addActionListener(new ActionListener() {
	  	    public void actionPerformed(ActionEvent ae){
	  	    	deactivateButtons();
	  	    	backButton();
	  	    }
	  	});
	    btnHelp.addMouseListener(new A());
	    add(btnHelp);
	
		quit= new JButton("Quit");
	    fontStyle= new Font("Forte",Font.BOLD,50);
	    quit.setFont(fontStyle);
	    fontClr= new Color(249, 2, 23);
	    quit.setForeground(fontClr); 
	    quit.setBounds(100,600,200,100);
	   
	    quit.setContentAreaFilled(false);
	    quit.setBorderPainted(false);
	  	    
	  	quit.addActionListener(new ActionListener() {
	  	    public void actionPerformed(ActionEvent ae){
	  	    		System.exit(0);
	  	    }
	  	});
	    add(quit);
		setVisible(true);
	}
	public static void main(String[] args) {
         new MainMenuOfArrow();
	}
	public void backButton(){
		btnBack= new JButton("Back");
		
	    fontStyle= new Font("Forte",Font.BOLD,50);
	    btnBack.setFont(fontStyle);
	    fontClr= new Color(249, 2, 23);
	    btnBack.setForeground(fontClr); 
	    btnBack.setBounds(500,390,200,100);
	   
	    btnBack.setContentAreaFilled(false);
	    btnBack.setBorderPainted(false);
	  	add(btnBack);   
	    btnBack.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent ae){
	  	    	btnStart.setVisible(true);
	  	    	btnLevel.setVisible(true);
	  	    	btnHighScore.setVisible(true);
	  	    	btnHelp.setVisible(true);
	  	    	if(btnBack==ae.getSource())
	  	    		btnBack.setVisible(false);
	  	    }
	  	});
	    add(btnBack);
	}
	public void deactivateButtons(){
		btnStart.setVisible(false);
  		btnLevel.setVisible(false);
  		btnHighScore.setVisible(false);
  		btnHelp.setVisible(false);
	}
}
class A extends MouseAdapter{
	public void mouseEntered(MouseEvent me) {
		JButton b=(JButton)me.getSource();
		b.setSize(300,80);
	}
	public void mouseExited(MouseEvent me) {
		JButton b=(JButton)me.getSource();
		b.setSize(MainMenuOfArrow.btnWidth,MainMenuOfArrow.btnHeight);
	}
}
