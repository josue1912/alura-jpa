package br.com.caelum.financas.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteMovimentacaoConta {

	public static void main(String[] args) {
	
		EntityManager em = new JPAUtil().getEntityManager();
		
		Movimentacao mov = em.find(Movimentacao.class, 1);
		System.out.println(mov.getConta().getTitular());
		
		Conta conta = em.find(Conta.class,1);
		System.out.println(conta.getMovimentacoes().size());
		
		Query query = em.createQuery("select distinct c from Conta c join fetch c.movimentacoes");
		List<Conta> contas = query.getResultList();
	
		for(Conta c:contas){
			System.out.println("\nTitular: "+c.getTitular() + "\nNum. de movimentações: "+c.getMovimentacoes().size());
		}
		
		em.close();
	}	
}
