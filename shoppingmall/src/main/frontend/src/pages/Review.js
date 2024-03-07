import './Review.css'
import {useLocation} from "react-router-dom";
import React, {useEffect, useState} from "react";
import axios from "axios";
import {PiStarFill, PiStarLight} from "react-icons/pi";
import './Paging.css'
import Pagination from "react-js-pagination";
import moment from 'moment';
// import 'moment/locale/kr';

function Review(){

    const location = useLocation();
    //product id 정보
    const id = location.state.id;
    //로그인 정보
    const [person, setPerson] = useState({});
    //현재 페이지 정보
    const [page, setPage] = useState(1);
    //총 후기 갯수 정보
    const [totalItemsCount, setTotalItemsCount] = useState();
    //해당 상품의 리뷰 정보
    const [review, setReview] = useState([]);
    //별점 정보
    const [rating, setRating] = useState(3);
    const [sendImage, setSendImage] = useState(null);
    //등록할 리뷰 정보
    const [registerReview, setRegisterReview] = useState({
        reviewContent: "",
        reviewRating: 0,
        memberId: 0,
        productId: 0
    });


    useEffect(()=>{
        setPerson(JSON.parse(sessionStorage.getItem("person")));
        let pageMinusOne = page - 1;
        axios.get("/api/review/product?id="+id+"&page="+pageMinusOne)
            .then(r => {
                console.log(r);
                setReview(r.data.content);
                setTotalItemsCount(r.data.totalElements);
            })
            .catch(e => {
                console.log(e);
            });
    },[]);

    useEffect(()=>{
        if(person != null){
            setRegisterReview({
                reviewContent: "",
                reviewRating: rating,
                memberId: person.id,
                productId: id});
        }
    },[person]);

    const onChangeImage = e => {
        if(e.target.files[0]){
            setSendImage(e.target.files[0]);
        }
    };

    const onChaneTextArea = (e) => {
        setRegisterReview({ ...registerReview, reviewContent: e.target.value });
    };

    const onRagingClick = (i) => {
        console.log(i);
        setRating(i);
        setRegisterReview({ ...registerReview, reviewRating: i});
    };



    //주문하기 버튼 클릭
    const onReviewClick = () => {

        const formData = new FormData();
        formData.append('file', sendImage);
        const blob = new Blob([JSON.stringify(registerReview)], { type: "application/json" });
        formData.append('req', blob);
        console.log(formData);

        axios.post("/api/review", formData, {
            headers: {
                "Contest-Type": "multipart/form-data"
            }
        })
            .then(r => {
                console.log(r);
                window.alert("후기 등록 완료");
            })
            .catch(e => {
                window.alert("후기 등록 실패");
            });
    };

    //페이지이동
    const handlePageChange = (page) => {
        setPage(page);
        let pageMinusOne = page - 1;
        axios.get("/api/review/product?id="+id+"&page="+pageMinusOne)
            .then(r => {
                console.log(r);
                setReview(r.data.content);
                setTotalItemsCount(r.data.totalElements);
            })
            .catch(e => {
                console.log(e);
            });
    };

    return(
        <div className={"review-div"}>
            <h1>후기 페이지</h1>
            <div className={"review-register-div"}>
                <text style={{fontWeight:"bold", fontSize:"20px", margin:"1%"}}>후기 작성하기</text>
                <div>
                    <input
                        type="file"
                        onChange={onChangeImage}
                        accept={".png, .jpg"}
                    />
                </div>
                <textarea
                    className={"review-register-textarea"}
                    placeholder="Input some text."
                    onChange={onChaneTextArea}
                />
                <div>
                    {[...Array(rating)].map((a, i) => (
                        <PiStarFill className="star-lg" key={i} onClick={() => onRagingClick(i + 1)} />
                    ))}
                    {[...Array(5 - rating)].map((a, i) => (
                        <PiStarLight className="star-lg" key={i} onClick={() => onRagingClick(rating + i + 1)} />
                    ))}
                </div>
                <button onClick={onReviewClick} className={"review-register-button"}>후기 쓰기</button>
            </div>
            <div className={"review-byOne-div"}>
                <text style={{fontWeight:"bold", fontSize:"20px", margin:"1%"}}>후기</text>
                {review && review.map((a) => (
                    <button key={a.reviewId} className={"review-byOne-button"}>
                        <div className={"review-byOne-div2"}>
                            <img className={"review-byOne-img"} src={a.reviewImg}/>
                            <div className={"review-byOne-div3"}>
                                <span> 작성자: {a.member.memberName} </span><br/>
                                <span> 작성 날짜: {moment(a.createdDate).format('YYYY.MM.DD HH:mm')}</span><br/>
                                <span> {a.reviewContent} </span><br/>
                                <div>
                                    {/*꽉 찬 별*/}
                                    {[...Array(a.reviewRating)].map((a, i) => (
                                        <PiStarFill className="star-lg" key={i}/>
                                    ))}
                                    {/*빈 별*/}
                                    {[...Array(5 - a.reviewRating)].map((a, i) => (
                                        <PiStarLight className="star-lg" key={i}/>
                                    ))}
                                </div>
                            </div>
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
    )
}

export default Review;