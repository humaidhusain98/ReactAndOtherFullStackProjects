import styled from 'styled-components';
import {useState,useEffect} from 'react';
import {Select , MenuItem} from '@material-ui/core';






const Create = ({web3}) => {

    const [publickey,setPubKey]= useState(null);
    const [privateKey,setPriKey]= useState(null);
    
    const [captcha,setCaptcha]=useState(false);

    const [crypto, setCrypto] = useState('');
    const [error, setError] = useState(null);
    const [address,setAddress]= useState('');



    const handleChange = (event) => {
    setCrypto(event.target.value);
    };

    const getData=async()=>{
        switch(crypto){
            case 1:
                {
                    const acc = await web3.eth.accounts.create();
                    setPubKey(acc.address);
                    setPriKey(acc.privateKey);
                    setError(null);
                    break;
                }
            case 2:
                {
                    setError(null);
                        break;
                }
            default :{
                setPubKey(null);
                setPriKey(null);
                setError("Please Select Crypto Type");
            }


        }
    }

  
   

    return (
        <Container>
        {/* <div style={{width: "200px",backgroundColor:"white",fontSize: "1.1em", border: "2px solid #f9f9f9",margin :"0.2em",padding : "0.6em",borderRadius:"3px"}}> */}
        <InnerContainer>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={crypto}
          onChange={handleChange}
        >
          <MenuItem value={1}>Ethereum</MenuItem>
          <MenuItem value={2}>Theta Token</MenuItem>
          <MenuItem value={3}>VeChain</MenuItem>
          <MenuItem value={4}>Binance(BNB)</MenuItem>
        </Select>
        </InnerContainer>
        <CaptchaContainer>
            {
                error && <><h3>{error}</h3>
                
                </>
            }
            {
                !error && privateKey && <><h3>Public Key :{publickey}</h3>
                            <h3>Private Key :{privateKey}</h3>
                </>
            }
        
        </CaptchaContainer>
        <ButtonContainer>
        <Button disabled={!address} onClick={getData}>Create Wallet</Button>
        </ButtonContainer>        
        </Container>

      );
}

const Button = styled.a`
background-color : rgb(0,0,0.6);
  padding : 8px 16px;
  text-transform : uppercase;
  letter-spacing :1.5px;
  border : 1px solid #f9f9f9;
  border-radius : 4px;
  transition : all .2s ease 0s;
  height : 50px;

  &:hover {
      background-color: #f9f9f9;
      color : #000;
      border-color : transparent;
  }
`;

const Container = styled.div`
  margin-top: 10%;
  margin-bottom : 5%;
  height : 650px;
  margin-left : 20%;
  margin-right : 20%;
  border : 2px solid #f9f9f9;
  padding : 10px;
`; 

const InnerContainer = styled.div`
  background-color:white;
  margin-top: 10%;
  margin-left : 15%;
  margin-right : 15%;
  margin-bottom : 10%;
  border-radius : 4px;
  border : 2px solid #f9f9f9;
`;

const ButtonContainer = styled.div`
    margin-left : 38%;
    margin-bottom : 20%;

`;

const CaptchaContainer = styled.div`
  margin-left :15%;
`;

export default Create;