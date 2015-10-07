package br.unb.cic.lp.gol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class MatrixButton extends JButton {
	private int[][] fModel;
    private int fX;
    private int fY;

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

    private void updateNameFromModel() {
        setText(String.valueOf(fModel[fX][fY]));
    }
}
