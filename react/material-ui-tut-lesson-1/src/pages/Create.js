import React,{useState} from 'react'
import { Typography,Button,ButtonGroup,Container,TextField } from '@material-ui/core';
import AcUnitOutlinedIcon from '@material-ui/icons/AcUnitOutlined';
import SendIcon from '@material-ui/icons/Send';
import KeyboardArrowRightIcon from '@material-ui/icons/KeyboardArrowRight';
import { makeStyles } from '@material-ui/core';

const useStyles = makeStyles({

  field : {
    marginTop : 20,
    marginBottom : 20,
    display : 'block'
  }

})


export default function Create() {

  const classes = useStyles();
  const [title,setTitle] = useState('');
  const [details,setDetails] = useState('');
  const [titleError,setTitleError] = useState(false);
  const [detailsError,setDetailsError] = useState(false);

  const handleSubmit = (e) => {

    e.preventDefault()
    setTitleError(false);
    setDetailsError(false);
    if(!title)
      setTitleError(true);
    if(!details)
      setDetailsError(true);

    if(title && details) {
      console.log(title,details);
      
    }

  }

  return (
    <Container>
      <Typography
        variant = "h6"
        component = "h2"
        color = "textSecondary"
        gutterBottom
      >
        Create a New Note
      </Typography>

      <form noValidate autoComplete="off" onSubmit={handleSubmit}>
        <TextField 
        onChange = {(e)=>setTitle(e.target.value)}
        className = {classes.field}
        label="Note Title"
        variant = "outlined"
        color ="secondary"
        fullWidth
        required
        error={titleError}
        />

        <TextField 
        onChange = {(e)=>setDetails(e.target.value)}
        className = {classes.field}
        label="Details"
        variant = "outlined"
        color ="secondary"
        multiline
        rows ={4}
        fullWidth
        required
        error = {detailsError}
        />

      <Button
        type="submit"
        color="secondary"
        variant="contained"
        endIcon = {<KeyboardArrowRightIcon/>}
      >
        Submit
      </Button>

      </form>

     
    </Container>
  )
}
