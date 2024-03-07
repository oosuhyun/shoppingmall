import React, {useEffect, useState} from 'react';
import './Cart.css'
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";
import { FiX } from "react-icons/fi";

const Cart = () => {

    const [person, setPerson] = useState({});
    const [cart, setCart] = useState([]);
    //체크된 아이템 담을 배열
    const [checkItems, setCheckItems] = useState([]);
    const navigate = useNavigate();
    const [product, setProduct] = useState([]);


    //처음 한 번만 실행
    useEffect(()=>{
            setPerson(JSON.parse(sessionStorage.getItem("person")));
    },[]);

    //person state 값이 변경됐을 때마다 실행
    useEffect(()=>{
        console.log(person);
        if(person != null){
            axios.get("/api/cart/"+person.id)
                .then(r => {
                    console.log(r);
                    setCart(r.data);
                })
                .catch(e => {
                    console.log(e);
                });
        }
    },[person]);

    // 체크박스 단일 선택
    const handleSingleCheck = (checked, id) => {
        if (checked) {
            // 단일 선택 시 체크된 아이템을 배열에 추가
            setCheckItems(prev => [...prev, id]);
        } else {
            // 단일 선택 해제 시 체크된 아이템을 제외한 배열 (필터)
            setCheckItems(checkItems.filter((el) => el !== id));
        }
        console.log(checkItems)
    };

    // 체크박스 전체 선택
    const handleAllCheck = (checked) => {
        if(checked) {
            // 전체 선택 클릭 시 데이터의 모든 아이템(id)를 담은 배열로 checkItems 상태 업데이트
            const idArray = [];
            cart.forEach((el) => idArray.push(el.cartId));
            setCheckItems(idArray);
        }
        else {
            // 전체 선택 해제 시 checkItems 를 빈 배열로 상태 업데이트
            setCheckItems([]);
        }
        console.log(checkItems)
    }

    //주문하기 버튼 클릭
    const onOrderClick = () => {
        axios.get("/api/cart/some",{params: {ids:checkItems.join(",")}})
            .then(r => {
                console.log(r);
                setProduct(r.data);
            })
            .catch(e => {
                console.log(e);
            });
    };

    //product state 값이 변경됐을 때마다 실행
    useEffect(()=>{
        if(product.length != 0){
            // let totalPrice = 0;
            // for(let i in product){
            //     totalPrice = product[1].cnt * i.product.productPrice;
            //     console.log(i);
            // }
            // console.log(totalPrice);
            navigate('/order', {state:{id: 2, checkItems: checkItems, cartProduct: product}});
        }

    },[product]);

    //상품 삭제 버튼 클릭
    const onXClick = (id) => {
        axios.delete("/api/cart/"+id)
            .then(r => {
                console.log(r);
                window.location.reload();
            })
            .catch(e => {
                console.log(e);
            });
    };

    return (
        <div className={"cart-div"}>
            <h1>장바구니</h1>
            <input type='checkbox' name='select-all'
                   onChange={(e) => handleAllCheck(e.target.checked)}
                // 데이터 개수와 체크된 아이템의 개수가 다를 경우 선택 해제 (하나라도 해제 시 선택 해제)
                   checked={checkItems.length === cart.length ? true : false}/>
            <text>전체선택</text>
            <div className={"cart-product"}>
                {cart && cart.map((a) => (
                    <button key={a.product.productId} className={"cart-product-button"}>
                        <div className={"cart-product-div"}>
                            <input type='checkbox' name={a.cartId}
                                   onChange={(e) => handleSingleCheck(e.target.checked, a.cartId)}
                                // 체크된 아이템 배열에 해당 아이템이 있을 경우 선택 활성화, 아닐 시 해제
                                   checked={checkItems.includes(a.cartId) ? true : false} />
                            <img className={"cart-product-img"} src={a.product.productImg}/>
                            <div className={"cart-product-div2"}>
                                <span> {a.product.productPrice}원 </span><br/>
                                <span> {a.product.productName} </span><br/>
                                <span> {a.cnt}개 </span>
                            </div>
                            <FiX
                                className={"cart-x-button"}
                                onClick={() => onXClick(a.cartId)}
                            />
                        </div>
                    </button>
                ))}
            </div>
            <button className={"cart-order-button"} onClick={onOrderClick}>주문하기</button>
        </div>
    );
};

export default Cart;