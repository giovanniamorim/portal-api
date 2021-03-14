package org.sindifisco.portal.api.entity.security;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Grupo.class)
public class Usuario_ {

	public static volatile SingularAttribute<Usuario, String> nome;
	public static volatile SingularAttribute<Usuario, String> email;
	public static volatile SingularAttribute<Usuario, Grupo> grupos;
	public static volatile SingularAttribute<Usuario, Boolean> ativo;
}

