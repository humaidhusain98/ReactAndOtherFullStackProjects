var express = require('express');
var bodyparser = require('body-parser');
var cors = require('cors');
var path= require('path');
const mongoose = require('mongoose');

var app = express();

const route = require('./routes/route');

// connect to mongodb
mongoose.connect('mongodb://localhost:27017/testdb',{ useNewUrlParser: true,useUnifiedTopology: true });


//on connection
mongoose.connection.on('connected',()=>{
    console.log("Connected to database @ 2701");
})

mongoose.connection.on('error',(err)=>{
    if(err){
        console.log("Error in Database connection "+err);
    }
})


const port =3000;

//adding middleware - cors
app.use(cors());

//body-parser
app.use(bodyparser.json());

//static files
app.use(express.static(path.join(__dirname,'public')));


app.get('/new',(req,res)=>{
    res.send('foobar');

});

//routes
app.use('/api',route);




app.listen(port,()=>{
    console.log('Server started at port:'+port);
})