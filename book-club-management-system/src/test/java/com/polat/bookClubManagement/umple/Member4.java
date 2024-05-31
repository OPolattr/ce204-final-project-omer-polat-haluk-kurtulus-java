package com.polat.bookClubManagement.umple;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.junit.Test;

public class Member4 {

	private Member member;
    private MemberManagement memberManagement;
    private DiscussionForum discussionForum;

    @Before
    public void setUp() {
        memberManagement = new MemberManagement();
        member = new Member("1", "Test Member", "test@example.com", "Fiction", memberManagement);
        discussionForum = member.addDiscussionForum();
    }

    @Test
    public void testGetDiscussionForum() {
        // Oluşturduğumuz tartışma forumunun doğru alınıp alınmadığını kontrol ediyoruz
        assertEquals(discussionForum, member.getDiscussionForum(0));
    }
    
    @Test
    public void testGetDiscussionForum1() {
        // Oluşturduğumuz tartışma forumunun doğru alınıp alınmadığını kontrol ediyoruz
        List<DiscussionForum> retrievedDiscussionForums = member.getDiscussionForum();
        
        // Döndürülen listeyi kontrol ediyoruz
        assertEquals(1, retrievedDiscussionForums.size());
        assertTrue(retrievedDiscussionForums.contains(discussionForum));
    }
    
    @Test
    public void testNumberOfDiscussionForum() {
        // Oluşturduğumuz bir tartışma forumunun sayısını kontrol ediyoruz
        int numberOfDiscussionForums = member.numberOfDiscussionForum();
        
        // Oluşturduğumuz tartışma forumunun sayısı 1 olmalı
        assertEquals(1, numberOfDiscussionForums);
    }
    
    @Test
    public void testIndexOfDiscussionForum() {
        // Oluşturduğumuz tartışma forumunu member içindeki indeksini alıyoruz
        int index = member.indexOfDiscussionForum(discussionForum);
        
        // Oluşturduğumuz tartışma forumunun indeksi 0 olmalı, çünkü sadece bir tane var
        assertEquals(0, index);
    }

}
