import React, {useState} from 'react';
import "./SignUp.css"
import axios from "axios";

function SignUp() {

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

    const [isNullCheck, setIsNullCheck] = useState(false);

    const handleInputChange = (e) => {
        setPerson({ ...person, [e.target.name]: e.target.value });
    };

    const onSubmit = () => {
        //회원가입 객체 공백 체크
        for(const value of Object.values(person)){
            if(! value){
                setIsNullCheck(true);
            }
        }
        //공백이 있을 경우 alert
        if(isNullCheck == true){
            window.alert("공백이 있습니다.");
        }
        //공백이 없을 경우 axios.post
        else{
            axios.post("/api/member/join", person)
                .then(r => {
                    console.log(r);
                    window.alert("회원가입에 성공하셨습니다.");
                })
                .catch(e => {
                    if(e.response && e.response.status == 409){
                        window.alert("아이디가 중복됩니다.");
                    } else{
                        window.alert("회원가입에 실패하셨습니다.");
                    }
                });
        }
    };


    return (
        <div>
            <div className={"signup-box"}>
                <h1 className={"signup-h1"}>회원가입</h1>
                <div className="signup-div">
                    <text className={"signup-text"}>아이디:</text>
                    <input
                        type="text"
                        placeholder="아이디 입력"
                        className={"signup-input"}
                        name="memberId"
                        value={person.memberId}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="signup-div">
                    <text className={"signup-text"}>비밀번호:</text>
                    <input
                        type="text"
                        placeholder="비밀번호 입력"
                        className={"signup-input"}
                        name="password"
                        value={person.password}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="signup-div">
                    <text className={"signup-text"}>이름:</text>
                    <input
                        type="text"
                        placeholder="이름 입력"
                        className={"signup-input"}
                        name="memberName"
                        value={person.memberName}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="signup-div">
                    <text className={"signup-text"}>전화번호:</text>
                    <input
                        type="text"
                        placeholder="전화번호 입력"
                        className={"signup-input"}
                        name="memberTel"
                        value={person.memberTel}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="signup-div">
                    <text className={"signup-text"}>성별:</text>
                    <input
                        type="text"
                        placeholder="성별 입력"
                        className={"signup-input"}
                        name="memberGender"
                        value={person.memberGender}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="signup-div">
                    <text className={"signup-text"}>우편번호:</text>
                    <input
                        type="text"
                        placeholder="우편번호 입력"
                        className={"signup-input"}
                        name="memberZipCode"
                        value={person.memberZipCode}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="signup-div">
                    <text className={"signup-text"}>주소:</text>
                    <input
                        type="text"
                        placeholder="주소 입력"
                        className={"signup-input"}
                        name="memberAddress"
                        value={person.memberAddress}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="signup-div">
                    <text className={"signup-text"}>상세주소:</text>
                    <input
                        type="text"
                        placeholder="상세주소 입력"
                        className={"signup-input"}
                        name="memberAddressDetail"
                        value={person.memberAddressDetail}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="signup-div">
                    <text className={"signup-text"}>요구사항:</text>
                    <input
                        type="text"
                        placeholder="요구사항 입력"
                        className={"signup-input"}
                        name="memberRequirements"
                        value={person.memberRequirements}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="signup-button-div">
                    <button
                        className={"signup-button"}
                        type="button"
                        onClick={onSubmit}
                    >회원가입
                    </button>
                </div>
            </div>
        </div>
    );
}

export default SignUp;