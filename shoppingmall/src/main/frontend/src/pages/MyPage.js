import React, {useEffect, useState} from 'react';
import "./MyPage.css"
import axios from "axios";
import {useNavigate} from "react-router-dom";

function MyPage() {

    const navigate = useNavigate();
    const [person, setPerson] = useState({
        memberId: "",
        password: "",
        memberName: "",
        memberTel: "",
        memberGender: "",
        memberZipCode: "",
        memberAddress: "",
        memberAddressDetail: "",
        memberRequirements: ""
    });

    const handleInputChange = (e) => {
        setPerson({ ...person, [e.target.name]: e.target.value });
    };

    useEffect(()=>{
        axios.get("/api/member/info",
    {
                headers: {Authorization: "Bearer " + sessionStorage.getItem("token")}
            }
        ).then(r => {
            console.log(r);
            setPerson(r.data);
        })
            .catch(e => {
                console.log(e);
                window.alert("로그인 후 이용해주세요");
            });
    },[]);

    const onSubmit = () => {
        axios.put("/api/member/update", person)
            .then(r => {
                window.alert("수정 완료되었습니다.");
            })
            .catch(e => {
                window.alert("수정 실패하셨습니다.");
            });
    };

    const infoOrderProduct = () => {
        navigate('/orderProduct');
    };

    const onRegisterProduct = () => {
        navigate('/registerProduct');
    };

    return (
        <div>
            <div className="myPage-orderProduct-button-div">
                <button
                    className={"myPage-orderProduct-button"}
                    type="button"
                    onClick={infoOrderProduct}>내 주문 내역<br/>확인하기
                </button>
                <button
                    className={"myPage-orderProduct-button"}
                    type="button"
                    onClick={onRegisterProduct}>판매 상품<br/>등록하기
                </button>
            </div>
            <div className={"myPage-box"}><br/>
                <h1 className={"myPage-h1"}>내정보</h1>
                <div className="myPage-div">
                    <text className={"myPage-text"}>아이디:</text>
                    <input
                        type="text"
                        defaultValue={person.memberId}
                        className={"myPage-order-info-input"}
                        name="memberId"
                        value={person.memberId}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="myPage-div">
                    <text className={"myPage-text"}>비밀번호:</text>
                    <input
                        type="text"
                        defaultValue={"****"}
                        className={"myPage-order-info-input"}
                    />
                </div>
                <div className="myPage-div">
                    <text className={"myPage-text"}>이름:</text>
                    <input
                        type="text"
                        defaultValue={person.memberName}
                        className={"myPage-order-info-input"}
                        name="memberName"
                        value={person.memberName}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="myPage-div">
                    <text className={"myPage-text"}>전화번호:</text>
                    <input
                        type="text"
                        defaultValue={person.memberTel}
                        className={"myPage-order-info-input"}
                        name="memberTel"
                        value={person.memberTel}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="myPage-div">
                    <text className={"myPage-text"}>성별:</text>
                    <input
                        type="text"
                        defaultValue={person.memberGender}
                        className={"myPage-order-info-input"}
                        name="memberGender"
                        value={person.memberGender}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="myPage-div">
                    <text className={"myPage-text"}>우편번호:</text>
                    <input
                        type="text"
                        defaultValue={person.memberZipCode}
                        className={"myPage-order-info-input"}
                        name="memberZipCode"
                        value={person.memberZipCode}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="myPage-div">
                    <text className={"myPage-text"}>주소:</text>
                    <input
                        type="text"
                        defaultValue={person.memberAddress}
                        className={"myPage-order-info-input"}
                        name="memberAddress"
                        value={person.memberAddress}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="myPage-div">
                    <text className={"myPage-text"}>상세주소:</text>
                    <input
                        type="text"
                        defaultValue={person.memberAddressDetail}
                        className={"myPage-order-info-input"}
                        name="memberAddressDetail"
                        value={person.memberAddressDetail}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="myPage-div">
                    <text className={"myPage-text"}>요구사항:</text>
                    <input
                        type="text"
                        defaultValue={person.memberRequirements}
                        className={"myPage-order-info-input"}
                        name="memberRequirements"
                        value={person.memberRequirements}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="myPage-button-div">
                    <button
                        className={"myPage-button"}
                        type="button"
                        onClick={onSubmit}
                    >수정하기
                    </button>
                </div>
            </div>
        </div>
    );
}

export default MyPage;