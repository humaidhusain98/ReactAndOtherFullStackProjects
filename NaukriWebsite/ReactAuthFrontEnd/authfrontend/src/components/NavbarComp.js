import {Navbar,Nav,NavDropdown} from 'react-bootstrap';
import {useEffect,useState} from 'react';
import {Link} from 'react-router-dom';
import axios from 'axios';

const NavbarComp = () => {
  // const [data,setData]=useState(null);

    const [loggedin,setloggedin]=useState(false);
  // useEffect(()=>{
  //     fetch('http://localhost:8000/headers').then((res)=>{
  //         if(!res.ok)
  //         {throw Error('Could not fetch the data for that resource');}
  //         return res.json();
  //     }).then((data)=>
  //     {
  //         setData(data);
  //         console.log(data);
  //     }).catch((e)=>{
  
  //         console.log("There has been an error"+e);
  //     });
  // },[]);

  const handleLogout=()=>{
    axios.delete("http://localhost:4000/logout",{withCredentials:true}).then((res)=>{
    
    console.log(res.data);
      setloggedin(false);
      window.location.reload();  
    }).catch((err)=>{
      console.log(err);
    })
  }


    useEffect(()=>{

      axios.get('http://localhost:4000/token',{withCredentials:true}).then((res)=>{
        setloggedin(true);
      }).catch((err)=>{
        setloggedin(false);
      });

    },[])


    return (   <Navbar sticky="top" collapseOnSelect expand="lg" bg="dark" variant="dark">
    <Navbar.Brand href="#home">TalGenie</Navbar.Brand>
    <Navbar.Toggle aria-controls="responsive-navbar-nav" />
    <Navbar.Collapse id="responsive-navbar-nav">
      {!loggedin && <Nav className="mr-auto">
         
          <Nav.Link href="/login">Login</Nav.Link>
        <Nav.Link href="/register">Register</Nav.Link>
        
       
      </Nav>}
      {
        loggedin &&  <Nav>
          <Nav.Link href="/home">Home</Nav.Link>
          <Nav.Link  onClick={handleLogout}>Logout</Nav.Link>
        </Nav>
      }
    </Navbar.Collapse>
   
  </Navbar>);
}
 
export default NavbarComp;