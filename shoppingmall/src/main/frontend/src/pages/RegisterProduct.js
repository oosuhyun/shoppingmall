import axios from "axios";
import './RegisterProduct.css'
import React, {useEffect, useState} from "react";
import {type} from "@testing-library/user-event/dist/type";

function RegisterProduct() {

    const [product, setProduct] = useState({
        productName: "",
        productDescription: "",
        productPrice: 0,
        productRestCnt: 0,
        productCategory: "top",
        id: 0
    });
    const [person, setPerson] = useState({});

    //처음 한 번만 실행
    useEffect(()=>{
        setPerson(JSON.parse(sessionStorage.getItem("person")));
    },[]);

    //person state 값이 변경됐을 때마다 실행
    useEffect(()=>{
        if(person != null){
            setProduct({ ...product, id: person.id});
        }
    },[person]);

    const handleInputChange = (e) => {
        setProduct({ ...product, [e.target.name]: e.target.value});
    };

    const [sendImage, setSendImage] = useState(null);

    const onChangeImage = e => {
        if(e.target.files[0]){
            setSendImage(e.target.files[0]);
        }
    };

    const onSubmit = async () => {
        const formData = new FormData();
        formData.append('file', sendImage);
        const blob = new Blob([JSON.stringify(product)], { type: "application/json" });
        formData.append('req', blob);
        console.log(formData);

        await axios.post("/api/product", formData, {
            headers: {
                "Contest-Type": "multipart/form-data"
            }
        })
            .then(r => {
                console.log(r);
                window.alert("상품 등록 완료");
            })
            .catch(e => {
                window.alert("상품 등록 실패");
            });
    };

    return(
        <div>
            <div className={"registerProduct-div"}>
                <h1 className={"registerProduct-h1"}>상품 등록</h1>
                <div className="registerProduct-product-div">
                    <text className={"registerProduct-text"}>상품 이미지:</text>
                    <input
                        type="file"
                        onChange={onChangeImage}
                        className={"registerProduct-input"}
                        accept={".png, .jpg"}
                    />
                </div>
                <div className="registerProduct-product-div">
                    <text className={"registerProduct-text"}>상품명:</text>
                    <input
                        type="text"
                        placeholder="상품명 입력"
                        className={"registerProduct-input"}
                        name="productName"
                        value={product.productName}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="registerProduct-product-div">
                    <text className={"registerProduct-text"}>상품 설명:</text>
                    <input
                        type="text"
                        placeholder="상품 설명 입력"
                        className={"registerProduct-input"}
                        name="productDescription"
                        value={product.productDescription}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="registerProduct-product-div">
                    <text className={"registerProduct-text"}>상품 가격:</text>
                    <input
                        type="text"
                        placeholder="상품 가격 입력"
                        className={"registerProduct-input"}
                        name="productPrice"
                        value={product.productPrice}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="registerProduct-product-div">
                    <text className={"registerProduct-text"}>상품 재고수:</text>
                    <input
                        type="text"
                        placeholder="상품재고수 입력"
                        className={"registerProduct-input"}
                        name="productRestCnt"
                        value={product.productRestCnt}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="registerProduct-product-div">
                    <text className={"registerProduct-text"}>상품 카테고리:</text>
                    <select name={"productCategory"} onChange={handleInputChange} className={"registerProduct-input"}>
                        <option value={"top"}>상의</option>
                        <option value={"bottom"}>하의</option>
                        <option value={"etc"}>기타</option>
                        <option value={"accessory"}>악세사리</option>
                    </select>
                </div>
                <div className="registerProduct-button-div">
                    <button
                        className={"registerProduct-button"}
                        type="button"
                        onClick={onSubmit}
                    >상품 등록하기
                    </button>
                </div>
            </div>
        </div>
    );
}

export default RegisterProduct;