pragma solidity ^0.5.16;

import "./DappToken.sol";

contract DappTokenSale {

    address admin;
    DappToken public tokenContract;
    uint256 public tokenPrice;
    uint256 public tokensSold;

    event Sell(address _buyer,uint256 _amount);


    constructor(DappToken _tokenContract,uint256 _tokenPrice) public{
        //assign an admin
        admin = msg.sender;
        tokenContract= _tokenContract;
        tokenPrice= _tokenPrice;
        //Token Contract
        //Token Price
        
    }


    //multiply
    function multiply(uint x, uint y) internal pure returns (uint z) {
        require(y == 0 || (z = x * y) / y == x, "ds-math-mul-overflow");
    }


    //Buy Tokens
    function buyTokens(uint256 _numberOfTokens) public payable {
        //Require that value is equal to tokens
        require(msg.value == multiply(_numberOfTokens ,tokenPrice));
        
        //Require that the contract has enough tokens
        require(tokenContract.balanceOf(address(this)) >= _numberOfTokens);

        //Require that a transfer is successful
        //Keep track of tokensSold
        tokensSold += _numberOfTokens;

        //Trigger Sell Event
         emit Sell(msg.sender,_numberOfTokens);
 
    }

    

}