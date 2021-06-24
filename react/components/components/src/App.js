import './App.css';
import styled from 'styled-components';
import MicExample from './components/micExample';
import WebcamComponent from './components/webcam';

const Content = styled.div`
    margin-top : 100px;
 
`;

const GridLayout = styled.div`
    display : grid;
    grid-template-columns : 10% auto;
    height: calc(100vh - 110px);
    gap : 25px;
    @media (max-width : 768px)
    {
      grid-template-columns : 35% auto;
    }

`;


const SideBarContainer = styled.div`
  margin-top : 100px;
  background-color : white;
  overflow : scroll;
  padding : 5px;
  border : 2px solid black;
  border-radius: 7px;
`;

function App() {
  return (
    <>
     {/* <Navbar />
     <GridLayout>
     <SideBarContainer>
          <SideBar/>
      </SideBarContainer>


     <Content>
          <Counter/>
     </Content>
     
     </GridLayout> */}

     {/* <FileUpload></FileUpload> */}

      {/* <WebcamComponent/> */}
     <MicExample/>
    </>
  );
}

export default App;
