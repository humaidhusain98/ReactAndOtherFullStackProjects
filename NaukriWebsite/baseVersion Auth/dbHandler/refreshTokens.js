const mongoose= require("./mongooseConnection");

const refreshSchema = new mongoose.Schema({
 	userId: {
 		type:String,
 		required : true
 	},
 	token:{	userId: {
 		type:String,
 		required : true
 	}}
});

const refresh= mongoose.model('refreshSchema', refreshSchema);

module.exports=refresh;
