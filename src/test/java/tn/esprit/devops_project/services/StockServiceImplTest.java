package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;
    @InjectMocks
    private StockServiceImpl stockService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void addStock() {
        Stock stock = new Stock();
        stock.setIdStock(1L);
        stock.setTitle("Sample Stock");
        Mockito.when(stockService.addStock(stock)).thenReturn(stock);

        Stock savedStock = stockService.addStock(stock);

        assertEquals(stock, savedStock);
        Mockito.verify(stockRepository, Mockito.times(1)).save(stock);
    }

    @Test
    void retrieveStock() {
        Long id = 1L;
        Stock stock = new Stock();
        stock.setIdStock(1L);
        stock.setTitle("Sample Stock");
        Mockito.when(stockRepository.findById(id)).thenReturn(Optional.of(stock));

        Stock retrievedStock = stockService.retrieveStock(id);

        assertEquals(stock, retrievedStock);
        Mockito.verify(stockRepository, Mockito.times(1)).findById(id);
    }

    @Test
    void retrieveAllStock() {
        List<Stock> stocks = new ArrayList<>();
        Stock stock = new Stock();
        stock.setIdStock(1L);
        stock.setTitle("Sample Stock");
        Stock stock1 = new Stock();
        stock1.setIdStock(2L);
        stock1.setTitle("Sample Stock 1");
        stocks.add(stock1);
        Mockito.when(stockRepository.findAll()).thenReturn(stocks);

        List<Stock> retrievedStocks = stockService.retrieveAllStock();

        assertEquals(stocks.size(), retrievedStocks.size());
        Mockito.verify(stockRepository, Mockito.times(1)).findAll();
    }
}