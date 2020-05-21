package view;

import controller.ThreadJoKenPo;

/*
 * 2 times de 5 integrantes
 * Um duelo de Jokenpo
 * Melhor de 5, simultaneos, aleatorizando a disputa
 * Quem fizer 3 pontos ganha
 * Mostrar os ganhadores
 */

public class Principal {
	public static void main (String [] args)
	{
		for (int i = 1; i <= 5; i++) {
			Thread td = new ThreadJoKenPo(i, 0);
			td.start();
		}
		for (int i = 1; i <= 5; i++) {
			Thread td = new ThreadJoKenPo(i, 1);
			td.start();
		}
	}
}