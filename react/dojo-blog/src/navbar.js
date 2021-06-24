import { Link } from 'react-router-dom';

const Navbar = () => {
    return ( 
        <nav className="navbar">
            <h1>Programming Benchmarks</h1>
            <Link to ="/">Benchmarks</Link>
            <Link to ="/create" style={
                {color:"white",
                backgroundColor: '#f1356d',
                borderRadius: '8px'
                }
                }>New Blog</Link>
         </nav>   
     );
}
 
export default Navbar;