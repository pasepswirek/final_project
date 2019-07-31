package pl.sda.model;

import lombok.Getter;

@Getter
public  enum AccountStatus {

    ACTIVE("ACTIVE"),
    NOTACTIVE("NOTACTIVE"),
    BLOCKED("BLOCKED");

    private final String accountStatus;

    AccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
}
