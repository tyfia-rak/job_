package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@Data
@EqualsAndHashCode
@AllArgsConstructor

public class Company {
    private int IdCompany;
    private String CompanyName;
    private String Address;
}
