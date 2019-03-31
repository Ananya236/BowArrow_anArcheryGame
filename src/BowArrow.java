import java.awt.*;

import java.awt.event.*;

import javax.swing.JFrame;

import javax.swing.*;
class BowArrow extends JFrame {
	private JLabel lblDisc,lblArrowCount,lblArrow[],lblScore,Shootlbl,lblScorel,lblScoreV,lblHitsl,lblHits,lblHitsV,lblMissl,lblMiss,lblMissV,lbllevel,lblpresent;
	private ImageIcon icoDisc,icoArrow,icoArcher,icobg,icoScore,icoHits,icoMissed;
	private JButton btnshooter;
	private Thread discThread,arrowThread;
	private Font flabel,flevel;
	private boolean chngDir=true,arrowReached=true;
	private Dimension desktop;
	private int discX=600,discY=0,discWidth=140,discHeight=170;
	private int arrowstX=201,arrowX,arrowY=320,arrowWidth=70,arrowHeight=10;
	private int shooterX=0,shooterY=200,shooterWidth=200,shooterHeight=300;
	private int scoreX=0,scoreY=550,scoreWidth=100,scoreHeight=100;
	private int scorelX=10,scorelY=640,scorelWidth=100,scorelHeight=30;
	private int hitsX=110,hitsY=550,hitsWidth=100,hitsHeight=100;
	private int hitslX=120,hitslY=640,hitslWidth=100,hitslHeight=30;
	private int missedX=220,missedY=550,missedWidth=100,missedHeight=100;
	private int missedlX=230,missedlY=640,missedlWidth=100,missedlHeight=30;
	private int levelX=625,levelY=70,levelWidth=150,levelHeight=50;
	private int presentWidth=30,presentHeight=20;
	private int white=10,black=20,blue=50,red=75,yellow=100,present=0; 
	private int currArrow=0;
	private int arrowCount=10;
	private int diffDisc=1;
	private int minScore=300;
	private int hits=0,miss=0,level=0,arrowSpeed=10,boardSpeed=10;
	static int score=0;
	void init(int level){
		desktop=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(desktop.width,desktop.height);
		setResizable(false);
		if(level==6) {
			btnshooter.addKeyListener(new KeyListener() {

				@Override
				public void keyPressed(KeyEvent ke) {
					switch(ke.getKeyCode()) {
					case KeyEvent.VK_UP:{
						shooterY=shooterY-20;
					}
					case KeyEvent.VK_DOWN:{
						shooterY=shooterY+20;
					}
						
					}
					 btnshooter.setBounds(shooterX,shooterY, shooterWidth,shooterHeight);
				}

				@Override
				public void keyReleased(KeyEvent ke) {
					
				}

				@Override
				public void keyTyped(KeyEvent ke) {
				}
				
			});
		}
		arrowSpeed=level*10000;
		boardSpeed=(5-level)*5;
		discX=600+level*100;
	    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    ImageIcon ii=new ImageIcon("images/bg2.png");
	    setContentPane(new JLabel(new ImageIcon(ii.getImage().getScaledInstance(desktop.width, desktop.height, Image.SCALE_SMOOTH))));
	    
	    addWindowListener(new WindowAdapter() {
	    	@SuppressWarnings("deprecation")
			public void windowClosing(WindowEvent we) {
	    		discThread.stop();
	    		if(arrowThread!=null)
	    			arrowThread.stop();
	    		dispose();
	    	}
	    });
	    setLayout(null);
	    getContentPane().setBackground(Color.CYAN);
	    setResizable(false);
	    
	    icoScore=new ImageIcon("images/scores.png");
	    flabel=new Font(Font.SERIF,Font.BOLD+Font.ITALIC,25);
	    flevel=new Font(Font.SERIF,Font.BOLD+Font.ITALIC,38);

	    lblScorel=new JLabel("SCORE");
	    lblScorel.setBounds(scorelX, scorelY, scorelWidth, scorelHeight);
	    lblScorel.setFont(flabel);
	    add(lblScorel);
	    lblScoreV=new JLabel("   0");
	    lblScoreV.setBounds(scoreX, scoreY, scoreWidth, scoreHeight);
	    lblScoreV.setFont(flabel);
	    add(lblScoreV);	    
	    lblScore=new JLabel(new ImageIcon(icoScore.getImage().getScaledInstance(scoreWidth, scoreHeight, Image.SCALE_SMOOTH)));
	    lblScore.setBounds(scoreX, scoreY, scoreWidth, scoreHeight);
	    add(lblScore);
	   
	    lblHitsl=new JLabel("HITS");
	    lblHitsl.setBounds(hitslX,hitslY,hitslWidth,hitslHeight);
	    lblHitsl.setFont(flabel);
	    add(lblHitsl);
	    lblHitsV=new JLabel("   0");
	    lblHitsV.setBounds(hitsX,hitsY,hitsWidth,hitsHeight);
	    lblHitsV.setFont(flabel);
	    add(lblHitsV);
	    lblHits=new JLabel(new ImageIcon(icoScore.getImage().getScaledInstance(hitsWidth, hitsHeight, Image.SCALE_SMOOTH)));
	    lblHits.setBounds(hitsX,hitsY,hitsWidth,hitsHeight);
	    add(lblHits);
	    
	    lblMissl=new JLabel("MISSED");
	    lblMissl.setBounds(missedlX,missedlY,missedlWidth,missedlHeight);
	    lblMissl.setFont(flabel);
	    add(lblMissl);
	    lblMissV=new JLabel("   0");
	    lblMissV.setBounds(missedX,missedY,missedWidth,missedHeight);
	    lblMissV.setFont(flabel);
	    add(lblMissV);
	    lblMiss=new JLabel(new ImageIcon(icoScore.getImage().getScaledInstance(missedWidth,missedHeight, Image.SCALE_SMOOTH)));
	    lblMiss.setBounds(missedX,missedY,missedWidth,missedHeight);
	    add(lblMiss);
	    
	    lbllevel = new JLabel("LEVEL "+level);
	    lbllevel.setBounds(levelX,levelY,levelWidth,levelHeight);
	    lbllevel.setFont(flevel);
	    add(lbllevel);
	    
	    Shootlbl=new JLabel("shoot");
	    Shootlbl.setVisible(false);
	    Shootlbl.setBounds(shooterX,shooterY+shooterHeight+100,shooterWidth,shooterHeight);
	    Shootlbl.setForeground(Color.YELLOW);
	    add(Shootlbl);
	    
	    icoArcher=new ImageIcon("images/archer.png");
	    btnshooter=new JButton(new ImageIcon(icoArcher.getImage().getScaledInstance(shooterWidth, shooterHeight, Image.SCALE_SMOOTH)));
	    btnshooter.setBounds(shooterX,shooterY, shooterWidth,shooterHeight);
        btnshooter.setContentAreaFilled(false);
        btnshooter.setBorderPainted(false);
	    btnshooter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(arrowReached==true && currArrow<arrowCount){
					arrowReached=false;
					arrowThread=new Thread() {
						public void run() {
							moveArrow(lblArrow[currArrow]);
							lblArrow[currArrow].setVisible(true);
							Shootlbl.setVisible(true);
						}
					};
					arrowThread.start();
				}
			}	
	    });
	    add(btnshooter);
	    
	    lblArrowCount = new JLabel(arrowCount+" ");
	    lblArrowCount.setBounds(arrowstX,arrowY+arrowHeight,arrowWidth*2,arrowHeight*3);
	    lblArrowCount.setFont(flabel);
	    Color c=new Color(239,169,28);
	    lblArrowCount.setForeground(c);
	    add(lblArrowCount);
	    
	    icoArrow=new ImageIcon("images/wood-arrow.png");
	    lblArrow=new JLabel[arrowCount];
	    for(int i=0;i<arrowCount;i++){
	    	lblArrow[i]=new JLabel(new ImageIcon(icoArrow.getImage().getScaledInstance(arrowWidth, arrowHeight, Image.SCALE_SMOOTH)));
	    	lblArrow[i].setBounds(arrowstX,arrowY,arrowWidth,arrowHeight);
	    	lblArrow[0].setVisible(true);
	    	add(lblArrow[i]);
	    	lblArrow[i].setVisible(false);
	    }
	    
	    lblpresent=new JLabel(" ");
	    lblpresent.setSize(arrowWidth*2,arrowHeight*3);
	    lblpresent.setFont(flabel);
	    lblpresent.setForeground(Color.ORANGE);
	    lblpresent.setVisible(false);
	    add(lblpresent);
	    
	    setSize(desktop.width,desktop.height);
	    setVisible(true);
	    disc();
	}
	public BowArrow(int level) {
		this.level=level;
		discThread=new Thread() {
			public void run() {
				init(level);
			}
		};
		discThread.start();
	}	
	void moveArrow(JLabel lblArrow) {
		int hitPos=0;
		boolean hit=false;
		for(arrowX=arrowstX;arrowX<discX;arrowX++){
			lblArrow.setLocation(arrowX,arrowY);
			try{
				Thread.sleep(0,arrowSpeed);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			revalidate();
		}
		if((arrowY >= discY && arrowY <= (discY+discHeight)) && ((arrowX+arrowWidth) == (discX+discWidth/2))){
     	    hitPos=arrowY-discY;
			hit=true;
			hits++;
			if((hitPos>=0 && hitPos<=17) ||(hitPos>=150 && hitPos<=170)){
				score+=white;
	            present=white;
			}
			else if((hitPos>=18 && hitPos<=35) ||(hitPos>=134 && hitPos<=149)){
				score+=black;
				present=black;
			}
			else if((hitPos>=36 && hitPos<=52) ||(hitPos>=117 && hitPos<=133)){
				score+=blue;
				present=blue;
			}
			else if((hitPos>=53 && hitPos<=68) ||(hitPos>=102 && hitPos<=116)){
				score+=red;
				present=red;
			}
			else{
				score+=yellow;
				present=yellow;
			}
			freeze(hitPos,lblArrow);
		}
		if(hit==false){
			score-=5;
			miss++;
		}
		lblScoreV.setText("  "+score);
		lblHitsV.setText("  "+hits);
		lblMissV.setText("  "+miss);
			
	    lblArrow.setVisible(false);
	    lblpresent.setVisible(false);
	    arrowReached=true;
       	currArrow++;
	    lblArrowCount.setText(arrowCount-currArrow+" left");
	    if(currArrow==arrowCount){	
	    	new FrmStatistics(this,level);
	    }
	}	
	void freeze(int check,JLabel lblArrow){
		int i=0;
		while(i<200){
			lblArrow.setLocation(arrowX,discY+check);
			lblpresent.setText("+"+present);
			lblpresent.setLocation(arrowX+arrowWidth/2,discY+check-arrowHeight*2);
			lblpresent.setVisible(true);
			try {
				Thread.sleep(10);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			i++;
			revalidate();
		}
	}
	void disc() {
		icoDisc=new ImageIcon("images/board.png");
		lblDisc=new JLabel(new ImageIcon(icoDisc.getImage().getScaledInstance(discWidth, discHeight , Image.SCALE_SMOOTH)));
		lblDisc.setBounds(discX, discY, discWidth, discHeight);
		add(lblDisc);	
		while(true) {
			lblDisc.setLocation(discX,discY);
			discY+=diffDisc;
			if(discY==desktop.height-discHeight)
				diffDisc=-1;
			if(discY==0)
				diffDisc=1;
			try{
				Thread.sleep(boardSpeed);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			revalidate();
		}
	}
}

