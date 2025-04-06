package org.sindifisco.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("sindifisco")
public class ApiProperty {

	private List<String> origensPermitidas = List.of(
			"https://app-47750.dc-us-1.absamcloud.com",
			"https://sindifisco.app.br"
	);

	private final Seguranca seguranca = new Seguranca();

	public List<String> getOrigensPermitidas() {
		return origensPermitidas;
	}

	public void setOrigensPermitidas(List<String> origensPermitidas) {
		this.origensPermitidas = origensPermitidas;
	}

	public Seguranca getSeguranca() {
		return seguranca;
	}

	public static class Seguranca {
		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
	}
}