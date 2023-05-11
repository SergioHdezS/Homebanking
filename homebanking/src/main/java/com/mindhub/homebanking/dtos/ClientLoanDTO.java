package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.ClientLoan;

public class ClientLoanDTO {

    private Long clientLoanId;
    private Long loanId;
    private String name;
    private Float amount;
    private Integer payments;

    public ClientLoanDTO(ClientLoan clientLoan) {
        this.clientLoanId = clientLoan.getClientLoanId();
        this.loanId = clientLoan.getLoan().getLoanId();
        this.name = clientLoan.getLoan().getName();
        this.amount = clientLoan.getAmount();
        this.payments = clientLoan.getPayments();
    }

    public Long getClientLoanId() {
        return clientLoanId;
    }

    public void setClientLoanId(Long clientLoanId) {
        this.clientLoanId = clientLoanId;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getPayments() {
        return payments;
    }

    public void setPayments(Integer payments) {
        this.payments = payments;
    }
}
