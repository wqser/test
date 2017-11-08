/**
 * Created by wqs on 2016/10/26.
 */

var a = 1;
var b = 2;

console.log(a + b);
obj={
    a:1,
    b:2,
    c:null
};
function isEmptyProperty(value) {
    if (value == null) {
        return true;
    }
    if (typeof value === 'string' && value.trim() === '') {
        return true;
    }
    return false;
}
function test(){
    for(var key in obj){
        if(obj.hasOwnProperty(key)){
            if(isEmptyProperty(obj[key])){
                delete obj[key];
            }else{
                console.log(obj[key]+'  []取值');
            }

        }else{
            console.log("没有");
        }
    }
    return obj;
}
var funt=test();
console.log(funt);
