const mongoose=require('mongoose');
mongoose.connect('mongodb://localhost/hrm', {useNewUrlParser: true, useUnifiedTopology: true});
const db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function() {
    console.log("Connection successfull for userModel!");
});


module.exports=mongoose;