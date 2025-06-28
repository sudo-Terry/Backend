package team3.kummit.service.music;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MusicSearchServiceTest {

    @Autowired
    MusicSearchService service;

    @Test
    public void test(){
        System.out.println(service.searchMusic("BTS"));
    }

}
