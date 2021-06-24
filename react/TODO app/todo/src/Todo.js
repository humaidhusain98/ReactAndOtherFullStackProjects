import React,{useState} from 'react'
import './Todo.css';
import {List,ListItem,Avatar,ListItemAvatar,ListItemText,Button,Modal} from '@material-ui/core';
import db from './firebase';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';
import {makeStyles} from '@material-ui/core/styles';


const useStyles = makeStyles((theme) => ({
    paper: {
      position: 'absolute',
      width: 400,
      backgroundColor: theme.palette.background.paper,
      border: '2px solid #000',
      boxShadow: theme.shadows[5],
      padding: theme.spacing(2, 4, 3),
    },
  }));

  function rand() {
    return Math.round(Math.random() * 20) - 10;
  }
  
  function getModalStyle() {
    const top = 50 + rand();
    const left = 50 + rand();
  
    return {
      top: `${top}%`,
      left: `${left}%`,
      transform: `translate(-${top}%, -${left}%)`,
    };
  }


function Todo(props) {

    const classes=useStyles();

    const [modalStyle] = React.useState(getModalStyle);

    const [open,setOpen]=useState(false);

    const [input,setInput]=useState();

    const handleOpen = ()=>{
        setOpen(true);
    }

    const updateTodo = ()=>{
        
        db.collection('todos').doc(props.text.id).set({
           todo:input 
        },{merge:true});
        setInput('');
        setOpen(false);
    }

    return (
        <>
        <Modal open={open} 
        onClose={e=>setOpen(false)}   
        aria-labelledby="simple-modal-title"
        aria-describedby="simple-modal-description">
            <div style={modalStyle} className={classes.paper}>
                <h1>I am a modal</h1>
                <input placeholder={props.text.todo} value={input} onChange={event=>setInput(event.target.value)}/>
                <Button onClick={updateTodo}>Update Todo</Button>
            </div>
        </Modal>
        
        <List className="todo__list">
            <ListItem>
            <ListItemAvatar>
                <Avatar></Avatar>
            </ListItemAvatar>
            <ListItemText primary={props.text.todo} secondary="Dummy Deadline"></ListItemText>
            </ListItem>
            <Button onClick={e=>setOpen(true)}>Edit</Button>
            <DeleteForeverIcon onClick={event=>db.collection('todos').doc(props.text.id).delete()}>DELETE ME</DeleteForeverIcon>
        </List>
        </>
    )
}

export default Todo;
