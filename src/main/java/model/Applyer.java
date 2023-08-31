package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
@AllArgsConstructor
@ToString
@Data
@EqualsAndHashCode
public class Applyer {
    private int id_applyer;
    private String name;
    private int age;
    private String email;
    private String phoneNumber;
    private Date applying_date;
    private Post post;
}
