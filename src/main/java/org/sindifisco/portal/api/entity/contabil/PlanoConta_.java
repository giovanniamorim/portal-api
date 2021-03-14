package org.sindifisco.portal.api.entity.contabil;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PlanoConta.class)
public class PlanoConta_ {
	
	public static volatile SingularAttribute<PlanoConta, Integer> id;
	public static volatile SingularAttribute<PlanoConta, String> conta;
	public static volatile SingularAttribute<PlanoConta, String> descricao;
	public static volatile SingularAttribute<PlanoConta, TipoLancamentoEnum> tipoLancamento;
	public static volatile SingularAttribute<PlanoConta, PlanoConta> contaPai;
	public static volatile SingularAttribute<PlanoConta, String> profundidade;
	public static volatile SingularAttribute<PlanoConta, String> anoExercicio;

}
