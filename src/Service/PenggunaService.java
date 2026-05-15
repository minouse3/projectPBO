package Service;

import DAO.PenggunaDAO;
import DAOInterface.PenggunaDAOInterface;
import Model.Pengguna;
import java.util.List;

public class PenggunaService {
    private PenggunaDAOInterface penggunaDAO;

    public PenggunaService() {
        this.penggunaDAO = new PenggunaDAO();
    }

    public List<Pengguna> getAllPengguna() {
        return penggunaDAO.getAll();
    }

    public void tambahPengguna(Pengguna p) {
        penggunaDAO.insert(p);
    }

    public void updatePengguna(Pengguna p) {
        penggunaDAO.update(p);
    }

    public void hapusPengguna(int id) {
        penggunaDAO.delete(id);
    }

    public List<Pengguna> cariPengguna(String nama) {
        return penggunaDAO.getCariNama(nama);
    }
}
