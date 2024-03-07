import React, {useState} from 'react';
import "./Login.css"
import {Link} from "react-router-dom";
import axios from "axios";

function Login() {


    const [person, setPerson] = useState({
        memberId: "",
        password: "",
    });
    const handleInputChange = (e) => {
        setPerson({ ...person, [e.target.name]: e.target.value });
    };
    const onSubmit = () => {
        axios.post("/api/member/login", person)
            .then(r => {
                console.log(r);
                console.log(r.data.accessToken);
                sessionStorage.setItem("token", r.data.accessToken);
                axios.get("/api/member/info",
                    {
                        headers: {Authorization: "Bearer " + sessionStorage.getItem("token")}
                    }
                ).then(r => {
                    console.log(r.data);
                    sessionStorage.setItem("person", JSON.stringify(r.data));
                })
                window.alert("로그인 성공하셨습니다.");
                window.location.reload();
            })
            .catch(e => {
                console.log(e);
                window.alert("로그인 실패하셨습니다.");
            });
    };


    return (
        <div>
            <div className={"box"}>
                <h1 className={"login-h1"}>로그인/회원가입</h1>
                <div className="login-div">
                    <text className={"login-text"}>아이디:</text>
                    <input
                        type="text"
                        placeholder="아이디 입력"
                        className={"login-input"}
                        name="memberId"
                        value={person.memberId}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="login-div">
                    <text className={"login-text"}>비밀번호:</text>
                    <input
                        type="text"
                        placeholder="비밀번호 입력"
                        className={"login-input"}
                        name="password"
                        value={person.password}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="login-button-div">
                    <button
                        className={"login-button"}
                        type="button"
                        onClick={onSubmit}>로그인
                    </button>
                </div>
                <div>
                    <Link to="/signUp" className={"signUp-text"}><text>회원가입하러가기</text></Link>
                </div>
            </div>
        </div>
    );
}

export default Login;