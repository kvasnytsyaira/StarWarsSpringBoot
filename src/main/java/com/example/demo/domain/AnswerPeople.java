package com.example.demo.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.io.Serializable;
import java.util.ArrayList;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonDeserialize
@JsonSerialize
public class AnswerPeople implements Serializable {

    @Column
    public int count;
    @Column
    public String next;
    @Column
    public String previous;
    @Column(length = 10000)
    @ElementCollection(targetClass = StarWarsPerson.class)
    public ArrayList<StarWarsPerson> results;

}
