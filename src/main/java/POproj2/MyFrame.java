package POproj2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.*;

public class MyFrame extends JFrame implements ActionListener, KeyListener{
	private JLabel[][] board;
	private Organism[][] map;
	private int height, width;
	private World w;
	private JButton saveGameB;
	private boolean pressed = false;
	JTextArea textarea;
	JScrollPane scroll;

	public MyFrame(World _w, Organism[][] _map, int _height, int _width){
		map=_map;
		height=_height;
		width=_width;
		w=_w;
		board = new JLabel[height][width];
		
		JPanel west = new JPanel();
		west.setLayout(new FlowLayout(FlowLayout.LEADING,1,1));
		west.setPreferredSize(new Dimension(631,631));
		west.setBackground(Color.black);
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				board[i][j]=new JLabel();
				if(map[i][j] == null)
				{
					ImageIcon image = new ImageIcon("src/img/ground.png");
					board[i][j].setIcon(image);
				}
				else
				{
					ImageIcon image = new ImageIcon(map[i][j].Draw());
					board[i][j].setIcon(image);
				}
				west.add(board[i][j]);
			}
		}
		textarea = new JTextArea(39, 48);
		textarea.setText(w.GetAClearEvents());
		textarea.setEditable(false);
		textarea.setFocusable(false);
		scroll = new JScrollPane(textarea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel east = new JPanel();
		east.setLayout(new FlowLayout(FlowLayout.LEADING,1,1));
		east.setPreferredSize(new Dimension(550,631));
		east.setBackground(Color.lightGray);
		east.add(scroll);
		
		saveGameB = new JButton();
		saveGameB.setPreferredSize(new Dimension(200,100));
		saveGameB.addActionListener(this);
		saveGameB.setText("Save game");
		saveGameB.setFocusable(false);
		saveGameB.setFont(new Font("Comic Sans",Font.BOLD,25));
		
		JTextArea info = new JTextArea(" Use ARROWS to move the man or E to use special skill! \n If Human is dead use E to go to the next round!");
		info.setEditable(false);
		info.setFocusable(false);
		
		JPanel south = new JPanel();
		south.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		south.setPreferredSize(new Dimension(1182,126));
		south.setBackground(Color.black);
		south.add(saveGameB);
		south.add(info);
		
		this.setLayout(new FlowLayout(FlowLayout.LEADING,1,1));
		this.setTitle("Wirtualny Œwiat - Dawid Migowski 184819");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(1200,800);
		//this.setSize(230,900);
		this.setFocusable(true);
		this.addKeyListener(this);
		this.getContentPane().setBackground(Color.darkGray);
		this.add(west);
		this.add(east);
		this.add(south);
		this.setVisible(true);
	}
	
	public void FrameRefresh()
	{
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				if(map[i][j] == null)
				{
					ImageIcon image = new ImageIcon("src/img/ground.png");
					board[i][j].setIcon(image);
				}
				else
				{
					ImageIcon image = new ImageIcon(map[i][j].Draw());
					board[i][j].setIcon(image);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == saveGameB)
		{
			try {
				w.SaveWorld();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println(e.getKeyCode());
		if(pressed==false && e.getKeyCode() == 37)
		{
			pressed=true;
			w.SetHumanDirection(37);
			w.TakeATurn();
			textarea.setText(w.GetAClearEvents());
			FrameRefresh();
		}
		else if(pressed == false && e.getKeyCode() == 38)
		{
			pressed=true;
			w.SetHumanDirection(38);
			w.TakeATurn();
			textarea.setText(w.GetAClearEvents());
			FrameRefresh();
		}
		else if(pressed == false && e.getKeyCode() == 39)
		{
			pressed=true;
			w.SetHumanDirection(39);
			w.TakeATurn();
			textarea.setText(w.GetAClearEvents());
			FrameRefresh();
		}
		else if(pressed == false && e.getKeyCode() == 40)
		{
			pressed=true;
			w.SetHumanDirection(40);
			w.TakeATurn();
			textarea.setText(w.GetAClearEvents());
			FrameRefresh();
		}
		else if(pressed == false && e.getKeyCode() == 69)
		{
			pressed=true;
			w.SetHumanDirection(69);
			w.TakeATurn();
			textarea.setText(w.GetAClearEvents());
			FrameRefresh();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println(e.getKeyCode()+ "released");
		if(e.getKeyCode() == 37)
		{
			pressed=false;
		}
		else if(e.getKeyCode() == 38)
		{
			pressed=false;
		}
		else if(e.getKeyCode() == 39)
		{
			pressed=false;
		}
		else if(e.getKeyCode() == 40)
		{
			pressed=false;
		}
		else if(e.getKeyCode() == 69)
		{
			pressed=false;
		}
		
	}
}
