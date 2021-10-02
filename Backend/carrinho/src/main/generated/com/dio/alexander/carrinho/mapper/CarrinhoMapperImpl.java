package com.dio.alexander.carrinho.mapper;

import com.dio.alexander.carrinho.dto.request.CarrinhoDTO;
import com.dio.alexander.carrinho.dto.request.ItemCarrinhoDTO;
import com.dio.alexander.carrinho.model.Carrinho;
import com.dio.alexander.carrinho.model.Carrinho.Status;
import com.dio.alexander.carrinho.model.ItemCarrinho;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-01T21:35:22-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class CarrinhoMapperImpl implements CarrinhoMapper {

    @Override
    public Carrinho toModel(CarrinhoDTO produtoDTO) {
        if ( produtoDTO == null ) {
            return null;
        }

        Carrinho carrinho = new Carrinho();

        carrinho.setId( produtoDTO.getId() );
        if ( produtoDTO.getStatus() != null ) {
            carrinho.setStatus( Enum.valueOf( Status.class, produtoDTO.getStatus() ) );
        }
        carrinho.setItens( itemCarrinhoDTOListToItemCarrinhoList( produtoDTO.getItens() ) );
        carrinho.setDataInclusao( produtoDTO.getDataInclusao() );
        carrinho.setDataAlteracao( produtoDTO.getDataAlteracao() );

        return carrinho;
    }

    @Override
    public CarrinhoDTO toDTO(Carrinho produto) {
        if ( produto == null ) {
            return null;
        }

        CarrinhoDTO carrinhoDTO = new CarrinhoDTO();

        carrinhoDTO.setId( produto.getId() );
        if ( produto.getStatus() != null ) {
            carrinhoDTO.setStatus( produto.getStatus().name() );
        }
        carrinhoDTO.setItens( itemCarrinhoListToItemCarrinhoDTOList( produto.getItens() ) );
        carrinhoDTO.setDataInclusao( produto.getDataInclusao() );
        carrinhoDTO.setDataAlteracao( produto.getDataAlteracao() );

        return carrinhoDTO;
    }

    protected ItemCarrinho itemCarrinhoDTOToItemCarrinho(ItemCarrinhoDTO itemCarrinhoDTO) {
        if ( itemCarrinhoDTO == null ) {
            return null;
        }

        ItemCarrinho itemCarrinho = new ItemCarrinho();

        itemCarrinho.setId( itemCarrinhoDTO.getId() );
        itemCarrinho.setProdutoId( itemCarrinhoDTO.getProdutoId() );
        itemCarrinho.setValor( itemCarrinhoDTO.getValor() );
        itemCarrinho.setQuantidade( itemCarrinhoDTO.getQuantidade() );

        return itemCarrinho;
    }

    protected List<ItemCarrinho> itemCarrinhoDTOListToItemCarrinhoList(List<ItemCarrinhoDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<ItemCarrinho> list1 = new ArrayList<ItemCarrinho>( list.size() );
        for ( ItemCarrinhoDTO itemCarrinhoDTO : list ) {
            list1.add( itemCarrinhoDTOToItemCarrinho( itemCarrinhoDTO ) );
        }

        return list1;
    }

    protected ItemCarrinhoDTO itemCarrinhoToItemCarrinhoDTO(ItemCarrinho itemCarrinho) {
        if ( itemCarrinho == null ) {
            return null;
        }

        ItemCarrinhoDTO itemCarrinhoDTO = new ItemCarrinhoDTO();

        itemCarrinhoDTO.setId( itemCarrinho.getId() );
        itemCarrinhoDTO.setProdutoId( itemCarrinho.getProdutoId() );
        itemCarrinhoDTO.setValor( itemCarrinho.getValor() );
        itemCarrinhoDTO.setQuantidade( itemCarrinho.getQuantidade() );

        return itemCarrinhoDTO;
    }

    protected List<ItemCarrinhoDTO> itemCarrinhoListToItemCarrinhoDTOList(List<ItemCarrinho> list) {
        if ( list == null ) {
            return null;
        }

        List<ItemCarrinhoDTO> list1 = new ArrayList<ItemCarrinhoDTO>( list.size() );
        for ( ItemCarrinho itemCarrinho : list ) {
            list1.add( itemCarrinhoToItemCarrinhoDTO( itemCarrinho ) );
        }

        return list1;
    }
}
