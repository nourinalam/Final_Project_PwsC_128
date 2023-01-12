/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.uaspws;

import finalproject.uaspws.exceptions.NonexistentEntityException;
import finalproject.uaspws.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author user
 */
public class UaspwsJpaController implements Serializable {

    public UaspwsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("finalproject_uaspws_jar_0.0.1-SNAPSHOTPU");
    public UaspwsJpaController() {}

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Uaspws uaspws) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uaspws);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUaspws(uaspws.getId()) != null) {
                throw new PreexistingEntityException("Uaspws " + uaspws + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Uaspws uaspws) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uaspws = em.merge(uaspws);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = uaspws.getId();
                if (findUaspws(id) == null) {
                    throw new NonexistentEntityException("The uaspws with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Uaspws uaspws;
            try {
                uaspws = em.getReference(Uaspws.class, id);
                uaspws.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uaspws with id " + id + " no longer exists.", enfe);
            }
            em.remove(uaspws);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Uaspws> findUaspwsEntities() {
        return findUaspwsEntities(true, -1, -1);
    }

    public List<Uaspws> findUaspwsEntities(int maxResults, int firstResult) {
        return findUaspwsEntities(false, maxResults, firstResult);
    }

    private List<Uaspws> findUaspwsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Uaspws.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Uaspws findUaspws(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Uaspws.class, id);
        } finally {
            em.close();
        }
    }

    public int getUaspwsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Uaspws> rt = cq.from(Uaspws.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
