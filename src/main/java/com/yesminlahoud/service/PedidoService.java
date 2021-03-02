package com.yesminlahoud.service;

import com.yesminlahoud.domain.entity.Pedido;
import com.yesminlahoud.domain.enums.StatusPedido;
import com.yesminlahoud.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar (PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
