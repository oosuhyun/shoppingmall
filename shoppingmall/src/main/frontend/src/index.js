import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import {BrowserRouter} from "react-router-dom";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    // <React.StrictMode>
    //     <BrowserRouter>
    //         <App />
    //     </BrowserRouter>
    // </React.StrictMode>
    //react.strictMode 개발 단계에서 에러를 잘 감지하기 위해 두 번씩 렌더링되게 한다.
    <BrowserRouter>
        <App />
    </BrowserRouter>
);
