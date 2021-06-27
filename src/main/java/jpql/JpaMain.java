package jpql;

import javax.persistence.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            em.createQuery("select m from Member m", Member.class)
                    .getSingleResult();

            em.createQuery("select o.address from Order o", Address.class)
                    .getSingleResult();

            em.createQuery("select m.username, m.age from Member m", Member.class)
                    .getSingleResult();

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
