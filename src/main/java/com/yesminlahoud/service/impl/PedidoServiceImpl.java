package com.yesminlahoud.service.impl;

import com.yesminlahoud.repository.PedidosRepository;
import com.yesminlahoud.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidosRepository pedidosRepository;

    public PedidoServiceImpl(PedidosRepository pedidosRepository) {
        this.pedidosRepository = pedidosRepository;
    }
}
