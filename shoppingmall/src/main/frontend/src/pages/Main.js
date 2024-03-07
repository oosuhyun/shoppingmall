import './Main.css'
import './Paging.css'
import React, {useEffect, useState} from "react";
import axios from "axios";
import Pagination from 'react-js-pagination'
import {Link, useNavigate} from "react-router-dom";

function Main() {

    const [search, setSearch] = useState("");
    const [category, setCategory] = useState("top");
    const [product, setProduct] = useState([]);
    const [page, setPage] = useState(1);
    const [totalItemsCount, setTotalItemsCount] = useState();
    const navigate = useNavigate();

    //처음 한 번만 실행
    useEffect(()=>{
        getProduct();
    },[]);

    //page, category state 값이 변경됐을 때마다 실행
    useEffect(()=>{
        getProduct();
    },[page, category]);

    //검색어입력
    const onSearchChange = (e) => {
        setSearch(e.target.value)
    }
    //검색
    const onSearchSubmit = () => {
        let pageMinusOne = page - 1;
        axios.get("/api/product/search?keyword="+search+"&page="+pageMinusOne)
            .then(r => {
                console.log(r);
                setProduct(r.data.content);
                setTotalItemsCount(r.data.totalElements);
            })
            .catch(e => {
                console.log(e);
            });
    };
    //카테고리 클릭
    const onCategoryClick = (c) => {
        setCategory(c);
        getProduct();
    };

    //페이지이동
    const handlePageChange = (page) => {
        setPage(page);
        getProduct();
    };

    //상품 클릭
    const onProductClick = (i) => {
        navigate('/product', {state:{id: i}});
    };

    function getProduct() {
        let pageMinusOne = page - 1;
        axios.get("/api/product/filter?category="+category+"&page="+pageMinusOne)
            .then(r => {
                console.log(r);
                setProduct(r.data.content);
                setTotalItemsCount(r.data.totalElements);
            })
            .catch(e => {
                console.log(e);
            });
    }

    return (
        <div>
            {/*검색창*/}
            <div className="search">
                <input className={"search-input"} type="text" placeholder="검색어 입력" onChange={onSearchChange}/>
                <button
                    className={"search-button"}
                    type="button"
                    onClick={onSearchSubmit}>검색
                </button>
            </div>
            {/*카테고리*/}
            <div className={"category"}>
                <button className={"category-button"} onClick={() => {onCategoryClick("top")}}>상의</button>
                <button className={"category-button"} onClick={() => {onCategoryClick("bottom")}}>하의</button>
                <button className={"category-button"} onClick={() => {onCategoryClick("etc")}}>기타</button>
                <button className={"category-button"} onClick={() => {onCategoryClick("accessory")}}>악세사리</button>
            </div>
            <hr/>
            {/*상품목록*/}
            <div className={"main-product"}>
                {product && product.map((a) => (
                    <button key={a.productId} className={"main-product-button"} onClick={() =>onProductClick(a.productId)}>
                        <div>
                            <img className={"main-product-img"} src={a.productImg} />
                            <span> {a.productPrice}원 </span><br/>
                            <span> {a.productName} </span>
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


export default Main;