import 'bootstrap/dist/css/bootstrap.min.css';
import React from "react";
import {Route, Routes} from "react-router-dom";
import Header from "./components/layout/Header";
import Footer from "./components/layout/Footer";
import Main from "./pages/Main";
import Login from "./pages/Login";
import SignUp from "./pages/SignUp";
import MyPage from "./pages/MyPage";
import Cart from "./pages/Cart";
import Product from "./pages/Product";
import Modal from "react-modal"
import Order from "./pages/Order";
import OrderProduct from "./pages/OrderProduct";
import OrderProductDetail from "./pages/OrderProductDetail";
import RegisterProduct from "./pages/RegisterProduct";
import Review from "./pages/Review";

Modal.setAppElement('#root')
function App() {
    return (
        <>
            <Header/>
            <Routes>
                <Route path="/" element ={<Main/>}></Route>
                <Route  path="/login" element ={<Login/>}></Route>
                <Route  path="/signUp" element={<SignUp/>}></Route>
                <Route  path="/myPage" element={<MyPage/>}></Route>
                <Route  path="/cart" element={<Cart/>}></Route>
                <Route  path="/product" element={<Product/>}></Route>
                <Route  path="/order" element={<Order/>}></Route>
                <Route  path="/orderProduct" element={<OrderProduct/>}></Route>
                <Route  path="/orderProductDetail" element={<OrderProductDetail/>}></Route>
                <Route path="/registerProduct" element={<RegisterProduct/>}></Route>
                <Route path="/review" element={<Review/>}></Route>
            </Routes>
            <Footer/>
        </>
    );
}

export default App;
