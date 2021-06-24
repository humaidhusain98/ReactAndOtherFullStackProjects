const express= require('express');
const app= express();
const cors=require('cors');
const otpRoute =require('./controller/otpRoute');
const cookieParser = require('cookie-parser');
const jobRoute=require('./controller/jobRoute');

app.use(express.json())
app.use(cookieParser());
app.use(cors({origin:'http://localhost:3000',credentials:true}));
app.use("/otp",otpRoute);
app.use("/jobs",jobRoute);

app.get("/isAuth",(req,res)=>{
    const jwt = req.cookies.jwt;
    if(!jwt)
        res.json({auth:false});
    else
        res.json({auth:true});
})

app.listen(4000,()=>{
    console.log("App started on port 4000");
});

