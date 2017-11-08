/**
 * Created by wqs on 2016/10/28.
 */
var a=1;
function print(a){
    console.log(a);
}
print(a);
var t_function=function (a){
    console.log(a)
};
t_function('你好啊!')
var  t_function2=function t_in(){
    console.log(typeof t_in)
}
t_function2()
var t_function3=function(){
    console.log(3);
}
var t_function3=function(){
    console.log(4);
}
t_function3()//重复定义的函数，前面的被后面的覆盖
function t_function4(x,y){
    return x+y;
}
console.log(t_function4(4,5))//函数的调用返回值，这个和java差不多
function t_fucntion5(num){
    if(num > 2){
        return t_fucntion5(num - 2)+t_fucntion5(num - 1);
    } else {
       return 2;
    }
}
console.log(t_fucntion5(6));
function add(x , y){
    return x + y;
}
function t_function6(o){
    return o;
}
console.log(t_function6(add(8 , 9)));
console.log(t_function6(add)(8 , 9));//这两种都可以
//函数的调用
t_function7();
 function t_function7(){
    console.log('function7被调用了');//由于函数的提升，可以先调用函数，在声明
}
//但是这样就不行了

var v_function8=function(){
    console.log('fucntion8')
}
//函数不能在条件语句中被声明
//函数的作用域
var v=1;
function t_fun9(){
    console.log(v);
}
t_fun9()
//外层定义的函数，被一个当参时，不能使用他内部定义的变量
/*var t_fun10 = function () {
    console.log(t);
};

function y2() {
    var t= 2;
    console.log('将引用t')
    t_fun10()
}
y2(t_fun10())*/
//闭包
function t_fun11(){
    var x=2;
    function bar(){
       return x;
    }
    return bar;
}
var f=t_fun11();
console.log(f())
//参数的转嘀方式
var p='外部变量';

function t_fun12(p){
    p=3;
}
t_fun12(p);
console.log(p);//当传入的是基本数据类型时，给参数重新复制，不会改变原有的全局变量
//但是当为复合类型时 ，传递的是地址，可以改变
var ob={
    p2:'外部对象属性'
}
function t_fun13(o){
    o.p2=3;
}
t_fun13(ob);
console.log(ob.p2)
//函数内部的arguments属性
//闭包
function f1() {
    var n = 999;
    function f2() {
        console.log(n);
    }
    return f2;
}

var result = f1();
result();
function createIn(start){
    return function (){
        return start++;
    };
}
var t_c=createIn(1);
console.log(t_c());
console.log(t_c());
console.log(t_c());
console.log(t_c());
console.log(t_c());
console.log(t_c());//根据依赖原理，可以使得start的值被记住，不会被垃圾回收机制回收
//IIFE 立即调用自己
(function (){
    console.log('立即调用自己')
}());
var   currentNum;
(function cur(){
    currentNum = 2 ;

}());
console.log(currentNum + '全局变量的内部赋值');
