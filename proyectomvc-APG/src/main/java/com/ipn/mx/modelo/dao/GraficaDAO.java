package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.GraficaDTO;
import com.ipn.mx.utilerias.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Atziri Perez
 */
public class GraficaDAO {

    public List graficaPeliXGen() {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        List lista = new ArrayList();
        try {
            transaction.begin();
            Query q = sesion.createQuery("select g.nombreGenero as Genero, count (*) as Peliculas from Pelicula p, Genero g where g.idGenero = p.idGenero group by g.idGenero");
            for (Object[] datos : (List<Object[]>) q.list()) {
                GraficaDTO dto = new GraficaDTO();
                dto.setNombre(String.valueOf(datos[0]));
                dto.setCantidad(Integer.parseInt(datos[1].toString()));
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

    public List graficaGenVotado() {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        List lista = new ArrayList();
        try {
            transaction.begin();
            Query q = sesion.createQuery("select g.nombreGenero as Genero, sum(p.votosPositivos) as Votos from Pelicula p, Genero g where g.idGenero = p.idGenero group by g.idGenero");
            for (Object[] datos : (List<Object[]>) q.list()) {
                GraficaDTO dto = new GraficaDTO();
                dto.setNombre(String.valueOf(datos[0]));
                dto.setCantidad(Integer.parseInt(datos[1].toString()));
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

    public List graficaGenNoVotado() {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        List lista = new ArrayList();
        try {
            transaction.begin();
            Query q = sesion.createQuery("select g.nombreGenero as Genero, sum(p.votosNegativos) as Votos from Pelicula p, Genero g where g.idGenero = p.idGenero group by g.idGenero");
            for (Object[] datos : (List<Object[]>) q.list()) {
                GraficaDTO dto = new GraficaDTO();
                dto.setNombre(String.valueOf(datos[0]));
                dto.setCantidad(Integer.parseInt(datos[1].toString()));
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

    public List graficaDuracion() {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        List lista = new ArrayList();
        try {
            transaction.begin();
            Query q = sesion.createQuery("select g.nombreGenero as Genero, avg(p.duracion) as Duracion from Pelicula p, Genero g where g.idGenero = p.idGenero group by g.idGenero");
            for (Object[] datos : (List<Object[]>) q.list()) {
                GraficaDTO dto = new GraficaDTO();
                dto.setNombre(String.valueOf(datos[0]));
                dto.setCantidad((int) Float.parseFloat(datos[1].toString()));
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
    
    public List graficaPelixClasificacion() {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        List lista = new ArrayList();
        try {
            transaction.begin();
            Query q = sesion.createQuery("select p.clasificacion as Clasificacion, count(*)  from Pelicula p group by clasificacion ");
            for (Object[] datos : (List<Object[]>) q.list()) {
                GraficaDTO dto = new GraficaDTO();
                dto.setNombre(String.valueOf(datos[0]));
                dto.setCantidad(Integer.parseInt(datos[1].toString()));
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
        GraficaDAO dao = new GraficaDAO();
        GraficaDTO dto = new GraficaDTO();

        List res = dao.graficaPeliXGen();
        System.out.println(res);
        
        res = dao.graficaGenVotado();
        System.out.println(res);
        
        res = dao.graficaGenNoVotado();
        System.out.println(res);
        
        res = dao.graficaDuracion();
        System.out.println(res);

        res = dao.graficaPelixClasificacion();
        System.out.println(res);
        
    }

}
