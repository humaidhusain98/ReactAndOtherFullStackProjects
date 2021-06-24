import styled from 'styled-components';
import {useState,useEffect} from 'react';
import {Select , MenuItem} from '@material-ui/core';
import dict from '../abiAddress';

const Wallet = ({web3}) => {
   


    useEffect(()=>{

    },[]);

 


    const [address,setAddress]= useState('');
    const [error, setError] = useState(null);
    const [balance,setBalance]=useState(null);


    const [crypto, setCrypto] = useState('');

    const handleChange = (event) => {
    setCrypto(event.target.value);
    };
    

    const getData=()=>{
      switch(crypto) {
        case 1:
          {
          try{
            web3.eth.getBalance(address,(err,bal)=>{
                console.log(err);
                if(err) throw Error("Invalid Address");
                else
                setBalance(web3.utils.fromWei(bal,'ether')+" Ethereum");
                setError(null);
            })
            }
            catch(err)
            {
                setError("Invalid Address");
            }
          break;
          }//Case 1 Block!;
        case 2: 
            {
            const abi = [{"constant":true,"inputs":[],"name":"name","outputs":[{"name":"","type":"string"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_spender","type":"address"},{"name":"_value","type":"uint256"}],"name":"approve","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"totalSupply","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_from","type":"address"},{"name":"_to","type":"address"},{"name":"_amount","type":"uint256"}],"name":"transferFrom","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"getController","outputs":[{"name":"","type":"address"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[],"name":"decimals","outputs":[{"name":"","type":"uint8"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_newController","type":"address"}],"name":"changeController","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"_owner","type":"address"},{"name":"_amount","type":"uint256"}],"name":"mint","outputs":[{"name":"","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"_unlockTime","type":"uint256"}],"name":"changeUnlockTime","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"getUnlockTime","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"_owner","type":"address"}],"name":"balanceOf","outputs":[{"name":"balance","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_addr","type":"address"}],"name":"allowPrecirculation","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"symbol","outputs":[{"name":"","type":"string"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"_addr","type":"address"}],"name":"isPrecirculationAllowed","outputs":[{"name":"","type":"bool"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_to","type":"address"},{"name":"_amount","type":"uint256"}],"name":"transfer","outputs":[{"name":"success","type":"bool"}],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"_addr","type":"address"}],"name":"disallowPrecirculation","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[{"name":"_owner","type":"address"},{"name":"_spender","type":"address"}],"name":"allowance","outputs":[{"name":"remaining","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[],"name":"controller","outputs":[{"name":"","type":"address"}],"payable":false,"stateMutability":"view","type":"function"},{"inputs":[{"name":"_unlockTime","type":"uint256"}],"payable":false,"stateMutability":"nonpayable","type":"constructor"},{"anonymous":false,"inputs":[{"indexed":true,"name":"_from","type":"address"},{"indexed":true,"name":"_to","type":"address"},{"indexed":false,"name":"_value","type":"uint256"}],"name":"Transfer","type":"event"},{"anonymous":false,"inputs":[{"indexed":true,"name":"_owner","type":"address"},{"indexed":true,"name":"_spender","type":"address"},{"indexed":false,"name":"_value","type":"uint256"}],"name":"Approval","type":"event"}];
            const contractAddress = "0x3883f5e181fccaF8410FA61e12b59BAd963fb645";
            const contract = new web3.eth.Contract(abi,contractAddress);
            console.log(contract);
            try
            {
              contract.methods.balanceOf(address).call((err,result)=>
            {
              if(err) throw Error("Invalid Address");
              else{
              setBalance(web3.utils.fromWei(result,'ether')+" THETA");
              setError(null);}  
            });
            }
            catch(error)
            {
              setError("Invalid Address");
            }
            break;
            }//Case 2:
        case 3:
          {
            const abi = [{"constant":true,"inputs":[],"name":"name","outputs":[{"name":"","type":"string"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_upgradedAddress","type":"address"}],"name":"deprecate","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"_spender","type":"address"},{"name":"_value","type":"uint256"}],"name":"approve","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"deprecated","outputs":[{"name":"","type":"bool"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_evilUser","type":"address"}],"name":"addBlackList","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"totalSupply","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_from","type":"address"},{"name":"_to","type":"address"},{"name":"_value","type":"uint256"}],"name":"transferFrom","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"upgradedAddress","outputs":[{"name":"","type":"address"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"","type":"address"}],"name":"balances","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[],"name":"decimals","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[],"name":"maximumFee","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[],"name":"_totalSupply","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[],"name":"unpause","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[{"name":"_maker","type":"address"}],"name":"getBlackListStatus","outputs":[{"name":"","type":"bool"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"","type":"address"},{"name":"","type":"address"}],"name":"allowed","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[],"name":"paused","outputs":[{"name":"","type":"bool"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"who","type":"address"}],"name":"balanceOf","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[],"name":"pause","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"getOwner","outputs":[{"name":"","type":"address"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[],"name":"owner","outputs":[{"name":"","type":"address"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[],"name":"symbol","outputs":[{"name":"","type":"string"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_to","type":"address"},{"name":"_value","type":"uint256"}],"name":"transfer","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"newBasisPoints","type":"uint256"},{"name":"newMaxFee","type":"uint256"}],"name":"setParams","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"amount","type":"uint256"}],"name":"issue","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"amount","type":"uint256"}],"name":"redeem","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[{"name":"_owner","type":"address"},{"name":"_spender","type":"address"}],"name":"allowance","outputs":[{"name":"remaining","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[],"name":"basisPointsRate","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"","type":"address"}],"name":"isBlackListed","outputs":[{"name":"","type":"bool"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"_clearedUser","type":"address"}],"name":"removeBlackList","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"MAX_UINT","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"newOwner","type":"address"}],"name":"transferOwnership","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"_blackListedUser","type":"address"}],"name":"destroyBlackFunds","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"inputs":[{"name":"_initialSupply","type":"uint256"},{"name":"_name","type":"string"},{"name":"_symbol","type":"string"},{"name":"_decimals","type":"uint256"}],"payable":false,"stateMutability":"nonpayable","type":"constructor"},{"anonymous":false,"inputs":[{"indexed":false,"name":"amount","type":"uint256"}],"name":"Issue","type":"event"},{"anonymous":false,"inputs":[{"indexed":false,"name":"amount","type":"uint256"}],"name":"Redeem","type":"event"},{"anonymous":false,"inputs":[{"indexed":false,"name":"newAddress","type":"address"}],"name":"Deprecate","type":"event"},{"anonymous":false,"inputs":[{"indexed":false,"name":"feeBasisPoints","type":"uint256"},{"indexed":false,"name":"maxFee","type":"uint256"}],"name":"Params","type":"event"},{"anonymous":false,"inputs":[{"indexed":false,"name":"_blackListedUser","type":"address"},{"indexed":false,"name":"_balance","type":"uint256"}],"name":"DestroyedBlackFunds","type":"event"},{"anonymous":false,"inputs":[{"indexed":false,"name":"_user","type":"address"}],"name":"AddedBlackList","type":"event"},{"anonymous":false,"inputs":[{"indexed":false,"name":"_user","type":"address"}],"name":"RemovedBlackList","type":"event"},{"anonymous":false,"inputs":[{"indexed":true,"name":"owner","type":"address"},{"indexed":true,"name":"spender","type":"address"},{"indexed":false,"name":"value","type":"uint256"}],"name":"Approval","type":"event"},{"anonymous":false,"inputs":[{"indexed":true,"name":"from","type":"address"},{"indexed":true,"name":"to","type":"address"},{"indexed":false,"name":"value","type":"uint256"}],"name":"Transfer","type":"event"},{"anonymous":false,"inputs":[],"name":"Pause","type":"event"},{"anonymous":false,"inputs":[],"name":"Unpause","type":"event"}];
            const contractAddress = "0xdAC17F958D2ee523a2206206994597C13D831ec7";
            const contract = new web3.eth.Contract(abi,contractAddress);
            console.log(contract);
            try
            {
              contract.methods.balanceOf(address).call((err,result)=>
            {
              if(err) throw Error("Invalid Address");
              else{
              setBalance(web3.utils.fromWei(result,'mwei')+" USDT");
              setError(null);}  
            });
            }
            catch(error)
            {
              setError("Invalid Address");
            }
            break;
          }
        case 4:{
            const abi = dict.BNB.abi;
            const contractAddress = dict.BNB.address;
            try
            {
              const contract = new web3.eth.Contract(abi,contractAddress);
              console.log(contract);
              contract.methods.balanceOf(address).call((err,result)=>
            {
              if(err) throw Error("Invalid Address")
              else{
              setBalance(web3.utils.fromWei(result,'ether')+" BNB");
              setError(null);}
            });
            }
            catch(err)
            {
              setError("Invalid Address");
            }


          break;
        } 

        default:
            setError("Please Select Crypto Type");
      }

       
    }

    

    return (
        <>
        <Heading><h1>Crypto Wallet</h1></Heading>
        <InputDiv>
        <Input placeholder="Enter Wallet Address" value={address} onChange={event=>setAddress(event.target.value)}>
        </Input>
        <div style={{backgroundColor:"white",fontSize: "1.1em", border: "2px solid #f9f9f9",margin :"0.2em",padding : "0.6em",borderRadius:"3px"}}>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={crypto}
          onChange={handleChange}
        >
          <MenuItem value={1}>Ethereum</MenuItem>
          <MenuItem value={2}>Theta Token</MenuItem>
          <MenuItem value={3}>USDT</MenuItem>
          <MenuItem value={4}>Binance(BNB)</MenuItem>
        </Select>
        </div>
        <Button disabled={!address} onClick={getData}>Get Data</Button>
        </InputDiv>
        { error && <Balance>{error}</Balance>

        }
        {
          !error &&  balance && <Balance>{balance}</Balance>
        }
        </>
   );

}

const Input = styled.input.attrs(props => ({
    // we can define static props
    type: "text",
  
    // or we can define dynamic ones
    size: props.size || "1em",
  }))`
    color: black;
    font-size: 1.1em;
    min-width : 300px;
    max-width : 650px;
    display: flex;
    width : 1200px;
    border: 2px solid #f9f9f9;
    border-radius: 3px;

    @media (max-width : 1300px && min-width : 100px)
    {
      width : 1000px;
      
    }


    @media (max-width : 1100px)
    {
      width : 500px;
      
    }
    
    @media (max-width : 768px)
    {
      width : 150px;
      
    }

    @media (max-width : 500px)
    {
      width : 100px;
    }

    @media (max-width : 300px)
    {
      width : 70px;
    }
  
    /* here we use the dynamically computed prop */

    padding: 1em;
    margin : 1em;
  `;
  
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

const Button = styled.a`
background-color : rgb(0,0,0.6);
  padding : 8px 16px;
  text-transform : uppercase;
  letter-spacing :1.5px;
  border : 1px solid #f9f9f9;
  border-radius : 4px;
  transition : all .2s ease 0s;
  margin : 20px;
  height : 50px;

  &:hover {
      background-color: #f9f9f9;
      color : #000;
      border-color : transparent;
  }
`;

const InputDiv = styled.div`
  align-items : center;
  margin-right: auto;
  margin-left: 100px;
  display :flex;

  @media (max-width : 768px)
    {
      margin-left: 20px;
    }

    @media (max-width : 500px)
    {
      margin-left: 10px;
    }

`;

 const Balance = styled.div`
    align-items : center;
    margin-left: 30%;
    margin-top : 3%;
    padding : 10px;
    font-size: 2em;

 
 `;



export default Wallet;