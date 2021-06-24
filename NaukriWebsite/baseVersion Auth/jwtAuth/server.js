require('dotenv').config();
const express = require('express');
const app = express();
const {generateOTP,validateOTP}=require('./../otpValidator/generateAndValidate');
const jwt = require('jsonwebtoken');
//
const cors = require('cors');
app.use(cors({origin:'http://localhost:3000',credentials:true}));
const cookieParser = require('cookie-parser');
app.use(cookieParser());
//
const userModel=require('./../dbHandler/userModel');
const tokenModel=require('./../dbHandler/refreshTokens');

app.use(express.json());



function authenticateToken(req,res,next)
{
   // const authHeader= req.headers['authorization'];
   // const token = authHeader && authHeader.split(' ')[1];
   const token = req.cookies.jwt;
    if(token ==  null)
        return res.sendStatus(401);

    jwt.verify(token,process.env.ACCESS_TOKEN_SECRET,(err,user)=>{
        if(err) return res.sendStatus(403);
        req.user = user;
        next();
    })

}

app.get('/posts',authenticateToken,async(req,res)=>{
    try
    {
        const allusers=await userModel.find();
        const getUser=await allusers.filter(obj=>obj.mobile==req.user.name);
        res.json(getUser);
    }
    catch(e)
    {
        res.json({message:"The user could not be found"});
    }
 

})

app.get('/register',async(req,res)=>{
    try
    {
        const first_name= req.query.first_name;
        const last_name= req.query.last_name;
        const mobileNumber=req.query.mobileNumber;
        const userobj=new userModel({first_name:first_name,last_name:last_name,mobile:mobileNumber});
        userobj.save();
        const otp = await generateOTP(mobileNumber);
        console.log(otp);
        const message="Your OTP generated for TalGenie is "+otp+" Valid for 2minutes";
        // messageSender(message,mobileNumber,"otpver");
        res.json({message:"User Successfully stored in db"}).status(200);

    }
    catch(e)
    {
        res.json({message:"There has been an error in Registering!"}).status(400);
    }
})


app.get('/generateOTP', async(req, res) => {

    try
    {
        const mobileNumber = req.query.mobileNumber;
        console.log(mobileNumber);
        const userObj = await userModel.findOne({mobile:mobileNumber});
        console.log(userObj);
        if(!userObj) return res.status(400).json({message:"User not Found"});
        const otp = await generateOTP(mobileNumber);
        console.log(otp);
      
        const message="Your OTP generated for TalGenie is "+otp+" Valid for 2minutes";
        // messageSender(message,mobileNumber,"otpver");
        res.status(200).json({message:"OTP sent successfully!"});
    }
    catch(e)
    {
        console.log(e);
        res.status(400).json({message:"There has been an error!!"});
    }
  

  });
  


app.get('/login',async(req,res)=>{
    const mobileNumber = req.query.mobileNumber;
    const otp =req.query.otp;
    console.log(mobileNumber);
    console.log(otp);
    const isValid =await validateOTP(mobileNumber,otp);
    console.log(isValid);
     //authenticate user
    if(isValid)
    {
        const user = { name: mobileNumber };
    
        const accessToken = jwt.sign(user,process.env.ACCESS_TOKEN_SECRET,{ expiresIn: '120s'});
        const refreshToken = jwt.sign(user,process.env.REFRESH_TOKEN_SECRET);
        try
        {
            const tokensave=await new tokenModel({userid:mobileNumber,token:refreshToken});
            await tokensave.save();
            res.status(200)
                .cookie('jwt',accessToken,{
                    sameSite:'strict',
                    path:'/',
                    expires:new Date(new Date().getTime()+120*1000),
                    httpOnly:true 
                })
                .cookie('refresh',refreshToken,{
                    sameSite:'strict',
                    path:'/',
                    httpOnly:true 
                })
            .json({accessToken,refreshToken});
        }
        catch(e)
        {
            res.json({message:"Error in Saving Refresh Token in db"});
        }
        

    }
    else
    {
        res.status(404).json({message:"Authentication Unsuccessful"});
    }
   
});

app.get('/token',(req,res)=>{
    const refreshToken = req.cookies.refresh;
    
    if( refreshToken == null) return res.sendStatus(401);
    if(!tokenModel.exists({token:refreshToken})) return res.sendStatus(403);
    jwt.verify(refreshToken,process.env.REFRESH_TOKEN_SECRET,(err,user)=>{
        if(err) return res.sendStatus(403);
        const accessToken = jwt.sign({name:user.name},process.env.ACCESS_TOKEN_SECRET,{ expiresIn: '120s'});
        res.cookie('jwt',accessToken,{
            sameSite:'strict',
            path:'/',
            expires:new Date(new Date().getTime()+120*1000),
            httpOnly:true 
        }).json({accessToken: accessToken});
    })

    
});

app.delete('/logout',async(req,res)=>{
    try
    {
    const refToken=req.cookies.refresh;
    if(refToken==null) res.sendStatus(401);
    
        const tokenObj=await tokenModel.findOne({token:refToken});
        console.log(tokenObj);
        if(tokenObj)
        {
           await tokenObj.deleteOne();
           res.clearCookie('refresh').clearCookie('jwt');
        }
        res.status(422).json({message:"Refresh Token is not valid"});
    }
    catch(e)
    {
        res.status(403).json({message:e});
    }
   
   
});

app.get("/isAuth",async(req,res)=>{
    const refTok=req.cookies.refresh;
    if(!refTok)
    {
        res.json({valid:false});
    }
    else
    {
        res.json({valid:true});
    }

});

app.get("/isValid",async(req,res)=>{
    const jwt=req.cookies.jwt;
    if(!jwt)
    {
        res.json({valid:false});
    }
    else
    {
        res.json({valid:true});
    }

});


//eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiS3lsZSIsImlhdCI6MTYxNjEyODE2MH0.DykChGRVxEz2zeHZNgrabf1ZKk2ixyPuqguXYaajuHU
app.listen(4000,()=>{
    console.log("Server started on port 4000");
});  
