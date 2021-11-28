package com.codigonline.hb.repository;

import com.codigonline.hb.entities.Empresa;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class EmpresaRepository implements Repository<Empresa> {

    final Session session;

    public EmpresaRepository(Session session) {
        this.session = session;
    }

    @Override
    public List<Empresa> findAll() {
        session.beginTransaction();
        Query<Empresa> query = session.createQuery("FROM Empresa", Empresa.class);
        List<Empresa> empresas = query.getResultList();
        session.getTransaction().commit();
        //return query.getResultList();
        return empresas;
    }

    @Override
    public Empresa findOneById(int id) {
        session.beginTransaction();
        Query<Empresa> query = session.createQuery("FROM Empresa where id =: idEmpresa", Empresa.class);
        query.setParameter("idEmpresa", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            System.err.println("No se ha encontrado ningún resultado");
            return new Empresa();
        }
        finally {
            session.getTransaction().commit();
        }
    }

    @Override
    public void save(Empresa empresa) {
        session.beginTransaction();
        session.clear();
        session.save(empresa);
        session.getTransaction().commit();
    }

    @Override
    public void updateById(int id, Empresa empresa) {
        session.beginTransaction();
        empresa.setId(id);
        session.update(empresa);
        session.getTransaction().commit();
    }

    public void update(Empresa empresa) {
        session.beginTransaction();
        session.update(empresa);
        session.getTransaction().commit();
    }

    @Override
    public void deleteById(int id) {
        session.beginTransaction();
        session.clear();
        Empresa empresa = new Empresa();
        empresa.setId(id);
        session.delete(empresa);
        session.getTransaction().commit();
    }
}
