/**
 * Created by wqs on 2016/10/26.
 */
/*var x = 1;

switch (x) {
    case 1:
        console.log('x 于1');
    case 2:
        console.log('x 等于2');
    default:
        console.log('x 等于其他值');
}*/
//三元运算符
var b=2;
var msg='the num '+b+' is '+((b%2)===0?' even':' odd');
console.log(msg);
//while循环
//数据类型
var ty_n=typeof  123;
console.log(ty_n);
var null_num;
var test=typeof null_num
console.log(test);
//isNaN
var va=123;
function myIsNaN(va) {
    return typeof va==='number' && isNaN(va)
}
console.log(myIsNaN());
console.log('\251');
console.log('\52')
var f_s=parseFloat('123.5#');
var n_s=Number('123.5');
var i_s=parseInt('123.5#');
console.log(f_s,n_s,i_s)
//对象的定义
var o=new Object();
   o={
       '1p':'你好哦',
         2:123,
         n:'123'
   }
console.log(o['1p']);
console.log(o[2]);
console.log(o.n);
var arr_test=[
    { name: "张三", age: 30 },
    { name: "李四", age: 24 },
    { name: "王五", age: 28  }
].sort();
console.log(arr_test);
var objec=({
    a : 'abc',
    b : 'b',
    c : 'c'
});
console.log(objec['a'])
function funa(){
    this.a=1;
    this.b=2
};
var functionb=new funa();
var a=funa();

console.log(functionb.a);
var o={
    name:'a',
    pass:'a'
};
console.log(o.name);
var stucture=function () {
    this.a='0';
    this.b=1;
};
var s=new stucture();
  stucture.age=''
var mmm=function(){
    this.mmma=1;
  return '10';
}
mmm.prototype.bao='wqs';
var nnb=new mmm();

console.log(nnb.bao);

var reuf=mmm();
console.log(reuf)
stucture.prototype=new mmm();
 var hello = new stucture();
console.log(hello.mmma);
var a = [1,2,3];
function testhuang(){
    for(i in a){
        console.log(i)
    }
};
testhuang();