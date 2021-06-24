import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';
import {Button,Alert,Breadcrumb,Card,Form,Container,Row,Col} from 'react-bootstrap';
import { useState } from 'react';
import { useLocation } from "react-router-dom";
import { useHistory } from "react-router-dom";
import axios from 'axios';

axios.defaults.withCredentials=true;



const OtpLogin = () => {
    const history = useHistory();
    const location = useLocation();
    var number="+91";
    if(location.state)
    {
        number=location.state.phone;
    }

    const [phone,setphone]=useState(number);
    const [error,seterror]=useState(null);
    
   
    
    const [otp,setOtp]=useState(null);

    // const createCookie = ()=>{
    //     axios.get('http://localhost:4000',{withCredentials:true}).then((res)=>{
    //         console.log(res.data);
    //     })
    // }

    // const deleteCookie = ()=>{
    //     axios.get('http://localhost:4000/deleteCookie',{withCredentials:true}).then((res)=>{
    //         console.log(res.data);
    //     })
    // }
    
    const handleClick=()=>{
        if(phone && otp)
        {
            axios.get('http://localhost:4000/otp/validateOTP?mobileNumber='+phone+'&otp='+otp,{withCredentials:true}).then((res)=>{
                alert("User Authenticated Successfully!");
                console.log(res.data);
                seterror(null);
                if(res.data.new)
                {
                    history.push("/register",{phone:phone});
                }
                else
                {
                    history.push("/");
                }
            
                window.location.reload();
                
            }).catch((err)=>{
                seterror("Not a valid Otp :"+err);
            });
        }
        else
        {
         seterror("Please enter valid input !!!");
        }
    }



    return ( 
    <div className="otplogin-component">
    {error &&<div className="error">{error}</div>}
    <Card>
        <Card.Title style={{color:'black'}}>Enter OTP!</Card.Title>
        <Card.Body>
            <Form>
                <Form.Group>
                    <Row style={{color:'black'}}>
                         <Form.Control type="text" value={phone} onChange={(e)=>{setphone(e.target.value)}} placeholder="Give 8 Digit OTP !"></Form.Control>
                            {/* <Form.Control type="text"   onChange={(e)=>{console.log(e.target.value)}} placeholder="Give First Name !">Hello</Form.Control> */}
                    </Row>
                    <Row>
                             <Form.Control type="number" onChange={(e)=>{setOtp(e.target.value)}} placeholder="Give 8 Digit OTP !"></Form.Control>
                    </Row>
                    <Button onClick={handleClick}>Submit</Button>
                </Form.Group>
            </Form>

        </Card.Body>
    </Card> 
    </div>);
}
 
export default OtpLogin;