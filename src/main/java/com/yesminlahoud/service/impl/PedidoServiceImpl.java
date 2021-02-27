package com.yesminlahoud.service.impl;

import com.yesminlahoud.domain.entity.Cliente;
import com.yesminlahoud.domain.entity.ItemPedido;
import com.yesminlahoud.domain.entity.Pedido;
import com.yesminlahoud.domain.entity.Produto;
import com.yesminlahoud.exception.RegraNegocioException;
import com.yesminlahoud.repository.ClientesRepository;
import com.yesminlahoud.repository.ItensPedidoRepository;
import com.yesminlahoud.repository.PedidosRepository;
import com.yesminlahoud.repository.ProdutosRepository;
import com.yesminlahoud.rest.dto.ItemPedidoDTO;
import com.yesminlahoud.rest.dto.PedidoDTO;
import com.yesminlahoud.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidosRepository pedidosRepository;
    private final ClientesRepository clientesRepository;
    private final ProdutosRepository produtosRepository;
    private final ItensPedidoRepository itensPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido((LocalDate.now()));
        pedido.setCliente(cliente);

        List<ItemPedido> itensPedido = converterItems(pedido, dto.getItems());
        pedidosRepository.save(pedido);
        itensPedidoRepository.saveAll(itensPedido);
        pedido.setItens(itensPedido);
        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens");
        }

        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
        }).collect(Collectors.toList());
    }
}
