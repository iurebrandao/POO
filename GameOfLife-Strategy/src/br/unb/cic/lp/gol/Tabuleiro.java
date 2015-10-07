package br.unb.cic.lp.gol;

import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import javax.swing.*;

@SuppressWarnings("serial")
public class Tabuleiro extends JFrame{
	private JTextField rows;
	private JTextField columns;
	private JButton confirmacao;
	private String numRows, numColumns;
	private Font FonteUsual;
	private int numLinhas,numColunas;
	
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
		rows.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,Collections.EMPTY_SET);
		columns.addKeyListener( new myKeyListener() );
		columns.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,Collections.EMPTY_SET);
		
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
	
	private void Desenha_matriz(int dimRows,int dimColumns){
		
	    int matrix[][] = new int[dimRows][dimColumns];

	    JFrame f = new JFrame("Window containing a matrix");
	    JPanel p = new JPanel();
	    p.setLayout(new GridLayout(dimRows, dimColumns));

	    for(int r = 0; r < dimRows; r++){
	        for(int c = 0; c < dimColumns; c++){
	            MatrixButton button= new MatrixButton(r, c, matrix);
	            p.add(button);
	        }
	    }
	    f.add(p);
	    f.pack();
	    f.setVisible(true); 
	}
	
	public class ButtonHandler implements ActionListener{
		public void actionPerformed (ActionEvent event){
			if (event.getSource() == confirmacao) {

				numLinhas = Integer.parseInt(rows.getText());
				numColunas = Integer.parseInt(columns.getText());
				Tabuleiro.this.dispose();
				Desenha_matriz(numLinhas,numColunas);
				
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
