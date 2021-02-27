package com.yesminlahoud.service;

import com.yesminlahoud.domain.entity.Pedido;
import com.yesminlahoud.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar (PedidoDTO dto);
}
