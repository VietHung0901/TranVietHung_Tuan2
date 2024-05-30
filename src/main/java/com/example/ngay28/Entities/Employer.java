package com.example.ngay28.Entities;

import lombok.*;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employer {
    private Long id;
    private String employer_name;
    private String sdt;
    private String diaChi;
    private Date ngaySinh;
}
