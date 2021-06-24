import {useEffect,useState} from 'react';
import {Link} from 'react-router-dom';

const HomepageComp = () => {
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


    return ( 
            <div className="home">
            <h1>Homepage component</h1> 
            {data &&
                data.map((header)=>(
                    <div className="home-preview" key={header.id}>
                    <Link to={`/benchmarks/${header.id}`}>
                    <h3>{header.name}</h3>
                    <p>{header.description}</p>
                    </Link>
                    </div>
                ))
            }
            </div>
        
        );
}
 
export default HomepageComp;