package org.sindifisco.portal.api.entity.contabil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Balancete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	@Lob
	@Column(columnDefinition="mediumblob")
	private byte[] arquivo;
}
