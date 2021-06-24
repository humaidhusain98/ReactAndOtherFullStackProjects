import {useState} from 'react';

import './App.css';

function App() {

  const [input,setInput]=useState('');
  const [messages,setMessage]=useState(['Hello','Hi','Whats Up']);

  const sendMessage=(event)=>{
      setMessages([...messages,input]);
      setInput('');
  }

  return (
    <div className="App">
      <h1>Hello</h1>
      <input/>
      <button onChange={event=>setInput(event.target.value)}>Send Message</button>

      {/* Input Field */}
      {/* Button */}
      {/*  */}
    </div>
  );
}

export default App;
