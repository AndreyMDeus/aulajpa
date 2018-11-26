package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {

		//Criando a conexão com o banco MySQL
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		
		//Inserindo os objetos criados no banco
		//Instanciando os objetos para inserção na tabela
		Pessoa p1 = new Pessoa(null,"Andrey Martins","andreydeus@gmail.com");
		Pessoa p2 = new Pessoa(null,"Leila Cristina","leilacristinasd@gmail.com");
		Pessoa p3 = new Pessoa(null,"Juliana Cristina","julianawolfgamers@gmail.com");
		em.getTransaction().begin();	
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);	
		em.getTransaction().commit();
		
		System.out.println("Pronto! Registros incluídos!");
		
		//Localizando um objeto no banco, para isto não é necessário uma transação
		
		Pessoa p4 = em.find(Pessoa.class, 2);
		System.out.println(p4);
		
		System.out.println("Pronto! Localizado o registro!");
		
		em.getTransaction().begin();
		Pessoa p5 = new Pessoa(null, "Teste de exclusão", "teste@teste");
		em.persist(p5);
		em.getTransaction().commit();
		
		//Para excluir um objeto no banco é necessário uma transação
		Pessoa p6 = em.find(Pessoa.class, 54);
		em.getTransaction().begin();
		em.remove(p6);
		em.getTransaction().commit();
		
		System.out.println("Pronto! Registro excluído!");		
		
		//Alterando um registro com o JPA
		Pessoa p8 = em.find(Pessoa.class, 15);
		p8.setNome("Voltei para Andrey o Nome");
		p8.setEmail("alterei@oemail.com");
		em.getTransaction().begin();
		em.persist(p8);
		em.getTransaction().commit();
		
		System.out.println(p8);
		System.out.println("Pronto! Registro 15 alterado com sucesso!");		
			
		em.close();
		emf.close();
		
		
	}

}
