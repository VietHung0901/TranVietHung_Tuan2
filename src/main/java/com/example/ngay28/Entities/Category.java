package com.example.ngay28.Entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Long id;
    private String category_name;
}
