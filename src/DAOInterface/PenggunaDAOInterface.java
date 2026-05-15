package DAOInterface;

import Model.Pengguna;
import java.util.List;

public interface PenggunaDAOInterface {
    public void insert(Pengguna p);
    public void update(Pengguna p);
    public void delete(int id);
    public List<Pengguna> getAll();
    public List<Pengguna> getCariNama(String nama);
    public Pengguna login(String identifier, String password);
}
