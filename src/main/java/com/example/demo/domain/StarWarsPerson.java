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

    public StarWarsPerson(String birth_year, ArrayList<String> films, String gender, String eye_color, String hair_color, String height, String homeworld, String mass, String name, String skin_color, String created, String edited, ArrayList<String> species, ArrayList<String> starships, String url, ArrayList<String> vehicles) {
        this.birth_year = birth_year;
        this.films = films;
        this.gender = gender;
        this.eye_color = eye_color;
        this.hair_color = hair_color;
        this.height = height;
        this.homeworld = homeworld;
        this.mass = mass;
        this.name = name;
        this.skin_color = skin_color;
        this.created = created;
        this.edited = edited;
        this.species = species;
        this.starships = starships;
        this.url = url;
        this.vehicles = vehicles;
    }
}
