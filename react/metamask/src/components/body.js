
import Typography from '@material-ui/core/Typography';
import Countdown from 'react-countdown';


const Body = ({rate,stakes,release,locked,basic,second,tert,master,onChange}) => {
    
    function handleTimeout() {
        onChange(0);
    }
    
    
    return (
    <>
        <div id="headings">
          <Typography variant="h5" gutterBottom>
              1 USDT = {rate} LEAD 
          </Typography>
          <Typography variant="h5" gutterBottom>
             Pooled 
          </Typography>
          <Typography variant="h5" gutterBottom>
             Return Percentage in Days
          </Typography>
          </div>


          <div id="data">
          <Typography variant="h6" gutterBottom>
              v1 Stakes: {(parseFloat(stakes)).toFixed(3)} LEAD
          </Typography>
          <Typography variant="h6" gutterBottom>
             Locked LP Tokens : {(parseFloat(locked)).toFixed(3)}
          </Typography>
          <Typography variant="h6" gutterBottom>
             1-100:{(basic/1000).toFixed(3)}%   101-300:{(second/1000).toFixed(3)}%
          </Typography>

          </div>
          <div id="data">
          <Typography variant="h6" gutterBottom>
              Release Period :  <Countdown date={Date.now() + (release*1000)} onComplete={handleTimeout}  />
          </Typography>
         
          <Typography variant="h6" gutterBottom>
              Total Supply: {(locked/10000000000000000).toFixed(3)}%
          </Typography>
          <Typography variant="h6" gutterBottom>
              301-1000: {(tert/1000).toFixed(3)}%  1000+ : {(master/1000).toFixed(3)}%
          </Typography>
          </div>
    </> )
}
 
export default Body;