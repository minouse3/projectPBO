package DAOInterface;

import Model.Lapangan;
import java.math.BigDecimal;
import java.util.List;

public interface LapanganDAOInterface {
    void insert(Lapangan lapangan);
    void updateBySuperadmin(Lapangan lapangan);
    void updateByAdmin(Lapangan lapangan);
    void delete(int idLapangan);
    void nonaktifkan(int idLapangan);
    Lapangan getById(int idLapangan);
    List<Lapangan> getAll();
    List<Lapangan> search(String keyword);
    List<Lapangan> getLapanganTersedia();
    BigDecimal getHargaPerJam(int idLapangan);
}
