package com.tshevchenko.server.dao;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tshevchenko.shared.Employee;
import com.tshevchenko.shared.History;
import com.tshevchenko.server.dao.JpaDao;
import java.util.Set;
import java.util.List;
/**
 * The EmployeeDao class 
 * 
 * @author Taras Shevchenko, tgshevchenko@gmail.com
 * 
 */
@Repository("employeeDAO")
public class EmployeeDao extends JpaDao<Long, Employee> {
    @Autowired
    EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void init() {
        super.setEntityManagerFactory(entityManagerFactory);
    }

    public Employee findByLogin(final String login) {
        Object result = null;
        try {
            result = getJpaTemplate().execute(new JpaCallback() {
                public Object doInJpa(EntityManager em)
                        throws PersistenceException {
                    Query q = em
                            .createQuery(
                                    "SELECT OBJECT(e) FROM Employee e WHERE e.login=:login")
                            .setParameter("login", login);
                    return q.getSingleResult();
                }
            });
        } catch (Exception e) {
            System.out.println("EmployeeDao::findByLogin is FAILED: " + e);
        }
        return (Employee) result;
    }

    public Employee findByLoginAndPassword(final String login,
            final String password) {
        Object result = null;
        try {
            result = getJpaTemplate().execute(new JpaCallback() {
                public Object doInJpa(EntityManager em)
                        throws PersistenceException {
                    Query q = em
                            .createQuery(
                                    "SELECT OBJECT(e) FROM Employee e WHERE e.login=:login"
                                            + " AND e.password=:password")
                            .setParameter("login", login)
                            .setParameter("password", password);
                    return q.getSingleResult();
                }
            });
        } catch (Exception e) {
            System.out
                    .println("EmployeeDao::findByLoginAndPassword is FAILED: "
                            + e);
        }
        return (Employee) result;
    }

    public List<History> getEmployeeHistory(final Long employeeId) {
        Object result = null;
        try {
            result = getJpaTemplate().execute(new JpaCallback() {
                public Object doInJpa(EntityManager em)
                        throws PersistenceException {
                    Query q = em
                            .createQuery(
                                    "SELECT OBJECT(h) FROM History h WHERE h.employeeId=:employeeId")
                            .setParameter("employeeId", employeeId);
                    return q.getResultList();
                }
            });
        } catch (Exception e) {
            System.out.println("EmployeeDao::getEmployeeHistory is FAILED: "
                    + e);
        }
        return (List<History>) result;
    }
}
