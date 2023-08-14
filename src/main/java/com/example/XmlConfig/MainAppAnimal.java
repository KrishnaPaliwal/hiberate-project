package com.example.XmlConfig;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class MainAppAnimal {

    public static void main(String[] args) {
        // Create the Hibernate configuration
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory =configuration.buildSessionFactory();

//        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
  //      Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
    //    sessionFactory = metadata.getSessionFactoryBuilder().build();

        // Create a new Animal object
        Animal animal1 = new Animal();
        animal1.setColor("White");
        animal1.setBreed("Labrador");
        animal1.setName("Buddy");
        animal1.setOrigin("United States");
        
       // Transient , Persist, Detached 
        // Save the animal object to the database
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(animal1);
        animal1.setBreed("effefe");
        session.save(animal1);
        transaction.commit();
        animal1.setBreed("ABC");
        session.close();

        // Fetch the animal from the database
        session = sessionFactory.openSession();
        Animal fetchedAnimal = session.get(Animal.class, 3);
        System.out.println("Fetched Animal: " + fetchedAnimal);
        session.close();

        // Update the animal in the database
       session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        fetchedAnimal.setColor("Black");
        session.update(fetchedAnimal);
        transaction.commit();
        session.close();

        // Delete the animal from the database
       /* session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.delete(fetchedAnimal);
        transaction.commit();
        session.close();
        */

        // Close the SessionFactory when done
        sessionFactory.close();
    }
}
