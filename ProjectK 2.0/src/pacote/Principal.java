package pacote;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal{

	public static void main(String[] args) {
		ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>(); 
		Scanner ler = new Scanner(System.in);
		int index=1;
		// MENU GERAL
		int menuADM = 0;
		do {
			System.out.println(
					" 1- Registrar revisão  \n 2- Listar veículos \n 3- Reparar \n 4- Remover veículo \n 5- Sair \n Qual opção deseja executar?");

			menuADM = ler.nextInt();
			switch (menuADM) {
			case 1:
				Veiculo.registrarRevisao();
				System.out.println("Veículo registrado!");
				break;
			case 2:

				Veiculo.obterDados();
				break;
			case 3:

				System.out.println("Digite o Id do veículo: ");
				int lerID = ler.nextInt();
				Veiculo.reparar(lerID);
				System.out.println("Reparado com sucesso!");
				break;
			case 4:

				System.out.println("Digite o Id do veículo: ");
				lerID = ler.nextInt();
				Veiculo.removerVeiculo(lerID);
				System.out.println("Veículo removido. \n ");
				break;
			}
		} while (menuADM > 0 && menuADM < 5);
	}

}





