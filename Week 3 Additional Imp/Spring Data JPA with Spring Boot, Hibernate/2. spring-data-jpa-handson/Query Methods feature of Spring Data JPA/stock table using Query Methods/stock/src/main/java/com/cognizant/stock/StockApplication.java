package com.cognizant.stock;

import com.cognizant.stock.model.Stock;
import com.cognizant.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class StockApplication implements CommandLineRunner {

    @Autowired
    private StockRepository stockRepository;

    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class, args);
    }

    @Override
    public void run(String... args) {
        getFacebookStocksInSep2019();
        getGoogleStocksAbove1250();
        getTop3ByVolume();
        getLowestNetflixStocks();
    }

    private void getFacebookStocksInSep2019() {
        System.out.println("Facebook stocks in Sep 2019:");
        List<Stock> list = stockRepository.findByCodeAndDateBetween("FB",
                LocalDate.of(2019, 9, 1), LocalDate.of(2019, 9, 30));
        list.forEach(System.out::println);
    }

    private void getGoogleStocksAbove1250() {
        System.out.println("Google stocks with close > 1250:");
        List<Stock> list = stockRepository.findByCodeAndCloseGreaterThan("GOOGL", new BigDecimal("1250"));
        list.forEach(System.out::println);
    }

    private void getTop3ByVolume() {
        System.out.println("Top 3 stock records by volume:");
        List<Stock> list = stockRepository.findTop3ByOrderByVolumeDesc();
        list.forEach(System.out::println);
    }

    private void getLowestNetflixStocks() {
        System.out.println("Lowest 3 Netflix stock prices:");
        List<Stock> list = stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX");
        list.forEach(System.out::println);
    }
}
