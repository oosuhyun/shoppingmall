import React, {useEffect, useState} from 'react';
import {useLocation, useNavigate} from "react-router-dom";
import './Product.css'
import axios from "axios";
import Modal from "react-modal"

function Product() {

    const location = useLocation();
    const id = location.state.id;
    const [product, setProduct] = useState({});
    const [cnt, setCnt] = useState(1);
    const [person, setPerson] = useState({});
    const navigate = useNavigate();

    useEffect(()=>{
        setPerson(JSON.parse(sessionStorage.getItem("person")));
        axios.get("/api/product/"+id)
            .then(r => {
                console.log(r);
                setProduct(r.data);
            })
            .catch(e => {
                console.log(e);
            });
    },[]);

    // modal이 안보일때는 false, 보일때는 true값으로 상태관리
    const [modalIsOpen, setIsOpen] = React.useState(false);

    // 함수가 실행될때 modal의 상태를 true로 바꿔준다.
    function openModal() {
        setIsOpen(true);
    }

    // 함수가 실행될때 modal의 상태를 false로 바꿔준다.
    function closeModal() {
        setIsOpen(false);
    }

    //Modal style custom
    const customStyles = {
        overlay: {
            zIndex: 1000,
            backgroundColor: '#00000070',
        },
        content: {
            width: '600px',
            height: '400px',
            inset: 'unset',
            margin: '50vh auto',
            padding: 0,
            transform: 'translateY(-50%)',
            position: 'relative'
        }
    };

    //제품 수량 마이너스 버튼
    const onCntMinus = () => {
        if(cnt != 1){
            setCnt(cnt-1);
        }
    };
    //제품 수량 플러스 버튼
    const onCntPlus = () => {
        setCnt(cnt+1);
    };

    //장바구니 넣기
    const onCart = () => {
        //로그인 여부 확인
        if(person == null){
            window.alert("로그인 후 이용해주세요.");
        } else{
            axios.post("/api/cart",
                {
                    "cnt": cnt,
                    "memberId": person.id,
                    "productId": id
                }
            )
                .then(r => {
                    console.log(r);
                    window.alert("장바구니에 추가되었습니다.");
                })
                .catch(e => {
                    window.alert("장바구니에 추가가 실패하였습니다..");
                });
        }
    };

    //제품 구매
    const onOrder = () => {
        if(person == null){
            window.alert("로그인 후 이용해주세요.");
        } else{
            navigate('/order', {state:{id: 1, cnt: cnt, product: product, price: product.price}});
        }
    };

    //리뷰보기 버튼 클릭
    const onReviewClick = () => {
        navigate('/review', {state:{id: id}});
    };

    return (
        <div>
            <div className={"img-container"}>
                <img className={"product-img"} src={product.productImg} />
            </div>
            <div className={"name-container"}>
                <text>{product.productName}</text>
            </div>
            <div className={"price-container"}>
                <text>{product.productPrice}원</text>
            </div>
            <div className={"description-container"}>
                <text>{product.productDescription}</text>
            </div>
            <div className={"button-container"}>
                <div className={"product-button-div"}>
                    <button onClick={openModal} className={"product-button"}>구매하기</button>
                    <button className={"product-button"} onClick={onReviewClick}>후기보기</button>
                </div>
                <Modal isOpen={modalIsOpen} style={customStyles}>
                    <div className={"modal-div"}>
                        <h2>{product.productName}</h2>
                        <text>수량을 선택하세요</text>
                        <div>
                            <button className={"modal-button"} onClick={onCntMinus}>-</button>
                            <text> {cnt} </text>
                            <button className={"modal-button"} onClick={onCntPlus}>+</button>
                        </div>
                        <div className={"modal-div2"}>
                            <button className={"modal-button-close"} onClick={closeModal}>close</button>
                            <button className={"modal-button-close"} onClick={onCart}>장바구니</button>
                            <button className={"modal-button-close"} onClick={onOrder}>구매하기</button>
                        </div>
                    </div>
                </Modal>
            </div>
        </div>
    );
}

export default Product;