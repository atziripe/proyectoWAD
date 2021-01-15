package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.GeneroDTO;
import com.ipn.mx.modelo.dto.PeliculaDTO;
import com.ipn.mx.modelo.entidades.Pelicula;
import com.ipn.mx.utilerias.HibernateUtil;
import com.ipn.mx.utilerias.Utilerias;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**@author Atziri Perez*/
public class PeliculaDAO {
    public void create(PeliculaDTO dto) {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        try {
            transaction.begin();
            sesion.save(dto.getEntidad());
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public void update(PeliculaDTO dto) {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        try {
            transaction.begin();
            sesion.update(dto.getEntidad());
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public void delete(PeliculaDTO dto) {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        try {
            transaction.begin();
            sesion.delete(dto.getEntidad());
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public PeliculaDTO read(PeliculaDTO dto) {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        try {
            transaction.begin();
            //select * from usuario where idUsuario = ?
            dto.setEntidad(sesion.get(dto.getEntidad().getClass(), dto.getEntidad().getIdPelicula()));
            //dto.setEntidad(dto.getEntidad());
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return dto;
    }

    public List readAll() {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        List lista = new ArrayList();
        try {
            transaction.begin();
            //select * from Usuario u order by u.idUsuario;
            //              Usuario u
            Query q = sesion.createQuery("from Pelicula peli order by peli.idPelicula");
            for (Pelicula peli : (List<Pelicula>) q.list()) {
                PeliculaDTO dto = new PeliculaDTO();
                dto.setEntidad(peli);
                lista.add(dto);
            }
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return lista;
    }
    
    public List readGen(PeliculaDTO dto) {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        List lista = new ArrayList();
        try {
            transaction.begin();
            dto.setEntidad(sesion.get(dto.getEntidad().getClass(), dto.getEntidad().getIdGenero()));
            Query q = sesion.createQuery("from Pelicula peli where idGenero = "+ dto.getEntidad().getIdGenero());

            for (Pelicula peli : (List<Pelicula>) q.list()) {
                dto.setEntidad(peli);
                lista.add(dto);
            }
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return lista;
    }

        public static void main(String[] args) {
        PeliculaDAO dao = new PeliculaDAO();
        PeliculaDTO dto = new PeliculaDTO();
        
        //dto.getEntidad().setIdGenero(3);

        dto.getEntidad().setIdPelicula(7);
//        dto.getEntidad().setIdGenero(4);
//        dto.getEntidad().setNombrePelicula("¿Dónde están las weras?");
//        dto.getEntidad().setSinopsis("Unas weras");
//        dto.getEntidad().setClasificacion("AA");
//        dto.getEntidad().setAnio(2000);
//        dto.getEntidad().setDuracion(102);
//        dto.getEntidad().setDirector("Director");
//        dto.getEntidad().setVotosPositivos(0);
//        dto.getEntidad().setVotosNegativos(0);
//        dto.getEntidad().setLink("sdgsdgdsgdsfgds");

//        dao.update(dto);
        dao.delete(dto);
//        dao.create(dto);
        
//System.out.println(dao.readAll());
//System.out.println(dao.readGen(dto));
    }

}