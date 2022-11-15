package digital.quintino.gerdocumentapi.model;

import javax.persistence.Lob;
import java.io.Serializable;
import java.util.UUID;

public class DocumentoModel implements Serializable {

    private UUID codigo;

    private String nome;

    private String tamanho;

    private String extencao;

    private Lob conteudo;

    public DocumentoModel() { }

    public UUID getCodigo() {
        return codigo;
    }

    public void setCodigo(UUID codigo) {
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

    public Lob getConteudo() {
        return conteudo;
    }

    public void setConteudo(Lob conteudo) {
        this.conteudo = conteudo;
    }

}
