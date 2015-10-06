package br.unb.gol.poo;



import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TesteInterface extends JFrame {
	private JButton conway;
	private JButton highlife;
	private JButton livefreeordie;
	private JTextPane selecione;
	
	public TesteInterface(){
		
		super("Selecione a regra");
		setLayout(new FlowLayout());
		this.setLocation(400, 300);
		
		selecione = new JTextPane();
		add(selecione);
		
		conway = new JButton("Conway");
		add(conway);
		
		highlife = new JButton("Highlife");
		add(highlife);
		
		livefreeordie = new JButton("LiveFreeOrDie");
		add(livefreeordie);
		
		ButtonHandler handler = new ButtonHandler();
		conway.addActionListener(handler);
		highlife.addActionListener(handler);
		livefreeordie.addActionListener(handler);
		
		JOptionPane.showMessageDialog(null,"Bem-vindo ao GOL");
		
		JOptionPane.showMessageDialog(null, "Regras Conway: \n"
				+"1. Qualquer célula viva com menos de dois vizinhos morre por subpopulação.\n"
				+"2. Qualquer célula viva com dois ou três vizinhos vivos permanece viva.\n"
				+"3. Qualquer célula viva com mais de três vizinhos vivos morre por superpopulação.\n"
				+"4. Qualquer célula morta com exatamente três vizinhos vivos renasce.\n\n"
				+"Regras HighLife: \n"
				+"1. Qualquer célula viva com menos de dois vizinhos morre por subpopulação.\n"
				+"2. Qualquer célula viva com dois ou três vizinhos vivos permanece viva.\n"
				+"3. Qualquer célula viva com mais de três vizinhos vivos morre por superpopulação.\n"
				+"4. Qualquer célula morta com exatamente três ou seis vizinhos vivos renasce.\n\n"
				+"Regras LiveFreeOrDie: \n"
				+"1. Uma célula só permanece viva se não existirem vizinhos.\n"
				+"2. Qualquer célula morta com exatamente dois vizinhos vivos renasce.\n\n"
				+"Obs.: Células vivas = 1\nCélulas mortas = 0");
		
	
	}
	
	private class ButtonHandler implements ActionListener{
//		trata evento de botao
		public void actionPerformed(ActionEvent event){
			
			if(event.getSource() == conway ){
				TesteInterface.this.dispose();
				
				int dim=10;
			    int matrix[][] = new int[10][10];

			    JFrame f = new JFrame("Window containing a matrix");
			    JPanel p = new JPanel();
			    p.setLayout(new GridLayout(dim, dim));

			    for(int r = 0; r < dim; r++){
			        for(int c = 0; c < dim; c++){
			            MatrixButton button= new MatrixButton(r, c, matrix);
			            p.add(button);
			        }
			    }
			    f.add(p);
			    f.pack();
			    f.setVisible(true);
			}	
			if(event.getSource() == highlife ){
				TesteInterface.this.dispose();
				
				int dim=10;
			    int matrix[][] = new int[10][10];

			    JFrame f = new JFrame("Window containing a matrix");
			    JPanel p = new JPanel();
			    p.setLayout(new GridLayout(dim, dim));

			    for(int r = 0; r < dim; r++){
			        for(int c = 0; c < dim; c++){
			            MatrixButton button= new MatrixButton(r, c, matrix);
			            p.add(button);
			        }
			    }
			    f.add(p);
			    f.pack();
			    f.setVisible(true);
			}
			if(event.getSource() == livefreeordie ){
				TesteInterface.this.dispose();
				
				int dim=10;
			    int matrix[][] = new int[10][10];

			    JFrame f = new JFrame("Window containing a matrix");
			    JPanel p = new JPanel();
			    p.setLayout(new GridLayout(dim, dim));

			    for(int r = 0; r < dim; r++){
			        for(int c = 0; c < dim; c++){
			            MatrixButton button= new MatrixButton(r, c, matrix);
			            p.add(button);
			        }
			    }
			    f.add(p);
			    f.pack();
			    f.setVisible(true);
			}
		}
	}
}
