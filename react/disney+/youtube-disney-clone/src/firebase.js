import firebase from 'firebase';

// Your web app's Firebase configuration
const firebaseConfig = {
    apiKey: "AIzaSyDfN0xnqgkvaZMOEKf0EYFeihnIfxFuIaI",
    authDomain: "disneyplus-clone-dac24.firebaseapp.com",
    projectId: "disneyplus-clone-dac24",
    storageBucket: "disneyplus-clone-dac24.appspot.com",
    messagingSenderId: "161984246531",
    appId: "1:161984246531:web:4387f3f54b3d702c1b6f36"
  };
  // Initialize Firebase
 const firebaseApp =  firebase.initializeApp(firebaseConfig);
 const db = firebaseApp.firestore();
 const auth = firebase.auth();
 const provider = new firebase.auth.GoogleAuthProvider();
 const storage = firebase.storage();

 export { auth,provider,storage };
 export default db;