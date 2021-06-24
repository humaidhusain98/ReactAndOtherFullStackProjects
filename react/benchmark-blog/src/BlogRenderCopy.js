import {useEffect, useState} from 'react';
import BarChart from './chart';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import {Button,Alert,Breadcrumb,Card,Form,Container,Row,Col} from 'react-bootstrap';

const BlogRender = () => {

    const [blog,setBlog] = useState(null);
    const [error,setError]= useState(null);
    const [displayLang,setLang]= useState(null);
    //Chart.js Script

   
    
    const handleClick=(val)=>{
        if(val==='java')
            {
                setLang(blog.code.Java);
            }
        else if(val==='c++')
            {
                setLang(blog.code.Cplusplus);
            }
        else if(val==='javascript')
            {
                setLang(blog.code.Javascript);
            }
        else
            {
                setLang(blog.code.Python);
            }
    
        console.log("Component Clicked  "  +val)
    }


    useEffect(()=>{
        fetch('http://localhost:8000/benchmark/1').then((res)=>{
            if(!res.ok)
            {throw Error('Could not fetch the data for that resource');}
            return res.json();
        }).then((data)=>
        {
            setBlog(data);
            setLang(data.code.Java);
            console.log(data.code.Java);
        }).catch((e)=>{
            setError(e);
            console.log("There has been an error"+e);
        });
    },[]);

    return (


            <div className="blog-render"> 
                { error && <div className="blog-error">{error.message}
                            </div>
                    
                } 
                { blog && 
                <div className="blog-element" style={{textAlign:'left'}}>
                        <h1 id="blog-title">{blog.title}</h1>
                        <p id="blog-paragraph">{blog.description}</p>
                        <h3 id="blog-testbench">Test Bench Specifications</h3>  
                        <ul id="blog-testbench-list">
                            <li>Operating System :{blog.testBench.os}</li>
                            <li>Processor :{blog.testBench.processor}</li>
                            <li>Ram :{blog.testBench.ram}</li>    
                            <li>Hard Disk :{blog.testBench.hardDisk}</li>
                        </ul>
                        <div id="code-container">
                            <div className="row">
                                <div className="column" onClick={()=>{
                                    handleClick("java")
                                }}>Java</div>
                                <div className="column" onClick={()=>{
                                     handleClick("c++")
                                }}>C++</div>
                                <div className="column" onClick={()=>{
                                     handleClick("python")
                                }}>Python</div>
                                <div className="column" onClick={()=>{
                                     handleClick("javascript")
                                }}>JavaScript</div>
                            </div>
                           
                        </div>   
                       

                </div>

               

                }

                
            {
             displayLang && <div id="displayFunction"><br/><p >{displayLang}</p></div>

            }

            {   blog &&
            <div id="testCase1">
                <h3>{blog.testCase1.name}</h3>
                <div id="barChart" >
                <BarChart code={blog.testCase1.timings} ></BarChart>
                </div>
            </div>
            }
                
             </div>

            
        );
}
 
export default BlogRender;

{/* <Card>
<Card.Title>
    <Row>
        <Col>
            Java
        </Col>
            
        <Col>
            JavaScript
        </Col>

        <Col>
            C++

        </Col>

        <Col>
            Python
        </Col>
        
    </Row>
</Card.Title> */}


// <Card className="card-container">
// <Card.Body >
// {displayLang &&<div>{(displayLang.split("\n")).map((newlineText,key)=>(
//        <div key={key}>{newlineText}</div>
//    ))}</div>
// }
// </Card.Body>
// </Card>