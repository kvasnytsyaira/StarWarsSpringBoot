package com.example.demo.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonDeserialize
@JsonSerialize
@Getter
@Setter
public class StarWarsSpecies implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @Column
    public String name;
    @Column
    public String classification;
    @Column
    public String designation;
    @Column
    public String average_height;
    @Column
    public String skin_colors;
    @Column
    public String hair_colors;
    @Column
    public String eye_colors;
    @Column
    public String average_lifespan;
    @Column
    public String homeworld;
    @Column
    public String language;
    @Column(length = 1000)
    public ArrayList<String> people;
    @Column(length = 1000)
    public ArrayList<String> films;
    @Column
    public String created;
    @Column
    public String edited;
    @Column
    public String url;

}
