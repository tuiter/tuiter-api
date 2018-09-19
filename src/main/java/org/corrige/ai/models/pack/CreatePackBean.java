package org.corrige.ai.models.pack;

import org.corrige.ai.enums.PackageType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePackBean {
	private String userId;
	private PackageType packageType;
}
