package com.attornatus.teste.domain.exception;

/**
 * O objeto que representa o corpo da resposta das exceções lançadas.
 * 
 * @author Kiev Maia
 *
 */
public class CorpoException {

	private String dataHora;
	private String message;

	private CorpoException(final Builder builder) {
		this.setDataHora(builder.dataHora);
		this.setMessage(builder.message);
	}

	/**
	 * Método estático que chama o Builder.
	 *
	 * @return {@link CorpoException.Builder}
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder do objeto CorpoException.
	 */
	public static final class Builder {
		private String dataHora;
		private String message;

		/**
		 * Construtor privado
		 */
		private Builder() {
		}

		public Builder withDataHora(final String dataHora) {
			this.dataHora = dataHora;
			return this;
		}

		public Builder withMessage(final String message) {
			this.message = message;
			return this;
		}

		public CorpoException build() {
			return new CorpoException(this);
		}

	}

	/**
	 * 
	 * @return {@link String}: data e hora do lançamento da exceção.
	 */
	public String getDataHora() {
		return dataHora;
	}

	/**
	 * 
	 * @param {@link String}: data e hora do lançamento da exceção.
	 */
	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	/**
	 * 
	 * @return {@link String}: mensagem da exceção lançada.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 
	 * @param {@link String}: mensagem da exceção lançada.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
