package br.com.caelum.financas.jpa;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteMovimentacaoConta {

	public static void main(String[] args) {
	
		EntityManager em = new JPAUtil().getEntityManager();
		
		Movimentacao mov = em.find(Movimentacao.class, 1);
		System.out.println(mov.getConta().getTitular());
		
		Conta conta = em.find(Conta.class,1);
		System.out.println(conta.getMovimentacoes().size());
		
		//Usando DAO
		MovimentacaoDao dao = new MovimentacaoDao(em);
		Double mediaDaContaPeloTipo = dao.mediaDaContaPeloTipo(conta, TipoMovimentacao.ENTRADA);
		System.out.println("Com DAO: "+mediaDaContaPeloTipo);
		
		
		//Usando NamedQuery
		TypedQuery<Double> query = em.createNamedQuery("mediaDaContaPeloTipo", Double.class);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.ENTRADA);
		Double media = query.getSingleResult();
		System.out.println("Com NamedQuery: "+media);
		
		
		em.close();
	}	
}
