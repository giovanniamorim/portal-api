package org.sindifisco;

public class UsuarioDTO {

    private String celular;
    private String rg;
    private String rgOrgaoExp;
    private String matricula;

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRgOrgaoExp() {
        return rgOrgaoExp;
    }

    public void setRgOrgaoExp(String rgOrgaoExp) {
        this.rgOrgaoExp = rgOrgaoExp;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
