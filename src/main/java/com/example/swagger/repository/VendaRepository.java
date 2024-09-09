package com.example.swagger.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.swagger.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    List<Venda> findByDesconto(BigDecimal desconto);

    List<Venda> findByDescontoGreaterThan(BigDecimal desconto);

    List<Venda> findAllByOrderByValorUnitDesc();

    @Query("SELECT v.codProd FROM Venda v WHERE v.idNf = ?1 GROUP BY v.codProd ORDER BY SUM(v.quantidade) DESC")
    List<Integer> findTopSellingProductInNf(int idNf);

    @Query("SELECT v.idNf FROM Venda v WHERE v.quantidade > 10 GROUP BY v.idNf")
    List<Integer> findNfWithMoreThanTenUnits();

    @Query("SELECT v.idNf, SUM(v.valorUnit * v.quantidade) as total FROM Venda v GROUP BY v.idNf HAVING SUM(v.valorUnit * v.quantidade) > 500 ORDER BY total DESC")
    List<Object[]> findByTotalGreaterThan500();

}
