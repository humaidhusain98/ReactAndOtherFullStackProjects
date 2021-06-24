

const users = [
    {_id:"126365652fgfgh",role:0,first_name:"SuperAdmin",username:"superadmin",password:"$2b$10$3HtazcnH1rXZbAHvs5f6j.s2KTgRL5tU7H2Q/aInR7/IVkhBbow2a"},
    {_id:"12636hgjhgjgh",role:1,first_name:"Admin",username:"admin",password:"$2b$10$3HtazcnH1rXZbAHvs5f6j.s2KTgRL5tU7H2Q/aInR7/IVkhBbow2a"},
    {_id:"12636565dfhjhggfgh",role:2,first_name:"User",username:"user",password:"$2b$10$3HtazcnH1rXZbAHvs5f6j.s2KTgRL5tU7H2Q/aInR7/IVkhBbow2a"},
    {_id:"126jkjhkjh365652fgfgh",role:3,first_name:"DeActivated",username:"deactivated",password:"$2b$10$avM7E3RkWkxqbO.xc4d01OdA/YRF70AOt6F5GehFH79Bc7jeBTzh6"},
    {_id:"126gfyhghgffdg5652fgfgh",role:1,first_name:"Humaid",username:"humaid",password:"$2b$10$avM7E3RkWkxqbO.xc4d01OdA/YRF70AOt6F5GehFH79Bc7jeBTzh6"},
    {_id:"12dfgfdg6365652fgfgh",role:2,first_name:"Tuba",username:"tuba",password:"$2b$10$avM7E3RkWkxqbO.xc4d01OdA/YRF70AOt6F5GehFH79Bc7jeBTzh6"},
]


const getUserById = (id)=>{

    const result =   users.filter((obj)=>obj._id===id);
    return result[0];

}


const getUserByUsername = (username)=>{

    const result =   users.filter((obj)=>obj.username===username);
    return result[0];

}




module.exports = {getUserById,getUserByUsername};
