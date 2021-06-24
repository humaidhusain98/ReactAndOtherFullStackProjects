const router = require('express').Router();
const jobModel = require('../dbHandler/jobModel');

router.post("/add",async(req,res)=>{
    const obj=req.body;
    try
    {
        const jobObj=new jobModel(obj);
        const resp=await jobObj.save();
        res.json({saved:resp});
    }
    catch(e)
    {
        res.json({saved:false});
    }

});

router.get("/all",async(req,res)=>{
    try
    {
        const allobj=await jobModel.find();
        res.json(allobj);
    }
    catch(e)
    {
        res.json({error:e});
    }
})


module.exports=router;