package pl.sda.model;

import lombok.Getter;

@Getter
public enum AccountType {

    NORMAL("NORMAL"),
    PREMIUM("PREMIUM");

    private final String accountType;

    AccountType(String accountType) {
        this.accountType = accountType;
    }
}
