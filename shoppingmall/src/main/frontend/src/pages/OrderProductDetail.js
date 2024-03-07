import {useLocation} from "react-router-dom";
import React, {useEffect, useState} from "react";
import axios from "axios";
import './OrderProductDetail.css'


function OrderProductDetail(){

    const location = useLocation();
    //주문 상품
    const id = location.state.id;
    const [product, setProduct] = useState([]);


    //처음 한 번만 실행
    useEffect(()=>{
        axios.get("/api/orderDetail?id="+id)
            .then(r => {
                console.log(r);
                setProduct(r.data);
            })
            .catch(e => {
                console.log(e);
            });
    },[]);

    return(
        <div>
            <div className={"orderProductDetail-div"}>
                <h1>주문 상품</h1>
                {product && product.map((a) => (
                    <button key={a.orderDetailId} className={"orderProductDetail-button"}>
                        <div className={"orderProductDetail-div2"}>
                            <img className={"orderProductDetail-img"} src={a.product.productImg}/>
                            <div>
                                <span> {a.product.productName} </span><br/>
                                <span> {a.orderDetailPrice}원 </span><br/>
                                <span> {a.orderDetailCnt}개 </span>
                            </div>
                        </div>
                    </button>
                ))}
            </div>
        </div>
    );
}

export default OrderProductDetail;