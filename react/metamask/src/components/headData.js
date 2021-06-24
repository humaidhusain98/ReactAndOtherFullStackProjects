import Typography from '@material-ui/core/Typography';

const HeadData = ({lpToken,leadamt,usdtamt}) => {
    return (   
    <div id="head-data">
    <Typography variant="h6" gutterBottom>
    LP Tokens - {parseFloat(lpToken).toFixed(3)} UNI V2
   </Typography>
   <Typography variant="h6" gutterBottom>
           Liquidity - {parseFloat(leadamt).toFixed(3)} LEAD | {parseFloat(usdtamt).toFixed(3)} USDT
   </Typography>
   </div> );
}
 
export default HeadData;