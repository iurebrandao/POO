package br.unb.cic.lp.gol;

import javax.swing.JOptionPane;

import br.unb.cic.lp.gol.estrategias.Conway;

/**
 * Classe que define o metodo principal do programa; ou 
 * seja, o metodo que vai ser executado pela JVM para inicializar 
 * o programa. 
 * 
 * @author rbonifacio
 */
public class Main {

	public static void main(String args[]) {
		
		GameController controller = new GameController();
		
		Statistics statistics = new Statistics();
		
		GameEngine engine = new GameEngine(60, 60, statistics);	
		
		//nessa implementacao, a estrategia do Conway eh 
		//configurada como a estrategia inicial. 
		engine.setEstrategia(new Conway());
		regras();
		GameView board = new GameView(controller, engine);
		
		controller.setBoard(board);
		controller.setEngine(engine);
		controller.setStatistics(statistics);
		
	}
	
	public static void regras(){
		JOptionPane.showMessageDialog(null,"Bem-vindo ao GOL");
		JOptionPane.showMessageDialog(null, "Regras Conway: \n"
				+"1. Qualquer celula viva com menos de dois vizinhos morre por subpopulacao.\n"
				+"2. Qualquer celula viva com dois ou tres vizinhos vivos permanece viva.\n"
				+"3. Qualquer celula viva com mais de tres vizinhos vivos morre por superpopulacao.\n"
				+"4. Qualquer celula morta com exatamente tres vizinhos vivos renasce.\n\n"
				+"Regras HighLife: \n"
				+"1. Qualquer celula viva com menos de dois vizinhos morre por subpopulacao.\n"
				+"2. Qualquer celula viva com dois ou tres vizinhos vivos permanece viva.\n"
				+"3. Qualquer celula viva com mais de tres vizinhos vivos morre por superpopulacao.\n"
				+"4. Qualquer celula morta com exatamente tres ou seis vizinhos vivos renasce.\n\n"
				+"Regras LiveFreeOrDie: \n"
				+"1. Uma celula so permanece viva se nao existirem vizinhos.\n"
				+"2. Qualquer celula morta com exatamente dois vizinhos vivos renasce.\n\n"
				+"Botões de opcões do tabuleiro:\n"
				+ "- Exit: Aperte essa tecla para sair do jogo.\n"
				+ "- Pause: Aperte essa tecla para pausar o jogo.\n"
				+ "- Continue: Aperte essa tecla para continuar o jogo.\n"
				+ "- Next Generation: Aperte essa tecla para comecar o jogo (Só aperte essa tecla uma vez).\n"
				+"\nObs.: Celulas vivas = Quadrado azul\nCelulas mortas = Quadrado Branco");
		
	}
}
