import {BrowserRouter as Router,Route,Switch} from 'react-router-dom';
import Wallet from './components/wallet';
import NotFound from './components/notFound';
import Home from './components/home';
import Crypto from './components/cryptopages/crypto';
import dict from './abiAddress';
import Create from './components/create';
import './App.css';

var Web3 = require('web3');
//https://mainnet.infura.io/v3/93bd039ddafb4cc4ac8243ca12de4b55
//geth --datadir ./myDataDir --networkid 1114 console 2 --rpc --rpcport 8543 --rpcaddr 127.0.0.1 ---this is important code to start ethereum on rpc

var web3 = new Web3('https://mainnet.infura.io/v3/93bd039ddafb4cc4ac8243ca12de4b55');

function App() {
  return (
    <div className="App">
      <Router>
      <Switch>
            <Route exact path="/">
              <Home web3={web3}/>
            </Route>  
            <Route exact path="/ETH">
              <Crypto name ="Ethereum (ETH)"web3={web3} />
            </Route>
            <Route exact path="/LINK">
              <Crypto name ="Chainlink (LINK)" web3={web3} />
            </Route>
            <Route exact path="/BNB">
              <Crypto name ="Binance coin (BNB)" web3={web3} abi={dict.BNB.abi} address={dict.BNB.address}/>
            </Route>
            <Route exact path="/USDT">
              <Crypto name ="Tether (USDT)" web3={web3} />
            </Route>
            <Route exact path="/USDC">
              <Crypto name ="USD coin (USDC)" web3={web3}/>
            </Route>
            <Route exact path="/WBTC">
              <Crypto name ="Wrapped bitcoin (WBTC)" web3={web3}/>
            </Route>
            <Route exact path="/THETA">
              <Crypto name ="Theta Token (THETA)" web3={web3}/>
            </Route>
            <Route exact path="/VET">
              <Crypto name ="VeChain (VET)" web3={web3}/>
            </Route>

            <Route exact path="/create">
              <Create web3={web3}/>
            </Route>

            <Route exact path="/wallet">
              <Wallet web3={web3}/>
            </Route>

            <Route path="*">
              <NotFound/>
            </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
