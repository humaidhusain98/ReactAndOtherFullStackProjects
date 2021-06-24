

import firebase from "firebase";

var firebaseConfig = {
    apiKey: "AIzaSyBU58vvnuHWN-GPeX-PdEVhDSqum_5B_AI",
    authDomain: "todoapp-1a27c.firebaseapp.com",
    projectId: "todoapp-1a27c",
    storageBucket: "todoapp-1a27c.appspot.com",
    messagingSenderId: "225895501946",
    appId: "1:225895501946:web:93a97e5638e2a758d9ebc3"
  };



const firebaseApp= firebase.initializeApp(firebaseConfig);

const db=firebaseApp.firestore();

export default db;