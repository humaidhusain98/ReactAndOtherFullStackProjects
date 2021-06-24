const mongoose= require("./mongooseConnection");

const jobSchema=new mongoose.Schema({
    dateJobCreated:{
        type:String,
        required :true
    },
    dateTillValid:{
        type:String,
        required:true
    },
    companyId:{
        type:String,
        required:true
    },
    companyTitle:{type:String,required:true},
    companyImageUrl:{type:String,required:true},
    companyDescription:{type:String,required :true},
    category1:{type:String,required:true},
    category2:{type:String,required :true},
    jobTitle:{type:String,required:true},
    salaryStart:{type:Number,required:true},
    salaryEnd:{type:Number,required:true},
    city:{type:String,required:true},
    country:{type:String,required:true},
    expStart:{type:Number,required:true},
    expEnd:{type:Number,required:true},
    jobDescription:{type:String,required:true},
    topRequirements:{type:String,required:true},
    compentencies:{type:String,required:true}
});

const job=mongoose.model('Job',jobSchema);

module.exports=job;