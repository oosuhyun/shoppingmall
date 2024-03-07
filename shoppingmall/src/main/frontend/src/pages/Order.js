import React, {useEffect, useState} from 'react';
import "./Order.css"
import axios from "axios";
import {useLocation, useNavigate} from "react-router-dom";

const Order = () => {

    const location = useLocation();
    //단일 구매시 상품 정보
    const id = location.state.id;
    const cnt = location.state.cnt;
    const product = location.state.product;
    //카트 상품 정보
    const checkItems = location.state.checkItems;
    const cartProduct = location.state.cartProduct;
    //주문 총 가격 정보
    const price = location.state.price;


    const [orderProduct, setOrderProduct] = useState([]);
    const [person, setPerson] = useState({});
    const navigate = useNavigate();


    useEffect(()=>{
        setPerson(JSON.parse(sessionStorage.getItem("person")));
        if(id == 1){
            setOrderProduct([{"cnt": cnt, "product": product}]);
        } else if(id ==2){
            setOrderProduct(cartProduct);
        }

        // ***
        //결제 시스템 아임포트(포트원)
        // ***
        const jquery = document.createElement("script");
        jquery.src = "http://code.jquery.com/jquery-1.12.4.min.js";
        const iamport = document.createElement("script");
        iamport.src = "http://cdn.iamport.kr/js/iamport.payment-1.1.7.js";
        document.head.appendChild(jquery);
        document.head.appendChild(iamport);
        return () => {
            document.head.removeChild(jquery);
            document.head.removeChild(iamport);
        };

    },[]);

    useEffect(()=>{

    },[orderProduct]);

    const handleInputChange = (e) => {
        setPerson({ ...person, [e.target.name]: e.target.value });
    };

    const onSubmit = () => {

        //포트원 -- 결제 기능
        const { IMP } = window;
        IMP.init('imp82482774');

        IMP.request_pay({
            pg: 'kakaopay',
            pay_method: 'card',
            merchant_uid: new Date().getTime(),
            name: '상품',
            amount: price,
            buyer_email: 'test@naver.com',
            buyer_name: person.memberName,
            buyer_tel: person.memberTel,
            buyer_addr: person.memberAddress,
            buyer_postcode: person.memberZipCode,
        }, async (rsp) => {
            try {
                const { data } = await axios.post('/api/pay/verify/' + rsp.imp_uid);
                if (rsp.paid_amount === data.response.amount) {




                    // alert('결제 성공');
                    //결제 완료 후 주문 데이터 생성
                    if(id == 1){
                        axios.post("/api/orderDetail", {
                            orderDetailPrice: product.productPrice,
                            orderDetailCnt: cnt,
                            productId: product.productId
                        })
                            .then(r => {
                                console.log(r);
                                axios.post("/api/order", {
                                    orderId: r.data,
                                    memberName: person.memberName,
                                    memberTel: person.memberTel,
                                    memberGender: person.memberGender,
                                    memberZipCode: person.memberZipCode,
                                    memberAddress: person.memberAddress,
                                    memberAddressDetail: person.memberAddressDetail,
                                    memberRequirements: person.memberRequirements,
                                    memberId: person.id
                                })
                                    .then(r => {
                                        console.log(r);
                                        window.alert("주문 완료되었습니다.");
                                        navigate('/main');
                                    })
                                    .catch(e => {
                                        console.log(e);
                                        window.alert("주문 실패하셨습니다.");
                                    });
                            })
                            .catch(e => {
                                console.log(e);
                            });
                    } else if(id == 2){
                        axios.post("/api/orderDetail/some",null,{params: {ids: checkItems.join(",")}})
                            .then(r => {
                                console.log(r);
                                axios.post("/api/order", {
                                    orderId: r.data,
                                    memberName: person.memberName,
                                    memberTel: person.memberTel,
                                    memberGender: person.memberGender,
                                    memberZipCode: person.memberZipCode,
                                    memberAddress: person.memberAddress,
                                    memberAddressDetail: person.memberAddressDetail,
                                    memberRequirements: person.memberRequirements,
                                    memberId: person.id
                                })
                                    .then(r => {
                                        console.log(r);
                                        window.alert("주문 완료되었습니다.");
                                    })
                                    .catch(e => {
                                        console.log(e);
                                        window.alert("주문 실패하셨습니다.");
                                    });
                            })
                            .catch(e => {
                                console.log(e);
                            });
                    }



                } else {
                    // alert('결제 실패');
                }
            } catch (error) {
                console.error('Error while verifying payment:', error);
                // alert('결제 실패');
            }
        });


    };

    return (
        <div>
            <div className={"order-div"}>
                {/*
                *
                주문 상품 정보
                *
                */}
                <div>
                    <text>주문 상품 정보</text>
                    <div className={"order-product-div"}>
                        {orderProduct && orderProduct.map((a) => (
                            <button key={a.product.productId} className={"order-product-button"}>
                                <div className={"order-product-div2"}>
                                    <img className={"order-product-img"} src={a.product.productImg}/>
                                    <div>
                                        <span> {a.product.productPrice}원 </span><br/>
                                        <span> {a.product.productName} </span><br/>
                                        <span> {a.cnt}개 </span>
                                    </div>
                                </div>
                            </button>
                        ))}
                    </div>
                </div>
                {/*
                *
                주문자 정보
                *
                */}
                <div className={"order-info-div"}><br/>
                    <h1 className={"order-info-h1"}>내정보</h1>
                    <div className="order-info-div2">
                        <text className={"order-info-text"}>이름:</text>
                        <input
                            type="text"
                            defaultValue={person.memberName}
                            className={"order-info-input"}
                            name="memberName"
                            value={person.memberName}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className="order-info-div2">
                        <text className={"order-info-text"}>전화번호:</text>
                        <input
                            type="text"
                            defaultValue={person.memberTel}
                            className={"order-info-input"}
                            name="memberTel"
                            value={person.memberTel}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className="order-info-div2">
                        <text className={"order-info-text"}>우편번호:</text>
                        <input
                            type="text"
                            defaultValue={person.memberZipCode}
                            className={"order-info-input"}
                            name="memberZipCode"
                            value={person.memberZipCode}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className="order-info-div2">
                        <text className={"order-info-text"}>주소:</text>
                        <input
                            type="text"
                            defaultValue={person.memberAddress}
                            className={"order-info-input"}
                            name="memberAddress"
                            value={person.memberAddress}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className="order-info-div2">
                        <text className={"order-info-text"}>상세주소:</text>
                        <input
                            type="text"
                            defaultValue={person.memberAddressDetail}
                            className={"order-info-input"}
                            name="memberAddressDetail"
                            value={person.memberAddressDetail}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className="order-info-div2">
                        <text className={"order-info-text"}>요구사항:</text>
                        <input
                            type="text"
                            defaultValue={person.memberRequirements}
                            className={"order-info-input"}
                            name="memberRequirements"
                            value={person.memberRequirements}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className="order-info-button-div">
                        <button
                            className={"order-info-button"}
                            type="button"
                            onClick={onSubmit}
                        >결제하기
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Order;