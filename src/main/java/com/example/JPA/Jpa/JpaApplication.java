package com.example.JPA.Jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaApplication {

    public static void main(String[] args) {
        //1.엔티티 매니저 팩토리생성 : persistence.xml에서 영속성유닛으로 생성됨 -> 딱 한번만 생성해 사용함!
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        //2.엔티티 매니저 생성 : DB커넥션과 밀접해 스레드간에 공유하거나 재사용금지!
        EntityManager em =  emf.createEntityManager();
        //3.트랜잭션 획득 : 트랜잭션안에서 데이터를 변경해야됨!
        EntityTransaction tx = em.getTransaction();
        System.out.println(em);
        System.out.println("Start!!");
        try{
            //1. 트랜잭션 시작 -> 비지니스 로직 수행 -> 커밋
            tx.begin();
            logic(em);

            tx.commit();
        }catch(Exception e){
            //롤백
            System.out.println("Rollback: "+e);
            tx.rollback();
        }finally {
            //엔티티 매니저 종료
            em.close();
        }
        //엔티티 매니저 팩토리 종료
        emf.close();
    }

    public static void logic(EntityManager em){
        // 비지니스 로직 수행
        System.out.println("[logic]===>");
        String id = "1";
        System.out.println("[logic]===>"+id);
        Member findMember = em.find(Member.class,id);
        System.out.println(findMember.getId());
        System.out.println(findMember.toString());
    }
}
