package br.unb.cic.lp.gol;

import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import javax.swing.*;

import br.unb.cic.lp.gol.teste.Teste_JUnit;

@SuppressWarnings("serial")
public class Tabuleiro extends JFrame{
	private JTextField rows,columns;
	private JButton confirmacao;
	private String numRows, numColumns;
	private Font FonteUsual;
	private JButton next_generation,exit;
	private BorderLayout layout;
	private int numLinhas,numColunas;
	private JFrame f;
	private GameController controller;
	private GameEngine engine;
	MatrixButton button;
	private JPanel p;
	private int matrix[][];
	
	public Tabuleiro(GameController controller, GameEngine engine){
		super("Tamanho do Tabuleiro");
		
		this.controller = controller;
		this.engine = engine;
		this.setLayout(null);
		
		FonteUsual = new Font("Serif",Font.PLAIN,14);
		
		rows = new JTextField("Numero de Linhas",2);
		rows.setFont(FonteUsual);
		rows.setBounds(10,20,170,40);
		rows.setToolTipText("Insira aqui o numero de linhas do tabuleiro");
		add(rows);
		
		columns = new JTextField("Numero de Colunas",2);
		columns.setFont(FonteUsual);
		columns.setBounds(200,20,170,40);
		columns.setToolTipText("Insira aqui o numero de colunas do tabuleiro");
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
		
	    matrix = new int[dimRows][dimColumns];
	    
	    f = new JFrame("Tabuleiro");
	    
	    p = new JPanel();
	    JPanel p2 = new JPanel();

	    p.setLayout(new GridLayout(dimRows, dimColumns));

	    for(int r = 0; r < dimRows; r++){
	        for(int c = 0; c < dimColumns; c++){
	            button = new MatrixButton(r, c, matrix);
	            button.setBackground(Color.WHITE);
	            p.add(button);
	        }
	    }
	    
	    p2.setLayout(new FlowLayout());
	    JButton exit = new JButton("Exit");
	    p2.add(exit);
	    JButton pause = new JButton("Pause");
	    p2.add(pause);
	    JButton next_generation = new JButton("Next Generation"); 
	    p2.add(next_generation);
	    
	    f.add(p,BorderLayout.NORTH);
	    f.add(p2,BorderLayout.SOUTH);
	    f.setBounds(200,70,1000,600);
	    f.setVisible(true);
	    
		exit.addActionListener(new ActionListener (){
			public void actionPerformed (ActionEvent e){
				f.dispose();
			}
		});
		
		next_generation.addActionListener(new ActionListener (){
			public void actionPerformed (ActionEvent e){
				int num_geracoes=0,num_max = 50;
				boolean existe_celula;
				
				do{
					existe_celula = button.setMatriz(controller);
					if(existe_celula)
						num_geracoes++;
					p.removeAll();
					
					for(int r = 0; r < numLinhas; r++){
				        for(int c = 0; c < numColunas; c++){
				        	button = new MatrixButton(r, c, matrix);
				        	button.setBackground(Color.WHITE);				            
				            p.add(button);
				        }
				    }
					p.revalidate();
					p.repaint();
					
				}while(existe_celula && num_geracoes < num_max);
				
				if(!existe_celula)
					JOptionPane.showMessageDialog(null,"Nenhuma celula viva no tabuleiro!.","Sem celula", JOptionPane.WARNING_MESSAGE);
				if(num_geracoes >= num_max)
					JOptionPane.showMessageDialog(null,"Numero maximo de geracoes atingido!","Limite maximo",JOptionPane.WARNING_MESSAGE);
				JOptionPane.showMessageDialog(null,"Numero de Geracoes geradas: "+num_geracoes);
			}
		});
		
		pause.addActionListener(new ActionListener (){
			public void actionPerformed (ActionEvent e){
				
			}
		});
	}
	
	public class ButtonHandler implements ActionListener{
		public void actionPerformed (ActionEvent event){
			if (event.getSource() == confirmacao) {

				numLinhas = Integer.parseInt(rows.getText());
				numColunas = Integer.parseInt(columns.getText());
				engine.setHeight(numLinhas);
				engine.setWidth(numColunas);
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
