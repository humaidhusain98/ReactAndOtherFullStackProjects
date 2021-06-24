require('dotenv').config();
const express = require('express');
const cors = require('cors');
const bcrypt = require('bcrypt');
const {getUserById,getUserByUsername} = require('./memdb');
const {checkValid,checkSuperAdmin,checkAdmin} = require('./authenticate');
const jwt=require('jsonwebtoken');


const app = express();


app.use(express.json());

app.use(cors({origin:'http://localhost:3000'}));

app.get("/test",(req,res)=>{
    res.json({msg:"success"});
})
//login


app.post("/login",async(req,res)=>{
    const username = req.body.username;
    const password = req.body.password;

    if(!username || !password) res.status(404).json({msg:"password or username missing"});
    else{

        const user = await getUserByUsername(username);
        if(!user) res.status(404).json({msg:"User not found"});
        else
        {
            try
            {
                bcrypt.compare(password, user.password,async(err,result)=>{
                    if(err)
                        res.status(403).json({msg:"Error occured while comparing"});
                    else    
                        {
                            if(result===false) res.status(403).json({msg:"Incorrect Username or password"});
                            else
                            {
                                const jwtUser = {_id:user._id , role:user.role};
                                const accessToken = await jwt.sign(jwtUser,process.env.ACCESS_TOKEN_SECRET,{ expiresIn: '8640000s'});
                                res.json({msg:"Token Successfully Generated after Authentication",token:accessToken});
                            }
                        }
                });
            }
            catch(e)
            {
                res.status(403).json({msg:"Error occured in try block"});
            }

          
        }
        
    }
   
});



app.post("/all",checkValid,(req,res)=>{
    res.json({msg:"success"});
});

app.post("/superadmin",checkSuperAdmin,(req,res)=>{
    res.json({msg:"success"});
})

app.post("/admin",checkAdmin,(req,res)=>{
    res.json({msg:"success"});
})

app.listen(4000,()=>{
    console.log("Server started on port 4000");
})

