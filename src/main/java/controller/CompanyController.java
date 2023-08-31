package controller;

import Service.companyService;
import model.Company;
import model.response;
import org.springframework.web.bind.annotation.*;
import repository.CRUDCompany;

import java.util.List;

@RestController
public class CompanyController {
    @GetMapping("/allCompany")
    public static List<Company> findAllCompany(){
        return CRUDCompany.findAllCompany();
    }

    @PostMapping("/company")
    public static Company insertCompany(@RequestBody Company toInsert){
        return companyService.insert(toInsert);
    }

    @PutMapping("/company/update/{FindById}")
    public static response updateCompany(@RequestBody Company toUpdate, @PathVariable int FindById) {
        return companyService.update(toUpdate, FindById);
    }

    @DeleteMapping("/company/delete/{postId}")
    public static response deleteCompany(@PathVariable int IdCompany) {
        return companyService.delete(IdCompany);
    }
}
