package digital.quintino.gerdocumentapi.dto;

import digital.quintino.gerdocumentapi.domain.DiretorioDomain;

import java.util.ArrayList;
import java.util.List;

public class DiretorioResponseDTO {

    private List<DiretorioDomain> diretorioRaizDomainList = new ArrayList<>();

    private List<DiretorioDomain> subdiretorioDiretorioRaizList = new ArrayList<>();

    public DiretorioResponseDTO() { }

    public List<DiretorioDomain> getDiretorioRaizDomainList() {
        return diretorioRaizDomainList;
    }

    public void setDiretorioRaizDomainList(List<DiretorioDomain> diretorioRaizDomainList) {
        this.diretorioRaizDomainList = diretorioRaizDomainList;
    }

    public List<DiretorioDomain> getSubdiretorioDiretorioRaizList() {
        return subdiretorioDiretorioRaizList;
    }

    public void setSubdiretorioDiretorioRaizList(List<DiretorioDomain> subdiretorioDiretorioRaizList) {
        this.subdiretorioDiretorioRaizList = subdiretorioDiretorioRaizList;
    }

}
