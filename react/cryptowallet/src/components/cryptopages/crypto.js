import styled from 'styled-components';

const Crypto= ({name,abi,address,web3}) => {
    return ( 
    <>
     <Heading><h1>{name}:</h1></Heading>
    </> );
}
 
const Heading =styled.div`
font-size: 1.2em;
min-width : 300px;
max-width : 400px;
align-items : center;
margin-right: auto;
margin-left: auto;
display: flex;
width : 400px;
border-radius: 3px;

`; 
export default Crypto;