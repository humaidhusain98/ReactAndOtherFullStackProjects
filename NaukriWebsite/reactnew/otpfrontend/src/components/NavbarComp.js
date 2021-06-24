import {Navbar,Nav,NavDropdown} from 'react-bootstrap';
import {useEffect,useState} from 'react';
import {Link} from 'react-router-dom';
import axios from 'axios';

const NavbarComp = ({auth}) => {
  // const [data,setData]=useState(null);

    useEffect(()=>{

    },[auth]);

    const handleLogout=()=>{
      axios.get("http://localhost:4000/otp/logout",{withCredentials:true}).then((res)=>{
        console.log(res.data.success);
        window.location.reload();
      }).catch((err)=>{
        console.log("Some error occured in logging out");
      })
    }
  



    // useEffect(()=>{

    //   axios.get('http://localhost:4000/token',{withCredentials:true}).then((res)=>{
    //     setloggedin(true);
    //   }).catch((err)=>{
    //     setloggedin(false);
    //   });

    // },[])


    return (   <Navbar sticky="top" collapseOnSelect expand="lg" bg="dark" variant="dark">
    <Navbar.Brand href="#home">TalGenie</Navbar.Brand>
    <Navbar.Toggle aria-controls="responsive-navbar-nav" />
    <Navbar.Collapse id="responsive-navbar-nav">
      {!auth && <Nav className="mr-auto">
         
          <Nav.Link href="/login">Login</Nav.Link>
        
       
      </Nav>}
      {
        auth &&  <Nav>
          <Nav.Link href="/home">Home</Nav.Link>
          <Nav.Link  onClick={handleLogout}>Logout</Nav.Link>
        </Nav>
      }
    </Navbar.Collapse>
   
  </Navbar>);
}
 
export default NavbarComp;