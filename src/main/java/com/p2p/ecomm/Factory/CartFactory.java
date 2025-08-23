package com.p2p.ecomm.Factory;

import com.p2p.ecomm.model.Response.CartResponse;
import com.p2p.ecomm.model.Response.ItemResponse;
import com.p2p.ecomm.model.domain.CartDTO;
import com.p2p.ecomm.model.domain.ItemDTO;
import com.p2p.ecomm.model.entity.Cart;
import com.p2p.ecomm.model.entity.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartFactory {

    public Cart convertCartDTOToEntity(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setCartType(cartDTO.getCartType());
        cart.setUserId(cartDTO.getUserId());
        cart.setCartId(cartDTO.getCartId());
        List<Item> items = new ArrayList<>();
        for (ItemDTO itemDTO : cartDTO.getItems()) {
            Item item = convertItemDTOToEntity(itemDTO);
            items.add(item);
        }
        cart.setItems(items);
        return cart;
    }

    public Item convertItemDTOToEntity(ItemDTO itemDTO) {
        Item item = new Item();
        item.setItemId(itemDTO.getItemId());
        item.setQuantity(itemDTO.getQuantity());
        item.setDeliveryDetails(itemDTO.getDeliveryDetails());
        item.setSku(itemDTO.getSkus());
        return item;
    }

    public CartResponse convertCartEntityToResponse(Cart cart) {
        CartResponse cartResponse = new CartResponse();
        cartResponse.setCartType(cart.getCartType());
        cartResponse.setUserId(cart.getUserId());
        cartResponse.setCartId(cart.getCartId());
        List<ItemResponse> items = new ArrayList<>();
        for (Item Item : cart.getItems()) {
            ItemResponse itemResponse = convertItemEntityToResponse(Item);
            items.add(itemResponse);
        }
        cartResponse.setItems(items);
        return cartResponse;
    }

    public ItemResponse convertItemEntityToResponse(Item item) {
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setItemId(item.getItemId());
        itemResponse.setQuantity(item.getQuantity());
        itemResponse.setDeliveryDetails(item.getDeliveryDetails());
        itemResponse.setSkus(item.getSku());
        return itemResponse;
    }

    public List<ItemResponse> convertItemListEntityToResponse(List<Item> items) {
        List<ItemResponse> itemResponseList = new ArrayList<>();
        for (Item item : items) {
            ItemResponse itemResponse = convertItemEntityToResponse(item);
            itemResponseList.add(itemResponse);
        }
        return itemResponseList;
    }

    public List<Item> convertItemListDTOToEntity(List<ItemDTO> itemDTOList) {
        List<Item> items = new ArrayList<>();
        for (ItemDTO itemDTO : itemDTOList) {
            Item item = convertItemDTOToEntity(itemDTO);
            items.add(item);
        }
        return items;
    }
} 