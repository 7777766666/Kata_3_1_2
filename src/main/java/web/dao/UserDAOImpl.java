package web.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import web.models.User;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User show(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(Integer id, User updatedUser) {
        User userForUpdate = entityManager.find(User.class, id);

        userForUpdate.setName(updatedUser.getName());
        userForUpdate.setLastName(updatedUser.getLastName());
        userForUpdate.setAge(updatedUser.getAge());
        entityManager.merge(userForUpdate);
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(show(id));
    }
}
