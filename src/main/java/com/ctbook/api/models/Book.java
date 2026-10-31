package com.ctbook.api.models;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private UUID id;
    private String title;
    private String author;
    private String publisher;
    private String year;
    private String category;
    private StatusBook status = StatusBook.Available;
}
