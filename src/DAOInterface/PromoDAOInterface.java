package DAOInterface;

import Model.Promo;
import java.util.List;

public interface PromoDAOInterface {
    public void insert(Promo p);
    public void update(Promo p);
    public void delete(int id);
    public List<Promo> getAll();
    public List<Promo> getCariNama(String nama);
}
