var DappTokenSale = artifacts.require("./DappTokenSale.sol");
var DappToken = artifacts.require("./DappToken.sol");

contract('DappTokenSale',(accounts)=>{
    var tokenInstance;
    var tokenSaleInstance;
    var admin = accounts[0];
    var tokenPrice = 1000000000000000;//in wei
    var tokensAvailable = 750000;
    var buyer= accounts[1];
    var numberOfTokens= 10;

    it('Initializes the contract with the correct values',()=>{
        return DappTokenSale.deployed().then((instance)=>{
            tokenSaleInstance=instance;
            return tokenSaleInstance.address
        }).then((address)=>{
            assert.notEqual(address,0x0,'has contract address');
            return tokenSaleInstance.tokenContract();
        }).then((address)=>{
            assert.notEqual(address,0x0,'has token contract address');
            return tokenSaleInstance.tokenPrice();
        }).then((price)=>{
            assert.equal(price, tokenPrice,'token price is correct');
        })
    });

    it('facilitates token buying',()=>{

        return DappToken.deployed().then((instance)=>{
            //Grab token instance first
            tokenInstance = instance;  
            return DappTokenSale.deployed();
        }).then((instance)=>{
            //Then grab token sale instance
            tokenSaleInstance=instance;
            //Provision 75% of all tokens to the token sale
            return tokenInstance.transfer(tokenSaleInstance.address,tokensAvailable,{ from: admin});
        }).then((receipt)=>{
            var value = numberOfTokens*tokenPrice;
            return tokenSaleInstance.buyTokens(numberOfTokens,{ from: buyer, value: value})
        }).then((receipt)=>{
            assert.equal(receipt.logs.length,1,'triggers one event');
            assert.equal(receipt.logs[0].event,'Sell','should be the "Sell" event');
            assert.equal(receipt.logs[0].args._buyer,buyer,'logs the account that purchased the tokens');
            assert.equal(receipt.logs[0].args._amount,numberOfTokens,'logs the number of tokens purchased');
            return tokenSaleInstance.tokensSold();
        }).then((amount)=>{
            assert.equal(amount.toNumber(),numberOfTokens,'increments the number of tokens sold')
            //Try to buy tokens different from the ether value
            return tokenSaleInstance.buyTokens(numberOfTokens,{ from: buyer, value: 1});
        }).then(assert.fail).catch((error)=>{
            assert(error.message.toString().indexOf('revert')>=0,'msg.value must equal number of tokens in wei');
            return tokenSaleInstance.buyTokens(800000,{ from: buyer, value: numberOfTokens*tokenPrice});
        }).then(assert.fail).catch((error)=>{
            assert(error.message.toString().indexOf('revert')>=0,'cannot purchase more tokens than available');
        })
    })

})
