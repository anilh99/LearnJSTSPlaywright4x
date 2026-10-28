function getStatus(code){
    if(code>=200&&code<300){
        console.log("Success");
    }else if(code>=400&&code<500){
        console.log("Client Error");
    }else{
        console.log("Server error");
    }
}
getStatus(201);