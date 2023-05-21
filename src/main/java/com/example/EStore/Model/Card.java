package com.example.EStore.Model;

import com.example.EStore.Enum.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name="card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true,nullable = false)
    String cardNo;

    @Enumerated(EnumType.STRING)
    CardType cardType;

    int cvv;

    Date validTill;

    /*Established parent-child relationship */

    @ManyToOne
    @JoinColumn
    Customer customer;
}
