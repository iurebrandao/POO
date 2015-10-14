package br.unb.cic.lp.gol;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import br.unb.cic.lp.gol.teste.Teste_JUnit;

@SuppressWarnings("serial")
 /* Classe que cria a matriz de botões, que funciona como o tabuleiro do jogo */
public class MatrixButton extends JButton {
	private int[][] fModel;
    private int fX;
    private int fY;
	private int tam_linha,tam_coluna;

    public MatrixButton(final int x, final int y, final int[][] model) {
        fX= x;
        fY= y;
        fModel= model;
        addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                fModel[fX][fY] = fModel[fX][fY] == 1 ? 0 : 1;
                updateNameFromModel();
            }
        });
        updateNameFromModel();
    }
    
    public int CelulaViva(){
    	if(fModel[fX][fY] == 1)
    		return 1;
    	else
    		return 0;
    }
    
    private void updateNameFromModel() {
    	if(fModel[fX][fY] == 1){
    		setBackground(Color.BLUE);
    	}
    	else
    		setBackground(Color.WHITE);
    }
    
    public boolean celulaviva(){
    	if(fModel[fX][fY] == 1)
    		return true;
    	else
    		return false;
    }
    
    public boolean setMatriz(GameController controller){
    	
    	boolean existe_celula;
    	tam_linha = fModel.length;
    	tam_coluna = fModel[0].length;
    	
    	for(int i=0;i<tam_linha;i++){
    		for(int j=0;j<tam_coluna;j++){
    			if(fModel[i][j] == 1){
    				controller.makeCellAlive(i,j);
    			}
    		}
    	}
    	existe_celula = controller.nextGeneration(fModel);
    	return existe_celula;
    }
    
    public int[][] getfModel(){
    	return fModel;
    }
}
