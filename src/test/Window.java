package test;

import java.awt.Toolkit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class Tlo extends javax.swing.JFrame{
	public Tlo(){
		super("Alley of Dead");
		JButton startNewGameButton;
		JLabel newGameLabel;
		setLayout(new BorderLayout());
	    JLabel background=new JLabel(new ImageIcon("H:\\Projekty\\test\\background.jpg"));
	    add(background);
	    background.setLayout(new FlowLayout());
	    newGameLabel=new JLabel("Nowa gra");
	    startNewGameButton=new JButton("START");
	    background.add(newGameLabel);
	    background.add(startNewGameButton);
	    startNewGameButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		System.exit(0);
	    	}
	    });
    }
	
}
public class Window {

	public static void main(String[] args) {
		///abra kadabra
		Tlo okienko = new Tlo();
		okienko.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.jpg"));
		okienko.setSize(1024,768);
		okienko.setResizable(false);
		okienko.setLocationRelativeTo(null);
		okienko.setDefaultCloseOperation(okienko.EXIT_ON_CLOSE);
		okienko.setVisible(true);
		
		
	}

}


