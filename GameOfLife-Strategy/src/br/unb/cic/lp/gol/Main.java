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

		GameEngine engine = new GameEngine(10, 10, statistics);	
		
		//nessa implementacao, a estrategia do Conway eh 
		//configurada como a estrategia inicial. 
		engine.setEstrategia(new Conway());
		
		
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
		GameView board = new GameView(controller, engine);
		
		
		controller.setBoard(board);
		controller.setEngine(engine);
		controller.setStatistics(statistics);
		
		controller.start();
	}
}
