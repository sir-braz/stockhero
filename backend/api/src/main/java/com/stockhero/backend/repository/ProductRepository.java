package com.stockhero.backend.repository;

import com.stockhero.backend.entity.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    @Modifying
    @Query(value = "INSERT INTO produto (nome, descricao, preco, quantidade) " +
            "VALUES (:nome, :descricao, :preco, :quantidade)", nativeQuery = true)
    void criarProdutosEmLote(String nome, String descricao, double preco, int quantidade);
}
