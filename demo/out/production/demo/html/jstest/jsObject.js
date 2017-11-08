/**
 * Created by wqs on 2016/10/31.
 */
var person={
    name:'张三',
    describe:function(){
        return '姓名'+this.name;
    }
};
console.log(person.describe());
//this的表示当前所在对象；
//对象的属性可以赋值给其他变量；
var personA={
    name:'李四'
};
personA.describe=person.describe;
console.log(personA.describe());
//当属性值被赋值给另一个对象时。this所指的当前对象会发生改变，
//上面的就发生了改变
function f() {
    return '姓名：'+ this.name;
}

var A = {
    name: '张三a',
    describe: f
};

var B = {
    name: '李四b',
    describe: f
};

console.log(A.describe()) // "姓名：张三"
console.log(B.describe()) // "姓名：李四"
//只要函数被赋值给了另一个数，this对应所在的当前对象会发生变化
var name='wqs';
var f=A.describe;
console.log(f());
//上面的例子中，当a的属性赋值给f后，调用f时，this指向了顶层对象
function va(obj,l,h){
    if((obj.value<l)||(obj.value>h)){
    console.log("年龄不对");
    }
}
//由此可以看到，回调函数中的this是文本框对象

//全局环境用this时，指的是window

//this的应用
//构造函数
//如
var obj=function(p){
    this.p=p;
    this.m=function(){
        return this.p;
    }
}
var  o=new obj('hello world');
console.log(o.p)
console.log(o.m());
//当用到多层this时
//第一层指向当前对象，第二层指向全局
//解决方法：把第一层的this赋值给一个变量，把这个变量传入 
//比如
var o_t={
    f1:function () {
        console.log(this);
        var that=this;
        var f2=function(){
            console.log(that)
        }();
    }
}
o_t.f1();
//数组处理中的this，this的第二层是指顶层对象，解决方法，一个用中间变量

//避免回调函数用this，当不同的对象用到这个函数时，他的this指向也会发生改变

//绑定this
//函数的实例call,绑定函数内部this的指向
//例如
var obj={
    aname:1
};
var f_f=function(){
    return this.aname;
};
console.log(f_f.call(obj));//绑定之后不是一直帮着

//绑定apply，后面可以加数组作为绑定函数的参数


var a=[20,23,54,2,3]

console.log(Math.max.apply(null,a))

//bind没运行一次就会返回一个新的函数
//可以加参数

//prototype属性

//构造函数
function  Cat(name,color){
   this.name=name;
    this.color=color;

}
var cat1=new Cat('mimi','black');
console.log(cat1.name,cat1.color);

//prototype是实力对象的原型对象，修改源性对象的，所有实例对象就会都会体现出来
function Animal (name) {
    this.name = name;
}

Animal.prototype.color = 'white';

var ca2 = new Animal('大毛');
var cat3 = new Animal('二毛');
console.log(cat3.color)
//总结，原型对象定义的所有实例对象共享的属性和方法，他是函数的属性，即构造函数的属性
//每个对象都有自己的原型对象，所以会形成一个链，就是原型链，object.prototype是null
//如果对象自身和它的原型，都定义了一个同名属性，那么优先读取对象自身的属性，这叫做“覆盖”（overiding）。

//constructor指向prototype对象所在的构造函数



//构造函数的继承
//第一步：在子类构造函数中调用父类的构造的函数
function Child(value){
Super.call(this)
    this.prop=value;
    //第二部，子类的原型指向父类的原型
    Child.prototype=Object.create(Super.prototype);
    Child.prototype.constructor=Child;
    Child.prototype.method='....';
}
