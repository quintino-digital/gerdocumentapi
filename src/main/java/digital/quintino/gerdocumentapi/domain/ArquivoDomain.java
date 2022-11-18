package digital.quintino.gerdocumentapi.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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
	
	@Column(name = "NOME", nullable = false)
	private String nome;
	
	@Column(name = "TAMANHO", nullable = false)
	private String tamanho;
	
	@Column(name = "EXTENCAO", nullable = false)
	private String extencao;
	
	@Lob
	@Type(type = "org.hibernate.type.ImageType")
	@Column(name = "CONTEUDO", nullable = false)
	private byte[] conteudo;

	public ArquivoDomain() { }

	public ArquivoDomain(String nome, String tamanho, String extencao, byte[] conteudo) {
		this.nome = nome;
		this.tamanho = tamanho;
		this.extencao = extencao;
		this.conteudo = conteudo;
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
