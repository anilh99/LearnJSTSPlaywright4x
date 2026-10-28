const grade=(score)=>{if(score>=90){
    return "A";
}else if(score>=80){
    return "B";
}else if(score>=70){
    return "C";
}   else{
    return "D";
}
}
console.log(grade(85)); // B
