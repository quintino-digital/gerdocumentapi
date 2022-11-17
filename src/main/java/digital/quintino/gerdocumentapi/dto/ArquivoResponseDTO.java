package digital.quintino.gerdocumentapi.dto;

public class ArquivoResponseDTO {
	
	private String codigo;
	
	private String nome;
	
	private String tamanho;
	
	private String extencao;
	
	private String url;
	
	public ArquivoResponseDTO() { }

	public ArquivoResponseDTO(String codigo, String nome, String tamanho, String extencao, String url) {
		this.codigo = codigo;
		this.nome = nome;
		this.tamanho = tamanho;
		this.extencao = extencao;
		this.url = url;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getExtencao() {
		return extencao;
	}

	public void setExtencao(String extencao) {
		this.extencao = extencao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
