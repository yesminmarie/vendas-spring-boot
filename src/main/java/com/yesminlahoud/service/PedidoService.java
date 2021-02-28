package com.yesminlahoud.service;

import com.yesminlahoud.domain.entity.Pedido;
import com.yesminlahoud.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar (PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);
}
