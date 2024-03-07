import React from 'react';
import './Header.css'
import {Link} from "react-router-dom";

const Header = () => {
    return (
        <header>
            <div>
                <nav>
                    <ul>
                        <Link to="/" style={{textDecoration:"none"}}><li>Home</li></Link>
                        <Link to="/cart" style={{textDecoration:"none"}}><li>Cart</li></Link>
                        <Link to="/myPage" style={{textDecoration:"none"}}><li>MyPage</li></Link>
                        <Link to="/login" style={{textDecoration:"none"}}><li>Login</li></Link>
                    </ul>
                </nav>
            </div>
        </header>
    );
};

export default Header;