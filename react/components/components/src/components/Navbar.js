import styled from 'styled-components';
import {useState} from 'react';


const DropdownContainer = styled.div`
    position : fixed;
    top : 60px;
    left : 0;
    right :0;
    background-color : black;
    color :white;
    padding : 30px;
    border : solid 2px #fff;
    border-radius: 5px;
    visibility : hidden;
    opacity : 0;


    @media (max-width : 768px)
    {
        opacity : ${props => props.opacity};
        transition-duration : 250ms;
        visibility : ${props => props.visibility || "hidden"};
    }
`;


const Dropdown = styled.div`
    padding-left : 50px;
    padding-top : 20px;

`;


const DropdownButton = styled.div`
    position : fixed;
    padding-right : 30px;
    right : 0; 
    cursor : pointer;
    img {
    height : 40px;
    width : 40px;
    border-radius : 4px;
    }   
`;



const LogoWrap = styled.div
`   
    img {
        height : 40px;
        width : 40px;
        border-radius : 4px;
    }
`;


const Nav = styled.div`
    position : fixed;
    background-color: #090b13;
    margin-top : 0px;
    color : white;
    top : 0;
    left : 0;
    right : 0; 
    height : 70px;
    display : flex;
    justify-content : flex-start;
    align-items : center;


    ${LogoWrap} {
        padding-left : 20px;
       
    }

    ${DropdownButton} {
        visibility : hidden;
    }

    a {
        padding-left : 90px;
        cursor : pointer;
    }

    @media (max-width : 768px)
    {
        ${LogoWrap} {
            padding-left : 20px;
            visibility : visible;
        }

        a {
            visibility : hidden;
        }

        ${DropdownButton} {
            visibility : visible;
        }
    }

`;

// const Nav= styled.nav`
//     position : fixed;
//     top : 0;
//     left : 0;
//     right: 0;
//     height : 70px;
//     background-color : #090b13;
//     display :flex;
//     justify-content : space-between;
//     align-items :center;
//     padding : 0 36px;
//     letter-spacing : 16px;
//     z-index :3
// `;




const Navbar = () => {
    const [visibility,setVisible] =useState('hidden');
    const [opacity,setOpacity] =useState(0);
    const toggleVisibility =()=>{
        if (visibility === 'hidden')
            {   setVisible('visible');
                setOpacity(1);
            }
        else
            {   setVisible('hidden');
                setOpacity(0);
            }
    }

    return ( <><Nav>
        <LogoWrap><img src='logo.png' alt=''></img></LogoWrap>
        <a onClick={()=>console.log("Home Clicked")}>Home</a>
        <a  onClick={()=>console.log("Watchlist Clicked")}>Watchlist</a>
        <a onClick={()=>console.log("Checklist Clicked")}>Checklist</a>
        <a  onClick={()=>console.log("Videos Clicked")}>Videos</a>
        <a  onClick={()=>console.log("Images Clicked")}>Images</a>
        <DropdownButton onClick={toggleVisibility}><img src='dropdown.png' alt=''></img></DropdownButton>
    </Nav>
    <DropdownContainer visibility={visibility} opacity ={opacity}>
        <Dropdown>Home</Dropdown>
        <Dropdown>Watchlist</Dropdown>
        <Dropdown>Checklist</Dropdown>
        <Dropdown>Videos</Dropdown>
        <Dropdown>Images</Dropdown>
    </DropdownContainer>
    </>
    
    );
}
 
export default Navbar;