package Service;

import DAO.PromoDAO;
import DAOInterface.PromoDAOInterface;
import Model.Promo;
import java.util.List;

public class PromoService {
    private PromoDAOInterface promoDAO;

    public PromoService() {
        this.promoDAO = new PromoDAO();
    }

    public List<Promo> getAllPromo() {
        return promoDAO.getAll();
    }

    public void tambahPromo(Promo p) {
        promoDAO.insert(p);
    }

    public void updatePromo(Promo p) {
        promoDAO.update(p);
    }

    public void hapusPromo(int id) {
        promoDAO.delete(id);
    }

    public List<Promo> cariPromo(String nama) {
        return promoDAO.getCariNama(nama);
    }
}
