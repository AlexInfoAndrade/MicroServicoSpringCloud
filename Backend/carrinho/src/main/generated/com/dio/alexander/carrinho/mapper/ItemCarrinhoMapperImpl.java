package com.dio.alexander.carrinho.mapper;

import com.dio.alexander.carrinho.dto.request.ItemCarrinhoDTO;
import com.dio.alexander.carrinho.model.ItemCarrinho;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-01T21:35:22-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class ItemCarrinhoMapperImpl implements ItemCarrinhoMapper {

    @Override
    public ItemCarrinho toModel(ItemCarrinhoDTO produtoDTO) {
        if ( produtoDTO == null ) {
            return null;
        }

        ItemCarrinho itemCarrinho = new ItemCarrinho();

        itemCarrinho.setId( produtoDTO.getId() );
        itemCarrinho.setProdutoId( produtoDTO.getProdutoId() );
        itemCarrinho.setValor( produtoDTO.getValor() );
        itemCarrinho.setQuantidade( produtoDTO.getQuantidade() );

        return itemCarrinho;
    }

    @Override
    public ItemCarrinhoDTO toDTO(ItemCarrinho produto) {
        if ( produto == null ) {
            return null;
        }

        ItemCarrinhoDTO itemCarrinhoDTO = new ItemCarrinhoDTO();

        itemCarrinhoDTO.setId( produto.getId() );
        itemCarrinhoDTO.setProdutoId( produto.getProdutoId() );
        itemCarrinhoDTO.setValor( produto.getValor() );
        itemCarrinhoDTO.setQuantidade( produto.getQuantidade() );

        return itemCarrinhoDTO;
    }
}
