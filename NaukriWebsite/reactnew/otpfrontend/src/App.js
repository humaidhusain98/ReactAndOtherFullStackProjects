import './App.css';
import axios from 'axios';
import {useState,useEffect} from 'react';
import {BrowserRouter as Router,Route,Switch} from 'react-router-dom';
import NavbarComp from './components/NavbarComp';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import {Button,Alert,Breadcrumb,Card,Form,Container,Row,Col} from 'react-bootstrap';
import GenerateOtp from './components/generateComp';
import OtpLogin from './components/otplogin';
import Register from './components/register';
import JobComp from './components/jobComp';

function App() {

  const [isloggedIn,setlogged]=useState(false);
  const [error,seterror]=useState(null);

  useEffect(()=>{
    axios.get("http://localhost:4000/isAuth",{withCredentials:true}).then((res)=>{
      console.log(res.data.auth);
        setlogged(res.data.auth);
        seterror(null);
    }).catch((err)=>{
        seterror("Oops Connection to Server lost");
    })
  },[]);

  return (
    <Router>
    <div className="App">
      <NavbarComp auth={isloggedIn}/>
      <header className="App-header">
        {error &&<p>{error}</p>}
        {!error &&
          <Switch>
          <Route exact path ="/login">
            <GenerateOtp/>
          </Route>
          <Route exact path ="/verify">
            <OtpLogin/>
          </Route>
          <Route exact path ="/register">
            <Register/>
          </Route>
          <Route exact path="/">
            <JobComp/>  
          </Route>
        </Switch>
        }
      </header>
    </div>
    </Router>
  );
}

export default App;
