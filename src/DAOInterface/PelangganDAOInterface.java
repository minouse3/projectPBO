package DAOInterface;

import Model.Pelanggan;
import java.util.List;

public interface PelangganDAOInterface {
    void insert(Pelanggan pelanggan);
    void update(Pelanggan pelanggan);
    void delete(int idPelanggan);
    Pelanggan getById(int idPelanggan);
    List<Pelanggan> getAll();
    List<Pelanggan> search(String keyword);
}
