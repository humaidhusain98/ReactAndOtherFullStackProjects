import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';
import {Button,Alert,Breadcrumb,Card,Form,Container,Row,Col} from 'react-bootstrap';
import { useState } from 'react';
import { useLocation } from "react-router-dom";
import { useHistory } from "react-router-dom";
import axios from 'axios';

const Register = () => {
    const history = useHistory();
    const location = useLocation();
    var number="+91";
    if(location.state)
    {
        number=location.state.phone;
    }

    const [phone,setphone]=useState(number);

    const userObj={mobile:phone};
    const [error,seterror]=useState(null);

    const handleClick=()=>{
        console.log(userObj);
        if(userObj.first_name && userObj.last_name && userObj.mobile)
        {
            axios.get('http://localhost:4000/otp/register?first_name='+userObj.first_name+'&last_name='+userObj.last_name+'&mobileNumber='+userObj.mobile,{withCredentials:true}).then(()=>{
                seterror("User SuccessFully Registered");
                history.push("/");
            }).catch((err)=>{
                seterror("Some Error has occured :"+err);
            });
        }
        else
        {
         seterror("Invalid Input! Please enter valid inputs and try again");
        }
    }

    return ( 
    <div className="register-component">
    {error && <div className="response">{error}</div>}
    <Card>
        <Card.Title style={{color:'black'}}>Register Now!</Card.Title>
        <Card.Body>
            <Form>
                <Form.Group>
                    <Row>
                            <Form.Control type="text" onChange={(e)=>{userObj.first_name=e.target.value;}} placeholder="Give First Name !"></Form.Control>
                    </Row>
                    <Row>
                             <Form.Control type="text" onChange={(e)=>{userObj.last_name=e.target.value;}} placeholder="Give Last Name !"></Form.Control>
                    </Row>
                    <Row>
                             <Form.Control type="text" value={phone} onChange={(e)=>{userObj.mobile=e.target.value;setphone(e.target.value);}} placeholder="Give Mobile Number !"></Form.Control>
                    </Row>
                    <Button onClick={handleClick}>Register</Button>
                </Form.Group>
            </Form>

        </Card.Body>
    </Card> 
    </div>);
}
 
export default Register;