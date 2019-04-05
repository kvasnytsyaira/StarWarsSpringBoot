package com.example.demo.domain;

import lombok.*;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Pagination{

    public String url;
    public ArrayList<?> list;
}
