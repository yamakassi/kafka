package com.example.kafkaconsumer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reviews")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int rating;
    private String text;
    private int upvotes;

    @ManyToOne
    private MovieEntity movie;
}