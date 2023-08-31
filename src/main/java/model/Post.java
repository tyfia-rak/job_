package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.sql.Date;

@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode

public class Post {
    private int IdPost;
    private Date DatePost;
    private String TitlePost;
    private String DescriptionPost;
    private String responsibility;
    private String requirement;
    private Double Salary;
    private Company company;
}
