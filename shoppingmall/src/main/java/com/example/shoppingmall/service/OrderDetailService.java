package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.OrderDetailReq;
import com.example.shoppingmall.dto.OrderDetailRes;
import com.example.shoppingmall.entity.Cart;
import com.example.shoppingmall.entity.Order;
import com.example.shoppingmall.entity.OrderDetail;
import com.example.shoppingmall.entity.Product;
import com.example.shoppingmall.repository.CartRepository;
import com.example.shoppingmall.repository.OrderDetailRepository;
import com.example.shoppingmall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    //단일 상품 주문 등록
    public void create(OrderDetailReq req, String str){
        Product product = productRepository.findById(req.getProductId())
                .orElseThrow(EntityExistsException::new);

        //랜덤 주문번호로 db에 저장
        req.setOrderId(str);

        // 상품 구매한 만큼 상품 재고 수량 수정
        product.setProductRestCnt(product.getProductRestCnt() - req.getOrderDetailCnt());
        productRepository.save(product);

        orderDetailRepository.save(req.toEntity(req, product));
    }

    //카트 상품 장바구니 -> 주문상품으로 등록
    public void createSome(List<Long> ids, String str){

        for (Long id : ids){

            Cart cart = cartRepository.findById(id)
                    .orElseThrow(EntityExistsException::new);

            //cart entity -> orderDetail entity 변환
            OrderDetail orderDetail = OrderDetailReq.cartToEntity(cart);

            //동일한 주문 번호로 저장
            orderDetail.setOrderId(str);

            //db에 저장
            orderDetailRepository.save(orderDetail);

            //재고 수량 수정
            Product product =  productRepository.findById(orderDetail.getProduct().getProductId())
                    .orElseThrow(EntityExistsException::new);
            product.setProductRestCnt(product.getProductRestCnt() - orderDetail.getOrderDetailCnt());
            productRepository.save(product);

            //장바구니 비우기
            cartRepository.deleteById(cart.getCartId());
        }
    }

    //랜덤 주문번호 생성
    public String createRandomNumber(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String str = dateFormat.format(now) + generatedString;
        System.out.println(str);
        return str;
    }

    //주문 상품 확인
    public List<OrderDetailRes> findByOrderId(String id){
        return orderDetailRepository.findByOrderId(id)
                .stream()
                .map(OrderDetailRes::toDTO)
                .collect(Collectors.toList());
    }

}
