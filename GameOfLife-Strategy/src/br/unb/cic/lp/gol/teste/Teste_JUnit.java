package br.unb.cic.lp.gol.teste;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import br.unb.cic.lp.gol.*;

@RunWith(JUnit4.class )
public class Teste_JUnit {
	
	private final int tam_def_linha = 2,tam_def_coluna = 3;
	private final int tam_def_linha2 = 4, tam_def_coluna2 = 5;
	private final int i=0,j=0;
	private int numLinhas, numColunas,matriz[][];
	private MatrixButton button;
	private GameEngine engine = new GameEngine(tam_def_linha, tam_def_coluna, null);
	private GameController controller = new GameController();;
	
//	Verificar se o numero de linhas e colunas é o que realmente o usuario colocou na 
//	matriz de botoes (Tabuleiro)
	@Test
	public void TesteTamMatriz () {
		
		matriz = new int[tam_def_linha][tam_def_coluna];
		for(int i=0;i<tam_def_linha;i++){
			for(int j=0;j<tam_def_coluna;j++){
				button = new MatrixButton(i,j,matriz);
			}
		}
		
		matriz = button.getfModel();
		
		numLinhas = matriz.length;
		numColunas = matriz[0].length;
		Assert.assertEquals("erro no numero de linhas",numLinhas,tam_def_linha);
		Assert.assertEquals("erro no numero de colunas",numColunas,tam_def_coluna);
		
	}
	
//	Testar a classe GameEngine
	@Test
	public void TesteEngine(){
		
//		Testando o construtor
		Assert.assertEquals("erro no construtor",tam_def_linha, engine.getHeight());
		Assert.assertEquals("erro no construtor",tam_def_coluna, engine.getWidth());
		
//		Testando os metodos setHeight e setWidth
		engine.setHeight(tam_def_linha2);
		engine.setWidth(tam_def_coluna2);
		Assert.assertEquals("erro no setHeight",tam_def_linha2, engine.getHeight());
		Assert.assertEquals("erro no setWidth",tam_def_coluna2, engine.getWidth());
		
//		Testando se mudou o tamanho das linhas e colunas do teste acima
		Assert.assertNotEquals("erro ao mudar o tamanho da linha", tam_def_linha, engine.getHeight());
		Assert.assertNotEquals("erro ao mudar o tamanho da coluna", tam_def_coluna, engine.getWidth());
	}
	
//	Testar a classe GameController
	@Test
	public void TesteController(){

		//Testando o setEngine da classe Controller	
		controller.setEngine(engine);
		Assert.assertEquals("Erro no setEngine",controller.getEngine(),engine);
		
		// Testar se é gerado celulas vivas no lugar certo		
		controller.makeCellAlive(i, j); //i=0,j=0
		engine.isCellAlive(i, j);
		Assert.assertEquals("Erro ao gerar celulas vivas",engine.isCellAlive(i, j),true);
		
		// Testar se no resto da matriz, as celulas estão mortas		
		Assert.assertNotEquals("Erro ao verificar celula morta", engine.isCellAlive(0, 1), true);
		Assert.assertNotEquals("Erro ao verificar celula morta", engine.isCellAlive(1, 0), true);
		Assert.assertNotEquals("Erro ao verificar celula morta", engine.isCellAlive(1, 1), true);
		
	}
}
