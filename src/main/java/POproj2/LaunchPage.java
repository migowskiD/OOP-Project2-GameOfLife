package POproj2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class LaunchPage implements ActionListener{
	JFrame frame = new JFrame();
	JButton newButton = new JButton("New Game");
	JButton loadButton = new JButton("Load Game");

	LaunchPage()
	{
		newButton.setFocusable(false);
		newButton.addActionListener(this);
		newButton.setPreferredSize(new Dimension(350,300));
		newButton.setFont(new Font("Comic Sans",Font.BOLD,25));
		loadButton.setFocusable(false);
		loadButton.addActionListener(this);
		loadButton.setPreferredSize(new Dimension(350,300));
		loadButton.setFont(new Font("Comic Sans",Font.BOLD,25));
		
		frame.add(newButton);
		frame.add(loadButton);
		frame.setTitle("Wirtualny Œwiat - Dawid Migowski 184819");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		frame.getContentPane().setBackground(Color.darkGray);
		frame.setSize(800,400);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==newButton)
		{
			frame.dispose();
			new World();
		}
		else if(e.getSource()==loadButton)
		{
			JFileChooser filechooser = new JFileChooser();
			filechooser.setCurrentDirectory(new File("."));
			int response = filechooser.showOpenDialog(null);
			if(response == JFileChooser.APPROVE_OPTION)
			{
				//System.out.println(filechooser.getSelectedFile().getAbsolutePath());
				try {
					frame.dispose();
					new World(filechooser.getSelectedFile().getAbsolutePath());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}
}
