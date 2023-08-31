package Service;

import model.Applyer;
import model.response;
import org.springframework.stereotype.Service;
import repository.CRUDApplyer; // Assurez-vous d'importer la classe CRUDApplyer
import java.sql.SQLException;

@Service
public class ApplyerService {

    public static Applyer insert(Applyer toInsert) {
        try {
            CRUDApplyer.insertApplyer(toInsert);
            return toInsert;
        } catch (SQLException e) {
            throw new RuntimeException("There was an error when inserting the Applyer.");
        }
    }

    public static response update(Applyer toUpdate, int applyerId) {
        try {
            CRUDApplyer.updateApplyer(toUpdate, applyerId);
            return new response("Data update successfully");
        } catch (SQLException e) {
            throw new RuntimeException("There was an error when updating the Applyer.");
        }
    }

    public static response delete(int applyerId) {
        try {
            boolean deleted = CRUDApplyer.deleteApplyer(applyerId);
            if (deleted) {
                return new response("Applyer deleted successfully.");
            } else {
                throw new RuntimeException("Applyer not found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred while deleting the Applyer.");
        }
    }
}

