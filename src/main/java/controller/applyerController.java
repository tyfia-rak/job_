package controller;

import Service.ApplyerService;
import model.Applyer;
import model.response;
import org.springframework.web.bind.annotation.*;
import repository.CRUDApplyer;

import java.util.List;

@RestController
public class applyerController {
    @GetMapping("/Applyers")
    public static List<Applyer> getAllPost(){
        return CRUDApplyer.findAllApplyer();
    }

    @PostMapping("/Applyer")
    public static Applyer insertApplyer(@RequestBody Applyer toInsert){
        return ApplyerService.insert(toInsert);
    }

    @PutMapping("/Applyer/update/{FindById}")
    public static response updateApplyer(@RequestBody Applyer toUpdate, @PathVariable int FindById) {
        return ApplyerService.update(toUpdate, FindById);
    }

    @DeleteMapping("/Applyer/update/{applyerId}")
    public static response deleteApplyer(@PathVariable int ApplyerId) {
        return ApplyerService.delete(ApplyerId);
    }
}
