package com.example.ngay28.Entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employer {

    private Long id;
    private String employer_name;
    private String email;
    private String sdt;
    private String diaChi;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySinh;
}
