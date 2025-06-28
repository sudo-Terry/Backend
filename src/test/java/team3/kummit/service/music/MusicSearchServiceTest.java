package team3.kummit.service.music;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MusicSearchServiceTest {

    @Autowired
    MusicSearchService service;

    @Test
    public void test(){
        System.out.println(service.searchMusic("BTS"));
    }

}