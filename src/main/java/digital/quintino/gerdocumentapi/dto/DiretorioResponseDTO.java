package digital.quintino.gerdocumentapi.dto;

import digital.quintino.gerdocumentapi.domain.ArquivoDomain;
import digital.quintino.gerdocumentapi.domain.DiretorioDomain;

import java.util.ArrayList;
import java.util.List;

public class DiretorioResponseDTO {

    private String codigo;

    private String codigoDiretorioPai;

    private String nome;

    private List<DiretorioDomain> subdiretorioList = new ArrayList<>();

    private List<ArquivoDomain> arquivoDomainList = new ArrayList<>();

    public DiretorioResponseDTO() { }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoDiretorioPai() {
        return codigoDiretorioPai;
    }

    public void setCodigoDiretorioPai(String codigoDiretorioPai) {
        this.codigoDiretorioPai = codigoDiretorioPai;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<DiretorioDomain> getSubdiretorioList() {
        return subdiretorioList;
    }

    public void setSubdiretorioList(List<DiretorioDomain> subdiretorioList) {
        this.subdiretorioList = subdiretorioList;
    }

    public List<ArquivoDomain> getArquivoDomainList() {
        return arquivoDomainList;
    }

    public void setArquivoDomainList(List<ArquivoDomain> arquivoDomainList) {
        this.arquivoDomainList = arquivoDomainList;
    }

}
