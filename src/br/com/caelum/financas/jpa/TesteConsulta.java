package br.com.caelum.financas.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConsulta {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		Conta conta = new Conta();
		conta.setId(2);
		
		Query query = em.createQuery("select m from Movimentacao m where m.conta = :pConta"
				+ " and m.tipo = :pTipo"
				+ " order by m.valor desc"
				);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.ENTRADA);
		
		List<Movimentacao> listaMovimentacao = query.getResultList();
		
		for(Movimentacao m: listaMovimentacao){
			System.out.println("\nDescricao: "+m.getDescricao());
			System.out.println("Valor....: "+m.getValor());
		}
		em.getTransaction().begin();
		
		
		em.getTransaction().commit();
		em.close();
	}
}
