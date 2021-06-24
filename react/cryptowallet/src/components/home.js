
import styled from 'styled-components';

const Home = () => {



    return (   <>
        <Heading><h1>Crypto Currencies:</h1></Heading>
        <List>
            <a href="/ETH">
                <ListItem>Ethereum (ETH)</ListItem>
            </a>

            <a href="/LINK">
                <ListItem>Chainlink (LINK)</ListItem>
            </a>

            <a href="/BNB">
                <ListItem>Binance coin (BNB)</ListItem>
            </a>

            <a href="/USDT">
                <ListItem>Tether (USDT)</ListItem>
            </a>

            <a href="/USDC">
                <ListItem>USD coin (USDC)</ListItem>
            </a>

            <a href="/WBTC">
                <ListItem>Wrapped bitcoin (WBTC)</ListItem>
            </a>

            <a href="/THETA">
                <ListItem>Theta Token (THETA)</ListItem>
            </a>

            <a href="/VET">
                <ListItem>VeChain (VET)</ListItem>
            </a>
        </List>
        </>
    );


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

const List = styled.div`
    margin-top : 5%;
    margin-left : 5%;
    margin-right : 5%;
    font-size : 2em;
    margin
    
`;

const ListItem = styled.p`
    border : 1px solid #f9f9f9;
    padding : 15px;
    border-radius : 5px;
    &:hover {
        background-color : grey;
        
    }
`;

export default Home;