package Service;

import model.Company;
import model.response;
import org.springframework.stereotype.Service;
import repository.CRUDPost;
import repository.CRUDCompany;

import java.sql.SQLException;
@Service
public class companyService {
    public static Company insert(Company toInsert){
        try{
            CRUDCompany.insertCompany(toInsert);
            return toInsert;
        }catch(SQLException e){
            throw new RuntimeException("There was an error when inserting the company.");
        }
    }

    public static response update(Company toUpdate, int FindById) {
        try {
            CRUDCompany.updateCompany(toUpdate, FindById);
            return new response("Data update successfully");
        } catch (SQLException e) {
            throw new RuntimeException("There was an error when updating the company.");
        }
    }

    public static response delete(int IdCompany) {
        try {
            boolean deleted = CRUDCompany.deleteCompany(IdCompany);
            if (deleted) {
                return new response("company deleted successfully.");
            } else {
                throw new RuntimeException("company not found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred while deleting the company.");
        }
    }
}
