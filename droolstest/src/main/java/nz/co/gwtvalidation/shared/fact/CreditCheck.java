package nz.co.gwtvalidation.shared.fact;

import java.io.Serializable;

public class CreditCheck implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 875745101880657269L;

    private int applicantAge;
    private int monthsOfDeclined;
    private int declineSeverityLevel;

    public int getApplicantAge() {
        return applicantAge;
    }

    public void setApplicantAge(final int applicantAge) {
        this.applicantAge = applicantAge;
    }

    public int getMonthsOfDeclined() {
        return monthsOfDeclined;
    }

    public void setMonthsOfDeclined(final int monthsOfDeclined) {
        this.monthsOfDeclined = monthsOfDeclined;
    }

    public int getDeclineSeverityLevel() {
        return declineSeverityLevel;
    }

    public void setDeclineSeverityLevel(final int declineSeverityLevel) {
        this.declineSeverityLevel = declineSeverityLevel;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(final boolean approved) {
        this.approved = approved;
    }

    private String comment;
    private boolean approved;

}
