import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';
import {Button,Alert,Breadcrumb,Card,Form,Container,Row,Col} from 'react-bootstrap';
import { useState } from 'react';


const Register = () => {
    const userObj={};
    const [error,seterror]=useState(null);

    const handleClick=()=>{
        if(userObj.first_name && userObj.last_name && userObj.mobile)
        {
            fetch('http://localhost:4000/register?first_name='+userObj.first_name+'&last_name='+userObj.last_name+'&mobileNumber='+userObj.mobile).then(()=>{
                seterror("User SuccessFully Registered")
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
                             <Form.Control type="text" onChange={(e)=>{userObj.mobile=e.target.value;}} placeholder="Give Mobile Number !"></Form.Control>
                    </Row>
                    <Button onClick={handleClick}>Register</Button>
                </Form.Group>
            </Form>

        </Card.Body>
    </Card> 
    </div>);
}
 
export default Register;