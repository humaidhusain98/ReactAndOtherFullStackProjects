require('dotenv').config();
const router = require('express').Router();
const {generateOTP,validateOTP} = require("../otp/generateAndValidate");
const messageSender= require('../awsMessageSender/sendMessage');
const userModel= require('../dbHandler/userModel');
const jwt=require('jsonwebtoken');

router.get("/generateOTP",async(req,res)=>{
    const mobile = req.query.mobileNumber;
    if(!mobile) res.sendStatus(406);
    const otp = await generateOTP(mobile);
    console.log(otp);
    const message="Your OTP generated for TalGenie is "+otp+" Valid for 2minutes";
    // messageSender(message,mobileNumber,"otpver");
    res.status(200).json({message:"OTP sent successfully!"});

});

//should be enclosed in try catch block because of db connection
router.get('/validateOTP',async(req,res)=>{
    const mobile =req.query.mobileNumber;
    const otp= req.query.otp;
    if(!mobile || !otp) res.sendStatus(406);
    const isValid =await validateOTP(mobile,otp);
    if(!isValid) return res.sendStatus(401)//unauthorized
    const userObj = await userModel.findOne({mobile:mobile});
    console.log(userObj);
    if(userObj) {
        const user = { name: userObj._id };
    
        const accessToken = jwt.sign(user,process.env.ACCESS_TOKEN_SECRET,{ expiresIn: '8640000s'});

        res.cookie('jwt',accessToken,{
            sameSite:'strict',
            path:'/',
            expires:new Date(new Date().getTime()+8640000*1000),
            httpOnly:true 
        }).json({new:false});
        }
    else res.status(200).json({new:true});

});

//should be enclosed in try catch block because of db connection
router.get("/register",async(req,res)=>{
    const mobile =req.query.mobileNumber;
    const fname=req.query.first_name;
    const lname=req.query.last_name;
    if(!mobile || !fname || !lname)
    {
        res.sendStatus(406);
    }
    const userObj=new userModel({mobile:mobile,first_name:fname,last_name:lname});
    if(userObj)
    {
        await userObj.save();
        const user = { name: userObj._id };
        const accessToken = await jwt.sign(user,process.env.ACCESS_TOKEN_SECRET,{ expiresIn: '8640000s'});
        res.cookie('jwt',accessToken,{
            sameSite:'strict',
            path:'/',
            expires:new Date(new Date().getTime()+8640000*1000),
            httpOnly:true 
        }).json({created:true});
    }
    else
    {
        res.json({created:false});
    }
})


router.get("/logout",async(req,res)=>{
   try
   {
    res.clearCookie("jwt").json({success:true});
   }
  catch(e)
  {
      res.status(401).json({success:false});
  }

})

module.exports=router;




