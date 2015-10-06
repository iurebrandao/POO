package br.unb.gol.poo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;

import javax.swing.*;


@SuppressWarnings("serial")
public class Tabuleiro extends JFrame{
	private JTextField rows;
	private JTextField columns;
	private JButton confirmacao;
	private String numRows, numColumns;
	private Font FonteUsual;
	
	public Tabuleiro(){
		super("Tamanho do Tabuleiro");
		
		this.setLayout(null);
		this.setLocation(530,140);
		
		FonteUsual = new Font("Serif",Font.PLAIN,14);
		
		
		rows = new JTextField("Número de Linhas",2);
		rows.setFont(FonteUsual);
		rows.setBounds(10,20,170,40);
		rows.setToolTipText("Insira aqui o número de linhas do tabuleiro");
		add(rows);
		
		columns = new JTextField("Número de Colunas",2);
		columns.setFont(FonteUsual);
		columns.setBounds(200,20,170,40);
		columns.setToolTipText("Insira aqui o número de colunas do tabuleiro");
		add(columns);
		
		confirmacao = new JButton("Next");
		confirmacao.setBounds(120,70,170,30);
		add(confirmacao);
		
		ButtonHandler handler = new ButtonHandler();
		
		confirmacao.addActionListener(handler);
		
		rows.addKeyListener( new myKeyListener() );
		rows.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,Collections.emptySet());
		columns.addKeyListener( new myKeyListener() );
		columns.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,Collections.emptySet());
		
		ManipuladorMouse handler_mouse = new ManipuladorMouse();
		rows.addMouseListener(handler_mouse);
		columns.addMouseListener(handler_mouse);
	}
	
	private class myKeyListener implements KeyListener{
		public void keyPressed(KeyEvent event){
			if ( event.getKeyCode() == KeyEvent.VK_TAB ) {
	    		  if(event.getSource() == rows){
	    			  rows.setBackground(Color.WHITE);
		    		  columns.setText("");
		    		  columns.setBackground(Color.LIGHT_GRAY);
		    		  columns.setFont(FonteUsual);
		    		  columns.requestFocus();
	    		  }
			}
		}
		public void keyTyped(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}
	}
	public class ButtonHandler implements ActionListener{
		public void actionPerformed (ActionEvent event){
			if (event.getSource() == confirmacao) {
				
				numRows = rows.getText();
				numColumns = columns.getText();
			}
		}
	}
	
	private class ManipuladorMouse implements MouseListener{
		public void mousePressed(MouseEvent event){
			rows.setBackground(Color.WHITE);
			columns.setBackground(Color.WHITE);
			
			if (event.getSource() == rows){
				rows.setText("");
				rows.setBackground(Color.LIGHT_GRAY);
				rows.setFont(FonteUsual);
			}
			
			if (event.getSource() == columns){
				columns.setText("");
				columns.setBackground(Color.LIGHT_GRAY);
				columns.setFont(FonteUsual);
			}
			
		}
		public void mouseClicked(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
	}
}
