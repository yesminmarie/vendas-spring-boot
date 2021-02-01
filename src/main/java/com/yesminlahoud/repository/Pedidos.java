package com.yesminlahoud.repository;

import com.yesminlahoud.domain.entity.Cliente;
import com.yesminlahoud.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente (Cliente cliente);
}
