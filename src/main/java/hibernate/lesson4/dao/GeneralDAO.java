package hibernate.lesson4.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import java.util.List;

public abstract class GeneralDAO<T> {
    private Class<T> parameterOfClass;
    private String getAll;
    private String delete;

    public T save(T t) {
        try(Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.save(t);

            tr.commit();
            session.close();
            return t;
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public T update(T t){
        try(Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.update(t);

            tr.commit();
            session.close();
            return t;
        } catch (HibernateException e) {
            System.err.println("Update is failed");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void delete(long id){
        try(Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            NativeQuery query = session.createNativeQuery(delete);
            query.setParameter(1, id);

            tr.commit();
            session.close();
        } catch (HibernateException e) {
            System.err.println("Delete gone wrong");
            System.err.println(e.getMessage());
        }
    }

    public T findById(long id) { // поиск продукта по id
        try (Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            return session.get(parameterOfClass,id);
        } catch (HibernateException e) {
            System.err.println("Finding by id gone wrong");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<T> getAll(){
        try (Session session = new Configuration().configure().buildSessionFactory().openSession()) {

            NativeQuery query = session.createNativeQuery(getAll);
            query.addEntity(parameterOfClass);

            return (List<T>) query.list();
        } catch (HibernateException e) {
            System.err.println("Getting all objects gone wrong");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void setParameterOfClass(Class<T> parameterOfClass) {
        this.parameterOfClass = parameterOfClass;
    }

    public void setGetAll(String getAll) {
        this.getAll = getAll;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }
}
