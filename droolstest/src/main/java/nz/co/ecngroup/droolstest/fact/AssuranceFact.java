package nz.co.ecngroup.droolstest.fact;

import java.util.Date;

public class AssuranceFact {
    private Date saleDate;
    private Date faultDate;
    private String assurance;

    public String getAssurance() {
        return assurance;
    }

    public void setAssurance(final String assurance) {
        this.assurance = assurance;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(final Date saleDate) {
        this.saleDate = saleDate;
    }

    public Date getFaultDate() {
        return faultDate;
    }

    public void setFaultDate(final Date faultDate) {
        this.faultDate = faultDate;
    }
}
