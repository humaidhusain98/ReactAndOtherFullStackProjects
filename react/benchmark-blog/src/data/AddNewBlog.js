import {Form,Row,Col,Button} from 'react-bootstrap';


const AddNewBlog = () => {
    const obj={code:{}};
    const handleSubmit = (e)=>{
      fetch('http://localhost:8000/benchmark',{
        method:'POST',
        headers:{"Content-Type":"application/json"},
        body: JSON.stringify(obj)
    }).then(()=>{
        console.log("New Blog Added!");

    }).catch((err)=>{console.log("There has been an err: "+err)});

    }//handleSubmit

    return (  
    <Form >
        
        <Col md>
        <h1>Benchmark Details:</h1>
          <Form.Group controlId="id">
            <Form.Label>Id:</Form.Label>
            <Form.Control type="number" onChange={(e)=>{obj.id=e.target.value;}} placeholder="Give Benchmark Id"></Form.Control>
          </Form.Group>
          <Form.Group controlId="titleBench">
            <Form.Label>Title:</Form.Label>
            <Form.Control type="text" onChange={(e)=>{obj.title=e.target.value;}} placeholder="Give Title of Benchmark"></Form.Control>
          </Form.Group>
          <Form.Group controlId="description">
            <Form.Label>Description:</Form.Label>
            <Form.Control type="text" onChange={(e)=>{obj.description=e.target.value;}} placeholder="Give Benchmark Description/Intro"></Form.Control>
          </Form.Group>
     
        <h3>Code:</h3>
          <Form.Group controlId="code-python">
            <Form.Label>Python:</Form.Label>
            <Form.Control type="text" onChange={(e)=>{obj.code.Python=e.target.value;console.log(obj);}} placeholder="Give Python Code Here"></Form.Control>
          </Form.Group>

          <Form.Group controlId="code-cplusplus">
            <Form.Label>C++:</Form.Label>
            <Form.Control type="text" placeholder="Give C++ Code Here"></Form.Control>
          </Form.Group>

          <Form.Group controlId="code-javascript">
            <Form.Label>JavaScript:</Form.Label>
            <Form.Control type="text" placeholder="Give JavaScript Code Here"></Form.Control>
          </Form.Group>

          <Form.Group controlId="code-java8">
            <Form.Label>Java:</Form.Label>
            <Form.Control type="text" placeholder="Give Java Code Here"></Form.Control>
          </Form.Group>
         
         <h3>Test Bench Specifications</h3>
         <Form.Group controlId="test-os">
            <Form.Label>Operating System:</Form.Label>
            <Form.Control type="text" placeholder="Give Os Specifications"></Form.Control>
          </Form.Group>

         <Form.Group controlId="test-processor">
            <Form.Label>Processor:</Form.Label>
            <Form.Control type="text" placeholder="Give Processor Name Here"></Form.Control>
          </Form.Group>

          <Form.Group controlId="test-ram">
            <Form.Label>Ram:</Form.Label>
            <Form.Control type="text" placeholder="Enter Ram Specifications"></Form.Control>
          </Form.Group>

          <Form.Group controlId="test-hdd">
            <Form.Label>Hard Disk:</Form.Label>
            <Form.Control type="text" placeholder="HardDisk Specifications"></Form.Control>
          </Form.Group>

          <h3>Test Case 1</h3>
         <Form.Group controlId="testcase1-name">
            <Form.Label>Name:</Form.Label>
            <Form.Control type="text" placeholder="Title of the testCase1!"></Form.Control>
          </Form.Group>

         <Form.Group controlId="testcase1-results">
            <Form.Label>Results:</Form.Label>
            <Form.Control type="text" placeholder="Elaborates the result of the Tests"></Form.Control>
          </Form.Group>

          <Form.Group controlId="testcase1-unit">
            <Form.Label>Unit:</Form.Label>
            <Form.Control type="text" placeholder="Enter Unit of time measurement"></Form.Control>
          </Form.Group>

          <Form.Group controlId="testcase1-timing">
            <Form.Label>Python:</Form.Label>
            <Form.Control type="text" placeholder="Python Timing"></Form.Control>
          </Form.Group>

         

          </Col>
          <Button variant="secondary" onClick={handleSubmit} >Add Data</Button>

    </Form>
        );
}
 
export default AddNewBlog;