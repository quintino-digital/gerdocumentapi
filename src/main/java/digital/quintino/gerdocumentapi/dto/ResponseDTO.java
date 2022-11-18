package digital.quintino.gerdocumentapi.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import digital.quintino.gerdocumentapi.utility.DateUtility;

// TODO -- Incluir o tempo de processamento
public class ResponseDTO {
	
	@JsonProperty("Data")
	private String data;
	
	@JsonProperty("Mensagem")
	private String mensagem;
	
	@JsonProperty("Situação")
	private int situacao;
	
	public ResponseDTO() { }

	public ResponseDTO(String mensagem, int situacao) {
		this.data = DateUtility.formatarData(new Date(), DateUtility.FORMATO_DDMMAAAA);
		this.mensagem = mensagem;
		this.situacao = situacao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

}
