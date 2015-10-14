package br.unb.cic.lp.gol;

import java.util.Scanner;

import javax.swing.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
@SuppressWarnings({ "serial", "unused" })
public class GameView extends JFrame {
	
	private static final int CONWAY = 1;
	private static final int HIGH_LIFE = 2;
	private static final int LIVE_FREE_OR_DIE = 3;

	private JButton conway;
	private JButton highlife;
	private JButton livefreeordie;
	private JOptionPane frame;
	private JLabel selecione;
	private GameEngine engine;
	private GameController controller;
	private EstrategiaDeDerivacao StrategyConway;
	private EstrategiaDeDerivacao StrategyHighlife;
	private EstrategiaDeDerivacao StrategyLive;
	private int option;


	/**
	 * Construtor da classe GameBoard
	 */
	public GameView(GameController controller, GameEngine engine) {
		
		super("Regras");
		this.controller = controller;
		this.engine = engine;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(550,200,350,250);
		
		this.setVisible(true);
		this.setLayout(null);
		
		selecione = new JLabel("Selecione uma das regras para o jogo:");
		selecione.setBounds(60, 30, 300, 30);
		add(selecione);
		
		conway = new JButton("Conway");
		add(conway);
		conway.setBounds(30,80,120,40);
		highlife = new JButton("Highlife");
		add(highlife);
		highlife.setBounds(170,80,120,40);
		
		livefreeordie = new JButton("LiveFreeOrDie");
		add(livefreeordie);
		livefreeordie.setBounds(100,140,120,40);
		
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
				Tabuleiro frame = new Tabuleiro(controller,engine);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setBounds(500,150,400,250);
				frame.setVisible(true);
				setStrategy(1);
			}		
			
			if(event.getSource() == highlife){
				GameView.this.dispose();
				Tabuleiro frame = new Tabuleiro(controller,engine);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setBounds(500,150,400,250);
				frame.setVisible(true);
				setStrategy(2);
			}
			if(event.getSource() == livefreeordie){
				GameView.this.dispose();
				Tabuleiro frame = new Tabuleiro(controller,engine);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setBounds(500,150,400,250);
				frame.setVisible(true);
				setStrategy(3); 
				
			}
		}
	}

	/**
	 * Atualiza o componente view (representado pela classe GameBoard),
	 * possivelmente como uma resposta a uma atualizacao do jogo.
	 */
	public boolean update(int [][]fModel) {
		boolean existe_celula = false;
		for (int i = 0; i < engine.getHeight(); i++) {
			for (int j = 0; j < engine.getWidth(); j++) {
				if(engine.isCellAlive(i, j)){ 
					fModel[i][j] = 1;
					existe_celula = true;
				}
				else
					fModel[i][j] = 0;
			}
		}
		return existe_celula;
	}

	public void setStrategy(int option) {
		// Injecao de dependencia utilizando Spring Framework para as regras do jogo
		ApplicationContext context = new ClassPathXmlApplicationContext("gameOfLife.xml");
		StrategyConway = (EstrategiaDeDerivacao) context.getBean("conway");
		
		StrategyHighlife = (EstrategiaDeDerivacao) context.getBean("highLife");
		
		StrategyLive = (EstrategiaDeDerivacao) context.getBean("liveFreeOrDie");
		
		this.option = option;
		switch(option) {
			case CONWAY : engine.setEstrategia(StrategyConway); break;
			case HIGH_LIFE : engine.setEstrategia(StrategyHighlife);break;
			case LIVE_FREE_OR_DIE : engine.setEstrategia(StrategyLive); break;
		}
	}
	
	public EstrategiaDeDerivacao getStrategy() {
		
		switch(option) {
			case CONWAY : return StrategyConway;
			case HIGH_LIFE : return StrategyHighlife;
			case LIVE_FREE_OR_DIE : return StrategyLive;
		}
		return StrategyConway;
	}
	
	public void makeCellAlive(int i,int j) {
		
		controller.makeCellAlive(i, j);
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
