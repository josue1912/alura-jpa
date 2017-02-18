package br.com.caelum.financas.jpa;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteJPA {

	public static void main(String[] args) {

		Conta conta = new Conta();
		conta.setTitular("José dos Santos");
		conta.setBanco("Itau");
		conta.setAgencia("055");
		conta.setNumero("12345");
		conta.setSaldo(new BigDecimal(5000));

		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		Conta conta2 = em.find(Conta.class, 3);
		em.getTransaction().commit();
		
		conta2.setTitular("Lionel Messi");
		em.getTransaction().begin();
		em.merge(conta2);
		em.getTransaction().commit();
		
		em.close();
	}
}
