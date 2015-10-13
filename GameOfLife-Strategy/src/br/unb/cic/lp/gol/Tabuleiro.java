package br.unb.cic.lp.gol;

import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.TimerTask;

import javax.swing.*;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Tabuleiro extends JFrame {
	private JTextField rows, columns;
	private JButton confirmacao;
	private JLabel campo_cel, campo_geracoes;
	private String numRows, numColumns;
	private Font FonteUsual;
	private JButton next_generation, exit;
	private BorderLayout layout;
	private int numLinhas, numColunas;
	private JFrame f;
	private GameController controller;
	private GameEngine engine;
	MatrixButton button;
	private JPanel p, p2, p_inf;
	private int matrix[][];
	private int num_geracoes = 0,num_celulas, num_max = 50;
	private boolean existe_celula, systemPause = false;
	

	public Tabuleiro(GameController controller, GameEngine engine) {
		super("Tamanho do Tabuleiro");

		this.controller = controller;
		this.engine = engine;
		this.setLayout(null);

		FonteUsual = new Font("Serif", Font.PLAIN, 14);

		rows = new JTextField("Numero de Linhas", 2);
		rows.setFont(FonteUsual);
		rows.setBounds(10, 20, 170, 40);
		rows.setToolTipText("Insira aqui o numero de linhas do tabuleiro");
		add(rows);

		columns = new JTextField("Numero de Colunas", 2);
		columns.setFont(FonteUsual);
		columns.setBounds(200, 20, 170, 40);
		columns.setToolTipText("Insira aqui o numero de colunas do tabuleiro");
		add(columns);

		confirmacao = new JButton("Next");
		confirmacao.setBounds(120, 70, 170, 30);
		add(confirmacao);

		ButtonHandler handler = new ButtonHandler();

		confirmacao.addActionListener(handler);

		rows.addKeyListener(new myKeyListener());
		rows.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
		columns.addKeyListener(new myKeyListener());
		columns.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);

		ManipuladorMouse handler_mouse = new ManipuladorMouse();
		rows.addMouseListener(handler_mouse);
		columns.addMouseListener(handler_mouse);

	}

	private class myKeyListener implements KeyListener {
		public void keyPressed(KeyEvent event) {
			if (event.getKeyCode() == KeyEvent.VK_TAB) {
				if (event.getSource() == rows) {
					rows.setBackground(Color.WHITE);
					columns.setText("");
					columns.setBackground(Color.LIGHT_GRAY);
					columns.setFont(FonteUsual);
					columns.requestFocus();
				}
			}
		}

		public void keyTyped(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) {
		}
	}

	private void Desenha_matriz(int dimRows, int dimColumns) {

		matrix = new int[dimRows][dimColumns];

		f = new JFrame("Tabuleiro");

		p = new JPanel();
		p2 = new JPanel();
		 p_inf = new JPanel();

		p.setLayout(new GridLayout(dimRows, dimColumns));

		for (int r = 0; r < dimRows; r++) {
			for (int c = 0; c < dimColumns; c++) {
				button = new MatrixButton(r, c, matrix);
				button.setSize(500, 505);
				p.add(button);
			}
		}

		p2.setLayout(new FlowLayout());
		JButton exit = new JButton("Exit");
		p2.add(exit);
		JButton pause = new JButton("Pause");
		p2.add(pause);
		JButton continueJogo = new JButton("Continue");
		p2.add(continueJogo);
		JButton next_generation = new JButton("Next Generation");
		p2.add(next_generation);
		
		f.add(p,BorderLayout.NORTH);
		f.add(p_inf);
		f.add(p2,BorderLayout.SOUTH);
		f.setBounds(1,1,1000,600);
		
//		f.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		f.setVisible(true);
		num_geracoes = 0;
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				System.exit(0);
			}
		});

		next_generation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campo_cel = new JLabel("Numero de celulas vivas: "+num_celulas);
				campo_geracoes = new JLabel("Numero de geracoes: "+num_geracoes);
				p_inf.setLayout(new FlowLayout());
				p_inf.add(campo_cel);
				p_inf.add(campo_geracoes);
				
				Timer timer = new Timer(500, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
						if (!systemPause){
								
							existe_celula = button.setMatriz(controller);
							num_celulas = 0;
							p.removeAll();
							
							if (existe_celula)
								num_geracoes++;

							for (int r = 0; r < numLinhas; r++) {
								for (int c = 0; c < numColunas; c++) {
									button = new MatrixButton(r, c, matrix);
									num_celulas += button.CelulaViva();
									p.add(button);
								}
							}
							p.revalidate();
							p.repaint();
							
							p_inf.removeAll();
							campo_cel = new JLabel("Numero de celulas vivas: "+num_celulas);
							campo_geracoes = new JLabel("Numero de geracoes: "+num_geracoes);
							p_inf.add(campo_cel);
							p_inf.add(campo_geracoes);
							p_inf.revalidate();
							p_inf.repaint();
							if(!existe_celula){
								JOptionPane.showMessageDialog(null, "Todas as celulas estao mortas!", "0 Celulas Vivas", JOptionPane.INFORMATION_MESSAGE);
								JOptionPane.showMessageDialog(null, "Jogo Pausado", "Pausa", JOptionPane.WARNING_MESSAGE);
								systemPause = true;
							}
						}
					}
				});
				timer.start();
			}
		});

		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!systemPause){
					systemPause = true;
					
					JOptionPane.showMessageDialog(null, "Jogo Pausado", "Pausa", JOptionPane.WARNING_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "O jogo já está pausado!", "Pausa", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		

		continueJogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(systemPause){
					systemPause = false;
					JOptionPane.showMessageDialog(null, "Jogo Despausado", "Jogo", JOptionPane.WARNING_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "O jogo não está pausado!", "Pausa", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}

	public class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == confirmacao) {
				
				try {	
					numLinhas = Integer.parseInt(rows.getText());
					if(numLinhas > 60){
						JOptionPane.showMessageDialog(null, "Tamanho de linhas invalido ", "Excecao",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Digite numero(s) no campo Linha", "Excecao",
							JOptionPane.ERROR_MESSAGE);
					
				}
				try {
					numColunas = Integer.parseInt(columns.getText());
					Tabuleiro.this.dispose();
					if(numColunas > 60){
						JOptionPane.showMessageDialog(null, "Tamanho de colunas invalido", "Excecao",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Digite numero(s) no campo Coluna", "Excecao",
							JOptionPane.ERROR_MESSAGE);
				}

				engine.setHeight(numLinhas);
				engine.setWidth(numColunas);
				Desenha_matriz(numLinhas, numColunas);

			}
		}
	}

	private class ManipuladorMouse implements MouseListener {
		public void mousePressed(MouseEvent event) {
			rows.setBackground(Color.WHITE);
			columns.setBackground(Color.WHITE);

			if (event.getSource() == rows) {
				rows.setText("");
				rows.setBackground(Color.LIGHT_GRAY);
				rows.setFont(FonteUsual);
			}

			if (event.getSource() == columns) {
				columns.setText("");
				columns.setBackground(Color.LIGHT_GRAY);
				columns.setFont(FonteUsual);
			}

		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}
	}

}
