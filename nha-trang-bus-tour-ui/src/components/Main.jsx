import React from 'react'
import './vendor/bootstrap/css/bootstrap.min.css'
import './vendor/icons/icofont.min.css'
import './css/custom.css'
import './vendor/sidebar/demo.css'
import './img/logo.png'

const Main = () => {
    return (
        <>
            <meta charSet="utf-8" />
            <meta
                name="viewport"
                content="width=device-width, initial-scale=1, shrink-to-fit=no"
            />
            <meta name="description" content="" />
            <meta name="author" content="" />
            <link rel="icon" type="image/png" href="./img/logo.png" />
            <title>Nha Trang Bus Tour</title>
            
            <div className="osahan-index bg-c d-flex align-items-center justify-content-center vh-100 index-page">
                <div className="text-center">
            
                    <a href="landing.html">
                        <i className="icofont-bus text-white display-1 bg-danger p-4 rounded-circle" />
                    </a>
                    <br />
                    
                    <div className="spinner" />
                </div>
            </div>
            <div className="osahan-fotter fixed-bottom m-3">
            <h1>Group 13 - Nha Trang Bus Tour</h1>
                <a
                    className="btn btn-block px-4 py-3 d-flex align-items-center osahanbus-btlan btn-danger text-white shadow"
                    href="landing.html"
                >
                    Get Started  <i className="icofont-arrow-right ml-auto" />
                </a>
            </div>
            {}
        </>

    )
}

export default Main