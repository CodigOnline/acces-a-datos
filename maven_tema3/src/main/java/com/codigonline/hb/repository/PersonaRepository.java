package com.codigonline.hb.repository;

import com.codigonline.hb.entities.Persona;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class PersonaRepository implements Repository<Persona> {

    final Session session;

    public PersonaRepository(Session session) {
        this.session = session;
    }

    @Override
    public List<Persona> findAll() {
        session.beginTransaction();
        Query<Persona> query = session.createQuery("FROM Persona", Persona.class);
        List<Persona> personas = query.getResultList();
        session.getTransaction().commit();
        //return query.getResultList();
        return personas;
    }

    @Override
    public Persona findOneById(int id) {
        session.beginTransaction();
        Query<Persona> query = session.createQuery("FROM Persona where id =: idPersona", Persona.class);
        query.setParameter("idPersona", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            System.err.println("No se ha encontrado ningún resultado");
            return new Persona();
        }
        finally {
            session.getTransaction().commit();
        }
    }

    @Override
    public void save(Persona persona) {
        session.beginTransaction();
        session.clear();
        session.save(persona);
        session.getTransaction().commit();
    }

    @Override
    public void updateById(int id, Persona persona) {
        session.beginTransaction();
        persona.setId(id);
        session.update(persona);
        session.getTransaction().commit();
    }

    public void update(Persona persona) {
        session.beginTransaction();
        session.update(persona);
        session.getTransaction().commit();
    }

    @Override
    public void deleteById(int id) {
        session.beginTransaction();
        session.clear();
        Persona persona = new Persona();
        persona.setId(id);
        session.delete(persona);
        session.getTransaction().commit();
    }
}
