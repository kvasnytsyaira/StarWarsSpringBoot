package com.example.demo.domain;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize
@JsonSerialize
@ToString
@Getter
@Setter
public class StarWarsPerson implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Column
    public String birth_year;
    @Column(length = 1000)
    public ArrayList<String> films;
    @Column
    public String gender;
    @Column
    public String eye_color;
    @Column
    public String hair_color;
    @Column
    public String height;
    @Column
    public String homeworld;
    @Column
    public String mass;
    @Column
    public String name;
    @Column
    public String skin_color;
    @Column
    public String created;
    @Column
    public String edited;
    @Column(length = 1000)
    public ArrayList<String> species;
    @Column(length = 1000)
    public ArrayList<String> starships;
    @Column
    public String url;
    @Column(length = 1000)
    public ArrayList<String> vehicles;

}
