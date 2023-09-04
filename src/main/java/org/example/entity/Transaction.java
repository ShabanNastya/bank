package org.example.entity;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private UUID uuid;
    private Date createdAt;
    private double amount;
    private int toAccount;
    private String bankFrom;
    private String bankTo;
}
