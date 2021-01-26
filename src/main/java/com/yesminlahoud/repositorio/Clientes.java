package com.yesminlahoud.repositorio;

import com.yesminlahoud.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Clientes extends JpaRepository<Cliente, Integer> {


    List<Cliente> findByNomeLike(String nome);
}
