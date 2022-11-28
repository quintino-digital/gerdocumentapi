package digital.quintino.gerdocumentapi.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**

	create table tb_arquivo (
       	codigo  bigserial not null,
        conteudo oid not null,
        extencao varchar(255) not null,
        nome varchar(255) not null,
        tamanho varchar(255) not null,
        primary key (codigo)
    );

*/

@Entity
@Table(name = "TB_ARQUIVO")
public class ArquivoDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "CODIGO", nullable = false)
	private String codigo;

	@ManyToOne
	@JoinColumn(name = "ID_DIRETORIO")
	private DiretorioDomain diretorioDomain;
	
	@Column(name = "NOME", nullable = false)
	private String nome;
	
	@Column(name = "TAMANHO", nullable = false)
	private String tamanho;
	
	@Column(name = "EXTENCAO", nullable = false)
	private String extencao;
	
	@Lob
	@Column(name = "CONTEUDO", length = 999999999)
	private byte[] conteudo;

	@Column(name = "URL")
	private String url;

	public ArquivoDomain() { }

	public ArquivoDomain(String nome, String tamanho, String extencao, byte[] conteudo) {
		this.nome = nome;
		this.tamanho = tamanho;
		this.extencao = extencao;
		this.conteudo = conteudo;
	}

	public ArquivoDomain(String nome, String tamanho, String extencao, String url, DiretorioDomain diretorioDomain) {
		this.nome = nome;
		this.tamanho = tamanho;
		this.extencao = extencao;
		this.url = url;
		this.diretorioDomain = diretorioDomain;
	}

	public ArquivoDomain(String nome, String tamanho, String extencao, byte[] conteudo, String url) {
		this.nome = nome;
		this.tamanho = tamanho;
		this.extencao = extencao;
		this.conteudo = conteudo;
		this.url = url;
	}

	public DiretorioDomain getDiretorioDomain() {
		return diretorioDomain;
	}

	public void setDiretorioDomain(DiretorioDomain diretorioDomain) {
		this.diretorioDomain = diretorioDomain;
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

	public byte[] getConteudo() {
		return conteudo;
	}

	public void setConteudo(byte[] conteudo) {
		this.conteudo = conteudo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArquivoDomain other = (ArquivoDomain) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
}
