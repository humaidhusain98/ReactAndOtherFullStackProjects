import {useEffect, useState} from 'react';
import { useHistory, useParams } from "react-router-dom";
import BarChart from './chart';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import {Button,Alert,Breadcrumb,Card,Form,Container,Row,Col} from 'react-bootstrap';
import SyntaxHighlighter from 'react-syntax-highlighter';
import { docco } from 'react-syntax-highlighter/dist/esm/styles/hljs';
import { dark } from 'react-syntax-highlighter/dist/esm/styles/prism';
import NavbarComp from './NavbarComp';

const BlogRender = () => {

    const {id} =useParams();
    const [blog,setBlog] = useState(null);
    const [error,setError]= useState(null);
    const [displayLang,setLang]= useState(null);
    const [lang,setCode] = useState("java");
    //Chart.js Script

   
    
    const handleClick=(val)=>{
        if(val==='java')
            {
                setLang(blog.code.Java);
                setCode(val);
            }
        else if(val==='c++')
            {
                setLang(blog.code.Cplusplus);
                setCode(val);
            }
        else if(val==='javascript')
            {
                setLang(blog.code.Javascript);
                setCode(val);
            }
        else
            {
                setLang(blog.code.Python);
                setCode(val);
            }
    
    }


    useEffect(()=>{
        fetch('http://localhost:8000/benchmark/'+id).then((res)=>{
            if(!res.ok)
            {throw Error('Could not fetch the data for that resource');}
            return res.json();
        }).then((data)=>
        {
            setBlog(data);
            setLang(data.code.Java);

        }).catch((e)=>{
            setError(e);
            console.log("There has been an error"+e);
        });
    },[]);

    return (
                <div className="blog-render">
                <Container fluid className="main-container">
                { error && <Alert variant='light'>Oops!Server could not be Connected</Alert>}
                {   blog && 
                <Container style={{backgroundColor:'#C0C0C0',minBlockSize:"125vh"}}>
                    <Row style={{minHeight:"35px"}}></Row>
                            
                            <Container>
                            <h1 id="title">{blog.title}</h1>
                            
                            </Container>
                            <Container className="firstcontainer">
                            <Card >
                                <Card.Text className="description">
                                {blog.description}
                                </Card.Text>
                            </Card>
                            </Container>
                          
                   
                            <Container className="testbenchspecs">
                                <h3 id="blog-testbench">Test Bench Specifications:</h3> 
                                <hr ></hr>
                                <Card>
                                <Card.Body>
                               
                                
                                <ul id="blog-testbench-list">
                                    <li>Operating System :{blog.testBench.os}</li>
                                    <li>Processor :{blog.testBench.processor}</li>
                                    <li>Ram :{blog.testBench.ram}</li>    
                                    <li>Hard Disk :{blog.testBench.hardDisk}</li>
                                </ul>
                                </Card.Body>
                                </Card>
                            </Container>
                            <Container className="breadcrumb-parent">
                            <Row >
                                <Col onClick={()=>{handleClick("java")}}><p id="breadcrumb-item">Java</p></Col>
                                <Col onClick={()=>{handleClick("javascript")}}><p id="breadcrumb-item">JavaScript</p></Col>
                                <Col onClick={()=>{handleClick("c++")}}><p id="breadcrumb-item">C++</p></Col>
                                <Col onClick={()=>{handleClick("python")}}><p id="breadcrumb-item">Python</p></Col>
                            </Row>
                            {
                                displayLang &&
                            <SyntaxHighlighter language={lang} style={docco}>
                              {displayLang}
                            </SyntaxHighlighter>
                            }
                                
                            </Container>

                            <Container className="test1container">
                                <div className="titletest1container">
                                     {blog.testCase1.name}
                                     <hr></hr>
                                </div>
                                <Card >
                                    
                                        <Card.Text className="test1card-results">
                                            {blog.testCase1.results} 
                                        </Card.Text>
                                        <hr></hr>
                                        <div id="barChart" >
                                        <BarChart code={blog.testCase1.timings} ></BarChart>
                                        </div>
                                    
                                </Card>
                            </Container>
                            <Container className="test2container">
                                <div className="titletest2container">
                                     {blog.testCase2.name}
                                     <hr></hr>
                                </div>
                                <Card >
                                    
                                        <Card.Text className="test2card-results">
                                            {blog.testCase2.results} 
                                        </Card.Text>
                                        <hr></hr>
                                        <div id="barChart" >
                                        <BarChart code={blog.testCase2.timings} ></BarChart>
                                        </div>
                                    
                                </Card>
                            </Container>
                            <Container className="test3container">
                                <div className="titletest3container">
                                     {blog.testCase3.name}
                                     <hr></hr>
                                </div>
                                <Card >
                                    
                                        <Card.Text className="test3card-results">
                                            {blog.testCase3.results} 
                                        </Card.Text>
                                        <hr></hr>
                                        <div id="barChart" >
                                        <BarChart code={blog.testCase3.timings} ></BarChart>
                                        </div>
                                    
                                </Card>
                            </Container>
                            <Container className="finalResults">
                            <div className="title-finalResults">
                                     Conclusion
                                     <hr></hr>
                            </div>
                            <Card >
                                    
                                    <Card.Text className="body-finalResults">
                                        {blog.finalResults} 
                                    </Card.Text>
                                    
                                
                            </Card>
                            <hr></hr>
                            </Container>

                          



                          

                   
                   

                </Container>


                }
                </Container>
                </div>

            
        );
}
 
export default BlogRender;