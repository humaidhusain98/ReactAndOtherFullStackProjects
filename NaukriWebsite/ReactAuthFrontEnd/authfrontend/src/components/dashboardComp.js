import axios from 'axios';
import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';
import {Button,Alert,Breadcrumb,Card,Form,Container,Row,Col} from 'react-bootstrap';
import {useEffect,useState} from 'react';
import { useHistory } from "react-router-dom";

const Dashboard = () => {
    const history = useHistory();

    const [user,setuser]=useState(null);
    const [error,seterror]=useState(null);

    useEffect(()=>{
        axios.get("http://localhost:4000/isValid",{withCredentials:true}).then((res)=>{
            if(!res.data.valid)
            {
                axios.get("http://localhost:4000/token",{withCredentials:true}).then((res)=>{
                        console.log(res.data);
                        console.log("JWT refreshed!");
                }).catch((err)=>{console.log("Error");seterror(err.message);history.push("/login");})
            }
            axios.get("http://localhost:4000/posts",{withCredentials:true}).then((res)=>{
                setuser(res.data[0]);
            }).catch((err)=>{seterror(err)});
            
        }).catch((error)=>{seterror("Connection to the server cannot be made")});
    },[])




    return (<div className="dashboard-comp">
                {error && <p>{error}</p>}
                {user && <p>{user.first_name}</p>}
            </div>
          );
}
 
export default Dashboard;