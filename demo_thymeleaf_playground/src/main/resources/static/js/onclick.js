function testOnclick() {

    var x = document.getElementsByClassName('testP');

    /**
     *  ==：运算符称作相等，用来检测两个操作数是否相等，这里的相等定义的非常宽松，可以允许进行类型转换
     ===：用来检测两个操作数是否严格相等

     1、对于string,number等基础类型，==和===是有区别的
     不同类型间比较，==之比较“转化成同一类型后的值”看“值”是否相等，===如果类型不同，其结果就是不等
     同类型比较，直接进行“值”比较，两者结果一样

     2、对于Array,Object等高级类型，==和===是没有区别的

     3、基础类型与高级类型，==和===是有区别的
     对于==，将高级转化为基础类型，进行“值”比较，因为类型不同，===结果为false
     */

    if (x[0].innerHTML === 'xxxx'){

        x[0].innerHTML = 'yyyy';
        x[0].style.color = 'red';
    } else {

        x[0].innerHTML = 'xxxx';
        x[0].style.color = "green";
    }
}