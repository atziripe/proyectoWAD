package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.GeneroDTO;
import com.ipn.mx.modelo.dto.GraficaDTO;
import com.ipn.mx.modelo.entidades.Genero;
import com.ipn.mx.utilerias.HibernateUtil;
import com.ipn.mx.utilerias.Utilerias;
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
            //select * from Usuario u order by u.idUsuario;
            //              Usuario u
            //select nombreGenero as Genero, count (*) as Peliculas from Pelicula p, Genero g where g.idGenero = p.idGenero group by g.idGenero
            //Query q = sesion.createQuery("from Pelicula peli where idGenero = "+ dto.getEntidad().getIdGenero());
            Query q = sesion.createQuery("select g.nombreGenero as Genero, count (*) as Peliculas from Pelicula p, Genero g where g.idGenero = p.idGenero group by g.idGenero");
            for (Genero gen : (List<Genero>) q.list()) {
                GeneroDTO dto = new GeneroDTO();
                dto.setEntidad(gen);
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
    }

}
