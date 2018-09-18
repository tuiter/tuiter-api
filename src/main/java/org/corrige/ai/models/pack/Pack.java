package org.corrige.ai.models.pack;

import java.util.Date;

import org.corrige.ai.enums.PackageType;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "packs")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pack implements Comparable<Pack>{
	private String userId;
	private PackageType type;
	private Integer counter;
	private Date createdAt;
	
	@Override
	public int compareTo(Pack other) {
		if (this.createdAt.before(other.getCreatedAt()))
			return -1;
		return 1;
	}
}
