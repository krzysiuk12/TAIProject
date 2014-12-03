package pl.edu.agh.repositories.interfaces;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
public interface IBaseHibernateRepository<T> {

    public void saveOrUpdate(Object entity);

    public T getById(Long id);

}
