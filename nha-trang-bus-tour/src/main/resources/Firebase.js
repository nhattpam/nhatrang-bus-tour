// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyAn_UB4CaJsDnJ7xzumn8piCla-FGDvSVY",
    authDomain: "swdproject-f1034.firebaseapp.com",
    projectId: "swdproject-f1034",
    storageBucket: "swdproject-f1034.appspot.com",
    messagingSenderId: "1033078286453",
    appId: "1:1033078286453:web:15084ed7add07f0fb6703c",
    measurementId: "G-JTLR1GE9B7"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);