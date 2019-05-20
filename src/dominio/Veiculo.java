package dominio;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Veiculo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String marca;
	private String modelo;
	
	public Veiculo(Integer id, String marca, String modelo) {
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
	}
	
	public Veiculo() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public void listarVeiculos(EntityManager em) {
		String jpql = "SELECT v FROM Veiculo v";
		List<Veiculo> veiculos = em.createQuery(jpql, Veiculo.class).getResultList();
		System.out.println(veiculos);		
	}
	public void buscarId(EntityManager em) {
		Scanner s = new Scanner(System.in);
		System.out.print("Digite o id: ");
		int id = s.nextInt();
		Veiculo v = em.find(Veiculo.class, id);
		if(v == null) {
			System.out.println("id não cadastrado!");
		}
		else {
			System.out.println("Veículo encontrado!");
			System.out.println(v);
		}
	}
	public void cadastrarVeiculo(EntityManager em) {
		Scanner s = new Scanner(System.in);
		System.out.println("Digite a marca: ");
		String marca = s.next();
		System.out.println("Digite o modelo: ");
		String modelo = s.next();
		em.getTransaction().begin();
		Veiculo veiculo = new Veiculo(null, marca, modelo);
		em.persist(veiculo);
		em.getTransaction().commit();
		System.out.println("Veículo cadastrado!");
	}
	public void atualizarVeiculo(EntityManager em) {
		Scanner s = new Scanner(System.in);
		System.out.print("Digite o id: ");
		int id1 = s.nextInt();
		Veiculo vFound = em.find(Veiculo.class, id1);
		if(vFound == null) {
			System.out.println("id não cadastrado!");
		}
		else {
			System.out.println("Escolha o atributo a ser atualizado:");
			System.out.println("1 - marca");
			System.out.println("2 - modelo");
			int e1 = s.nextInt();
			switch (e1) {
			case 1:
				String novaMarca = s.next();
				vFound.setMarca(novaMarca);
				em.getTransaction().begin();
				em.persist(vFound);
				em.getTransaction().commit();
				System.out.println("Atualizado!");
				break;
			case 2:
				String novoModelo = s.next();
				vFound.setModelo(novoModelo);
				em.getTransaction().begin();
				em.persist(vFound);
				em.getTransaction().commit();
				System.out.println("Atualizado!");
				break;
			default:
				System.out.println("Opção Inválida!");
				break;
			}
		}
	}
	public void removerVeiculo(EntityManager em) {
		Scanner s = new Scanner(System.in);
		System.out.println("Digite o id: ");
		int id2 = s.nextInt();
		Veiculo vFound = em.find(Veiculo.class, id2);
		if(vFound == null) {
			System.out.println("id não cadastrado!");
		}
		else {
			em.getTransaction().begin();
			em.remove(vFound);
			em.getTransaction().commit();
			System.out.println("Removido!");
		}
	}
	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", marca=" + marca + ", modelo=" + modelo + "]";
	}
	
}
