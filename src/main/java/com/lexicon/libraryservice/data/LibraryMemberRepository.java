package com.lexicon.libraryservice.data;

	import javax.enterprise.context.ApplicationScoped;
	import javax.inject.Inject;
	import javax.persistence.EntityManager;
	import javax.persistence.criteria.CriteriaBuilder;
	import javax.persistence.criteria.CriteriaQuery;
	import javax.persistence.criteria.Root;
	import java.util.List;

	import com.lexicon.libraryservice.model.LibraryMember;

	@ApplicationScoped
      public class LibraryMemberRepository {
		//public class MemberRepository {

	    @Inject
	    private EntityManager em;

	    public LibraryMember findById(Long id) {
	        return em.find(LibraryMember.class, id);
	    }

	    public LibraryMember findByEmail(String email) {
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<LibraryMember> criteria = cb.createQuery(LibraryMember.class);
	        Root<LibraryMember> member = criteria.from(LibraryMember.class);
	        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
	        // feature in JPA 2.0
	        // criteria.select(member).where(cb.equal(member.get(Member_.name), email));
	        criteria.select(member).where(cb.equal(member.get("email"), email));
	        return em.createQuery(criteria).getSingleResult();
	    }

	    public List<LibraryMember> findAllOrderedByName() {
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<LibraryMember> criteria = cb.createQuery(LibraryMember.class);
	        Root<LibraryMember> member = criteria.from(LibraryMember.class);
	        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
	        // feature in JPA 2.0
	        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
	        criteria.select(member).orderBy(cb.asc(member.get("name")));
	        return em.createQuery(criteria).getResultList();
	    }
	}
	
