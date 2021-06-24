import {Navbar,Nav,NavDropdown} from 'react-bootstrap';
import {useEffect,useState} from 'react';
import {Link} from 'react-router-dom';


const NavbarComp = () => {
  const [data,setData]=useState(null);

  useEffect(()=>{
      fetch('http://localhost:8000/headers').then((res)=>{
          if(!res.ok)
          {throw Error('Could not fetch the data for that resource');}
          return res.json();
      }).then((data)=>
      {
          setData(data);
          console.log(data);
      }).catch((e)=>{
  
          console.log("There has been an error"+e);
      });
  },[]);


    return (   <Navbar sticky="top" collapseOnSelect expand="lg" bg="dark" variant="dark">
    <Navbar.Brand href="#home">Coding-Benchmarks</Navbar.Brand>
    <Navbar.Toggle aria-controls="responsive-navbar-nav" />
    <Navbar.Collapse id="responsive-navbar-nav">
      <Nav className="mr-auto">
        <Nav.Link href="/">Home</Nav.Link>
        <Nav.Link href="/about">About</Nav.Link>
        <NavDropdown title="Benchmarks" id="collasible-nav-dropdown">
          {
            data && data.map((header)=>(
              <div className="home-preview" key={header.id}>
              <a href={`/benchmarks/${header.id}`}>
              <p>{header.name}</p>
              </a>
              </div>
            ))
          }
        </NavDropdown>
      </Nav>
      <Nav>
       <Nav.Link href="/add">Add New Benchmark</Nav.Link>
    </Nav>
    </Navbar.Collapse>
   
  </Navbar>);
}
 
export default NavbarComp;