import React, {useEffect, useState} from 'react';
import axios from "axios";
import './OrderProduct.css'
import './Paging.css'
import Pagination from "react-js-pagination";
import {useNavigate} from "react-router-dom";
import moment from 'moment';

function OrderProduct() {

    //사용자 정보
    const [person, setPerson] = useState({});
    //페이지 정보
    const [page, setPage] = useState(1);
    //주문 목록 정보
    const [orderList, setOrderList] = useState([]);
    //주문 목록 갯수 정보
    const [totalItemsCount, setTotalItemsCount] = useState();
    //페이지 이동
    const navigate = useNavigate();


    //처음 한 번만 실행
    useEffect(()=>{
        //사용자 정보 set
        setPerson(JSON.parse(sessionStorage.getItem("person")));
    },[]);

    useEffect(()=>{
        if(person != null){
            let pageMinusOne = page - 1;
            axios.get("/api/order/my?id="+person.id+"&page="+pageMinusOne)
                .then(r => {
                    console.log(r);
                    setOrderList(r.data.content);
                    setTotalItemsCount(r.data.totalElements);
                })
                .catch(e => {
                    console.log(e);
                });
        }
    },[person]);

    //페이지이동
    const handlePageChange = (page) => {
        setPage(page);
        let pageMinusOne = page - 1;
        axios.get("/api/order/my?id="+person.id+"&page="+pageMinusOne)
            .then(r => {
                console.log(r);
                setOrderList(r.data.content);
                setTotalItemsCount(r.data.totalElements);
            })
            .catch(e => {
                console.log(e);
            });
    };

    //상품 클릭
    const onProductClick = (i) => {
        navigate('/orderProductDetail', {state:{id: i}});
    };

    return (
        <div>
            <div className={"orderProduct-div"}>
                <h1>주문 내역</h1>
                {orderList && orderList.map((a) => (
                    <button key={a.orderId} className={"orderProduct-button"} onClick={() =>onProductClick(a.orderId)}>
                        <div className={"orderProduct-div2"}>
                            <text style={{fontWeight:"bold", fontSize:"23px", margin:"1%"}}>
                                {moment(a.createdDate).format('YYYY-MM-DD')}
                            </text>
                            <text>
                                <br/>주문 번호: {a.orderId}
                                <br/>주문 상태: {a.orderStatus}
                                <br/>총 결제 금액: {a.orderTotalPrice}원
                            </text>
                            {/*<span>*/}
                            {/*    주문 날짜: {moment(a.createdDate).format('YYYY-MM-DD')}*/}
                            {/*    <br/>주문 번호: {a.orderId}*/}
                            {/*    <br/>주문 상태: {a.orderStatus}*/}
                            {/*    <br/>총 결제 금액: {a.orderTotalPrice}원*/}
                            {/*</span>*/}
                        </div>
                    </button>
                    ))}
            </div>
            <Pagination
                activePage={page}
                itemsCountPerPage={10}
                totalItemsCount={totalItemsCount}
                pageRangeDisplayed={5}
                prevPageText={"‹"}
                nextPageText={"›"}
                onChange={handlePageChange}
            />
        </div>
    );
}

export default OrderProduct;