package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {

    public static void main(String[] args) {
        // Build SessionFactory and open a session
        try (Session session = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Department.class)
                .buildSessionFactory()
                .openSession()) {

            // Begin transaction
            Transaction transaction = session.beginTransaction();

            // Update query using positional parameters
            String hql = "UPDATE Department SET name = ?1, location = ?2 WHERE departmentId = ?3";
            int rowsUpdated = session.createQuery(hql)
                    .setParameter(1, "Updated Department Name") // Positional parameter 1
                    .setParameter(2, "Updated Location")        // Positional parameter 2
                    .setParameter(3, 1)                        // Positional parameter 3
                    .executeUpdate();

            // Commit the transaction
            transaction.commit();

            System.out.println(rowsUpdated + " row(s) updated.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

