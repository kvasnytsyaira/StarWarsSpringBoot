package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.UUID;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Results {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID uuid;
    @Column(length = 10000)
    ArrayList<Object> list;
    @Column
    String race;

    public Results(ArrayList<Object> list, String race) {
        this.list = list;
        this.race = race;
    }


}
