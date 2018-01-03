package com.lexicon.libraryservice.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

//import com.lexicon.libraryservice.data.LibraryMemberRepository;
import com.lexicon.libraryservice.model.LibraryMember;

@RequestScoped
public class LibraryMemberListProducer {
	//public class MemberListProducer {
	    @Inject
	    private LibraryMemberRepository memberRepository;

	    private List<LibraryMember> members;

	    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.
	    // Facelets or JSP view)
	    @Produces
	    @Named
	    public List<LibraryMember> getLibraryMembers() {
	        return members;
	    }

	    public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final LibraryMember member) {
	        retrieveAllMembersOrderedByName();
	    }

	    @PostConstruct
	    public void retrieveAllMembersOrderedByName() {
	        members = memberRepository.findAllOrderedByName();
	    }
	
}
