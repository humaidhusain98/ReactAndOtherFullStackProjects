import {useEffect,useState} from 'react';
import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';
import {Button,Alert,Breadcrumb,Card,Form,Container,Row,Col} from 'react-bootstrap';
import axios from 'axios';
const JobComp = () => {
    const [alljobs,setalljobs]=useState(null);
    const [error,seterror]=useState(null);

    useEffect(()=>{
            axios.get("http://localhost:4000/jobs/all").then((res)=>{
                setalljobs(res.data);
                console.log(res.data);
            }).catch(()=>{
                seterror("Oops! Something went Wrong!");
            })
    },[])

    return (
        <div className="jobComp"> 
        {error &&<p>{error}</p>}
        {
          alljobs && alljobs.map((job)=>(
              <div className="job" key={job._id}>
              <Container>
                <Card>
                    <Card.Title style={{color:'black'}}>{job.jobTitle}</Card.Title>
                </Card>
                </Container>
                </div>
            ))
        }
        </div> 
        );
}
 
export default JobComp;