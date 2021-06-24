import './App.css';
import {useState} from 'react';
import Button from '@material-ui/core/Button';
import ButtonGroup from '@material-ui/core/ButtonGroup';
import Typography from '@material-ui/core/Typography';
import OutlinedInput from '@material-ui/core/OutlinedInput';
import { makeStyles } from '@material-ui/core/styles';
import Body from './components/body';
import HeadData from './components/headData';
import Slider from '@material-ui/core/Slider';
import Grid from '@material-ui/core/Grid';
import CircularProgress from '@material-ui/core/CircularProgress';
import Countdown from 'react-countdown';

const Web3 = require('web3');
const {testAbi:contractAbi ,testAddress:contractAddress } = require('./erc20data/contractAbi');
const {testAbi:leadAbi ,testAddress:leadAddress } = require('./erc20data/leadAbi');
const {testAbi:usdAbi ,testAddress:usdAddress } = require('./erc20data/usdtAbi');
const {testAbi:v1stakeAbi ,testAddress:v1stakeAddress } = require('./erc20data/v1stake');


let loggedIn=false;

if(window.ethereum){
   window.ethereum.on("accountsChanged",()=>{
    if(loggedIn){
        window.location.reload();
    }

  })
}


const handleClickNew = async(accounts)=>{

  const ethereum= window.ethereum;
  if(ethereum){


  const allinfo = {
    stakes:null,
    pBonus:null,
    aBonus:null,
    basicROI:null,
    secondROI:null,
    tertROI:null,
    masterROI:null,
    lpToken:null,
    userLiq:null,
    locked:null,
    leadBal:null,
    usdtBal:null,
    rate:null
  }

 
  const web3 = new Web3(ethereum);
 
  // accounts = await ethereum.request({ method: 'eth_requestAccounts' });


  //Staking v1 Contract
  var v1stakecontract = new web3.eth.Contract(v1stakeAbi,v1stakeAddress);
  try
  {
      await v1stakecontract.methods.stakes(accounts[0]).call((err,result)=>{
          if (!err) 
          {
             allinfo.stakes=result;
          }
      })
  }
    catch(error)
  {
    console.log(error);
    console.log("Error on stakes contract");
  }



  //Contract methods -rate 
  var contract = new web3.eth.Contract(contractAbi,contractAddress);
  try
  {
      await contract.methods.rate(1,usdAddress,leadAddress).call((err,result)=>{
          if (!err) 
          {

             allinfo.rate=result;
          }
      })
  }
    catch(error)
  {
    console.log("Error on contract execution");
  }

  //Contract Number method No 6

  try
  {
      await contract.methods.getUserRelease(accounts[0]).call((err,result)=>{
          if (!err) 
          {

              allinfo.time = result;
              allinfo.date= new Date();
       
          }
      })
  }
    catch(error)
  {
    console.log("Error on contract execution method 6");
  }

   //Contract Number method No 7
   try
   {
       await contract.methods.getTotalLP().call((err,result)=>{
           if (!err) 
           {

              allinfo.locked=web3.utils.fromWei(web3.utils.toBN(result),'ether');
           }
       })
   }
     catch(error)
   {
     console.log("Error on contract execution method 7");
   }

   ////Contract Number method No 10 Basic
   try
   {
       await contract.methods.basicROI().call((err,result)=>{
           if (!err) 
           {
  
              allinfo.basicROI=result;
           }
       })
   }
     catch(error)
   {
     console.log("Error on contract execution method 10 Basic");
   }

    //Contract Number method No 10 Secondary
    try
    {
        await contract.methods.secondaryROI().call((err,result)=>{
            if (!err) 
            {

               allinfo.secondROI=result;
            }
        })
    }
      catch(error)
    {
      console.log("Error on contract execution method 10 Secondary");
    }

     //Contract Number method No 10 Tertiary
     try
     {
         await contract.methods.tertiaryROI().call((err,result)=>{
             if (!err) 
             {

                allinfo.tertROI=result;
             }
         })
     }
       catch(error)
     {
       console.log("Error on contract execution method 10 Tertiary");
     }

     //Contract Number method No 10 master
     try
     {
         await contract.methods.masterROI().call((err,result)=>{
             if (!err) 
             {
   
                allinfo.masterROI=result;
             }
         })
     }
       catch(error)
     {
       console.log("Error on contract execution method 10 master");
     }

     //Contract method number 15 getLPToken
     try
     {
         await contract.methods.getUserLPToken(accounts[0]).call((err,result)=>{
             if (!err) 
             {
 
                allinfo.lpToken=web3.utils.fromWei(result,'ether');
             }
         })
     }
       catch(error)
     {
       console.log("Error on contract execution method 15 get user lp token");
     }


      //Contract method number 16 getLPToken
      try
      {
          await contract.methods.getUserLiquidity(accounts[0]).call((err,result)=>{
              if (!err) 
              {
                
                 
                  result.USDT_amount=web3.utils.fromWei(web3.utils.toBN(result.USDT_amount),'mwei');
                  result.LEAD_amount=web3.utils.fromWei(web3.utils.toBN(result.LEAD_amount),'ether');
                  allinfo.userLiq=result;
                //  allinfo.userLiq=web3.utils.fromWei(web3.utils.toBN(result),'ether');
              }
          })
      }
        catch(error)
      {
        console.log("Error on contract execution method 16 get user liquidity based on usdt and lead");
      }

      //Contract method number 20 get Pending Bonus
      try
      {
        await contract.methods.getUserPendingBonus(accounts[0]).call((err,result)=>{
              if (!err) 
              {
      
                 allinfo.pBonus=web3.utils.fromWei(web3.utils.toBN(result),'ether');
              }
          })
      }
        catch(error)
      {
        console.log("Error on contract execution method 20 get Pending Bonus");
      }


       //Contract method number 21 get Pending Bonus
       try
       {
          await contract.methods.getUserAvailableBonus(accounts[0]).call((err,result)=>{
               if (!err) 
               {
       
                  allinfo.aBonus=web3.utils.fromWei(web3.utils.toBN(result),'ether');
               }
           })
       }
         catch(error)
       {
         console.log("Error on contract execution method 20 get Pending Bonus");
       }

 

  //lead method
  var leadContract = new web3.eth.Contract(leadAbi,leadAddress);
  try
  {
      await leadContract.methods.balanceOf(accounts[0]).call((err,result)=>{
          if (!err) 
          {
            
            allinfo.leadBal=web3.utils.fromWei(result,'ether');
            
          }
      })
  
  }
  catch(error)
  {
    console.log("Error Occured taking lead balance");
  }



   //usdt method
   var usdtContract = new web3.eth.Contract(usdAbi,usdAddress);
   try
   {
      await usdtContract.methods.balanceOf(accounts[0]).call((err,result)=>{
           if (!err) 
           {
            
             allinfo.usdtBal=web3.utils.fromWei(result,'mwei');
           }
       })
   
   }
   catch(error)
   {
     console.log("Error Occured usdt balance");
   }


return [allinfo,accounts[0]];

}
else{
  console.log("Metamask is not installed");
  return null;
}

}// handleClickNew




const useStyles = makeStyles({
  input: {
    color:"white",
    backgroundColor:"transparent",
    border: "2px solid white",
    borderRadius: "5px",
    padding : "5px",
    margin : "10px",
    height : "45px",
    width : "350px"
  },
  btn : {
    height : "45px",
    width : "150px",  padding : "5px",
    margin : "10px",
    "&:disabled":{
      color:"white"
    }
  },
  btnLong : {
    height : "55px",
    width : "250px",  padding : "5px",
    margin : "10px",
    "&:disabled":{
      color:"white"
    }
  
  },
  inputLong: {
    color:"white",
    backgroundColor:"transparent",
    border: "2px solid white",
    borderRadius: "5px",
    padding : "5px",
    margin : "10px",
    height : "55px",
    width : "600px"
  },
  viewContract: {
    
    borderRadius: "5px",
    padding : "5px",
    margin : "10px",
    height : "40px",
    width : "300px"
    

  },
  slider : {
    width:"600px",
    marginLeft : "10%",
    color : "white"
  }


});


function App() {


  const classes = useStyles();
  const ethereum = window.ethereum;
 


  //required
  const [btn,setBtn]= useState(1);
  const [pending,setPending]=useState(false);
  const [account,setAccount]= useState(null);
  const [days,setDays] =  useState(null);
  const [approveAmt,setAmt]=useState(null);
  const [fullData,setData]=useState(null);
  const [install,setInstall]=useState(null);
  const [timer,setTimer]= useState(null);
  const [lastDate,setDate]= useState(null);
    //slider
  const [slider,setSlider] =  useState(0);
  //


  



  const handleUnlock = ()=>{
    if(slider!==0)
    {
      const web3 = new Web3(ethereum);

      var contract = new web3.eth.Contract(contractAbi,contractAddress);

      try
      {

          const amt = web3.utils.toBN((fullData.lpToken*slider*(10**16)));
          contract.methods.withdrawLiquidity(amt).send({from:account},(err,txHash)=>{
            if(!err)
              console.log("Withdraw Liquidity Transaction Hash : "+txHash);
          });
      }
        catch(error)
      {
        console.log(error);
        console.log("Error on contract execution");
      }

    }
    //Contract method 18
  

  }



  

  //method no 22
  const handleClaim = () =>{
  
    const web3 = new Web3(ethereum);

    var contract = new web3.eth.Contract(contractAbi,contractAddress);
    try
    {
        contract.methods.withdrawUserBonus().send({from:account},(err,txHash)=>{
          if(!err)
            console.log("Withdraw User Bonus Transaction Hash : "+txHash);
        });
    }
      catch(error)
    {
      console.log("Error on contract Withdraw User Bonus execution");
    }

  }


  const relockLiquidity = () =>{
    if(days)
    {
      console.log(days);
      const web3 = new Web3(ethereum);

      var contract = new web3.eth.Contract(contractAbi,contractAddress);
    try
    {
        contract.methods.relockLiquidity(days).send({from:account},(err,txHash)=>{
          if(!err)
            console.log("Relock Liquidity Transaction Hash : "+txHash);
        });
    }
      catch(error)
    {
      console.log("Error on contract execution");
    }
    }
    
  }


  const handleSlider = (event, newValue) => {
    setSlider(newValue);
  };


  const handleClickBridge = async()=>{
    setPending(true);
    if(window.ethereum)
    {
      const accounts = await window.ethereum.request({ method: 'eth_requestAccounts' });
      setAccount(accounts[0]);
      handleClickNew(accounts).then((result)=>{ 
        if(result){
          setData(result[0]);
          setTimer(result[0].time);
          setDate(result[0].date);
          setTimeout(function(){setPending(false)},1000);
          }
          else{
            setPending(false);
            setInstall("Please Install Metamask!,Metamask not detected");
          }
          loggedIn=true;
      });
    }
    else
    {
      setPending(false);
      setInstall("Please Install Metamask!,Metamask not detected");
    }
 
  
   
   
  }

 


  



  




  const handleApprove =async()=>{
    const web3 = new Web3(window.ethereum);
    if(approveAmt && days)
    {
      var contract = await new web3.eth.Contract(contractAbi,contractAddress);

      var leadContract = new web3.eth.Contract(leadAbi,leadAddress);
      var usdtContract = new web3.eth.Contract(usdAbi,usdAddress);
      var leadAllowance; var usdtAllowance
      try{
         await leadContract.methods.allowance(account,contractAddress).call((err,result)=>{
            if(!err)
              leadAllowance=result;
          });
      }
      catch(e)
      {
        console.log("Error on Lead allowance")
      }

      try{
        await usdtContract.methods.allowance(account,contractAddress).call((err,result)=>{
          if(!err)
            usdtAllowance=result;
        });
    }
    catch(e)
    {
      console.log("Error on Lead allowance")
    }




      const supply = await web3.utils.toBN('1000000000000000000000000000');

      
        if(leadAllowance==="0" && usdtAllowance==="0")
        { 
          console.log("lead Allowance triggered");
          await leadContract.methods.approve(contractAddress,supply).send({from:account},(err,txHash)=>{
            if(!err)
              console.log("Lead Approval Transaction Hash : "+txHash);
          });

          console.log("usdt Allowance triggered");
        await usdtContract.methods.approve(contractAddress,supply).send({from:account},(err,txHash)=>{
          if(!err)
            console.log("Usdt Approval Transaction Hash : "+txHash);
        });


      const amtBN = await web3.utils.toBN(approveAmt);

      await contract.methods.addLiquidity(amtBN,days).send({from:account
        },(err,txHash)=>{
        console.log(err);
        console.log("Contract Transaction Hash : "+txHash);
      });



        }
      if(leadAllowance!=="0" && usdtAllowance==="0" )
      {
        console.log("usdt Allowance triggered");
        await usdtContract.methods.approve(contractAddress,supply).send({from:account},(err,txHash)=>{
          if(!err)
            console.log("Usdt Approval Transaction Hash : "+txHash);
        });


      const amtBN = await web3.utils.toBN(approveAmt);

      await contract.methods.addLiquidity(amtBN,days).send({from:account
        },(err,txHash)=>{
        console.log(err);
        console.log("Contract Transaction Hash : "+txHash);
      });
      }

      if(leadAllowance==="0" && usdtAllowance!=="0" )
      {
        console.log("lead Allowance triggered");
          await leadContract.methods.approve(contractAddress,supply).send({from:account},(err,txHash)=>{
            if(!err)
              console.log("Lead Approval Transaction Hash : "+txHash);
          });


      const amtBN = await web3.utils.toBN(approveAmt);

      await contract.methods.addLiquidity(amtBN,days).send({from:account
        },(err,txHash)=>{
        console.log(err);
        console.log("Contract Transaction Hash : "+txHash);
      });
      }
      console.log(leadAllowance, usdtAllowance);
      if(leadAllowance!=="0" && usdtAllowance!=="0" )
      {
      const amtBN = await web3.utils.toBN(approveAmt);

      await contract.methods.addLiquidity(amtBN,days).send({from:account
        },(err,txHash)=>{
        console.log(err);
        console.log("Contract Transaction Hash : "+txHash);
      });
      }
  
      
    

     
 
     
      //total supply of leadContract = 1000000000000000000000000000
      //total supply of usdtcontract = 1000000000000000000000000000


    }

  }
  
  const handleTimeout = ()=>{
      setTimer("0");
  }


  return (
    <div className="App">
      {
          !pending && <header className="App-header">

          {
            !fullData && !install && <Button color="primary" id="connect" variant="contained"  onClick={handleClickBridge}>Connect Metamask </Button>
          }
          {
            !fullData && install && <Typography>{install}</Typography>
          }

          {
            fullData  && (<div id="button-group">
          <ButtonGroup size="large" variant="contained" color="primary" aria-label="contained primary button group">
            <Button color={btn === 1 ?"secondary":"primary"} onClick={()=>{if(timer>0){const diff = (new Date().getTime()-lastDate.getTime())/1000;setTimer(timer-diff);setDate(new Date())}else{setTimer("0")};setBtn(1)}}>LOCK LIQUIDITY</Button>
            <Button color={btn === 2 ?"secondary":"primary"} onClick={()=>{if(timer>0){const diff = (new Date().getTime()-lastDate.getTime())/1000;setTimer(timer-diff);setDate(new Date())}else{setTimer("0")};setBtn(2);}}>RELOCK LIQUIDITY</Button>
            <Button color={btn === 3 ?"secondary":"primary"} onClick={()=>{if(timer>0){const diff = (new Date().getTime()-lastDate.getTime())/1000;setTimer(timer-diff);setDate(new Date())}else{setTimer("0")};setBtn(3)}}>UNLOCK LIQUIDITY</Button>
            <Button color={btn === 4 ?"secondary":"primary"} onClick={()=>{if(timer>0){const diff = (new Date().getTime()-lastDate.getTime())/1000;setTimer(timer-diff);setDate(new Date())}else{setTimer("0")};setBtn(4)}}>BONUS</Button>
          </ButtonGroup>
          </div>
            )
          }
             {
            fullData?.usdtBal && fullData?.leadBal && btn===1 && (
              <>
            <div id="wallet-balance">
              <Typography variant="h5" gutterBottom>
                Wallet Balance {(parseFloat(fullData.usdtBal)).toFixed(3)} USDT | {parseFloat((fullData.leadBal)).toFixed(3)} LEAD
               </Typography>
            </div>
            <div id ="input-tags">
              <OutlinedInput className={classes.input} placeholder ="Enter Lead Amount" onChange = {(e)=>setAmt(e.target.value*(10**18))}/>
              <OutlinedInput className={classes.input} placeholder ="Enter Days to Lock " onChange = {(e)=>setDays(parseInt(e.target.value*86400))}/>
              <Button  className={classes.btn} variant="contained"  disabled={(timer!=="0")} color="primary" onClick={handleApprove}>Lock</Button>
            </div>
            <div id="equivalent">
              <Typography variant="subtitle2">
                    USDT Equivalent - {approveAmt?((approveAmt)/((10**18)*(fullData.rate))):0}
              </Typography>
            </div>
            </>
              )
          }
          {
            fullData && btn===2 &&  (<>
            {
              fullData.lpToken && fullData.userLiq && <HeadData lpToken={fullData.lpToken} leadamt={fullData.userLiq.LEAD_amount} usdtamt={fullData.userLiq.USDT_amount}/>
            }
       
            
            <div id ="input-tags">
              <OutlinedInput className={classes.inputLong} placeholder ="Enter Days to Lock " onChange = {(e)=>setDays(parseInt(e.target.value*86400))}/>
              <Button  className={classes.btnLong} variant="contained" disabled={(timer==="0" && fullData.lpToken!=="0")?false:true} color="primary" onClick={relockLiquidity}>Relock</Button>
            </div>
            </>)
          }
          {
            fullData && btn===3 && <> 
           {
              fullData.lpToken && fullData.userLiq && <HeadData lpToken={fullData.lpToken} leadamt={fullData.userLiq.LEAD_amount} usdtamt={fullData.userLiq.USDT_amount}/>
            }
             <div id="slider-unlock">
               <div className={classes.slider}>
               <Typography variant="subtitle2">
                   {(slider*fullData.lpToken/100).toFixed(3)} Uni V2
                  </Typography>
               <Grid container spacing={2}>
                <Grid item>
                <Typography variant="subtitle2">
                   0
                  </Typography>
                 </Grid>
                 <Grid item xs>
                   <Slider value={slider} disabled={(timer==="0" && fullData.lpToken!=="0")?false:true} onChange={handleSlider} aria-labelledby="continuous-slider" />
                 </Grid>
                <Grid item>
                 <Typography variant="subtitle2">
                    {parseFloat(fullData.lpToken).toFixed(3)}
                  </Typography>
                 </Grid>
                </Grid>
               </div>
               <Button  className={classes.btnLong} variant="contained" color="primary" disabled={(timer==="0" && fullData.lpToken!=="0")?false:true} onClick={handleUnlock}>Unlock</Button>
             </div>
            </>
          }
          {
            fullData && btn===4 && timer && fullData.aBonus && fullData.pBonus && <>
            <div id="bonus-lead">
               <Typography variant="h3" gutterBottom>
                 Bonus LEAD 
               </Typography>
               <Typography variant="h5" gutterBottom>
                 Locked-{parseFloat(fullData.pBonus).toFixed(3)}
               </Typography>
               <Typography variant="h5" gutterBottom>
                 Released-{parseFloat(fullData.aBonus).toFixed(3)} 
               </Typography>
            </div>
            <div id="bonus-div">
            <div id="release-period">
            <Typography variant="h6" gutterBottom>
                 Release Period 
             </Typography>
             <Typography variant="h4" gutterBottom>
             <Countdown date={Date.now() + (timer*1000)} onComplete={()=>alert("Completed")} />
             </Typography>
             </div>
             <Button className={classes.viewContract} onClick={handleClaim} variant="contained" color="primary">Claim</Button>
             </div>
            </>
          }
          {
            fullData && btn!==4 && fullData.rate && fullData.stakes && timer && fullData.locked && fullData.basicROI && fullData.secondROI && fullData.tertROI && fullData.masterROI &&
            <Body rate={fullData.rate} stakes={fullData.stakes} onChange={handleTimeout} release={timer} locked={fullData.locked} basic={fullData.basicROI} second={fullData.secondROI} tert={fullData.tertROI} master={fullData.masterROI} />
          }
          {
            fullData &&  <div id="view-contract">
            <Button className={classes.viewContract} onClick={()=>{window.open("https://ropsten.etherscan.io/address/0xC7baa945DD38D7385C1Be6B0189ef47f1e8208e7");}} variant="contained" color="primary">View Contract</Button>
          </div>
          }
         
          {/* {
            account && <div>Your Account Address = {account}</div>
          }
          <br/>
          {
            balance && <div>Ethereum Balance = {balance}</div>
          }
          <br/>
          {
            leadBal && <div>Lead Balance = {leadBal}</div>
          }
           <br/>
          {
            usdtBal && <div>Usdt Balance = {usdtBal}</div>
          }
           <br/>
          {
            gasPrice && <div>The Current gas price is = {gasPrice}</div>
          }
          <br/>
          {
            rate && <div>Rate: 1 USDT = {rate} LEAD</div>
          } */}
          {}
       
        </header>
      }

      {
        pending && <header id="App-header-loading"><div id="spinner"><div id="spinner-container"><CircularProgress size={90} /></div></div></header>
      }
     
    </div>
  );
}

export default App;


//Lead Approval Transaction Hash : 0xdc856e369bf77017ca93e14625b8a81bf0eee1b3bb68c8bedb6bfe6ad4214aee
// Usdt Approval Transaction Hash : 0xd4e58dde2b8361595bad56dc8c10ca8de9861adf78c2cf56d08efd98c584e6e5
//Contract Transaction Hash : 0xc4d51b1cb6eac4716fbfab6d89bab2821c0ff669df772d5d4958da75afa25917


//0xd12b817a0e4a700086706b72257cc336ff7b60a8eb972ac09843a278ae2d9aba Lead approval



// const handleClick = async()=>{
//   console.log("Handle Click triggered!!");

// if(ethereum){

//   setPending(true);
//   const allinfo = {
//     stakes:null,
//     pBonus:null,
//     aBonus:null,
//     basicROI:null,
//     secondROI:null,
//     tertROI:null,
//     masterROI:null,
//     lpToken:null,
//     userLiq:null,
//     locked:null,
//     leadBal:null,
//     usdtBal:null,
//     rate:null
//   }

 
//   const web3 = new Web3(ethereum);
 
//   const accounts = await ethereum.request({ method: 'eth_requestAccounts' });


//   //Staking v1 Contract
//   var v1stakecontract = new web3.eth.Contract(v1stakeAbi,v1stakeAddress);
//   try
//   {
//       v1stakecontract.methods.stakes(accounts[0]).call((err,result)=>{
//           if (!err) 
//           {
//              allinfo.stakes=result;
//           }
//       })
//   }
//     catch(error)
//   {
//     console.log(error);
//     console.log("Error on stakes contract");
//   }



//   //Contract methods -rate 
//   var contract = new web3.eth.Contract(contractAbi,contractAddress);
//   try
//   {
//       contract.methods.rate(1,usdAddress,leadAddress).call((err,result)=>{
//           if (!err) 
//           {

//              allinfo.rate=result;
//           }
//       })
//   }
//     catch(error)
//   {
//     console.log("Error on contract execution");
//   }

//   //Contract Number method No 6
//   try
//   {
//       contract.methods.getUserRelease(accounts[0]).call((err,result)=>{
//           if (!err) 
//           {

//               setTimer(result);
//               setDate(new Date());
       
//           }
//       })
//   }
//     catch(error)
//   {
//     console.log("Error on contract execution method 6");
//   }

//    //Contract Number method No 7
//    try
//    {
//        contract.methods.getTotalLP().call((err,result)=>{
//            if (!err) 
//            {

//               allinfo.locked=web3.utils.fromWei(web3.utils.toBN(result),'ether');
//            }
//        })
//    }
//      catch(error)
//    {
//      console.log("Error on contract execution method 7");
//    }

//    ////Contract Number method No 10 Basic
//    try
//    {
//        contract.methods.basicROI().call((err,result)=>{
//            if (!err) 
//            {
  
//               allinfo.basicROI=result;
//            }
//        })
//    }
//      catch(error)
//    {
//      console.log("Error on contract execution method 10 Basic");
//    }

//     //Contract Number method No 10 Secondary
//     try
//     {
//         contract.methods.secondaryROI().call((err,result)=>{
//             if (!err) 
//             {

//                allinfo.secondROI=result;
//             }
//         })
//     }
//       catch(error)
//     {
//       console.log("Error on contract execution method 10 Secondary");
//     }

//      //Contract Number method No 10 Tertiary
//      try
//      {
//          contract.methods.tertiaryROI().call((err,result)=>{
//              if (!err) 
//              {

//                 allinfo.tertROI=result;
//              }
//          })
//      }
//        catch(error)
//      {
//        console.log("Error on contract execution method 10 Tertiary");
//      }

//      //Contract Number method No 10 master
//      try
//      {
//          contract.methods.masterROI().call((err,result)=>{
//              if (!err) 
//              {
   
//                 allinfo.masterROI=result;
//              }
//          })
//      }
//        catch(error)
//      {
//        console.log("Error on contract execution method 10 master");
//      }

//      //Contract method number 15 getLPToken
//      try
//      {
//          contract.methods.getUserLPToken(accounts[0]).call((err,result)=>{
//              if (!err) 
//              {
 
//                 allinfo.lpToken=web3.utils.fromWei(result,'ether');
//              }
//          })
//      }
//        catch(error)
//      {
//        console.log("Error on contract execution method 15 get user lp token");
//      }


//       //Contract method number 16 getLPToken
//       try
//       {
//           contract.methods.getUserLiquidity(accounts[0]).call((err,result)=>{
//               if (!err) 
//               {
                
                 
//                   result.USDT_amount=web3.utils.fromWei(web3.utils.toBN(result.USDT_amount),'mwei');
//                   result.LEAD_amount=web3.utils.fromWei(web3.utils.toBN(result.LEAD_amount),'ether');
//                   allinfo.userLiq=result;
//                 //  allinfo.userLiq=web3.utils.fromWei(web3.utils.toBN(result),'ether');
//               }
//           })
//       }
//         catch(error)
//       {
//         console.log("Error on contract execution method 16 get user liquidity based on usdt and lead");
//       }

//       //Contract method number 20 get Pending Bonus
//       try
//       {
//           contract.methods.getUserPendingBonus(accounts[0]).call((err,result)=>{
//               if (!err) 
//               {
      
//                  allinfo.pBonus=web3.utils.fromWei(web3.utils.toBN(result),'ether');
//               }
//           })
//       }
//         catch(error)
//       {
//         console.log("Error on contract execution method 20 get Pending Bonus");
//       }


//        //Contract method number 21 get Pending Bonus
//        try
//        {
//            contract.methods.getUserAvailableBonus(accounts[0]).call((err,result)=>{
//                if (!err) 
//                {
       
//                   allinfo.aBonus=web3.utils.fromWei(web3.utils.toBN(result),'ether');
//                }
//            })
//        }
//          catch(error)
//        {
//          console.log("Error on contract execution method 20 get Pending Bonus");
//        }

 

//   //lead method
//   var leadContract = new web3.eth.Contract(leadAbi,leadAddress);
//   try
//   {
//       leadContract.methods.balanceOf(accounts[0]).call((err,result)=>{
//           if (!err) 
//           {
            
//             allinfo.leadBal=web3.utils.fromWei(result,'ether');
            
//           }
//       })
  
//   }
//   catch(error)
//   {
//     console.log("Error Occured taking lead balance");
//   }



//    //usdt method
//    var usdtContract = new web3.eth.Contract(usdAbi,usdAddress);
//    try
//    {
//        usdtContract.methods.balanceOf(accounts[0]).call((err,result)=>{
//            if (!err) 
//            {
            
//              allinfo.usdtBal=web3.utils.fromWei(result,'mwei');
//            }
//        })
   
//    }
//    catch(error)
//    {
//      console.log("Error Occured usdt balance");
//    }

//  setTimeout(function(){ setPending(false) }, 1000);
//  setData(allinfo);
//  setAccount(accounts[0]);

// }
// else{
//   console.log("Metamask is not installed");
//   setInstall("Please Install Metamask!,Metamask not detected");
// }

// }
