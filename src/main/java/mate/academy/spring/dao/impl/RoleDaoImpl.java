package mate.academy.spring.dao.impl;

import java.util.Optional;
import mate.academy.spring.dao.AbstractDao;
import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.exception.DataProcessingException;
import mate.academy.spring.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {

    public RoleDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Optional<Role> getByName(Role.RoleName roleName) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.createQuery("FROM Role "
                    + "WHERE Role.roleName = :roleName", Role.class)
                    .setParameter("roleName", roleName)
                    .getSingleResult());
        } catch (DataProcessingException e) {
            throw new DataProcessingException("Can't find role by" + roleName, e);
        }
    }
}