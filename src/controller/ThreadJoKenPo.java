package controller;

import java.util.concurrent.Semaphore;
import view.Principal;

public class ThreadJoKenPo extends Thread {	
	private int ptA = 0;
	private int ptB = 0;
	
	private String jogA;
	private String jogB;

	private Semaphore s = new Semaphore(1);
	
	public ThreadJoKenPo(String jogadorA, String jogadorB) {
		this.jogA = jogadorA;
		this.jogB = jogadorB;
	}
	
	@Override
	public void run() {
		duelar();
	}

	private void duelar() {
		// se ganhador = 1
		// jogador A ganhou

		// se ganhador = 2
		// jogador B ganhou

		// se ganhador = 0
		// empate
		while(ptA<3&&ptB<3){
			int ganhador = 0;
			try {
				sleep(1000);
				ganhador = verJogada();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(ganhador == 1) ptA++;
			if(ganhador == 2) ptB++;
		}
		
		if(ptA>ptB) {
			System.out.println(jogA+" fez "+ptA+" pontos, time A ganhou essa rodada");
			Principal.totalA++;
		}else {
			System.out.println(jogB+" fez "+ptB+" pontos, time B ganhou essa rodada");
			Principal.totalB++;
		}
		System.out.println("TIME A POSSUI "+Principal.totalA+" PONTOS");
		System.out.println("TIME B POSSUI "+Principal.totalB+" PONTOS");		
	}

	private int verJogada() {
		int jogadaA = 0;
		int jogadaB = 0;
		String jogadas[] = { "pedra", "papel", "tesoura" };

		// 1 = pedra
		// 2 = papel
		// 3 = tesoura

		jogadaA = (int) ((Math.random() * 3) + 1);
		jogadaB = (int) ((Math.random() * 3) + 1);

		if (jogadaA == jogadaB) {
			System.out.println(jogA + " jogou " + jogadas[jogadaA-1] + " e " + jogB + " jogou " + jogadas[jogadaB-1] + "\n");
			System.out.println("EMPATE\n");
			return 0;
		} else if (jogadaA == 1 && jogadaB == 3) {
			System.out.println(jogA + " jogou " + jogadas[jogadaA-1] + " e " + jogB + " jogou " + jogadas[jogadaB-1] + "\n");
			System.out.println("GANHOU " + jogA);
			return 1;
		} else if (jogadaA == 2 && jogadaB == 1) {
			System.out.println(jogA + " jogou " + jogadas[jogadaA-1] + " e " + jogB + " jogou " + jogadas[jogadaB-1] + "\n");
			System.out.println("GANHOU " + jogA);
			return 1;
		} else if (jogadaA == 3 && jogadaB == 2) {
			System.out.println(jogA + " jogou " + jogadas[jogadaA-1] + " e " + jogB + " jogou " + jogadas[jogadaB-1] + "\n");
			System.out.println("GANHOU " + jogA);
			return 1;
		} else {
			System.out.println(jogA + " jogou " + jogadas[jogadaA-1] + " e " + jogB + " jogou " + jogadas[jogadaB-1] + "\n");
			System.out.println("GANHOU " + jogB);
			return 2;
		}

	}

}