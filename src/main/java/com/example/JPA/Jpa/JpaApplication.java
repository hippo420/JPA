package com.example.JPA.Jpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
        //findAll(em);
        //updateMember(em);
        //insertMember(em);
        //removeMember(em);
        findMember(em);
    }

    public static void findAll(EntityManager em){
        TypedQuery<Member> query = em.createQuery("select m from Member m",Member.class);
        List<Member> members = query.getResultList();

        for(Member vo :members)
            System.out.println(vo.toString());
    }

    public static void updateMember(EntityManager em){
        String id = "1";
        Member findMember = em.find(Member.class,id);
        findMember.setName("sancho");
    }

    public static void insertMember(EntityManager em){
        Member member = new Member();
        member.setId("5");
        member.setName("messi");
        member.setAge(35);
        em.persist(member);
    }

    public static void removeMember(EntityManager em){
        String id = "5";
        Member member = em.find(Member.class,id);
        em.remove(member);
    }

    public static void findMember(EntityManager em){
        String id = "1";
        Member findMember = em.find(Member.class,id);
        System.out.println(findMember.toString());
    }
}
