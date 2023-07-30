package com.CockroachDb.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "client_data")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientData {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String firstname;
    private String surname;
}
