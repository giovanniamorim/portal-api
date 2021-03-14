package org.sindifisco.portal.api.entity.contabil;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Lancamento.class)
public class Lancamento_ {
	
	public static volatile SingularAttribute<Lancamento, TipoLancamentoEnum> tipo;
	public static volatile SingularAttribute<Lancamento, LocalDate> data;
	public static volatile SingularAttribute<Lancamento, PlanoConta> conta;
	public static volatile SingularAttribute<Lancamento, BigDecimal> valor;
	public static volatile SingularAttribute<Lancamento, ModoPagamento> modoPagamento;
	public static volatile SingularAttribute<Lancamento, TipoDocumento> tipoDocumento;
	public static volatile SingularAttribute<Lancamento, String> numCheque;
	public static volatile SingularAttribute<Lancamento, String> observacoes;
	public static volatile SingularAttribute<Lancamento, String> anoExercicio;
	public static volatile SingularAttribute<Lancamento, byte[]> imgComprovante;

}