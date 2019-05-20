package aplicacao;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Veiculo;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("prova-jpa");
		EntityManager em = emf.createEntityManager();
		Scanner s = new Scanner(System.in);
		Veiculo v = new Veiculo();
		int e = 0;
		do {
			System.out.println("1 � Listar Ve�culos cadastrados");
			System.out.println("2 � Buscar um Ve�culo pelo id");
			System.out.println("3 � Cadastrar Ve�culo");
			System.out.println("4 � Atualizar Ve�culo");
			System.out.println("5 � Remover um Ve�culo");
			System.out.println("0 � Sair");
			e = s.nextInt();
			switch (e) {
			case 1:
				v.listarVeiculos(em);
				break;
			case 2:
				v.buscarId(em);
				break;
			case 3:
				v.cadastrarVeiculo(em);
				break;
			case 4:
				v.atualizarVeiculo(em);
				break;
			case 5:
				v.removerVeiculo(em);
				break;
			case 0:
				break;
			default:
				System.out.println("Op��o Inv�lida!");
				break;
			}
		}while(e != 0);
		
		em.close();
		emf.close();
	}

}
