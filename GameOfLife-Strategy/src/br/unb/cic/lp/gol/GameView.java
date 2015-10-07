package br.unb.cic.lp.gol;

import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.unb.cic.lp.gol.estrategias.Conway;
import br.unb.cic.lp.gol.estrategias.HighLife;
import br.unb.cic.lp.gol.estrategias.LiveFreeOrDie;

/**
 * Atua como um componente de apresentacao (view), exibindo o estado atual do
 * game com uma implementacao baseada em caracteres ASCII.
 * 
 * @author rbonifacio
 */
public class GameView extends JFrame {
	private static final String LINE = "+-----+";
	private static final String DEAD_CELL = "|     |";
	private static final String ALIVE_CELL = "|  o  |";
	
	private static final int CONWAY = 1;
	private static final int HIGH_LIFE = 2;
	private static final int LIVE_FREE_OR_DIE = 3;
	private static final int MAKE_CELL_ALIVE = 4;
	private static final int NEXT_GENERATION = 5;
	private static final int HALT = 6;
	private static final int EXIT = 7;
	
	private JButton conway;
	private JButton highlife;
	private JButton livefreeordie;
	private JOptionPane frame;

	private GameEngine engine;
	private GameController controller;

	/**
	 * Construtor da classe GameBoard
	 */
	public GameView(GameController controller, GameEngine engine) {
		super("Selecione a regra");
		this.controller = controller;
		this.engine = engine;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(275,110);
		this.setVisible(true);
		setLayout(new FlowLayout());
		this.setLocation(400, 300);
		
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

	}
	
	private class ButtonHandler implements ActionListener{
//		trata evento de botao
		public void actionPerformed(ActionEvent event){
			
			if(event.getSource() == conway  ){
				GameView.this.dispose();
				Tabuleiro frame = new Tabuleiro();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(400,500);
				frame.setVisible(true);
				setStrategy(1);
			}		
			
			if(event.getSource() == highlife){
				GameView.this.dispose();
				Tabuleiro frame = new Tabuleiro();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(400,500);
				frame.setVisible(true);
				setStrategy(2);
			}
			if(event.getSource() == livefreeordie){
				GameView.this.dispose();
				Tabuleiro frame = new Tabuleiro();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(400,500);
				frame.setVisible(true);
				setStrategy(3); // Arrumar para ter livefreeordie!!!
			}
		}
	}

	/**
	 * Atualiza o componente view (representado pela classe GameBoard),
	 * possivelmente como uma resposta a uma atualizacao do jogo.
	 */
	public void update(boolean existe_celula) {
		
		for (int i = 0; i < engine.getHeight(); i++) {
			for (int j = 0; j < engine.getWidth(); j++) {
				System.out.print(engine.isCellAlive(i, j) ? ALIVE_CELL : DEAD_CELL);
			}
			System.out.println("   " + i);
			
		}
		
	}

	private void setStrategy(int option) {
		
		switch(option) {
			case MAKE_CELL_ALIVE : makeCellAlive(); break;
			case NEXT_GENERATION : nextGeneration(); break;
			case CONWAY : engine.setEstrategia(new Conway()); update(false); break;
			case HIGH_LIFE : engine.setEstrategia(new HighLife()); update(false);break;
			case LIVE_FREE_OR_DIE : engine.setEstrategia(new LiveFreeOrDie()); update(false);break;
			case HALT : halt();
			case EXIT: System.exit(0);
		}
	}
	
	private void makeCellAlive() {
		int i, j = 0;
		Scanner s = new Scanner(System.in);
		
		do {
			System.out.print("\n Inform the row number (0 - " + engine.getHeight() + "): " );
			
			i = s.nextInt();
			
			System.out.print("\n Inform the column number (0 - " + engine.getWidth() + "): " );
			
			j = s.nextInt();
		}while(!validPosition(i,j));
		
		controller.makeCellAlive(i, j);
	}
	
	private void nextGeneration() {
		controller.nextGeneration();	
	}
	
	private void halt() {
		controller.halt();
	}
	
	private boolean validPosition(int i, int j) {
		System.out.println(i);
		System.out.println(j);
		return i >= 0 && i < engine.getHeight() && j >= 0 && j < engine.getWidth();
	}

}
