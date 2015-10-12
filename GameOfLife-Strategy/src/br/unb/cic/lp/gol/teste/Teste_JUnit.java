package br.unb.cic.lp.gol.teste;

import java.io.ObjectInputStream.GetField;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.unb.cic.lp.gol.MatrixButton;

@RunWith(JUnit4.class )
public class Teste_JUnit {
	
	private final int tam_def_linha = 2,tam_def_coluna = 2;
	private int numLinhas, numColunas,matriz[][];
	MatrixButton button;
	
//	Verificar se o numero de linhas e colunas é o que realmente o usuario colocou
	@Test
	public void TamMatriz () {
		
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
	
}
