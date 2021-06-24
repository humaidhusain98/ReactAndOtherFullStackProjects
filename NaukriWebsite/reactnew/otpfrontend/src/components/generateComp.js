import { useState } from 'react';
import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';
import {Button,Alert,Breadcrumb,Card,Form,Container,Row,Col} from 'react-bootstrap';
import axios from 'axios';
import { useHistory } from "react-router-dom";

const GenerateOtp = () => {
    const history = useHistory();

    

    const handleClick=()=>{
       axios.get("http://localhost:4000/otp/generateOTP?mobileNumber="+phone).then((res)=>{
           alert("OTP SuccessFully Generated");
           seterror(res.data.message);   
           history.push("/verify",{phone:phone});

       }).catch((err)=>{
           console.log(err);
           seterror(err.response.data.message);
       })
    }

    const [error,seterror]=useState(null);
    const [phone,setphone]=useState(null);
    const [disabled,setdisabled]=useState(true);

    return (   <div className="generateotp-component">
    {error &&<div className="error">{error}</div>}
    <Card>
        <Card.Title style={{color:'black'}}>Enter Mobile!</Card.Title>
        <Card.Body>
            <Form>
                <Form.Group>
                    <Row style={{color:'black'}}>
                         <Form.Control type="text" onChange={(e)=>{setphone(e.target.value);
                         if(phone){
                                if(phone.length>=9){
                                    setdisabled(false);
                                }
                                else
                                {
                                    setdisabled(true);
                                }
                         }}} placeholder="Give Phone Number !"></Form.Control>
     
                    </Row>
                    <Button disabled={disabled} onClick={handleClick}>GenerateOtp</Button>
                </Form.Group>
            </Form>

        </Card.Body>
    </Card> 
    </div>);
}
 
export default GenerateOtp;