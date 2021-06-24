var QRCode = require('qrcode.react');


const MainContent = () => {
    return ( 
        <>
             <QRCode value="Md Humaid Husain" size={300} />
        </>
     );
}
 
export default MainContent;