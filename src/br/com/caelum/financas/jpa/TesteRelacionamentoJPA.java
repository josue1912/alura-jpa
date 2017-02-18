package br.com.caelum.financas.jpa;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteRelacionamentoJPA {

	public static void main(String[] args) {

		Conta conta = new Conta();
		conta.setTitular("José dos Santos");
		conta.setBanco("Itau");
		conta.setAgencia("055");
		conta.setNumero("12345");
		conta.setSaldo(new BigDecimal(5000));

		Movimentacao mov = new Movimentacao();
		mov.setConta(conta);
		mov.setData(Calendar.getInstance());
		mov.setDescricao("Conta de luz");
		mov.setTipo(TipoMovimentacao.SAIDA);
		mov.setValor(new BigDecimal(330));
		
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		em.persist(conta);
		em.persist(mov);		
		em.getTransaction().commit();
		
		em.close();
	}
}
