package team3.kummit.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ITunesSearchServiceTest {

    @Autowired
    ITunesSearchService iTunesSearchService;

    @Test
    void searchMusic() {
        String result = iTunesSearchService.searchMusic("IU", "KR", 1000);
        System.out.println(result);
    }
}