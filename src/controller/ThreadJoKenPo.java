package controller;

import java.util.concurrent.Semaphore;

public class ThreadJoKenPo extends Thread {

	//controlar jogadores
	private Semaphore s = new Semaphore(5);
	private int idJogador;
	private int time;
	private int pos = -1;
	
	//controlar as batalhas
	private static int batalhas;
	private static int[][] jogo = new int[2][5];
	private static int[][] duelosVencidos = new int[2][6];
	private static int[][] oponente = new int[2][5];
	private static int[] pontosTime = { 0, 0 };
	
	@Override
	public void run() {
		try {
			s.acquire();
			escolheOp();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			s.release();
			duelar();
		}
	}
	
	public ThreadJoKenPo(int id, int time) {
		this.idJogador = id;
		this.time = time;
	}

	private void escolheOp() {
		while (pos < 0) {
			int i = (int)(Math.random()* 5)+ 0;
			if(oponente[time][i] < 1) {
				oponente[time][i] = idJogador;
				pos = i;
			}
		}
	}

	private void duelar() {
		try {
			sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		if (time == 0) {
			System.out.println("Time 1, Jogador " + oponente[0][pos] + " contra " + "Time 2, Jogador " + oponente[1][pos]);
		}
		while ((duelosVencidos[0][pos] < 3) && (duelosVencidos[1][pos] < 3)) {
			jogo[time][pos] = (int) (Math.random() * 3) + 1;
			if (time == 0) {
				try {
					sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				switch (jogo[time][pos]) {
				case 1:
					switch (jogo[1][pos]) {
					case 1:
						System.out.println("Time 1, Jogador " + oponente[0][pos] + " - PEDRA | EMPATE | PEDRA - "
								+ "Time 2, Jogador " + oponente[1][pos]);
						break;
					case 2:
						System.out.println("Time 1, Jogador " + oponente[0][pos] + " - PEDRA | GANHOU Time 1, Jogador "
								+ oponente[0][pos] + " | TESOURA - " + "TimeB: Jogador#" + oponente[1][pos]);
						duelosVencidos[0][pos]++;
						duelosVencidos[0][5]++;
						break;
					case 3:
						System.out.println("TimeA: Jogador#" + oponente[0][pos] + " - PEDRA | GANHOU Time 2, Jogador "
								+ oponente[1][pos] + " | PAPEL - " + "Time 2, Jogador " + oponente[1][pos]);
						duelosVencidos[1][pos]++;
						duelosVencidos[1][5]++;
						break;
					}
					break;
				case 2:
					switch (jogo[1][pos]) {
					case 1:
						System.out.println("Time 1, Jogador " + oponente[0][pos] + " - TESOURA | GANHOU Time 2, Jogador "
								+ oponente[1][pos] + " | PEDRA - " + "Time 2, Jogador " + oponente[1][pos]);
						duelosVencidos[1][pos]++;
						duelosVencidos[1][5]++;
						break;
					case 2:
						System.out.println("Time 1, Jogador " + oponente[0][pos] + " - TESOURA | EMPATE | TESOURA - "
								+ "Time 2, Jogador " + oponente[1][pos]);
						break;
					case 3:
						System.out.println("Time 1, Jogador " + oponente[0][pos] + " - TESOURA | GANHOU Time 1, Jogador "
								+ oponente[0][pos] + " | PAPEL - " + "Time 2, Jogador " + oponente[1][pos]);
						duelosVencidos[0][pos]++;
						duelosVencidos[0][5]++;
						break;
					}
					break;
				case 3:
					switch (jogo[1][pos]) {
					case 1:
						System.out.println("Time 1, Jogador " + oponente[0][pos] + " - PAPEL | GANHOU Time 1, Jogador "
								+ oponente[0][pos] + " | PEDRA - " + "Time 2, Jogador " + oponente[1][pos]);
						duelosVencidos[0][pos]++;
						duelosVencidos[0][5]++;
						break;
					case 2:
						System.out.println("Time 1, Jogador " + oponente[0][pos] + " - PAPEL | GANHOU Time 2, Jogador "
								+ oponente[1][pos] + " | TESOURA - " + "Time 2, Jogador " + oponente[1][pos]);
						duelosVencidos[1][pos]++;
						duelosVencidos[1][5]++;
						break;
					case 3:
						System.out.println("Time 1, Jogador " + oponente[0][pos] + " - PAPEL | EMPATE | PAPEL - "
								+ "Time 2, Jogador " + oponente[1][pos]);
						break;
					}
					break;
				}
			}
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (time == 0) {
			if (duelosVencidos[0][pos] == 3) {
				System.out.println("PONTUAÇÕES: Time 1, Jogador " + oponente[0][pos] + "[" + duelosVencidos[0][pos]
						+ "] contra " + "Time 2, Jogador " + oponente[1][pos] + "[" + duelosVencidos[1][pos]
						+ "] acaba vencendo : Time 1, Jogador " + oponente[0][pos]);
				pontosTime[0]++;
			} else {
				System.out.println("PONTUAÇÕES: Time 1, Jogador " + oponente[0][pos] + "[" + duelosVencidos[0][pos]
						+ "] contra " + "Time 2, Jogador " + oponente[1][pos] + "[" + duelosVencidos[1][pos]
						+ "] acaba vencendo : Time 2, Jogador " + oponente[1][pos]);
				pontosTime[1]++;
			}
			batalhas++;
		}
		if (batalhas == 5) {
			System.out.println("PONTOS TOTAIS");
			System.out.println("Time 1 possui " + pontosTime[0] + " pontos e venceu "
					+ duelosVencidos[0][5] + " vezes " + "\nTime 2 possui " + pontosTime[1]
					+ " pontos e venceu " + duelosVencidos[1][5]+" vezes");
			if (pontosTime[0] > pontosTime[1]) {
				System.out.println("TIME 1 VENCEU");
			} else {
				System.out.println("TIME 2 VENCEU");
			}
		}
	}
}
