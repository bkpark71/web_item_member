package com.example.third;

import com.example.third.repository.ItemRepository;
import com.example.third.repository.JpaMemberRepository;
import com.example.third.repository.MemberRepository;
import com.example.third.repository.MemoryItemRepository;
import com.example.third.service.ItemService;
import com.example.third.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final EntityManager em;
    private final DataSource dataSource;
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }
    @Bean
    public ItemService itemService(){
        return new ItemService(itemRepository());
    }
    @Bean
    public ItemRepository itemRepository(){
        return new MemoryItemRepository();
    }
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }
}
